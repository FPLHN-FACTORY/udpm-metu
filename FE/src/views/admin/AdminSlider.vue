<template>

  <a-layout>
    <a-layout-sider theme="light" v-model:collapsed="collapsed" :trigger="null" collapsible>
      <div class="logo" style="width:100%; height: 70px;">QUẢN TRỊ VIÊN ADMIN</div>
      <a-menu v-model:selectedKeys="selectedKeys" theme="light" mode="inline">
        <a-menu-item v-for="item in menuItems" :key="item.key">
          <component :is="item.icon"/>
          <span><router-link :to="item.path" @click="handleMenuClick(item.key)">{{ item.label }}</router-link></span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>

    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <menu-unfold-outlined v-if="collapsed" class="trigger" @click="() => (collapsed = !collapsed)"/>
        <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)"/>
      </a-layout-header>
      <a-layout-content :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }">
        <router-view></router-view>
      </a-layout-content>
    </a-layout>
  </a-layout>

</template>


<script lang="ts" setup>
import {ref} from 'vue';
import {useRouter} from 'vue-router';
import {MenuFoldOutlined, MenuUnfoldOutlined, UserOutlined,} from '@ant-design/icons-vue';
import {ROUTES_CONSTANTS} from "@/constants/path.ts";

const collapsed = ref<boolean>(false);
const selectedKeys = ref<string[]>(['1']);
const route = useRouter();

const menuItems = ref([
  {key: '1', icon: UserOutlined, label: 'Thể loại doanh nghiệp', path: ROUTES_CONSTANTS.ADMIN.children.BUSINESS_TYPE},
  {key: '2', icon: UserOutlined, label: 'Danh sách doanh nghiệp', path: ROUTES_CONSTANTS.ADMIN.children.BUSINESS},

]);

// // Menu items
// const menuItems = ref([
//     {
//         key: '1',
//         label: 'Tổng quan',
//         icon: UserOutlined,
//         path: '/auth/dashboard',
//     },
//     {
//         key: 'sub1',
//         label: 'Thống kê',
//         icon: UserOutlined,
//         children: [
//             { key: '2', label: 'Thống kê tương tác', path: '/auth/statistical-interaction' },
//             { key: '3', label: 'Thống kê theo menu', path: '/auth/statistical-menu' },
//             { key: '4', label: 'Thống kê theo nút', path: '/auth/statistical-button' },
//         ],
//     },
//     {
//         key: '5',
//         label: 'Quản lý menu',
//         icon: UserOutlined,
//         path: '/auth/menu',
//     },
// ]);

const handleMenuClick = (key: string) => {
  selectedKeys.value = [key];
  const item = menuItems.value.find(item => item.key === key);
  if (item) {
    route.push(item.path);
  }
};

</script>


<style>
#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.3);
  margin: 16px;
}

.site-layout .site-layout-background {
  background: #fff;
}

.logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}
</style>


<script lang="ts">
export default {
  name: "AdminPage",
}
</script>