import {useContext} from "react";
import {FlexProps} from "@chakra-ui/react";
import {useForm, SubmitHandler, UseFormReturn} from "react-hook-form"
import AuthService from "api/user/AuthService.ts";
import {useNavigate} from "react-router-dom";
import {LANDING_ROUTE} from "routes/guarded-routes.tsx";
import {AuthContext} from "providers/AuthContextProvider.tsx";
import { useToast } from '@chakra-ui/react'
import {isEmpty} from 'lodash'
import {useMutation} from "@tanstack/react-query";
import {AuthRequest} from "api/user/types/auth-types.ts";
import {AxiosError} from "axios";
import {useTranslation} from "react-i18next";
import FormInput from "lib/form-component/FormInput.tsx";
import FormComponent from "lib/form-component/FormComponent.tsx";
import PlataButton from "components/button/PlataButton.tsx";

type LoginFormInputs = {
    username: string
    password: string
}

const resetFields = (formContext: UseFormReturn<LoginFormInputs, null, any>) => {
    formContext.setValue('username', '')
    formContext.setValue('password', '')
}

const wrapperProps: FlexProps = {
    alignItems: 'center',
    justifyContent: 'center',
    direction: 'column',
    width: '100%'
};

const LoginForm = () => {
    const {t} = useTranslation()

    const navigate = useNavigate()
    const {getUserData} = useContext(AuthContext)

    const toast = useToast()

    const formContext = useForm<LoginFormInputs>()

    const loginHandle = useMutation({
        mutationFn: (request: AuthRequest) => AuthService.login(request),
        onSuccess: () => getUserData(() => navigate(LANDING_ROUTE)),
        onError: (error: AxiosError<any>) => {
            resetFields(formContext)

            if (error.response?.status === 403) {
                return toast({
                    title: t('errors.login-form.unauthenticated'),
                    status: 'error'
                })
            }

            toast({
                title: error.response?.data.message,
                status: 'error'
            })
        }
    })

    const onSubmit: SubmitHandler<LoginFormInputs> = ({ username, password }) => {
        if (isEmpty(username) || isEmpty(password)) {
            return
        }

        loginHandle.mutate({ username, password })
    }

    return (
        <FormComponent formContext={formContext} wrapperProps={wrapperProps} >
            <FormInput
                name={'username'}
                label={t('form.labels.username')}
            />
            <FormInput
                label={t('form.labels.password')}
                name={'password'}
                type={'password'}
            />
            <PlataButton
                onClick={formContext.handleSubmit(onSubmit)}
                isDisabled={loginHandle.isPending}
            >
                {t('form.actions.login')}
            </PlataButton>
        </FormComponent>
    )
}

export default LoginForm