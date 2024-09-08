import Landing from "pages/Landing.tsx";
import FullLayout from "lib/layout/FullLayout.tsx";
import {RouteObject} from "react-router-dom";
import AppPermissions from "api/permissions/app-permissions.ts";
import CreateGuild from "pages/guild/CreateGuild.tsx";
import Guild from "pages/guild/Guild.tsx";

export const LANDING_ROUTE = '/';
export const GUILD_CREATE_GUILD = 'guild/create';
export const GUILD_GUILD = `/guild/:id`;

export type ProtectedRouteType = {
    permission?: string,
    routeObject: RouteObject
}

const guardedRoutes: ProtectedRouteType[] =  [
    {
        routeObject: {
            path: '/',
            element: <FullLayout />,
            children: [
                {
                    path: LANDING_ROUTE,
                    element: <Landing />,
                },
            ],
        }
    },
    {
        permission: AppPermissions.guild.createGuild,
        routeObject: {
            element: <FullLayout />,
            children: [
                {
                    path: GUILD_CREATE_GUILD,
                    element: <CreateGuild />,
                }
            ],
        }
    },
    {
        permission: AppPermissions.guild.readGuild,
        routeObject: {
            element: <FullLayout />,
            children: [
                {
                    path: GUILD_GUILD,
                    element: <Guild />
                }
            ]
        }
    }
]

export default guardedRoutes