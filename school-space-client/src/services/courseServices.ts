import axios from "axios";
import {Course} from "../models/Course";
import {Student} from "../models/Student";

const rest_path = 'api/course';
const BASE_URL = `${process.env.REACT_APP_API_URL}/${rest_path}` || `http://localhost:8080/${rest_path}` || `http://34.238.242.234:8080/${rest_path}`;

export const getCourses = async (): Promise<Course[]> => {
    const response = await axios.get(`${BASE_URL}`);
    return response.data;
};

export const getCours = async (id: number) => {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
};

export const createCourse = async (course: any): Promise<Student> => {
    const response = await axios.post(`${BASE_URL}`, course ,{
        headers: {
            "Content-Type": "application/json"
        }
    });
    return response.data;
};

export const updateCourse = async (id: number, course: any): Promise<Course> => {
    const response = await axios.put(`${BASE_URL}/${id}`, course ,{
        headers: {
            "Content-Type": "application/json"
        }
    })
    return response.data;
}

export const deleteCours = async (id: number) => {
    await axios.delete(`${BASE_URL}/${id}`);
};