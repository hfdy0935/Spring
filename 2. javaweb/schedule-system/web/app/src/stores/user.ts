import { ref } from 'vue';
import { defineStore } from 'pinia';
import type { UserInfo } from '@/types/common';

function userStore() {
    const userInfo = ref<UserInfo>({} as UserInfo);
    const updateUserInfo = (data: UserInfo) => {
        userInfo.value = data;
    };
    return { userInfo, updateUserInfo };
}

export default defineStore('user', userStore, { persist: true });
