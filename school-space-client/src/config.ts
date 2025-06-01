console.log("👉 ENV REACT_APP_API_URL:", process.env.REACT_APP_API_URL);

// @ts-ignore
const API_BASE_URL = process.env.REACT_APP_API_URL || "http://localhost:8080";
export default API_BASE_URL;
