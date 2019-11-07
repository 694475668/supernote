<template>
    <div id="about">
        <div class="banner flex"
             :style="{height: getSize(460), background: width===750?'url('+banner2+')':'url('+banner+')',}">
            <div>
                <span class="searchTitle bold"  :style="{textAlign: 'center', fontSize: getSize(36),display:'inline-block',paddingBottom:width===750? getSize(80) :''}">{{$t('message.about.title')}}</span>
            </div>
        </div>
        <section :style="{ background: width===750 ? '#ffffff' : '#ffffff', paddingLeft: width === 750 ? getSize(49) : getSize(250), paddingTop: getSize(80), paddingRight: width === 750 ? getSize(49) : getSize(250),}">
            <div class="content"
                 :style="{display: width === 750 ? 'block' : 'flex', justifyContent: width === 750 ? 'unset':'space-between',marginTop: width === 750 ? getSize(10) : getSize(97)}">
                <div class="left leftNav" v-if="width > 750" :style="{width: getSize(250)}">
                    <div v-for="(item, index) in this.$store.state.aboutList" :style="{marginBottom: item.status ? getSize(35) : getSize(40)}"
                         @click="tabChage(item)">
                        <span class="bold" :style="{fontWeight: item.title === type ? '500' : 'normal', fontSize: item.title === type ? getSize(24) : getSize(18)}">{{item.title}}</span>
                        <div v-if="item.title === type"
                             :style="{width: getSize(160), borderBottom: '1px solid #000000', marginTop: getSize(15)}">
                            <div :style="{width: getSize(40), height: '2px', background: '#000000'}"></div>
                        </div>
                    </div>
                </div>
                <div class="right" :style="{width: width === 750 ? 'auto' : getSize(1020)}">
                    <div v-for="(item, index) in this.$store.state.aboutList" :id="'about'+index" :style="{ marginBottom: width === 750 ? getSize(70) :getSize(100)}">
                        <div :style="{marginBottom: getSize(24)}">
                            <span class="title bold" :style="{fontSize: getSize(32)}">{{item.title}}</span>
                        </div>
                        <div class="flex" v-if="width > 750">
                          <span class="borderBottom"
                                :style="{width: getSize(50), marginBottom: getSize(40)}"></span>
                        </div>
                        <div class="ql-snow ql-editor" v-if="width > 750" style="color: #000000; height: auto" v-html="item.aboutInfo.descriptionPc">{{item.aboutInfo.descriptionPc}}</div>
                        <div class="ql-snow ql-editor" v-if="width === 750" style="color: #000000; height: auto" v-html="item.aboutInfo.descriptionMobile">{{item.aboutInfo.descriptionMobile}}</div>
                    </div>
                    <div class="contact" :style="{marginBottom: getSize(100)}">
                        <div :style="{position: 'relative', display: 'flex', paddingLeft: width===750? 0 : getSize(98), paddingRight:  width===750? 0 : getSize(98), justifyContent:'space-between', marginTop: width===750?getSize(68):getSize(34)}">
                            <div v-for="item in contact2List" :style="{width: getSize(90),}">
                                <div v-if="item.id === 1 || item.id === 6" @mouseover="item.id === 1 ? hoverStatus=true:item.id === 6 ? hover2Status=true: '' " @mouseleave="item.id === 1 ? hoverStatus=false:item.id === 6 ? hover2Status=false: ''" >
                                    <img :style="{display: 'inline-block', width: getSize(50), height: getSize(50),}"
                                         :src="item.img" alt="">
                                    <span :style="{display: 'block', fontSize: getSize(16)}">{{item.title}}</span>
                                </div>
                                <a v-if="item.id === 2 || item.id === 3 || item.id === 4 || item.id === 5" target="_blank" :href="item.path">
                                    <img :style="{display: 'inline-block', width: getSize(50), height: getSize(50),}" :src="item.img" alt="">
                                    <span :style="{display: 'block', fontSize: getSize(16)}">{{item.title}}</span>
                                </a>
                            </div>
                            <div v-show="hoverStatus" class="img_qcord"
                                 :style="{position: 'absolute', bottom:width===750? getSize(60):getSize(80), left: width===750?getSize(0):getSize(110)}">
                                <img :style="{width: getSize(150), height: getSize(150)}"
                                     src="../assets/code/wechat.png" alt="">
                            </div>
                            <div v-show="hover2Status" class="img_qcord" :style="{position: 'absolute', bottom: width===750? getSize(60):getSize(80), right:width===750?getSize(0):getSize(115)}">
                                <img :style="{width: getSize(150), height: getSize(150)}" src="../assets/code/line.png" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>

