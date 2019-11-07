import request from '@/utils/request'

//设备信息
export function getInfomationList(data) {
    return request({
        url: 'equipment/query/user/equipment',
        method: 'post',
        headers: {
            "Content-Type": 'application/json',
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}