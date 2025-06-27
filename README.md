# CyWeather Application
## Overview
A full-stack weather application built with Spring Boot (backend), React (frontend), and PostgreSQL database, dockerized with Docker Compose for easy setup and deployment. **Init page is included for case requirements, application doesn't require init page to run.**

## Features

 - Search cities and display current and forecast weather data

 - Fetch and store weather data from external APIs

 - Responsive React frontend with multiple views

 - Backend REST API with Spring Boot and JPA

 - Data persistence with PostgreSQL

 - Easy local deployment using Docker Compose

## Prerequisites

 - Docker and Docker Compose installed on your machine


## Getting Started
1. Clone the repository
  ```sh
  git clone https://github.com/yourusername/cyweather.git
  cd cyweather
  ```
2. Insert your API key
   Open docker-compose.yml and type your api key in the WEATHERAPI_KEY parameter.

   If you don't have an API key, you can get one from https://www.weatherapi.com/
   
3. Build and start the application with Docker Compose

This command will:

  - Build backend and frontend images

  - Start backend, frontend, and PostgreSQL containers

  - Set up networking between containers
```sh
  docker-compose up --build
```
### Access the application

  Frontend: http://localhost:3000

  Backend API: http://localhost:8080

### Environment Variables

The project uses environment variables to configure API URLs and database connection details.

   Backend service reads PostgreSQL config from docker-compose.yml.

   Backend service reads API key from docker-compose.yml.
  

### Testing

You can run backend unit and integration tests locally:

```sh
  cd backend
  mvn clean test
```

**Keep in mind tests are not finalized, thus some funcionalities may not have appropriate tests.**

### Troubleshooting

  Make sure Docker daemon is running

  Ports 80 (frontend) and 8080 (backend) must be free on your machine

  Check logs for backend or frontend if something is not working:
  ```sh
    docker-compose logs backend
    docker-compose logs frontend
  ```
