import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './routers/router';
import Antd from "ant-design-vue";
import "ant-design-vue/dist/reset.css";
import Vue3Toastify, { type ToastContainerOptions } from "vue3-toastify";
import "vue3-toastify/dist/index.css";
import { VueQueryPlugin } from "@tanstack/vue-query";
import { createPinia } from "pinia";

const app = createApp(App);

app.use(router);
app.use(Antd);
app.use(Vue3Toastify, {
    autoClose: 3000,
  } as ToastContainerOptions);
app.use(VueQueryPlugin);
app.use(createPinia());

app.mount('#app');

