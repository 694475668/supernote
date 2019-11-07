import request from '@/utils/request'

//根据id查询数据字典信息接口
export function getDiclist(id) {
    return request({
        url: 'system/cms/base/dictionary/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//添加数据字典信息接口
export function addDiclist(data) {
    return request({
        url: 'system/cms/base/dictionary/add',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改数据字典信息接口
export function amendDiclist(id, data) {
    return request({
        url: 'system/cms/base/dictionary/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//删除数据字典信息接口
export function deleteDiclist(id) {
    return request({
        url: 'system/cms/base/dictionary/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}


//根据参数模糊查询接口
export function searchDiclist(data) {
    return request({
        url: 'system/cms/base/dictionary',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//根据数据名称类型查询接口
export function typeDiclist() {
    return request({
        url: 'system/cms/base/dictionary',
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}


/**
 * 前端刷新公共方法
 */
export function dataManipulation(tableData, rowData, remark, valueCn, valueEn, updateUser, updateTime) {
    tableData.map(element => {
        //如果是单选的直接用if判断id是否相等就可以了,如果是复选框的就需要嵌套for循环判断
        if (element.id == rowData.id) {
            if (remark != null && remark != "") {
                element.remark = remark;
            }
            if (valueCn != null && valueCn != "") {
                element.valueCn = valueCn;
            }
            if (valueEn != null && valueEn != "") {
                element.valueEn = valueEn;
            }
            if (updateUser != null && updateUser != "") {
                element.opUser = updateUser;
            }
            if (updateTime != null && updateTime != "") {
                element.opTime = updateTime;
            }
        }
    });
    return tableData;
}