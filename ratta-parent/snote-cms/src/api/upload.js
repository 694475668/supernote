import request from '@/utils/request'

export function uploads(data) {
    return request({
        url: 'cms/picture/upload',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data,
        timeout: 3600000
    })
}