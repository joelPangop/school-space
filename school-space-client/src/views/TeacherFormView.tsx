import React from "react";
import {useTeacherFormController} from "../controller/useTeacherFormController";

const teacherFormView: React.FC = () => {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const {teacher, handleChange, handleSubmit} = useTeacherFormController();
    return (
        <div style={{padding: '2rem'}}>
            <h2>Formulaire Enseignant</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nom : </label>
                    <input
                        type="text"
                        name="name"
                        value={teacher && teacher.name !== undefined ? teacher.name : ''}
                        onChange={handleChange}
                    />
                </div>
                <br/>
                <div>
                    <label>Email : </label>
                    <input
                        type="text"
                        name="email"
                        value={teacher && teacher.email !== undefined ? teacher.email : ''}
                        onChange={handleChange}
                    />
                </div>
                <br/>
                <div>
                    <label>Âge : </label>
                    <input
                        type="number"
                        name="age"
                        value={teacher && teacher.age !== undefined ? teacher.age : ''}
                        onChange={handleChange}
                    />
                </div>
                <br/>
                <button type="submit">Soumettre</button>
            </form>
        </div>
    )
};
export default teacherFormView;