import request from '@/utils/request'
// 根据参数查询设备日志信息接口
export function searchAppVersion(data) {
    return request({
        url: 'system/business/app/version/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

// 下载
export function download(id, data) {
    return request({
        url: 'system/business/app/version/download/url/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//删除
export function deleteApp(id, data) {
    return request({
        url: 'system/business/app/version/delete/' + id,
        method: 'delete',
        data,
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

//撤销
export function undo(id, data) {
    return request({
        url: 'system/business/app/version/undo/' + id,
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}
//审核
export function audit(data) {
    return request({
        url: 'system/business/app/version/audit',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}
//发布
export function deploy(id, data) {
    return request({
        url: 'system/business/app/version/deploy/' + id,
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}
//上传检查
export function getCheckUpload(data) {
    return request({
        url: 'system/business/app/version/upload/check',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token'),
            "Content-Type": 'multipart/form-data'
        },
        data,
        timeout: 600000
    })
}

//上传
export function getUpload(data) {
    return request({
        url: 'system/business/app/version/upload',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data,
        timeout: 1200000
    })
}

//添加灰度
export function addGray(data) {
    return request({
        url: 'system/business/app/version/gray',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token'),
            "Content-Type": 'application/json'
        },
        data
    })
}

//根据app查询灰度信息
export function querGray(appId) {
    return request({
        url: 'system/business/app/version/gray/info/' + appId,
        method: 'GET',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

//根据app查询灰度信息
export function delGray(id) {
    return request({
        url: 'system/business/app/version/gray/delete/' + id,
        method: 'delete',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

/**
 * 前端刷新公共方法
 */
export function dataManipulation(tableData, rowData, auditingFlag, deployFlag, opTime) {
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环判断
        if (element.id == rowData.id) {
            if (auditingFlag != null && auditingFlag != "") {
                element.auditingFlag = auditingFlag;
            }
            if (deployFlag != null && deployFlag != "") {
                element.deployFlag = deployFlag;
            }
            element.opUser = window.localStorage.getItem("username");
            if (opTime != null && opTime != "") {
                element.opTime = opTime;
            }
        }
    });
    return tableData;
}