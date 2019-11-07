import request from '@/utils/request'

//查询问题反馈记录
export function getFeedbackRecodList(data) {
    return request({
        url: 'system/business/feedback/record/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询一级问题类型
export function getFeedbackRecodFirst(data) {
    return request({
        url: 'system/business/feedback/type/query/layered',
        method: 'post',
        data,
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

//查询二级问题类型
export function getFeedbackRecodSecond(data) {
    return request({
        url: 'system/business/feedback/type/query/layered',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        method: 'post',
        data
    })
}

//查询三级问题类型
export function getFeedbackRecodThird(data) {
    return request({
        url: 'system/business/feedback/type/query/layered',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        method: 'post',
        data
    })
}

//导出问题反馈记录
export function exportFeedbackRecodList(data) {
    return request({
        url: 'system/business/feedback/record/export',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        responseType: 'blob',
        data
    })
}