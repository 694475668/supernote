import request from '@/utils/request'

//查询配件系列(cms系统)
export function getLists(data) {
    return request({
        url: 'cms/part/series/list',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//新增配件系列
export function addList(data) {
    return request({
        url: 'cms/part/series',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改配件系列上线下线
export function changeOn(data,id) {
    return request({
        url: 'cms/part/series/line/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改配件系列序号
export function changeSeq(data,id) {
    return request({
        url: 'cms/part/series/seq/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询单个配件系列信息
export function searchSeq(id) {
    return request({
        url: 'cms/part/series/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//修改配件系列
export function changePart(id,data) {
    return request({
        url: 'cms/part/series/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//删除配件系列
export function delePart(id) {
    return request({
        url: 'cms/part/series/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//推荐

export function recommend() {
    return request({
        url: 'cms/part/series/recommend/box/list',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

