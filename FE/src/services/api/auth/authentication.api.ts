import {PREFIX_API_LOGIN} from "@/constants/url";
import request from "@/services/request";
import {DefaultResponse,} from "@/utils/types/api.common";
import {AxiosResponse} from "axios";

export interface Login {
    email: string;
    password: string;
}

export const login = async (
    params: Login
) => {
    const res = (await request({
        url: `${PREFIX_API_LOGIN}`,
        method: "POST",
        data: params,
    })) as AxiosResponse<DefaultResponse<Login>>;

    return res.data;
};
