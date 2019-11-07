import request from '@/utils/request'

//查询问题反馈类型
export function getFeedbackTypeList(data) {
    return request({
        url: 'system/business/feedback/type/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//添加问题反馈类型
export function addFeedvackType(data) {
    return request({
        url: 'system/business/feedback/type/save',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//根据id查询问题反馈类型
export function getFeedbackTypeById(typeId) {
    return request({
        url: 'system/business/feedback/type/query/' + typeId,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

//修改问题反馈类型
export function updateFeedbackType(data) {
    return request({
        url: 'system/business/feedback/type/update',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改问题反馈类型
export function deletFeedbackType(typeId) {
    return request({
        url: 'system/business/feedback/type/delete/' + typeId,
        method: 'delete',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

/**
 * 前端刷新公共方法
 */
export function dataManipulation(tableData, rowData, valueCn, valueEn, valueJa, opTime) {
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环判断
        if (element.typeId == rowData.typeId) {
            if (valueCn != null && valueCn != "") {
                element.valueCn = valueCn;
            }
            if (valueEn != null && valueEn != "") {
                element.valueEn = valueEn;
            }
            if (valueJa != null && valueJa != "") {
                element.valueJa = valueJa;
            }
            element.opUser = window.localStorage.getItem("username");
            if (opTime != null && opTime != "") {
                element.opTime = opTime;
            }
        }
    });
    return tableData;
}