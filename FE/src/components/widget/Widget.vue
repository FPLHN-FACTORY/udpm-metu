<template>
  <div>
    <a-modal :open="open" width="40%" title="Danh sách nút METU"
             @cancel="handleClose">
      <div>
        <a-space style="width: 100%" direction="vertical" size="middle">
          <hr>
          <a-typography-title :level="5">Kho danh sách nút
            <hr>
          </a-typography-title>

          <a-space style="width: 100%" direction="vertical" size="middle">
            <template v-for="item in listWidgetGroupByCategory">
              <a-space style="width: 100%" direction="vertical" size="middle">
                <p>Nút {{ item?.categoryName.toLowerCase() }}:</p>
                <a-flex wrap="wrap" gap="small">
                  <template v-for="widget in item?.widgets">
                    <a-card hoverable style="width: 75px;" @click="()=>{
                      openFormWidget(widget);
                    }">
                      <template #cover>
                        <a-tooltip placement="top">
                          <template #title>
                            <span>{{ widget?.name }}</span>
                          </template>
                          <div class="flex justify-center">
                            <img :src="extractIconUrl(widget?.iconUrl)[0]"
                                 width="50px"
                                 height="50px"
                                 class="m-2"
                            />
                          </div>
                          <div class=" flex justify-center text-center">
                            <p class="widget-description w-[70px]">{{ widget?.name }}</p>
                          </div>
                        </a-tooltip>
                      </template>
                    </a-card>
                  </template>
                </a-flex>
              </a-space>
            </template>
          </a-space>
          <widget-book :widget="widgetProp.widget"
                       @handleOpenCreateWidgetModal="handleOpen"
                       @handleCloseModal="()=>{handleCloseWidgetModal(WidgetType.BOOK);}"
                       :open="openWidgetBook"/>
          <widget-call-me-back :widget="widgetProp.widget"
                               @handleOpenCreateWidgetModal="handleOpen"
                               @handleCloseModal="()=>{handleCloseWidgetModal(WidgetType.CALL_ME_BACK);}"
                               :open="openWidgetCallMeBack"/>
          <widget-call-now :widget="widgetProp.widget"
                           @handleOpenCreateWidgetModal="handleOpen"
                           @handleCloseModal="()=>{handleCloseWidgetModal(WidgetType.CALL_NOW);}"
                           :open=" openWidgetCallNow"/>
          <widget-email :widget="widgetProp.widget"
                        @handleOpenCreateWidgetModal="handleOpen"
                        @handleCloseModal="()=>{handleCloseWidgetModal(WidgetType.EMAIL);}"
                        :open=" openWidgetEmail"/>
          <widget-hotline :widget="widgetProp.widget"
                          @handleOpenCreateWidgetModal="handleOpen"
                          @handleCloseModal="()=>{handleCloseWidgetModal(WidgetType.HOTLINE);}"
                          :open="openWidgetHotline"/>
          <widget-link :widget="widgetProp.widget"
                       @handleOpenCreateWidgetModal="handleOpen"
                       @handleCloseModal="()=>{handleCloseWidgetModal(WidgetType.LINK);}"
                       :open="openWidgetLink"/>
          <widget-live-chat :widget="widgetProp.widget"
                            @handleOpenCreateWidgetModal="handleOpen"
                            @handleCloseModal="()=>{handleCloseWidgetModal(WidgetType.LIVE_CHAT);}"
                            :open=" openWidgetLiveChat"/>
          <widget-text :widget="widgetProp.widget"
                       @handleOpenCreateWidgetModal="handleOpen"
                       @handleCloseModal="()=>{handleCloseWidgetModal(WidgetType.TEXT);}"
                       :open=" openWidgetText"/>
          <widget-s-m-s :widget="widgetProp.widget"
                       @handleOpenCreateWidgetModal="handleOpen"
                       @handleCloseModal="()=>{handleCloseWidgetModal(WidgetType.SMS);}"
                       :open="openWidgetSMS"/>
        </a-space>
      </div>
      <template #footer>
        <a-button @click="handleClose">Đóng</a-button>
      </template>
    </a-modal>
  </div>
</template>

