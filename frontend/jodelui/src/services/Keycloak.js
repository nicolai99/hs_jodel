import Keycloak from "keycloak-js";
const baseUrl = "http://keycloak:8180";

const initKeycloak = async () => {
  const keycloak = new Keycloak({
    url: baseUrl,
    realm: "jodel",
    clientId: "jodel-client",
  });

  const authenticated = await keycloak.init({
    onLoad: "login-required",
    checkLoginIframe: true,
  });

  if (!authenticated) {
    keycloak.login();
  }
};

export default initKeycloak;
