<template>
    <div id="header-mobile">
        <div id="nav" :style="{marginLeft: getSize(40), marginRight: getSize(48)}">
            <div class="left" @click="$router.push('/')">
                <img :style="{width: getSize(180), height: getSize(24)}" src="../assets/icon/img_nav_logo.svg" alt="">
            </div>
            <div class="right">
                <!--                <div class="icon"><img src="../assets/icon/icon_user_nor@2x.png" alt=""></div>-->
                <div class="icon" @click="dialogShow">
                    <img v-if="!dialog" src="../assets/icon/icon_menu_nor.svg" alt="">
                    <img v-if="dialog" src="../assets/mobile/mobile-icon/nav-close@2x.png" alt="">
                </div>
            </div>
        </div>
        <el-drawer
                title=""
                :visible.sync="dialog"
                direction="rtl"
                :size.sync="widthDrawer">
            <div :style="{width: '100%', height: '100%', paddingLeft: getSize(60), paddingRight: getSize(60), paddingBottom: getSize(40)}">
                <div class="language" style="color: #ffffff; text-align: right;" :style="{fontSize: getSize(28), marginTop: getSize(30), marginBottom: getSize(20)}">
                    <span class="en bold" :style="{ cursor: 'pointer', opacity: this.$i18n.locale === 'zh-CN'? 0.5 : 1}"
                          @click="change('en')">{{$t("message.language.en")}}</span>
                    <span class="center" style=" padding: 0 1.5rem; opacity: 0.5">|</span>
                    <span class="zh bold" :style="{ cursor: 'pointer', opacity: this.$i18n.locale === 'zh-CN'? 1 : 0.5}"
                          @click="change('zh')">{{$t("message.language.zh")}}</span>
                </div>
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
                    <span class="bold" :style="{color: '#ffffff', fontSize: getSize(25), fontWeight: '500', cursor: 'pointer', display: 'block',}"
                          @click="goToPage(item)">{{item.name}}</span>
                    <div v-if="navList.length > 0"
                         :style="{marginTop: getSize(16), height: 'auto', display: 'flex', flexWrap: 'wrap'}">
                        <span v-for="(val, index) in item.children"
                              :style="{color: '#ffffff', cursor: 'pointer', fontSize: getSize(18), marginLeft: getSize(30), marginBottom: getSize(12)}"
                              @click="goPage(item.id, val, index)">{{val.name || val.title}}</span>
                    </div>
                </div>
                <div class="footerMes" :style="{bottom: getSize(40)}">
                    <div :style="{width: getSize(90), marginBottom: getSize(20), height: '1px', background: '#ffffff'}"></div>
                    <div :style="{display: 'flex', alignItems: 'center', flexWrap: 'wrap'}">
                        <span><img :style="{width: getSize(24), height: getSize(24)}"
                                   src="../assets/icon/icon_menu_phone.svg" alt=""></span>
                        <span :style="{fontSize: getSize(28), fontWeight: '500', color: '#ffffff', marginLeft: getSize(12), marginRight: getSize(20)}">400-921-7470</span>
                        <span :style="{color: '#ffffff', fontSize: getSize(14), }">{{$t("message.time")}}</span>
                    </div>
                    <div :style="{color: '#ffffff', textAlign: 'left', fontSize: getSize(18), marginBottom: getSize(33), marginTop: getSize(69),}">
                        <span>{{$t("message.attention")}}</span>
                    </div>
                    <div class="icon-chat" :style="{display: 'flex'}">
                        <div @mouseover="hover(true)" @mouseleave="hover(false)" :style="imgWH"><img width="100%"
                                                                                                     src="../assets/icon/icon_footer_wechat.svg"
                                                                                                     alt=""></div>

                        <div :style="imgWH"><a target="_blank" href="https://weibo.com/supernote"><img width="100%"
                                                                                                       src="../assets/icon/icon_footer_sina.svg"
                                                                                                       alt=""></a></div>
                        <div :style="imgWH"><a target="_blank" href="http://twitter.com/rattasupernote"><img width="100%"
                                                                                                      src="../assets/icon/icon_footer_Twitter.svg"
                                                                                                      alt=""></a></div>
                        <div :style="imgWH"><a target="_blank" href="https://www.instagram.com/supernote_official/"><img
                                width="100%" src="../assets/icon/icon_footer_ins.svg" alt=""></a></div>
                        <div :style="imgWH"><a target="_blank" href="https://www.facebook.com/supernote2018/"><img
                                width="100%" src="../assets/icon/icon_footer_facebook.svg" alt=""></a></div>
                        <div @mouseover="hover2Status = true" @mouseleave="hover2Status = false" :style="imgWH"><img width="100%" src="../assets/icon/logo_line_nor.svg" alt=""></div>
                        <div v-show="hover2Status" class="img_qcord" :style="{position: 'absolute', bottom: getSize(50), left: getSize(115)}">
                            <img :style="{width: getSize(350), height: getSize(350)}" src="../assets/code/line.png" alt="">
                        </div>
                        <div v-show="hoverStatus" class="img_qcord"
                             :style="{position: 'absolute', bottom: getSize(50), left: '-'+getSize(50)}">
                            <img :style="{width: getSize(350), height: getSize(350)}" src="../assets/code/wechat.png"
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
        name: "HeaderMobile",
        data() {
            return {
                dialog: false,
                widthDrawer: this.getSize(650),
                navList: [
                    {id: 4, name: this.$i18n.t("message.nav.service"), path: "/service", children: []},
                    {id: 5, name: this.$i18n.t("message.nav.blog"), path: "/blog", children: []},
                    {id: 6, name: this.$i18n.t("message.nav.about"), path: "/about", children: this.$store.state.aboutList}
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
        created() {
            this.$store.dispatch('parts', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en'})
            this.$store.dispatch('about', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',})
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
            if (localStorage.getItem('language') === "zh-CN") {
                document.querySelector(".zh").style.opacity = 1;
                document.querySelector(".en").style.opacity = 0.5;
            } else {
                document.querySelector(".zh").style.opacity = 0.5;
                document.querySelector(".en").style.opacity = 1;
            }
        },
        inject: ["reload"],
        methods: {
            getSize(num) {
                return window.innerWidth * num / 750 + "px";
            },
            hover(type) {
                this.hoverStatus = type
            },
            change(lan) {
                if (lan === "zh") {
                    this.$i18n.locale = "zh-CN";
                    localStorage.setItem("language", "zh-CN");
                    document.querySelector(".zh").style.opacity = 1;
                    document.querySelector(".en").style.opacity = 0.5;
                } else if (lan === "en") {
                    this.$i18n.locale = "en-US";
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
                if (id === 3) {
                    this.$router.push({path: '/part', query: {id: item.serialNumber}});
                } else {
                    this.$router.push({path: '/about', query: {type: item.title, index: index}});
                }
                this.dialog = false

            },
            goPath(item) {
                this.$router.push({path: '/product', query: {value: item.value}})
                this.dialog = false
            },
            dialogShow() {
                this.dialog = !this.dialog;
            }
        }
    };
</script>
<style lang="less">
    #header-mobile {
        height: 5rem;
        /*position: absolute;*/
        z-index: 1000;
        width: 100%;
        background: #020202;

        #nav {
            background: #020202;
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

                div.icon {
                    cursor: pointer;
                }
            }
        }


    }

    .el-dialog__wrapper, .v-modal {
        top: 5rem !important;
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
