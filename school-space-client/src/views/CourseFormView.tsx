import React from "react";
import {useCourseFormController} from "../controller/useCourseFormController";

const courseFormView: React.FC = () => {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const {course, students,teachers, handleChange, handleSubmit, handleStudentCheckboxChange, handleTeachersCheckboxChange} = useCourseFormController()
    return (
        <div style={{padding: '2rem'}}>
            <h2>Formulaire Étudiant</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nom : </label>
                    <input
                        type="text"
                        name="name"
                        value={course.name}
                        onChange={handleChange}
                    />
                </div>
                <br/>
                <div>
                    <label>Sigle : </label>
                    <input
                        type="text"
                        name="sigle"
                        value={course.sigle}
                        onChange={handleChange}
                    />
                </div>
                <br/>
                <div>
                    <label>Etudiants: </label>
                    {students.map(student => (
                        <div key={student.id}>
                            <input
                                type="checkbox"
                                checked={course.students.includes(student.id)}
                                value={student.id}
                                onChange={(e) => handleStudentCheckboxChange(student, e.target.checked)}
                            />
                            {student.name}
                        </div>
                    ))}
                </div>
                <br/>
                <div>
                    <label>Enseignants: </label>
                    {teachers.map(teacher => (
                        <div key={teacher.id}>
                            <input
                                type="checkbox"
                                checked={course.teachers.includes(teacher.id)}
                                value={teacher.id}
                                onChange={(e) => handleTeachersCheckboxChange(teacher, e.target.checked)}
                            />
                            {teacher.name}
                        </div>
                    ))}
                </div>
                <br/>
                <button type="submit">Soumettre</button>
            </form>
        </div>
    );
}

export default courseFormView;