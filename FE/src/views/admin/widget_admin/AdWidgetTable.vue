<template>
  <div class="shadow-xl p-3 m-3 rounded-md flex h-full flex-col overflow-auto">
    <div class="flex justify-between items-center min-h-36">
      <h2 class="p-4 flex items-center text-primary text-3xl font-semibold">
        <v-icon name="bi-list-ul" class="text-black" scale="2"/>
        <span class="ml-2 text-2xl text-black">Danh sách widget </span>
      </h2>
      <a-button type="primary" @click="$emit('handleOpenModalAdd')" size="large" class="m-4">
        Tạo mới
      </a-button>
    </div>
    <div class="flex h-0 flex-1 flex-col">
      <MetuTable wrapperClassName="min-h-[410px]" :columns="columnsSubject" :data-source="dataSource" :loading="loading"
                 :pagination-params="paginationParams || {}" :total-pages="totalPages || 0"
                 @update:pagination-params="$emit('update:paginationParams', $event)">
        <template #bodyCell="{ column, record }">
          <div v-if="column.key === 'action'" class="space-x-2 flex items-center justify-center">
            <a-tooltip title="Chỉnh sửa" color="#1E90FF">
              <a-button class="flex items-center justify-center" type="primary" size="large"
                        @click="$emit('handleOpenModalUpdate', record)" :icon="h(EditOutlined)"/>

            </a-tooltip>
            <a-tooltip title="Xóa" color="#1E90FF">
              <a-button class="flex items-center justify-center" type="primary" size="large"
                        @click="showConfirmDelete(record.id)" :icon="h(DeleteOutlined)"/>
            </a-tooltip>
          </div>
          <div v-if="column.key==='description'">
            {{ record.description.substring(0, 35) }}{{ record.description.length > 35 ? '...' : '' }}
          </div>
          <div v-if="column.key==='iconUrl'">
            <a-image :src="record.iconUrl" style="max-width: 100px; max-height: 100px;"/>
          </div>

          <div v-else>
            {{ record[column.dataIndex] }}
          </div>
        </template>
      </MetuTable>
    </div>

    <a-modal v-model:visible="confirmDeleteVisible" title="Xác nhận xóa?" @cancel="confirmDeleteVisible = false"
             @ok="handleDelete">
      <p>Bạn có chắc chắn muốn xóa không?</p>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import MetuTable from "@/components/forms/MetuTable.vue";
import {DeleteOutlined, EditOutlined} from "@ant-design/icons-vue";
import {h, ref} from "vue";
import {WidgetResponse} from "@/services/api/widget/ad-widget/ad-widget.api.ts";

defineProps({
  dataSource: Array<WidgetResponse>,
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
    align: "center",
    width: 100,
  },
  {
    title: "Mã",
    dataIndex: "code",
    key: "code",
    align: "center",
    width: 200,
  },
  {
    title: "Tên",
    dataIndex: "name",
    key: "name",
    align: "center",
    width: 250,
  },
  {
    title: "Thể loại",
    dataIndex: "categoryName",
    align: "center",
    key: "categoryName",
    width: 200,
  },
  {
    title: "Icon",
    align: "center",
    key: "iconUrl",
    width: 100,
  },
  {
    title: "Kiểu",
    dataIndex: "type",
    align: "center",
    key: "type",
    width: 200,
  },
  {
    title: "Mô Tả",
    key: "description",
    width: 300,
  },
  {
    title: "Hành Động",
    align: "center",
    width: "200px",
    key: "action",
  },
];
</script>
