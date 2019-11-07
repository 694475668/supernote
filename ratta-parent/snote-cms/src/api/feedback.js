import request from '@/utils/request'

//查询问题反馈信息
export function getLists(data) {
    return request({
        url: 'cms/feedback/record/list',
        method: 'POST',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


//修改问题反馈处理状态和备注
export function changeList(id,data) {
    return request({
        url: 'cms/feedback/record/part/' + id,
        method: 'put',
        headers: {
            "x-access-token": window.localStorage.getItem('token')
        },
        data
    })
}


