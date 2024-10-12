<template>
    <div>
        <a-modal :open="open" :title="modalTitle" @cancel="handleClose" @ok="handleAddOrUpdate" :ok-text="okText"
            destroyOnClose centered>
            <div v-if="props.isLoadingDetail" class="flex justify-center items-center">
                <a-spin />
            </div>
            <div v-else>
                <a-form layout="vertical">
                    <template v-for="field in formFields">
                        <a-form-item :label="field.label" :name="field.name" v-bind="validateInfos[field.name]">
                            <component :is="field.component" v-bind="field.props" v-model:value="modelRef[field.name]">
                            </component>
                        </a-form-item>
                    </template>
                </a-form>
            </div>
        </a-modal>
    </div>
</template>

<script lang="ts" setup>
import { ERROR_MESSAGE } from "@/constants/message.constant";
import {
    useCreateBusinessType,
    useUpdateBusinessType,
} from "@/services/service/businesstype/businesstype.action";
import { Form } from "ant-design-vue";
import { computed, reactive, watch } from "vue";
import { toast } from "vue3-toastify";

interface BusinessTypeForm {
    name: string;
    description: string;
}

const props = defineProps({
    open: Boolean,
    BusinessTypeDetail: Object as () => any | null,
    isLoadingDetail: Boolean,
});

const emit = defineEmits(["handleClose"]);

const { mutate: createBusinessType } = useCreateBusinessType();
const { mutate: updateBusinessType } = useUpdateBusinessType();

const modelRef = reactive<BusinessTypeForm>({
    name: "",
    description: "",
});

const rulesRef = reactive({
    name: [
        { required: true, message: "Vui lòng nhập tên thể loại doanh nghiệp", trigger: "blur" },
        { min: 5, max: 255, message: "Tên thể loại doanh nghiệp phải từ 5 đến 255 ký tự", trigger: "blur" },
    ],
    description: [
        { required: false, message: "Vui lòng nhập mô tả", trigger: "blur" },
        { max: 500, message: "Mô tả không được vượt quá 500 ký tự", trigger: "blur" },
    ],
});


const { resetFields, validate, validateInfos } = Form.useForm(
    modelRef,
    rulesRef
);

const modalTitle = computed(() =>
    props.BusinessTypeDetail ? "Cập nhật thể loại doanh nghiệp" : "Thêm thể loại doanh nghiệp"
);

const okText = computed(() =>
    props.BusinessTypeDetail ? "Cập nhật thể loại doanh nghiệp" : "Thêm thể loại doanh nghiệp"
);

watch(
    () => props.BusinessTypeDetail,
    (newVal) => {
        if (newVal) {
            Object.assign(modelRef, {
                name: newVal.name,
                description: newVal.description,
            });
        } else {
            resetFields();
        }
    },
    { immediate: true }
);

const formFields = computed(() => [
    {
        label: "Tên thể loại doanh nghiệp",
        name: "name",
        component: "a-input",
        props: { placeholder: "Nhập tên thể loại doanh nghiệp" },
    },
    {
        label: "Mô tả",
        name: "description",
        component: "a-input",
        props: { placeholder: "Nhập mô tả" },
    },
]);

const handleAddOrUpdate = async () => {
    const payload = {
        name: modelRef.name,
        description: modelRef.description,
    };

    try {
        await validate();
        if (props.BusinessTypeDetail) {
            await updateBusinessType({
                id: props.BusinessTypeDetail.id,
                params: payload,
            });
        } else {
            await createBusinessType(payload);
        }
        toast.success(
            props.BusinessTypeDetail
                ? "Cập nhật thể loại doanh nghiệp thành công"
                : "Thêm thể loại doanh nghiệp thành công"
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