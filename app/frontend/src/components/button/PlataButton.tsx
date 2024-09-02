import {Button, ButtonProps} from "@chakra-ui/react";

const PlataButton = (props: ButtonProps) => {
    return (
        <Button
            variant={'plata'}
            {...props}
        >
            {props.children}
        </Button>
    )
}

export default PlataButton