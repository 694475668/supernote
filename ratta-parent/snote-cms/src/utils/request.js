import axios from 'axios'
import {
    Message
} from 'element-ui'
import store from '@/store'
import {
    getToken
} from '@/utils/auth'
import {
    removeToken
} from '@/utils/auth'
// create an axios instance
const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
    withCredentials: true, // send cookies when cross-domain requests
    timeout: 5000 // request timeout
    
})

// request interceptor
service.interceptors.request.use(
    config => {
        // do something before request is sent

        if (store.getters.token) {
            // let each request carry token
            // ['X-Token'] is a custom headers key
            // please modify it according to the actual situation
            // config.headers['X-Token'] = getToken()
        }
        return config
    },
    error => {
        // do something with request error
        console.log(error) // for debug
        return Promise.reject(error)
    }
)

// response interceptor
service.interceptors.response.use(
    /**
     * If you want to get http information such as headers or status
     * Please return  response => response
     */

    /**
     * Determine the request status by custom code
     * Here is just an example
     * You can also judge the status by HTTP Status Code
     */
    response => {
        const res = response.data
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
            removeToken()
            window.location.href = '#/login'
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