import request from '@/utils/request'
import {
    Date
} from 'core-js';

//查询用户信息
export function getUsermess(data) {
    return request({
        url: 'system/base/sys/user/role',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}



//添加用户
export function addUsermess(data) {
    return request({
        url: 'system/base/sys/user/add',
        method: 'post',
        data,
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

//批量授权接口
export function authorUsermess(data) {
    return request({
        url: 'system/base/sys/user/grant',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//批量启用
export function startUsermess(data) {
    return request({
        url: 'system/base/sys/user/open',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//批量停用
export function closeUsermess(data) {
    return request({
        url: 'system/base/sys/user/close',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改用户信息
export function amendUsermess(id, data) {
    return request({
        url: 'system/base/sys/user/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//解锁用户信息
export function unblockUsermess(id) {
    return request({
        url: 'system/base/sys/user/unlock/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}
//用户解锁验证是否是自己接口
export function unblockUser(id) {
    return request({
        url: 'system/base/sys/user/erificationUnlock/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}
//删除用户信息
export function deleteUsermess(id) {
    return request({
        url: 'system/base/sys/user/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}
//根据用户ID查询用户信息接口
export function getIduser(id) {
    return request({
        url: 'system/base/sys/user/' + id,
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        method: 'get'
    })
}

export function getCheckuser(id) {
    return request({
        url: 'system/base/sys/user/role/' + id,
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        method: 'get'
    })
}

//修改密码
export function pasdUsermess(data) {
    return request({
        url: 'system/base/sys/user',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//用户标识无效时修改密码
export function amendPasw(data) {
    return request({
        url: 'system/base/sys/user/pwd',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        method: 'put',
        data
    })
}

//验证原密码是否正确
export function checkOldPwd(data) {
    return request({
        url: 'system/base/sys/user/check',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        method: 'post',
        data
    })
}
//验证登录的用户是否是可用
export function verifvPassword() {
    return request({
        url: 'system/base/sys/user/verifvPassword',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        method: 'get'
    })
}

/**
 * 前端刷新公共方法
 */
export function dataManipulation(tableData, rowData, name, phone, email, updateUser, updateTime, status) {
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环判断
        rowData.map(row => {
            if (element.id == row.id) {
                if (name != null && name != "") {
                    element.name = name;
                }
                if (phone != null && phone != "") {
                    element.phone = phone;
                }
                if (email != null && email != "") {
                    element.email = email;
                }
                if (updateUser != null && updateUser != "") {
                    element.updateUser = updateUser;
                }
                if (updateTime != null && updateTime != "") {
                    element.updateTime = updateTime;
                }
                if (status != null && status != "") {
                    element.status = status;
                }
            }
        })
    });
    return tableData;
}
