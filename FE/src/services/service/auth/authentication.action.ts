import {getUserInformation} from "@/services/api/auth/authentication.api.ts";
import { UseQueryReturnType, useQuery } from "@tanstack/vue-query";

export const useGetUserInformation = (
    data?: string,
    options?: any
): UseQueryReturnType<
    Awaited<ReturnType<typeof getUserInformation>>,
    Error
> => {
    return useQuery({
        queryKey: ["userInformationKey", data],
        queryFn: () => getUserInformation(data),
        ...options,
    });
};