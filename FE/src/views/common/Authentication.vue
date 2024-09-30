<template>
    <a-layout>
        <a-layout-sider theme="light" :style="{width: '300px'}" v-model:collapsed="collapsed" :trigger="null" collapsible>
            <div class="logo" style="width:100%; height: 70px;">LOGO</div>
            <a-menu v-model:selectedKeys="selectedKeys" theme="light" mode="inline">

                <template v-for="item in menuItems">
                    <a-menu-item :key="item.key" v-if="!item.children" @click="handleMenuClick(item.key)">
                        <component :is="item.icon" />
                        <span>{{ item.label }}</span>
                    </a-menu-item>

                    <a-sub-menu v-else>
                        <template #title>
                            <span>
                                <component :is="item.icon" />
                                <span>{{ item.label }}</span>
                            </span>
                        </template>

                        <a-menu-item v-for="child in item.children" :key="child.key"
                            @click="handleMenuClick(child.key)">
                            {{ child.label }}
                        </a-menu-item>
                    </a-sub-menu>
                </template>

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

const selectedKeys = ref<string[]>(['1']);
const collapsed = ref<boolean>(false);
const route = useRouter();

// // Menu items
const menuItems = ref([
    {
        key: '1',
        label: 'Tổng quan',
        icon: UserOutlined,
        path: '/auth/dashboard',
    },
    {
        key: 'sub1',
        label: 'Thống kê',
        icon: UserOutlined,
        children: [
            { key: '2', label: 'Chi tiết tương tác', path: '/auth/statistical-interaction' },
            { key: '3', label: 'Theo menu', path: '/auth/statistical-menu' },
            { key: '4', label: 'Theo nút', path: '/auth/statistical-button' },
        ],
    },
    {
        key: '5',
        label: 'Quản lý menu',
        icon: UserOutlined,
        path: '/auth/menu',
    },
 
]);

const handleMenuClick = (key: string) => {
    selectedKeys.value = [key];
    const item = menuItems.value.find((menuItem) =>
        menuItem.key === key || menuItem.children?.some((child) => child.key === key)
    );
    if (item) {
        const target = item.children?.find((child) => child.key === key) || item;
        if (target?.path) {
            router.push(target.path);
        }
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
</style>