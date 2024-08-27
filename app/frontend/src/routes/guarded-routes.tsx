import {RouteObject} from "react-router-dom";
import Landing from "pages/Landing.tsx";
import FullLayout from "lib/layout/FullLayout.tsx";

export const LANDING_ROUTE = '/';
const guardedRoutes: RouteObject[] =  [
    {
        path: '/',
        element: <FullLayout />,
        children: [
            {
                path: LANDING_ROUTE,
                element: <Landing />
            }
        ]
    }
]

export default guardedRoutes