import API_BASE_URL from "../config";
import axios from 'axios';
import {Student} from "../models/Student";

const rest_path = 'api/auth';
const BASE_URL = API_BASE_URL + `/${rest_path}`;

class AuthService {

    login = async (user: any): Promise<Student> => {
        const response = await axios.post(`${BASE_URL}/login`, user, {
            headers: {
                "Content-Type": "application/json"
            }
        });
        localStorage.setItem('token', response.data.token);
        return response.data;
    }

    register = async (user: any): Promise<Student> => {
        const response = await axios.post(`${BASE_URL}/register`, user, {
            headers: {
                "Content-Type": "application/json"
            }
        });
        // localStorage.setItem('token', response.data.token);
        return response.data;
    }

    logout() {
        localStorage.removeItem('token');
    }

    getToken() {
        return localStorage.getItem('token');
    }

    isAuthenticated() {
        return !!this.getToken();
    }

    async fetchWithAuth(url: string, options: RequestInit = {}): Promise<any> {
        const token = this.getToken();
        if (!token) {
            throw new Error("No token found");
        }
        const headers = {
            ...options.headers,
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
        };

        const response = await fetch(`${url}`, {
            ...options,
            headers,
        });

        if (!response.ok) {
            throw new Error('Unauthorized or failed request');
        }
        if (response.ok) {
            return headers;
        } else {
            throw new Error('Unauthorized or failed request');
        }
    }
}

export default new AuthService();