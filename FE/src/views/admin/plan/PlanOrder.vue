<template>
  <div class="h-full">
    <a-input-search
      v-model="searchQuery"
      placeholder="Tìm kiếm..."
      @input="onSearch"
      class="mb-4"
    />
    <MetuTable
      :columns="columns"
      :dataSource="filteredPlanOrderData"
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
import { ref, computed, watch } from "vue";
import { useGetListPlanOrder } from "@/services/service/plan/planorder/planorder.action";

// Trạng thái tìm kiếm
const searchQuery = ref("");
const paginationParams = ref({ page: 1, size: 10 });

// Gọi hook để lấy danh sách đơn đặt kế hoạch từ API và quản lý trạng thái tải
const { data: listPlanOrders, isLoading, refetch } = useGetListPlanOrder({
  refetchOnWindowFocus: false, // Không tự động gọi lại API khi tab được kích hoạt
});

// Dữ liệu đã lọc theo tìm kiếm
const filteredPlanOrderData = computed(() => {
  const planOrders = listPlanOrders?.value?.data || [];
  return planOrders; // Chúng ta không cần lọc ở đây mà sẽ gọi API với query
});

// Tính toán tổng số trang
const totalPages = computed(() => 
  Math.ceil(listPlanOrders?.value?.data?.length / paginationParams.value.size)
);

// Cập nhật cột để bao gồm STT
const columns = [
  { title: "STT", dataIndex: "stt", key: "stt" }, // Thêm cột STT
  { title: "Tên doanh nghiệp", dataIndex: ["businesses", 0, "name"], key: "businessName" },
  { title: "Email", dataIndex: "email", key: "email" },
  {
    title: "Ngày tạo",
    dataIndex: "createAt",
    key: "createAt",
    render: (text) => new Date(text * 1000).toLocaleString(), // Định dạng lại ngày
  },
  {
    title: "Ngày hết hạn",
    dataIndex: "expirationDate",
    key: "expirationDate",
    render: (text) => new Date(text * 1000).toLocaleString(), // Định dạng lại ngày
  },
  { title: "Giá", dataIndex: "price", key: "price", render: (text) => `$${text.toFixed(2)}` },
  { title: "Số lượng", dataIndex: "quantity", key: "quantity" },
  { title: "Tổng tiền", dataIndex: "totalPrice", key: "totalPrice" },
  { title: "Trạng thái", dataIndex: "status", key: "status" },
  { title: "Tên Plan", dataIndex: ["plans", 0, "name"], key: "planName" },
];

// Hàm xử lý khi bảng thay đổi
const handleTableChange = (pagination, filters, sorter) => {
  console.log("Table changed:", pagination, filters, sorter);
  paginationParams.value = {
    page: pagination.current,
    size: pagination.pageSize,
  };
  refetch(); // Gọi lại API với tham số phân trang mới
};

// Cập nhật tham số phân trang
const updatePaginationParams = (newParams) => {
  paginationParams.value = newParams;
  refetch(); // Gọi lại API với tham số phân trang mới
};

// // Hàm xử lý tìm kiếm
// const onSearch = () => {
//   refetch({ 
//     email: searchQuery.value, // Truyền lại tham số tìm kiếm email
//     page: paginationParams.value.page, // Truyền lại trang hiện tại
//     size: paginationParams.value.size // Truyền lại kích thước trang
//   });
// };

// // Theo dõi sự thay đổi của searchQuery để gọi lại API
// watch(searchQuery, (newValue) => {
//   refetch({ 
//     email: newValue, // Truyền lại tham số tìm kiếm email mới
//     page: paginationParams.value.page, // Truyền lại trang hiện tại
//     size: paginationParams.value.size // Truyền lại kích thước trang
//   });
// });

</script>
