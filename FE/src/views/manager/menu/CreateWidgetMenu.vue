<template>
  <div>
    <div
      class="border-dotted border-2 border-indigo-600 rounded-md w-fit"
      @click="handleOpenCreateWidget"
    >
      <v-icon name="fa-plus" scale="3" />
    </div>
    <widget
      :open="openCreateWidget"
      :listWidgetGroupByCategory="widget"
      @handleOpenModal="handleOpenCreateWidget"
      @handleCloseModal="handleCloseCreateWidgetModal"
    />
  </div>
</template>

<script lang="ts" setup>
import Widget from "@/components/widget/Widget.vue";
import { keepPreviousData } from "@tanstack/vue-query";

import { ref, computed } from "vue";
import { useWidgetGroupByCategory } from "@/services/service/widget/create-widget.action.ts";
let openCreateWidget = ref<boolean>(false);
const { data: widgets } = useWidgetGroupByCategory({
  refetchOnWindowFocus: false,
  placeholderData: keepPreviousData,
  enabled: computed(() => openCreateWidget.value),
});
const widget = computed(() => {
  const data = widgets?.value?.data;
  return Array.isArray(data) ? [...data] : null;
});

const handleOpenCreateWidget = () => {
  openCreateWidget.value = true;
};

const handleCloseCreateWidgetModal = () => {
  openCreateWidget.value = false;
};
</script>
