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
            class="h-12 rounded-2 text-xs"
            :is="field.component"
            v-bind="field.props"
            v-model:value="modelRef[field.name]"
        />
      </a-form-item>
    </a-form>

    <router-link
        :to="ROUTES_CONSTANTS.AUTHENTICATION.children.FORGOT_PASSWORD.path"
        class="text-gray-500 hover:text-gray-500 cursor-pointer text-xs"
    >
      Quên mật khẩu
    </router-link>

    <a-button
        @click="handleSubmitLogin()"
        html-type="submit"
        :loading="isLoginPass"
        class="h-12 w-full rounded-2xl bg-blue-700 text-white hover:bg-blue-900 hover:text-white cursor-pointer text-xs"
    >
      Đăng nhập
    </a-button>


    <span class="text-gray-500 cursor-default text-xs">Bạn chưa có tài khoản?</span>

    <div class="flex justify-center items-center flex-col">
      <button
          @click="route.push(ROUTES_CONSTANTS.AUTHENTICATION.children.REGISTER.name)"
          type="button"
          class="py-3.5 border-2 rounded-2xl w-full text-xs text-gray-900"
      >
        Đăng ký miễn phí
      </button>
    </div>

    <span class="text-gray-500 cursor-default text-xs">
      Or sign in with
    </span>

    <div class="flex justify-center items-center flex-col">
      <button
          @click="handleLoginGoogle"
          type="button"
          class="flex justify-center items-center py-3.5 border-2 rounded-2xl w-full text-xs text-gray-900"
          :disabled="isLoginGoogle"
      >
        <v-icon name="fc-google" class="mr-2"/>
        Google
      </button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {URL_FRONTEND, URL_OAUTH2_GOOGLE} from "@/constants/url";
import {ROUTES_CONSTANTS} from "@/constants/path.ts";
import {ERROR_MESSAGE} from "@/constants/message.constant.ts";
import route from "@/routes/route.ts";
import {computed, reactive, ref, watch} from "vue";
import {Form} from "ant-design-vue";
import {toast} from "vue3-toastify";
import {keepPreviousData} from "@tanstack/vue-query";
import {useLogin} from "@/services/service/auth/authentication.action.ts";
import {LoginForm} from "@/services/api/auth/authentication.api.ts";

const isLoginPass = ref(false);
const isLoginGoogle = ref(false);

const modelRef = reactive<LoginForm>({
  email: "",
  password: "",
})

const rulesRef = reactive({
  email: [
    {required: true, message: "Vui lòng nhập email", trigger: "blur"},
    {type: "email", message: "Email không hợp lệ", trigger: "blur"},
  ],
  password: [
    {required: true, message: "Vui lòng nhập mật khẩu", trigger: "blur"},
    {min: 8, message: "Mật khẩu phải có ít nhất 8 ký tự", trigger: "blur"},
    {max: 50, message: "Mật khẩu phải ít hơn 50 ký tự", trigger: "blur"},
  ],
})

const {validate, validateInfos} = Form.useForm(
    modelRef,
    rulesRef
);

const formFields = computed(() => [
  {
    label: "Email",
    name: "email",
    component: "a-input",
    props: {placeholder: "Email / Tên đăng nhập"},
  },
  {
    label: "Password",
    name: "password",
    component: "a-input-password",
    props: {placeholder: "Mật khẩu"},
  },
]);

let buttonClicked = ref<Boolean>(false);

const {data: state} = useLogin(modelRef, {
  refetchOnWindowFocus: false,
  placeholderData: keepPreviousData,
  enabled: computed(() => buttonClicked.value)
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

const handleSubmitLogin = async () => {
  try {
    await validate();
    buttonClicked.value = true;
  } catch (error: any) {
    console.error("🚀 ~ onFinish login ~ error:", error);
    if (error.errorFields) {
      return;
    }
    toast.error(
        error?.response?.data?.message || ERROR_MESSAGE.SOMETHING_WENT_WRONG
    );
  }
};

const handleLoginGoogle = () => {
  console.log(
      "URL_OAUTH2_GOOGLE + URL_FRONTEND",
      URL_OAUTH2_GOOGLE + URL_FRONTEND
  );
  window.location.href = URL_OAUTH2_GOOGLE + URL_FRONTEND;
};

</script>
