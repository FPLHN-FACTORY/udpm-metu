import { DefaultResponse, PaginationParams, PaginationResponse, ResponseList } from "@/utils/types/api.common";
import { Ref } from "vue";
import request from "@/services/request";
import { API_ADMIN_PLAN_ORDER } from "@/constants/url";
import { AxiosResponse } from "axios";

// Các kiểu dữ liệu cho PlanOrder
export interface ParamsGetPlanOrder extends PaginationParams {
    name?: string,
    status?: string
    // Bạn có thể thêm các tham số khác nếu cần
}

export type PlanOrderResponse = ResponseList & {
    id: string; // hoặc kiểu dữ liệu tương ứng
    createAt: number;
    expirationDate: number;
    price: number;
    quantity: number;
    // Thêm các thuộc tính khác tương ứng với dữ liệu API của bạn
};

// Hàm gọi API để lấy danh sách PlanOrder
export const getListPlanOrders = async (params: Ref<ParamsGetPlanOrder>): Promise<DefaultResponse<PaginationResponse<PlanOrderResponse[]>>> => {
    const res = (await request({
        url: `${API_ADMIN_PLAN_ORDER}`,
        method: 'GET',
        params: params.value,
    })) as AxiosResponse<DefaultResponse<PaginationResponse<PlanOrderResponse[]>>>;

    return res.data;
};