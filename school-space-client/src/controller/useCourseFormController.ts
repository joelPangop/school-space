import {useEffect, useState} from "react";
import {Course} from "../models/Course";
import {useNavigate, useParams} from "react-router-dom";
import {createCourse, deleteCours, getCours, getCourses, updateCourse} from "../services/courseServices";
import {Student} from "../models/Student";
import {getStudents} from "../services/studentServices";
import {getTeachers} from "../services/teacherServices";
import {Teacher} from "../models/Teacher";

export const useCourseFormController = () =>{
    const {id} = useParams();
    const navigate = useNavigate();
    const [course, setCourse] = useState<Course>(
        new Course('', '')
    )

    useEffect(() => {
        const fetchCours = async () => {
            if (id) {
                const existingCours = await getCours(parseInt(id));
                const originalCours = new Course(existingCours._name, existingCours._sigle);
                originalCours.id = existingCours._id;
                originalCours.students = existingCours._students;
                originalCours.teachers = existingCours._teachers;
                setCourse(originalCours);
            }
        };
        fetchCours();
        fetchStudents();
        fetchTeachers();
    }, [id]);

    const [courses, setCourses] = useState<Course[]>([]);
    // useEffect(() => {
    // }, []);

    const [students, setStudents] = useState<Student[]>([]);
    const [teachers, setTeachers] = useState<Teacher[]>([]);

    useEffect(() => {
        fetchCourses();

    }, []);

    const handleChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        const updatedCourse = new Course(
            course.name,
            course.sigle
        )
        switch (name) {
            case 'name':
                updatedCourse.name = value;
                break;
            case 'sigle':
                updatedCourse.sigle = value;
                break;
        }
        setCourse(updatedCourse);
    }

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();

        // const jsonPayload = course.toJson();
        // console.log('Données du Course :', jsonPayload);
        if (id) {
            await updateCourse(parseInt(id), course);
        } else {
            await createCourse(course);
        }
        navigate("/courses");
    }

    const handleStudentCheckboxChange = (student: Student, checked: boolean) => {
        if (checked) {
            course.students.push(student.id)
        } else {
            // setSelectedStudentIds((prev) => prev.filter((sid) => sid !== id));
            course.students.splice(student.id)
        }
    };

    const handleTeachersCheckboxChange = (teacher: Teacher, checked: boolean) => {
        if (checked) {
            course.teachers.push(teacher.id)
        } else {
            course.teachers.splice(teacher.id)
        }
    };

    const fetchCourses = async () => {
        getCourses().then((response) => {
            console.log("typeof response.data:", typeof response);
            console.log("isArray:", Array.isArray(response));
            console.log("data =", response);
            const mapped = response.map((raw: any) => {
                const course = new Course(raw._name, raw._sigle);
                course.id = raw._id;
                return course;
            });
            setCourses(Object.values(mapped));
        });
    };

    async function deleteItem(id: number) {
        await deleteCours(id);
        await fetchCourses();
    }

    const fetchStudents = async () => {
        getStudents().then((response) => {
            console.log("typeof response.data:", typeof response);
            // console.log("isArray:", Array.isArray(response));
            console.log("data =", response);
            const mapped = response.map((raw: any) => {
                const student = new Student(raw._name, raw._email, raw._age);
                student.id = raw._id;
                return student;
            });
            setStudents(Object.values(mapped));
        });
    };

    const fetchTeachers = async () => {
        getTeachers().then((response) => {
            console.log("typeof response.data:", typeof response);
            console.log("isArray:", Array.isArray(response));
            console.log("data =", response);
            const mapped = response.map((raw: any) => {
                const teacher = new Teacher(raw._name, raw._email, raw._age);
                teacher.id = raw._id;
                return teacher;
            });
            setTeachers(Object.values(mapped));
        });
    };

    return {
        course,
        courses,
        students,
        teachers,
        handleChange,
        handleSubmit,
        deleteItem,
        handleStudentCheckboxChange,
        handleTeachersCheckboxChange
    };
}