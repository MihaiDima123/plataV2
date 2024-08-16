import {createContext, ReactNode, useCallback, useEffect, useState} from "react";
import {User} from "types/entity/user-types.ts";
import UserService from "api/user/UserService.ts";
import {AxiosError} from "axios";

type AuthContextType = {
    user?: User | null,
    userLoggedIn?: boolean | null,
    getUserData: (onSuccess: (() => void) | null) => void,
}

const AuthContextDefaultValue: AuthContextType = {
    user: null,
    userLoggedIn: null,
    getUserData: (_onSuccess) => {},
}

export const AuthContext =
    createContext<AuthContextType>(AuthContextDefaultValue)

type AuthContextProps = {
    children: ReactNode;
}

const AuthContextProvider = (props: AuthContextProps) => {
    const [user, setUser] = useState<User | null>(null);
    const [userLoggedIn, setUserLoggedIn] = useState<boolean | null>(null);

    const getUserData = useCallback((onSuccess: (() => void) | null) => {
        setUserLoggedIn(null)
        UserService.getSelfUser({
            onSuccess: (data) => {
                setUserLoggedIn(true)
                setUser(data)
                onSuccess && onSuccess()
            },
            onError: (_error: AxiosError) => {
                setUserLoggedIn(false)
            }
        })
    }, []);

    useEffect(() => {
        getUserData(null)
    }, [])

    return (
        <AuthContext.Provider
            value={{
                user,
                userLoggedIn,
                getUserData,
            }}
        >
            {props.children}
        </AuthContext.Provider>
    )
}

export default AuthContextProvider