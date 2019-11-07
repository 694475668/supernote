import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [{
        path: '/login',
        component: () =>
            import ('@/views/login/index'),
        hidden: true
    },

    {
        path: '/404',
        component: () =>
            import ('@/views/404'),
        hidden: true
    },

    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        hidden: true,
        children: [{
            path: 'dashboard',
            name: 'Dashboard',
            component: () =>
                import ('@/views/dashboard/index'),
            meta: {
                title: '主页',
                icon: 'dashboard',
                affix: true
            }
        }]
    }
]
export const asyncRoutes = [
    //系统管理
    {
        path: '/systems',
        component: Layout,
        redirect: '/systems/resource',
        alwaysShow: true, // will always show the root menu
        name: 'systems',
        meta: {
            title: '系统管理',
            icon: 'da', // you can set roles in root nav
        },
        children: [{
                path: 'resource',
                component: () =>
                    import ('@/views/systems/resource.vue'),
                name: 'Resource',
                meta: {
                    title: '资源管理',
                }
            },
            {
                path: 'role',
                component: () =>
                    import ('@/views/systems/role.vue'),
                name: 'RolePermission',
                meta: {
                    title: '角色管理',
                }
            },
            {
                path: 'systemuser',
                component: () =>
                    import ('@/views/systems/systemuser.vue'),
                name: 'Systemuser',
                meta: {
                    title: '系统用户管理',
                }
            },
            {
                path: 'dictionaries',
                component: () =>
                    import ('@/views/systems/dictionaries.vue'),
                name: 'Dictionaries',
                meta: {
                    title: '数据字典',
                }
            },
        ]
    },
    //首页
    {
        path: '/home',
        component: Layout,
        redirect: '/home/banner',
        alwaysShow: true, // will always show the root menu
        name: 'Home',
        meta: {
            title: '首页',
            icon: 'home', // you can set roles in root nav
        },
        children: [{
                path: 'banner',
                component: () =>
                    import ('@/views/home/banner.vue'),
                name: 'Banner',
                meta: {
                    title: '轮播图',
                }
            },
            {
                path: 'awards',
                component: () =>
                    import ('@/views/home/awards.vue'),
                name: 'Awards',
                meta: {
                    title: '荣获奖项轮播图',
                }
            },
        ]
    },
    //超级笔记页
    {
        path: '/product',
        component: Layout,
        redirect: '/product/banner',
        alwaysShow: true, // will always show the root menu
        name: 'product',
        meta: {
            title: '超级笔记页',
            icon: 'super', // you can set roles in root nav
        },
        children: [{
                path: 'banners',
                component: () =>
                    import ('@/views/product/banners.vue'),
                name: 'Banners',
                meta: {
                    title: 'Banner图',
                }
            },
            {
                path: 'summarize',
                component: () =>
                    import ('@/views/product/summarize.vue'),
                name: 'Summarize',
                meta: {
                    title: '产品详情',
                }
            },
            {
                path: 'addpro',
                hidden: true,
                component: () =>
                    import ('@/views/product/addpro.vue'),
                name: 'Addpro',
                meta: {
                    title: '新增产品页面',
                }
            },
            {
                path: 'changepro',
                hidden: true,
                component: () =>
                    import ('@/views/product/changepro.vue'),
                name: 'Addpro',
                meta: {
                    title: '修改产品页面',
                }
            },
        ]
    },

    //用户选配
    {
        path: '/change',
        component: Layout,
        redirect: '/change/customization',
        alwaysShow: true, // will always show the root menu
        name: 'Change',
        meta: {
            title: '套餐定制',
            icon: 'user', // you can set roles in root nav
        },
        children: [{
                path: 'customization',
                component: () =>
                    import ('@/views/change/customization.vue'),
                name: 'Customization',
                meta: {
                    title: '套餐定制',
                }
            },
            // {
            //     path: 'email',
            //     component: () =>
            //         import ('@/views/change/email.vue'),
            //     name: 'Email',
            //     meta: {
            //         title: '邮件管理',
            //     }
            // },
        ]
    },


    //配件页
    {
        path: '/parts',
        component: Layout,
        redirect: '/parts/list',
        alwaysShow: true, // will always show the root menu
        name: 'Parts',
        meta: {
            title: '配件页',
            icon: 'parts', // you can set roles in root nav
        },
        children: [{
                path: 'list',
                component: () =>
                    import ('@/views/parts/list.vue'),
                name: 'List',
                meta: {
                    title: '配件系列',
                }
            },
            {
                path: 'partsMess',
                component: () =>
                    import ('@/views/parts/partsMess.vue'),
                name: 'PartsMess',
                meta: {
                    title: '配件信息',
                }
            },
            {
                path: 'addmess',
                hidden: true,
                component: () =>
                    import ('@/views/parts/addmess.vue'),
                name: 'Addmess',
                meta: {
                    title: '新增配件信息页面',
                }
            },
            {
                path: 'changemess',
                hidden: true,
                component: () =>
                    import ('@/views/parts/changemess.vue'),
                name: 'Changemess',
                meta: {
                    title: '修改配件信息页面',
                }
            },
            {
                path: 'addlist',
                hidden: true,
                component: () =>
                    import ('@/views/parts/addlist.vue'),
                name: 'Addlist',
                meta: {
                    title: '新增配件系列页面',
                }
            },
            {
                path: 'changelist',
                hidden: true,
                component: () =>
                    import ('@/views/parts/changelist.vue'),
                name: 'Changelist',
                meta: {
                    title: '修改配件系列页面',
                }
            },
        ]
    },

    //服务页
    {
        path: '/serve',
        component: Layout,
        redirect: '/serve/search',
        alwaysShow: true, // will always show the root menu
        name: 'Serve',
        meta: {
            title: '服务页',
            icon: 'serve', // you can set roles in root nav
        },
        children: [{
                path: 'faq',
                component: () =>
                    import ('@/views/serve/faq.vue'),
                name: 'Faq',
                meta: {
                    title: 'FAQ/FMQ标题',
                }
            },
            {
                path: 'qamess',
                component: () =>
                    import ('@/views/serve/qamess.vue'),
                name: 'Qamess',
                meta: {
                    title: 'FAQ/FMQ内容',
                }
            },
            {
                path: 'detail',
                component: () =>
                    import ('@/views/serve/detail.vue'),
                name: 'Detail',
                meta: {
                    title: '说明书/固件',
                }
            },
            {
                path: 'addqamess',
                hidden: true,
                component: () =>
                    import ('@/views/serve/addqamess.vue'),
                name: 'Addqamess',
                meta: {
                    title: '新增内容页面',
                }
            },
            {
                path: 'changeqamess',
                hidden: true,
                component: () =>
                    import ('@/views/serve/changeqamess.vue'),
                name: 'Changeqamess',
                meta: {
                    title: '修改内容页面',
                }
            },
            {
                path: 'adddetail',
                hidden: true,
                component: () =>
                    import ('@/views/serve/adddetail.vue'),
                name: 'Adddetail',
                meta: {
                    title: '新增说明书/固件页面',
                }
            },
            {
                path: 'changedetail',
                hidden: true,
                component: () =>
                    import ('@/views/serve/changedetail.vue'),
                name: 'Changedetail',
                meta: {
                    title: '修改说明书/固件页面',
                }
            },
            {
                path: 'feedback',
                component: () =>
                    import ('@/views/serve/feedback.vue'),
                name: 'Feedback',
                meta: {
                    title: '反馈信息',
                }
            }

        ]
    },

    //博客
    {
        path: '/blog',
        component: Layout,
        redirect: '/blog/blogList',
        alwaysShow: true, // will always show the root menu
        name: 'Blog',
        meta: {
            title: '博客',
            icon: 'blog', // you can set roles in root nav
        },
        children: [{
                path: 'blogList',
                component: () =>
                    import ('@/views/blog/blogList.vue'),
                name: 'BlogList',
                meta: {
                    title: '博客列表',
                }
            },
            {
                path: 'comment',
                component: () =>
                    import ('@/views/blog/comment.vue'),
                name: 'Comment',
                meta: {
                    title: '评论',
                }
            },
            {
                path: 'addblog',
                hidden: true,
                component: () =>
                    import ('@/views/blog/addBlog.vue'),
                name: 'Addblog',
                meta: {
                    title: '新增博客页面',
                }
            },

            {
                path: 'changeblog',
                hidden: true,
                component: () =>
                    import ('@/views/blog/changeBlog.vue'),
                name: 'Changeblog',
                meta: {
                    title: '修改博客页面',
                }
            },
        ]
    },

    //关于
    {
        path: '/about',
        component: Layout,
        redirect: '/about/abouts',
        alwaysShow: true, // will always show the root menu
        name: 'About',
        meta: {
            title: '关于',
            icon: 'about', // you can set roles in root nav
        },
        children: [{
                path: '/abouts',
                component: () =>
                    import ('@/views/about/abouts.vue'),
                name: 'Abouts',
                meta: {
                    title: '关于',
                }
            }, {
                path: '/addabout',
                hidden: true,
                component: () =>
                    import ('@/views/about/addabout.vue'),
                name: 'AddAbout',
                meta: {
                    title: '添加关于页面',
                }
            },
            {
                path: '/changeabout',
                hidden: true,
                component: () =>
                    import ('@/views/about/changeabout.vue'),
                name: 'Changeabout',
                meta: {
                    title: '修改关于页面',
                }
            },

        ]
    },
    //seo
    {
        path: '/seo',
        component: Layout,
        redirect: '/seo/overallseo',
        alwaysShow: true, // will always show the root menu
        name: 'Seo',
        meta: {
            title: 'SEO',
            icon: 'seo', // you can set roles in root nav
        },
        children: [{
            path: '/overallseo',
            component: () =>
                import ('@/views/seo/overallseo.vue'),
            name: 'Resourc',
            meta: {
                title: '全局SEO',
            }
        }]
    },

    // 404 page must be placed at the end !!!

]


export const notFound = [{
    path: '*',
    redirect: '/404',
    hidden: true
}]

const createRouter = () => new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({
        y: 0
    }),
    routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router