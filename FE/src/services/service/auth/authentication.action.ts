import {login, Login} from "@/services/api/auth/authentication.api.ts";
import {useMutation, useQueryClient} from "@tanstack/vue-query";

export const useLogin = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: (params: Login) =>
            login(params),
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: ["userInformationKey"],
            });
        },
        onError: (error) => {
            console.log("🚀 ~ useCreateDepartment ~ error:", error);
        },
    });
};