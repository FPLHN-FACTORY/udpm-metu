<template>
  <div class="mt-4">
    <div class="flex justify-between items-center">
      <h2 class="p-4 flex items-center text-primary text-3xl font-semibold">
        <v-icon name="hi-office-building" class="text-black" scale="2" />
        <span class="m-2 text-3xl text-black">Quản lý nút Widget</span>
      </h2>
    </div>
    <AdWidgetFilter @filter="handleFilter" />
    <AdWidgetTable :data-source="widgetData" :loading="isLoading || isFetching" :pagination-params="params"
                       :total-pages="totalPages" @update:pagination-params="handlePaginationChange"
                       @handleOpenModalUpdate="handleOpenModalUpdate" @handleOpenModalAdd="handleOpenModalAdd"
                       @handle-confirm-delete="handleConfirmDelete" />
    <AdWidgetModal :open="open" @handleClose="handleClose" @cancel="open = false"
                                   :WidgetDetail="widgetDetail || null" :is-loading-detail="isLoadingDetail || false" />
  </div>
</template>

<script lang="ts" setup>
import {ParamsGetFacility} from "@/services/api/businesstype/businesstype.api";
import {keepPreviousData} from "@tanstack/vue-query";
import {computed, ref} from "vue";
import {toast} from "vue3-toastify";
import AdWidgetFilter from "@/views/admin/widget_admin/AdWidgetFilter.vue";
import AdWidgetTable from "@/views/admin/widget_admin/AdWidgetTable.vue";
import AdWidgetModal from "@/views/admin/widget_admin/AdWidgetModal.vue";
import {useDeleteWidget, useDetailWidget, useGetWidget} from "@/services/service/widget/ad-widget-action.ts";
import {ParamsGetWidget, WidgetResponse} from "@/services/api/widget/ad-widget/ad-widget.api.ts";

const params = ref<ParamsGetFacility>({
  page: 1,
  size: 10,
  search: ""
});

const open = ref(false);

const id = ref<string | null>(null);

const { data, isLoading, isFetching } = useGetWidget(params, {
  refetchOnWindowFocus: false,
  placeholderData: keepPreviousData,
});

const { data: dataDetail, isLoading: isLoadingDetail } = useDetailWidget(
    id,
    {
      refetchOnWindowFocus: false,
      enabled: () => !!id.value,
    }
);

const handlePaginationChange = (newParams: ParamsGetWidget) => {
  params.value = { ...params.value, ...newParams };
};



const handleFilter = (newParams: ParamsGetWidget) => {
  params.value = { ...params.value, ...newParams };
};


const { mutate: deleteWidget } = useDeleteWidget();

const handleConfirmDelete = async (id: string) => {
  try {
    await deleteWidget(id);
    toast.success(
        "Xóa thành công!"
    );

  } catch (error) {
    console.error("Error deleting widget:", error);
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

const handleOpenModalUpdate = (record: WidgetResponse) => {
  id.value = record.id;
  open.value = true;
};

const widgetData = computed(() => data?.value?.data?.data || []);
const totalPages = computed(() => data?.value?.data?.totalPages || 0);
const widgetDetail = computed(() =>
    id.value
        ? {
          ...dataDetail.value?.data,
          id: id.value,
        }
        : null

);
</script>
