import React from "react";
import {Link} from "react-router-dom";
import {useCourseFormController} from "../controller/useCourseFormController";

const coursesList = () => {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const {courses, deleteItem} = useCourseFormController();
    return (
        <div>
            <h2>Liste des Cours</h2>
            <div>
                <Link to="/editCours">Add</Link>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Sigle</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {courses.map((cours, index) => (
                    <tr key={index}>
                        <td>{cours.name}</td>
                        <td>{cours.sigle}</td>
                        <td>
                            <Link to={`/editCours/${cours.id}`}>Edit</Link>&nbsp;
                            <Link to="/editCours" onClick={(e) => {
                                e.preventDefault(); // pour empêcher le lien de naviguer
                                deleteItem(cours.id);
                            }}>Delete</Link>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default coursesList;