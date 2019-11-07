import { getList } from '@/api/tree'

const state = {}

const mutations = {}

const actions = {
    // tree
    getList({ commit }) {
        return new Promise((resolve, reject) => {
            getList().then(response => {
                console.log(response.token)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    },


}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}