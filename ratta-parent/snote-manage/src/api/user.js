import request from '@/utils/request'

export function login(data) {
    return request({
        url: 'system/base/sys/user/login',
        method: 'post',
        data
    })
}

export function getInfo(id) {
    return request({
        url: 'system/base/resource/left/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

// export function logout() {
//     return request({
//         url: '/user/logout',
//         headers: {
//             "x-access-token": window.localStorage.getItem('token')
//         },
//         method: 'post'
//     })
// }