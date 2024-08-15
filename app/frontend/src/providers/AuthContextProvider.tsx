import {createContext, ReactNode, useEffect} from "react";
import {User} from "types/entity/user-types.ts";

type AuthContextType = {
    user?: User | null
}

export const AuthContext = createContext<AuthContextType>({})

type AuthContextProps = {
    children: ReactNode;
}

const AuthContextDefaultValue: AuthContextType = {
    user: null
}

const AuthContextProvider = (props: AuthContextProps) => {

    useEffect(() => {

    }, [])

    return (
        <AuthContext.Provider value={AuthContextDefaultValue}>
            {props.children}
        </AuthContext.Provider>
    )
}

export default AuthContextProvider