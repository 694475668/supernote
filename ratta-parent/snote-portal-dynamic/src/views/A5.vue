<template>
    <div id="a5">
        <section id='section' v-if="width===1920">
            <div class="sub-nav" :style="{paddingTop: getSize(24), paddingBottom: getSize(24)}">
                <div>
                    <span class="bold" :style="{fontSize: getSize(24), fontWeight: '500'}">{{this.$store.state.product.title}}</span>
                </div>
                <div class="right">
                    <div class="product-content">
                        <div v-for="item in this.$store.state.proDetail"
                             @click="checkSet(item)"
                             :style="{marginLeft: getSize(30), fontSize: getSize(16), opacity: item.status && checkShow !== ''? 1 : 0.75}">
                            <span :class="item.status && checkShow !== ''? 'medium' : ''">{{item.title}}</span>
                        </div>
                        <div v-if="this.$store.state.customizeList.customizeFlag === 'Y'" @click="checkShow = ''" :style="{marginLeft: getSize(30), fontSize: getSize(16), opacity: checkShow === '' ? 1 : 0.75}">
                            <span :class=" checkShow === '' ? 'medium' : ''">{{this.$i18n.t('message.menu2')}}</span>
                        </div>
                    </div>
                    <a v-if="this.$store.state.product.buttonLink" style="text-decoration: none" :href="this.$store.state.product.buttonLink" target="_blank">
                        <div class="btn"
                             :style="{width: getSize(96), marginLeft: getSize(60), height: getSize(36),borderRadius: getSize(18), fontSize: getSize(16)}">
                            <span>{{this.$store.state.product.buttonName}}</span>
                        </div>
                    </a>
                </div>
            </div>
            <div :style="{paddingLeft: getSize(40), paddingRight: getSize(40)}" class="content">
                <div v-if="checkShow === 0" :style="{width: '100%', marginBottom: getSize(40), position: 'relative'}">
                    <img width="100%" :src="this.$store.state.product.urlPc" alt="">
                    <div :style="{position: 'absolute',bottom: getSize(280), left: getSize(220),}">
                        <div :style="{fontSize: getSize(64), color: '#ffffff'}"><span class="song">{{this.$store.state.product.title}}</span></div>
                        <div :style="{marginTop: getSize(20), width: $i18n.locale === 'en-US'? '100%' : getSize(350), fontSize: getSize(32), color: '#ffffff'}"><span v-html="this.$store.state.product.description">{{this.$store.state.product.description}}</span></div>
                        <div :style="{fontSize: getSize(24), marginTop: getSize(20),  marginBottom: getSize(40), color: '#ffffff', opacity: 0.6}"><span v-html="this.$store.state.product.detail">{{this.$store.state.product.detail}}</span></div>
                        <a v-if="this.$store.state.product.buttonLink" style="text-decoration: none" :href="this.$store.state.product.buttonLink" target="_blank">
                            <div class="buy"
                                 :style="{width: getSize(156), cursor: 'pointer',  height: getSize(54), lineHeight: getSize(54), textAlign: 'center', background: '#C9A060', borderRadius: getSize(27), fontSize: getSize(24)}">
                                <span style="color: #ffffff">{{this.$store.state.product.buttonName}}</span>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="overview" v-if="this.$store.state.proContent.descriptionPc && checkShow !== ''" :style="{width: '100%', overflow: 'hidden'}">
                    <div class="ql-snow ql-editor" :style="{width: '100%', height: 'auto'}" v-html="this.$store.state.proContent.descriptionPc">{{this.$store.state.proContent.descriptionPc}}</div>
                </div>
                <div class="set-menu" v-if="checkShow === ''"
                     :style="{paddingLeft: getSize(155), paddingTop: getSize(80), paddingBottom: getSize(113)}">
                    <div class="left" :style="{width: getSize(720), height: getSize(667), marginRight: getSize(195)}">
                        <div class="note"
                             :style="{width: getSize(353), height: getSize(482), left: getSize(44), bottom: getSize(50), zIndex: '999'}">
                            <img width="100%" :src="this.$store.state.proDetail[0].productPictureUrl" alt="">
                            <img width="100%" v-if="$route.query.value === 'SN-A5' && !this.$store.state.proDetail[0].productPictureUrl" src="../assets/part/content/A5@2x.png" alt="">
                            <img width="100%" v-if="$route.query.value !== 'SN-A5' && !this.$store.state.proDetail[0].productPictureUrl" src="../assets/part/content/A6@2x.png" alt="">
                        </div>
                        <div class="cover"
                             :style="{width: getSize(376), height: getSize(466), left: getSize(225), bottom: getSize(115), zIndex: '100'}">
                            <img :style="{width: '100%',}" :src="this.$store.state.partData.coverImg" alt="">
                        </div>
                        <div class="pen"
                             :style="{width: getSize(53), height: getSize(330), left: getSize(485), bottom: getSize(15), zIndex: '999'}">
                            <img :style="{width: '100%',}" :src="this.$store.state.partData.penImg" alt="">
                        </div>
