import Keycloak from "keycloak-js";
const baseUrl = process.env.REACT_APP_KEYCLOAK;
console.log(baseUrl);

const keycloak = new Keycloak({
  url: baseUrl,
  realm: "jodel",
  clientId: "jodel-client",
});

export default keycloak;
