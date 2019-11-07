import request from '@/utils/request'

//调度任务信息列表
export function getDislist(data) {
    return request({
        url: 'system/base/schedule',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询查询日志
export function searchDislist(data) {
    return request({
        url: 'system/base/schedule/param',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//启用定时任务
export function startDislist(data) {
    return request({
        url: 'system/base/schedule/open/close',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

// //停用定时任务
// export function endDislist() {
//     return request({
//         url: 'system/base/schedule/close',
//         method: 'put'
//     })
// }

//修改调度任务信息
export function amendDislist(data) {
    return request({
        url: 'system/base/schedule',
        method: 'put',
        data,
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//通过id查询任务信息
export function amendsDislist(id) {
    return request({
        url: 'system/base/schedule/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

//执行定时任务
export function executeTime(id) {
    return request({
        url: 'system/base/schedule/execute/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

/**
 * 前端刷新公共方法
 */
export function dataManipulation(tableData, rowData, status) {
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环判断
        rowData.map(row => {
            if (element.id == row.id) {
                if (status != null && status != "") {
                    element.status = status;
                }
            }
        })
    });
    return tableData;
}