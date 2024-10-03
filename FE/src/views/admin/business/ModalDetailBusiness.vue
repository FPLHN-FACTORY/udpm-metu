<template>
  <a-modal ref="modalRef" :open="open" :wrap-style="{ overflow: 'hidden' }">
    <template #title>
      <div ref="modalTitleRef" style="width: 100%; cursor: move">
        Cập nhật doanh nghiệp
      </div>
    </template>
    <template>
      <div>
        <a-form>
          <a-form-item label="Mã cở sở con" class="mt-6">
            <a-input
              :value="data.codeBusiness"
              readonly
              placeholder=""
              allowClear
            />
          </a-form-item>
          <a-form-item label="Tên doanh nghiệp" class="mt-6">
            <a-input
              :value="data.namebusiness"
              readonly
              placeholder=""
              allowClear
            />
          </a-form-item>
        </a-form>
      </div>
    </template>
  </a-modal>
</template>

<script setup lang="ts">
defineProps({
  open: Boolean,
  title: String,
  data: (Object as () => any) || null,
});
</script>
