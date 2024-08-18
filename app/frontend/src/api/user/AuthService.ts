import api from "api/index.ts";
import {AuthRequest} from "api/user/types/auth-types.ts";
import BaseService from "api/BaseService.ts";

class AuthService {
    private static BASE_PATH: string = 'auth'
    private static LOGIN_PATH = `${this.BASE_PATH}/login`

    static async login(loginRequest: AuthRequest): Promise<any> {
        return BaseService.handleApiResponse(
            api({
                method: 'post',
                url: this.LOGIN_PATH,
                data: loginRequest,
                withCredentials: true
            }),
        )
    }
}

export default AuthService