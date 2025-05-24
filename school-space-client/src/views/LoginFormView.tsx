import React from "react";
import {useLoginController} from "../controller/useLoginController";
import {Link, useNavigate} from "react-router-dom";

const LoginFormView: React.FC = () => {

    const {email, setEmail, password, setPassword, handleLogin} = useLoginController()
    return (
        <div style={{padding: '2rem'}}>
            <h2>LOGIN</h2>
            <form onSubmit={handleLogin}>
                <input type="email" value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" />
                <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Mot de passe" />
                <button type="submit">Se connecter</button>
            </form>
            <br/>
            <p>Not registered yet? Do it here</p>&nbsp;<Link to={`/register`}>Edit</Link>
        </div>
    )
};
export default LoginFormView;