import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
};

export default new Router({
  hash: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("./views/Home.vue")
    },{
      path: "/blog",
      name: "blog",
      component: () => import("./views/Blog.vue")
    }, {
      path: "/blog/detail",
      name: "detail",
      component: () => import("./views/Detail.vue")
    },{
      path: "/service",
      name: "Service",
      component: () => import("./views/Service.vue")
    },{
      path: "/service/faq",
      name: "FAQ",
      component: () => import("./views/FAQ.vue")
    },{
      path: "/service/trouble",
      name: "Trouble",
      component: () => import("./views/Trouble.vue")
    },{
      path: "/service/trouble/detail",
      name: "Bug",
      component: () => import("./views/Bug.vue")
    },{
      path: "/service/faq/detail",
      name: "Content",
      component: () => import("./views/Content.vue")
    },{
      path: "/service/firmware",
      name: "Firmware",
      component: () => import("./views/Firmware.vue")
    },{
      path: "/product",
      name: "A5",
      component: () => import("./views/A5.vue")
    },{
      path: "/service/instruction",
      name: "Instruction",
      component: () => import("./views/Instruction.vue")
    },{
      path: "/service/search",
      name: "search",
      component: () => import("./views/Search.vue")
    },{
      path: "/part",
      name: "part",
      component: () => import("./views/Part.vue")
    }, {
      path: "/form",
      name: "form",
      component: () => import("./views/Form.vue")
    },{
      path: "/about",
      name: "about",
      component: () => import("./views/About.vue")
    }
  ]
});
