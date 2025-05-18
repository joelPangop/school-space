import axios from 'axios';
import {Student} from "../models/Student";

const rest_path = 'api/students';
const BASE_URL = `${process.env.REACT_APP_API_URL}/${rest_path}` || `http://localhost:8080/${rest_path}` || `http://44.201.170.102/:8080/${rest_path}`;

export const getStudents = async (): Promise<Student[]> => {
    const response = await axios.get(`${BASE_URL}`);
    return response.data;
};

export const getStudent = async (id: number) => {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
};

export const createStudent = async (student: Student): Promise<Student> => {
    const response = await axios.post(`${BASE_URL}`, student ,{
        headers: {
            "Content-Type": "application/json"
        }
    });
    return response.data;
};

export const updateStudent = async (id: number, student: Student): Promise<Student> => {
    const response = await axios.put(`${BASE_URL}/${id}`, student ,{
        headers: {
            "Content-Type": "application/json"
        }
    })
    return response.data;
}

export const deleteStudent = async (id: number) => {
    await axios.delete(`${BASE_URL}/${id}`);
};