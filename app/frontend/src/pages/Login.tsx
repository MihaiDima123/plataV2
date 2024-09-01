import PageLayout from "lib/layout/PageLayout.tsx";
import LoginForm from "components/login-form/LoginForm.tsx";
import {Box} from "@chakra-ui/react";

const Login = () => {
    return (
        <PageLayout>
            <Box
                display="flex"
                alignItems={'flex-start'}
                justifyContent={'center'}
                width={500}
                maxHeight={'100vh'}
            >
                <LoginForm />
            </Box>
        </PageLayout>
    )
}

export default Login