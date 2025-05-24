import {useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import AuthService from "../services/AuthService";
import {User} from "../models/User";

export const useLoginController = () => {
    const {id} = useParams();
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    let [user, setUser] = useState<User>(new User('', '', '', '', 'USER'));

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            user.email = email;
            user.password = password;
            await AuthService.login(user);
            alert('Connexion réussie !');
            navigate("/");
        } catch (err) {
            alert('Échec de la connexion');
        }
    };

    const handleChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = event.target;
        const updatedUser = new User(
            user.firstname,
            user.lastname,
            user.email,
            user.password,
            user.role
        );

        switch (name) {
            case 'firstname':
                updatedUser.firstname = value;
                break;
            case 'lastname':
                updatedUser.lastname = value;
                break;
            case 'email':
                updatedUser.email = value;
                break;
            case 'password':
                updatedUser.password = value;
                break;
            case 'role':
                updatedUser.role = value;
                break;
            case 'telephone':
                updatedUser.telephone = value;
                break;
            case 'address':
                updatedUser.address = value;
                break;
        }
        setUser(updatedUser);
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        console.log('Données du User :', user);
        // if (id) {
        //     await updateStudent(parseInt(id), student);
        // } else {
        //     await createStudent(student);
        // }
        await AuthService.register(user);
        user = new User('', '', '', '', 'USER');
        navigate("/");
    };

    return {
        email,
        setEmail,
        password,
        setPassword,
        user,
        setUser,
        handleLogin,
        handleChange,
        handleSubmit
    }
}