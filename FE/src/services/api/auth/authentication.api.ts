import { PREFIX_API_LOGIN, PREFIX_API_REGISTER, PREFIX_API_LOGOUT, PREFIX_API_FORGOT_PASSWORD } from "@/constants/url";
import request from "@/services/request";
import {
    DefaultResponse,
} from "@/utils/types/api.common";
import {AxiosResponse} from "axios";
import {UserInformation} from "@/utils/types/auth.type.ts";


export const getUserInformation = async (data?: string) => {
    const res = (await request({
        url: PREFIX_API_LOGIN,
        data: {
            data,
        },
        method: "POST",
    })) as AxiosResponse<DefaultResponse<UserInformation>>;

    return res.data;
};
