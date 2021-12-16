import { buildSchema } from 'graphql';

// Construct a schema, using GraphQL schema language
export const schema = buildSchema(`
type Query {
  hello: String
}

  type Event {
    id: ID!
    city: String!
    planning: String!
  }

  input EventInput {
      city: String!
  }

  type QueryResolver {
      getEvents: [Event!]!
  }

  type MutationResolver {
      createEvent(eventInput: EventInput): [Event!]!
      deleteEvent(id: ID!): ID
  }

  schema {
      query: QueryResolver
      mutation: MutationResolver
  }
`);
