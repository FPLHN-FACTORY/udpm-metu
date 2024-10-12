import { useQuery, UseQueryReturnType } from "@tanstack/vue-query";
import { getListPlanOrders, ParamsGetPlanOrder } from "@/services/api/plan/planorder/planorder.api";
import { queryKey } from "@/constants/queryKey";
import { Ref } from "vue";

// Sử dụng useQuery để quản lý dữ liệu và trạng thái
export const useGetListPlanOrder = (
  params: Ref<ParamsGetPlanOrder>,
  options?: any
): UseQueryReturnType<Awaited<ReturnType<typeof getListPlanOrders>>, Error> => {
  return useQuery({
    queryKey: [
      queryKey.admin.planorder.PlanOrder,
      params
    ],
    queryFn: () => getListPlanOrders(params),
    ...options,
  });
};
