package com.example.jodel.city.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jodel.city.model.City;
import com.example.jodel.city.repository.CityRepository;
import com.example.jodel.exception.RecordNotFound;

@Service
public class CityService {

    @Autowired
    CityRepository rep;

    public Optional<City> getCityById(long id) {
        return rep.findById(id);
    }

    public List<City> getAllCity() {
        return rep.findAll();
    }

    public double getDistance(double lat, double lon, City city) {
        return rep.getDistanceToCity(lat, lon, city.getId());
    }

    public City findByLatAndLon(double lat, double lon) throws RecordNotFound {
        return rep.findByLatitudeAndLongitude(lat, lon).orElseThrow(() -> new RecordNotFound("City"));
    }

    public City setCity(double lat, double lon) {

        // HIER NOCH lat, lon nach 2 Nochkommastellen cuten, damit nicht so viele Städte
        // drin sind.
        try {
            return findByLatAndLon(lat, lon);
        } catch (RecordNotFound e) {
            City city = new City();
            Location name = new Location();
            city.setName(name.getLocation(lat, lon));
            city.setLatitude(lat);
            city.setLongitude(lon);
            return rep.save(city);

        }

    }
}
