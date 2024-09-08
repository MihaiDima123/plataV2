import api from "api/index.ts";
import BaseService from "api/BaseService.ts";
import {User} from "types/entity/user-types.ts";

class AuthService {
    private static BASE_PATH: string = 'user'
    private static SELF_USER = `${this.BASE_PATH}/self-user`

    static async getSelfUser(): Promise<(User | string)> {
        return BaseService.handleApiResponse(api.get(this.SELF_USER))
    }
}

export default AuthService