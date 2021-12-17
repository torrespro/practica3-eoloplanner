import sequelize_pkg from 'sequelize';

//Sequelize doesn't use named exports
const { Sequelize, Model, DataTypes } = sequelize_pkg;
const sequelize = new Sequelize('eoloplantsDB', 'root', 'password', {
  dialect: 'mysql'
});

export class EolicPlant extends Model { }

  EolicPlant.init({
    city: DataTypes.STRING,
    planning: DataTypes.STRING
  }, { sequelize, modelName: 'eolicplant' });
  
  await sequelize.sync({ force: true });

  await EolicPlant.create({
    city: 'Madrid',
    planning: 'madrid-sunny-flat'
  });


