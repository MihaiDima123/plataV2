import {Flex, FormLabel, Input} from "@chakra-ui/react";
import { useForm, SubmitHandler } from "react-hook-form"
import PlataButton from "lib/button/PlataButton.tsx";
import AuthService from "api/user/AuthService.ts";
import {useNavigate} from "react-router-dom";
import {LANDING_ROUTE} from "routes/guarded-routes.tsx";
import {useContext} from "react";
import {AuthContext} from "providers/AuthContextProvider.tsx";

type LoginFormInputs = {
    username: string
    password: string
}

const LoginForm = () => {
    const navigate = useNavigate()
    const {getUserData} = useContext(AuthContext)

    const {
        handleSubmit,
        register
    } = useForm<LoginFormInputs>();

    const onSubmit: SubmitHandler<LoginFormInputs> = ({ username, password }) => {
        AuthService.login(
            { username, password },
            {
                onSuccess: () => {
                    getUserData(() => navigate(LANDING_ROUTE))
                },
                onError: (error) => console.log("Something went horribly wrong", error)
            }
        )
    }
    
    return (
        <Flex
            alignItems="center"
            justifyContent="center"
        >
            <form onSubmit={handleSubmit(onSubmit)}>
                <FormLabel>Username</FormLabel>
                <Input {...register('username')} />
                <FormLabel>Password</FormLabel>
                <Input {...register('password')} type={'password'} />
                <PlataButton type="submit">
                    Login
                </PlataButton>
            </form>
        </Flex>
    )
}

export default LoginForm