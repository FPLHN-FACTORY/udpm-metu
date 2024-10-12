<template>
  <div class="mt-4">
    <div class="flex justify-between items-center">
      <h2 class="p-4 flex items-center text-primary text-3xl font-semibold">
        <v-icon name="hi-office-building" scale="2" />
        <span class="m-2 text-3xl">Lịch sử thanh toán</span>
      </h2>
    </div>
    <plan-order-filter @filter="handleFilter" />
    <plan-order-table :data-source="planOrderData"
      :total-pages="TotalPages"
      :pagination-params="planOrderParams" @update:pagination-params="handleChangePagination" />
  </div>
</template>

<script setup lang="ts">
import { computed, ref, onMounted } from 'vue';
import PlanOrderFilter from '@/views/admin/plan/PlanOrderFilter.vue';
import PlanOrderTable from '@/views/admin/plan/PlanOrderTable.vue';
import { useGetListPlanOrder } from '@/services/service/plan/planorder/planorder.action';
import { ParamsGetPlanOrder } from '@/services/api/plan/planorder/planorder.api';
import { keepPreviousData } from '@tanstack/vue-query';

const planOrderParams = ref<ParamsGetPlanOrder>({
  page: 1,
  size: 5
})

const { data: listPlanOrder } = useGetListPlanOrder(planOrderParams, {
  refetchOnWindowFocus: false,
  placeholderData: keepPreviousData,
});

const handleChangePagination = (newParams: ParamsGetPlanOrder) => {
  planOrderParams.value = { ...planOrderParams.value, ...newParams }
}
const handleFilter = (newParams: ParamsGetPlanOrder) => {
  planOrderParams.value = { ...planOrderParams.value, ...newParams }
}

const planOrderData = computed(() => {
  return listPlanOrder?.value?.data?.data.map(item => ({
    email: item[0],
    createAt: item[1],
    expirationDate: item[2],
    price: item[3],
    quantity: item[4],
    status: item[5],
    businessName: item[6],
    packageType: item[7]
  })) || [];
});

const TotalPages = computed(() => listPlanOrder?.value?.data?.totalPages || 0)

onMounted(() => {
  console.log('Dữ liệu từ API:', handleFilter);
});

</script>
