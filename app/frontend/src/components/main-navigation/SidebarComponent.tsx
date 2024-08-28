import {Box, VStack} from "@chakra-ui/react";
import NavItem from "components/main-navigation/NavItem.tsx";
import navigationItems from "components/main-navigation/constants/navigation-items.ts";
import {useTranslation} from "react-i18next";

const SidebarContent = () => {
    const {t} = useTranslation()

    return (
        <Box color="gray.900" h="full" width={300}>
            <VStack align="start" spacing={5} ml={5}>
                {navigationItems.map((navItem) => (
                    <NavItem
                        key={navItem.key}
                        icon={navItem.icon}
                        link={navItem.link}
                    >
                        {t(navItem.label)}
                    </NavItem>
                ))}
            </VStack>
        </Box>
    );
}

export default SidebarContent