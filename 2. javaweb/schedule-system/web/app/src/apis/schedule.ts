import request from '@/utils/request';
import type {
    AddScheduleReq,
    AddScheduleResp,
    DeleteScheduleReq,
    DeleteScheduleResp,
    UpdateScheduleReq,
    UpdateScheduleResp,
    FindAllScheduleResp
} from '@/types/schedule';
import type { UserInfo } from '@/types/common';

/**
 * 获取所有schedule
 */
export const reqGetAllSchedule = async (uid: number) =>
    await request<number, FindAllScheduleResp>({
        method: 'get',
        url: `/schedule/getAll?uid=${uid}`
    });

/**
 * 更新一个schedule
 * @param data UpdateScheduleReq
 */
export const reqUpdateSchedule = async (data: UpdateScheduleReq) =>
    await request<UpdateScheduleReq, UpdateScheduleResp>({
        method: 'put',
        url: '/schedule/update',
        data
    });

/**
 * 添加一个schedule
 * @param data AddScheduleReq
 */
export const reqAddSchedule = async (data: AddScheduleReq) =>
    await request<AddScheduleReq, AddScheduleResp>({
        method: 'put',
        url: '/schedule/add',
        data
    });

/**
 * 删除一个schedule
 * @param sid number
 */
export const reqDeleteSchedule = async (sid: number) =>
    await request<DeleteScheduleReq, DeleteScheduleResp>({
        method: 'put',
        url: `/schedule/delete?sid=${sid}`
    });