<script lang="ts" setup>
import WidgetBook from '@/components/widget/forms/WidgetBook.vue'
import WidgetCallMeBack from '@/components/widget/forms/WidgetCallMeBack.vue'
import WidgetCallNow from '@/components/widget/forms/WidgetCallNow.vue'
import WidgetEmail from '@/components/widget/forms/WidgetEmail.vue'
import WidgetHotline from '@/components/widget/forms/WidgetHotline.vue'
import WidgetLink from '@/components/widget/forms/WidgetLink.vue'
import WidgetLiveChat from '@/components/widget/forms/WidgetLiveChat.vue'
import WidgetText from '@/components/widget/forms/WidgetText.vue'
import WidgetSMS from '@/components/widget/forms/WidgetSMS.vue'

import {
  WidgetType,
  WidgetsGroupByCategory,
  Widget,
  extractIconUrl
} from '@/services/api/widget/widgetforms/widget-form.api.ts'

import {defineEmits, defineProps, reactive, ref} from 'vue'

let openWidgetText = ref<Boolean>(false);
let openWidgetLink = ref<Boolean>(false);
let openWidgetEmail = ref<Boolean>(false);
let openWidgetBook = ref<Boolean>(false);
let openWidgetCallMeBack = ref<Boolean>(false);
let openWidgetCallNow = ref<Boolean>(false);
let openWidgetHotline = ref<Boolean>(false);
let openWidgetLiveChat = ref<Boolean>(false);
let openWidgetSMS = ref<Boolean>(false);

const widgetProp = reactive({
  widget: Object as () => Widget
});

const props = defineProps({
  open: Boolean,
  listWidgetGroupByCategory: Array as () => WidgetsGroupByCategory[],
});
const emit = defineEmits(["handleCloseModal","handleOpenModal"]);

const handleClose = () => {
emit("handleCloseModal");
  handleOpenCreateWidgetModal();
};

const handleOpenCreateWidgetModal = () => {
  emit("handleOpenCreateWidgetModal");
}

const handleOpen = () => {
  emit("handleOpenModal");
}

const openFormWidget = (widget: Widget) => {
  widgetProp.widget = widget
  handleClose();
  switch (widget?.type) {
    case WidgetType.TEXT:
      openWidgetText.value = true;
      break;
    case WidgetType.LINK:
      openWidgetLink.value = true;
      break;
    case WidgetType.EMAIL:
      openWidgetEmail.value = true;
      break;
    case WidgetType.HOTLINE:
      openWidgetHotline.value = true;
      break;
    case WidgetType.BOOK:
      openWidgetBook.value = true;
      break;
    case WidgetType.CALL_ME_BACK:
      openWidgetCallMeBack.value = true;
      break;
    case WidgetType.CALL_NOW:
      openWidgetCallNow.value = true;
      break;
    case WidgetType.LIVE_CHAT:
      openWidgetLiveChat.value = true;
      break;
    case WidgetType.SMS:
      openWidgetSMS.value = true;
      break;
    default:
      return null;
  }
}

const handleCloseWidgetModal = (type: WidgetType) => {
  switch (type) {
    case WidgetType.TEXT:
      openWidgetText.value = false;
      break;
    case WidgetType.LINK:
      openWidgetLink.value = false;
      break;
    case WidgetType.EMAIL:
      openWidgetEmail.value = false;
      break;
    case WidgetType.HOTLINE:
      openWidgetHotline.value = false;
      break;
    case WidgetType.BOOK:
      openWidgetBook.value = false;
      break;
    case WidgetType.CALL_ME_BACK:
      openWidgetCallMeBack.value = false;
      break;
    case WidgetType.CALL_NOW:
      openWidgetCallNow.value = false;
      break;
    case WidgetType.LIVE_CHAT:
      openWidgetLiveChat.value = false;
      break;
    case WidgetType.SMS:
      openWidgetSMS.value = false;
      break;
    default:
      return null;
  }
}

</script>

<style scoped>
.widget-description {
  display: inline-block;
  max-width: 100%; /* Giới hạn chiều rộng */
  white-space: nowrap; /* Không cho phép xuống dòng */
  overflow: hidden; /* Ẩn phần văn bản vượt quá chiều rộng */
  text-overflow: ellipsis; /* Hiển thị dấu ... nếu quá dài */
}
</style>