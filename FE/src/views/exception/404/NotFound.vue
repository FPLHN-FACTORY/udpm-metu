<template>
  <a-result
      status="404"
      title="404"
      sub-title="Xin lỗi, trang bạn truy cập không tồn tại."
  >
    <template #extra>
      <a-button type="primary" @click="route.push('/')">Quay lại</a-button>
    </template>
  </a-result>
</template>

<script setup lang="ts">
import route from "@/routes/route.js";
</script>