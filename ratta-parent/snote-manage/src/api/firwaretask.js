import request from '@/utils/request'

//查询固件任务信息
export function getTask(data) {
    return request({
        url: 'system/business/firmware/task/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询版本列表
export function getVersion(data) {
    return request({
        url: 'system/business/firmware/info/version',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//根据固件版本号查询设备型号
export function getModel(data) {
    return request({
        url: 'system/business/firmware/equipment/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//根据设备型号查询设备用途
export function getPurpose(equipmentModel) {
    return request({
        url: 'system/business/stock/purpose/query/' + equipmentModel,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//发布
export function getDeploy(data) {
    return request({
        url: 'system/business/firmware/task/deploy',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//撤销
export function getRepeal(id) {
    return request({
        url: 'system/business/firmware/task/undo/' + id,
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//单刷
export function getFlash(data) {
    return request({
        url: 'system/business/firmware/task/flash',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//批刷
export function getFlashAll(data) {
    return request({
        url: 'system/business/firmware/task/flash/batch',
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
export function dataManipulation(tableData, rowData, status, opTime) {
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环判断!
        if (element.id == rowData.id) {
            if (status != null && status != "") {
                element.status = status;
            }
            element.revokeUser = window.localStorage.getItem("username");
            if (opTime != null && opTime != "") {
                element.revokeTime = opTime;
            }
        }
    });
    return tableData;
}