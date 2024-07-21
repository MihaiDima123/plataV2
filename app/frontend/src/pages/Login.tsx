import PageLayout from "../lib/layout/PageLayout.tsx";
import SocialLoginForm from "../components/social-login-form/SocialLoginForm.tsx";
import PlataBox from "../lib/box/PlataBox.tsx";

const Login = () => {
    return (
        <PageLayout>
            <PlataBox
                display="flex"
                alignItems={'flex-start'}
                justifyContent={'center'}
                height={400}
                width={500}
                maxHeight={'100vh'}
            >
                <SocialLoginForm />
            </PlataBox>
        </PageLayout>
    )
}

export default Login