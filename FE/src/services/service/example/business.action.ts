import { queryKey } from "@/constants/queryKey.ts";
import {
  deleteBusiness,
  updateBusiness,
} from "./../../api/business/business.api";
import {
  useMutation,
  useQuery,
  useQueryClient,
  UseQueryReturnType,
} from "@tanstack/vue-query";
import { Ref } from "vue";
import {
  createUpdateBusinessParams,
  getAllBuisiness,
  paramBusinessGetAll,
} from "@/services/api/business/business.api";

export const getListBusiness = (
  params: Ref<paramBusinessGetAll>,
  options?: any
): UseQueryReturnType<Awaited<ReturnType<typeof getAllBuisiness>>, Error> => {
  return useQuery({
    queryKey: [queryKey.admin.getBusiness, params],
    queryFn: () => getAllBuisiness(params),
    ...options,
  });
};

export const updateAdminBusiness = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({
      idBusiness,
      params,
    }: {
      idBusiness: string;
      params: createUpdateBusinessParams;
    }) => updateBusiness(params, idBusiness),
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: [queryKey.admin.getBusiness],
      });
    },
    onError: (error) => {
      console.log("🚀 ~ useUpdateSubject ~ error:", error);
    },
  });
};

export const Admindeletebusiness = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (id: string) => deleteBusiness(id),
    onSuccess: () => {
      queryClient.invalidateQueries({
        predicate: (query) => {
          return query.queryKey[0] === queryKey.admin.getBusiness;
        },
      });
    },
  });
};
