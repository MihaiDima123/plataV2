import PageLayout from "lib/layout/PageLayout.tsx";
import PlataBox from "lib/box/PlataBox.tsx";
import LoginForm from "components/login-form/LoginForm.tsx";

const Login = () => {
    return (
        <PageLayout>
            <PlataBox
                display="flex"
                alignItems={'flex-start'}
                justifyContent={'center'}
                width={500}
                maxHeight={'100vh'}
            >
                <LoginForm />
            </PlataBox>
        </PageLayout>
    )
}

export default Login