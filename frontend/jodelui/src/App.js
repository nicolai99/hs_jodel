import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  NavLink,
} from "react-router-dom";
import { ReactKeycloakProvider, useKeycloak } from "@react-keycloak/web";
import "./App.css";
import apiService from "./services/Api";
import JodelPage from "./Pages/JodelPage";
import AccountPage from "./Pages/AccountPage";
import CommentPage from "./Pages/CommentPage";
import keycloak from "./services/Keycloak"; // Stelle sicher, dass keycloak richtig importiert wird

function App() {
  useEffect(() => {
    keycloak.init({ onLoad: "login-required" }).then((authenticated) => {
      if (authenticated) {
        console.log("User is authenticated");
      } else {
        console.error("Authentication failed");
      }
    });
  }, []);

  const [displayName, setDisplayName] = useState("");

  useEffect(() => {
    setDisplayNameFromToken();
  }, []);

  const setDisplayNameFromToken = () => {
    setTimeout(() => {
      if (keycloak.authenticated) {
        const decodedToken = keycloak.tokenParsed;
        const name = `${decodedToken.preferred_username}`.toUpperCase();
        setDisplayName(name);
      } else {
        console.error("Benutzer ist nicht authentifiziert.");
      }
    }, 500);
  };

  const keyLogout = () => {
    keycloak.logout({
      redirectUri: `/`,
    });
  };

  return (
    <ReactKeycloakProvider authClient={keycloak}>
      <Router>
        <div className="container">
          <header className="header">
            <h1>Jodel</h1>
            <div className="header-right">
              <p>{displayName}</p>
              <button onClick={keyLogout}>logout</button>
            </div>
          </header>

          <nav className="tab-bar">
            <NavLink to="/" end>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                height="24px"
                viewBox="0 -960 960 960"
                width="24px"
                fill="#FF8D00"
              >
                <path d="M280-240q-17 0-28.5-11.5T240-280v-80h520v-360h80q17 0 28.5 11.5T880-680v600L720-240H280ZM80-280v-560q0-17 11.5-28.5T120-880h520q17 0 28.5 11.5T680-840v360q0 17-11.5 28.5T640-440H240L80-280Zm520-240v-280H160v280h440Zm-440 0v-280 280Z" />
              </svg>
              <span>Jodel</span>
            </NavLink>
            <NavLink to="/account">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                height="24px"
                viewBox="0 -960 960 960"
                width="24px"
                fill="#FF8D00"
              >
                <path d="M234-276q51-39 114-61.5T480-360q69 0 132 22.5T726-276q35-41 54.5-93T800-480q0-133-93.5-226.5T480-800q-133 0-226.5 93.5T160-480q0 59 19.5 111t54.5 93Zm246-164q-59 0-99.5-40.5T340-580q0-59 40.5-99.5T480-720q59 0 99.5 40.5T620-580q0 59-40.5 99.5T480-440Zm0 360q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q53 0 100-15.5t86-44.5q-39-29-86-44.5T480-280q-53 0-100 15.5T294-220q39 29 86 44.5T480-160Zm0-360q26 0 43-17t17-43q0-26-17-43t-43-17q-26 0-43 17t-17 43q0 26 17 43t43 17Zm0-60Zm0 360Z" />
              </svg>
              <span>Account</span>
            </NavLink>
          </nav>

          <Routes>
            <Route path="/" element={<JodelPage />} />
            <Route path="/account" element={<AccountPage />} />
            <Route path="/jodel/:id" element={<CommentPage />} />
          </Routes>
        </div>
      </Router>
    </ReactKeycloakProvider>
  );
}

export default App;
