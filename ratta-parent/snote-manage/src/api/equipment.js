import request from '@/utils/request'

//根据条件查询用户设备
export function getEquipment(data) {
    return request({
        url: 'equipment/query/user/equipment',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询所有库存信息
export function getAllinfo(data) {
    return request({
        url: 'system/business/stock/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询所有库存信息
export function getStockInfo(data) {
    return request({
        url: 'system/business/stock/query/no/task',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//单台入库
export function toLead(data) {
    return request({
        url: 'system/business/stock/in',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//批量入库
export function toLeadall(equipmentNumber, data) {
    return request({
        url: 'system/business/stock/in/import/' + equipmentNumber,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token'),
            "Content-Type": 'multipart/form-data'
        },
        data
    })
}

//单台出库
export function stock(data) {
    return request({
        url: 'system/business/stock/out',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//批量出库
export function stockAll(data) {
    return request({
        url: 'system/business/stock/out/import',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//撤销
export function backout(equipmentNumber) {
    return request({
        url: 'system/business/stock/undo/' + equipmentNumber,
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

//导出
export function stockExport(data) {
    return request({
        url: 'system/business/stock/export',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        responseType: 'blob',
        data
    })
}

//修改设备健康状态
export function headlthystate(data) {
    return request({
        url: 'system/business/stock/update/healthystate',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function equipmentPurpostate(data) {
    return request({
        url: 'system/business/stock/update/purpose',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//批入模板下载
export function inModeldownload() {
    return request({
        url: 'system/business/stock/download/in/xlsx',
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        responseType: 'blob',
    })
}

//批出模板下载
export function outModeldownload() {
    return request({
        url: 'system/business/stock/download/out/xlsx',
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        responseType: 'blob',
    })
}

//批撤模板下载
export function undoModeldownload() {
    return request({
        url: 'system/business/stock/download/undo/xlsx',
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        responseType: 'blob',
    })
}
//批量撤销
export function undoAlldownload(data) {
    return request({
        url: 'system/business/stock/undo/import',
        method: 'post',
        headers: {
            "Content-Type": 'multipart/form-data',
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

/**
 * 前端刷新公共方法
 */
export function dataManipulation(tableData, rowData, healthyState, equipmentPurpose, opTime) {
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就 可以了,如果是复选框的就需要嵌套for循环判断
        if (element.equipmentModel == rowData.equipmentModel) {
            if (healthyState != null && healthyState != "") {
                if (healthyState == 0 && healthyState == "0") {
                    element.healthyState = "正常";
                }
                if (healthyState == 1 && healthyState == "1") {
                    element.healthyState = "维修中";
                }
                if (healthyState == 2 && healthyState == "2") {
                    element.healthyState = "报废";
                }
            }
            if (equipmentPurpose != null && equipmentPurpose != "") {
                if (equipmentPurpose == 0 && equipmentPurpose == "0") {
                    element.equipmentPurpose = "测试设备";
                }
                if (equipmentPurpose == 1 && equipmentPurpose == "1") {
                    element.equipmentPurpose = "售卖设备";
                }
            }
            element.opUser = window.localStorage.getItem("username");
            if (opTime != null && opTime != "") {
                element.opTime = opTime;
            }
        }
    });
    return tableData;
}