// @ts-ignore
// const API_BASE_URL = `http://54.165.49.100:3000/${rest_path}`;
const API_BASE_URL = `http://44.201.171.106:8080` || 'http://$(curl http://169.254.169.254/latest/meta-data/public-ipv4):8080' || `${process.env.REACT_APP_API_URL}`;
// const API_BASE_URL = `${process.env.REACT_APP_API_URL}` || "http://localhost:8080";
export default API_BASE_URL;