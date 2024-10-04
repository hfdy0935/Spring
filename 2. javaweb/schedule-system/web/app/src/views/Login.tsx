import { ref, defineComponent, reactive } from 'vue';
import { useRouter } from 'vue-router';
import type { UserInfo } from '@/types/common';
import { reqLogin } from '@/apis/login';
import { Lock, User } from '@element-plus/icons-vue';
import { ElMessage, type FormRules } from 'element-plus';
import { useUserStore } from '@/stores';

export default defineComponent({
    name: 'Login',
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
                const result = await reqLogin({
                    username: userInfo.value.username,
                    password: userInfo.value.password
                });
                if (result.code === 200) {
                    ElMessage.success('登录成功');
                    userInfo.value = result.data!;
                    useUserStore().updateUserInfo(userInfo.value);
                    await router.push('/dashboard');
                } else {
                    ElMessage.error('登录失败，请检查用户名或密码');
                }
            }
            userInfo.value = {} as UserInfo;
        };
        return () => (
            <div class='w-full h-full flex flex-col justify-center items-center'>
                <p class='text-2xl my-6'>欢迎登录</p>

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
                    登录
                </el-button>
                <div>
                    没有账号？
                    <a
                        class='text-blue-500 font-bold cursor-pointer hover:underline hover:text-red-600'
                        onClick={() => {
                            router.push('/register');
                        }}
                    >
                        去注册
                    </a>
                </div>
            </div>
        );
    }
});
