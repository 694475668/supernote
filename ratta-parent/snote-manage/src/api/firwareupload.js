import request from '@/utils/request'

//查询所有经销商信息
export function getFirware(data) {
    return request({
        url: 'system/business/firmware/info/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询固件版本子类信息
export function getFirwarechildren(data) {
    return request({
        url: 'system/business/firmware/info/line/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//上传
export function firwareUpload(data) {
    return request({
        url: 'system/business/firmware/info/upload',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//删除
export function deleteFirware(id) {
    return request({
        url: 'system/business/firmware/info/delete/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//修改固件设备型号关系
export function changeFirware(data) {
    return request({
        url: 'system/business/firmware/equipment/update',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询固件版本详细信息
export function getDetailed(id) {
    return request({
        url: 'system/business/firmware/info/query/detail/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//查询子固件版本详细信息
export function getChildDetailed(id) {
    return request({
        url: 'system/business/firmware/info/line/query/detail/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//校验合法性
export function checkValidity(password, data) {
    return request({
        url: 'system/business/firmware/info/upload/check/' + password,
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token'),
            "Content-Type": "multipart/form-data"
        },
        data,
        timeout: 600000
    })
}

//上传
export function getUpload(data) {
    return request({
        url: 'system/business/firmware/info/upload',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data,
        timeout: 3600000
    })
}

//根据固件版本号查询设备型号
export function searchEquipment(data) {
    return request({
        url: 'system/business/firmware/equipment/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//审核测试
export function getTest(data) {
    return request({
        url: 'system/business/firmware/task/audit/test',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}
//审核测试删除
export function testDelete(equipmentNumber) {
    return request({
        url: 'system/business/firmware/task/delete/' + equipmentNumber,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

//根据版本号查询所有库存信息
export function getTestlists(data) {
    return request({
        url: 'system/business/stock/version/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}



//跳过
export function stride(data) {
    return request({
        url: 'system/business/firmware/info/stride',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//审核
export function finishTest(data) {
    return request({
        url: 'system/business/firmware/info/audit',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

/**
 * 前端刷新公共方法
 *
 */
export function dataManipulation(tableData, rowData, status, auditFlag, auditInfo, isStride, auditTime) {
    console.log(isStride)
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环 判断
        if (element.id == rowData.id) {
            if (auditInfo != null && auditInfo != "") {
                element.auditInfo = auditInfo;
            }
            if (status != null && status != "") {
                element.status = status;
            }
            if (auditFlag != null && auditFlag != "") {
                element.auditFlag = auditFlag;
            }
            element.isStride = "0";
            if (isStride != null && isStride != "") {
                if (isStride == 0 || isStride == "0") {
                    element.isStride = "0";
                }
                if (isStride == 1 || isStride == "1") {
                    element.isStride = "1";
                }
            }
            if (auditTime != null && auditTime != "") {
                element.auditUser = window.localStorage.getItem("username");
                element.auditTime = auditTime;
            }
        }
    });
    return tableData;
}