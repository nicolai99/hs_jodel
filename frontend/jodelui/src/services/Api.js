import keycloak from "./Keycloak";
const host = "http://localhost:8082/jodel/api";

async function apiRequest(endpoint, options = {}) {
  const { method = "GET", body } = options;
  const requestOptions = {
    method,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${keycloak.token}`,
    },
    body: JSON.stringify(body),
  };

  console.log(host + endpoint);
  const request = await fetch(host + endpoint, requestOptions);
  if (!request.ok) {
    throw new Error(request.status);
  }
  if (request.status == 401) {
  }

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
// http://localhost:8082/jodel/api/vote/setvote
function setVote(postID, direction, userID, type) {
  return apiRequest("/vote/setvote", {
    method: "POST",
    body: {
      f_entity: postID,
      direction: direction,
      f_user: userID,
      voteType: type,
    },
  });
}

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


async function getJodelById(id) {
  return apiRequest(`/jodel/${id}`);
}

async function setComment(jodelId, text) {
  return apiRequest("/comment", {
    method: "POST",
    body: {
      text: text,
      f_jodel: jodelId
    },
  });
}

async function getCities() {
  return apiRequest('/city');
}

const apiService = {
  getAllJodel,
  getJodelVote,
  setJodel,
  getCurrentLocation,
  setVote,
  getJodelById,
  setComment,
  getCities,
};
export default apiService;
