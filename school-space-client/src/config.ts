// @ts-ignore
const API_BASE_URL = import.meta.env.VITE_API_URL || "http://localhost:8080" || `${process.env.REACT_APP_API_URL}/${rest_path}`;
export default API_BASE_URL;