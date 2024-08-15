import Login from "pages/Login.tsx";

export const LOGIN_ROUTE = "/login";

const publicRoutes = [
    {
        path: LOGIN_ROUTE,
        element: <Login />,
    }
]

export default publicRoutes