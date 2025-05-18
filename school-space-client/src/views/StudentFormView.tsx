import React from "react";
import {useStudentFormController} from "../controller/useStudentFormController";

const StudentFormView: React.FC = () => {
    const {student, handleChange, handleSubmit} = useStudentFormController();
    return (
        <div style={{padding: '2rem'}}>
            <h2>Formulaire Étudiant</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nom : </label>
                    <input
                        type="text"
                        name="name"
                        value={student && student.name !== undefined ? student.name : ''}
                        onChange={handleChange}
                    />
                </div>
                <br/>
                <div>
                    <label>Email : </label>
                    <input
                        type="text"
                        name="email"
                        value={student && student.email !== undefined ? student.email : ''}
                        onChange={handleChange}
                    />
                </div>
                <br/>
                <div>
                    <label>Âge : </label>
                    <input
                        type="number"
                        name="age"
                        value={student && student.age !== undefined ? student.age : ''}
                        onChange={handleChange}
                    />
                </div>
                <br/>
                <button type="submit">Soumettre</button>
            </form>
        </div>
    )
};
export default StudentFormView;