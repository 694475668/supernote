import request from '@/utils/request'

//查询产品页banner图接口
export function queryProductBannerAll(data) {
    return request({
        url: '/cms/product/banner/all',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//上线、下线产品页banner图接口
export function updateProductBannerLine(data, id) {
    return request({
        url: '/cms/product/banner/line/' + id,
        method: 'PUT',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//添加产品页banner图接口
export function addProductBanner(data) {
    return request({
        url: '/cms/product/banner',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改产品页banner图接口
export function updateProductBanner(data, id) {
    return request({
        url: '/cms/product/banner/' + id,
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}