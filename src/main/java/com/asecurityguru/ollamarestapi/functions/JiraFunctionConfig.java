package com.asecurityguru.ollamarestapi.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class JiraFunctionConfig {

    private final JiraApiProperties jiraApiProperties;

    public JiraFunctionConfig(JiraApiProperties jiraApiProperties) {
        this.jiraApiProperties = jiraApiProperties;
    }

    @Bean
    @Description("Provides a function to fetch Jira story details and generate test cases.")
    public Function<JiraDataService.Request, JiraDataService.Response> jiraFunction()  {
        // Validate Jira configuration properties
        validateJiraConfigProperties(jiraApiProperties);

        // Create and return a new JiraDataService instance
        return new JiraDataService(jiraApiProperties);
    }

    private void validateJiraConfigProperties(JiraApiProperties properties) {
        if (properties.getApiUrl() == null || properties.getApiUrl().isEmpty()) {
            throw new IllegalArgumentException("Jira API URL must be provided.");
        }
        if (properties.getApiToken() == null || properties.getApiToken().isEmpty()) {
            throw new IllegalArgumentException("Jira API token must be provided.");
        }
        if (properties.getUsername() == null || properties.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Jira username must be provided.");
        }
    }
}
