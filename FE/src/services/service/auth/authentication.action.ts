import {getBusinessType, login, LoginForm, register, RegisterForm} from "@/services/api/auth/authentication.api.ts";
import {useQuery, UseQueryReturnType} from "@tanstack/vue-query";
import {queryKey} from "@/constants/queryKey.ts";
import request from "@/services/request.ts";
import {PREFIX_API_LOGIN} from "@/constants/url.ts";
import {AxiosResponse} from "axios";
import {DefaultResponse} from "@/utils/types/api.common.ts";

export const useLogin = (
    params: LoginForm,
    options?: any
): UseQueryReturnType<
    Awaited<ReturnType<typeof login>>,
    Error
> => {
    return useQuery({
        queryKey: [queryKey.admin.authentication.login],
        queryFn: () => login(params),
        ...options
    });
};

export const useGetBusinessType = (
    options?: any
): UseQueryReturnType<
    Awaited<ReturnType<typeof getBusinessType>>,
    Error
> => {
    return useQuery({
        queryKey: [queryKey.admin.authentication.getBusinessType],
        queryFn: () => getBusinessType(),
        ...options
    });
}

export const useRegister = (
    params: RegisterForm,
    options?: any
): UseQueryReturnType<
    Awaited<ReturnType<typeof register>>,
    Error
> => {
    return useQuery({
        queryKey: [queryKey.admin.authentication.register],
        queryFn: () => register(params),
        ...options
    });
};