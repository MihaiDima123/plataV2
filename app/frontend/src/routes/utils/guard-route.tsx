import {RouteObject} from "react-router-dom";
import AuthGuard from "guards/AuthGuard.tsx";

const guardRoutes = (routes: RouteObject[]) => (
    routes.map(route => {
        return ({
            ...route,
            element: (
                <AuthGuard key={route.path}>
                    {route.element}
                </AuthGuard>
            )
        })
    })
)

export default guardRoutes