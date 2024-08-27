import {Box, VStack} from "@chakra-ui/react";
import NavItem from "components/main-navigation/NavItem.tsx";
import navigationItems from "components/main-navigation/constants/navigation-items.ts";
import {useTranslation} from "react-i18next";

const SidebarContent = () => {
    const {t} = useTranslation()

    return (
        <Box
            bg="gray.900"
            color="white"
            borderRight="1px"
            borderRightColor="gray.700"
            w={{ base: "full", md: 60 }}
            pos="fixed"
            h="full"
        >
            <VStack align="start" mt={5} spacing={5} ml={5}>
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