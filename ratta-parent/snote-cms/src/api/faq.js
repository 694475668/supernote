
import request from '@/utils/request'
//查询FAQ标题信息
export function getLists(data) {
    return request({
        url: 'cms/faq/title/list',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//新增FAQ标题信息
export function addList(data) {
    return request({
        url: 'cms/faq/title',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改FAQ标题信息
export function changeList(data,id) {
    return request({
        url: 'cms/faq/title/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改FAQ标题上线下线
export function changeOn(data,id) {
    return request({
        url: 'cms/faq/title/line/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}
//修改固件说明书序号
export function changeSeq(data,id) {
    return request({
        url: 'cms/faq/title/seq/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}
//通过类型查询FAQ列表信息
export function faqType(type) {
    return request({
        url: 'cms/faq/title/list/' + type,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//查询单个FAQ标题信息
export function aloneTit(id) {
    return request({
        url: 'cms/faq/title/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//删除FAQ标题信息
export function deleTit(id) {
    return request({
        url: 'cms/faq/title/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}