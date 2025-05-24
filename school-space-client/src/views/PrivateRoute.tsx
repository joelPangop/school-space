// components/PrivateRoute.tsx
import { Navigate } from "react-router-dom";
import AuthService from "../services/AuthService";
import {JSX} from "react";

const PrivateRoute = ({ children }: { children: JSX.Element }) => {
    return AuthService.isAuthenticated() ? children : <Navigate to="/login" replace />;
};

export default PrivateRoute;
