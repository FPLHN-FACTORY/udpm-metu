<template>
    <div class="shadow-xl p-3 m-3 rounded-md flex h-full flex-col overflow-auto">
        <div class="flex justify-between items-center min-h-36">
            <h2 class="p-4 flex items-center text-primary text-3xl font-semibold">
                <v-icon name="bi-list-ul" scale="2" />
                <span class="ml-2 text-2xl">Danh sách lịch sử thanh toán</span>
            </h2>
        </div>
        <div class="flex h-0 flex-1 flex-col">
            <metu-table
                wrapperClassName="min-h-[410px]"
                :columns="columnsPlanOrder" 
                :data-source="dataSource"
                :pagination-params="paginationParams || {}"
                :total-pages="totalPages || 0"
                @update:pagination-params="$emit('update:paginationParams', $event)"
            >
            </metu-table>
        </div>
    </div>
</template>

<script setup lang="ts">
import MetuTable from "@/components/forms/MetuTable.vue";
import { PlanOrderResponse } from "@/services/api/plan/planorder/planorder.api";
import { ColumnType } from 'ant-design-vue/es/table';


defineProps({
    dataSource: Array<PlanOrderResponse>,
    totalPages: Number,
    paginationParams: Object,
})

defineEmits(['handleOpenModalAdd', 'handleOpenModalUpdate', 'update:paginationParams'])

const columnsPlanOrder: ColumnType[] = [
    {
        title: "STT",
        dataIndex: "orderNumber",
        key: "index",
        ellipsis: true,
    },
    {
        title: "Tên doanh nghiệp",
        dataIndex: "businessName",
        key: "businessName",
        ellipsis: true,
    },
    {
        title: "Email",
        dataIndex: "email",
        key: "email",
        ellipsis: true,
    },
    {
        title: "Ngày tạo",
        dataIndex: "createAt",
        key: "createAt",
        ellipsis: true,
    },
    {
        title: "Ngày hết hạn",
        dataIndex: "expirationDate",
        key: "expirationDate",
        ellipsis: true,
    },
    {
        title: "Giá",
        dataIndex: "price",
        key: "price",
        ellipsis: true,
    },
    {
        title: "Số lượng",
        dataIndex: "quantity",
        key: "quantity",
        ellipsis: true,
    },
    {
        title: "Tổng tiền",
        dataIndex: "totalPrice",
        key: "totalPrice",
        ellipsis: true,
    },
    {
        title: "Trạng thái",
        dataIndex: "status",
        key: "status",
        ellipsis: true,
    },
    {
        title: "Tên Plan",
        dataIndex: "planName",
        key: "planName",
        ellipsis: true,
    },
];
</script>
