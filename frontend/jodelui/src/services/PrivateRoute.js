import { useKeycloak } from "@react-keycloak/web";
import { Navigate } from "react-router-dom";

const PrivateRoute = ({ children }) => {
  const { keycloak, initialized } = useKeycloak();

  if (!initialized) {
    return <div>Loading...</div>;
  }

  if (!keycloak.authenticated) {
    window.location.href = keycloak.createLoginUrl();
    return null;
  }

  return keycloak.authenticated ? children : null;
};

export default PrivateRoute;
