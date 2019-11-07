import request from '@/utils/request'

//查询配件列表(cms系统)
export function getLists(data) {
    return request({
        url: 'cms/part/list',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//新增配件
export function addList(data) {
    return request({
        url: 'cms/part',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//修改配件上线下线
export function changeOn(data,id) {
    return request({
        url: 'cms/part/line/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改配件序号
export function changeSeq(data,id) {
    return request({
        url: 'cms/part/seq/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询单个配件信息
export function aloneList(id) {
    return request({
        url: 'cms/part/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//修改配件
export function changePart(id,data) {
    return request({
        url: 'cms/part/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//删除配件
export function delePart(id) {
    return request({
        url: 'cms/part/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

