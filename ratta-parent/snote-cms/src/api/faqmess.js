import request from '@/utils/request'

//查询FAQ条目信息
export function getLists(data) {
    return request({
        url: 'cms/faq/item/list',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//新增FAQ条目信息
export function addList(data) {
    return request({
        url: 'cms/faq/item',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改FAQ条目上线下线
export function changeOn(data,id) {
    return request({
        url: 'cms/faq/item/line/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改FAQ条目序号
export function changeSeq(data,id) {
    return request({
        url: 'cms/faq/item/seq/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询单个FAQ条目信息
export function aloneFaq(id) {
    return request({
        url: 'cms/faq/item/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}


//修改FAQ条目信息
export function changeFaq(id,data) {
    return request({
        url: 'cms/faq/item/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//删除FAQ条目信息
export function delefaq(id) {
    return request({
        url: 'cms/faq/item/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}




