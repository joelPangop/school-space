import React from "react";
import {Link} from "react-router-dom";
import {useTeacherFormController} from "../controller/useTeacherFormController";

const teachersList = () => {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const {teachers, deleteItem} = useTeacherFormController();
    return (
        <div>
            <h2>Liste des Enseignants</h2>
            <div>
                <Link to="/editTeacher">Add</Link>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Email</th>
                    <th>Age</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {teachers.map((teacher, index) => (
                    <tr key={index}>
                        <td>{teacher.name}</td>
                        <td>{teacher.email}</td>
                        <td>{teacher.age}</td>
                        <td>
                            <Link to={`/editTeacher/${teacher.id}`}>Edit</Link>&nbsp;
                            <Link to="/editTeacher"   onClick={(e) => {
                                e.preventDefault(); // pour empêcher le lien de naviguer
                                deleteItem(teacher.id);
                            }}>Delete</Link>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default teachersList;