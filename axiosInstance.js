// utils/axiosInstance.js
import axios from "axios";

const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_BASE_URL,
  headers: {
    "ngrok-skip-browser-warning": "skip-browser-warning",
  },
});

export default axiosInstance;
