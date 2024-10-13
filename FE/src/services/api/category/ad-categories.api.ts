import {DefaultResponse, ResponseList} from "@/utils/types/api.common.ts";
import request from "@/services/request.ts";
import {PREFIX_API_ADMIN} from "@/constants/url.ts";
import {AxiosResponse} from "axios";

export type CategoryResponse ={
    id:string
    name:string
}

export const getAllCategory = async () => {
    const res = (await request({
        url: `${PREFIX_API_ADMIN}/categories`,
        method: "GET",
    })) as AxiosResponse<DefaultResponse<Array<CategoryResponse>>>;

    return res.data;
}