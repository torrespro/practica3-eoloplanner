import express from 'express';
import cors from 'cors';
import { graphqlHTTP } from 'express-graphql';
import path from 'path';
const __dirname = path.resolve();
import { credentials } from '@grpc/grpc-js';
import { WeatherService } from './proto.js';
import { promisify } from 'util';
import fetch from 'node-fetch';
import { EolPlant } from './database.js';
import { schema } from './schema.js';
import { toPlainObj, toUpperCase } from './utils.js';

const topographicServiceURL = "http://localhost:8080/api/v1/topographicdetails/";

var weatherService = new WeatherService('localhost:9090', credentials.createInsecure());
weatherService.GetWeather = promisify(weatherService.GetWeather.bind(weatherService));

// The root provides a resolver function for each API endpoint
const root = {
  getEvents: async (parent, args, context, info) => {
    const rows = await EolPlant.findAll();
    return toPlainObj(rows);
  },
  createEvent: async (parent, args, context, info) => {
    const city = parent.eventInput.city.trim().replace(/^\w/, (c) => c.toUpperCase());
    var planning = city;
    var tasks = [
      (async () => {
        var result = await weatherService.GetWeather({ city: city });
        planning += "-" + result.weather
      })(),
      (async () => {
        var result = await (await fetch(topographicServiceURL + city)).json();
        planning += "-" + result.landscape;
      })()
    ]

    await Promise.all(tasks);

    const planta = await EolPlant.create({ city: city, planning: toUpperCase(planning)});
    console.log('Customer inserted with id:', planta.id);
    const rows = EolPlant.findAll({ where: { 'id': planta.id } });
    return rows;
  },
  deleteEvent: async (parent, args, context, info) => {
    const id = parent.id;
    await EolPlant.destroy({ where: { id } });
    return id;
  },
};

const app = express();

//Allow requests from browser apps loaded in other domain
app.use(cors());

app.use('/graphql', graphqlHTTP({
  schema: schema,
  rootValue: root,
  graphiql: true,
  pretty: true,
}));

app.use(express.static(path.join(__dirname, '/static')))

app.listen(3000, () => {
  console.log('Running a GraphQL API server at http://localhost:3000/graphql');
});
