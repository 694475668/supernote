import request from '@/utils/request'


//角色管理

//获取列表树
export function getList(id) {
    return request({
        url: 'system/base/resource/left/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}
//获取所有列表树
export function getAllList() {
    return request({
        url: 'system/base/resource/all',
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//查询资源
// export function searchList() {
//     return request({
//         url: 'system/base/resource/{id}',
//         method: 'get'
//     })
// }


//添加资源
export function addList(data) {
    return request({
        url: 'system/base/resource',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改资源
export function amendList(id, data) {
    return request({
        url: 'system/base/resource/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}
//根据id查找资源
export function idList(id) {
    return request({
        url: 'system/base/resource/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//删除资源
export function deleteList(id) {
    return request({
        url: 'system/base/resource/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}


//获取所有角色
export function getRole(data) {
    return request({
        url: 'system/base/role/resource',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//获取所有角色
export function getAlluser() {
    return request({
        url: 'system/base/role',
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}
//查询角色
export function getSearch(data) {
    return request({
        url: 'system/base/role/resource',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//添加角色
export function addUser(data) {
    return request({
        url: 'system/base/role',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//根据角色ID查询角色信息接口
export function idUser(id) {
    return request({
        url: 'system/base/role/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//修改角色
export function amendUser(id, data) {
    return request({
        url: 'system/base/role/' + id,
        method: 'put',
        data,
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//授权
export function authorUser(id, data) {
    return request({
        url: 'system/base/role/resource/' + id,
        method: 'put',
        data,
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//删除
export function deleteUser(id) {
    return request({
        url: 'system/base/role/' + id,
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        method: 'delete'
    })
}