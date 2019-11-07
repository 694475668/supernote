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
            icon: 'dashboard', // you can set roles in root nav
        },
        children: [{
                path: 'resource',
                component: () =>
                    import ('@/views/systems/resource.vue'),
                name: 'Resourc',
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
                path: 'dispatcher',
                component: () =>
                    import ('@/views/systems/dispatcher.vue'),
                name: 'Dispatche',
                meta: {
                    title: '调度任务',
                }
            }
        ]
    },
    //参数管理
    {
        path: '/parameter',
        component: Layout,
        redirect: '/parameter/dictionaries',
        alwaysShow: true, // will always show the root menu
        name: 'Paramete',
        meta: {
            title: '参数管理',
            icon: 'guide', // you can set roles in root nav
        },
        children: [{
            path: 'dictionaries',
            component: () =>
                import ('@/views/parameter/dictionaries.vue'),
            name: 'Dictionarie',
            meta: {
                title: '数据字典管理',
            }
        }, {
            path: 'configuration',
            component: () =>
                import ('@/views/parameter/configuration.vue'),
            name: 'Configuratio',
            meta: {
                title: '参数配置',
            }
        }]
    },
    //用户管理
    {
        path: '/user',
        component: Layout,
        redirect: '/user/enter',
        alwaysShow: true, // will always show the root menu
        name: 'User',
        meta: {
            title: '用户管理',
            icon: 'lock', // you can set roles in root nav
        },
        children: [{
            path: 'enter',
            component: () =>
                import ('@/views/user/enter.vue'),
            name: 'Enter',
            meta: {
                title: '登录记录',
            }
        }, {
            path: 'userControl',
            component: () =>
                import ('@/views/user/userControl.vue'),
            name: 'UserControl',
            meta: {
                title: '用户信息',
            }
        }, ]
    },
    //业务管理
    {
        path: '/business',
        component: Layout,
        redirect: '/business/dealer',
        alwaysShow: true, // will always show the root menu
        name: 'Business',
        meta: {
            title: '业务管理',
            icon: 'component', // you can set roles in root nav
        },
        children: [{
            path: 'dealer',
            component: () =>
                import ('@/views/business/dealer.vue'),
            name: 'Deale',
            meta: {
                title: '经销商信息',
            }
        }]
    },

    // // {
    // //     path: '/icon',
    // //     component: Layout,
    // //     hidden: true,
    // //     children: [{
    // //         path: 'index',
    // //         component: () =>
    // //             import ('@/views/svg-icons/index'),
    // //         name: 'Icons',
    // //         meta: { title: 'icons', icon: 'icon', noCache: true }
    // //     }]
    // // },
    //设备管理
    {
        path: '/equipment',
        component: Layout,
        redirect: '/equipment/equiinfo',
        alwaysShow: true, // will always show the root menu
        name: 'Equipment',
        meta: {
            title: '设备管理',
            icon: 'nested', // you can set roles in root nav
        },
        children: [

            {
                path: 'equiinfo',
                component: () =>
                    import ('@/views/equipment/equiinfo.vue'),
                name: 'Equiinfo',
                meta: {
                    title: '库存信息'
                }
            }, {
                path: 'exchange',
                component: () =>
                    import ('@/views/equipment/exchange.vue'),
                name: 'Exchange',
                meta: {
                    title: '退换货管理'
                }
            },
            {
                path: 'information',
                component: () =>
                    import ('@/views/equipment/information.vue'),
                name: ' information',
                meta: {
                    title: '设备信息',
                }
            },
        ]
    },

    //固件管理
    {
        path: '/firmware',
        component: Layout,
        redirect: '/firmware/firwareupload',
        alwaysShow: true, // will always show the root menu
        name: 'Firmware',
        meta: {
            title: '固件管理',
            icon: 'example', // you can set roles in root nav
        },
        children: [{
            path: 'firwareupload',
            component: () =>
                import ('@/views/firmware/firwareupload.vue'),
            name: 'Firwareupload',
            meta: {
                title: '固件版本信息',
            }
        }, {
            path: 'firwarerepair',
            component: () =>
                import ('@/views/firmware/firwarerepair.vue'),
            name: 'Firwarerepair',
            meta: {
                title: '固件版本修复点'
            }
        }, {
            path: 'firwareissue',
            component: () =>
                import ('@/views/firmware/firwareissue.vue'),
            name: 'Firwareissue',
            meta: {
                title: '固件版本发布'
            }
        }, ]
    },
    //终端管理
    {
        path: '/terminal',
        component: Layout,
        redirect: '/terminal/log',
        alwaysShow: true, // will always show the root menu
        name: 'Terminal',
        meta: {
            title: '终端管理',
            icon: 'tab', // you can set roles in root nav
        },
        children: [{
            path: 'log',
            component: () =>
                import ('@/views/terminal/log.vue'),
            name: 'Log',
            meta: {
                title: '终端日志管理',
            }
        }]
    },

    //反馈管理
    {
        path: '/feedback',
        component: Layout,
        redirect: '/feedback/feedbacktype',
        alwaysShow: true, // will always show the root menu
        name: 'Feedback',
        meta: {
            title: '反馈管理',
            icon: 'clipboard', // you can set roles in root nav
        },
        children: [{
            path: 'feedbacktype',
            component: () =>
                import ('@/views/feedback/feedbacktype.vue'),
            name: 'Feedbacktype',
            meta: {
                title: '反馈问题类型',
            }
        }, {
            path: 'feedbackrecord',
            component: () =>
                import ('@/views/feedback/feedbackrecord.vue'),
            name: 'Feedbackrecord',
            meta: {
                title: '反馈问题记录'
            }
        }]
    },
    //应用管理
    {
        path: '/apply',
        component: Layout,
        redirect: '/apply/appupload',
        alwaysShow: true, // will always show the root menu
        name: 'Apply',
        meta: {
            title: '应用管理',
            icon: 'link', // you can set roles in root nav
        },
        children: [{
            path: 'appupload',
            component: () =>
                import ('@/views/apply/appupload.vue'),
            name: 'Appupload',
            meta: {
                title: 'app版本信息',
            }
        }, {
            path: 'apprepair',
            component: () =>
                import ('@/views/apply/apprepair.vue'),
            name: 'Apprepair',
            meta: {
                title: 'app版本修复点'
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