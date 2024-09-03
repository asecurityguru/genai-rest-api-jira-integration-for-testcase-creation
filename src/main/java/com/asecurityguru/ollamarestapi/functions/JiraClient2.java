// package com.asecurityguru.ollamarestapi.functions;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;

// public class JiraClient2 {

//     private static final String API_URL = "https://asecurityguru.atlassian.net/rest/api/3/issue/";

//     public static void main(String[] args) {
//         String username = "gurusecuity@gmail.com";
//         String apiToken = "ATATT3xFfGF0-Q0TxJnNZV8kjvB9YRM7zMzLtVKjEzmyL546WOmB9vChREAbkQjRT0D0rojQN8nhS6D67bN1E1jFSPDIl-tnDX_AyHCxGQIlo9zIeij6Yu11kUbh1UjeGy3BZEiSLeBcUKeeh2fl8Nl79LSQaN4qlHU0ixc2gXNfdCNI_9XpWuw=D6B56B5C";
//         String issueKey = "SCRUM-1"; // Example issue key

//         try {
//             // Create URL and HttpURLConnection
//             URL url = new URL(API_URL + issueKey);
//             HttpURLConnection connection = (HttpURLConnection) url.openConnection();

//             // Set HTTP method and authentication
//             connection.setRequestMethod("GET");
//             String auth = username + ":" + apiToken;
//             String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
//             connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

//             // Read response
//             BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//             String inputLine;
//             StringBuilder response = new StringBuilder();
//             while ((inputLine = in.readLine()) != null) {
//                 response.append(inputLine);
//             }
//             in.close();

//             // Print the response
//             System.out.println("Response: " + response.toString());

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
