import request from '@/utils/request'

//查询所有固件修复点信息列表
export function getFirmwareFixpointList(data) {
    return request({
        url: 'system/business/firmware/fixpoint/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询语言列表
export function getLanList(data) {
    return request({
        url: 'system/base/dictionary/param',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询固件版本号列表
export function getFirmwareVersionList(data) {
    return request({
        url: 'system/business/firmware/info/version',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//新增固件修复点信息
export function addFirmwareFixpoint(data) {
    return request({
        url: 'system/business/firmware/fixpoint/save',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改固件修复点信息
export function updateFirmwareFixpoint(data, id) {
    return request({
        url: 'system/business/firmware/fixpoint/update/' + id,
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

/**
 * 前端刷新公共方法
 */
export function dataManipulation(tableData, rowData, fixPoint, opTime) {
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环判断
        if (element.id == rowData.id) {
            if (fixPoint != null && fixPoint != "") {
                element.fixPoint = fixPoint;
            }
            element.opUser = window.localStorage.getItem("username");
            if (opTime != null && opTime != "") {
                element.opTime = opTime;
            }
        }
    });
    return tableData;
}