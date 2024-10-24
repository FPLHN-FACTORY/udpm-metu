<template>
  <div>
    <a-modal :open="open" :title="modalTitle" @cancel="handleClose" @ok="handleAddOrUpdate" :ok-text="okText"
             destroyOnClose centered>
      <div v-if="props.isLoadingDetail" class="flex justify-center items-center">
        <a-spin/>
      </div>
      <div v-else>
        <a-form layout="vertical">
          <template v-for="field in formFields">
            <a-form-item :label="field.label" :name="field.name" v-bind="validateInfos[field.name]">
              <component
                  v-if="field.component.includes('a-input')"
                  :is="field.component"
                  v-bind="field.props"
                  v-model:value="modelRef[field.name]"/>
              <component
                  v-else-if="field.name.includes('type')"
                  :is="field.component"
                  :options="widgetTypesList"
                  v-bind="field.props"
                  v-model:value="modelRef[field.name]"/>
              <component
                  v-else
                  :is="field.component"
                  v-bind="field.props"
                  :options="categoriesData"
                  v-model:value="modelRef[field.name]"
              />

            </a-form-item>
          </template>
        </a-form>
      </div>
    </a-modal>
  </div>
</template>

<script lang="ts" setup>
import {ERROR_MESSAGE} from "@/constants/message.constant";
import {Form} from "ant-design-vue";
import {computed, reactive, watch} from "vue";
import {toast} from "vue3-toastify";
import {useCreateWidget, useUpdateWidget} from "@/services/service/widget/ad-widget-action.ts";
import {WidgetType} from "@/services/api/widget/widgetforms/widget-form.api.ts";
import {useGetCategories} from "@/services/service/category/ad-categories.action.ts";
import {CategoryResponse} from "@/services/api/category/ad-categories.api.ts";

interface WidgetForm {
  categoryId: string,
  code: string,
  iconUrl: string,
  type: string,
  name: string,
  description: string,
}

const props = defineProps({
  open: Boolean,
  WidgetDetail: Object as () => WidgetForm | null,
  isLoadingDetail: Boolean,
});

const widgetTypesList = Object.entries(WidgetType).map(([key, value]) => ({
  value: value,
  label: key.replace(/_/g, ' ').toLowerCase().replace(/\b\w/g, char => char.toUpperCase()), // Chuyển đổi key thành label
}));

const emit = defineEmits(["handleClose"]);

const {mutate: createWidget} = useCreateWidget();
const {mutate: updateWidget} = useUpdateWidget();

const modelRef = reactive<WidgetForm>({
  categoryId: '',
  code: '',
  iconUrl: '',
  type: widgetTypesList[0].value,
  name: '',
  description: '',
});

const rulesRef = reactive({
  code: [
    {required: true, message: "Vui lòng nhập mã cho widget", trigger: "blur"},
    {min: 3, max: 30, message: "Mã phải từ 5 đến 30 ký tự", trigger: "blur"},
  ],
  iconUrl: [
    {required: true, message: "Vui lòng nhập đường dẫn", trigger: "blur"},
  ],
  name: [
    {required: true, message: "Vui lòng nhập tên widget", trigger: "blur"},
    {min: 5, max: 255, message: "Tên widget phải từ 5 đến 255 ký tự", trigger: "blur"},
  ],
  description: [
    {required: false, message: "Vui lòng nhập mô tả", trigger: "blur"},
    {max: 500, message: "Mô tả không được vượt quá 500 ký tự", trigger: "blur"},
  ],
  categoryId: [{required: false, message: "Vui lòng nhập mô tả", trigger: "blur"}],
});

const {resetFields, validate, validateInfos} = Form.useForm(
    modelRef,
    rulesRef
);

const modalTitle = computed(() =>
    props.WidgetDetail ? "Cập nhật Widget" : "Thêm Widget"
);

const okText = computed(() =>
    props.WidgetDetail ? "Cập nhật Widget" : "Thêm Widget"
);

watch(
    () => props.WidgetDetail,
    (newVal) => {
      if (newVal) {
        Object.assign(modelRef, {
          name: newVal.name,
          code: newVal.code,
          description: newVal.description,
          iconUrl: newVal.iconUrl,
          type: newVal.type,
          categoryId: newVal.categoryId,
        });
      } else {
        resetFields();
      }
    },
    {immediate: true}
);

const formFields = computed(() => [
  {
    label: "Tên widget",
    name: "name",
    component: "a-input",
    props: {placeholder: "Nhập tên của widget"},
  },
  {
    label: "Mã widget",
    name: "code",
    component: "a-input",
    props: {placeholder: "Nhập mã của widget"},
  },
  {
    label: "Đường dẫn ",
    name: "iconUrl",
    component: "a-input",
    props: {placeholder: "Nhập mã của widget"},
  },
  {
    label: "Kiểu",
    name: "type",
    component: "a-select",
    props: {placeholder: "Nhập mã của widget"},
  },
  {
    label: "Thể loại",
    name: "categoryId",
    component: "a-select",
    props: {placeholder: "Nhập mã của widget"},
  },
  {
    label: "Mô tả",
    name: "description",
    component: "a-input",
    props: {placeholder: "Nhập mô tả"},
  },
]);

const {data: categories} = useGetCategories();
const categoriesData = computed(() => {
  return categories?.value?.data?.map((item: CategoryResponse) => ({
    value: item.id,
    label: item.name
  })) || [];
})

const handleAddOrUpdate = async () => {
  const payload = {
    categoryId: modelRef.categoryId,
    code: modelRef.code,
    iconUrl: modelRef.iconUrl,
    type: modelRef.type,
    name: modelRef.name,
    description: modelRef.description,
  };
  await validate();
  try {

    if (props.WidgetDetail) {
      await updateWidget({
        id: props.WidgetDetail.id,
        params: payload,
      });
    } else {
      await createWidget(payload);
    }
    toast.success(
        props.WidgetDetail
            ? "Cập nhật Widget thành công!"
            : "Thêm Widget thành công"
    );
    emit("handleClose");
  } catch (error: any) {
    console.error("🚀 ~ handleAddOrUpdate ~ error:", error);
    toast.error(
        error?.response?.data?.message || ERROR_MESSAGE.SOMETHING_WENT_WRONG
    );
  }
};

const handleClose = () => {
  emit("handleClose");
  resetFields();
};
</script>