const host = "http://localhost:8082/jodel/api";

// async function apiRequest(endpoint, options = {}) {

//   const { method = 'GET' } = options;
//   const requestOptions = { method };

//   console.log(host + endpoint);
//   const request = await fetch(host + endpoint, requestOptions);
//   const data = await request.json();
//   alert(JSON.stringify(data));
//   return data;
// }

async function apiRequest(endpoint, options = {}) {
  const { method = "GET", body } = options;

  const requestOptions = {
    method,
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  };

  console.log(host + endpoint);
  const request = await fetch(host + endpoint, requestOptions);
  const data = await request.json();
  // alert(JSON.stringify(data));
  return data;
}

async function getAllJodel() {
  return apiRequest("/jodel");
}

// type: jodel oder comment
async function getJodelVote(id, type) {
  return apiRequest(`/vote/getsum/${id}?voteType=${type}`);
}

// "/api/vote/setvote"

async function setJodel(text, latitude, longitude) {
  return apiRequest("/jodel/setjodel", {
    method: "POST",
    body: {
      text: text,
      f_user: "UserId1",
      latitude: latitude,
      longitude: longitude,
    },
  });
}

function getCurrentLocation() {
  return new Promise((resolve, reject) => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const lat = position.coords.latitude;
          const lon = position.coords.longitude;
          resolve({ lat, lon });
        },
        (error) => {
          reject(error);
        }
      );
    } else {
      reject(new Error("Geolocation is not supported by this browser."));
    }
  });
}

const apiService = {
  getAllJodel,
  getJodelVote,
  setJodel,
  getCurrentLocation,
};
export default apiService;
