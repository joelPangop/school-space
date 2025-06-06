import React from 'react';
import './App.css';
import StudentFromView from "./views/StudentFormView";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import StudentsList from "./views/StudentsList";
import CourseFormView from "./views/CourseFormView";
import CoursesList from "./views/CoursesList";
import TeachersList from "./views/TeachersList";
import TeacherFormView from "./views/TeacherFormView";
import LoginFormView from "./views/LoginFormView";
import RegisterFormView from "./views/RegisterFormView";
import PrivateRoute from "./views/PrivateRoute";

function App() {
    return (
        <BrowserRouter>
            <nav>
                <Link to="/">Accueil</Link> | <Link to="/courses">Cours</Link> | <Link to="/teachers">Enseignants</Link>
            </nav>
            <Routes>
                {/*<Route path="/" element={<StudentsList/>}/>*/}
                <Route path="/login" element={<LoginFormView />} />
                <Route
                    path="/"
                    element={
                        <PrivateRoute>
                            <StudentsList />
                        </PrivateRoute>
                    }
                />
                <Route path="/editStudent/:id" element={<StudentFromView/>}/>
                <Route path="/editStudent" element={<StudentFromView/>}/>
                <Route path="/courses" element={<CoursesList/>}/>
                <Route path="/editCours/:id" element={<CourseFormView/>}/>
                <Route path="/editCours" element={<CourseFormView/>}/>
                <Route path="/teachers" element={<TeachersList/>}/>
                <Route path="/editTeacher/:id" element={<TeacherFormView/>}/>
                <Route path="/editTeacher" element={<TeacherFormView/>}/>
                <Route path="/register" element={<RegisterFormView/>}/>
                {/*<Route path="/login" element={<LoginFormView/>}/>*/}
            </Routes>
        </BrowserRouter>
    )
}

export default App;
