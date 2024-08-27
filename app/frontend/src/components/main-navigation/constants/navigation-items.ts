import {FiHome} from "react-icons/fi";
import {JSXElementConstructor} from "react";

type NavItemType = {
    key: string,
    link: string,
    icon?: JSXElementConstructor<any>,
    label: string
}

const navigationItems: NavItemType[] = [
    {
        key: 'home',
        link: '/',
        icon: FiHome,
        label: 'labels.navigation.navigation-list.home'
    },
]

export default navigationItems