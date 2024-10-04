import type { Resp, UserInfo } from './common';

export type RegisterReq = Omit<UserInfo, 'uid'>;
export type RegisterResp = Resp<UserInfo>;
