import request from '@/utils/request'

//查询所有退换货记录信息
export function getExchange(data) {
    return request({
        url: 'system/business/return/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//换货
export function getBarter(data) {
    return request({
        url: 'system/business/return/exchangeStock',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//退货
export function getReturn(data) {
    return request({
        url: 'system/business/return/returnStock',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}