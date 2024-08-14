import {AuthRequest} from "./types/auth-types.ts";
import api from "../api.ts";
import BaseService, {BaseHandlers} from "../BaseService.ts";

class AuthService {
    private static BASE_PATH: string = 'auth'
    private static LOGIN_PATH = `${this.BASE_PATH}/login`

    static login(loginRequest: AuthRequest, handlers: BaseHandlers<any>) {
        BaseService.handleApiResponse(
            api({
                method: 'post',
                url: this.LOGIN_PATH,
                data: loginRequest,
                withCredentials: true
            }),
            handlers
        )
    }
}

export default AuthService