import {useContext, useEffect} from "react";
import {AuthContext} from "providers/AuthContextProvider.tsx";
import {LOGIN_ROUTE} from "routes/public-routes.tsx";
import {useNavigate} from "react-router-dom";
import LoadingOverlay from "pages/custom/LoadingOverlay.tsx";

type AuthGuardProps = {
    children: any
}

const AuthGuard = (props: AuthGuardProps) => {
    const navigate = useNavigate();
    const {
        userLoggedIn,
        userLoginLoading
    } = useContext(AuthContext)

    useEffect(() => {
        if (!userLoggedIn && !userLoginLoading) {
            navigate(LOGIN_ROUTE)
        }
    }, [userLoggedIn, userLoginLoading])

    if (userLoginLoading) {
        return <LoadingOverlay />
    }

    return props.children
}

export default AuthGuard