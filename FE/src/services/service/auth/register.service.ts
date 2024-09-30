import { UserCreateApi } from "@/services/api/auth/register.api";

export const UserService = {
    async postNewUser(data: any) {
        try {
            const response = UserCreateApi.postNewUser(data);
            return response;
        } catch (error) {
            throw error;
        }
    },
    async getAllBusinessType() {
        try {
            const response = UserCreateApi.getAllBusinessType();
            return response;
        } catch (error) {
            throw error;
        }
    }
}