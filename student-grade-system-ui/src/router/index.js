import { createRouter, createWebHistory } from 'vue-router'
import MainLayout from '../layout/MainLayout.vue' // <--- 修改这里
import LoginView from '../views/LoginView.vue'

const routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/',
    component: MainLayout, // <--- 修改这里
    redirect: '/home',
    children: [
      {
        path: 'home',
        name: 'home',
        component: () => import('@/views/HomeView.vue'),
        meta: { title: '首页', requiresAuth: true }
      },
      {
        path: 'about',
        name: 'about',
        component: () => import('@/views/AboutView.vue'),
        meta: { title: '关于', requiresAuth: true }
      }
    ]
  }
]
// ... router.beforeEach 的部分保持不变 ...
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 全局前置守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token');

  if (to.matched.some(record => record.meta.requiresAuth)) {
    // 这个路由需要认证
    if (!token) {
      // 如果没有 token，则重定向到登录页面
      next({
        path: '/login',
        query: { redirect: to.fullPath } // 登录后可以跳回原页面
      });
    } else {
      next(); // 如果有 token，则正常进入
    }
  } else {
    next(); // 不需要认证的页面，直接进入
  }
});


export default router