<template>
  <div class="shadow-md p-3 rounded-md m-3">
    <h2 class="p-4 d-flex align-items-center text-black text-3xl font-semibold">
      <v-icon name="co-filter" scale="2" class="text-black" />
      <span class="ml-2 text-2xl text-black">Bộ lọc</span>
    </h2>
    <a-form layout="vertical" class="mb-4">
      <label class="form-label">Nhập vào dữ liệu:</label>
      <a-form-item class="text-center">
        <a-input class="w-100" :value="params.search" @input="onChange('search', $event)"
                 placeholder="Mã, tên của Widget ..." allowClear />
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { debounce } from "lodash";
import { defineEmits, ref, watch } from "vue";

type ParamsFilterWidget = {
  search: string | null;
};

const params = ref<ParamsFilterWidget>({
  search: null,
});

const emit = defineEmits(["filter"]);

const debouncedEmit = debounce(() => {
  emit("filter", params.value);
}, 500);

function onChange(key: keyof ParamsFilterWidget, event: Event) {
  if (
      event &&
      event.target &&
      (event.target as HTMLInputElement).value !== undefined
  ) {
    params.value[key] = (event.target as HTMLInputElement).value;
  } else if (event && typeof event === "string") {
    params.value[key] = event;
  }
}

watch(
    params,
    () => {
      debouncedEmit();
    },
    { deep: true }
);
</script>