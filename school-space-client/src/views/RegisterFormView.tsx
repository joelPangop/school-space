import React from "react";
import {useLoginController} from "../controller/useLoginController";

const RegisterFormView: React.FC = () => {
    const {user, handleChange, handleSubmit} = useLoginController();
    return (
        <div style={{padding: '2rem'}}>
            <h2>Formulaire Étudiant</h2>
            <div style={{padding: '2rem'}}>
                <h2>Register</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <label>First Name : </label>
                        <input
                            type="text"
                            name="firstname"
                            value={user && user.firstname !== undefined ? user.firstname : ''}
                            onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <div>
                        <label>Last Name : </label>
                        <input
                            type="text"
                            name="lastname"
                            value={user && user.lastname !== undefined ? user.lastname : ''}
                            onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <div>
                        <label>Email : </label>
                        <input
                            type="email"
                            name="email"
                            value={user && user.email !== undefined ? user.email : ''}
                            onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <div>
                        <label>Password : </label>
                        <input
                            type="password"
                            name="password"
                            value={user && user.password !== undefined ? user.password : ''}
                            onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <div>
                        <label>Telephone : </label>
                        <input
                            type="text"
                            name="telephone"
                            value={user && user.telephone !== undefined ? user.telephone : ''}
                            onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <div>
                        <label>Address : </label>
                        <input
                            type="text"
                            name="address"
                            value={user && user.address !== undefined ? user.address : ''}
                            onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <button type="submit">Soumettre</button>
                </form>
            </div>
        </div>
    )
};

export default RegisterFormView;