<template>
  <div>
    <a-modal
      :open="open"
      width="40%"
      :title="'Cấu hình nút: ' + widget?.name"
      @cancel="handleClose"
      @ok="handleOk"
    >
      <a-space style="width: 100%" direction="vertical" size="middle">
        <hr />
        <a-tag style="width: 100%" color="red" class="tag-text">
          <strong>Mô tả:</strong><br />
          {{ widget?.description }}
        </a-tag>
        <a-typography-title :level="5"
          >Cấu hình hiển thị nút
          <hr />
        </a-typography-title>
        <p>Biểu tượng nút</p>
        <div>
          <div id="components-a-tooltip-demo-placement">
            <div :style="{ clear: 'both', whiteSpace: 'nowrap' }">
              <a-tooltip placement="bottom" color="#fff">
                <template #title>
                  <div>
                    <div
                      v-for="item in extractIconUrl(widget?.iconUrl)"
                      class="m-1"
                    >
                      <div
                        class="border-2 border-indigo-600 rounded-md w-fit"
                        @click="
                          () => {
                            handleChangeCurrentIcon(item);
                          }
                        "
                      >
                        <img
                          v-if="extractIconUrl(widget?.iconUrl)?.length"
                          :src="item"
                          :alt="widget?.name"
                          width="50px"
                          height="50px"
                          class="m-2"
                        />
                      </div>
                    </div>
                  </div>
                </template>
                <div class="relative w-fit p-1">
                  <div class="w-fit">
                    <img
                      v-if="extractIconUrl(widget?.iconUrl)?.length"
                      :src="currentIcon"
                      :alt="widget?.name"
                      width="50px"
                      height="50px"
                      class="m-2"
                    />
                  </div>
                  <div
                    class="absolute bottom-0 right-0 border-inherit border-2 border-indigo-600 bg-white rounded-full m-1"
                  >
                    <v-icon name="bi-pencil" scale="1" />
                  </div>
                </div>
              </a-tooltip>
            </div>
          </div>
        </div>
        <a-flex gap="middle" direction="vertical">
          <a-checkbox value="">Sử dụng hiệu ứng động</a-checkbox>
        </a-flex>
        <p>Tên hiển thị của nút</p>
        <a-input :value="widget?.name" show-count :maxlength="100" />
        <p>Nhập nội dung chú thích</p>
        <a-input
          v-model:value="descriptionOnHover"
          show-count
          :maxlength="100"
          placeholder="Hiển thị chú thích khi di chuột vào"
        />
        <a-typography-title :level="5"
          >Cấu hình giá trị các thuộc tính nút
          <hr />
        </a-typography-title>
        <a-card>
          <a-space style="width: 100%" direction="vertical" size="middle">
            <a-space style="width: 100%" direction="vertical" size="small">
              <p>Tiêu đề của form (*)</p>
              <a-input v-model:value="formTitle" show-count :maxlength="100" />
            </a-space>
            <a-space style="width: 100%" direction="vertical" size="small">
              <p>Nhập đường dẫn điều hướng sau khi gửi form thông tin</p>
              <a-input
                v-model:value="urlAffterSendForm"
                show-count
                :maxlength="100"
                placeholder="https://domain.com/thankyou-page"
              />
            </a-space>
          </a-space>
        </a-card>
        <a-typography-title :level="5"
          >Các trường thông của form liên hệ
          <hr />
        </a-typography-title>
        <a-card>
          <a-space style="width: 100%" direction="vertical" size="middle">
            <a-space style="width: 100%" direction="vertical" size="middle">
              <template v-for="item in fields">
                <a-space style="width: 100%" direction="vertical" size="small">
                  <p>Tên trường thông tin</p>
                  <a-row :gutter="16">
                    <a-col :span="6" :push="18">
                      <a-checkbox v-model:checked="item.required"
                        >Bắt buộc</a-checkbox
                      >
                    </a-col>
                    <a-col :span="18" :pull="6">
                      <a-input
                        v-model:value="item.nameField"
                        show-count
                        :maxlength="100"
                        placeholder="Tiêu đề của trường cần nhập thông tin"
                      />
                    </a-col>
                  </a-row>
                </a-space>
              </template>
            </a-space>
            <p
              class="underline text-blue-500 text-xs cursor-pointer"
              @click="handleAddField"
            >
              <v-icon name="fa-plus" scale="1" />
              Thêm trường thông tin
            </p>
          </a-space>
        </a-card>
      </a-space>
    </a-modal>
  </div>
</template>
<script setup lang="ts">
import { defineProps, defineEmits, watch, ref } from "vue";
import {
  field,
  extractIconUrl,
} from "@/services/api/widget/widgetforms/widget-form.api.ts";

const prop = defineProps({
  open: Boolean,
  widget: Object as () => any | null,
  buttonId: String,
});

let descriptionOnHover = ref<string>("");
let formTitle = ref<string>(
  "Để lại email, chúng tôi sẽ liên hệ với bạn sớm nhất"
);
let urlAffterSendForm = ref<string>("");

let currentIcon = ref<string | undefined>("");
watch(
  () => prop.widget?.iconUrl,
  (newIconUrl) => {
    if (newIconUrl) {
      const icons = extractIconUrl(newIconUrl);
      currentIcon.value = icons[0];
    } else {
      currentIcon.value = "";
    }
  },
  { immediate: true }
);
const handleChangeCurrentIcon = (url:string) => {
  currentIcon.value = url;
};

let fields = ref<Array<field>>([
  {
    nameField: "Email",
    required: true,
  },
  {
    nameField: "Để lại lời nhắn",
    required: false,
  },
]);

const emit = defineEmits(["handleCloseModal", "handleOpenCreateWidgetModal"]);

const handleOk = () => {
  emit("handleCloseModal");
};

const handleClose = () => {
  emit("handleCloseModal");
  handleOpenCreateWidgetModal();
};

const handleOpenCreateWidgetModal = () => {
  emit("handleOpenCreateWidgetModal");
};

const handleAddField = () => {
  fields.value.push({
    nameField: "",
    required: false,
  });
};
</script>

<style scoped>
.tag-text {
  white-space: normal;
  line-height: 1.5;
}
</style>
