<template>
    <div id="Detail" :key="$route.query.id">
        <section :style="{background: width ===750 ? '#ffffff' : '#f7f7f7', paddingBottom: getSize(80), paddingLeft: width === 750 ? getSize(50) : '13%', paddingRight: width === 750 ? getSize(50) : '13%',}">
            <article :style="{width: width=== 750? '100%' : getSize(880), paddingTop: getSize(40)}">
                <div :style="{marginBottom: getSize(14), textAlign: 'left'}">
                    <span class="bold" :style="{color: '#131313', fontWeight: '600', fontSize: width=== 750? getSize(24) : getSize(24)}">{{this.$store.state.blogDetail.title}}</span>
                </div>
                <div class="message" :style="{marginBottom: getSize(32)}">
                    <div style="display: flex; align-items: center">
                        <span :style="{fontSize: getSize(14), color: '#333333'}">{{this.$store.state.blogDetail.author}}</span>
                        <span :style="{marginLeft: getSize(42), fontSize: getSize(14), color: '#717379'}">{{this.$store.state.blogDetail.updateTime}}</span>
                    </div>
                    <div v-if="width===1920" style="display: flex; ">
                        <div :style="countStyle">
                            <img :style="imgStyle" src="../assets/icon/icon_look_nor.svg" alt="">
                            <span :style="{fontSize:  width===750?getSize(24) : getSize(12),color:'#717379'}">{{this.$store.state.blogDetail.viewCount}}</span>
                        </div>
                        <div :style="countStyle">
                            <img :style="imgStyle" src="../assets/icon/icon_comment_nor.svg" alt="">
                            <span :style="{fontSize:  width===750?getSize(24) : getSize(12),color:'#717379'}">{{this.$store.state.blogDetail.commentsCount}}</span>
                        </div>
                        <div :style="countStyle">
                            <img :style="imgStyle" src="../assets/icon/icon_nor.svg" alt="">
                            <span :style="{fontSize:  width===750?getSize(24) : getSize(12),color:'#717379'}">{{this.$store.state.blogDetail.thumbsCount}}</span>
                        </div>
                    </div>
                </div>
                <div class="content ql-snow ql-editor" :style="{width: width === 750 ? '100%' : getSize(880), height: 'auto'}" v-html="this.$store.state.blogDetail.content">{{this.$store.state.blogDetail.content}}</div>
                <div v-if="width === 1920" style="text-align: center; cursor: pointer" @click="!praiseStatus?praise2():null">
                    <img v-if="!praiseStatus" :style="thumbs" src="../assets/icon/btn_dianzan_nor.svg" alt="">
                    <img v-if="praiseStatus" :style="thumbs" src="../assets/icon/btn_dianzan_sel.svg" alt="">
                </div>
                <div v-if="width===750">
                    <span v-if="this.$store.state.blogDetail.recommendBlogList && this.$store.state.blogDetail.recommendBlogList.length > 0" :style="{fontWeight: '600', fontSize: getSize(32), display: 'block', color: '#000000' }">{{$t('message.recommend')}}</span>
                    <SwiperMoreMobile :type="type" :slidesPerView="1" :status="2" :list="this.$store.state.blogDetail.recommendBlogList"/>
                </div>
                <div v-if="width === 750" style="position: fixed; justify-content: space-between; top: 47%; z-index: 1000; display: flex;" :style="{width: getSize(650)}">
                    <div @click="commentMobile = true" :style="{width: getSize(98), height: getSize(98), lineHeight: getSize(98), textAlign: 'center', background: '#ffffff', left: 0, border: '1px solid #e5e5e5', borderRadius: '50%'}">
                        <img width="70%" src="../assets/icon/icon_comment_nor.svg" alt="">
                    </div>
                    <div :style="{width: getSize(98), height: getSize(98)}"  @click="praiseStatus? null:praise2">
                        <img width="100%" v-if="!praiseStatus" src="../assets/icon/btn_dianzan_nor.svg" alt="">
                        <img width="100%" v-if="praiseStatus" src="../assets/icon/btn_dianzan_sel.svg" alt="">
                    </div>
                </div>
                <div class="comment">
                    <div v-if="width===1920" style="position: relative">
                        <div :style="{paddingLeft: getSize(20), position: 'relative', paddingRight: getSize(20), paddingTop: getSize(40), paddingBottom: getSize(20)}">
                            <el-input
                                    rows="3"
                                    v-model="commentData.content"
                                    maxlength="200"
                                    type="textarea"
                                    :style="{width: width === 750 ? '100%' : getSize(840)+'!important',}"
                                    :placeholder="$t('message.comment.say')"></el-input>
                            <span :style="{fontSize: getSize(16), color: '#717379', position: 'absolute', bottom: getSize(25), right: getSize(35)}">200</span>
                        </div>
                        <div v-if="addComStatus" :style="{position: 'absolute', background: '#ffffff', top: getSize(50), left: getSize(150), zIndex: 1000, height: getSize(148), lineHeight: getSize(148), width: getSize(540), textAlign: 'center', color: '#090720', fontSize: getSize(18), boxShadow: '0px 0px 7px 0px rgba(231,231,231,1)'}">
                            <span>{{$t('message.comment.msg')}}</span>
                        </div>
                        <div @click="blogComment" :style="{textAlign: 'right', cursor: 'pointer', marginRight: getSize(20), color: '#C9A060', fontSize: getSize(16),}">
                            <span style=" border-bottom: 1px solid #C9A060">{{$t('message.comment.peo')}}</span>
                        </div>
                    </div>
                    <div v-if="width===1920" class="commentAll" :style="{paddingTop: getSize(32)}">
                        <div v-if="this.$store.state.commentTotal > 0" :style="{height: getSize(66), fontSize: getSize(20), color:'#090720', paddingLeft: getSize(20), display: 'flex', alignItems: 'center', justifyContent: 'flex-start', borderTop: '1px solid #ECECEA', }">
                            <span>{{$t('message.comment.all')}}</span>
                            <span :style="{color: '#878789', paddingLeft: getSize(25)}">{{this.$store.state.commentTotal}}</span>
                        </div>
                        <div v-if="this.$store.state.commentTotal < 1" :style="{width: '100%', fontSize: getSize(14), height: getSize(100), display: 'flex', alignItems: 'center', justifyContent: 'center'}">
                            <span>{{$t('message.firstPeople')}}</span>
                        </div>
                        <div class="listAll" :style="{paddingLeft: getSize(35), paddingRight: getSize(35)}">
                            <div class="listCom" :style="{paddingTop: getSize(20), paddingBottom: getSize(20),}"
                                 v-for="item in this.$store.state.blogCommentList">
                                <div :style="comStyle">
                                    <span class="medium" :style="{marginRight: getSize(32)}">{{item.userName}}</span>
                                    <span style="color: #717379">{{item.updateTime}}</span>
                                </div>
                                <div :style="comStyle" style="text-align: justify; text-justify: newspaper; word-break: break-all;">
                                    <span :style="{lineHeight: 1.75}">{{item.content}}</span>
                                </div>
                            </div>
                        </div>
                        <div v-if="this.$store.state.commentTotal > 100" :style="{paddingLeft: getSize(35), paddingTop: getSize(30), color: '#C9A060', fontSize: getSize(16), paddingBottom: getSize(40)}">
                            <span>{{$t('message.comment.more')}}</span>
                        </div>
                    </div>
                    <div v-if="width===750" class="commentAll" :style="{paddingTop: getSize(32)}">
                        <div :style="{height: getSize(66), fontSize: getSize(20), color:'#090720', display: 'flex', alignItems: 'center', justifyContent: 'flex-start',}">
                            <span :style="{color: '#878789',}">{{this.$store.state.commentTotal}}</span>
                            <span>{{$t('message.comment.mobile')}}</span>
                        </div>
                        <div class="listAll" :style="{paddingLeft: getSize(35), paddingRight: getSize(35)}">
                            <div class="listCom" :style="{paddingTop: getSize(20), paddingBottom: getSize(20),}"
                                 v-for="item in this.$store.state.blogCommentList">
                                <div :style="comStyle">
                                    <span :style="{fontWeight: '600', marginRight: getSize(32)}">{{item.userName}}</span>
                                    <span style="color: #717379">{{item.updateTime}}</span>
                                </div>
                                <div :style="comStyle" style="text-align: justify; text-justify: newspaper; word-break: break-all;">
                                    <span :style="{lineHeight: 1.75}">{{item.content}}</span>
                                </div>
                            </div>
                        </div>
                        <div v-if="this.$store.state.commentTotal > 100" :style="{paddingLeft: getSize(35), paddingTop: getSize(30), color: '#C9A060', fontSize: getSize(16), paddingBottom: getSize(40)}">
                            <span>{{$t('message.comment.more')}}</span>
                        </div>

                    </div>
                </div>
                <div class="commentMobile" v-if="commentMobile">
                    <el-input
                            v-model="commentData.content"
                            :placeholder="$t('message.service.search')"
                            class="search"
                            @keyup.enter.native="blogComment"
                            :style="{width: '90%', marginLeft:'5%', border: 0,}">
                    </el-input>
                </div>
            </article>
            <aside v-if="width === 1920" :style="{width: getSize(420), paddingTop: getSize(78), textAlign: 'left'}">
                <span v-if="this.$store.state.blogDetail.recommendBlogList && this.$store.state.blogDetail.recommendBlogList.length > 0" :style="{fontWeight: '600', fontSize: getSize(32), paddingBottom: getSize(30), display: 'block', color: '#000000' }">{{$t('message.recommend')}}</span>
                <ListItem v-for="item in this.$store.state.blogDetail.recommendBlogList"
                          :item="item"
                          @click="blogDetail(item)"
                          :style="{ marginBottom: getSize(80), cursor: 'pointer' }"/>
            </aside>
        </section>
    </div>
