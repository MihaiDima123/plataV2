import {AuthRequest} from "./types/auth-types.ts";
import api from "../api.ts";
import BaseService, {BaseHandlers} from "../BaseService.ts";

class AuthService {
    private static BASE_PATH: string = 'auth'
    private static LOGIN_PATH = `${this.BASE_PATH}/login`

    static login(loginRequest: AuthRequest, handlers: BaseHandlers<any>) {
        BaseService.handleApiResponse(
            api.post(this.LOGIN_PATH, loginRequest),
            handlers
        )
    }
}

export default AuthService