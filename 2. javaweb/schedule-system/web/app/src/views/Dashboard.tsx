import { defineComponent, ref, onMounted, nextTick } from 'vue';
import { useRouter } from 'vue-router';
import type { ScheduleList } from '@/types/common';
import {
    reqGetAllSchedule,
    reqAddSchedule,
    reqDeleteSchedule,
    reqUpdateSchedule
} from '@/apis/schedule';
import { ElInput, ElMessage } from 'element-plus';
import { useUserStore } from '@/stores';
import { Edit, Delete, Plus, Check } from '@element-plus/icons-vue';
import type { AddScheduleReq } from '@/types/schedule';

export default defineComponent({
    name: 'Dashboard',
    setup() {
        const router = useRouter();
        const userStore = useUserStore();
        const scheduleList = ref<ScheduleList>([]);
        // 获取所有日程
        const getAll = async () => {
            const res = await reqGetAllSchedule(userStore.userInfo.uid);
            if (res.code === 200) {
                scheduleList.value = res.data || [];
            } else {
                ElMessage.error('获取日程失败');
            }
        };
        onMounted(getAll);
        // 新增一行
        const addOne = () => {
            // 如果有没写完的
            if (scheduleList.value.some((item) => !item.title)) {
                ElMessage.warning('请先填完已有日程');
                return;
            }
            // 发送请求
            const item = {
                uid: userStore.userInfo.uid,
                title: '',
                completed: 0
            };
            reqAddSchedule(item as AddScheduleReq)
                .then(() => {
                    scheduleList.value.push(item as AddScheduleReq);
                    isEditList.value.push(false);
                    getAll();
                })
                .catch(() => {
                    ElMessage.error('添加失败');
                });
        };
        // 每行的状态
        const isEditList = ref<boolean[]>(new Array(scheduleList.value.length).fill(false));
        // 修改一行的completed
        const changeOneCompleted = (i: number) => {
            const item = scheduleList.value[i];
            reqUpdateSchedule(item).catch(() => {
                // 出错了就取反，回到原来的状态
                scheduleList.value[i].completed = item.completed == 0 ? 1 : 0;
            });
        };
        // 点击一行的编辑按钮
        const clickOneEdit = async (i: number) => {
            isEditList.value[i] = true;
            await nextTick();
        };
        // 编辑一行的title
        const changeOneTitle = (i: number) => {
            const item = scheduleList.value[i];
            reqUpdateSchedule(item)
                .then(() => {
                    isEditList.value[i] = false;
                })
                .catch(() => {
                    // 出错，返回原来状态
                    scheduleList.value[i].title = item.title;
                });
        };
        // 删除一行
        const deleteOne = (index: number) => {
            const sid = scheduleList.value[index].sid;
            reqDeleteSchedule(sid as number).then(() => {
                scheduleList.value.splice(index, 1);
                isEditList.value.splice(index, 1);
                ElMessage.success('删除成功');
            });
        };
        // 退出登录的对话框是否显示
        const isDialogShow = ref(false);
        // 退出登录
        const exitLogin = () => {
            isDialogShow.value = false;
            router.replace('/login');
            userStore.updateUserInfo({} as UserInfo);
            ElMesage.success('已退出');
        };

        return () => (
            <div class='w-full h-full flex flex-col justify-start items-center'>
                <p class='text-2xl my-6'>日程目录</p>
                {scheduleList.value.length ? (
                    <el-table
                        style={{ height: '500px' }}
                        data={scheduleList.value}
                        stripe
                        border
                        header-cell-style={{ textAlign: 'center' }}
                        cell-style={{ textAlign: 'center' }}
                    >
                        <el-table-column
                            type='index'
                            index={(i: number) => i + 1}
                            class='text-center'
                        ></el-table-column>
                        <el-table-column prop='title' label='标题'>
                            {{
                                default: ({ $index: i }) => (
                                    <>
                                        {isEditList.value[i] ? (
                                            <el-input
                                                v-model={scheduleList.value[i].title}
                                                autofocus
                                                onBlur={() => {
                                                    changeOneTitle(i);
                                                }}
                                            ></el-input>
                                        ) : (
                                            <span>{scheduleList.value[i].title}</span>
                                        )}
                                    </>
                                )
                            }}
                        </el-table-column>
                        <el-table-column prop='completed' label='是否完成'>
                            {{
                                default: ({ $index: i }) => (
                                    <el-switch
                                        v-model={scheduleList.value[i].completed}
                                        active-value={1}
                                        inactive-value={0}
                                        onChange={() => {
                                            changeOneCompleted(i);
                                        }}
                                    ></el-switch>
                                )
                            }}
                        </el-table-column>
                        <el-table-column label='操作'>
                            {{
                                default: ({ $index: i }) => (
                                    <>
                                        {isEditList.value[i] ? (
                                            <el-button
                                                type='success'
                                                icon={Check}
                                                onClick={() => {
                                                    changeOneTitle(i);
                                                }}
                                            ></el-button>
                                        ) : (
                                            <el-button
                                                type='primary'
                                                icon={Edit}
                                                onClick={() => {
                                                    clickOneEdit(i);
                                                }}
                                            ></el-button>
                                        )}
                                        <el-popconfirm
                                            title='确定要删除次日程吗？'
                                            confirm-button-text='确定'
                                            cancel-button-text='取消'
                                            cancel-button-type='danger'
                                            confirm-button-type='text'
                                            onConfirm={() => {
                                                deleteOne(i);
                                            }}
                                        >
                                            {{
                                                reference: () => (
                                                    <el-button
                                                        type='danger'
                                                        icon={Delete}
                                                    ></el-button>
                                                )
                                            }}
                                        </el-popconfirm>
                                    </>
                                )
                            }}
                        </el-table-column>
                    </el-table>
                ) : (
                    <el-empty description='暂无日程' class='h-[500px]'></el-empty>
                )}
                <div>
                    <el-button type='success' class='my-2' icon={Plus} onClick={addOne}></el-button>
                </div>
                <div class='w-full flex justify-around'>
                    <el-button
                        type='primary'
                        onClick={() => {
                            router.replace('/change-password');
                        }}
                    >
                        修改密码
                    </el-button>
                    <el-button
                        type='danger'
                        onClick={() => {
                            isDialogShow.value = true;
                        }}
                    >
                        退出登录
                    </el-button>
                </div>
                <el-dialog v-model={isDialogShow.value} title='提示' width={500}>
                    {{
                        default: () => <div>确定要退出登录吗？</div>,
                        footer: () => (
                            <div>
                                <el-button
                                    type='text'
                                    onClick={() => {
                                        isDialogShow.value = false;
                                    }}
                                >
                                    取消
                                </el-button>
                                <el-button type='primary' onClick={exitLogin}>
                                    确认
                                </el-button>
                            </div>
                        )
                    }}
                </el-dialog>
            </div>
        );
    }
});
