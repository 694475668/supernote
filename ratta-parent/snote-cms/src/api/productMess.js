import request from '@/utils/request'


//查询产品页详情接口(cms系统调用)
export function getLists(data) {
    return request({
        url: 'cms/product/description/all',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//添加产品页详情接口
export function addList(data) {
    return request({
        url: 'cms/product/description',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//上线、下线产品页详情接口
export function changeOn(data, id) {
    return request({
        url: 'cms/product/description/line/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//修改产品页详情图接口
export function changeBanner(id, data) {
    return request({
        url: 'cms/product/description/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//修改产品页详情序号
export function changeSeq(data, id) {
    return request({
        url: 'cms/product/description/seq/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//删除产品页详情接口
export function deleDescription(id) {
    return request({
        url: '/cms/product/description/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}