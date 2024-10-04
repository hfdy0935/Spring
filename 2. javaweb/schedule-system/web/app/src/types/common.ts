export interface Resp<T> {
    code: number;
    message: string;
    data?: T;
}

export interface UserInfo {
    uid: number;
    username: string;
    password: string;
}

export interface ScheduleItem {
    sid?: number;
    uid: number;
    title: string;
    completed: 0 | 1;
}

export type ScheduleList = ScheduleItem[];

export interface ChangePassword extends Omit<UserInfo, 'password'> {
    oldPassword: string;
    newPassword: string;
}
