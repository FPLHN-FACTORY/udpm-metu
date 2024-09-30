<template>
  <div>
    <div class="border-dotted border-2 border-indigo-600 rounded-md w-fit " @click="handleOpenCreateWidget">
      <v-icon name="fa-plus" scale="3"/>
    </div>
    <widget :open="openCreateWidget"
            :list-widget-group-by-category="widgetGroupByCategory"
            @handleOpenModal="handleOpenCreateWidget"
            @handleCloseModal="handleCloseCreateWidgetModal"/>
  </div>
</template>

<script lang="ts" setup>
import Widget from '@/components/widget/Widget.vue'
import {keepPreviousData} from '@tanstack/vue-query';

import {ref, computed} from 'vue';
import {useWidgetGroupByCategory} from "@/services/service/widget/create-widget.action.ts";

let openCreateWidget = ref<Boolean>(false);
const {data: widgets, isLoading, isFetching, refetch} = useWidgetGroupByCategory({
  refetchOnWindowFocus: false,
  placeholderData: keepPreviousData,
  enabled: computed(() => openCreateWidget.value)
});
const widgetGroupByCategory = computed(()=>{
  return widgets?.value?.data? [ ...widgets?.value?.data ] : null;
});

const handleOpenCreateWidget = () => {
  openCreateWidget.value = true;
}

const handleCloseCreateWidgetModal = () => {
  openCreateWidget.value = false;
}
</script>
