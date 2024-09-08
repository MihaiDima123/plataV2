import BaseService from "api/BaseService.ts";
import api from "api/index.ts";
import {CreateGuildDTO} from "api/guild/types/guild-types.ts";

class GuildService {
    private static BASE_PATH: string = 'guild'
    private static CREATE_GUILD = this.BASE_PATH

    static async createGuild(createGuildRequest: CreateGuildDTO): Promise<string | number> {
        return BaseService.handleApiResponse<number>(
            api.post(this.CREATE_GUILD, createGuildRequest)
        )
    }
}

export default GuildService