<!--                        <div :style="{width: getSize(730), height: getSize(251), position: 'absolute', left: getSize(14), bottom: getSize(18),}">-->
<!--                            <img width="100%" src="../assets/product/shadow.png" alt="">-->
<!--                        </div>-->
                    </div>
                    <div class="right">
                        <div :style="{fontSize: getSize(36)}">
                            <span class="bold">{{$t("message.menu")}}</span>
                        </div>
                        <div :style="{fontSize: getSize(28), margin: getSize(15), marginLeft: 0, paddingBottom: getSize(10), color: '#2E292D', textAlign: 'left'}">
                            <span>{{$t('message.checkMsg')}}</span>
                        </div>
                        <div :style="partStyle">
                            <span>{{$t("message.pen")}}</span>
                        </div>
                        <div class="colorStyle"
                             :style="{width: '80%', marginLeft: '-'+getSize(20), height: $i18n.locale === 'zh-CN' ? getSize(180) : getSize(100), minHeight: $i18n.locale === 'zh-CN' ? getSize(180) : getSize(100), marginBottom: getSize(30)}">
                            <div v-for="item in this.$store.state.partProList.penPartList"
                                 @click="checkItem(item, '')"
                                 :style="{width: $i18n.locale === 'zh-CN' ? getSize(80) : getSize(90), flexFlow: 'column', alignItems: 'center', marginBottom: getSize(20), float: 'left'}">
                                <div :style="{margin: '0 auto', width: '40px', height: '40px', borderRadius: '20px', border: item.status ? '1px solid #000000' : '1px solid #ffffff', display: 'flex', alignItems: 'center', justifyContent: 'center', }">
                                    <div :style="{display: 'inline-block', margin: '0 auto', width: '32px', borderRadius: '16px', height: '32px'}">
                                        <img width="100%" :src="item.colourUrl" alt="">
                                    </div>
                                </div>
                                <div v-if="$i18n.locale === 'zh-CN'" :style="{fontSize: getSize(12), marginTop: getSize(10), color: '#2E292D'}">
                                    <span>{{item.colourName}}</span>
                                </div>
                            </div>
                        </div>
                        <div :style="partStyle">
                            <span>{{$t("message.cover")}}</span>
                        </div>
                        <div class="colorStyle"
                             :style="{width: getSize(450), marginLeft: '-'+getSize(20), height: getSize(120), minHeight: getSize(120)}">
                            <div v-for="item in this.$store.state.partProList.coverPartList"
                                 @click="checkItem('', item)"
                                 :style="{width: $i18n.locale === 'zh-CN' ? getSize(80) : getSize(90), flexFlow: 'column', alignItems: 'center', marginBottom: getSize(20), float: 'left'}">
                                <div :style="{margin: '0 auto', width: '40px', height: '40px', borderRadius: '20px', border: item.status ? '1px solid #000000' : '1px solid #ffffff', display: 'flex', alignItems: 'center', justifyContent: 'center', }">
                                    <div :style="{display: 'inline-block', margin: '0 auto', width: '32px', borderRadius: '16px', height: '32px'}">
                                        <img width="100%" :src="item.colourUrl" alt="">
                                    </div>
                                </div>
                                <div v-if="$i18n.locale === 'zh-CN'" :style="{fontSize: getSize(12), marginTop: getSize(10), color: '#2E292D'}">
                                    <span>{{item.colourName}}</span>
                                </div>
                            </div>
                        </div>
                        <div class="buy"
                             @click="visibleDialog()"
                             :style="{width: $i18n.locale === 'zh' ? getSize(156) : getSize(210), cursor: 'pointer', height: getSize(54), marginTop: getSize(20), lineHeight: getSize(54), textAlign: 'center', border: '1px solid #C9A060', borderRadius: getSize(27), fontSize: getSize(24)}">
                            <span style="color: #C9A060">{{$t("message.button.buy2")}}</span>
                        </div>
                    </div>
                </div>
            </div>
            <Dialog @sortShow="dialog" v-if="dialogVisible" :chose="this.$store.state.partData" :title="$t('message.service.question.titleTwo')"/>
        </section>
        <section v-if="width===750">
            <div class="sub-nav" :style="{padding: getSize(30)}">
                <div>
                    <span class="bold"
                          :style="{fontSize: getSize(28), fontWeight: '500'}">{{this.$store.state.product.title && this.$store.state.product.title.replace('Supernote ', '')}}</span>
                </div>
                <div class="right">
                    <div class="product-content">
                        <div v-for="item in this.$store.state.proDetail"
                             @click="checkSet(item)"
                             :style="{marginRight: getSize(20), fontSize: getSize(24), opacity: item.status && checkShow !== ''? 1 : 0.75}">
                            <span :class="item.status && checkShow !== ''?'medium' : ''">{{item.title}}</span>
                        </div>
                        <div v-if="this.$store.state.customizeList.customizeFlag === 'Y'" @click="checkShow = ''" :style="{marginRight: getSize(20), fontSize: getSize(24), opacity: checkShow === '' ? 1 : 0.75}">
                            <span :class=" checkShow === '' ? 'medium' : ''">{{this.$i18n.t('message.menu2')}}</span>
                        </div>
                    </div>
                    <a v-if="this.$store.state.product.buttonLink" style="text-decoration: none" :href="this.$store.state.product.buttonLink" target="_blank">
                        <div style="background: #C9A060; width: 60px; height: 18px; line-height: 18px; text-align: center; border-radius: 9px; color: #ffffff; font-size: 8px">
                            <span>{{this.$store.state.product.buttonName}}</span>
                        </div>
                    </a>
                </div>
            </div>
            <div class="content">
                <div class="overview" v-if="checkShow === 0">
                    <div style="position: relative">
                        <img :src="this.$store.state.product.urlMobile" alt="">
                        <div :style="{position: 'absolute',top: getSize(175), width: '100%', textAlign: 'center'}">
                            <div :style="{fontSize: getSize(48), color: '#ffffff'}"><span class="bold" v-html="$store.state.product.title">{{this.$store.state.product.title}}</span></div>
                            <div :style="{marginTop: getSize(20), display: 'inline-block', width: $i18n.locale === 'en-US' ? '90%' : getSize(260), fontSize: getSize(28), color: '#ffffff'}"><span v-html="$store.state.product.description">{{this.$store.state.product.description}}</span></div>
                        </div>
                    </div>
                </div>
                <div class="overview" v-if="this.$store.state.proContent.descriptionMobile && checkShow !== ''">
                    <div class="ql-snow ql-editor" :style="{width: '100%', height: 'auto'}" v-html="this.$store.state.proContent.descriptionMobile">{{this.$store.state.proContent.descriptionMobile}}</div>
                </div>
                <div class="set-menu" style="display: block" v-if="checkShow === ''">
                    <div :style="{fontSize: getSize(48), paddingTop: getSize(50), fontWeight: '500', color: '#2E292D', textAlign: 'center'}">
                        <span class="bold">{{$t("message.menu2")}}</span>
                    </div>
                    <div :style="{fontSize: getSize(28), margin: getSize(15), paddingBottom: getSize(10), color: '#2E292D', textAlign: 'center'}">
                        <span>{{$t('message.checkMsg')}}</span>
                    </div>

                    <div class="left"
                         :style="{width: getSize(580), height: getSize(600), overflow: 'hidden', margin: '0 auto'}">
                        <div class="note"
                             :style="{width: getSize(353), height: getSize(482), left: getSize(30), bottom: getSize(50), zIndex: '999'}">
                            <img width="100%" :src="this.$store.state.proDetail[0].productPictureUrl" alt="">
                            <img width="100%" v-if="this.$store.state.product.title === 'Supernote A5' && !this.$store.state.proDetail[0].productPictureUrl" src="../assets/part/content/A5@2x.png" alt="">
                            <img width="100%" v-if="this.$store.state.product.title !== 'Supernote A5' && !this.$store.state.proDetail[0].productPictureUrl" src="../assets/part/content/A6@2x.png" alt="">
                        </div>
                        <div class="cover"
                             :style="{width: getSize(376), height: getSize(466), left: getSize(210), bottom: getSize(110), zIndex: '100'}">
                            <img :style="{width: '100%',}" :src="this.$store.state.partData.coverImg" alt="">
                        </div>
                        <div class="pen"
                             :style="{width: getSize(53), height: getSize(330), left: getSize(460), bottom: getSize(5), zIndex: '999'}">
                            <img :style="{width: '100%',}" :src="this.$store.state.partData.penImg" alt="">
                        </div>
                        <!-- <div :style="{width: getSize(730), height: getSize(251), position: 'absolute', left: '-'+getSize(10), bottom: getSize(20),}">
                            <img width="100%" src="../assets/product/shadow.png" alt="">
                        </div> -->
                    </div>
                    <div class="right">

                        <div :style="part2Style">
                            <span class="bold">{{$t("message.pen")}}</span>
                        </div>
                        <div class="colorStyle" :style="{paddingLeft: '10%', paddingRight: '10%'}">
                            <div v-for="item in this.$store.state.partProList.penPartList"
                                 @click="checkItem(item, '')"
                                 :style="{width: $i18n.locale === 'zh-CN' ? getSize(110) : getSize(85), height: $i18n.locale === 'zh-CN' ? getSize(160) : getSize(114), float: 'left'}">
                                <div :style="{margin: '0 auto', width: '28px', height: '28px', borderRadius: '14px', border: item.status ? '1px solid #000000' : '1px solid #ffffff', display: 'flex', alignItems: 'center', justifyContent: 'center', }">
                                    <div :style="{display: 'inline-block', margin: '0 auto', width: '22px', borderRadius: '11px', height: '22px',}">
                                        <img width="100%" :src="item.colourUrl" alt="">
                                    </div>
                                </div>
                                <div v-if="$i18n.locale === 'zh-CN'" :style="{fontSize: getSize(12), marginTop: getSize(10), color: '#2E292D'}">
                                    <span>{{item.colourName}}</span>
                                </div>
                            </div>
                        </div>
                        <div :style="part2Style">
                            <span>{{$t("message.cover")}}</span>
                        </div>
                        <div class="colorStyle" :style="{paddingLeft: '10%', paddingRight: '10%'}">
                            <div v-for="item in this.$store.state.partProList.coverPartList"
                                 @click="checkItem('', item)"
                                 :style="{width: $i18n.locale === 'zh-CN' ? getSize(110) : getSize(85), height: $i18n.locale === 'zh-CN' ? getSize(160) : getSize(114), float: 'left'}">
                                <div :style="{margin: '0 auto', width: '28px', height: '28px', borderRadius: '14px', border: item.status ? '1px solid #000000' : '1px solid #ffffff', display: 'flex', alignItems: 'center', justifyContent: 'center', }">
                                    <div :style="{display: 'inline-block', background: 'url('+item.colourUrl+')', backgroundSize: 'cover', margin: '0 auto', width: '22px', borderRadius: '11px', height: '22px'}"></div>
                                </div>
                                <div v-if="$i18n.locale === 'zh-CN'" :style="{fontSize: getSize(12), marginTop: getSize(10), color: '#2E292D'}">
                                    <span>{{item.colourName}}</span>
                                </div>
                            </div>
                        </div>
                        <div class="buy"
                             @click="$router.push({path: '/form', query: {path: '/a5',chose: JSON.stringify($store.state.partData), title: $t('message.service.question.titleTwo')}})"
                             :style="{width: '62%', height: getSize(54), lineHeight: getSize(54), marginTop: getSize(80), textAlign: 'center', marginLeft: '19%', marginRight: '19%', border: '1px solid #C9A060', borderRadius: getSize(27), fontSize: getSize(24)}">
                            <span style="color: #C9A060">{{$t("message.button.buy2")}}</span>
                        </div>
                        <div :style="{height: getSize(40)}"></div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>

