import React, { useState, useEffect, useContext } from "react";
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import './AccountPage.css'
import keycloak from "../services/Keycloak";
import apiService from "../services/Api";
// import { LocationContext } from "../services/LocationContext";


const AccountPage = () => {
  const defaultCity = { id: 0, name: 'Mein Standort' };
  const [cities, setCities] = useState([]);
  // const { location, setLocation } = useContext(LocationContext);

  const getCities = async () =>  {
    try {
      const response = await apiService.getCities();
      const citieWithMyLocation = [defaultCity, ...response];

      // Doppelte Namen filtern
      const uniqueCities = citieWithMyLocation.filter((city, index, self) => 
        index === self.findIndex((t) => t.name === city.name)
      );

      setCities(uniqueCities);
    } catch (error) {
      alert("Fehler bei getCities");
      console.error('Get-cities-error', error);
    }
  }

  useEffect(() => {
    getCities();
  }, []);

  const [selectedCity, setSelectedCity] = useState(defaultCity);

  // const handleCityChange = (e) => {
  //   setSelectedCity(e.value);
  //   setLocation(e.value.name);  // Den location Zustand im Kontext aktualisieren
  // };

  const keyLogout = () => {
    keycloak.logout({
      redirectUri: "http://localhost:3000/account",
    });
  };

  return (

    <div className="container">

      <h2>Account</h2>

      <div className="setting-column top-column">
        <label>Standort</label>
        <div className="card flex justify-content-center">
        {/* <Dropdown value={selectedCity} onChange={handleCityChange} options={cities} optionLabel="name" 
            placeholder="Standort wählen" className="w-full md:w-14rem" /> */}
            <Dropdown value={selectedCity} onChange={(e) => setSelectedCity(e.value)} options={cities} optionLabel="name" 
            placeholder="Standort wählen" className="w-full md:w-14rem" />
        </div>
      </div>

      <div className="setting-column">
        <div className="card flex justify-content-center">
            <Button label="Logout" onClick={keyLogout}/>
        </div>
      </div>

      

    </div>
    
  );
};

export default AccountPage;
