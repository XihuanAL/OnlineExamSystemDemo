import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "./axios";
import './plugins/element.js'
import installElementPlus from './plugins/element'


const app = createApp(App)

installElementPlus(app)
app.use(store).use(router).mount('#app')
app.config.globalProperties.$axios = axios



