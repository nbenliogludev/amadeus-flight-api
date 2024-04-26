# About the project

This is the Backend (Java) Developer Case Assignment within the Future Talent Program of Amadeus & Coderspace.

author: [Nikolay Benlioglu](https://github.com/nikben08)
## Technologies Used

- Java
- Spring Boot
- PostgreSQL
- Docker

## Check out the Swagger API documentation 📦

You need to run the project on your local environment first.

localhost:8080/swagger-ui/index.html

Prepare your PostgreSQL server and then seed your airports and flights using the /api/v1/jobs/trigger [POST] endpoint!

## Check out the Swagger API documentation 📦

Run in Postman 🚀

You will need to set up Basic Authentication in Postman before executing any requests. To do this, you should create an API user with appropriate credentials.

## How to use?

Description
This API provides access to comprehensive flight data based on given parameters. It allows users to search for flights between specified departure and arrival airports on a given departure date. Optionally, users can also retrieve return flights for the same airports by providing a return date.

Search Endpoint
/api/v1/flights/search

Method
GET

## Request Parameters

