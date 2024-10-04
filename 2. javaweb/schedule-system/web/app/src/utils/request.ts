import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();

const request = axios.create({ baseURL: '/api' });

request.interceptors.response.use(
    (res) => res.data,
    async (err) => {
        if (err.response.status === 504) {
            // 还未登录
            console.log(err);
            // console.log(router)
            // await router.replace('/login');
        }
    }
);

export default request;
