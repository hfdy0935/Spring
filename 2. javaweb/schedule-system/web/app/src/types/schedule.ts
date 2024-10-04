import type { Resp, UserInfo, ScheduleList, ScheduleItem } from './common';

// 增
export type AddScheduleReq = Omit<ScheduleItem, 'sid'>;
export type AddScheduleResp = Resp<null>;

// 删
export type DeleteScheduleReq = ScheduleItem;
export type DeleteScheduleResp = Resp<null>;

// 改
export type UpdateScheduleReq = ScheduleItem;
export type UpdateScheduleResp = Resp<null>;

// 查
export type FindAllScheduleResp = Resp<ScheduleList>;
