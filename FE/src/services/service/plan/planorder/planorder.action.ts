import { useQuery, UseQueryReturnType } from "@tanstack/vue-query";
import { getListPlanOrders } from "@/services/api/plan/planorder/planorder.api";
import { queryKey } from "@/constants/queryKey";

// Sử dụng useQuery thay vì useMutation
export const useGetListPlanOrder = (options?: any): UseQueryReturnType<Awaited<ReturnType<typeof getListPlanOrders>>, Error> => {
    return useQuery({
        queryKey: [queryKey.admin.planorder.PlanOrder], // Query key, có thể dùng như "getListPlanOrder"
        queryFn: () => getListPlanOrders(), // Hàm để lấy danh sách từ API
        ...options, // Tùy chọn thêm từ options
    });
};
