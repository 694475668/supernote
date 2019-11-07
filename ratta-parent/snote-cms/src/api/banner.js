import request from '@/utils/request'
export function queryBanner(data) {
    return request({
        url: 'cms/manage/homeBanner/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


export function queryRecommend(data) {
    return request({
        url: 'cms/part/series/recommend/box/list',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


export function addBanner(data) {
    return request({
        url: 'cms/manage/homeBanner',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function updateBanner(data) {
    return request({
        url: 'cms/manage/homeBanner',
        method: 'put',
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

export function getBannerById(id) {
    return request({
        url: 'cms/manage/homeBanner/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

export function updateLowerLine(data, id) {
    return request({
        url: 'cms/manage/homeBanner/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function updateSeq(data, id) {
    return request({
        url: 'cms/manage/homeBanner/seq/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function delBanner(id) {
    return request({
        url: 'cms/manage/homeBanner/' + id,
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