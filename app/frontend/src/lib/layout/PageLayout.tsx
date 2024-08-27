import {Flex, FlexProps} from "@chakra-ui/react";

const PageLayout = (props: FlexProps) => {
    return (
        <Flex
            height={'100vh'}
            width={'100vw'}
            alignItems={'center'}
            justifyContent={'center'}
        >
            {props.children}
        </Flex>
    )
}

export default PageLayout