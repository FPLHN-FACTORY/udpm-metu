<template>
    <a-config-provider
        :theme="{
            token: {
                colorPrimary: '#f97316',
            },
        }"
    >
    </a-config-provider>
  <div class="flex gap-4 h-full">
        <div class="w-1/3 bg-gray-100 overflow-y-auto">
            <div class="flex justify-between items-center p-2 border-gray-300 border-b">
                <router-link to="/manager/menu" class="butotn">
                    <a-button class="rounded-full flex items-center">
                        <ArrowLeftOutlined :style="{fontSize: '20px', color: '#f97316'}" />
                        Quay lại           
                    </a-button>
                </router-link>
                <router-link to="/manager/create-menu" class="butotn">
                    <a-button class="rounded-full flex items-center">
                        <SaveFilled :style="{fontSize: '20px', color: '#f97316'}" />
                        Lưu menu            
                    </a-button>
                </router-link>
            </div>
            <div class="p-2 mt-2">
                <a-form
                    :model="formMenu"
                    name="basic"
                    layout="vertical"
                    @finish="onFinish"
                >
                    <a-form-item
                        label="Tên menu"
                        class="font-bold"
                        :rules="[{ required: true, message: 'Vui lòng nhập tên menu!' }]"
                    >
                        <a-input v-model:value="formMenu.menuName" placeholder="Nhập tên menu"/>
                    </a-form-item>
                    <a-form-item
                        label="Danh sách các nút"
                        class="font-bold"
                    >
                        <a-space wrap>
                            <a-button class="flex flex-col items-center justify-evenly custom-button-menu" >
                                <PhoneFilled :style="{fontSize: '30px', color: '#f97316'}" />
                                <span style="margin: 0">Gọi ngay</span>
                            </a-button>
                            <a-button class="flex flex-col items-center justify-evenly custom-button-menu" >
                                <PhoneFilled :style="{fontSize: '30px', color: '#f97316'}" />
                                <span style="margin: 0">Zalo</span>
                            </a-button>
                            <a-button class="flex flex-col items-center justify-evenly custom-button-menu" >
                                <FacebookFilled :style="{fontSize: '30px', color: '#f97316'}" />
                                <span style="margin: 0">Facebook</span>
                            </a-button>
                            <a-button class="flex flex-col items-center justify-evenly custom-button-menu" >
                                <ShoppingFilled :style="{fontSize: '30px', color: '#f97316'}" />
                                <span style="margin: 0">Đặt mua</span>
                            </a-button>
                            <a-button class="flex flex-col items-center justify-evenly custom-button-menu" >
                                <PlusCircleFilled :style="{fontSize: '30px', color: '#f97316'}" />
                                <span style="margin: 0">Thêm nút</span>
                            </a-button>
                        </a-space>
                    </a-form-item>
                    <a-form-item
                        label="Màu sắc"
                        class="font-bold"
                    >
                        <a-input type="color" v-model:value="color"></a-input>
                    </a-form-item>
                    <a-form-item
                        label="Chọn thiết bị muốn hiển thị"
                        class="font-bold"
                    >
                        <a-radio-group name="deviceType" v-model:value="deviceType" >
                            <a-radio value="all" class="font-thin">Mọi thiết bị</a-radio>
                            <a-radio value="computer" class="font-thin">Máy tính</a-radio>
                            <a-radio value="phone" class="font-thin">Điện thoại</a-radio>
                        </a-radio-group>
                    </a-form-item>
                    <a-form-item
                        label="Chọn giao diện muốn hiển thị"
                        class="font-bold">
                        <a-tabs v-model:activeKey="showInterface">
                            <a-tab-pane key="shape" tab="Hình dạng">
                                <a-radio-group name="typeShape" v-model:value="typeShape" >
                                    <a-radio value="circle" class="font-thin">Tròn</a-radio>
                                    <a-radio value="square" class="font-thin">Vuông</a-radio>
                                    <a-radio value="rectangle" class="font-thin">Chữ nhật</a-radio>
                                </a-radio-group>
                            </a-tab-pane>
                            <a-tab-pane key="position" tab="Vị trí" force-render>
                                <a-radio-group name="typePosition" v-model:value="typePosition">
                                    <a-radio value="top" class="font-thin">Trên</a-radio>    
                                    <a-radio value="bottom" class="font-thin">Dưới</a-radio>    
                                    <a-radio value="left" class="font-thin">Trái</a-radio>    
                                    <a-radio value="right" class="font-thin">Phải</a-radio>    
                                </a-radio-group>
                            </a-tab-pane>
                        </a-tabs>
                    </a-form-item>
                    <a-form-item
                        label="Cấu hình đường dẫn hiển thị"
                        class="font-bold">
                        <a-select v-model:value="configLink" class="mb-3">
                            <a-select-option value="allPage">Hiển thị ở tất cả các trang</a-select-option>
                            <a-select-option value="onePage">Hiển thị ở đường dẫn chính xác</a-select-option>
                            <a-select-option value="containText">Hiển thị theo đường dẫn có chứa ký tự</a-select-option>
                        </a-select>
                        <a-input v-if="configLink === 'onePage'" placeholder="Vui lòng nhập đường dẫn chính xác..." class="font-thin"></a-input>
                        <a-input v-else-if="configLink === 'containText'" placeholder="Vui lòng nhập từ khóa tồn tại trong đường dẫn..." class="font-thin"></a-input>
                    </a-form-item>
                    <a-form-item>
                        <a-collapse class="font-bold">
                            <a-collapse-panel header="Cài đặt nâng cao">
                                <a-form-item
                                    label="Thời gian hiển thị">
                                    <a-time-range-picker format="HH" :placeholder="['Từ giờ', 'Đến giờ']" class="font-thin" />
                                </a-form-item>
                                <a-form-item
                                    label="Thời gian ẩn hiện menu">
                                        <div class="flex items-center mb-2">
                                            <span style="width: 70px">Hiện sau:</span>
                                            <a-time-picker format="ss" placeholder="Giây" class="font-thin"/>
                                        </div>
                                        <div class="flex items-center">
                                            <span style="width: 70px">Ẩn sau:</span>
                                            <a-time-picker format="ss" placeholder="Giây" class="font-thin"/>
                                        </div>
                                </a-form-item>
                            </a-collapse-panel>
                        </a-collapse>
                    </a-form-item>
                </a-form>
            </div>
        </div>

        <div class="w-2/3 bg-gray-100 flex flex-col">
            <div class="pt-2 ps-2">
                <h1 class="font-bold">Danh sách nút đã tạo</h1>
                <div class="flex justify-between items-center p-2 border-gray-300 border-b">
                    <a-button class="flex flex-col items-center justify-evenly w-14 h-14" >
                        <PlusCircleFilled :style="{fontSize: '30px', color: '#f97316'}" />
                    </a-button>
                </div>
            </div>
            <div class="p-2 mt-2 flex flex-col justify-evenly grow">
                <a-radio-group size="large" v-model:value="showOnDevice">
                    <a-radio-button value="computer">Máy tính</a-radio-button>
                    <a-radio-button value="phone">Điện thoại</a-radio-button>
                </a-radio-group>

                <div v-if="showOnDevice === 'computer'" class="flex items-center justify-center h-full">
                    <div class="w-full h-full flex justify-center items-center bg-gray-100">
                        <div class="bg-gray-900 rounded-lg p-4 shadow-lg w-4/6 h-5/6 max-h-96 flex flex-col">
                            <div class="flex justify-between items-center bg-gray-700 p-2 rounded-t-lg">
                                <div class="flex space-x-2">
                                    <div class="w-3 h-3 bg-red-500 rounded-full"></div>
                                    <div class="w-3 h-3 bg-yellow-500 rounded-full"></div>
                                    <div class="w-3 h-3 bg-green-500 rounded-full"></div>
                                </div>
                            </div>
                            <div class="bg-white p-2 rounded-b-lg grow">
                                <iframe src="/manager/computer-screen" scrolling="no" class="w-full h-full"></iframe>
                            </div>
                        </div>
                    </div>
                </div>

                <div v-else-if="showOnDevice === 'phone'" class="flex items-center justify-center h-full">
                    <div class="w-full h-full max-h-96 flex justify-center items-center bg-gray-100">
                        <div class="bg-gray-900 rounded-[30px] p-3 shadow-lg w-64 h-full flex flex-col">
                            <div class="flex justify-center items-center bg-gray-700 p-2 rounded-t-[30px]">
                                <div class="w-2/3 h-1 bg-gray-400 rounded-full"></div>
                            </div>
                            <div class="bg-white p-2 grow">
                                <iframe src="/manager/phone-screen" scrolling="no" class="w-full h-full"></iframe>
                            </div>
                            <div class="flex justify-center items-center bg-gray-700 p-1 rounded-b-[30px]">
                                <div class="w-6 h-6 bg-gray-500 rounded-full"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { 
    SaveFilled, 
    ArrowLeftOutlined,
    PhoneFilled, 
    FacebookFilled, 
    ShoppingFilled, 
    PlusCircleFilled
} from '@ant-design/icons-vue';

const color = ref('#f97316')
const deviceType = ref('all')
const showOnDevice = ref('computer')
const showInterface = ref('shape')
const typeShape = ref('circle')
const typePosition = ref('top')
const configLink = ref('allPage')

interface FormMenu {
  menuName: string;
}

const formMenu = reactive<FormMenu>({
  menuName: '',
});

const onFinish = (values: any) => {
  console.log('Success:', values);
};

</script>

<style scoped>
.custom-input {
    height: 45px;
    font-size: 18px;
}
.custom-button-menu {
    height: 70px;
    width: 70px;
    padding: 0;
}
</style>