</template>

<script>
    import ListItem from '@/components/ListItem.vue'
    import SwiperMoreMobile from "@/components/SwiperMoreMobile.vue";
    import { blogComment, praise } from '../../config/api'
    export default {
        components: {
            ListItem,
            SwiperMoreMobile
        },
        name: 'Detail',
        data() {
            return {
                addComStatus: false,
                praiseStatus: false,
                commentMobile: false,
                comStyle: {
                    fontSize: this.getSize(14),
                    color: '#333333',
                    marginTop: this.getSize(10),
                },
                countStyle: {
                    fontSize: this.getSize(20),
                    display: 'flex',
                    alignItems: 'center',
                    marginRight: this.getSize(20)
                },
                imgStyle: {
                    width: this.getSize(25),
                    height: this.getSize(25),
                    marginRight: this.getSize(8)
                },
                thumbs: {
                    marginTop: this.getSize(47),
                    marginBottom: this.getSize(49),
                    width: this.getSize(114),
                    height: this.getSize(114),
                },
                type: 'bullets',
                commentList: [],
                width: null,
                dataVal: {
                    language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',
                    pageNo: 0,
                    pageSize: 0,
                    sortField: '',
                    sortRules: '',
                    id: this.$route.query.id ? this.$route.query.id : null
                },
                commentData: {
                    blogId: this.$route.query.id,
                    content: ''
                }
            }
        },
        created() {
            this.$store.dispatch('blogDetail', this.dataVal)
            this.$store.dispatch('blogCommentList', this.dataVal)
        },
        watch: {
            '$route'(to,from){
                console.log(to)
                this.dataVal.id = to.query.id
                this.$store.dispatch('blogDetail', this.dataVal)
                this.$store.dispatch('blogCommentList', this.dataVal)
                this.praiseStatus = false
            },
        },
        computed: {
            data() {
                return this.$store.state.blogDetail, this.$store.state.blogCommentList, this.$store.state.commentTotal, this.$store.state.praise
            }
        },
        mounted() {
            window.scrollTo(0, 0);
            if (this.list && this.list.length > 0) {
                this.total = this.list.length
            }
            if (window.innerWidth < 961) {
                this.width = 750
            } else {
                this.width = 1920
            }
        },
        updated() {
            if (window.innerWidth > 960) {
                if(this.$store.state.blogDetail){
                    // document.querySelector('p.ql-align-center img').style.width = '70% !important'

                }
            }
        },
        methods: {
            getSize(num) {
                if (window.innerWidth < 961) {
                    return window.innerWidth * num / 750 + 'px'
                } else {
                    return window.innerWidth * num / 1920 + 'px'
                }
            },
            blogComment () {
                if(this.commentData.content){
                    blogComment(this.commentData).then(res=>{
                        if(res.data.success){
                            this.addComStatus = true
                            this.commentMobile = false
                            this.commentData.content = ''
                            setTimeout(()=>{
                                this.addComStatus = false
                            }, 1000)
                        }
                    })
                }
            },
            blogDetail(item){
                this.dataVal.id = item.id
                this.$store.dispatch('blogDetail', this.dataVal)
                this.$store.dispatch('blogCommentList', this.dataVal)
            },
            praise2() {
                praise({id: this.dataVal.id}).then(res=>{
                    if(res.data.success){
                        this.praiseStatus = true
                    }
                })
            }

        },
    }
