package com.example.jodel.city.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jodel.city.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>

{// Haversine https://en.wikipedia.org/wiki/Haversine_formula
        @Query(value = "SELECT distance FROM (" +
                        "SELECT id, name, latitude, longitude, " +
                        "(6371 * acos(cos(radians(:lat)) * cos(radians(latitude)) * cos(radians(longitude) - radians(:lon)) + "
                        +
                        "sin(radians(:lat)) * sin(radians(latitude)))) AS distance " +
                        "FROM city) AS subquery " +
                        "WHERE id = :id " +
                        "ORDER BY distance", nativeQuery = true)
        double getDistanceToCity(
                        @Param("lat") double lat,
                        @Param("lon") double lon,
                        @Param("id") long id);

        Optional<City> findByLatitudeAndLongitude(double lat, double lon);
}