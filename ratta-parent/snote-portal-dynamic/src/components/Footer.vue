<template>
    <div id="footer">
        <div class="footer">
            <div style="display: flex; justify-content: space-between;">
                <div class="left" :style="{marginTop: getSize(80)}">
                    <span class="bold" :style="father2">{{$t('message.footer.company')}}</span>
                    <span :style="children">{{$t('message.footer.address')}}</span>
                    <span :style="children">{{$t('message.footer.service')}}</span>
                    <span :style="children">{{$t('message.footer.marker')}}</span>
                    <span :style="children">{{$t('message.footer.phone')}}</span>
                    <div :style="{marginTop: getSize(50), width: getSize(68), height: getSize(66)}">
                        <img width="100%" src="../assets/icon/img_footer_logo.svg" alt="">
                    </div>
                </div>
                <div class="right" :style="{marginTop: getSize(80)}">
                    <div class="one">
                        <span v-for="item in this.$store.state.dictionary" class="bold" :style="father" @click="goPath(item)">{{item.valueCn}}</span>
                        <span class="bold" @click="$router.push('/service')" :style="father">{{$t('message.nav.service')}}</span>
                        <span class="bold" :style="father" @click="$router.push('/blog')">{{$t('message.nav.blog')}}</span>
                    </div>
                    <div class="two" :style="{marginLeft: getSize(80), marginRight: getSize(80)}">
                        <span class="bold" :style="father">{{$t('message.nav.part')}}</span>
                        <span v-for="item in this.$store.state.partsList" :style="children" @click="goPage(item)">{{item.name}}</span>
                    </div>
                    <div class="three">
                        <span class="bold" :style="father" @click="$router.push({path: '/about', query: {type: $store.state.aboutList[0].title}})">{{$t('message.nav.about')}}</span>
                        <span :style="children" v-for="(item, index) in $store.state.aboutList" @click="go2Page(item, index)">{{item.title}}</span>
                    </div>
                </div>
            </div>
            <div class="icon-chat"
                 :style="{ display: 'flex', justifyContent: 'flex-end', position: 'relative',}">
                <div @mouseover="hover(true)" @mouseleave="hover(false)" :style="imgWH"><img width="100%" src="../assets/icon/icon_footer_wechat.svg" alt=""></div>
                <div :style="imgWH"><a target="_blank" href="https://weibo.com/supernote"><img width="100%" src="../assets/icon/icon_footer_sina.svg" alt=""></a></div>
                <div :style="imgWH"><a target="_blank" href="http://twitter.com/rattasupernote"><img width="100%" src="../assets/icon/icon_footer_Twitter.svg" alt=""></a></div>
                <div :style="imgWH"><a target="_blank" href="https://www.instagram.com/supernote_official/"><img width="100%" src="../assets/icon/icon_footer_ins.svg" alt=""></a></div>
                <div :style="imgWH"><a target="_blank" href="https://www.facebook.com/supernote2018/"><img width="100%" src="../assets/icon/icon_footer_facebook.svg" alt=""></a></div>
                <div @mouseover="hover2Status = true" @mouseleave="hover2Status = false" :style="imgWH"><img width="100%" src="../assets/icon/logo_line_nor.svg" alt=""></div>
                <div v-show="hover2Status" class="img_qcord" :style="{position: 'absolute', bottom: getSize(50), right: getSize(3)}">
                    <img :style="{width: getSize(110), height: getSize(110)}" src="../assets/code/line.png" alt="">
                </div>
                <div v-show="hoverStatus" class="img_qcord" :style="{position: 'absolute', bottom: getSize(50), right: getSize(310)}">
                    <img :style="{width: getSize(110), height: getSize(110)}" src="../assets/code/wechat.png" alt="">
                </div>
            </div>
            <div class="copyright" :style="{height: getSize(85), marginTop: getSize(50)}">
                <span :style="{fontSize: getSize(18)}">{{$t('message.copyright')}}</span>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Footer",
        data() {
            return {
                father: {
                    marginBottom: this.getSize(30),
                    fontSize: this.getSize(24),
                    fontWeight: '500',
                },
                father2: {
                    marginBottom: this.getSize(50),
                    fontSize: this.getSize(28),
                    fontWeight: '500',
                },
                children: {
                    marginTop: this.getSize(20),
                    fontSize: this.getSize(18),
                    fontWeight: '400',
                },
                imgWH: {
                    width: this.getSize(44),
                    height: this.getSize(44),
                    marginLeft: this.getSize(30),
                },
                hoverStatus: false,
                hover2Status: false,
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
        methods: {
            getSize(num) {
                return window.innerWidth * num / 1920 + 'px'
            },
            clickAbout(type) {
                this.$router.push({
                    path: '/about',
                    query: {
                        type
                    }
                })
            },
            hover(type){
                this.hoverStatus = type
            },
            goPage(item) {
                this.$router.push({path: '/part', query: {id: item.serialNumber}});

            },
            go2Page(item, index) {
                this.$router.push({path: '/about', query: {type: item.title, index: index}});

            },
            goPath(item) {
                this.$router.push({path: '/product', query: {value: item.value}})
            }
        }
    }
</script>

<style scoped lang="less">
    #footer {
        padding: 0 13%;

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
                display: flex;

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
                border-top: 1px solid #9f9f9f;
                display: flex;
                align-items: center;

                span {
                    font-weight: 400;
                    color: #f5f5f5
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
