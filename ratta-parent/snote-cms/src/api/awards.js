import request from '@/utils/request'
export function queryAward(data) {
    return request({
        url: 'cms/manage/homeAward/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function upload(data) {
    return request({
        url: 'cms/picture/upload',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data,
        timeout: 3600000
    })
}

export function addAward(data) {
    return request({
        url: 'cms/manage/homeAward',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function updateAward(data) {
    return request({
        url: 'cms/manage/homeAward',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function getAwardById(id) {
    return request({
        url: 'cms/manage/homeAward/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

export function updateLowerLine(data, id) {
    return request({
        url: 'cms/manage/homeAward/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function updateSeq(data, id) {
    return request({
        url: 'cms/manage/homeAward/seq/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function delAward(id) {
    return request({
        url: 'cms/manage/homeAward/' + id,
        method: 'delete',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

/**
 * 前端刷新公共方法
 */
export function dataManipulation(tableData, id, line, updateTime, updateUser) {
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
        }
    });
    return tableData;
}