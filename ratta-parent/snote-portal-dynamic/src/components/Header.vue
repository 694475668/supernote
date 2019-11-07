<template>
    <div id="header">
        <div id="nav">
            <div class="left" @click="$router.push('/')">
                <img :style="{width: getSize(180), height: getSize(24)}" src="../assets/icon/img_nav_logo.svg" alt="">
            </div>
            <div class="right">
                <!--                <div class="icon"><img src="../assets/icon/icon_user_nor@2x.png" alt=""></div>-->
                <div class="language">
                    <span :class="$i18n.locale === 'zh-CN'?'':'bold'" class="en" @click="change('en')">{{$t("message.language.en")}}</span>
                    <span class="center">|</span>
                    <span :class="$i18n.locale === 'zh-CN'?'bold':''" class="zh" @click="change('zh')">{{$t("message.language.zh")}}</span>
                </div>
                <div class="icon" @click="dialog = !dialog"><img src="../assets/icon/icon_menu_nor.svg" alt=""></div>
            </div>
        </div>
        <el-drawer
                title=""
                custom-class="el-drawer_custom-class"
                :visible.sync="dialog"
                direction="rtl"
                :size.sync="widthDrawer">
            <div :style="{width: '100%', height: getSize(1000), padding: getSize(60),}">
                <div v-for="item in this.$store.state.dictionary" :style="{marginBottom: getSize(20), height: 'auto'}">
                    <span :style="{color: '#ffffff', fontSize: getSize(25), fontWeight: '500', cursor: 'pointer', display: 'block',fontFamily:'PingFang Medium'}"
                          @click="goPath(item)">{{item.valueCn}}</span>
                </div>
                 <div :style="{marginBottom: getSize(20), height: 'auto'}">
                    <span :style="{color: '#ffffff', fontSize: getSize(25), fontWeight: '500', cursor: 'pointer', display: 'block',fontFamily:'PingFang Medium'}">{{$t("message.nav.part")}}</span>
                    <div v-if="this.$store.state.partsList && this.$store.state.partsList.length > 0"
                         :style="{marginTop: getSize(16), height: 'auto', display: 'flex', flexWrap: 'wrap'}">
                        <span v-for="(val, index) in this.$store.state.partsList"
                              :style="{color: '#ffffff', cursor: 'pointer', fontSize: getSize(17), marginLeft: getSize(30), marginBottom: getSize(12)}"
                              @click="goPage(3, val, index)">{{val.title || val.name}}</span>
                    </div>
                </div>
                <div v-for="item in navList" :style="{marginBottom: getSize(20), height: 'auto'}">
                    <span :style="{color: '#ffffff', fontSize: getSize(25), fontWeight: '500', cursor: 'pointer', display: 'block',fontFamily:'PingFang Medium'}"
                          @click="goToPage(item)">{{item.name}}</span>
                    <div v-if="item.children && item.children.length > 0"
                         :style="{marginTop: getSize(16), height: 'auto', display: 'flex', flexWrap: 'wrap'}">
                        <span v-for="(val, index) in item.children"
                              :style="{color: '#ffffff', cursor: 'pointer', fontSize: getSize(17), marginLeft: getSize(30), marginBottom: getSize(12)}"
                              @click="goPage(item.id, val, index)">{{val.title || val.name}}</span>
                    </div>
                </div>
                <div :style="{bottom: getSize(40),}">
                    <div :style="{width: getSize(90), marginBottom: getSize(20), height: '1px', background: '#ffffff'}"></div>
                    <div :style="{height: getSize(40), lineHeight: getSize(40)}">
                        <span style="float: left; display:block;"><img :style="{width: getSize(24), height: getSize(24)}"
                                   src="../assets/icon/icon_menu_phone.svg" alt=""></span>
                        <span class='bold'
                              :style="{float: 'left', fontSize: getSize(24), display: 'block', color: '#ffffff', marginLeft: getSize(12), marginRight: getSize(15)}">400-921-7470</span>
                        <span :style="{float: 'left', color: '#ffffff', display: 'block', fontSize: getSize(14), }">{{$t("message.time")}}</span>
                    </div>
                    <div :style="{color: '#ffffff', clear: 'both', textAlign: 'left', fontSize: getSize(18), marginBottom: getSize(33), marginTop: getSize(69),}">
                        <span>{{$t("message.attention")}}</span>
                    </div>
                    <div class="icon-chat"
                         :style="{display: 'flex', position: 'relative'}">
                        <div @mouseover="hover(true)" @mouseleave="hover(false)" :style="imgWH"><img width="100%"
                                                                                                     src="../assets/icon/icon_footer_wechat.svg"
                                                                                                     alt=""></div>
                        <div :style="imgWH"><a target="_blank" href="https://weibo.com/supernote"><img width="100%"
                                                                                                       src="../assets/icon/icon_footer_sina.svg"
                                                                                                       alt=""></a></div>
                        <div :style="imgWH"><a target="_blank" href="http://twitter.com/rattasupernote"><img
                                width="100%" src="../assets/icon/icon_footer_Twitter.svg" alt=""></a></div>
                        <div :style="imgWH"><a target="_blank" href="https://www.instagram.com/supernote_official/"><img
                                width="100%" src="../assets/icon/icon_footer_ins.svg" alt=""></a></div>
                        <div :style="imgWH"><a target="_blank" href="https://www.facebook.com/supernote2018/"><img
                                width="100%" src="../assets/icon/icon_footer_facebook.svg" alt=""></a></div>
                        <div @mouseover="hover2Status = true" @mouseleave="hover2Status = false" :style="imgWH"><img
                                width="100%" src="../assets/icon/logo_line_nor.svg" alt=""></div>
                        <div v-show="hover2Status" class="img_qcord"
                             :style="{position: 'absolute', bottom: getSize(50), right: getSize(30)}">
                            <img :style="{width: getSize(110), height: getSize(110)}" src="../assets/code/line.png"
                                 alt="">
                        </div>
                        <div v-show="hoverStatus" class="img_qcord"
                             :style="{position: 'absolute', bottom: getSize(50), left: '-'+getSize(10)}">
                            <img :style="{width: getSize(110), height: getSize(110)}" src="../assets/code/wechat.png"
                                 alt="">
                        </div>
                    </div>
                </div>
            </div>
        </el-drawer>
    </div>
