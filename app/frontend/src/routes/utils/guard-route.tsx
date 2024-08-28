import AuthGuard from "guards/AuthGuard.tsx";
import {ProtectedRouteType} from "routes/guarded-routes.tsx";

const guardRoutes = (routes: ProtectedRouteType[]) => (
    routes.map(route => {
        return ({
            ...route.routeObject,
            element: (
                <AuthGuard
                    key={route.routeObject.path}
                    permission={route.permission}
                >
                    {route.routeObject.element}
                </AuthGuard>
            )
        })
    })
)

export default guardRoutes