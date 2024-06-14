import Keycloak from "keycloak-js";
const baseUrl = "http://keycloak:8180";

const keycloak = new Keycloak({
  url: baseUrl,
  realm: "jodel",
  clientId: "jodel-client",
});

export default keycloak;
