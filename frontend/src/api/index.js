import axios from "axios";

// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function hotplaceInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_HOTPLACE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function tripwayInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_TRIPWAY_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function tripInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_TRIP_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { apiInstance, hotplaceInstance, tripwayInstance, tripInstance };
