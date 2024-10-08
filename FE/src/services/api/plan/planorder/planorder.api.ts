import request from "@/services/request";
import { AxiosResponse } from "axios";
import { DefaultResponse } from "@/utils/types/api.common";
import { API_ADMIN_PLAN_ORDER } from "@/constants/url";

// Định nghĩa hàm format Long thành Date
const formatTimestampToDate = (timestamp: number): string => {
    return new Date(timestamp * 1000).toLocaleDateString("vi-VN"); // Chuyển đổi timestamp từ giây sang Date và định dạng chỉ lấy ngày tháng năm
};

// Hàm gọi API để lấy dữ liệu PlanOrder
export const getListPlanOrders = async (): Promise<DefaultResponse<any[]>> => {
    const res = await request({
        url: `${API_ADMIN_PLAN_ORDER}`,
        method: "GET",
    }) as AxiosResponse<DefaultResponse<any[]>>;

    // Format lại createAt, expDate và thêm STT, tổng tiền cho từng PlanOrder trước khi trả về
    const formattedData = res.data.data.map((planOrder, index) => ({
        ...planOrder,
        createAt: formatTimestampToDate(planOrder.createAt), // Format createAt thành Date string
        expDate: formatTimestampToDate(planOrder.expDate), // Format expDate thành Date string
        stt: index + 1, // Thêm số thứ tự (STT), bắt đầu từ 1
        totalPrice: planOrder.price * planOrder.quantity, // Tính tổng tiền = đơn giá * số lượng
    }));

    return {
        ...res.data,
        data: formattedData,
    };
};
