import React from "react";
import {useStudentFormController} from "../controller/useStudentFormController";
import {Link} from "react-router-dom";

const studentsList = () => {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const {students, deleteItem} = useStudentFormController();
    return (
        <div>
            <h2>Liste des Étudiants</h2>
            <div>
                <Link to="/editStudent">Add</Link>
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
                {students.map((student, index) => (
                    <tr key={index}>
                        <td>{student.name}</td>
                        <td>{student.email}</td>
                        <td>{student.age}</td>
                        <td>
                            <Link to={`/editStudent/${student.id}`}>Edit</Link>&nbsp;
                            <Link to="/editStudent" onClick={(e) => {
                                e.preventDefault(); // pour empêcher le lien de naviguer
                                deleteItem(student.id);
                            }}>Delete</Link>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default studentsList;