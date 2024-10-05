
<template>

    <a-layout>
        <a-layout-sider theme="light" v-model:collapsed="collapsed" :trigger="null" collapsible>
            <div class="logo" style="width:100%; height: 70px;">QUẢN TRỊ VIÊN</div>
            <a-menu v-model:selectedKeys="selectedKeys" theme="light" mode="inline">
                <a-menu-item v-for="item in menuItems" :key="item.key">
                    <component :is="item.icon" />
                    <span><router-link :to="item.path" @click="handleMenuClick(item.key)">{{ item.label }}</router-link></span>
                </a-menu-item>
            </a-menu>
        </a-layout-sider>

        <a-layout>
            <a-layout-header style="background: #fff; padding: 0">
                <menu-unfold-outlined v-if="collapsed" class="trigger" @click="() => (collapsed = !collapsed)" />
                <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)" />
            </a-layout-header>
            <a-layout-content :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }">
                <router-view></router-view>
            </a-layout-content>
        </a-layout>
    </a-layout>

</template>


<script lang="ts" setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import {
    UserOutlined,
    MenuUnfoldOutlined,
    MenuFoldOutlined,
} from '@ant-design/icons-vue';

const collapsed = ref<boolean>(false);
const selectedKeys = ref<string[]>(['1']);
const router = useRouter();

const menuItems = ref([
    { key: '1', icon: UserOutlined, label: 'Quản lý menu', path: '/manager/menu' },
    { key: '2', icon: UserOutlined, label: 'Chức năng khác', path: '/manager' },

]);

const handleMenuClick = (key: string) => {
  selectedKeys.value = [key];
  const item = menuItems.value.find(item => item.key === key);
  if (item) {
    router.push(item.path);
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
    name: "ManagerPage",
}
</script>