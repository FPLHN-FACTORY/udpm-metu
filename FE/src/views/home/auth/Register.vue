<template>
  <div class="text-center flex flex-col gap-5">
    <a-form layout="vertical" class="text-left text-xs flex flex-col gap-5">
      <a-form-item
        class="m-0"
        v-for="field in formFields"
        :name="field.name"
        v-bind="validateInfos[field.name]"
      >
        <component
          v-if="field.component.includes('a-input')"
          :is="field.component"
          v-bind="field.props"
          v-model:value="modelRef[field.name]"
          v-bind:class="{
            'h-12 rounded-2 text-xs': true,
            'some-other-class': true,
          }"
        />
        <component
          v-else
          :is="field.component"
          v-bind="field.props"
          :options="businessTypesData"
          v-model:value="modelRef[field.name]"
          size="large"
          class="h-12 rounded-2 text-xs"
        />
      </a-form-item>
    </a-form>

    <a-button
      @click="handleSubmitRegister()"
      html-type="submit"
      :loading="isRegisterPass"
      class="h-12 w-full rounded-2xl bg-blue-700 text-white hover:bg-blue-900 hover:text-white cursor-pointer text-xs"
    >
      Đăng ký
    </a-button>

    <span class="text-gray-500 cursor-default text-xs"
      >Bạn đã có tài khoản?</span
    >

    <div class="flex justify-center items-center flex-col">
      <button
        @click="route.push(ROUTES_CONSTANTS.AUTHENTICATION.children.LOGIN.name)"
        type="button"
        class="py-3.5 border-2 rounded-2xl w-full text-xs text-gray-900"
      >
        Đăng nhập
      </button>
    </div>

    <span class="text-gray-500 cursor-default text-xs"> Or sign up with </span>

    <div class="flex justify-center items-center flex-col">
      <button
        @click="handleRegisterGoogle"
        type="button"
        class="flex justify-center items-center py-3.5 border-2 rounded-2xl w-full text-xs text-gray-900"
        :disabled="isRegisterGoogle"
      >
        <v-icon name="fc-google" class="mr-2" />

        Google
      </button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { URL_FRONTEND, URL_OAUTH2_GOOGLE } from "@/constants/url";
import { ROUTES_CONSTANTS } from "@/constants/path.ts";
import { ERROR_MESSAGE } from "@/constants/message.constant.ts";
import route from "@/routes/route.ts";
import { computed, reactive, ref, watch } from "vue";
import { toast } from "vue3-toastify";
import { keepPreviousData } from "@tanstack/vue-query";
import {
  useGetBusinessType,
  useRegister,
} from "@/services/service/auth/authentication.action.ts";
import {
  BusinessType,
  RegisterForm,
} from "@/services/api/auth/authentication.api.ts";
import { Form } from "ant-design-vue";

const isRegisterPass = ref(false);
const isRegisterGoogle = ref(false);

const modelRef = reactive<RegisterForm>({
  fullName: "",
  email: "",
  businessName: "",
  businessType: null,
  password: "",
  rePassword: "",
});

const rulesRef = reactive({
  fullName: [
    { required: true, message: "Vui lòng nhập họ tên", trigger: "blur" },
    { min: 2, message: "Họ tên phải có ít nhất 2 ký tự", trigger: "blur" },
    { max: 50, message: "Họ tên phải ít hơn 50 ký tự", trigger: "blur" },
  ],
  email: [
    { required: true, message: "Vui lòng nhập email", trigger: "blur" },
    { type: "email", message: "Email không hợp lệ", trigger: "blur" },
  ],
  businessName: [
    { required: true, message: "Vui lòng nhập tên công ty", trigger: "blur" },
    { min: 2, message: "Tên công ty phải có ít nhất 2 ký tự", trigger: "blur" },
    { max: 50, message: "Tên công ty phải ít hơn 50 ký tự", trigger: "blur" },
  ],
  businessType: [
    {
      required: true,
      message: "Vui lòng chọn loại công ty",
      trigger: "change",
    },
  ],
  password: [
    { required: true, message: "Vui lòng nhập mật khẩu", trigger: "blur" },
    { min: 8, message: "Mật khẩu phải có ít nhất 8 ký tự", trigger: "blur" },
    { max: 50, message: "Mật khẩu phải ít hơn 50 ký tự", trigger: "blur" },
  ],
  rePassword: [
    { required: true, message: "Vui lòng nhập lại mật khẩu", trigger: "blur" },
    { min: 8, message: "Mật khẩu phải có ít nhất 8 ký tự", trigger: "blur" },
    { max: 50, message: "Mật khẩu phải ít hơn 50 ký tự", trigger: "" },
  ],
});

const { validate, validateInfos } = Form.useForm(modelRef, rulesRef);

const formFields = computed(() => [
  {
    label: "Họ và tên",
    name: "fullName",
    component: "a-input",

    props: { placeholder: "Email / Tên đăng nhập" },
  },
  {
    label: "Tên doanh nghiệp",
    name: "businessName",
    component: "a-input",

    props: { placeholder: "Hình thức doanh nghiệp" },
  },
  {
    label: "Password",
    name: "password",
    component: "a-input-password",

    props: { placeholder: "Xác nhận mật khẩu" },
  },
]);

const { data: businessTypes } = useGetBusinessType({
  refetchOnWindowFocus: false,
  placeholderData: keepPreviousData,
});

const businessTypesData = computed(() => {
  return (
    businessTypes?.value?.data?.map((item: BusinessType) => ({
      value: item.id,
      label: item.name,
    })) || []
  );
});

watch(
  () => businessTypesData.value,
  (newBusinessTypes) => {
    if (
      newBusinessTypes &&
      newBusinessTypes.length > 0 &&
      !modelRef.businessType
    ) {
      modelRef.businessType = newBusinessTypes[0].value;
    }
  },
  { immediate: true }
);

let buttonClicked = ref<Boolean>(false);

const { data: state } = useRegister(modelRef, {
  refetchOnWindowFocus: false,
  placeholderData: keepPreviousData,
  enabled: computed(() => buttonClicked.value),
});

watch(
  () => state.value,
  (result) => {
    if (result?.data) {
      window.location.href = `${URL_FRONTEND}?state=${result.data}`;
    } else {
      buttonClicked.value = false;
      toast.error(
        result?.response?.data?.message || ERROR_MESSAGE.SOMETHING_WENT_WRONG
      );
    }
  }
);

const handleSubmitRegister = async () => {
  try {
    await validate();
    buttonClicked.value = true;
  } catch (error: any) {
    console.error("🚀 ~ onFinish register ~ error:", error);
    if (error.errorFields) {
      return;
    }
    toast.error(
      error?.response?.data?.message || ERROR_MESSAGE.SOMETHING_WENT_WRONG
    );
  }
};

const handleRegisterGoogle = () => {
  console.log(
    "URL_OAUTH2_GOOGLE + URL_FRONTEND",
    URL_OAUTH2_GOOGLE + URL_FRONTEND
  );
  window.location.href = URL_OAUTH2_GOOGLE + URL_FRONTEND;
};
</script>
