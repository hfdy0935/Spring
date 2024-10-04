import request from '@/utils/request';
import type { ChangePasswordReq, ChangePasswordResp } from '@/types/changePassword';

export const reqChagePassword = async (data: ChangePasswordReq) =>
    await request<ChangePasswordReq, ChangePasswordResp>({
        method: 'put',
        url: '/user/changePassword',
        data
    });
