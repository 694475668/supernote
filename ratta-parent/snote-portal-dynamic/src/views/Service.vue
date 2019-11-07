<template>
    <div id="service">
        <div class="banner flex"
             :style="{width: '100%', ition: 'relative'}">
            <img width="100%" :src="bannerImg" alt="">
            <div style="position: absolute; text-align: center">
                <span v-if="width > 750" class="searchTitle bold" :style="{fontSize: getSize(36),}">{{$t('message.service.please')}}{{$t('message.service.indexTitle')}}</span>
                <span v-if="width === 750" class="searchTitle bold" :style="{fontSize: getSize(36),}">{{$t('message.service.please')}}<br>{{$t('message.service.indexTitle')}}</span>
                <el-input
                        v-model="search"
                        :placeholder="$t('message.service.search')"
                        @keyup.enter.native="handleIconClick"
                        @keyup.enter="handleIconClick"
                        class="search"
                        :style="{width: getSize(600), lineHeight: 2.5, marginTop: getSize(50), opacity: 0.75}">
                    <div :style="{cursor: 'pointer', marginRight: getSize(10)}" slot="suffix"
                         @click="handleIconClick">
                        <img :style="{width: width===750?getSize(40):getSize(25), height: width===750?getSize(40):getSize(25)}"
                             src="../assets/service/icon_sousuo_nor@2x.png" alt="">
                    </div>
                </el-input>
            </div>
        </div>
        <section :style="{padding: width === 750 ? 0 : '0 13%', background: width === 750 ? '#ffffff' : '#ffffff' }">
            <div class="cloud"
                 :style="{height: width === 750 ? getSize(440) : getSize(280), position: 'relative', justifyContent: 'center', color: '#000000', display: 'flex', alignItems: 'center', marginBottom: getSize(80), marginTop: getSize(80),}">
                <img v-if="$i18n.locale === 'zh-CN'" width="100%" :src="cloudImg" alt="">
                <img v-if="$i18n.locale === 'en-US' && width === 1920" width="100%" src="../assets/service/banner_yunfuwu.jpg" alt="">
                <img v-if="$i18n.locale === 'en-US' && width === 750" width="100%" src="../assets/mobile/service/banner_2.jpg" alt="">
                <b :style="{cursor: 'pointer', position: 'absolute', fontSize: getSize(36)}"><a class="bold" target="_blank" style="color: #000000; text-decoration: none" href="https://cloud.supernote.com.cn/#/login">{{$t('message.service.title')}}</a></b>
            </div>
            <div class="common">
                <div :style="{marginBottom: getSize(41),}">
                    <span class="bold" :style="{fontSize: getSize(32)}">{{$t('message.service.commonService')}}</span>
                </div>
                <div class="flex" v-show="width > 750">
                    <span class="borderBottom"
                          :style="{width: getSize(50), marginBottom: getSize(75)}"></span>
                </div>
                <div class="serviceList"
                     :style="{ paddingLeft: width === 750 ? getSize(50) : 0, paddingRight: width === 750 ? getSize(50) : 0, flexWrap: width === 750 ? 'wrap' : 'unset', paddingBottom: getSize(77)}">
                    <div class="serviceItem" v-for="item in serviceList"
                         :style="{width: width === 750 ? getSize(320) : getSize(275), marginBottom: width === 750 ? getSize(30) :0, height: width === 750 ? getSize(240) : getSize(275), background: width === 750 ? '#f7f7f7' : '#f7f7f7'}"
                         @click="item.path ? $router.push(item.path) : width===750 ? $router.push({path: '/form', query: {title: $t('message.service.question.title'),showSort: true, question: true}}) : visibleDialog()">
                        <span :style="{marginTop: getSize(46),padding: getSize(16)}">
                            <img :style="imgWH" :src="item.img" alt="">
                        </span>
                        <span class="bold" :style="{fontSize: getSize(24),paddingTop: getSize(7), paddingBottom: getSize(11)}">{{item.title}}</span>
                    </div>
                </div>
            </div>
        </section>
        <Dialog @sortShow="dialog" :showSort="true" v-if="dialogVisible" :title="$t('message.service.question.title')"/>
    </div>
</template>

<script>
    import Dialog from '@/components/Dialog.vue'

    export default {
        components: {
            Dialog,
        },
        name: 'Service',
        data() {
            return {
                search: '',
                name: '',
                phone: '',
                serviceList: [
                    {
                        id: 1,
                        title: this.$i18n.t('message.service.faq.title'),
                        img: require('../assets/service/icon_faq.png'),
                        path: '/service/faq'
                    },
                    {
                        id: 2,
                        title: this.$i18n.t('message.service.bug.title'),
                        img: require('../assets/service/icon_guzhang.png'),
                        path: '/service/trouble'
                    },
                    {
                        id: 3,
                        title: this.$i18n.t('message.service.instruction.title'),
                        img: require('../assets/service/icon_download.png'),
                        path: '/service/instruction'
                    },
                    {
                        id: 4,
                        title: this.$i18n.t('message.service.firm.title'),
                        img: require('../assets/service/icon_shumingshu.png'),
                        path: '/service/firmware'
                    },
                    {
                        id: 5,
                        title: this.$i18n.t('message.service.question.title'),
                        img: require('../assets/service/icon_feedback.png')
                    },
                ],
                imgWH: {
                    width: this.getSize(80),
                    height: this.getSize(80),
                },
                bannerImg: require('../assets/service/banner_fuwu.png'),
                cloudImg: require('../assets/service/banner_yunfuwu.png'),
                dialogVisible: false,
                width: null,
            }
        },
        created() {
            this.$store.dispatch('seoAll', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en', pageSerial: '1'})
        },
        mounted() {
            window.scrollTo(0, 0);

            if (window.innerWidth < 961) {
                this.bannerImg = require('../assets/mobile/service/banner1.png')
                this.cloudImg = require('../assets/mobile/service/banner_2.png')
                this.width = 750

            } else {
                this.bannerImg = require('../assets/service/banner_fuwu.png')
                this.cloudImg = require('../assets/service/banner_yunfuwu.png')
                this.width = 1920

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
            handleIconClick() {
                console.log(this.search)
                if(this.search){
                    this.$router.push({path: '/service/search', query: {name: this.search}})
                }
            },
            dialog(e) {
                this.dialogVisible = e
            },
            visibleDialog() {
                this.dialogVisible = !this.dialogVisible
            }
        },
    }
</script>

<style lang="less">
    #service {
        // background: #fff;
        .banner {
            width: 100%;

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

            .cloud {
                div {
                    width: 100%;
                    color: #333333;

                    &:nth-child(1) {
                        span {
                            font-weight: 500;
                        }
                    }
                }
            }

            .common {
                text-align: center;

                span {
                    display: block;
                    color: #000000;
                    font-weight: 500;
                }

                .borderBottom {
                    background: #282528;
                    height: 4px;
                }

                .serviceList {
                    display: flex !important;
                    justify-content: space-between;

                    .serviceItem {
                        cursor: pointer;
                    }
                }
            }
        }

        .footer {
            background: #111111
        }
    }

    .search .el-input__inner:hover {
        border-color: #ffffff !important;
    }

    .search .el-input__inner {
        border-color: #ffffff !important;
        border-radius: 20px;
        color: #333333;
    }

    .question .el-input__inner {
        background: #F7F7F7 !important;
    }

</style>