<script>
    // @ is an alias to /src
    import Dialog from '@/components/Dialog.vue'

    export default {
        components: {
            Dialog,
        },
        name: "A5",
        data() {
            return {
                height: null,
                contentList: [
                    {id: 1, name: "概述", status: true},
                    {id: 2, name: this.$i18n.t('message.menu2'), status: false},
                    {id: 3, name: "技术规格", status: false}
                ],
                chose: {
                    coverColor: '黑色',
                    penColor: '森林绿',
                    product: 'a5'
                },
                partStyle: {
                    fontSize: this.getSize(24),
                    fontWeight: "500",
                    marginTop: this.getSize(55),
                    marginBottom: this.getSize(10),
                },
                part2Style: {
                    fontSize: this.getSize(24),
                    fontWeight: "500",
                    marginTop: this.getSize(40),
                    marginBottom: this.getSize(20),
                    paddingLeft: "12%",
                    paddingRight: "12%"
                },
                checkShow: 0,
                dialogVisible: false,
                width: null,
                widthNow: null,
                content: null,
                contentMobile: null,
                dataVal: {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en', type: this.$route.query.value},
                dataVal2: {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en', productType: this.$route.query.value},
            }
        },
        created() {
            this.$store.dispatch('product', this.dataVal)
            this.$store.dispatch('proDetail', this.dataVal)
            this.$store.dispatch('partProList', this.dataVal2)
            this.$store.dispatch('customize')
        },
        computed: {
            data() {
                return this.$store.state.product, this.$store.state.proDetail, this.$store.state.proContent, this.$store.state.partProList, this.$store.state.partData, this.$store.state.customizeList
            }
        },
        watch: {
            '$route'(to,from){
                console.log(to)
                this.dataVal.type = to.query.value
                this.dataVal2.productType = to.query.value
                this.$store.dispatch('product', this.dataVal)
                this.$store.dispatch('proDetail', this.dataVal)
                this.$store.dispatch('partProList', this.dataVal2)
                this.$store.dispatch('customize')
            },
        },
        updated(){
            if(this.$route.query.value){
                this.$store.state.partData.product = this.$route.query.value
            }
        },
        mounted() {
            if(this.$route.query.checkStatus){
                this.checkShow = ''
            }
            this.height = window.innerHeight;
            this.widthNow = window.innerWidth
            if (window.innerWidth < 961) {
                this.width = 750;

            } else {
                this.width = 1920;
            }
            window.scrollTo(0, 0);

        },
        methods: {
            getSize(num) {
                if (window.innerWidth < 961) {
                    return window.innerWidth * num / 750 + "px";
                } else {
                    return window.innerWidth * num / 1920 + "px";
                }
            },
            dialog(e) {
                this.dialogVisible = e
            },
            visibleDialog() {
                this.dialogVisible = !this.dialogVisible
            },
            checkItem(pen, cover) {
                if (pen) {
                    this.$store.state.partData.penImg = pen.matchUrl;
                    this.$store.state.partProList.penPartList.map(item => {
                        if (item.id === pen.id) {
                            item.status = true;
                            this.$store.state.partData.penImg = pen.matchUrl;
                        } else {
                            item.status = false;
                        }
                    });
                }
                if (cover) {
                    this.$store.state.partData.coverImg = cover.matchUrl;
                    this.$store.state.partProList.coverPartList.map(item => {
                        if (item.id === cover.id) {
                            item.status = true;
                            this.chose.coverColor = item.name
                        } else {
                            item.status = false;
                        }
                    });
                }
            },
            goAnchor(selector) {
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
                        document.body.scrollTop = total - 80
                        document.documentElement.scrollTop = total - 80
                    }
                })()
            },
            checkSet(data) {
                this.checkShow = data.title;
                this.$store.state.proDetail.map((item, index) => {
                    if (item.title === data.title) {
                        this.checkShow = index
                        console.log(item.title)
                        this.$store.state.proContent = item.productDescriptionInfo
                        item.status = true;
                    } else {
                        item.status = false;
                    }
                });

            },
        },

    };
