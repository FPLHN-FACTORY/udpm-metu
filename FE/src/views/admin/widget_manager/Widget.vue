<template>
  <div class="h-full">
    <!-- <a-input-search
      v-model="searchQuery"
      placeholder="Tìm kiếm..."
      @search="onSearch"
      class="mb-4"
    /> -->

    <MetuTable
      :columns="columns"
      :dataSource="widgetData"
      :loading="loading"
      :paginationParams="paginationParams"
      :totalPages="totalPages"
      :scroll="computedScroll"
      @tableChange="handleTableChange"
      @update:paginationParams="updatePaginationParams"
    />
  </div>
</template>

<script setup lang="ts">
import MetuTable from "@/components/forms/MetuTable.vue";
import { ref, computed, onMounted } from "vue";
import request from "@/services/request";

interface WidgetResponse {
  id: string;
  category: string;
  code: string;
  name: string;
  type: string;
  description: string;
  top: string;
}

// const searchQuery = ref("");
const loading = ref(false);
const widgetData = ref<WidgetResponse[]>([]);

const paginationParams = ref({ page: 1, size: 10 });
const totalPages = computed(() =>
  Math.ceil(widgetData.value.length / paginationParams.value.size)
);

const fetchDataSource = async () => {
  try {
    let response;
    response = await request.get("/view-widget");
    return response.data;
  } catch (error) {
    console.error("Error fetching widget data:", error);
    throw error;
  }
};

onMounted(async () => {
  try {
    widgetData.value = await fetchDataSource();
  } catch (error) {
    console.error("Error loading data:", error);
  }
});

const columns = [
  { title: "STT", dataIndex: "id", key: "id", width: 50 },
  { title: "Thể loại", dataIndex: "category", key: "category" },
  { title: "Mã", dataIndex: "code", key: "code" },
  { title: "Tên", dataIndex: "name", key: "name" },
  { title: "Kiểu mẫu", dataIndex: "type", key: "type" },
  { title: "Mô tả", dataIndex: "description", key: "description" },
  { title: "Top", dataIndex: "top", key: "top" },
];
const computedScroll = computed(() => {
  return {
    x: "100%", // Chiều rộng bảng có thể cuộn ngang
    y: "400px", // Chiều cao bảng cố định
  };
});
// const onSearch = () => {
//   // ... your search logic here, if needed
// };

const handleTableChange = (pagination: any, filters: any, sorter: any) => {
  // Handle table sorting and filtering here.
  console.log("Table changed:", pagination, filters, sorter);
};

const updatePaginationParams = (newParams: any) => {
  paginationParams.value = newParams;
};
</script>
