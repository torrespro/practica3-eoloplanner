import sequelize_pkg from 'sequelize';

//Sequelize doesn't use named exports
const { Sequelize, Model, DataTypes } = sequelize_pkg;
const sequelize = new Sequelize('eoloplantsDB', 'root', 'password', {
  dialect: 'mysql'
});

export class EolPlant extends Model { }

  EolPlant.init({
    city: DataTypes.STRING,
    planning: DataTypes.STRING
  }, { sequelize, modelName: 'eolplant' });
  
  await sequelize.sync({ force: true });

  await EolPlant.create({
    city: 'Madrid',
    planning: 'madrid-sunny-flat'
  });


