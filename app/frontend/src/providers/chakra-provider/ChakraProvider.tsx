import { ChakraProvider } from '@chakra-ui/react'
import { extendTheme } from "@chakra-ui/react";
import components from "providers/chakra-provider/components";
import baseColors from "providers/chakra-provider/colors/base-colors.ts";
import {baseGlobalStyle} from "providers/chakra-provider/styles/base-styles.ts";
import baseFonts from "providers/chakra-provider/fonts/base-fonts.ts";

const customTheme = extendTheme({
    colors: baseColors,
    fonts: baseFonts,
    styles: { global: baseGlobalStyle.global },
    components
});

type MyChakraProviderProps = {
    children: any
}

const MyChakraProvider = (props: MyChakraProviderProps) => {
    return (
        <ChakraProvider theme={customTheme} >
            {props.children}
        </ChakraProvider>
    )
}

export default MyChakraProvider