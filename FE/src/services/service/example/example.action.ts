import {getUsers} from "@/services/api/example/example.api";
import {useMutation, useQueryClient} from "@tanstack/vue-query";

export const useGetListUser = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: () => getUsers(),
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: ["getListUser"],
            });
        },
        onError: (error) => {
            console.log("🚀 ~ getListUser ~ error:", error);
        },
    });
};