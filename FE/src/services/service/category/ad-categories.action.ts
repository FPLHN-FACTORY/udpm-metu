import {useQuery, UseQueryReturnType} from "@tanstack/vue-query";
import {queryKey} from "@/constants/queryKey.ts";
import {getAllCategory} from "@/services/api/category/ad-categories.api.ts";

export const useGetCategories = (
    options?: any)
    : UseQueryReturnType<Awaited<ReturnType<typeof getAllCategory>>, Error> => {
    return useQuery({
        queryKey: [queryKey.admin.widget.widgetAd],
        queryFn: () => getAllCategory(),
        ...options
    });
};