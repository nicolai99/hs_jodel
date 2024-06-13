package com.example.jodel.authentification;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

public class NameConverter {
    public String name;
    public String sub;

    public NameConverter(String authorizationHeader) {
        String token = authorizationHeader.substring(7); // Remove "Bearer "
        String[] parts = token.split("\\.");
        String payload = parts[1];
        // Decode Base64
        byte[] decodedBytes = Base64.decodeBase64(payload.getBytes());

        try {
            // Convert to JSON string
            String jsonStr = new String(decodedBytes, "UTF-8");
            JSONObject json = new JSONObject(jsonStr);
            this.name = json.getString("preferred_username");
            this.sub = json.getString("sub");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
