import request from "@/services/request.ts";
import {PREFIX_API_ADMIN} from "@/constants/url.ts";
import {AxiosResponse} from "axios";
import {DefaultResponse, PaginationParams, PaginationResponse, ResponseList} from "@/utils/types/api.common.ts";
import {Ref} from "vue";

export interface ParamsGetWidget extends PaginationParams {
    search: string
}

export type WidgetResponse = ResponseList & {
    categoryName: string;
    code: string;
    name: string;
    iconUrl: string;
    type: string;
    description: string;
    stt: string;
};

export interface CreateUpdateWidgetParams {
    name: string;
    description: string;
}

export type DetailWidgetResponse = {
    id: string;
    categoryId: string;
    name: string;
    code: string;
    iconUrl: string;
    description: string;
};


export const getAllWidget = async (params: Ref<ParamsGetWidget>) => {
    const res = (await request({
        url: `${PREFIX_API_ADMIN}/widget`,
        method: "GET",
        params: params.value
    })) as AxiosResponse<DefaultResponse<PaginationResponse<Array<WidgetResponse>>>>;

    return res.data;
}

export const addWidget = async (params: CreateUpdateWidgetParams) => {
    const res = (await request({
        url: `${PREFIX_API_ADMIN}/widget`,
        method: "POST",
        data: params,
    })) as AxiosResponse<DefaultResponse<null>>;

    return res.data;
};

export const getDetailWidget = async (id: string | null) => {
    const res = (await request({
        url: `${PREFIX_API_ADMIN}/widget/${id}`,
        method: "GET",
    })) as AxiosResponse<DefaultResponse<DetailWidgetResponse>>;

    return res.data;
};

export const updateWidget= async (params: CreateUpdateWidgetParams, id: string) => {
    const res = (await request({
        url: `${PREFIX_API_ADMIN}/widget/${id}`,
        method: "PUT",
        data: params,
    })) as AxiosResponse<DefaultResponse<null>>;

    return res.data;
};

export const deleteWidget = async (id: string) => {
    const res = (await request({
        url: `${PREFIX_API_ADMIN}/widget/${id}`,
        method: "DELETE",
    })) as AxiosResponse<DefaultResponse<any>>;

    return res.data;
};