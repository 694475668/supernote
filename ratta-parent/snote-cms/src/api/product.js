import request from '@/utils/request'


//查询产品页banner图接口（cms系统调用调用）
export function getLists(data) {
    return request({
        url: 'cms/product/banner/all',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//添加产品页banner图接口
export function addList(data) {
    return request({
        url: 'cms/product/banner',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//上线、下线产品页banner图接口
export function changeOn(data,id) {
    return request({
        url: 'cms/product/banner/line/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//修改产品页banner图接口
export function changeBanner(data,id) {
    return request({
        url: 'cms/product/banner/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//删除产品页banner图接口
export function deleBanner(id) {
    return request({
        url: 'cms/product/banner/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

