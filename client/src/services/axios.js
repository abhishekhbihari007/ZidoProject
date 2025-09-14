// src/api/axios.js
import axios from "axios";

const BASE_URL = process.env.NODE_ENV === 'production' 
  ? 'https://your-backend-url.com/api'  // Update this with your actual backend URL
  : 'http://localhost:8080/api';

const axiosInstance = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// Add JWT token to headers if available
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default axiosInstance;
