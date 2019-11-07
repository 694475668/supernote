import request from '@/utils/request'

// 根据参数查询设备日志信息接口
export function searchEquipmentLogList(data) {
    return request({
        url: 'equipment/log',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

// 删除日志信息
export function deleteEquipmentLog(data) {
    return request({
        url: 'equipment/log/delete',
        method: 'delete',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

// 下载请求地址
export function download(data) {
    return request({
        url: 'equipment/log/download',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}
// 下载完成后更新掉下载状态
export function updateIsDownload(data) {
    return request({
        url: 'equipment/log/update/status',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

// 根据id获取日志信息
export function getEquipmentLog(id) {
    return request({
        url: 'equipment/log/' + id,
        method: 'get',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
    })
}

// 添加备注信息
export function addRemark(data) {
    return request({
        url: 'equipment/log/add/remark',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}

// 审阅
export function passed(data) {
    return request({
        url: 'equipment/log/passed',
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}