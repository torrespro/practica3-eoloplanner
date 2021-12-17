import { buildSchema } from 'graphql';

// Construct a schema, using GraphQL schema language
export const schema = buildSchema(`
  type EolicPlant {
    id: ID!
    city: String!
    planning: String!
  }

  input EolicPlantInput {
      city: String!
  }

  type QueryResolver {
      getEolicPlants: [EolicPlant!]!
  }

  type MutationResolver {
      createEolicPlant(eolicPlantInput: EolicPlantInput): [EolicPlant!]!
      deleteEolicPlant(id: ID!): ID
  }

  schema {
      query: QueryResolver
      mutation: MutationResolver
  }
`);
