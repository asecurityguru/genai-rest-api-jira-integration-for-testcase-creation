

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
public class JiraClient {

    private final RestTemplate restTemplate;

    public JiraClient() {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setInterceptors(Collections.singletonList(new BasicAuthInterceptor("gurusecuity@gmail.com", "ATATT3xFfGF0-Q0TxJnNZV8kjvB9YRM7zMzLtVKjEzmyL546WOmB9vChREAbkQjRT0D0rojQN8nhS6D67bN1E1jFSPDIl-tnDX_AyHCxGQIlo9zIeij6Yu11kUbh1UjeGy3BZEiSLeBcUKeeh2fl8Nl79LSQaN4qlHU0ixc2gXNfdCNI_9XpWuw=D6B56B5C")));
    }

    public String getJiraIssue(String issueKey) {
        String url = "https://asecurityguru.atlassian.net/rest/api/3/issue/" + issueKey;
        return restTemplate.getForObject(url, String.class);
    }

    static class BasicAuthInterceptor implements ClientHttpRequestInterceptor {

        private final String username;
        private final String password;

        public BasicAuthInterceptor(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64Utils.encode(auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);

            HttpHeaders headers = request.getHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, authHeader);

            return execution.execute(request, body);
        }
    }
}
