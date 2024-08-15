import {Flex} from "@chakra-ui/react";

type PageLayoutProps = {
    children?: any
}

const PageLayout = (props: PageLayoutProps) => {
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