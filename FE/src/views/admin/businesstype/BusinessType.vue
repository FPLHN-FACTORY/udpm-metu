<template>
  <div class="mt-4">
    <div class="flex justify-between items-center">
      <h2 class="p-4 flex items-center text-primary text-3xl font-semibold">
        <v-icon name="hi-office-building" class="text-black" scale="2" />
        <span class="m-2 text-3xl text-black">Quản lý thể loại doanh nghiệp</span>
      </h2>
    </div>
    <BusinessTypeFilter @filter="handleFilter" />
    <BusinessTypeTable :data-source="businessTypeData" :loading="isLoading || isFetching" :pagination-params="params"
      :total-pages="totalPages" @update:pagination-params="handlePaginationChange"
      @handleOpenModalUpdate="handleOpenModalUpdate" @handleOpenModalAdd="handleOpenModalAdd"
      @handle-confirm-delete="handleConfirmDelete" />
    <CreateUpdateBusinessTypeModal :open="open" @handleClose="handleClose" @cancel="open = false"
      :subject-detail="businessTypeDetail || null" :is-loading-detail="isLoadingDetail || false" />
  </div>
</template>

<script lang="ts" setup>
import CreateUpdateBusinessTypeModal from "@/views/admin/businesstype/BusinessTypeModel.vue";
import BusinessTypeFilter from "@/views/admin/businesstype/BusinessTypeFilter.vue";
import BusinessTypeTable from "@/views/admin/businesstype/BusinesstypeTable.vue";
import { ParamsGetFacility, BusinessTypeResponse } from "@/services/api/businesstype/businesstype.api";
import { useDetailBusinessType, useGetBusinessType, useDeleteBusinessType } from "@/services/service/businesstype/businesstype.action";
import { keepPreviousData } from "@tanstack/vue-query";
import { computed, ref } from "vue";
import { toast } from "vue3-toastify";

const params = ref<ParamsGetFacility>({
  page: 1,
  size: 10,
  search: ""
});

const open = ref(false);

const id = ref<string | null>(null);

const { data, isLoading, isFetching } = useGetBusinessType(params, {
  refetchOnWindowFocus: false,
  placeholderData: keepPreviousData,
});

const { data: dataDetail, isLoading: isLoadingDetail } = useDetailBusinessType(
  id,
  {
    refetchOnWindowFocus: false,
    enabled: () => !!id.value,
  }
);

const handlePaginationChange = (newParams: ParamsGetFacility) => {
  params.value = { ...params.value, ...newParams };
};

const handleFilter = (newParams: ParamsGetFacility) => {
  params.value = { ...params.value, ...newParams };
};


const { mutate: deleteBusinessType } = useDeleteBusinessType();

const handleConfirmDelete = async (id: string) => {
  try {
    await deleteBusinessType(id);
    toast.success(
      "Xóa thể loại doanh nghiệp thành công"
    );
  } catch (error) {
    console.error("Error deleting business type:", error);
  }
};
const handleClose = () => {
  open.value = false;
  id.value = null;
};

const handleOpenModalAdd = () => {
  open.value = true;
  id.value = null;
};

const handleOpenModalUpdate = (record: BusinessTypeResponse) => {
  id.value = record.id;
  open.value = true;
};

const businessTypeData = computed(() => data?.value?.data?.data || []);
const totalPages = computed(() => data?.value?.data?.totalPages || 0);
const businessTypeDetail = computed(() =>
  id.value
    ? {
      ...dataDetail.value?.data,
      id: id.value,
    }
    : null
);
</script>
