package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailVerificationService {

    @Value("${emailverification.api.url}")
    private String apiUrl;

    @Value("${emailverification.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public EmailVerificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean verifyEmail(String email) {
        String url = String.format("%s?api_key=%s&email=%s", apiUrl, apiKey, email);
        try {
            // Log the constructed URL
            System.out.println("Constructed URL: " + url);

            // Make the API request
            ResponseEntity<VerificationResponse> response = restTemplate.getForEntity(url, VerificationResponse.class);
            System.out.println("API Response Status Code: " + response.getStatusCode());
            System.out.println("API Response Body: " + response.getBody());

            // Check if the response is not null and contains data
            VerificationResponse verificationResponse = response.getBody();
            if (verificationResponse != null) {
                String status = verificationResponse.getStatus();
                System.out.println("Email verification status: " + status);
                // Return true if the status indicates the email is valid
                return "valid".equalsIgnoreCase(status);
            } else {
                System.err.println("Verification response is null");
            }
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error verifying email: " + e.getMessage());
            e.printStackTrace();
        }
        // Return false if any issues occur
        return false;
    }

    // Update VerificationResponse class to match the actual response structure
    static class VerificationResponse {
        private String address;
        private String status;
        private String sub_status;
        private boolean free_email;
        private String did_you_mean;
        private String account;
        private String domain;
        private String domain_age_days;
        private String smtp_provider;
        private String mx_found;
        private String mx_record;
        private String firstname;
        private String lastname;
        private String gender;
        private String country;
        private String region;
        private String city;
        private String zipcode;
        private String processed_at;

        // Getters and Setters
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSub_status() {
            return sub_status;
        }

        public void setSub_status(String sub_status) {
            this.sub_status = sub_status;
        }

        public boolean isFree_email() {
            return free_email;
        }

        public void setFree_email(boolean free_email) {
            this.free_email = free_email;
        }

        public String getDid_you_mean() {
            return did_you_mean;
        }

        public void setDid_you_mean(String did_you_mean) {
            this.did_you_mean = did_you_mean;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getDomain_age_days() {
            return domain_age_days;
        }

        public void setDomain_age_days(String domain_age_days) {
            this.domain_age_days = domain_age_days;
        }

        public String getSmtp_provider() {
            return smtp_provider;
        }

        public void setSmtp_provider(String smtp_provider) {
            this.smtp_provider = smtp_provider;
        }

        public String getMx_found() {
            return mx_found;
        }

        public void setMx_found(String mx_found) {
            this.mx_found = mx_found;
        }

        public String getMx_record() {
            return mx_record;
        }

        public void setMx_record(String mx_record) {
            this.mx_record = mx_record;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getProcessed_at() {
            return processed_at;
        }

        public void setProcessed_at(String processed_at) {
            this.processed_at = processed_at;
        }
    }
}
