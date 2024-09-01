import {createContext, ReactNode, useEffect} from "react";
import {User} from "types/entity/user-types.ts";
import UserService from "api/user/UserService.ts";
import {useQuery} from "@tanstack/react-query";
import {useTranslation} from "react-i18next";

type AuthContextType = {
    user?: User | null,
    userLoggedIn?: boolean,
    userLoginLoading?: boolean,
    getUserData: (onSuccess: (() => void) | null) => void,
}

const AuthContextDefaultValue: AuthContextType = {
    user: null,
    userLoggedIn: false,
    userLoginLoading: false,
    getUserData: (_onSuccess) => {},
}

export const AuthContext =
    createContext<AuthContextType>(AuthContextDefaultValue)

type AuthContextProps = {
    children: ReactNode;
}

const AuthContextProvider = (props: AuthContextProps) => {
    const {i18n} = useTranslation();

    const { data, refetch, isSuccess, isLoading } = useQuery({
        queryKey: ['selfUser'],
        queryFn: () => UserService.getSelfUser(),
        retry: false
    })

    useEffect(() => {
        if (data?.locale) {
            i18n.changeLanguage(data?.locale)
                .then(_r => undefined)
        }
    }, [data])

    return (
        <AuthContext.Provider
            value={{
                user: data as User,
                userLoggedIn: isSuccess,
                userLoginLoading: isLoading,
                getUserData: (onSuccess) => (
                    refetch().then(() => onSuccess && onSuccess())
                ),
            }}
        >
            {props.children}
        </AuthContext.Provider>
    )
}

export default AuthContextProvider