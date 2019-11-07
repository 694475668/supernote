import request from '@/utils/request'

//查询经销商信息
export function getDealer(data) {
    return request({
        url: 'system/business/dealer/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}
//查询所有经销商信息
export function getDealerAll() {
    return request({
        url: 'system/business/dealer/query/all',
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}
//查询单个经销商信息
export function getDealerAlone(id) {
    return request({
        url: 'system/business/dealer/query/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//添加
export function addDealer(data) {
    return request({
        url: 'system/business/dealer/save',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改
export function exchangeDealer(id, data) {
    return request({
        url: 'system/business/dealer/update/' + id,
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//删除
export function deleteDealer(id) {
    return request({
        url: 'system/business/dealer/delete/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//模板下载
export function templateDownload() {
    return request({
        url: 'system/business/dealer/download/xlsx',
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        responseType: 'blob'
    })
}

//导入
export function toLead(data) {
    return request({
        url: 'system/business/dealer/import',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token'),
            "Content-Type": 'multipart/form-data'
        },
        data
    })
}

//导出
export function derive(data) {
    return request({
        url: 'system/business/dealer/export',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        responseType: 'blob',
        data
    })
}

/**
 * 前端刷新公共方法
 */
export function dataManipulation(tableData, rowData, dealerType, dealerName, contact, phone, address, salesman, applicationTime, remark) {
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环判断
        if (element.id == rowData.id) {
            if (dealerType != null && dealerType != "") {
                if (dealerType == "1") {
                    element.dealerType = "线上代理";
                } else if (dealerType == "2") {
                    element.dealerType = "线下代理";
                } else if (dealerType == "3") {
                    element.dealerType = "海外代理";
                }
            }
            if (dealerName != null && dealerName != "") {
                element.dealerName = dealerName;
            }
            if (contact != null && contact != "") {
                element.contact = contact;
            }
            if (phone != null && phone != "") {
                element.phone = phone;
            }
            if (address != null && address != "") {
                element.address = address;
            }
            if (salesman != null && salesman != "") {
                element.salesman = salesman;
            }
            if (applicationTime != null && applicationTime != "") {
                element.applicationTime = applicationTime;
            }
            if (remark != null && remark != "") {
                element.remark = remark;
            }
        }
    });
    return tableData;
}