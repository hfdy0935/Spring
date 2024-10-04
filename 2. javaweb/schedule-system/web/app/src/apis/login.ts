import request from '@/utils/request';
import type { LoginReq, LoginResp } from '@/types/login';

export const reqLogin = async (data: LoginReq) =>
    request<LoginReq, LoginResp>({
        method: 'post',
        url: '/user/login',
        data
    });
