import request from './request'
import app from './app'
let api = '';
console.log(process.env.NODE_ENV)
if (process.env.NODE_ENV === 'development') {
    api = app.development
} else if (process.env.NODE_ENV === 'test') {
	api = app.test
} else if (process.env.NODE_ENV === 'production') {
    api = app.production
} else if (process.env.NODE_ENV === 'uat') {
    api = app.uat
}

//反馈问题
export function feedback(data) {
    return request({
        url: `${api}/official/cms/feedback/record`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            // "x-access-token": window.localStorage.getItem('token'),
            "withCredentials": true
        },
        data
    })
}

//定制套餐接口
export function loveBuy(data) {
    return request({
        url: `${api}/official/cms/customize`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 关于
 * @param data
 * @returns {language：cn/en}
 */

export function about(data) {
    return request({
        url: `${api}/official/cms/about/list`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 服务搜索
 * @param data
 * @returns { key language pageNo pageSize }
 */

export function search(data) {
    return request({
        url: `${api}/official/cms/search`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 根据语言查询整体SEO信息
 * @param data
 * @returns { language: cn / en }
 */

export function seoAll(data) {
    return request({
        url: `${api}/official/cms/seo/overall/lan`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 产品页套餐定制是否展示查询接口
 * @param data
 * @returns {}
 */

export function customize() {
    return request({
        url: `${api}/official/cms/product/customize`,
        method: 'get',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
    })
}

/**
 * 根据语言和类型查询固件说明书列表信息
 * @param data
 * @returns {language pageNo pageSize type}
 */

export function firmware(data) {
    return request({
        url: `${api}/official/cms/firmware/manual/list/type`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 获取微博列表接口
 * @param data
 * @returns {recommendFlag line language pageNo pageSize sortField sortRules titleId}
 */

export function blogList(data) {
    return request({
        url: `${api}/official/cms/manage/blog/website`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 官网根据博客id的查询所属评论
 * @param data
 * @returns {audit roofPlacement language pageNo pageSize sortField sortRules titleId}
 */

export function blogCommentList(data) {
    return request({
        url: `${api}/official/cms/manage/comment/website/${data.id}`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 官网添加微博评论接口
 * @param data
 * @returns {blogId content}
 */

export function blogComment(data) {
    return request({
        url: `${api}/official/cms/manage/comment`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 官网通过微博id查询内容以及推荐博客接口
 * @param data
 * @returns {language line pageNo pageSize sortField sortRules type}
 */

export function blogDetail(data) {
    return request({
        url: `${api}/official/cms/manage/blog/${data.id}`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 根据语言和类型查询标题条目列表
 * @param data
 * @returns {language type}
 */

export function faqList(data) {
    return request({
        url: `${api}/official/cms/faq/title/item/list`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 查询产品页banner图接口
 * @param data
 * @returns {language type}
 */

export function proList(data) {
    return request({
        url: `${api}/official/cms/product/banner/one`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 查询产品页详情接口
 * @param data
 * @returns {language type}
 */

export function proDetail(data) {
    return request({
        url: `${api}/official/cms/product/description/list`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 查询配件列表(官网配件页列表)
 * @param data
 * @returns {language}
 */

export function partDetail(data) {
    return request({
        url: `${api}/official/cms/part/out/list/${data.id}`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 查询配件
 * @param data
 * @returns {productType: A5/A6}
 */

export function partProList(data) {
    return request({
        url: `${api}/official/cms/part/customize/list`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 查询配件列表
 * @param data
 * @returns {language}
 */

export function partList(data) {
    return request({
        url: `${api}/official/cms/part/home/list`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 查询配件详情
 * @param data
 * @returns {language}
 */

export function parDetail(data) {
    return request({
        url: `${api}/official/cms/part/series/out/${data.id}`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 查询配件系列列表
 * @param data
 * @returns {language}
 */

export function partsList(data) {
    return request({
        url: `${api}/official/cms/part/series/out/list`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 获取奖项轮播图接口
 * @param data
 * @returns {language pageNo pageSize sortField sortRules}
 */

export function honorList(data) {
    return request({
        url: `${api}/official/cms/manage/homeAward/website`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 获取轮播图接口
 * @param data
 * @returns {language pageNo pageSize sortField sortRules}
 */

export function bannerList(data) {
    return request({
        url: `${api}/official/cms/manage/homeBanner/website`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 查询数据字典接口
 * @param data
 * @returns {name: PRODUCT_TYPE}
 */

export function dictionary(data) {
    return request({
        url: `${api}/official/base/cms/dictionary/param`,
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}

/**
 * 点赞
 * @param data
 * @returns {name: PRODUCT_TYPE}
 */

export function praise(data) {
    return request({
        url: `${api}/official/cms/manage/blog/website/${data.id}`,
        method: 'put',
        headers: {
            'Content-Type': 'application/json',
            "withCredentials": true
        },
        data
    })
}
