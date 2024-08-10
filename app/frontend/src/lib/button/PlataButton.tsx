import {Button, ButtonProps} from "@chakra-ui/react";


const PlataButton = ({children, ...props}: ButtonProps) => {

    return (
        <Button
            backgroundColor={'primary.500'}
            _hover={'primary.500'}
            {...props}
        >
            {children}
        </Button>
    )

}

export default PlataButton