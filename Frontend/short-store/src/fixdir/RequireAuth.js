import { useLocation, Navigate, Outlet } from "react-router-dom";
import useAuth from "../hooks/useAuth";

const requireAuth = () => {
    const { auth } = useAuth();
    const location = useLocation();

    return auth?.userEmail ? (
        <Outlet />
    ) : (
        <Navigate to="/login" state={{ from: location }} replace />
    );
};

export default requireAuth;
