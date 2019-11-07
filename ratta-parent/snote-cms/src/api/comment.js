import request from '@/utils/request'
export function queryComment(data) {
    return request({
        url: 'cms/manage/comment/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


export function updateSeq(data, id) {
    return request({
        url: 'cms/manage/comment/seq/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function updateAudit(data, id) {
    return request({
        url: 'cms/manage/comment/audit/' + id,
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
export function dataManipulation(tableData, id, line, audit, roofPlacement, updateTime, updateUser) {
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环判断
        if (element.id == id) {
            if (updateUser != null && updateUser != "") {
                element.updateUser = updateUser;
            }
            if (updateTime != null && updateTime != "") {
                element.updateTime = updateTime;
            }
            if (line != null && line != "") {
                element.line = line;
            }
            if (audit != null && audit != "") {
                element.audit = audit;
            }
            if (roofPlacement != null && roofPlacement != "") {
                element.roofPlacement = roofPlacement;
            }
        }
    });
    return tableData;
}