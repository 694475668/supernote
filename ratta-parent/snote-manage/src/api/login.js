import request from '@/utils/request'

//按条件查询所有用户登录记录
export function getLogin(data) {
    return request({
        url: 'user/query/loginRecord',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//根据用户id查询用户信息
export function getIduser(id) {
    return request({
        url: 'user/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token'),
            "User-ID": id
        },
    })
}

//按条件查询所有用户
export function getUsercontrol(data) {
    return request({
        url: 'user/query/all',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//根据用户id查询用户信息(内部调用)
export function getUserid(userId) {
    return request({
        url: 'user/query/' + userId,
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        method: 'get'
    })
}

//修改手机号
export function amendPhone(id, data) {
    return request({
        url: 'user/telephone',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token'),
            "User-ID": id
        },
        data
    })
}

//修改邮箱
export function amendEmail(id, data) {
    return request({
        url: 'user/email',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token'),
            "User-ID": id
        },
        data
    })
}

//冻结或解冻用户
export function freezes(data) {
    return request({
        url: 'user/freeze',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

/**
 * 前端刷新公共方法
 */
export function dataManipulation(tableData, rowData, telephone, email, isNormal) {
    tableData.map(element => {
        rowData.map(row => {
            //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环判断
            if (element.userId == row.userId) {
                if (telephone != null && telephone != "") {
                    element.telephone = telephone;
                }
                if (email != null && email != "") {
                    element.email = email;
                }
                if (isNormal != null && isNormal != "") {
                    element.isNormal = isNormal;
                }
            }
        })
    });
    return tableData;
}