<template>

    <div class="flex gap-4 h-screen">
        <div class="w-1/3 bg-gray-100 h-full flex flex-col justify-between">
            <div>
                <div class="flex justify-between items-center p-2 border-gray-300 border-b">
                    <h2 class="flex items-between text-orange-500 text-3xl font-semibold">
                        <span class="m-2 text-xl">Danh sách menu</span>
                    </h2>
                    <router-link to="/manager/create-menu" class="butotn">
                        <a-button class="rounded-full flex items-center">
                            <PlusCircleFilled :style="{fontSize: '20px', color: '#f97316'}" />
                            Tạo menu                    
                        </a-button>
                    </router-link>
                </div>
                <div class="p-2 mt-2">
                    <a-input-search
                        size="large"
                        placeholder="Tìm kiếm menu"
                    />
                </div>
            </div>
            <div class="text-center py-2">
                <a-pagination v-model:current="current" simple :total="50" />
            </div>
        </div>

        <div class="w-2/3 bg-gray-100 flex flex-col">
            <div class="flex justify-between items-center p-2 border-gray-300 border-b">
                <h2 class="flex items-center text-orange-500 text-3xl font-semibold">
                    <span class="m-2 text-xl">Giao diện mẫu</span>
                </h2>
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
    import { ref } from 'vue';
    import { PlusCircleFilled } from '@ant-design/icons-vue';

    const current = ref(2);
    const showOnDevice = ref('computer')
</script>

<style scoped></style>