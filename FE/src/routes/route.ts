import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router';
import {ROUTES_CONSTANTS} from "@/constants/path.ts";
import {ROLES} from "@/constants/role.ts";
import {useAuthStore} from "@/stores/auth.ts";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'home',
        component: import('@/views/home/Home.vue'),
        alias: "/home",
        redirect: '/instroduce',
        children: [
            {
                path: '/instroduce',
                name: 'instroduce',
                component: import('@/views/home/Introduce.vue'),
            },
            {
                path: '/instruction',
                name: 'instruction',
                component: import('@/views/home/Instruction.vue'),
            },
        ],
    },
    {
        path: ROUTES_CONSTANTS.REDIRECT.path,
        name: ROUTES_CONSTANTS.REDIRECT.name,
        component: () => import("@/routes/guard/Redirect.vue"),
    },
    {
        path: ROUTES_CONSTANTS.NOT_FOUND.path,
        name: ROUTES_CONSTANTS.NOT_FOUND.name,
        component: () => import("@/views/exception/404/NotFound.vue"),
    },
    {
        path: '/admin',
        name: 'admin',
        component: import("@/views/admin/AdminSider.vue"),
        meta: {requiresAuth: true},
        redirect: '/admin/business',
        children: [
            {
                path: 'business',
                name: 'business',
                component: import('@/views/admin/business/Business.vue'),
                meta: {
                    requiresRole: ROLES.ADMIN,
                    requiresAuth: true
                },
            },
            {
                path: 'business-type',
                name: 'business-type',
                component: import('@/views/admin/business/BusinessType.vue'),
                meta: {
                    requiresRole: ROLES.ADMIN,
                    requiresAuth: true
                },
            },
        ],
    },
    {
        path: '/manager',
        name: 'manager',
        component: import("@/views/admin/AdminSider.vue"),
        meta: {requiresAuth: true},
        redirect: '/manager/business',
        children: [
            {
                path: 'business',
                name: 'business',
                component: import('@/views/admin/business/Business.vue'),
                meta: {
                    requiresRole: ROLES.MANAGER,
                    requiresAuth: true
                },
            },
            {
                path: 'business-type',
                name: 'BusinessType',
                component: import('@/views/admin/business/BusinessType.vue'),
                meta: {
                    requiresRole: ROLES.MANAGER,
                    requiresAuth: true
                },
            },
        ],
    },
    {
        path: '/manager',
        name: 'manager',
        component: import("@/views/admin/AdminSider.vue"),
        meta: {requiresAuth: true},
        redirect: '/manager/business',
        children: [
            {
                path: 'business',
                name: 'business',
                component: import('@/views/admin/business/Business.vue'),
                meta: {
                    requiresRole: ROLES.MANAGER,
                    requiresAuth: true
                },
            },
            {
                path: 'business-type',
                name: 'BusinessType',
                component: import('@/views/admin/business/BusinessType.vue'),
                meta: {
                    requiresRole: ROLES.MANAGER,
                    requiresAuth: true
                },
            },
        ],
    },
    {
        path: '/auth',
        name: 'authentication',
        redirect: '/auth/login',
        children: [
            {
                path: '/auth/login',
                name: 'login',
                component: import('@/views/home/Login.vue'),
            },
            {
                path: '/auth/register',
                name: 'register',
                component: import('@/views/home/Register.vue'),
            },
        ],
    },
];

const route = createRouter({
    history: createWebHistory(),
    routes,
});

route.beforeEach((to, from, next) => {
    const authStore = useAuthStore();
    const requiresAuth = to.matched.some((record) => record.meta.requiresAuth);
    const requiresRole = to.matched.some((record) => record.meta.requiresRole);
    const userRole = authStore?.user?.rolesCodes;

    if (userRole === null && requiresAuth) {
        next({name: ROUTES_CONSTANTS.LOGIN.name});
    } else if (requiresAuth && !authStore.isAuthenticated) {
        next({name: ROUTES_CONSTANTS.LOGIN.name});
    } else {
        next();
    }

});

export default route;
