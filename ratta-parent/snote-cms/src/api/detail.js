
import request from '@/utils/request'

//查询固件说明书信息
export function getLists(data) {
    return request({
        url: 'cms/firmware/manual/list',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//新增固件说明书信息
export function addList(data) {
    return request({
        url: 'cms/firmware/manual',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改固件说明书上线下线
export function changeOn(data,id) {
    return request({
        url: 'cms/firmware/manual/line/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//根据语言和类型查询固件说明书列表信息
export function search(data) {
    return request({
        url: 'cms/firmware/manual/list/type',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改固件说明书序号
export function changeSeq(data,id) {
    return request({
        url: 'cms/firmware/manual/seq/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//查询单个固件说明书信息
export function aloneMess(id) {
    return request({
        url: 'cms/firmware/manual/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}

//修改固件说明书信息
export function changeMess(data,id) {
    return request({
        url: 'cms/firmware/manual/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//删除固件说明书信息
export function deleMess(id) {
    return request({
        url: 'cms/firmware/manual/' + id,
        method: 'delete',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}


