import React, { useState, useEffect, useContext } from "react";
import { Dropdown } from "primereact/dropdown";
import { Button } from "primereact/button";
import "./AccountPage.css";
import keycloak from "../services/Keycloak";
import apiService from "../services/Api";
import { LocationContext } from "../services/LocationContext";

const AccountPage = () => {
  const [cities, setCities] = useState([]);
  const { location, setLocation } = useContext(LocationContext);

  const getCities = async () => {
    try {
      const response = await apiService.getCities();
      const defaultCity = {
        name: "Mein Standort",
        latitude: location.latitude,
        longitude: location.longitude,
      };
      const citiesWithDefault = [defaultCity, ...response];

      // Doppelte Namen filtern
      const uniqueCities = citiesWithDefault.filter(
        (city, index, self) =>
          index === self.findIndex((t) => t.name === city.name)
      );

      setCities(uniqueCities);
    } catch (error) {
      alert("Fehler bei getCities");
      console.error("Get-cities-error", error);
    }
  };

  useEffect(() => {
    if (location.latitude !== null && location.longitude !== null) {
      getCities();
    }
  }, [location]);

  const handleCityChange = (e) => {
    setLocation(e.value); // Den location Zustand im Kontext aktualisieren
  };

  const keyLogout = () => {
    keycloak.logout({
      redirectUri: `${process.env.REACT_APP_BASE_URL}/account`,
    });
  };

  return (
    <div className="container">
      <h2>Account</h2>
      <div className="setting-column top-column">
        <label>Standort</label>
        <div className="card flex justify-content-center">
          <Dropdown
            value={location}
            onChange={handleCityChange}
            options={cities}
            optionLabel="name"
            placeholder="Standort wählen"
            className="w-full md:w-14rem"
          />
        </div>
      </div>
      <div className="setting-column">
        <div className="card flex justify-content-center">
          <Button label="Logout" onClick={keyLogout} />
        </div>
      </div>
    </div>
  );
};

export default AccountPage;
