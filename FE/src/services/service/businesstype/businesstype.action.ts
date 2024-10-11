import { queryKey } from "@/constants/queryKey";
import {
    addBusinessType,
    CreateUpdateBusinessTypeParams,
    deleteBusinessType,
    getAllBusinessType,
    ParamsGetFacility,
    updateBusinessType,
    getDetailBusinessType
} from "@/services/api/businesstype/businesstype.api";
import {
    useMutation,
    useQuery,
    useQueryClient,
    UseQueryReturnType,
} from "@tanstack/vue-query";
import { Ref } from "vue";


export const useGetBusinessType = (params: Ref<ParamsGetFacility>,
    options?: any)
    : UseQueryReturnType<Awaited<ReturnType<typeof getAllBusinessType>>, Error> => {
    return useQuery({
        queryKey: [queryKey.admin.businessType.businesstypeList, params],
        queryFn: () => getAllBusinessType(params),
        ...options
    });
};

export const useCreateBusinessType = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: (params: CreateUpdateBusinessTypeParams) => addBusinessType(params),
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: [queryKey.admin.businessType.businesstypeList],
            });
        },
        onError: (error) => {
            console.log("🚀 ~ useCreateBusinessType ~ error:", error);
        },
    });
};

export const useUpdateBusinessType = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: ({
            id,
            params,
        }: {
            id: string;
            params: CreateUpdateBusinessTypeParams;
        }) => updateBusinessType(params, id),
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: [queryKey.admin.businessType.businesstypeList],
            });
        },
        onError: (error) => {
            console.log("🚀 ~ useUpdateBusinessType ~ error:", error);
        },
    });
};

export const useDeleteBusinessType = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: (id: string) => deleteBusinessType(id),
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: [queryKey.admin.businessType.businesstypeList],
            });
        },
        onError: (error) => {
            console.log("🚀 ~ useDeleteBusinessType ~ error:", error);
        },
    });
};

export const useDetailBusinessType = (
    id: Ref<string | null>,
    options?: any
): UseQueryReturnType<Awaited<ReturnType<typeof getDetailBusinessType>>, Error> => {
    return useQuery({
        queryKey: [queryKey.admin.businessType.businesstypeList, id],
        queryFn: () => getDetailBusinessType(id.value),
        ...options,
    });
};