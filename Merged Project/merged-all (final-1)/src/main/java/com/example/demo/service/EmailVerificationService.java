package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EmailVerificationService {

    @Value("${email.verification.api.key}")
    private String apiKey;

    @Value("${email.verification.api.url}")
    private String apiUrl;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Use Jackson ObjectMapper

    public boolean verifyEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("api_key", apiKey)
                    .queryParam("email", email);

            String url = uriBuilder.toUriString();
            String response = restTemplate.getForObject(url, String.class);
            System.out.println(response);

            // Parse the response
            return parseResponse(response);
        } catch (Exception e) {
            System.err.println("Error verifying email: " + e.getMessage());
            e.printStackTrace();
            return false; // Return false if there's an error
        }
    }

    private boolean parseResponse(String response) {
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            // Example checks based on your JSON structure
            String deliverability = jsonNode.path("deliverability").asText();
            boolean isValidFormat = jsonNode.path("is_valid_format").path("value").asBoolean();

            // Check if the email is deliverable and has a valid format
            return "DELIVERABLE".equalsIgnoreCase(deliverability) && isValidFormat;
        } catch (Exception e) {
            System.err.println("Error parsing response: " + e.getMessage());
            e.printStackTrace();
            return false; // Return false if parsing fails
        }
    }
}