</script>
<style lang="less">
    #a5 {
        section {
            background: #111111;

            .sub-nav {
                padding: 0 13%;
                display: flex;
                justify-content: space-between;
                background: #111111;
                width: 100%;
                z-index: 10000;

                div {
                    text-align: left;
                    color: #ffffff;
                }

                .right {
                    display: flex;
                    justify-content: flex-end;
                    align-items: center;

                    .product-content {
                        display: flex;
                        justify-content: flex-end;

                        div {
                            cursor: pointer;
                        }
                    }

                    .btn {
                        background: #C9A060;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        cursor: pointer;
                    }
                }
            }

            .content {
                .overview {
                    overflow: hidden;
                    div {
                        img{
                            width: 100%
                        }
                    }
                }
                .set-menu {
                    background: #ffffff;
                    display: flex;
                    justify-content: flex-start;

                    .left {
                        position: relative;

                        .note, .cover, .pen {
                            position: absolute;
                        }
                    }

                    .right {
                        div {
                            text-align: left;

                            span {
                                color: #000000;
                            }
                        }

                        .colorStyle {
                            display: flex;
                            flex-wrap: wrap;
                            align-content: flex-start;
                            justify-items: flex-start;

                            div {
                                position: relative;
                                overflow: hidden;

                                div {
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                    cursor: pointer;
                                }
                            }

                        }
                    }

                    .buy {
                        cursor: pointer;

                    }
                }
            }


        }

        .footer {
            background: #111111
        }
    }

</style>
