import axios from 'axios'
import {
    Message
} from 'element-ui'
// create an axios instance
const service = axios.create({
    timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
    // config => {
    //     // do something before request is sent
    //     if (store.getters.token) {
    //         // let each request carry token
    //         // ['X-Token'] is a custom headers key
    //         // please modify it according to the actual situation
    //         // config.headers['X-Token'] = getToken()
    //     }
    //     return config
    // },
    // error => {
    //     // do something with request error
    //     console.log(error) // for debug
    //     return Promise.reject(error)
    // }
)

// response interceptor
service.interceptors.response.use(
    response => {
        const res = response
        return res
        
    },
    error => {
        const err = JSON.parse(JSON.stringify(error))
        console.log(err) // for debug
        if (err.response.status === 401) {
            Message({
                message: '登录失效，请重新登录',
                type: 'error',
                duration: 5 * 1000
            })
            window.location.href = '/login'
            window.localStorage.removeItem('token')
        } else {
            Message({
                message: error.message,
                type: 'error',
                duration: 5 * 1000
            })
        }
        return Promise.reject(error)
    }
)

export default service