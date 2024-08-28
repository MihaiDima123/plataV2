import {Flex, Heading, Link, Text} from "@chakra-ui/react";

const NotFound = () => {
    return (
        <Flex
            direction="column"
            alignItems="center"
            justifyContent="center"
            height="100vh"
            textAlign="center"
            p={5}
            >
            <Heading as="h1" size="2xl" mb={4}>
                404
            </Heading>
            <Text>
                Oops! The page you are looking for does not exist.
            </Text>
            <Link href={'/'} colorScheme="blue">
                Go Back Home
            </Link>
        </Flex>
    )
}

export default NotFound