import request from '@/utils/request'

//查询关于页数据接口
export function queryAboutAll(data) {
    return request({
        url: '/cms/about/all',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//上线、下线关于页数据接口
export function updateAboutLine(data, id) {
    return request({
        url: '/cms/about/line/' + id,
        method: 'PUT',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//添加关于页数据接口
export function addAbout(data) {
    return request({
        url: '/cms/about',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改关于页数据接口
export function updateAbout(data, id) {
    return request({
        url: '/cms/about/' + id,
        method: 'PUT',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//修改关于页数据序号
export function updateSeq(data, id) {
    return request({
        url: '/cms/about/seq/' + id,
        method: 'PUT',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//图片上传接口
export function upload(data) {
    return request({
        url: 'cms/picture/upload',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

//删除产品页详情接口
export function deleAbouts(id) {
    return request({
        url: '/cms/about/' + id,
        method: 'DELETE',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        }
    })
}