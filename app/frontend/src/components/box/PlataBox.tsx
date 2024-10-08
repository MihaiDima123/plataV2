import {Box, BoxProps} from "@chakra-ui/react";

const PlataBox = (props: BoxProps) => {

    return (
        <Box
            display={'flex'}
            padding={4}
            backgroundColor={'white'}
            borderRadius={8}
            boxShadow={'xs'}
            {...props}
        >
            {props.children}
        </Box>
    )
}

export default PlataBox