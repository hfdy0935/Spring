import request from '@/utils/request';
import type { RegisterReq, RegisterResp } from '@/types/register';

export const reqRegister = async (userInfo: RegisterReq) =>
    request<RegisterReq, RegisterResp>({
        method: 'post',
        url: '/user/register',
        data: userInfo
    });
