package com.example.jodel.city.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jodel.city.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>

{
}