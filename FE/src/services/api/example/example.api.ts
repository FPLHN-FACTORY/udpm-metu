import {PREFIX_API_ADMIN, PREFIX_API_MANAGER} from "@/constants/url";
import request from "@/services/request";
import {DefaultResponse,} from "@/utils/types/api.common";
import {AxiosResponse} from "axios";

export const getUsers = async () => {
    const res = (await request({
        url: `${PREFIX_API_ADMIN}/feature`,
        method: "GET",
    })) as AxiosResponse<DefaultResponse<any>>;

    return res.data;
}