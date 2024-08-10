import PlataButton from "../../lib/button/PlataButton.tsx";
import googleIcon from '../../assets/icons/google-icon.svg'
import {Flex, Image, Text} from "@chakra-ui/react";

const SocialLoginForm = () => {
    const loginHandler = () => {
        console.log("Here")
    }

    return (
        <Flex
            alignItems={'center'}
            justifyContent={'flex-start'}
            gap={4}
            flexDirection={'column'}
            width={'100%'}
            height={'100%'}
        >
            <Text>
                Login with your social media
            </Text>
            <Flex
                justifyContent={'center'}
                alignItems={'center'}
                height={'inherit'}
            >
                <PlataButton
                    onClick={loginHandler}
                    backgroundColor={'transparent'}
                >
                    <a href={'http://localhost:8080/oauth2/authorization/google'}>
                        <Image src={googleIcon} />
                    </a>
                </PlataButton>
            </Flex>
        </Flex>
    )
}

export default SocialLoginForm