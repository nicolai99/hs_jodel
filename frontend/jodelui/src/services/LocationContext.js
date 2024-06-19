// services/LocationContext.js
import React, { createContext, useState, useEffect, useContext } from "react";
import apiService from "../services/Api";

export const LocationContext = createContext();

export const LocationProvider = ({ children }) => {
  const [location, setLocation] = useState(() => {
    const savedLocation = localStorage.getItem("location");
    return savedLocation
      ? JSON.parse(savedLocation)
      : { name: "Mein Standort", latitude: null, longitude: null };
  });

  const getCurrentLocation = async () => {
    try {
      const { lat, lon } = await apiService.getCurrentLocation();
      const currentLocation = {
        name: "Mein Standort",
        latitude: lat,
        longitude: lon,
      };
      setLocation(currentLocation);
      localStorage.setItem("location", JSON.stringify(currentLocation));
    } catch (error) {
      console.error("Fehler bei getCurrentLocation", error);
    }
  };

  useEffect(() => {
    if (location.latitude === null && location.longitude === null) {
      getCurrentLocation();
    }
  }, [location.latitude, location.longitude]);

  useEffect(() => {
    localStorage.setItem("location", JSON.stringify(location));
  }, [location]);

  return (
    <LocationContext.Provider value={{ location, setLocation }}>
      {children}
    </LocationContext.Provider>
  );
};

export const useLocation = () => useContext(LocationContext);
