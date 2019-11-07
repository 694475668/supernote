import request from '@/utils/request'

export function login(data) {
    return request({
        url: 'system/cms/base/manage/user/login',
        method: 'post',
        data
    })
}

export function getInfo() {
    return request({
        url: 'system/cms/base/manage/resource',
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