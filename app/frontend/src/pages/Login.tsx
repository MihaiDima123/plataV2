import PageLayout from "lib/layout/PageLayout.tsx";
import LoginForm from "components/login-form/LoginForm.tsx";
import PlataBox from "components/box/PlataBox.tsx";

const Login = () => {
    return (
        <PageLayout>
            <PlataBox
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