<template>
  <div class="shadow-xl p-3 m-3 rounded-md flex h-full flex-col overflow-auto">
    <div class="flex justify-between items-center min-h-36">
      <h2 class="p-4 flex items-center text-primary text-3xl font-semibold">
        <v-icon name="bi-list-ul" class="text-black" scale="2" />
        <span class="ml-2 text-2xl text-black">Danh sách thể loại doanh nghiệp </span>
      </h2>
      <a-button type="primary" @click="$emit('handleOpenModalAdd')" size="large" class="m-4">
        Tạo thể loại
      </a-button>
    </div>
    <div class="flex h-0 flex-1 flex-col">
      <MetuTable wrapperClassName="min-h-[410px]" :columns="columnsSubject" :data-source="dataSource" :loading="loading"
        :pagination-params="paginationParams || {}" :total-pages="totalPages || 0"
        @update:pagination-params="$emit('update:paginationParams', $event)">
        <template #bodyCell="{ column, record }">
          <div v-if="column.key === 'action'" class="space-x-2 flex items-center justify-center">
            <a-tooltip title="Chỉnh sửa thể loại" color="#1E90FF">
              <a-button class="flex items-center justify-center" type="primary" size="large"
                @click="$emit('handleOpenModalUpdate', record)" :icon="h(EditOutlined)" />
            </a-tooltip>
            <a-tooltip title="Xóa thể loại" color="#1E90FF">
              <a-button class="flex items-center justify-center" type="primary" size="large"
                @click="showConfirmDelete(record.id)" :icon="h(DeleteOutlined)" />
            </a-tooltip>
          </div>
          <div v-else>
            {{ record[column.dataIndex] }}
          </div>
        </template>
      </MetuTable>
    </div>

    <a-modal v-model:visible="confirmDeleteVisible" title="Xác nhận xóa" @cancel="confirmDeleteVisible = false"
      @ok="handleDelete">
      <p>Bạn có chắc chắn muốn xóa thể loại này không?</p>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import MetuTable from "@/components/forms/MetuTable.vue";
import { BusinessTypeResponse } from "@/services/api/businesstype/businesstype.api";
import { EditOutlined,DeleteOutlined } from "@ant-design/icons-vue";
import { h, ref } from "vue";

defineProps({
  dataSource: Array<BusinessTypeResponse>,
  loading: Boolean,
  paginationParams: Object,
  totalPages: Number,
});

const emit = defineEmits([
  "update:paginationParams",
  "handleOpenModalUpdate",
  "handleOpenModalAdd",
  "handleConfirmDelete"
]);

const confirmDeleteVisible = ref(false);
const idToDelete = ref<string | null>(null);

const showConfirmDelete = (id: string) => {
  confirmDeleteVisible.value = true;
  idToDelete.value = id;
};

const handleDelete = () => {
  if (idToDelete.value) {
    emit('handleConfirmDelete', idToDelete.value);
  }
  confirmDeleteVisible.value = false;
  idToDelete.value = null;
};

const columnsSubject = [
  {
    title: "STT",
    dataIndex: "stt",
    key: "stt",
    ellipsis: true,
  },
  {
    title: "Mã",
    dataIndex: "code",
    key: "code",
  },
  {
    title: "Tên",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "Mô Tả",
    dataIndex: "description",
    key: "description",
  },
  {
    title: "Hành Động",
    align: "center",
    width: "200px",
    key: "action",
  },
];
</script>
