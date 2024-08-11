import { createBrowserRouter } from "react-router-dom";
import publicRoutes from "./public-routes.tsx";
import guardedRoutes from "./guarded-routes.tsx";
import {guardRoute} from "./utils";

const router = createBrowserRouter([
    ...publicRoutes,
    ...(guardRoute(guardedRoutes)),
]);


export default router
