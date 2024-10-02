<template>
  <a-form
      layout="horizontal"
      :model="formRegister"
      name="basic"
      :wrapper-col="{ span: 30 }"
      autocomplete="off"
      @finish="onFinish"
  >
    <a-form-item
        name="fullName"
        :validate-status="errors.fullName ? 'error' : ''"
        :help="errors.fullName"
    >
      <a-input class="custom-input" v-model:value="formRegister.fullName" placeholder="Họ và tên"/>
    </a-form-item>
    <a-form-item
        name="email"
        :validate-status="errors.email ? 'error' : ''"
        :help="errors.email"
    >
      <a-input class="custom-input" v-model:value="formRegister.email" placeholder="Email"/>
    </a-form-item>
    <a-form-item
        name="businessName"
        :validate-status="errors.businessName ? 'error' : ''"
        :help="errors.businessName"
    >
      <a-input class="custom-input" v-model:value="formRegister.businessName"
               placeholder="Tên công ty/doanh nghiệp"/>
    </a-form-item>
    <a-form-item
        name="businessTypeId"
        :validate-status="errors.businessTypeId ? 'error' : ''"
        :help="errors.businessTypeId"
    >
      <a-select v-model:value="formRegister.businessTypeId" placeholder="Lĩnh vực">
        <a-select-option
            v-for="option in businessTypeList"
            :key="option.id"
            :value="option.id"
        >
          {{ option.name }}
        </a-select-option>
      </a-select>
    </a-form-item>
    <a-form-item name="password"
                 :validate-status="errors.password ? 'error' : ''"
                 :help="errors.password">
      <a-input-password class="custom-input" v-model:value="formRegister.password" placeholder="Mật khẩu"/>
    </a-form-item>
    <a-form-item name="rePassword"
                 :validate-status="errors.rePassword ? 'error' : ''"
                 :help="errors.rePassword">
      <a-input-password class="custom-input" v-model:value="formRegister.rePassword"
                        placeholder="Xác nhận mật khẩu"/>
    </a-form-item>

    <a-form-item class="text-center">
      <a-button html-type="submit" class="custom-button">Đăng ký</a-button>
    </a-form-item>
    <a-form-item class="text-center">
      <p>
        Bạn đã có tài khoản?
        <router-link to="/login" class="text-blue-500 hover:underline">Đăng nhập tại đây</router-link>
      </p>
    </a-form-item>
  </a-form>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import {useRouter} from 'vue-router'
// import {UserService} from '@/services/service/auth/register.service.ts'
import {toast} from "vue3-toastify"

const router = useRouter()

const businessTypeList = ref<{ id: string, name: string }[]>([])

const fetchBusinessTypeList = async () => {
  try {
    // const response = await UserService.getAllBusinessType()
    // businessTypeList.value = response.data
  } catch (error) {
    console.error('Lỗi khi lấy dữ liệu lĩnh vực:', error)
  }
};

interface formRegister {
  fullName: string
  email: string
  businessName: string
  businessTypeId: null
  password: string
  rePassword: string
}

interface errors {
  fullName: string
  email: string
  businessName: string
  businessTypeId: string | null
  password: string
  rePassword: string
}

const formRegister = reactive<formRegister>({
  fullName: '',
  email: '',
  businessName: '',
  businessTypeId: null,
  password: '',
  rePassword: ''
});

const errors = reactive<errors>({
  fullName: '',
  email: '',
  businessName: '',
  businessTypeId: '' as string | null,
  password: '',
  rePassword: ''
});

const onFinish = async () => {
  if (validateForm()) {
    try {
      const newUser = {
        fullName: formRegister.fullName,
        email: formRegister.email,
        businessName: formRegister.businessName,
        businessTypeId: formRegister.businessTypeId,
        password: formRegister.password
      }
      // await UserService.postNewUser(newUser)
      toast.success('Đăng ký thành công!\nChào mừng bạn đến với Metu')
      setTimeout(() => {
        router.push('/instroduce')
      }, 3000)
    } catch (error) {
      toast.error('Lỗi khi đăng ký thành viên mới!' + error)
    }
  }
};

const validateForm = () => {
  let check = true

  if (!formRegister.fullName) {
    errors.fullName = 'Họ và tên không được để trống!'
    check = false
  } else {
    errors.fullName = ''
  }

  const validDomains = ['@gmail.com', '@fpt.edu.vn']
  if (!formRegister.email) {
    errors.email = 'Email không được để trống!'
    check = false
  } else if (!validDomains.some(domain => formRegister.email.endsWith(domain))) {
    errors.email = 'Sai định dạng Email!'
    check = false
  } else {
    errors.email = ''
  }

  if (!formRegister.businessName) {
    errors.businessName = 'Tên công ty/doanh nghiệp không được để trống!'
    check = false
  } else {
    errors.businessName = ''
  }

  if (!formRegister.businessTypeId) {
    errors.businessTypeId = 'Lĩnh vực không được để trống!'
    check = false
  } else {
    errors.businessTypeId = ''
  }

  if (!formRegister.password) {
    errors.password = 'Mật khẩu không được để trống!'
    check = false
  } else {
    errors.password = ''
  }

  if (!formRegister.rePassword) {
    errors.rePassword = 'Xác nhận mật khẩu không được để trống!'
    check = false
  } else if (formRegister.password !== formRegister.rePassword) {
    errors.rePassword = 'Mật khẩu và xác nhận mật khẩu không khớp!'
    check = false
  } else {
    errors.rePassword = ''
  }

  return check
};

onMounted(() => {
  fetchBusinessTypeList()
})
</script>

<style scoped>
.image-col {
  background-image: url('https://admin.metu.vn/assets_metu/media/images/bg-10.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.custom-input {
  width: 400px;
  height: 45px;
  font-size: 18px;
}

.custom-button {
  width: 400px;
  height: 45px;
  font-size: 18px;
  padding: 0;
  margin-top: 30px;
  background-color: #6B59AF;
  color: #FFF;
}

.custom-button:hover {
  opacity: 0.95;
  color: #FFF;
  border: none;
}
</style>