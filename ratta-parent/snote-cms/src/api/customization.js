import request from '@/utils/request'

//查询套餐定制数据集合
export function queryAboutAll(data) {
    return request({
        url: '/cms/customize/all',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//备注套餐定制接口
export function updateCustomizeRemark(data, id) {
    return request({
        url: '/cms/customize/remark/' + id,
        method: 'PUT',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//备注套餐定制接口
export function updateProductCustomizeFlag(flag) {
    return request({
        url: '/cms/product/customize/' + flag,
        method: 'PUT',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}