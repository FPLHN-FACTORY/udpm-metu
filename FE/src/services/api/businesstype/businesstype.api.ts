import { PREFIX_API_ADMIN } from "@/constants/url";
import request from "@/services/request";
import { DefaultResponse, PaginationParams, PaginationResponse, ResponseList } from "@/utils/types/api.common";
import { AxiosResponse } from "axios";
import { Ref } from "vue";

export interface ParamsGetFacility extends PaginationParams {
  search: string
}

export type BusinessTypeResponse = ResponseList & {
  code: string;
  name: string;
  description: string;
  stt: string;
};

export interface CreateUpdateBusinessTypeParams {
  name: string;
  description: string;
}

export type DetailBusinessTypeResponse = {
  id: string;
  name: string;
  code: string;
  description: string;
};

export const getAllBusinessType = async (params: Ref<ParamsGetFacility>) => {
  const res = (await request({
    url: `${PREFIX_API_ADMIN}/business-type`,
    method: "GET",
    params: params.value
  })) as AxiosResponse<DefaultResponse<PaginationResponse<Array<BusinessTypeResponse>>>>;

  return res.data;
}

export const addBusinessType = async (params: CreateUpdateBusinessTypeParams) => {
  const res = (await request({
    url: `${PREFIX_API_ADMIN}/business-type`,
    method: "POST",
    data: params,
  })) as AxiosResponse<DefaultResponse<null>>;

  return res.data;
};

export const deleteBusinessType = async (id: string) => {
  const res = (await request({
    url: `${PREFIX_API_ADMIN}/business-type/${id}`,
    method: "DELETE",
  })) as AxiosResponse<DefaultResponse<any>>;

  return res.data;
};

export const updateBusinessType = async (params: CreateUpdateBusinessTypeParams, id: string) => {
  const res = (await request({
    url: `${PREFIX_API_ADMIN}/business-type/${id}`,
    method: "PUT",
    data: params,
  })) as AxiosResponse<DefaultResponse<null>>;

  return res.data;
};

export const getDetailBusinessType = async (id: string | null) => {
  const res = (await request({
    url: `${PREFIX_API_ADMIN}/business-type/${id}`,
    method: "GET",
  })) as AxiosResponse<DefaultResponse<DetailBusinessTypeResponse>>;

  return res.data;
};