import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/table/list',
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        params
    })
}