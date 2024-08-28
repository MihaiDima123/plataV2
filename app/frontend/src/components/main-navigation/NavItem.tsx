import {Box, Flex, Link} from "@chakra-ui/react";
import React, {JSXElementConstructor} from "react";

type NavItemProps = {
    icon?:  JSXElementConstructor<any>
    children?: React.ReactNode
    link?: string
}

 const NavItem = (props: NavItemProps) => {
    return (
        <Link
            href={props.link}
            style={{ textDecoration: 'none' }}
            _focus={{ boxShadow: 'none' }}
        >
            <Flex
                align="center"
                p="4"
                mx="4"
                borderRadius="lg"
                role="group"
                cursor="pointer"
                fontWeight={700}
            >
                {props.icon && (
                    <Box
                        color={'gray.900'}
                        mr="4"
                        fontWeight={700}
                        fontSize="16"
                        as={props.icon}
                    />
                )}
                {props.children}
            </Flex>
        </Link>
    );
}

export default NavItem