import request from '@/utils/request'
export function queryBlog(data) {
    return request({
        url: 'cms/manage/blog/query',
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

export function addBlog(data) {
    return request({
        url: 'cms/manage/blog',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function updateBlog(data) {
    return request({
        url: 'cms/manage/blog',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function getBlogById(id) {
    return request({
        url: 'cms/manage/blog/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

export function updateLowerLine(data, id) {
    return request({
        url: 'cms/manage/blog/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function updateSeq(data, id) {
    return request({
        url: 'cms/manage/blog/seq/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

export function delBlog(id) {
    return request({
        url: 'cms/manage/blog/' + id,
        method: 'delete',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}


export function updateRecommend(data) {
    return request({
        url: 'cms/manage/blog/recommend',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
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