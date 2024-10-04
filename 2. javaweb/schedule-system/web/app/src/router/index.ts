import { createRouter, createWebHistory } from 'vue-router';
import { useUserStore } from '@/stores';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'main',
            redirect: 'login'
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('@/views/Login')
        },
        {
            path: '/register',
            name: 'register',
            component: () => import('@/views/Register')
        },
        {
            path: '/change-password',
            name: 'changePassword',
            component: () => import('@/views/changePassword')
        },
        {
            path: '/dashboard',
            name: 'dashboard',
            component: () => import('@/views/Dashboard')
        }
    ]
});

router.beforeEach((to, from) => {
    if (
        ['dashboard', 'changePassword'].includes(to.name as string) &&
        !useUserStore().userInfo.username
    ) {
        return '/login';
    } else {
        return true;
    }
});

export default router;
