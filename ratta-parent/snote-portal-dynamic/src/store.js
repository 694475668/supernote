import Vue from "vue";
import Vuex from "vuex";
import {
    bannerList,
    honorList,
    blogList,
    blogDetail,
    blogComment,
    blogCommentList,
    partsList,
    partList,
    partDetail,
    parDetail,
    partProList,
    about,
    proDetail,
    proList,
    faqList,
    customize,
    seoAll,
    search,
    firmware,
    dictionary,
    praise
} from '../config/api'
Vue.use(Vuex);
export default new Vuex.Store({
    state: {
        bannerList: [],
        honorList: [],
        productList: [],
        partList: [],
        blogList: [],
        blogTotal: 0,
        blogDetail: {},
        blogComment: [],
        blogCommentList: [],
        commentTotal: 0,
        partsList: [],
        partDetail: {},
        partType: [],
        partProList: {},
        aboutList: [],
        proDetail: [],
        proContent: {},
        partData: {},
        product: {},
        faqList: [],
        customizeList: [],
        seoAll: {},
        searchList: [],
        searchTotal: 0,
        firmware: [],
        firmTotal: 0,
        dictionary: [],
        praise: {},
        parDetail: {},
        metaInfo: {
            title: '',
            keywords: '',
            description: ''
        }
    },
    mutations: {
        banner(state, res) {
            state.bannerList = []
            state.bannerList = res.data.voList
            // console.log('banner', res.data.voList)
        },
        honor(state, res) {
            state.honorList = res.data.voList
            // console.log('honor', res.data.voList)
        },
        blog(state, res) {
            state.blogList = res.data.voList
            state.blogTotal = res.data.total
            // console.log('blog', res.data.voList)
        },
        blogDetail(state, res) {
            state.blogDetail = res.data
            state.metaInfo.title = res.data.seoTitle
            state.metaInfo.keywords = res.data.seoKeywords
            state.metaInfo.description = res.data.seoDescription
            console.log('blogDetail', res.data)
        },
        blogComment(state, res) {
            state.blogComment = res.data.voList
            // console.log('blogComment', res)
        },
        blogCommentList(state, res) {
            state.blogCommentList = res.data.voList
            state.commentTotal = res.data.total
            // console.log('blogCommentList', res)
        },
        part(state, res) {
            state.partList = res.data.voList
            // console.log('partList', res.data.voList)
        },
        parDetail(state, res) {
            if(res.data.voT.mutiUrl){
                res.data.voT.mutiUrl = res.data.voT.mutiUrl.split(',')
            } else {
                res.data.voT.mutiUrl = []
            }
            if(res.data.voT.productList && res.data.voT.productList.length > 0){
                res.data.voT.productList[0].status = true
                state.partType = res.data.voT.productList[0].productType
            }
            state.parDetail = res.data.voT
            state.metaInfo.title = res.data.voT.seoTitle
            state.metaInfo.keywords = res.data.voT.seoKeywords
            state.metaInfo.description = res.data.voT.seoDescription
            console.log('parDetail', res.data)
        },
        parts(state, res) {
            state.partsList = res.data.voList
            // console.log('partsList', res.data.voList)
        },
        partDetail(state, res) {
            state.partDetail = res.data.voList
            // console.log('partDetail', res.data)
        },
        partProList(state, res) {
            let obj = {}
            if(res.data.voT.penPartList.length > 0){
                res.data.voT.penPartList[0].status = true
                obj.penColor = res.data.voT.penPartList[0].productNumber
                obj.penImg = res.data.voT.penPartList[0].matchUrl
            }
            if(res.data.voT.coverPartList.length > 0){
                res.data.voT.coverPartList[0].status = true
                obj.coverColor = res.data.voT.coverPartList[0].productNumber
                obj.coverImg = res.data.voT.coverPartList[0].matchUrl
            }
            state.partProList = res.data.voT
            state.partData = obj
            // console.log('partProList', res.data.voT)
        },
        about(state, res) {
            state.aboutList = res.data.voList
            // console.log('about', res.data.voList)
        },
        proDetail(state, res) {
            let obj = {}
            if(res.data.voList && res.data.voList.length > 0){
                res.data.voList[0].status = true
                obj = res.data.voList[0].productDescriptionInfo
            }
            state.proDetail = res.data.voList
            state.proContent = obj
            // console.log('proDetail', res.data)
        },
        product(state, res) {
            state.product = res.data
            state.metaInfo.title = res.data.seoTitle
            state.metaInfo.keywords = res.data.seoKeywords
            state.metaInfo.description = res.data.seoDescription
            console.log('product', res.data)
        },
        faq(state, res) {
            let title = ''
            res.data.voList.map(item=>{
                if(item.status){
                    title = item.title
                }
            })
            state.faqList = res.data.voList
            // console.log('faq', res.data)
        },
        customize(state, res) {
            state.customizeList = res.data
            // console.log('customize', res.data)
        },
        seoAll(state, res) {
            state.seoAll = res.data.voT
            state.metaInfo.title = res.data.voT.seoTitle
            state.metaInfo.keywords = res.data.voT.seoKeywords
            state.metaInfo.description = res.data.voT.seoDescription
            // console.log('seoAll', res.data)
        },
        search(state, res) {
            state.searchList = res.data.voList
            state.searchTotal = res.data.total
            // console.log('searchList', res.data)
        },
        firmware(state, res) {
            state.firmware = res.data.voList
            state.firmTotal = res.data.total
            // console.log('firmware', res.data)
        },
        dictionary(state, res) {
            state.dictionary = res.data.dictionaryVOList
            // console.log('dictionary', res.data)
        },
        praise(state, res) {
            state.praise = res.data
            // console.log('praise', res.data)
        }

    },
    actions: {
        banner(context, data) {
            bannerList(data).then(res => {
                context.commit('banner', res)
            })
        },
        honor(context, data) {
            honorList(data).then(res => {
                context.commit('honor', res)
            })
        },
        blog(context, data) {
            blogList(data).then(res => {
                context.commit('blog', res)
            })
        },
        blogDetail(context, data) {
            blogDetail(data).then(res => {
                context.commit('blogDetail', res)
            })
        },
        blogComment(context, data) {
            blogComment(data).then(res => {
                context.commit('blogComment', res)
            })
        },
        blogCommentList(context, data) {
            blogCommentList(data).then(res => {
                context.commit('blogCommentList', res)
            })
        },
        part(context, data) {
            partList(data).then(res => {
                context.commit('part', res)
            })
        },
        parts(context, data) {
            partsList(data).then(res => {
                context.commit('parts', res)
            })
        },
        partDetail(context, data) {
            partDetail(data).then(res => {
                context.commit('partDetail', res)
            })
        },
        partProList(context, data) {
            partProList(data).then(res => {
                context.commit('partProList', res)
            })
        },
        about(context, data) {
            about(data).then(res => {
                if(data.title){
                    res.data.voList.map(item=>{
                        if(item.title === data.title){
                            item.status = true
                        }
                    })
                } else {
                    res.data.voList[0].status = true
                }
                context.commit('about', res)
            })
        },
        proDetail(context, data) {
            proDetail(data).then(res => {
                context.commit('proDetail', res)
            })
        },
        product(context, data) {
            proList(data).then(res => {
                context.commit('product', res)
            })
        },
        faq(context, data) {
            faqList(data).then(res => {
                res.data.voList[0].status = true
                if(data.title){
                    res.data.voList.map(item=>{
                        if(item.title === data.title){
                            res.data.voList[0].status = false
                            item.status = true
                        }
                    })
                }
                context.commit('faq', res)
            })
        },
        customize(context, data) {
            customize(data).then(res => {
                context.commit('customize', res)
            })
        },
        seoAll(context, data) {
            seoAll(data).then(res => {
                context.commit('seoAll', res)
            })
        },
        search(context, data) {
            search(data).then(res => {
                context.commit('search', res)
            })
        },
        firmware(context, data) {
            firmware(data).then(res => {
                context.commit('firmware', res)
            })
        },
        dictionary(context, data) {
            dictionary(data).then(res => {
                context.commit('dictionary', res)
            })
        },
        praise(context, data) {
            praise(data).then(res => {
                context.commit('praise', res)
            })
        },
        parDetail(context, data) {
            parDetail(data).then(res => {
                context.commit('parDetail', res)
            })
        },
    }
});
