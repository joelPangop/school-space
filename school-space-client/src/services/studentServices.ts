import axios from 'axios';
import {Student} from "../models/Student";
import API_BASE_URL from "../config";
import AuthService from "./AuthService";

const rest_path = 'api/students';
const BASE_URL = API_BASE_URL + `/${rest_path}`;

export const getStudents = async (): Promise<Student[]> => {
    return AuthService.fetchWithAuth(rest_path).then(async (response) => {
        const resp = await axios.get(`${BASE_URL}`, {headers: response});
        return resp.data;
    }).catch(e => {
        console.error("Error fetching students:", e);
        return null
    });
};

export const getStudent = async (id: number) => {
    return AuthService.fetchWithAuth(rest_path).then(async (resp) => {
        const response = await axios.get(`${BASE_URL}/${id}`, {headers: resp});
        return response.data;
    }).catch(e => {
        console.error("Error fetching students:", e);
        return null
    });
};

export const createStudent = async (student: Student): Promise<Student> => {
    return AuthService.fetchWithAuth(rest_path).then(async (resp) => {
        const response = await axios.post(`${BASE_URL}`, student, {
            headers: resp
        });
        return response.data;
    }).catch(e => {
        console.error("Error fetching students:", e);
        return null
    });
};

export const updateStudent = async (id: number, student: Student): Promise<Student> => {
    return AuthService.fetchWithAuth(rest_path).then(async (resp) => {
        const response = await axios.put(`${BASE_URL}/${id}`, student, {
            headers: resp
        })
        return response.data;
    }).catch(e => {
        console.error("Error fetching students:", e);
        return null
    });
}

export const deleteStudent = async (id: number) => {
    return AuthService.fetchWithAuth(rest_path).then(async (resp) => {
        await axios.delete(`${BASE_URL}/${id}`, {headers: resp});
    }).catch(e => {
        console.error("Error fetching students:", e);
        throw e
    });
};