</template>
<script>

    export default {
        name: "Header",
        data() {
            return {
                dialog: false,
                widthDrawer: this.getSize(548),
                navList: [
                    {id: 4, name: this.$i18n.t("message.nav.service"), path: "/service", children: []},
                    {id: 5, name: this.$i18n.t("message.nav.blog"), path: "/blog", children: []},
                    {
                        id: 6,
                        name: this.$i18n.t("message.nav.about"),
                        path: "/about",
                        children: this.$store.state.aboutList
                    }
                ],
                imgWH: {
                    width: this.getSize(44),
                    height: this.getSize(44),
                    marginRight: this.getSize(30)
                },
                hoverStatus: false,
                hover2Status: false,
            };
        },
        inject: ["reload"],
        created() {
            this.$store.dispatch('parts', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en'})
            this.$store.dispatch('about', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en', title: ''})
            this.$store.dispatch('dictionary', {name: 'PRODUCT_TYPE',})
        },
        computed: {
            data() {
                return this.$store.state.partsList, this.$store.state.aboutList, this.$store.state.dictionary
            }
        },
        updated() {
            this.navList.map(item => {
                if (item.id === 3) {
                    item.children = this.$store.state.partsList
                } else if (item.id === 6) {
                    item.children = this.$store.state.aboutList
                }
            })
        },
        mounted() {
            document.querySelector(".el-drawer__close-btn").innerHTML = "";
            if (this.$i18n.locale === "zh-CN") {
                document.querySelector(".zh").style.opacity = 1;
                document.querySelector(".en").style.opacity = 0.5;
            } else {
                document.querySelector(".zh").style.opacity = 0.5;
                document.querySelector(".en").style.opacity = 1;
            }
        },
        methods: {
            getSize(num) {
                return window.innerWidth * num / 1920 + "px";
            },
            hover(type) {
                this.hoverStatus = type
            },
            change(lan) {
                if (lan === "zh") {
                    localStorage.setItem("language", "zh-CN");
                    this.$i18n.locale = 'zh-CN'
                    document.querySelector(".zh").style.opacity = 1;
                    document.querySelector(".en").style.opacity = 0.5;
                } else if (lan === "en") {
                    this.$i18n.locale = 'en-US'
                    localStorage.setItem("language", "en-US");
                    document.querySelector(".zh").style.opacity = 0.5;
                    document.querySelector(".en").style.opacity = 1;
                }
                this.reload()
                if(window.location.href.indexOf('blog/detail') > 0){
                    this.$router.push('/blog')
                }

            },
            goToPage(item) {
                if(item.id === 6){
                    this.$router.push({path: '/about', query: {type: this.$store.state.aboutList[0].title}})
                } else {
                    this.$router.push(item.path)
                }
                this.dialog = false

            },
            goPage(id, item, index) {
                console.log(index)
                if (id === 3) {
                    this.$router.push({path: '/part', query: {id: item.serialNumber}}); //serialNumber
                } else {
                    this.$router.push({path: '/about', query: {type: item.title, index: index}});
                }
                this.dialog = false

            },
            goPath(item) {
                this.$router.push({path: '/product', query: {value: item.value}})
                this.dialog = false
            }
        }
    };
</script>
<style lang="less">
    #header {
        height: 6.675rem;
        /*position: absolute;*/
        z-index: 1000;
        width: 100%;
        background: #020202;

        #nav {
            background: #020202;
            margin: 0 13%;
            height: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;

            .left {
                cursor: pointer;
            }

            .icon {
                width: 1.8rem;
                height: 1.8rem;

                img {
                    width: 100%
                }
            }

            .right {
                display: flex;
                justify-content: flex-end;

                .language {
                    color: #ffffff;
                    font-size: 1.6rem;
                    margin: 0 3rem;

                    span {
                        opacity: 0.5;
                    }

                    span.en, span.zh {
                        cursor: pointer;
                    }

                    .center {
                        padding: 0 1.5rem;
                    }
                }

                div.icon {
                    cursor: pointer;
                }
            }
        }
    }

    .el-drawer__body {
        flex: unset;
        overflow: scroll
    }

    .el-dialog__wrapper, .v-modal {
        top: 6.675rem !important;
    }

    .el-drawer_custom-class {
        top: 6.675rem !important;
        position: fixed !important
    }

    .el-drawer {
        background-color: #111111 !important;
    }

    .el-drawer__header {
        margin: 0 !important;
        padding: 0 !important;
        display: none !important;
    }

    .footerMes {
        position: absolute;
    }
</style>
