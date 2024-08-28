import {FiHome} from "react-icons/fi";
import { FaUsers } from 'react-icons/fa';
import {JSXElementConstructor} from "react";
import AppPermissions from "api/permissions/app-permissions.ts";

type NavItemType = {
    key: string,
    link: string,
    icon?: JSXElementConstructor<any>,
    label: string,
    position?: 'top' | 'bottom',
    permission?: string
}

const navigationItems: NavItemType[] = [
    {
        key: 'home',
        link: '/',
        icon: FiHome,
        label: 'labels.navigation.navigation-list.home',
        position: 'top',
        permission: AppPermissions.guild.createGuild, // hmm - that should be available for all users anyway
    },
    {
        key: 'create-guild',
        link: '/guild/create',
        icon: FaUsers,
        label: 'labels.navigation.navigation-list.create-guild',
        position: 'bottom',
        permission: AppPermissions.guild.createGuild,
    },
]

export default navigationItems