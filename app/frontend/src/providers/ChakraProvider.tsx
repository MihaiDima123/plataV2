import { ChakraProvider } from '@chakra-ui/react'
import { extendTheme } from "@chakra-ui/react";

const colors = {
    primary: {
        50: "#e5fcf1",
        100: "#27e29e",
        200: "#26d993",
        300: "#24d188",
        400: "#23c97d",
        500: "#21c173",
        600: "#1fb969",
        700: "#1eb15f",
        800: "#1ca955",
        900: "#1aa14b",
    },
    secondary: {
        50: "#ffe3ec",
        100: "#ffb3c1",
        200: "#ff8397",
        300: "#ff536d",
        400: "#ff2243",
        500: "#e6002a",
        600: "#b40020",
        700: "#820016",
        800: "#50000c",
        900: "#200002",
    },
};

const customTheme = extendTheme({
    colors,
    fonts: {
        heading: "Georgia, serif",
        body: "Arial, sans-serif",
    },
    styles: {
        global: {
            "html, body": {
                backgroundColor: "gray.100",
                color: "gray.800",
            },
            a: {
                color: "primary.500",
                _hover: {
                    textDecoration: "underline",
                },
            },
        },
    },
});

type MyChakraProviderProps = {
    children: any
}

const MyChakraProvider = (props: MyChakraProviderProps) => {
    return (
        <ChakraProvider
            theme={customTheme}
        >
            {props.children}
        </ChakraProvider>
    )
}

export default MyChakraProvider