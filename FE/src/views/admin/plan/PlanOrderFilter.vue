<template>
    <div class="shadow-md p-3 rounded-md m-3">
        <h2 class="p-4 flex items-center text-primary text-3xl font-semibold">
            <v-icon name="co-filter" scale="2" />
            <span class="ml-2 text-2xl">Bộ lọc</span>
        </h2>
        <a-form layout="vertical" class="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-2 p-5">
            <a-form-item label="Cơ sở" class="col-span-1 md:col-span-2 lg:col-span-1" @input="onChange('email', $event)"
                :value="params.email">
                <a-input placeholder="email" allowClear />
            </a-form-item>
            <a-form-item label="Trạng thái" class="col-span-1 md:col-span-2 lg:col-span-1">
                <a-select :value="params.status" placeholder="Trạng thái" allowClear
                    @change="onSelectChange('status', $event)">
                    <a-select-option v-for="option in orderStatusOptions" :key="option.value" :value="option.value">
                        {{ option.label }}
                    </a-select-option>
                </a-select>
            </a-form-item>
        </a-form>
    </div>
</template>

<script setup lang="ts">
import { debounce } from 'lodash';
import { ref, watch } from 'vue';

// Định nghĩa kiểu ParamsFilterOrders
type ParamsFilterOrders = {
    email: string | null,
    status: string | null
}

// Danh sách các tùy chọn trạng thái
const orderStatusOptions = [
    { label: "Chọn trạng thái", value: "" },
    { label: "Đang xử lý", value: 'processing' },
    { label: "Đã hoàn thành", value: 'completed' },
    { label: "Đã hủy", value: 'canceled' }
];

// Khai báo trạng thái của bộ lọc
const params = ref<ParamsFilterOrders>({
    email: '',
    status: ''
});

// Định nghĩa emit cho event 'filter'
const emit = defineEmits(['filter']);

// Hàm xử lý sự thay đổi giá trị
function onChange(key: keyof ParamsFilterOrders, event: Event) {
    if (
        event &&
        event.target &&
        (event.target as HTMLInputElement).value !== undefined
    ) {
        params.value[key] = (event.target as HTMLInputElement).value
    } else if (event && typeof event === 'string') {
        params.value[key] = event
    }
}

function onSelectChange(key: keyof ParamsFilterOrders, value: string) {
    params.value[key] = value;
    emit('filter', params.value)
}

const debouncedEmit = debounce(() => {
    emit('filter', params.value)
}, 2000)

watch(
    params,
    () => {
        debouncedEmit();
    },
    { deep: true }
);
</script>

