import request from '@/utils/request'

//查询固件任务信息
export function getFixpoint(data) {
    return request({
        url: 'system/business/app/fixpoint/query',
        method: 'post',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}