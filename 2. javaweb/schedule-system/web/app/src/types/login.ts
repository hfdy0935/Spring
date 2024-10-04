import type { Resp,UserInfo } from './common';


// 登录时不传uid
export type LoginReq = Omit<UserInfo, 'uid'>;

export type LoginResp = Resp<UserInfo>;
