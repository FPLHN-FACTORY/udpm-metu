import { business } from "./business.api";
import { PaginationResponse } from "./../../../utils/types/api.common";
import { PREFIX_API_ADMIN_BUSINESS } from "@/constants/url";
import request from "@/services/request";
import { DefaultResponse } from "@/utils/types/api.common";
import { PaginationConfig } from "ant-design-vue/es/pagination";
import { AxiosResponse } from "axios";
import { Ref } from "vue";

export type businessRespone = {
  id: string;
  code: String;
  name: String;
};

export interface paramBusiness {
  code: any;
  name: any;
}

export interface paramBusinessGetAll {
  code?: string;
  name?: string;
  page: number;
  size: number;
  orderBy?: string;
  sortBy?: string;
  q?: string;
}

export type business = {
  id: string;
  stt: number;
  businessType: string;
  code: string;
  name: string;
  description: string;
  status: string;
  createAt: any;
  createBy: any;
  updateAt: any;
  updateBy: any;
  deleted: string | number;
};

export interface createUpdateBusinessParams {
  name: string;
  code: string;
}

export const getAllBuisiness = async (params: Ref<paramBusinessGetAll>) => {
  const res = (await request({
    url: "http://localhost:1919/business",
    method: "GET",
    params: params.value,
  })) as AxiosResponse<DefaultResponse<PaginationResponse<Array<business>>>>;
  return res.data;
};

export const updateBusiness = async (
  params: createUpdateBusinessParams,
  businessId: string
) => {
  const res = (await request({
    url: `http://localhost:1919/business/${businessId}`,
    method: "PUT",
    data: params,
  })) as AxiosResponse<DefaultResponse<null>>;
  return res.data;
};

export const deleteBusiness = async (_businessId: string) => {
  const res = (await request({
    url: `http://localhost:1919/business/${_businessId}`,
    method: "DELETE",
  })) as AxiosResponse<DefaultResponse<null>>;
  return res.data;
};
