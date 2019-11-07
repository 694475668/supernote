import Vue from "vue";
import VueI18n from 'vue-i18n';
import ElementUI from 'element-ui';
import VueAwesomeSwiper from 'vue-awesome-swiper';
import 'bootstrap/dist/css/bootstrap.css'
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { post, get } from "../config/axios";
import 'element-ui/lib/theme-chalk/index.css';
import Meta from 'vue-meta'

Vue.prototype.$get = get;
Vue.prototype.$post = post;
Vue.use(VueI18n);
Vue.use(ElementUI)
Vue.use(VueAwesomeSwiper);
Vue.config.productionTip = false;
Vue.use(Meta)

const locale = (localStorage.getItem("language") || navigator.language || navigator.browserLanguage).toLowerCase() || 'zh-CN';
let lang = ''
console.log(locale.indexOf('zh'))
console.log(locale)
if(locale.indexOf('zh') === 0){
	lang = 'zh-CN'
  localStorage.setItem("language", 'zh-CN')
} else {
	lang = 'en-US'
  localStorage.setItem("language", 'en-US')
}

const i18n = new VueI18n({
  //定义默认语言
  locale: localStorage.getItem('lang') ? localStorage.getItem('lang') : lang,
  silentFallbackWarn: true,
  messages: {
    'zh-CN': require('./i18n/zh'),   // 中文语言包
    'en-US': require('./i18n/en')    // 英文语言包
  }
})

router.beforeEach((to, from, next) => {
  if (to.meta.metaInfo)
    store.commit("CAHNGE_META_INFO", to.meta.metaInfo)
  next()
});

new Vue({
  router,
  i18n,
  store,
  metaInfo(){
    return {
      title: this.$store.state.metaInfo.title,
      meta: [
        {
          name: "keywords",
          content: this.$store.state.metaInfo.keywords
        }, {
          name: "description",
          content: this.$store.state.metaInfo.description
        }
      ]
    }
  },
  render: h => h(App)
}).$mount("#app");
