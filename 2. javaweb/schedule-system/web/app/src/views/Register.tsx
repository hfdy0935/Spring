import { defineComponent, reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import type { UserInfo } from '@/types/common';
import type { FormRules } from 'element-plus';
import { ElMessage } from 'element-plus';
import { reqRegister } from '@/apis/register';
import { Lock, User } from '@element-plus/icons-vue';
import { useUserStore } from '@/stores';

export default defineComponent({
    name: 'Register',
    setup() {
        const router = useRouter();
        const userInfo = ref<UserInfo>({
            uid: -1,
            username: '',
            password: ''
        });
        // 表单校验
        const rules = reactive<FormRules<UserInfo>>({
            username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
            password: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
        });
        // 登录
        const doLogin = async () => {
            if (!userInfo.value.username) {
                ElMessage.warning('用户名不能为空');
            } else if (!userInfo.value.password) {
                ElMessage.warning('密码不能为空');
            } else {
                const result = await reqRegister({
                    username: userInfo.value.username,
                    password: userInfo.value.password
                });
                if (result.code === 200) {
                    ElMessage.success('注册成功');
                    userInfo.value = result.data!;
                    useUserStore().updateUserInfo(userInfo.value);
                    await router.push('/dashboard');
                } else {
                    ElMessage.error('注册失败，用户名重复或服务端发生错误');
                }
                userInfo.value = {} as UserInfo;
            }
        };
        return () => (
            <div class='w-full h-full flex flex-col justify-center items-center'>
                <p class='text-2xl my-6'>欢迎注册</p>
                <el-form
                    rules={rules}
                    label-width='auto'
                    model={userInfo.value}
                    label-position='left'
                >
                    <el-form-item label='用户名' prop='username' style={{ width: '400px' }}>
                        <el-input
                            v-model={userInfo.value.username}
                            placeholder='用户名'
                            prefix-icon={User}
                            clearable
                        ></el-input>
                    </el-form-item>
                    <el-form-item label='密码' prop='password' style={{ width: '400px' }}>
                        <el-input
                            v-model={userInfo.value.password}
                            placeholder='密码'
                            type='password'
                            prefix-icon={Lock}
                            clearable
                            show-password
                        ></el-input>
                    </el-form-item>
                </el-form>
                <el-button type='primary' class='my-6' onClick={doLogin}>
                    注册
                </el-button>
                <div>
                    已有账号？
                    <a
                        class='text-blue-500 font-bold cursor-pointer hover:underline hover:text-red-600'
                        onClick={() => {
                            router.push('/login');
                        }}
                    >
                        去登录
                    </a>
                </div>
            </div>
        );
    }
});
