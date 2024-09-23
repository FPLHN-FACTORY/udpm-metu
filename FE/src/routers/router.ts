import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';

import Home from '@/views/home/Home.vue';
import Login from '@/views/home/Login.vue';
import Register from '@/views/home/Register.vue';

// Routes được Lazy Loaded
const Instroduce = () => import('@/views/home/Introduce.vue');
const Instruction = () => import('@/views/home/Instruction.vue');

// 1. routes của admin
const AdminPage = () => import("@/views/admin/AdminSider.vue")
const Business = () => import('@/views/admin/business/Business.vue');
const BusinessType = () => import('@/views/admin/business/BusinessType.vue');

// 2. routes của người dùng metu
const Authentication = () => import("@/views/common/Authentication.vue");
const Dashboard = () => import('@/views/employee/Dashboard.vue');
const InteractionStatistical = () => import('@/views/employee/InteractionStatistical.vue');
const MenuStatistical = () => import('@/views/employee/MenuStatistical.vue');
const ButtonStatistical = () => import('@/views/employee/ButtonStatistical.vue');

const Menu = () => import('@/views/manager/Menu.vue');


// Cấu hình route
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    alias: "/home",
    redirect: '/instroduce',
    children: [
      {
        path: '/instroduce',
        name: 'Instroduce',
        component: Instroduce,
      },
      {
        path: '/instruction',
        name: 'Instruction',
        component: Instruction,
      },
    ],
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
  {
    path: '/admin',
    name: 'AdminPage',
    component: AdminPage,
    meta: { requiresAuth: true },
    redirect: '/admin/business',
    children: [
      {
        path: 'business',
        name: 'Business',
        component: Business,
        meta: { requiresAuth: true },
      },
      {
        path: 'business-type',
        name: 'BusinessType',
        component: BusinessType,
        meta: { requiresAuth: true },
      },
    ],
  },

  {
    path: '/auth',
    name: 'Authentication',
    component: Authentication,
    meta: { requiresAuth: true },
    redirect: '/auth/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: Dashboard,
        meta: { requiresAuth: true },
      },
      {
        path: 'menu',
        name: 'Menu',
        component: Menu,
        meta: { requiresAuth: true },
      },
      {
        path: 'statistical-interaction',
        name: 'InteractionStatistical',
        component: InteractionStatistical,
        meta: { requiresAuth: true },
      },
      {
        path: 'statistical-menu',
        name: 'MenuStatistical',
        component: MenuStatistical,
        meta: { requiresAuth: true },
      },
      {
        path: 'statistical-button',
        name: 'ButtonStatistical',
        component: ButtonStatistical,
        meta: { requiresAuth: true },
      },
      
    ],
  },
 
];

// Tạo router
const router = createRouter({
  history: createWebHistory(),
  routes,
});


// Navigation Guard - Yêu cầu đăng nhập
router.beforeEach((to, from, next) => {
  
  const isAuthenticated = localStorage.getItem('auth');
 
  if (to.meta.requiresAuth && !isAuthenticated) {
    next({ name: 'Login' });
  } else {
    next();
  }
});

export default router;
