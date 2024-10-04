import { createPinia } from 'pinia';
import PiniaPluginPersistedState from 'pinia-plugin-persistedstate';

export { default as useUserStore } from './user';

const pinia = createPinia();
pinia.use(PiniaPluginPersistedState);
export default pinia;
