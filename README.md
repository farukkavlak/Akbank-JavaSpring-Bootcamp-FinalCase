# 
<h1 align="center">Akbank Bootcamp Final Project Backend Part</h1>
<h1 align="center">Ömer Faruk Kavlak</h1><br>

 <p align="center">
  &#8505; <a href="#demo">Demo</a> 
  &#8505; <a href="#project-details">Project Details</a> 
  &#8505; <a href="#technologies">Technologies</a> 
  &#8505; <a href="#project-requirements">Project Requirements</a> 
  &#8505; <a href="#test">Test</a> 
  &#8505; <a href="#swagger-screenshots">Swagger Screenshots</a> 
</p>

## Frontend Repo
https://github.com/farukkavlak/Akbank-JavaSpring-Bootcamp-FinalCase-Frontend

## Demo
#### Since used free deployment services, there may be some delays in requests.
https://user-images.githubusercontent.com/79375232/177862614-1692973a-f8d3-4a58-893a-40978c7559f7.mp4

## Project Details

The main purpose of the application is to read the air pollution information(CO,O3,SO2 values) according to the City Name and Date Range using https://openweathermap.org/api, save it in the database and display it to the user.

## Technologies
<ul>
  <li>Frontend = ReactJS</li>
  <li>Backend = Java Spring Boot </li>
  <li>External Api = https://openweathermap.org/api</li>
  <li>Database = PostgreSQL</li>
</ul>

## Project Requirements
- Weather Forecast (https://openweathermap.org/forecast5) and Geocoding (https://openweathermap.org/api/geocoding-api) endpoints will be used.✔️
- With the help of the City Name information and the Geocoding API, the coordinate(lat,lon) information of the relevant city will be obtained.✔️
- According to these results, 5-day, 3-hourly weather forecast data will be obtained.✔️
- Authentication structure will be established.✔️
- Users can create an account to save their city and view the weather forecast for the saved cities.✔️
- The cities searched by users and their predictions will be kept in the db.✔️
- Even if the requester is not registered, the estimates of the cities he called will be kept in the database.✔️
- Error Handling.✔️

## Test
- Tests were partial due to time constraints, only controls were tested

## Swagger Screenshots
#### Controller
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/Controller.png"></img>
#### Schemas from Heroku
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/Schema1.png"></img>
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/Schema2.png"></img>
#### PostgreSQL Database in Heroku
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/HerokuPostgreSQLDatabase.png"></img>

#### Get Weather From Api and Log
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherFromApi.png"></img>
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherFromApiLog.png"></img>

#### Get Weather From Db and then Api and Log
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherFromApiDb.png"></img>
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherFromApiDbLog.png"></img>

#### Get Weather From Api-Db-Api and Log
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherFromApiDbApi.png"></img>
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherFromApiDbApiLog.png"></img>

#### Get Weather Without Date (Default last week)
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherWithoutDate.png"></img>

#### Error - Restrict Date - Earlier than 27 November 2020 and Log
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherError2.png"></img>
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherError2Log.png"></img>

#### Error - Last Date cannot be earlier than First Date
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherError.png"></img>
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/getWeatherErrorLog.png"></img>

#### Delete Weather
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/deleteWeather.png"></img>

#### Delete Weather Error - Doesn't Exists in DB 
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/deleteWeatherError.png"></img>
<img src="https://github.com/farukkavlak/MobileActionFinalBackend/blob/main/SwaggerUI/deleteWeatherErrorLog.png"></img>





