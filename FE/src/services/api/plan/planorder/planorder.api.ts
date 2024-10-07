import request from "@/services/request";
import { AxiosResponse } from "axios";
import { DefaultResponse } from "@/utils/types/api.common";
import { API_ADMIN_PLAN_ORDER } from "@/constants/url";

// Hàm gọi API để lấy dữ liệu PlanOrder
export const getListPlanOrders = async (): Promise<DefaultResponse<PlanOrderResponse[]>> => {
    const res = await request({
        url: `${API_ADMIN_PLAN_ORDER}`,
        method: "GET",
    }) as AxiosResponse<DefaultResponse<PlanOrderResponse[]>>;

    return res.data;
};
