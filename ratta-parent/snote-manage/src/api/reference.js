import request from '@/utils/request'

export function getreferlist(data) {
    return request({
        url: 'system/base/reference',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function addList(data) {
    return request({
        url: 'system/base/reference/add',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//根据id查询系统参数详细信息
export function changeList(id) {
    return request({
        url: 'system/base/reference/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

//更新系统参数详细信息接口
export function updateList(id, data) {
    return request({
        url: 'system/base/reference/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//根据id删除系统参数详细信息接口
export function deletaList(id) {
    return request({
        url: 'system/base/reference/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}