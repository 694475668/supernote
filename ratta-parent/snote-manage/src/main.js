import Vue from 'vue'

import VueCron from 'vue-cron'
import VueQuillEditor from 'vue-quill-editor'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n
// require styles 引入样式
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'


import { directive } from './utils/directive'

import '@/icons' // icon
import '@/permission' // permission control


directive()

// import axios from 'axios'

// axios.defaults.withCredentials = true; //让ajax携带cookie

// Vue.prototype.$axios = axios;

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */
// import {
//     mockXHR
// } from '../mock'
// if (process.env.NODE_ENV === 'production') {
//     mockXHR()
// }

// set ElementUI lang to EN
Vue.use(ElementUI, {
    locale
})
Vue.use(VueQuillEditor)

Vue.use(VueCron)

Vue.config.productionTip = false

new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
})
