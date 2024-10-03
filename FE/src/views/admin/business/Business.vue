<template>
  <div class="filter-section mb-4 p-3 border rounded bg-light">
    <a-form
      layout="vertical"
      class="mb-4 grid grid-cols-1 md:grid-cols-2 gap-4 p-5"
    >
      <a-form-item label="Mã" class="col-span-1">
        <a-input
          placeholder="Nhập mã doanh nghiệp"
          style="width: 100%"
          v-model:value="valueinputSearchCode"
        />
      </a-form-item>

      <a-form-item label="Tên doanh nghiệp" class="col-span-1">
        <a-input
          placeholder="Nhập tên doanh nghiệp"
          style="width: 90%"
          v-model:value="valueinputSearchName"
        />
        <a-button
          shape="circle"
          @click="handleSearch"
          class="ml-5 w-1 border-none"
        >
          <SearchOutlined style="font-size: 20px"
        /></a-button>
      </a-form-item>
    </a-form>
  </div>

  <div class="flex h-0 flex-1 flex-col">
    <metu-table
      :columns="columsBusiness"
      :total-pages="totalPages"
      :pagination-params="paramGetAllBusiness"
      :data-source="adminGetAllBusiness"
      :loading="isLoading || isFetching"
      @update:pagination-params="handlePaginationChange"
    >
      <template #bodyCell="{ column, record }">
        <div
          v-if="column.key === 'action'"
          class="space-x-2 flex items-center justify-center"
        >
          <a-tooltip title=" sửa doanh nghiệp" color="#FFC26E">
            <a-button
              size="large"
              type="red"
              @click="handUpdateModalBusiness(record)"
            >
              <EditOutlined style="color: red" />
            </a-button>
          </a-tooltip>
          <a-tooltip title="Xóa doanh nghiệp" color="#FFC26E">
            <a-button
              type="red"
              size="large"
              @click="handleDeleteBusiness(record.id)"
            >
              <DeleteOutlined
            /></a-button>
          </a-tooltip>
        </div>
      </template>
    </metu-table>
  </div>

  <div>
    <a-modal
      ref="modalRef"
      v-model:open="openModal"
      :wrap-style="{ overflow: 'hidden' }"
      @ok="adminUpdate"
    >
      <a-form-item
        name="code"
        label="Mã cở sở con"
        class="mt-6"
        :validate-status="errors.code ? 'error' : ''"
        :help="errors.code"
      >
        <a-input placeholder="" v-model:value="formRegister.code" />
      </a-form-item>
      <a-form-item
        name="name"
        label="Tên cở sở con"
        class="mt-6"
        :validate-status="errors.name ? 'error' : ''"
        :help="errors.name"
      >
        <a-input placeholder="" v-model:value="formRegister.name" />
      </a-form-item>
      <template #title>
        <div ref="modalTitleRef" style="width: 100%; cursor: move">
          Cập nhật doanh nghiệp
        </div>
      </template>
    </a-modal>
  </div>
</template>

<script lang="ts" setup>
import MetuTable from "@/components/forms/MetuTable.vue";
import {
  businessRespone,
  paramBusiness,
  paramBusinessGetAll,
} from "@/services/api/business/business.api";
import { computed, reactive, ref, watch } from "vue";
import {
  SearchOutlined,
  EditOutlined,
  DeleteOutlined,
} from "@ant-design/icons-vue";
import { toast } from "vue3-toastify";
import { Modal } from "ant-design-vue";
import {
  Admindeletebusiness,
  getListBusiness,
  updateAdminBusiness,
} from "@/services/service/example/business.action";
import { keepPreviousData, QueryClient } from "@tanstack/vue-query";
import { ERROR_MESSAGE } from "@/constants/message.constant";
const openModal = ref(false);
const valueinputSearchName = ref("");
const valueinputSearchCode = ref("");
const detailBusiness = ref<Partial<businessRespone>>({});
const formRegister = reactive<paramBusiness>({
  code: "",
  name: "",
});

const paramGetAllBusiness = ref<paramBusinessGetAll>({
  page: 1,
  size: 5,
  name: "",
  code: "",
});

