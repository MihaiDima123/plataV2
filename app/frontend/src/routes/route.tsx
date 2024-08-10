import { createBrowserRouter } from "react-router-dom";
import Login from "../pages/Login.tsx";
import Landing from "../pages/Landing.tsx";

const router = createBrowserRouter([
    {
        path: "/login",
        element: <Login />,
    },
    {
        path: '/',
        element: <Landing />
    }
]);


export default router
