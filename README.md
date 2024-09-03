# Weather Inquiry Application with Ollama Model Integration - Feeding live weather data to Ollama Models

## Overview

The Weather Inquiry Application is a Spring Boot application designed to provide weather information based on user queries about cities. It leverages AI to process natural language input and fetches real-time weather data from an external API.

## Project Structure

1. **`WeatherInquiryController`**:
   - Handles HTTP requests for weather inquiries.
   - Uses the AI model to process user messages and return weather information.

2. **`WeatherFunctionConfig`**:
   - Configures and provides the `WeatherDataService` bean.
   - Sets up the weather function used by the AI model to fetch weather data.

3. **`WeatherDataService`**:
   - Configures and communicates with the weather API to fetch current weather data.
   - Reads API key and URL from properties.
   - Maps API responses into Java records.

4. **`WeatherApiProperties`**:
   - Stores API key and URL for the weather service.
   - Provides configuration properties for `WeatherDataService`.

## Configuration

1. **Set up your environment**:
   - Ensure you have Java 17 and Gradle installed.

2. **Configure the application properties**:
   - Update `src/main/resources/application.properties` with your weather API credentials:
     ```properties
     weather.apiKey=YOUR_API_KEY
     weather.apiUrl=https://api.weatherapi.com/v1
     ```

## Running the Application

1. **Build the project**:
   ```sh
   ./gradlew clean build bootRun


## CREDITS
Raghu The Security Expert and ASG
