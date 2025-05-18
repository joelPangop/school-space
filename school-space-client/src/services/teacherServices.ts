import axios from "axios";
import {Teacher} from "../models/Teacher";

const rest_path = 'api/teacher';
const BASE_URL = `${process.env.REACT_APP_API_URL}/${rest_path}` || `http://localhost:8080/${rest_path}` || `http://http://34.238.242.234/:8080/${rest_path}`;

export const getTeachers = async (): Promise<Teacher[]> => {
    const response = await axios.get(`${BASE_URL}`);
    return response.data;
};

export const getTeacher = async (id: number) => {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
};

export const createTeacher = async (teacher: Teacher): Promise<Teacher> => {
    const response = await axios.post(`${BASE_URL}`, teacher ,{
        headers: {
            "Content-Type": "application/json"
        }
    });
    return response.data;
};

export const updateTeacher = async (id: number, teacher: Teacher): Promise<Teacher> => {
    const response = await axios.put(`${BASE_URL}/${id}`, teacher ,{
        headers: {
            "Content-Type": "application/json"
        }
    })
    return response.data;
}

export const deleteTeacher = async (id: number) => {
    await axios.delete(`${BASE_URL}/${id}`);
};