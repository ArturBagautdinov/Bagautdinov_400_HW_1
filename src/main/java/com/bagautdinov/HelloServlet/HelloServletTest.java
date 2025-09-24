package com.bagautdinov.HelloServlet;

import com.bagautdinov.http.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class HelloServletTest {
    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        String url = "http://localhost:8080/hello";

        System.out.println("Get");
        String getResponse = client.get(url, null, null);
        System.out.println(getResponse);

        System.out.println("\nPost");
        Map<String, String> postData = new HashMap<>();
        postData.put("message", "Hello World");
        String postResponse = client.post(url, null, postData);
        System.out.println(postResponse);

        System.out.println("\nPut");
        Map<String, String> putData = new HashMap<>();
        putData.put("update", "Hello World, Im ");
        String putResponse = client.put(url, null, putData);
        System.out.println(putResponse);

        System.out.println("\nDelete");
        String deleteResponse = client.delete(url, null, null);
        System.out.println(deleteResponse);
    }
}