package com.example.jodel.city.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Location {

    public static void main(String[] args) {
        Location loc = new Location();
        loc.getLocation(48.9422756, 9.2892781);
    }

    public String getLocation(double lat, double lon) {
        try {
            String uriString = "https://nominatim.openstreetmap.org/reverse?lat=" + lat + "&lon=" + lon
                    + "&format=json";
            System.out.println(uriString);
            URI uri = new URI(uriString);
            URL url = uri.toURL();
            InputStream is = url.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = mapper.readValue(rd, Map.class);
            Map<String, Object> address = (Map<String, Object>) map.get("address");
            String village = (String) address.get("village");
            System.out.println(village);

            rd.close();
            is.close();
            return village;
        } catch (Exception e) {
            return "Error in get Location";
        }

    }

}
