import React from "react";
import { useTeacherFormController } from "../controller/useTeacherFormController";

const TeacherFormView: React.FC = () => {
    const { teacher, handleChange, handleSubmit } = useTeacherFormController();

    return (
        <div style={{ padding: '2rem' }}>
            <h2>Formulaire Enseignant</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="name">Nom : </label>
                    <input
                        id="name"
                        type="text"
                        name="name"
                        value={teacher?.name ?? ''}
                        onChange={handleChange}
                    />
                </div>
                <br />
                <div>
                    <label htmlFor="email">Email : </label>
                    <input
                        id="email"
                        type="text"
                        name="email"
                        value={teacher?.email ?? ''}
                        onChange={handleChange}
                    />
                </div>
                <br />
                <div>
                    <label htmlFor="age">Âge : </label>
                    <input
                        id="age"
                        type="number"
                        name="age"
                        value={teacher?.age ?? ''}
                        onChange={handleChange}
                    />
                </div>
                <br />
                <button type="submit">Soumettre</button>
            </form>
        </div>
    );
};

export default TeacherFormView;
