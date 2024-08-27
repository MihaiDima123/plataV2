import {
    Box,
    IconButton,
    useDisclosure,
    Drawer,
    DrawerContent,
    DrawerOverlay,
} from '@chakra-ui/react';
import { FiMenu } from 'react-icons/fi';
import SidebarContent from "components/main-navigation/SidebarComponent.tsx";
import React from "react";

function SideNav({ children }: { children: React.ReactNode }) {
    const { isOpen, onOpen, onClose } = useDisclosure();

    return (
        <Box minH="100vh" bg="gray.100" display={'flex'} alignSelf={'flex-start'}>
            <IconButton
                display={{ base: 'flex', md: 'none' }}
                onClick={onOpen}
                icon={<FiMenu />}
                variant="outline"
                aria-label="open menu"
                position="fixed"
                top={4}
                left={4}
                zIndex={20}
            />

            <Drawer
                isOpen={isOpen}
                placement="left"
                onClose={onClose}
                returnFocusOnClose={false}
                onOverlayClick={onClose}
                size="full"
            >
                <DrawerOverlay />
                <DrawerContent>
                    <SidebarContent />
                </DrawerContent>
            </Drawer>

            <Box display={{ base: 'none', md: 'block' }}>
                <SidebarContent />
            </Box>

            <Box ml={{ base: 0, md: 60 }} p="4">
                {children}
            </Box>
        </Box>
    );
}

export default SideNav;