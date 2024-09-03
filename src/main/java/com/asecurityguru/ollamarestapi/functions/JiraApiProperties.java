package com.asecurityguru.ollamarestapi.functions;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jira")
public class JiraApiProperties {

    private String username;
    private String apiToken;
    private String apiUrl;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public String toString() {
        return "JiraApiProperties{" +
                "username='" + username + '\'' +
                ", apiToken='" + apiToken + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                '}';
    }
}
