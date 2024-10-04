import { defineComponent, ref, reactive } from 'vue';
import { useUserStore } from '@/stores';
import { storeToRefs } from 'pinia';
import type { ChangePassword, UserInfo } from '@/types/common';
import { useRouter } from 'vue-router';
import type { FormRules } from 'element-plus';
import { ElMessage } from 'element-plus';
import { Lock, User } from '@element-plus/icons-vue';
import { reqChagePassword } from '@/apis/changePassword';

export default defineComponent({
    name: 'ChangePassword',
    setup() {
        const router = useRouter();
        const userStore = useUserStore();
        const { userInfo } = storeToRefs(userStore);
        const changePassword = ref<ChangePassword>({
            uid: userInfo.value.uid,
            username: userInfo.value.username,
            oldPassword: '',
            newPassword: ''
        });
        const rules = reactive<FormRules<ChangePassword>>({
            username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
            oldPassword: [{ required: true, message: '密码不能为空', trigger: 'blur' }],
            newPassword: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
        });
        // 提交
        const submit = async () => {
            if (!changePassword.value.username) {
                ElMessage.warning('用户名不能为空');
                return;
            }
            if (!changePassword.value.oldPassword) {
                ElMessage.warning('旧密码不能为空');
                return;
            }
            if (!changePassword.value.newPassword) {
                ElMessage.warning('新密码不能为空');
                return;
            }
            if (changePassword.value.oldPassword === changePassword.value.newPassword) {
                ElMessage.warning('两次密码一样');
                return;
            }

            try {
                const res = await reqChagePassword(changePassword.value);
                console.log(res.code);
                ElMessage.success('修改成功，登录过期，请重新登录');
                userStore.updateUserInfo({} as UserInfo);
                router.replace('/login');
            } catch (e) {
                ElMessage.error('修改失败');
            }
        };
        return () => (
            <div class='w-full h-full flex flex-col justify-center items-center'>
                <p class='text-2xl my-6'>修改密码</p>
                <el-form model={changePassword.value} rules={rules} label-width='auto'>
                    <el-form-item label='用户名：' style={{ width: '400px' }} prop='username'>
                        <el-input
                            placeholder='用户名'
                            clearable
                            prefix-icon={User}
                            disabled
                            v-model={changePassword.value.username}
                        ></el-input>
                    </el-form-item>
                    <el-form-item label='旧密码：' prop='oldPassword'>
                        <el-input
                            placeholder='旧密码'
                            type='password'
                            clearable
                            show-password
                            prefix-icon={Lock}
                            v-model={changePassword.value.oldPassword}
                        ></el-input>
                    </el-form-item>
                    <el-form-item label='新密码：' prop='newPassword'>
                        <el-input
                            placeholder='新密码'
                            type='password'
                            clearable
                            show-password
                            prefix-icon={Lock}
                            v-model={changePassword.value.newPassword}
                        ></el-input>
                    </el-form-item>
                </el-form>
                <div class='w-full flex justify-around mt-10'>
                    <el-button
                        type='warning'
                        onClick={() => {
                            changePassword.value = {
                                uid: userInfo.value.uid,
                                username: userInfo.value.username,
                                oldPassword: '',
                                newPassword: ''
                            };
                        }}
                    >
                        重置
                    </el-button>
                    <el-button type='primary' onClick={submit}>
                        提交
                    </el-button>
                </div>
            </div>
        );
    }
});
