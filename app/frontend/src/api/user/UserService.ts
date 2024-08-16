import api from "api/index.ts";
import BaseService, {BaseHandlers} from "api/BaseService.ts";

class AuthService {
    private static BASE_PATH: string = 'user'
    private static SELF_USER = `${this.BASE_PATH}/self-user`

    static getSelfUser(handlers: BaseHandlers<any>) {
        BaseService.handleApiResponse(
            api.get(this.SELF_USER),
            handlers
        )
    }
}

export default AuthService