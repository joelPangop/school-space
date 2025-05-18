import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {Teacher} from "../models/Teacher";
import {createTeacher, deleteTeacher, getTeacher, getTeachers, updateTeacher} from "../services/teacherServices";
import {getStudents} from "../services/studentServices";
import {Student} from "../models/Student";

export const useTeacherFormController = () => {
    const {id} = useParams();
    const navigate = useNavigate();

    const [teacher, setTeacher] = useState<Teacher>(
        new Teacher('', '', 0)
    );
    useEffect(() => {
        const fetchTeacher = async () => {
            if (id) {
                const existingTeacher = await getTeacher(parseInt(id));
                const originalTeacher = new Teacher(existingTeacher._name, existingTeacher._email, existingTeacher._age);
                originalTeacher.id = existingTeacher._id;
                setTeacher(originalTeacher);
            }
        };
        fetchTeacher();
    }, [id]);

    const [teachers, setTeachers] = useState<Teacher[]>([]);

    useEffect(() => {
        fetchTeachers();
    }, []);

    const [students, setStudents] = useState<Student[]>([]);

    useEffect(() => {
        fetchStudents();
    }, []);

    const handleChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        const updatedTeacher = new Teacher(
            teacher.name,
            teacher.email,
            teacher.age
        );

        switch (name) {
            case 'name':
                updatedTeacher.name = value;
                break;
            case 'email':
                updatedTeacher.email = value;
                break;
            case 'age':
                updatedTeacher.age = Number(value);
                break;
        }
        setTeacher(updatedTeacher);
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        console.log('Données du Teacher :', teacher);
        if (id) {
            await updateTeacher(parseInt(id), teacher);
        } else {
            await createTeacher(teacher);
        }
        navigate("/teachers");

    };

    const fetchTeachers = async () => {
        getTeachers().then((response) => {
            console.log("typeof response.data:", typeof response);
            console.log("isArray:", Array.isArray(response));
            console.log("Teachers data =", response);
            const mapped = response.map((raw: any) => {
                const student = new Teacher(raw._name, raw._email, raw._age);
                student.id = raw._id;
                return student;
            });
            setTeachers(Object.values(mapped));
        });
    };

    async function deleteItem(id: number) {
        await deleteTeacher(id);
        await fetchTeachers();
    }

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

    return {
        teacher,
        teachers,
        students,
        handleChange,
        handleSubmit,
        deleteItem
    };

}