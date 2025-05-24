import {useEffect, useState} from "react";
import {Student} from "../models/Student";
import {createStudent, deleteStudent, getStudent, getStudents, updateStudent} from "../services/studentServices";
import {useNavigate, useParams} from "react-router-dom";
import AuthService from "../services/AuthService";

export const useStudentFormController = () => {
    const {id} = useParams();
    const navigate = useNavigate();

    const [student, setStudent] = useState<Student>(
        new Student('', '', 0)
    );
    useEffect(() => {
        const fetchStudent = async () => {
            if (id) {
                const existingStudent = await getStudent(parseInt(id));
                const originalStudent = new Student(existingStudent._name, existingStudent._email, existingStudent._age);
                originalStudent.id = existingStudent._id;
                setStudent(originalStudent);
            }
        };
        fetchStudent();
    }, [id]);

    const [students, setStudents] = useState<Student[]>([]);

    useEffect(() => {
        fetchStudents();
    }, []);

    const handleChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        const updatedStudent = new Student(
            student.name,
            student.email,
            student.age
        );

        switch (name) {
            case 'name':
                updatedStudent.name = value;
                break;
            case 'email':
                updatedStudent.email = value;
                break;
            case 'age':
                updatedStudent.age = Number(value);
                break;
        }
        setStudent(updatedStudent);
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        console.log('Données du Student :', student);
        if (id) {
            await updateStudent(parseInt(id), student);
        } else {
            await createStudent(student);
        }
        navigate("/");
    };

    const fetchStudents = async () => {
        getStudents().then((response) => {
            console.log("typeof response.data:", typeof response);
            console.log("isArray:", Array.isArray(response));
            console.log("data =", response);
            const mapped = response.map((raw: any) => {
                const student = new Student(raw._name, raw._email, raw._age);
                student.id = raw._id;
                return student;
            });
            setStudents(Object.values(mapped));
        });

    };

    async function deleteItem(id: number) {
        await deleteStudent(id);
        await fetchStudents();
    }

    return {
        student,
        students,
        handleChange,
        handleSubmit,
        deleteItem
    };

}