<script>

    export default {
        components: {},
        name: 'About',
        data() {
            return {
                search: '',
                // img: require('../assets/about/pc/bg_join_us.png'),
                imgWH: {
                    width: this.getSize(68),
                    height: this.getSize(68),
                },
                font: {
                    fontSize: this.getSize(14),
                    textAlign: this.width === 750 ? 'left !important' : 'center'
                },
                fontDesc: {
                    fontSize: this.getSize(18),
                    display: 'inline-block',
                    paddingTop: this.getSize(30),
                    paddingBottom: this.getSize(25),
                    marginBottom: this.getSize(20),
                    background: '#f7f7f7',
                    width: '100%',
                    textAlign: this.width === 750 ? 'left !important' : 'center'
                },
                contactList: [
                    {
                        id: 1,
                        title: '客服热线',
                        img: require('../assets/about/icon_kefu.svg'),
                        pm: '400-921-7470'
                    },
                    {
                        id: 2,
                        title: '售后⽀持',
                        img: require('../assets/about/icon_service.svg'),
                        pm: 'service@supernote.com'
                    },
                    {
                        id: 3,
                        title: '市场推⼴/品牌合作',
                        img: require('../assets/about/icon_cooperation.svg'),
                        pm: 'market@supernote.com'
                    },
                    {
                        id: 4,
                        title: '国际业务',
                        img: require('../assets/about/icon_international.svg'),
                        pm: 'richard.huang@supernote.com'
                    },
                ],
                contact4List: [
                    {
                        id: 1,
                        title: '客服热线',
                        img: require('../assets/mobile/about/icon_kefu.png'),
                        pm: '400-921-7470'
                    },
                    {
                        id: 2,
                        title: '服务/咨询',
                        img: require('../assets/mobile/about/icon_shou.png'),
                        pm: 'service@supernote.com'
                    },
                    {
                        id: 3,
                        title: '市场推⼴/品牌合作',
                        img: require('../assets/mobile/about/icon_hezuo.png'),
                        pm: 'market@supernote.com'
                    },
                    {
                        id: 4,
                        title: '国际业务',
                        img: require('../assets/mobile/about/icon_guoji.png'),
                        pm: 'global_business@supernote.com'
                    },
                ],
                contact2List: [
                    {
                        id: 1,
                        title: this.$i18n.t('message.wechat'),
                        img: require('../assets/about/logo_weixin_nor.svg'),
                    },
                    {
                        id: 2,
                        title: this.$i18n.t('message.sina'),
                        img: require('../assets/about/logo_weibo_nor.svg'),
                        path: 'https://weibo.com/supernote'
                    },
                    {
                        id: 3,
                        title: 'Twitter',
                        img: require('../assets/about/logo_twitter_nor.svg'),
                        path: 'http://twitter.com/rattasupernote'
                    },
                    {
                        id: 4,
                        title: 'Instgram',
                        img: require('../assets/about/logo_ins_nor.svg'),
                        path: 'https://www.instagram.com/supernote_official/'
                    },
                    {
                        id: 5,
                        title: 'Facebook',
                        img: require('../assets/about/logo_facebook_nor.svg'),
                        path: 'https://www.facebook.com/supernote2018/'
                    },
                    {
                        id: 6,
                        title: 'LINE',
                        img: require('../assets/about/logo_line_nor.svg'),
                    },
                ],
                contact3List: [
                    {
                        id: 1,
                        img: require('../assets/mobile/mobile-icon/icon_footer_wechat.png'),
                    },
                    {
                        id: 2,
                        img: require('../assets/mobile/mobile-icon/icon_footer_sina.png'),
                        path: 'https://weibo.com/supernote'
                    },
                    {
                        id: 3,
                        img: require('../assets/mobile/mobile-icon/icon_footer_Twitter.png'),
                        path: 'http://twitter.com/rattasupernote'
                    },
                    {
                        id: 4,
                        img: require('../assets/mobile/mobile-icon/icon_footer_ins.png'),
                        path: 'https://www.instagram.com/supernote_official/'
                    },
                    {
                        id: 5,
                        img: require('../assets/mobile/mobile-icon/icon_footer_facebook.png'),
                        path: 'https://www.facebook.com/supernote2018/'
                    },
                    {
                        id: 6,
                        img: require('../assets/mobile/mobile-icon/logo_line_nor.png'),
                    },
                ],

                dialogVisible: false,
                hoverStatus: false,
                hover2Status: false,
                width: null,
                key: 0,
                banner: require('../assets/about/pc/banner_guanyu.jpg'),
                banner2: require('../assets/about/mobile/banner_guanyu.jpg'),
                type: this.$route.query.type
            }
        },
        created() {
            this.$store.dispatch('about', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en'})
            this.$store.dispatch('seoAll', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en', pageSerial: '5'})

        },
        computed: {
            data() {
                return this.$store.state.aboutList
            }
        },
        mounted() {

            window.scrollTo(0, 0);
            window.addEventListener("scroll", this.handleScroll);
            window.addEventListener("scroll", this.onScroll);
            console.log(window.innerWidth)
            if (window.innerWidth < 961) {
                this.width = 750
                this.contact2List = this.contact3List
            } else {
                this.width = 1920
                this.contact2List = this.contact2List
            }
            setTimeout(()=>{
                this.type = this.$route.query.type
                this.goAnchor('#about'+this.$route.query.index, 180)
            }, 200)
        },
        watch: {
            '$route'(to){
                if(to.query.type){
                    this.type = to.query.type
                    this.goAnchor('#about'+ to.query.index, 100)
                } else {
                    this.type = this.$store.state.aboutList[0].title
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
            tabChage(item) {
                this.$store.state.aboutList.map((i, index) => {
                    this.type = item.title
                    if (item.title === i.title) {
                        i.status = true
                        this.goAnchor('#about'+ index, 100)
                    } else {
                        i.status = false
                    }
                })
                console.log(this.$store.state.aboutList)
            },
            goAnchor(selector, size) {
                let anchor = this.$el.querySelector(selector)
                let total = anchor.offsetTop

                // 平滑滚动，时长500ms，每10ms一跳，共50跳
                // 获取当前滚动条与窗体顶部的距离
                let distance = document.documentElement.scrollTop || document.body.scrollTop
                // 计算每一小段的距离
                let step = total / 50;
                (function smoothDown() {
                    if (distance < total) {
                        distance += step
                        // 移动一小段
                        document.body.scrollTop = distance
                        document.documentElement.scrollTop = distance
                        // 设定每一次跳动的时间间隔为10ms
                        setTimeout(smoothDown, 10)
                    } else {
                        // 限制滚动停止时的距离
                        document.body.scrollTop = total - size
                        document.documentElement.scrollTop = total - size
                    }
                })()
            },
            handleScroll () {
                let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
                if(this.width > 750) {
                    if (scrollTop > 400) {
                        document.querySelector(".leftNav").style.position = "fixed";
                        document.querySelector(".leftNav").style.left = "13%";
                        document.querySelector(".leftNav").style.top = "13%";
                        document.querySelector(".content").style.justifyContent = "flex-end";
                    } else {
                        document.querySelector(".leftNav").style.position = "unset";
                        document.querySelector(".content").style.justifyContent = "space-between";
                    }
                }

            },
            hover(type){
                this.hoverStatus = type
            },
            // onScroll() {
            //     let scrolled = document.documentElement.scrollTop || document.body.scrollTop
            //     if(scrolled < 100){
            //         this.key = 0
            //     }
            //     console.log(this.key)
            //     this.$store.state.aboutList.map((item, index) =>{
            //         let about = document.querySelector('#about' + this.key).offsetTop
            //         if(scrolled == about){
            //             this.type = item.title
            //             this.key++
            //         }
            //     })
            //     // 586、1063分别为第二个和第三个锚点对应的距离
            //
            // }
        },
        destroyed () {
            window.removeEventListener("scroll", this.handleScroll);
            window.removeEventListener("scroll", this.onScroll);
        }
    }
</script>

<style lang="less">
    #about {
        .banner {
            width: 100%;
            background-size: cover !important;

            .searchTitle {
                color: #ffffff;
                font-weight: 500
            }

            span {
                display: block;
            }
        }

        .flex {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        section {
            overflow-y: auto;

            .content {

                .left {
                    div {
                        cursor: pointer;
                        text-align: left;
                        color: #000000;
                    }
                }

                span {
                    color: #000000;
                }

                .right {
                    .flex {
                        display: flex;
                        align-items: center;
                        justify-content: center;

                        .borderBottom {
                            background: #282528;
                            height: 4px;
                        }
                    }
                    [id^="about"]	{
                        p {
                            img{
                                width: 100%
                            }
                        }
                    }
                    div {
                        text-align: center;

                        .title {
                            font-weight: 500;
                        }

                        span {
                            color: #000000;
                        }

                        .join-us {
                            background-size: cover !important
                        }
                    }
                }
            }

        }

        .footer {
            background: #111111
        }
    }


</style>
