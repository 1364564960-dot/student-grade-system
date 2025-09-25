import request from '@/utils/request';

export function login(data) {
    return request({
        url: '/auth/login', // 注意，baseURL 已在 request.js 中配置为 /api
        method: 'post',
        data
    });
}