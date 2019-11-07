import request from '@/utils/request'
export function querySeo(data) {
    return request({
        url: 'cms/seo/overall/list',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function addSeo(data) {
    return request({
        url: 'cms/seo/overall',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function updateSeo(data, id) {
    return request({
        url: 'cms/seo/overall/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


export function getSeoById(id) {
    return request({
        url: 'cms/seo/overall/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}


export function delSeo(id) {
    return request({
        url: 'cms/seo/overall/' + id,
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