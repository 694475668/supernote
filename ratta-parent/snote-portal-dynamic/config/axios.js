import {
    baseUrl, //引入baseUrl
} from "../config/env";
import axios from 'axios';
axios.defaults.timeout = 10000; //设置请求时间
axios.defaults.baseURL = baseUrl;//设置默认接口地址
/**
 * 封装get方法
 * @param url
 * @param data
 * @returns {Promise}
 */
export function get(url,params={}){
    return new Promise((resolve,reject) => {
        axios.get(baseUrl+url,{
            params:params
        })
            .then(response => {
                resolve(response.data);
            })
            .catch(err => {
                reject(err)
            })
    })
}
/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function post(url,data = {}){
    return new Promise((resolve,reject) => {
        axios.post(baseUrl+url,data)
            .then(response => {
                resolve(response.data);
            },err => {
                reject(err)
            })
    })
}

//因此默认axios的接口地址，所以直接写后面的接口名字就行
// this.$get("/posts").then((res)=>{
//     console.log(res)
// })
