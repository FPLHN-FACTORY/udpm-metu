import {useMutation, useQuery, useQueryClient, UseQueryReturnType} from "@tanstack/vue-query";
import {queryKey} from "@/constants/queryKey.ts";
import {
    addWidget,
    CreateUpdateWidgetParams,
    deleteWidget,
    getAllWidget,
    getDetailWidget,
    ParamsGetWidget,
    updateWidget
} from "@/services/api/widget/ad-widget/ad-widget.api.ts";
import {Ref} from "vue";


export const useGetWidget = (params: Ref<ParamsGetWidget>,
                                   options?: any)
    : UseQueryReturnType<Awaited<ReturnType<typeof getAllWidget>>, Error> => {
    return useQuery({
        queryKey: [queryKey.admin.widget.widgetAd, params],
        queryFn: () => getAllWidget(params),
        ...options
    });
};

export const useCreateWidget = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: (params: CreateUpdateWidgetParams) => addWidget(params),
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: [queryKey.admin.widget.widgetAd],
            });
        },
        onError: (error) => {
            console.log("🚀 ~ useCreateWidget ~ error:", error);
        },
    });
};


export const useDetailWidget = (
    id: Ref<string | null>,
    options?: any
): UseQueryReturnType<Awaited<ReturnType<typeof getDetailWidget>>, Error> => {
    return useQuery({
        queryKey: [queryKey.admin.widget.widgetAd, id],
        queryFn: () => getDetailWidget(id.value),
        ...options,
    });
};

export const useUpdateWidget = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: ({
                         id,
                         params,
                     }: {
            id: string;
            params: CreateUpdateWidgetParams;
        }) => updateWidget(params, id),
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: [queryKey.admin.widget.widgetAd],
            });
        },
        onError: (error) => {
            console.log("🚀 ~ useUpdateWidget ~ error:", error);
        },
    });
};

export const useDeleteWidget = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: (id: string) => deleteWidget(id),
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: [queryKey.admin.businessType.businesstypeList],
            });
        },
        onError: (error) => {
            console.log("🚀 ~ useDeleteWidget ~ error:", error);
        },
    });
};