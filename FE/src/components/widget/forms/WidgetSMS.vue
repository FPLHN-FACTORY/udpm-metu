<template>
  <div>
    <a-modal :open="open" width="40%" :title="'Cấu hình nút: ' +  widget?.name" @cancel="handleClose" @ok="handleOk">
      <a-space direction="vertical" size="middle" style="width: 100%">
        <hr>
        <a-tag style="width: 100%" color="red" class="tag-text">
          <strong>Mô tả:</strong><br/>
          {{ widget?.description }}
        </a-tag>
        <a-typography-title :level="5">Cấu hình hiển thị nút
          <hr>
        </a-typography-title>
        <p>Biểu tượng nút</p>
        <div>
          <div id="components-a-tooltip-demo-placement">
            <div :style="{  clear: 'both', whiteSpace: 'nowrap' }">
              <a-tooltip placement="bottom" color="#fff">
                <template #title>
                  <div>
                    <div v-for="item in extractIconUrl(widget?.iconUrl)" class="m-1">
                      <div class="border border-2 border-indigo-600 rounded-md w-fit" @click="()=>{
                          handleChangeCurrentIcon(item)
                        }">
                        <img v-if="extractIconUrl(widget?.iconUrl)?.length"
                             :src="extractIconUrl(item)"
                             :alt="widget?.name"
                             width="50px"
                             height="50px"
                             class="m-2"/>

                      </div>
                    </div>
                  </div>
                </template>
                <div class="relative w-fit p-1">
                  <div class=" w-fit">
                    <img v-if="extractIconUrl(widget?.iconUrl)?.length"
                         :src="currentIcon"
                         :alt="widget?.name"
                         width="50px"
                         height="50px"
                         class="m-2"/>

                  </div>
                  <div
                      class="absolute bottom-0 right-0 border border-inherit border-2 border-indigo-600 bg-white rounded-full m-1">
                    <v-icon name="bi-pencil" scale="1"/>
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
        <a-input :value="widget?.name" show-count :maxlength="100"/>
        <p>Nhập nội dung chú thích</p>
        <a-input v-model:value="descriptionOnHover" show-count :maxlength="100"
                 placeholder="Hiển thị chú thích khi di chuột vào"/>
        <a-typography-title :level="5">Cấu hình giá trị các thuộc tính nút
          <hr>
        </a-typography-title>
        <a-card>
          <a-space style="width: 100%" direction="vertical" size="small">
            <p>Nhập số điện thoại của bạn để khách nhắn tin đến (*)</p>
            <a-input v-model:value="link" show-count :maxlength="10"
                     placeholder="0987654321"/>
            <p>Nhập nội dung soạn sẵn cho khách hàng (*)</p>
            <a-input v-model:value="link" show-count :maxlength="500"
                     placeholder="SP 012345678 KH"/>
          </a-space>
        </a-card>
      </a-space>
    </a-modal>
  </div>
</template>
<script setup lang="ts">
import {defineProps, defineEmits, ref, watch} from "vue";
import {Widget, extractIconUrl} from "@/services/api/widget/widgetforms/widget-form.api.ts";

const prop = defineProps({
  open: Boolean,
  widget: Object as () => any | null,
  buttonId: String
});
let currentIcon = ref<string | null>(null);
watch(
    () => prop.widget?.iconUrl,
    (newIconUrl) => {
      if (newIconUrl) {
        const icons = extractIconUrl(newIconUrl);
        currentIcon.value = icons[0];
      } else {
        currentIcon.value = null;
      }
    },
    {immediate: true}
);
const handleChangeCurrentIcon = (url) => {
  currentIcon.value = url;
}

let descriptionOnHover = ref<string>("");
const emit = defineEmits(["handleCloseModal"]);

const handleOk = (e: MouseEvent) => {
  open = false;
};

const handleClose = () => {
  emit("handleCloseModal");
  handleOpenCreateWidgetModal();
};

const handleOpenCreateWidgetModal = () => {
  emit("handleOpenCreateWidgetModal");
};
</script>

<style scoped>
.tag-text {
  white-space: normal;
  line-height: 1.5;
}
</style>