package com.bagautdinov.http;

import java.util.HashMap;
import java.util.Map;

public class MainRequestsTest {
    public static void main(String[] args) {
        HttpClient client = new HttpClient();

        System.out.println("GET");
        Map<String, String> params = new HashMap<>();
        params.put("userId", "1");
        String getResponse = client.get("https://jsonplaceholder.typicode.com/posts", null, params);
        System.out.println(getResponse);

        System.out.println("\nPOST");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer a54f579d4c48b5b6db9791e0299f76f1db7497d37856feeac01811332bbd317a");
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        Map<String, String> postData = new HashMap<>();
        postData.put("name", "Artur");
        postData.put("email", "artur.test" + System.currentTimeMillis() + "@gmail.com");
        postData.put("gender", "male");
        postData.put("status", "active");

        String postResponse = client.post("https://gorest.co.in/public/v2/users", headers, postData);
        System.out.println(postResponse);

        String createdUserId = extractIdFromJson(postResponse);
        System.out.println("Created user ID: " + createdUserId);

        if (createdUserId != null) {
            System.out.println("\nPUT");
            Map<String, String> putData = new HashMap<>();
            putData.put("name", "Artur Updated");
            putData.put("email", "artur.updated" + System.currentTimeMillis() + "@gmail.com");
            putData.put("gender", "male");
            putData.put("status", "inactive");

            String putResponse = client.put("https://gorest.co.in/public/v2/users/" + createdUserId, headers, putData);
            System.out.println(putResponse);

            System.out.println("\nDELETE");
            String deleteResponse = client.delete("https://gorest.co.in/public/v2/users/" + createdUserId, headers, null);
            System.out.println(deleteResponse);
        } else {
            System.out.println("Failed to extract user ID from POST response");
        }
    }

    private static String extractIdFromJson(String json) {
        try {
            int idIndex = json.indexOf("\"id\":");
            if (idIndex != -1) {
                int start = json.indexOf(":", idIndex) + 1;
                int end = json.indexOf(",", start);
                if (end == -1) end = json.indexOf("}", start);

                String idStr = json.substring(start, end).trim();
                return idStr;
            }
        } catch (Exception e) {
            System.out.println("Error parsing ID: " + e.getMessage());
        }
        return null;
    }
}