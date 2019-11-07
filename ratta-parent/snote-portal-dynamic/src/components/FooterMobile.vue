<template>
    <div id="footer-mobile" :style="{height: 'auto', padding: getSize(50)}">
        <div class="footer">
            <div>
                <div class="right">
                    <div v-for="item in this.$store.state.dictionary">
                        <div :style="{width: '100%', height: getSize(70), display: 'flex', justifyContent: 'space-between', alignItems:'center', borderBottom: '1px solid #454545'}">
                            <span :style="{color: '#ffffff', fontSize: getSize(24), cursor: 'pointer', display: 'block'}"
                                  @click="goPath(item)">{{item.valueCn}}</span>
                        </div>
                    </div>
                    
                    <div>
                        <div :style="{width: '100%', height: getSize(70), display: 'flex', justifyContent: 'space-between', alignItems:'center', borderBottom: '1px solid #454545'}">
                            <span :style="{color: '#ffffff', fontSize: getSize(24), width: getSize(200), cursor: 'pointer', display: 'block'}"
                                  @click="goToPage(item)">{{$t("message.nav.part")}}</span>
                            <span v-if="this.$store.state.partsList.length > 0"
                                  :style="{display: 'inline-block', height: getSize(70), lineHeight: getSize(70), paddingLeft: getSize(30)}"
                                  @click="partStatus = !partStatus">
                                <img v-show="partStatus === true" :style="{width: getSize(24), height: getSize(24)}"
                                     src="../assets/mobile/mobile-icon/icon_close.png" alt="">
                                <img v-show="partStatus === false" :style="{width: getSize(24), height: getSize(24)}"
                                     src="../assets/mobile/mobile-icon/icon_open.png" alt="">
                            </span>
                        </div>
                        <div v-if="this.$store.state.partsList.length > 0 && partStatus">
                            <div v-for="val in this.$store.state.partsList"
                                 :style="{color: '#ffffff', fontSize: getSize(24), cursor: 'pointer', width: '100%', height: getSize(60), display: 'flex', alignItems:'center',}"
                                 @click="goPage(3, val, index)">
                                <span>{{val.name || val.title}}</span>
                            </div>
                        </div>
                    </div>

                    <div v-for="item in navList">
                        <div :style="{width: '100%', height: getSize(70), display: 'flex', justifyContent: 'space-between', alignItems:'center', borderBottom: '1px solid #454545'}">
                            <span :style="{color: '#ffffff', fontSize: getSize(24), width: getSize(200), cursor: 'pointer', display: 'block'}"
                                  @click="goToPage(item)">{{item.name}}</span>
                            <span v-if="item.children.length > 0"
                                  :style="{display: 'inline-block', height: getSize(70), lineHeight: getSize(70), paddingLeft: getSize(30)}"
                                  @click="closeOpen(item)">
                                <img v-show="item.status === true" :style="{width: getSize(24), height: getSize(24)}"
                                     src="../assets/mobile/mobile-icon/icon_close.png" alt="">
                                <img v-show="item.status === false" :style="{width: getSize(24), height: getSize(24)}"
                                     src="../assets/mobile/mobile-icon/icon_open.png" alt="">
                            </span>
                        </div>
                        <div v-if="item.children.length > 0 && item.status">
                            <div v-for="val in item.children"
                                 :style="{color: '#ffffff', fontSize: getSize(24), cursor: 'pointer', width: '100%', height: getSize(60), display: 'flex', alignItems:'center',}"
                                 @click="goPage(item.id, val, index)">
                                <span>{{val.name || val.title}}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="icon-chat"
                     :style="{display: 'flex', marginTop: getSize(22), position: 'relative', alignItems: 'center'}">
                    <div :style="{ fontSize: getSize(24), marginRight: getSize(40)}"><span>{{$t('message.contact_us')}}</span>
                    </div>
                    <div @mouseover="hover(true)" @mouseleave="hover(false)" :style="imgWH">
                        <img width="100%" src="../assets/icon/icon_footer_wechat.svg" alt="">
                    </div>
                    <div :style="imgWH"><a target="_blank" href="https://weibo.com/supernote"><img width="100%"
                                                                                                   src="../assets/icon/icon_footer_sina.svg"
                                                                                                   alt=""></a></div>
                    <div :style="imgWH"><a target="_blank" href="http://twitter.com/rattasupernote"><img width="100%"
                                                                                                         src="../assets/icon/icon_footer_Twitter.svg"
                                                                                                         alt=""></a>
                    </div>
                    <div :style="imgWH"><a target="_blank" href="https://www.instagram.com/supernote_official/"><img
                            width="100%" src="../assets/icon/icon_footer_ins.svg" alt=""></a></div>
                    <div :style="imgWH"><a target="_blank" href="https://www.facebook.com/supernote2018/"><img
                            width="100%" src="../assets/icon/icon_footer_facebook.svg" alt=""></a></div>
                    <div @mouseover="hover2Status = true" @mouseleave="hover2Status = false" :style="imgWH"><img
                            width="100%" src="../assets/icon/logo_line_nor.svg" alt=""></div>
                    <div v-show="hover2Status" class="img_qcord"
                         :style="{position: 'absolute', top: '-'+getSize(360), right: getSize(5)}">
                        <img :style="{width: getSize(350), height: getSize(350)}" src="../assets/code/line.png" alt="">
                    </div>
                    <div v-show="hoverStatus" class="img_qcord"
                         :style="{position: 'absolute', top: '-'+getSize(360), left: getSize(95)}">
                        <img :style="{width: getSize(350), height: getSize(350)}" src="../assets/code/wechat.png"
                             alt="">
                    </div>
                </div>
                <div class="left"
                     :style="{marginTop: getSize(40), height: getSize(220), display: 'flex', justifyContent: 'space-between'}">
                    <div>
                        <span :style="father2">{{$t('message.footer.company')}}</span>
                        <span :style="children">{{$t('message.footer.address')}}</span>
                        <span :style="children">{{$t('message.footer.service')}}</span>
                        <span :style="children">{{$t('message.footer.marker')}}</span>
                        <span :style="children">{{$t('message.footer.phone')}}</span>
                    </div>
                    <div :style="{ width: getSize(66), height: getSize(66)}">
                        <img width="100%" src="../assets/icon/img_footer_logo.svg" alt="">
                    </div>
                </div>
            </div>
            <div class="copyright" :style="{marginTop: getSize(50), paddingTop: getSize(30)}">
                <span :style="{fontSize: getSize(18)}">{{$t('message.copyright')}}</span>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'FooterMobile',
        data() {
            return {
                father: {
                    marginBottom: this.getSize(30),
                    fontSize: this.getSize(28),
                    fontWeight: '500',
                },
                father2: {
                    marginBottom: this.getSize(31),
                    fontSize: this.getSize(24),
                    fontWeight: '500',
                },
                children: {
                    marginBottom: this.getSize(10),
                    fontSize: this.getSize(22),
                    color: '#999999'
                },
                imgWH: {
                    width: this.getSize(52),
                    height: this.getSize(52),
                    marginRight: this.getSize(30),
                },
                hoverStatus: false,
                hover2Status: false,
                partStatus: false,
                navList: [
                    {id: 4, name: this.$i18n.t('message.nav.service'), status: false, path: '/service', children: []},
                    {id: 5, name: this.$i18n.t('message.nav.blog'), status: false, path: '/blog', children: []},
                    {
                        id: 6, name: this.$i18n.t('message.nav.about'), status: false, path: '/about', children: this.$store.state.aboutList
                    },
                ],
            }
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
        methods: {
            getSize(num) {
                return window.innerWidth * num / 750 + 'px'
            },
            clickAbout(type) {
                this.$router.push({
                    path: '/about',
                    query: {
                        type
                    }
                })
            },
            closeOpen(item) {
                this.navList.map(ite => {
                    if (item.id === ite.id) {
                        ite.status = !ite.status
                    } else {
                        ite.status = false
                    }
                })
            },
            hover(type) {
                this.hoverStatus = type
            },
            goToPage(item) {
                if(item.id === 6){
                    this.$router.push({path: '/about', query: {type: this.$store.state.aboutList[0].title}})
                } else {
                    this.$router.push(item.path)
                }
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
            }
        }
    }
</script>

<style scoped lang="less">
    #footer-mobile {
        overflow-y: scroll;

        .footer {
            position: relative;

            div {
                span {
                    display: block;
                    text-align: left;
                    color: #ffffff;
                }
            }

            .right {
                div {
                    span {
                        cursor: pointer;
                    }
                }

                .three {
                    .img_qcord {
                        display: none;

                        &:hover {
                            display: block;
                        }
                    }
                }
            }

            .copyright {
                border-top: 1px solid #454545;
                display: flex;
                align-items: center;

                span {
                    font-weight: 400;
                    color: #999999
                }
            }

            .icon-chat {
                div {
                    img {
                        cursor: pointer;
                    }
                }
            }
        }
    }
</style>