</script>

<style lang="less">
    #Detail {
        section {
            display: flex;
            justify-content: space-between;

            article {
                .message {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                }
                .content {
                    overflow: hidden;
                    word-break: break-word;
                    p {
                        img {
                            width: 100%
                        }
                    }
                }
                .comment {
                    background: #ffffff;
                    .commentAll {
                        .listAll {
                            .listCom {
                                text-align: left;
                                border-bottom: 1px solid #ECECEA;
                                &:last-child {
                                    border: 0
                                }
                            }
                        }
                    }
                }

                .commentMobile {
                    position: fixed;
                    bottom: 0;
                    width: 100%;
                    left: 0;
                    z-index: 10000;
                    background: #F4F4F4;
                }
            }


            .list-items {
                display: flex;
                flex-wrap: wrap;
                align-content: flex-start;

                .listItem {
                    &:nth-child(3n) {
                        margin-right: 0 !important;
                    }
                }
            }

        }
        .el-input__inner {
            width: 100% !important;
            height: 60px !important;
            border-radius: 0;
            padding-left: 15px;
        }

        .el-textarea__inner {
            border-radius: 0;
            background: #f7f7f7;
            resize: none
        }
        .el-textarea__inner::-webkit-input-placeholder {
            color: #717379;
        }
        .el-textarea__inner:-moz-placeholder {
            color: #717379;
        }
        .el-textarea__inner:-ms-input-placeholder {
            color: #717379;
        }
        .el-textarea__inner:focus {
            border-color: #E4E3E0
        }

        .footer {
            background: #111111
        }
        .commentMobile .search .el-input__inner {
            background: #ececec !important;
        }
    }


</style>
