const host = "http://localhost:8080/jodel/api";

async function apiRequest(endpoint) {
  console.log(host + endpoint);
  const request = await fetch(host + endpoint);
  const data = await request.json();
  alert(JSON.stringify(data));
  return data;
}

async function getAllJodel() {
  return apiRequest("/jodel");
}

const apiService = {
  getAllJodel,
};
export default apiService;
