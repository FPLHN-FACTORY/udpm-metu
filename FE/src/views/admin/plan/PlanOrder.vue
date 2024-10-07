<template>
  <div class="h-full">
    <a-input-search
      v-model="searchQuery"
      placeholder="Tìm kiếm..."
      @search="onSearch"
      class="mb-4"
    />
    <MetuTable
      :columns="columns"
      :dataSource="planOrderData"
      :loading="isLoading"
      :paginationParams="paginationParams"
      :totalPages="totalPages"
      @tableChange="handleTableChange"
      @update:paginationParams="updatePaginationParams"
    />
  </div>
</template>

<script setup lang="ts">
import MetuTable from "@/components/forms/MetuTable.vue";
import { useGetListPlanOrder } from "@/services/service/plan/planorder/planorder.action";
import { ref, computed, onMounted } from "vue";

const searchQuery = ref("");
const paginationParams = ref({ page: 1, size: 10 });

const planOrderData = ref([]);
const isLoading = ref(false);

const fetchPlanOrderData = async () => {
  try {
    isLoading.value = true;
    const { data } = await useGetListPlanOrder();
    planOrderData.value = data.value?.data;
  } catch (error) {
    console.error("Error fetching plan order data:", error);
  } finally {
    isLoading.value = false;
  }
};

onMounted(async () => {
  await fetchPlanOrderData();
});

const filteredPlanOrderData = computed(() => {
  if (searchQuery.value) {
    return planOrderData.value?.filter((item) =>
      item.email.toLowerCase().includes(searchQuery.value.toLowerCase())
    );
  }
  return planOrderData.value;
});

const totalPages = computed(() => 
  Math.ceil(filteredPlanOrderData.value?.length / paginationParams.value.size)
);

const columns = [
  { title: "Email", dataIndex: "email", key: "email" },
  { title: "Ngày tạo", dataIndex: "createAt", key: "createAt", render: (text) => new Date(text).toLocaleString() },
  { title: "Ngày hết hạn", dataIndex: "expDate", key: "expDate", render: (text) => new Date(text).toLocaleString() },
  { title: "Giá", dataIndex: "price", key: "price", render: (text) => `$${text.toFixed(2)}` },
  { title: "Số lượng", dataIndex: "quantity", key: "quantity" },
  { title: "Trạng thái", dataIndex: "status", key: "status" },
  { title: "Tên Plan", dataIndex: ["plans", 0, "name"], key: "planName" },
  { title: "Tên Business", dataIndex: ["businesses", 0, "name"], key: "businessName" },
];

// Hàm xử lý khi bảng thay đổi
const handleTableChange = (pagination, filters, sorter) => {
  console.log("Table changed:", pagination, filters, sorter);
  paginationParams.value = {
    page: pagination.current,
    size: pagination.pageSize,
  };
  fetchPlanOrderData(); // Gọi lại dữ liệu mới
};

// Cập nhật tham số phân trang
const updatePaginationParams = (newParams) => {
  paginationParams.value = newParams;
  fetchPlanOrderData(); // Gọi lại dữ liệu mới
};
</script>