const { data, isLoading, isFetching } = getListBusiness(paramGetAllBusiness, {
  refetchOnWindowFocus: false,
  placeholderData: keepPreviousData,
});

interface errors {
  code: string;
  name: string;
}

const handCloseModalBusiness = (record: businessRespone) => {
  openModal.value = false;
  resetErrors();
  detailBusiness.value = {};
};

const handlePaginationChange = (newParams: paramBusinessGetAll) => {
  paramGetAllBusiness.value = { ...paramGetAllBusiness.value, ...newParams };
};

const handUpdateModalBusiness = (record: businessRespone) => {
  formRegister.code = record.code || "";
  formRegister.name = record.name || "";
  openModal.value = true;
  detailBusiness.value = record;
};
const { mutateAsync: updateBusinessMutation } = updateAdminBusiness();
const { mutate: deleteBusiness } = Admindeletebusiness();

const adminUpdate = async () => {
  if (validate()) {
    try {
      await updateBusinessMutation({
        idBusiness: detailBusiness.value.id as string,
        params: formRegister,
      });

      toast.success("Cập nhật thành công!");
      if (typeof handCloseModalBusiness === "function") {
        openModal.value = false;
        resetErrors();
        detailBusiness.value = {};
      } else {
        console.warn("handCloseModalBusiness không phải là một hàm.");
      }
    } catch (error: any) {
      console.error("Lỗi khi cập nhật:", error);
      toast.error("Cập nhật thất bại! Vui lòng thử lại.");
    }
  } else {
    toast.error("Vui lòng kiểm tra lại thông tin.");
  }
};

const handleDeleteBusiness = (_businessId: string) => {
  Modal.confirm({
    content: "Bạn chắc chắn muốn xóa không!",
    centered: true,
    async onOk() {
      try {
        deleteBusiness(_businessId, {
          onSuccess: () => {
            toast.success("Xóa doanh nghiệp thành công!");
          },
          onError: (error: any) => {
            toast.error(
              error?.response?.data?.message ||
                ERROR_MESSAGE.SOMETHING_WENT_WRONG
            );
          },
        });
      } catch (error: any) {
        console.error("🚀 ~ handleDelete~ error:", error);
        toast.error(
          error?.response?.data?.message || ERROR_MESSAGE.SOMETHING_WENT_WRONG
        );
      }
    },
    cancelText: "Huỷ",
    onCancel() {
      Modal.destroyAll();
    },
  });
};

const resetErrors = () => {
  errors.code = "";
  errors.name = "";
};

const handleSearch = () => {
  paramGetAllBusiness.value.code = valueinputSearchCode.value;
  paramGetAllBusiness.value.name = valueinputSearchName.value;
};

const errors = reactive<errors>({
  code: "",
  name: "",
});

const validate = () => {
  let check = true;

  if (formRegister.code === "" || formRegister.code == null) {
    errors.code = "Vui lòng nhập code";
    check = false;
  }

  if (formRegister.name === "" || formRegister.name == null) {
    errors.name = "Vui lòng nhập name";
    check = false;
  }

  return check;
};

watch(
  () => paramGetAllBusiness.value.code,
  () => {},
  { deep: true }
);

watch(
  () => paramGetAllBusiness.value.name,

  () => {},
  { deep: true }
);

const columsBusiness = [
  {
    title: "STT",
    dataIndex: "stt",
    key: "stt",
    width: "100px",
    ellipsis: true,
  },
  {
    title: "Code",
    dataIndex: "code",
    key: "code",
    ellipsis: true,
  },
  {
    title: "Tên doanh nghiệp",
    dataIndex: "name",
    key: "name",
    width: "30%",
    ellipsis: true,
  },
  {
    title: "Mô Tả",
    dataIndex: "description",
    key: "description",
    ellipsis: true,
  },
  {
    title: "Hành Động",
    align: "center",
    width: "200px",
    key: "action",
  },
];

const adminGetAllBusiness = computed(() => data?.value?.data?.data || []);
const totalPages = computed(() => data?.value?.data?.totalPages || 0);
</script>
