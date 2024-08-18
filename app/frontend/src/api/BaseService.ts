import {AxiosPromise} from "axios";

class BaseService {
    static async handleApiResponse<T>(apiPromise: AxiosPromise): Promise<T | string> {
        const {data: { data, message }} = await apiPromise;

        return data || message;
    }
}

export default BaseService