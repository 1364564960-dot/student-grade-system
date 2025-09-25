import axios from 'axios';
import { ElMessage } from 'element-plus';

// 1. 创建 Axios 实例
const service = axios.create({
    // 在 .env.development 文件中配置 VUE_APP_BASE_API = 'http://localhost:8080'
    baseURL: process.env.VUE_APP_BASE_API || '/api',
    timeout: 10000, // 请求超时时间
});

// 2. 请求拦截器
service.interceptors.request.use(
    config => {
        // 在发送请求前做些什么
        // 例如，从 localStorage 中获取 token 并添加到请求头
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        // 对请求错误做些什么
        console.error(error); // for debug
        return Promise.reject(error);
    }
);

// 3. 响应拦截器
service.interceptors.response.use(
    response => {
        // 对响应数据做点什么
        const res = response.data;
        // 如果是成功的响应，直接返回数据
        return res;
    },
    error => {
        // 对响应错误做点什么
        console.error('err' + error); // for debug
        ElMessage({
            message: error.message || '请求失败',
            type: 'error',
            duration: 5 * 1000
        });
        return Promise.reject(error);
    }
);

export default service;