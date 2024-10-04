import './assets/main.css';

import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import pinia from '@/stores';

const app = createApp(App);

app.use(router).use(ElementPlus).use(pinia);

app.mount('#app');
