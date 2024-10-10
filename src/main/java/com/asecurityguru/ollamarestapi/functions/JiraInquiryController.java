package com.asecurityguru.ollamarestapi.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
public class JiraInquiryController {
    private static final Logger log = LoggerFactory.getLogger(JiraInquiryController.class);
    private final ChatClient chatClient;
    private final Function<JiraDataService.Request, JiraDataService.Response> jiraFunction;

    public JiraInquiryController(ChatClient.Builder chatClientBuilder,
                                 Function<JiraDataService.Request, JiraDataService.Response> jiraFunction) {
        this.chatClient = chatClientBuilder
                         .defaultFunctions("jiraFunction")
                         .build();
        this.jiraFunction = jiraFunction;
    }

    @GetMapping("/api/v1/jira-story")
    public String getJiraStoryDetails(@RequestParam String storyKey) {

        try {
            // Fetch Jira story details
            JiraDataService.Response jiraResponse = jiraFunction.apply(new JiraDataService.Request(storyKey));

            // Generate test cases using the AI model based on acceptance criteria
            String prompt = "Based on the following acceptance criteria, generate detailed selenium script using Java:\n\n" 
                            + jiraResponse.acceptanceCriteria();

            String aiResponse = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            log.info("AI response: {}", aiResponse);
            return aiResponse;
        } catch (Exception e) {
            log.error("Error processing request for storyKey: {}", storyKey, e);
            return "Unable to process your request at this time.";
        }
    }
}
