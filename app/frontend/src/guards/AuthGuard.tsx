import {useContext, useEffect} from "react";
import {AuthContext} from "providers/AuthContextProvider.tsx";
import {LOGIN_ROUTE} from "routes/public-routes.tsx";
import {useNavigate} from "react-router-dom";

type AuthGuardProps = {
    children: any
}

const AuthGuard = (props: AuthGuardProps) => {
    const navigate = useNavigate();
    const {userLoggedIn} = useContext(AuthContext)

    useEffect(() => {
        if (userLoggedIn === false) {
            navigate(LOGIN_ROUTE)
        }
    }, [userLoggedIn])

    return props.children
}

export default AuthGuard