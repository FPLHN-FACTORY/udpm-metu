import {
    useQuery,
    UseQueryReturnType,
} from "@tanstack/vue-query";
import {getListWidgetGroupByCategory} from "@/services/api/widget/widgetforms/widget-form.api.ts";
import {queryKey} from "@/constants/queryKey.ts";

export const useWidgetGroupByCategory = (options?: any): UseQueryReturnType<Awaited<ReturnType<typeof getListWidgetGroupByCategory>>, Error> => {
    return useQuery({
        queryKey: [queryKey.manager.widget.WidgetGroupByCategory],
        queryFn: () => getListWidgetGroupByCategory(),
        ...options,
    });
};