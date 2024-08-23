import {useContext} from "react";
import {Button, Flex, FormLabel, Input} from "@chakra-ui/react";
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

type LoginFormInputs = {
    username: string
    password: string
}

const resetFields = (formContext: UseFormReturn<LoginFormInputs, null, any>) => {
    formContext.setValue('username', '')
    formContext.setValue('password', '')
}

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
        <Flex alignItems="center" justifyContent="center" direction={'column'} width={'100%'}>
            <Flex direction={'column'} width={'100%'}>
                <FormLabel>{t('form.login.username')}</FormLabel>
                <Input {...formContext.register('username')} />
            </Flex>
            <Flex direction={'column'} width={'100%'}>
                <FormLabel>{t('form.login.password')}</FormLabel>
                <Input {...formContext.register('password')} type={'password'} />
            </Flex>
            <Button
                variant={'plata'}
                width={'100%'}
                mt={10}
                onClick={formContext.handleSubmit(onSubmit)}
                isDisabled={loginHandle.isPending}
            >
                {t('form.actions.login')}
            </Button>
        </Flex>
    )
}

export default LoginForm