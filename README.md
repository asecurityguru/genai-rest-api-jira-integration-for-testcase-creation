# Jira Stories Test Case Generation Application with Ollama Model Integration

## Overview

The Jira Stories Test Case Generation Application is a Spring Boot application designed to fetch user story details from Jira and generate test cases based on the acceptance criteria. It integrates with the Ollama AI model to process natural language inputs and automatically generate detailed test cases.

## Project Structure

- **JiraInquiryController**:
  - Handles HTTP requests for Jira story inquiries.
  - Utilizes the Ollama AI model to process acceptance criteria and generate relevant test cases.

- **JiraFunctionConfig**:
  - Configures and provides the `JiraDataService` bean.
  - Sets up the Jira function used by the AI model to fetch Jira story details and generate test cases.

- **JiraDataService**:
  - Manages communication with the Jira API to fetch user story details, including acceptance criteria.
  - Reads Jira API credentials and URL from configuration properties.
  - Parses the Jira API responses to extract relevant information.

- **JiraApiProperties**:
  - Stores Jira API credentials (username, API token) and the base URL.
  - Provides configuration properties for `JiraDataService`.

## Configuration

1. **Set up your environment**:
   - Ensure you have Java 17 and Gradle installed.

2. **Configure the application properties**:
   - Update `src/main/resources/application.properties` with your JIRA API credentials:
     ```properties
     jira.username=email@domain.com
     jira.apiToken=YOUR_JIRA_API_TOKEN
     jira.apiUrl=https://jira.attlasian.net/v1
     ```

## Running the Application

1. **Build and Start the Application**:
   ```sh
   ./gradlew clean build bootRun


2. **Send Request to the Application from Postman/Httpie or any other tool**:
   ```bash
   GET http://localhost:8080/api/v1/jira-story?storyKey=SCRUM-2


## CREDITS
Raghu The Security Expert and ASG
