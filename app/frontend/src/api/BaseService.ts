import {AxiosPromise} from "axios";

export interface BaseHandlers<T> {
    onSuccess?: (data: T) => any,
    onError?: (error: string) => void
    onFinally?: () => void
}

class BaseService {
    static handleApiResponse<T>(apiPromise: AxiosPromise, baseHandlers: BaseHandlers<T>) {
        apiPromise
            .then(({data}) => baseHandlers.onSuccess && baseHandlers.onSuccess(data.data || data.message))
            .catch(e => baseHandlers.onError && baseHandlers.onError(e.message))
            .finally(() => baseHandlers.onFinally && baseHandlers.onFinally())
    }
}

export default BaseService