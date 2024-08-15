import Landing from "pages/Landing.tsx";

export const LANDING_ROUTE = '/';
const guardedRoutes =  [
    {
        path: LANDING_ROUTE,
        element: <Landing />
    }
]

export default guardedRoutes