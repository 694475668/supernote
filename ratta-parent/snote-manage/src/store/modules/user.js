import {
    login,
    logout,
    getInfo
} from '@/api/user'
import {
    getToken,
    setToken,
    removeToken
} from '@/utils/auth'
import {
    resetRouter
} from '@/router'
import router, {
    constantRoutes,
    asyncRoutes
} from '../../router'

const state = {
    token: getToken(),
    name: '',
    routers: constantRoutes,
    permission: ''
}

const mutations = {
    SET_TOKEN: (state, token) => {
        state.token = token
    },
    SET_NAME: (state, name) => {
        state.name = name
    },
    SET_ROUTERS: (state, routers) => {
        state.routers = routers
    },
    SET_PERMISSION: (state, permission) => {
        state.permission = permission
    }
}

const actions = {
    // user login
    login({
        commit
    }, userInfo) {
        const {
            username,
            password
        } = userInfo
        return new Promise((resolve, reject) => {
            login({
                username: username.trim(),
                password: password
            }).then(response => {
                commit('SET_TOKEN', response.token)
                setToken(response.token)
                resolve(response)
            }).catch(error => {
                reject(error)
            })
        })
    },
    // get user info
    getInfo({
        commit,
        state
    }) {
        return new Promise((resolve, reject) => {
            getInfo(0).then(response => {
                console.log(response)
                const arr = []
                let permission = []
                asyncRoutes.map((v, i) => {
                    const idx = response.findIndex(val => val.label === v.meta.title)
                    if (idx === -1) {
                        arr.push(i)
                    }
                    const arr1 = []
                    let arr2 = []
                    response.map(val => {
                        v.children.map(item => {
                            val.children.map(key => {
                                if (key.label == item.meta.title) {
                                    item.meta.attributes = key.children
                                    permission = permission.concat(key.children)
                                    arr2.push(item)
                                }
                            })
                        })

                    })
                    v.children = arr2
                })
                for (let i = arr.length - 1; i >= 0; i--) {
                    asyncRoutes.splice(arr[i], 1)
                }
                commit('SET_PERMISSION', permission)
                commit('SET_NAME', response.name)
                commit('SET_ROUTERS', asyncRoutes)
                router.addRoutes(asyncRoutes)

                resolve(response)
            }).catch(error => {
                reject(error)
            })
        })
    },


    // user logout
    // logout({ commit, state }) {
    //     return new Promise((resolve, reject) => {
    //         logout(state.token).then(() => {
    //             commit('SET_TOKEN', '')
    //             removeToken()
    //             resetRouter()
    //             resolve()
    //         }).catch(error => {
    //             reject(error)
    //         })
    //     })
    // },

    // remove token
    resetToken({
        commit
    }) {
        return new Promise(resolve => {
            commit('SET_TOKEN', '')
            removeToken()
            resolve()
        })
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}