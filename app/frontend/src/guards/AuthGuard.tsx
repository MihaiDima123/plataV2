import {useEffect} from "react";
import api from "api/index.ts";

type AuthGuardProps = {
    children: any
}

const AuthGuard = (props: AuthGuardProps) => {
    // TODO: Get self user data

    useEffect(() => {
        api.get('/test-123')
            .then(res => console.log(res))
    }, [])

    return props.children
}

export default AuthGuard