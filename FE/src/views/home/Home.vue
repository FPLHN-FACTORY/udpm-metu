<template>
  <a-layout class="layout">
    <a-layout-header>
      <div class="logo" />
      <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
      >
        <a-menu-item
          v-for="item in menuItems"
          :key="item.key"
        >
          <router-link :to="item.path" @click="handleMenuClick(item.key)">{{ item.label }}</router-link>
        </a-menu-item>
      </a-menu>
    </a-layout-header>
    
    <a-layout-content style="padding: 0 50px">
      <router-view></router-view>
    </a-layout-content>

    <a-layout-footer style="text-align: center">
      Metu ©2024 Created by FPT
    </a-layout-footer>
  </a-layout>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const selectedKeys = ref<string[]>(['1']);
const router = useRouter();

const menuItems = ref([
  { key: '1', label: 'Giới thiệu', path: '/' },
  { key: '2', label: 'Hướng dẫn sử dụng', path: '/instruction' },
  { key: '7', label: 'Đăng nhập', path: '/login' },
  { key: '8', label: 'Đăng ký', path: '/register' },
  
  
  
]);

const handleMenuClick = (key: string) => {
  selectedKeys.value = [key];
  const item = menuItems.value.find(item => item.key === key);
  if (item) {
    router.push(item.path);
  }
};
</script>

<style scoped>
.site-layout-content {
  min-height: 280px;
  padding: 24px;
  background: #fff;
}
#components-layout-demo-top .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}
.ant-row-rtl #components-layout-demo-top .logo {
  float: right;
  margin: 16px 0 16px 24px;
}
</style>



<script lang="ts">

    export default {
      name: 'Home',
    };

</script>