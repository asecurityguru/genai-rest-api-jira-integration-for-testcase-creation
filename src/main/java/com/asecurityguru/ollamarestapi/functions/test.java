package com.asecurityguru.ollamarestapi.functions;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class test {

    private static final Logger log = LoggerFactory.getLogger(test.class);
    private final JiraRestClient jiraRestClient;

    public test(String apiUrl, String username, String apiToken) {
        try {
            // Initialize Jira REST client
            URI jiraUri = new URI(apiUrl);
            this.jiraRestClient = new AsynchronousJiraRestClientFactory()
                    .createWithBasicHttpAuthentication(jiraUri, username, apiToken);

            log.info("Jira API URL: {}", apiUrl);
            log.info("Jira Username: {}", username);
        } catch (URISyntaxException e) {
            log.error("Invalid Jira API URL", e);
            throw new IllegalArgumentException("Invalid Jira API URL", e);
        } catch (Exception e) {
            log.error("Failed to initialize Jira REST client", e);
            throw new RuntimeException("Failed to initialize Jira REST client", e);
        }
    }

    public Response fetchIssueDetails(Request request) {
        try {
            log.info("Fetching details for story: {}", request.storyKey());

            // Fetch the Jira issue details
            Issue issue = jiraRestClient.getIssueClient().getIssue(request.storyKey()).claim();

            log.info("Fetched issue details successfully for story: {}", request.storyKey());

            return new Response(
                    issue.getKey(),
                    issue.getSummary(),
                    issue.getDescription(),
                    issue.getPriority().getName(),
                    issue.getStatus().getName(),
                    issue.getAssignee() != null ? issue.getAssignee().getDisplayName() : "Unassigned"
            );
        } catch (Exception e) {
            log.error("Error fetching Jira issue details for story: {}", request.storyKey(), e);
            throw new RuntimeException("Failed to fetch Jira issue details for story: " + request.storyKey(), e);
        }
    }

    // Records to map Jira issue details
    public static class Request {
        private final String storyKey;

        public Request(String storyKey) {
            this.storyKey = storyKey;
        }

        public String storyKey() {
            return storyKey;
        }
    }

    public static class Response {
        private final String key;
        private final String summary;
        private final String description;
        private final String priority;
        private final String status;
        private final String assignee;

        public Response(String key, String summary, String description, String priority, String status, String assignee) {
            this.key = key;
            this.summary = summary;
            this.description = description;
            this.priority = priority;
            this.status = status;
            this.assignee = assignee;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "key='" + key + '\'' +
                    ", summary='" + summary + '\'' +
                    ", description='" + description + '\'' +
                    ", priority='" + priority + '\'' +
                    ", status='" + status + '\'' +
                    ", assignee='" + assignee + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        // Replace these values with your actual Jira API credentials and URL
        String apiUrl = "https://asecurityguru.atlassian.net/rest/api/3/issue/";
        String username = "gurusecuity@gmail.com";
        String apiToken = "ATATT3xFfGF0-Q0TxJnNZV8kjvB9YRM7zMzLtVKjEzmyL546WOmB9vChREAbkQjRT0D0rojQN8nhS6D67bN1E1jFSPDIl-tnDX_AyHCxGQIlo9zIeij6Yu11kUbh1UjeGy3BZEiSLeBcUKeeh2fl8Nl79LSQaN4qlHU0ixc2gXNfdCNI_9XpWuw=D6B56B5C";

        test service = new test(apiUrl, username, apiToken);

        // Example request
        Request request = new Request("YOUR-ISSUE-KEY");
        Response response = service.fetchIssueDetails(request);

        // Output response
        System.out.println(response);
    }
}
