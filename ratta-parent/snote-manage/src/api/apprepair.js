import request from '@/utils/request'

//查询app修复点列表
export function getAppFixpointList(data) {
    return request({
        url: 'system/business/app/fixpoint/query',
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

//查询app名称列表
export function getAppNameList() {
    return request({
        url: 'system/business/app/version/name',
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

//查询app版本列表
export function getAppVersionList(data) {
    return request({
        url: 'system/business/app/version/number',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//新增app修复点信息
export function addAppFixpoint(data) {
    return request({
        url: 'system/business/app/fixpoint/save',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改app修复点信息
export function updateAppFixpoint(data, id) {
    return request({
        url: 'system/business/app/fixpoint/update/' + id,
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
            if (name != null && name != "") {
                element.name = name;
            }
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