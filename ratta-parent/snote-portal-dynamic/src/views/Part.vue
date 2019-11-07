<template>
    <div id="dermis">
        <section v-if="width===1920">
            <div class="product">
                <div class="product-swiper"
                     :style="{width: getSize(1200), height: getSize(800)}">
                    <img width="100%" :src="firstImg?firstImg:this.$store.state.parDetail.mutiUrl && this.$store.state.parDetail.mutiUrl.length > 0?this.$store.state.parDetail.mutiUrl[0]:''" alt="">
                    <div :style="{position: 'absolute', left: 0, width: '100%', bottom: getSize(20)}">
                        <PartSlider :part="this.$store.state.parDetail.mutiUrl" @img="showImg"/>
                    </div>
                </div>
                <div class="product-content" :style="{marginTop: getSize(120), marginLeft: getSize(100), width: getSize(520)}">
                    <div class="product-content-html ql-snow ql-editor" v-html="this.$store.state.parDetail.description" :style="{height: 'auto', fontSize: getSize(36), fontWeight: '600', marginBottom: getSize(40)}">
                        {{this.$store.state.parDetail.description}}
                    </div>
                    <div v-if="this.$store.state.parDetail.type === '2'" :style="{fontSize: getSize(24), fontWeight: '600', marginBottom: getSize(20)}">
                        <span class="medium">{{$t("message.part.type")}}</span>
                    </div>
                    <div v-if="this.$store.state.parDetail.type === '2'" style="display: flex; justify-content: flex-start">
                        <div v-for="item in this.$store.state.parDetail.productList"
                             @click="checkPro2(item)"
                             :style="{width: getSize(132), height: getSize(48), cursor: 'pointer', lineHeight: getSize(48), marginRight: getSize(20), textAlign: 'center', color: item.status ? '#C9A060' : '#2E292D', border: item.status ? '1px solid #C9A060' : '1px solid #2E292D', borderRadius: getSize(24), fontSize: item.status ? getSize(24): getSize(18)}">
                            <span>{{item.productName.split(' ')[1]}}</span>
                        </div>
                    </div>
                    <div v-if="this.$store.state.partDetail && this.$store.state.partDetail.length > 0" :style="{fontSize: getSize(24), fontWeight: '600', marginTop: getSize(80), marginBottom: getSize(20)}">
                        <span class="bold">{{$t("message.part.color")}}</span>
                    </div>
                    <div v-if="this.$store.state.parDetail.type !== '2'" :style="{width: getSize(550), marginLeft: '-'+getSize(20), overflow: 'auto', zoom: 1}">
                        <div v-for="item in this.$store.state.partDetail"
                             @click="checkItem(item)"
                             :style="{width: getSize(80), flexFlow: 'column', cursor: 'pointer', alignItems: 'center', height: $i18n.locale === 'zh-CN' ? getSize(110) : getSize(80), float: 'left'}">
                             <div :style="{margin: '0 auto', width: '40px', height: '40px', borderRadius: '20px', border: item.status ? '1px solid #000000' : '1px solid #ffffff', display: 'flex', alignItems: 'center', justifyContent: 'center', }">
                                 <div :style="{display: 'inline-block', width: '32px', borderRadius: '16px', border: item.color === '#ffffff' ? '1px solid #EAEAEA' : 0, height: '32px'}">
                                     <img width="100%" :src="item.colourUrl" alt="">
                                 </div>
                             </div>
                            <div v-if="$i18n.locale === 'zh-CN'" :style="{fontSize: getSize(12), marginTop: getSize(10), color: '#2E292D',textAlign:'center'}">
                                <span>{{item.colourName}}</span>
                            </div>
                        </div>
                    </div>
                    <div v-if="this.$store.state.parDetail.type === '2'" :style="{width: getSize(550), marginLeft: '-'+getSize(20), height: this.$store.state.partDetail.length > 6 ? getSize(120) : getSize(50), minHeight: this.$store.state.partDetail.length > 6 ? getSize(120) : getSize(50)}">
                        <div v-for="item in this.$store.state.partDetail"
                             @click="checkItem(item)"
                             v-if="$store.state.partType == item.productType"
                             :style="{width: $i18n.locale === 'zh-CN' ? getSize(80) : getSize(90), cursor: 'pointer', flexFlow: 'column', alignItems: 'center', height: $i18n.locale === 'zh-CN' ? getSize(90) : getSize(50), float: 'left'}">
                            <div :style="{margin: '0 auto', width: '40px', height: '40px', borderRadius: '20px', border: item.status ? '1px solid #000000' : '1px solid #ffffff', display: 'flex', alignItems: 'center', justifyContent: 'center', }">
                                <div :style="{display: 'inline-block', width: '32px', borderRadius: '16px', border: item.color === '#ffffff' ? '1px solid #EAEAEA' : 0, height: '32px'}">
                                    <img width="100%" :src="item.colourUrl" alt="">
                                </div>
                            </div>
                            <div v-if="$i18n.locale === 'zh-CN'" :style="{fontSize: getSize(12), marginTop: getSize(10), color: '#2E292D',textAlign:'center'}">
                                <span>{{item.colourName}}</span>
                            </div>
                        </div>
                    </div>
                    <a style="text-decoration: none" :href="this.$store.state.parDetail.buttonLink" target="_blank">
                        <div class="buy"
                             :style="{width: getSize(132), cursor: 'pointer', height: getSize(48), lineHeight: getSize(48), textAlign: 'center', marginTop: getSize(40), border: '1px solid #C9A060', borderRadius: getSize(24), fontSize: getSize(18)}">
                            <span style="color: #C9A060">{{this.$store.state.parDetail.buttonName}}</span>
                        </div>
                    </a>
                </div>
            </div>
            <div v-html="this.$store.state.parDetail.contentPc" class="detail ql-snow ql-editor" :style="{height: 'auto', marginBottom: getSize(80)}">
                {{this.$store.state.parDetail.contentPc}}
            </div>
            <div class="recommend" :style="{height: getSize(440)}">
                <div class="left" :style="{marginTop: getSize(140), marginRight: getSize(80), fontSize: getSize(36)}">
                    <span class="bold">{{$t("message.recomProduct")}}</span>
                </div>
                <div class="right" :style="{marginTop: getSize(100)}">
                    <div v-for="item in this.$store.state.parDetail.recommendVOList" :style="{marginLeft: getSize(80), cursor: 'pointer', width: getSize(300)}"
                         @click="item.type === '0' ? $router.push({path: '/product', query: {value: item.recommendId}}) : goPart(item.recommendId)">
                        <div :style="{height: getSize(240), marginBottom: getSize(15), overflow: 'hidden'}">
                            <img width="100%" :src="item.recommendUrl" alt="">
                        </div>
                        <div :style="{fontSize: getSize(18), opacity: 0.6}">
                            <span>{{item.name}}</span>
                        </div>
                    </div>
                </div>
            </div>
            <Dialog v-if="dialogVisible"/>
        </section>
        <section v-if="width===750">
            <div>
                <div style="position: fixed; bottom: 0; left: 0; width: 100%; z-index: 1000; color: #131313; height: 50px; background: #ffffff; border-top: 1px solid #F4F4F4; display: flex; align-items: center; justify-content: space-between; padding: 0 5%">
                    <div>
                        <span class="medium" style="font-size: 14px">{{this.$store.state.parDetail.name}}</span>
                    </div>
                    <a style="text-decoration: none" :href="this.$store.state.parDetail.buttonLink" target="_blank">
                        <div style="background: #C9A060; width: 90px; height: 30px; line-height: 30px; text-align: center; border-radius: 15px; color: #ffffff; font-size: 14px">
                            {{this.$store.state.parDetail.buttonName}}
                        </div>
                    </a>
                </div>
                <div class="product-swiper">
                    <SwiperMobile v-if="!bannerImg" :type="type" :banner="this.$store.state.parDetail.mutiUrl"/>
                    <img v-if="bannerImg" width="100%" :src="bannerImg" alt="">
                </div>
                <div style="padding-left: 36px;">
                    <div style="margin-top: 20px; font-size: 22px; margin-bottom: 40px">
                        <span class="medium" :style="{ color: '#131313', fontWeight: '600', }">{{this.$store.state.parDetail.name}}</span>
                    </div>
                    <div v-if="this.$store.state.parDetail.type === '2'" style="font-size: 14px; color: #2E292D; font-weight: 600; margin-bottom: 10px">
                        <span class="medium">{{$t("message.part.type")}}</span>
                    </div>
                    <div v-if="this.$store.state.parDetail.type === '2'" style="display: flex; justify-content: flex-start">
                        <div v-for="item in this.$store.state.parDetail.productList"
                             :style="{color: item.status ? '#C9A060' : '#2E292D', border: item.status ? '1px solid #C9A060' : '1px solid #2E292D',}"
                             @click="checkPro2(item)"
                             style="width: 66px; height: 24px; cursor: pointer; line-height: 24px; margin-right: 20px; text-align: center; border-radius: 24px; font-size: 12px">
                            <span>{{item.productName.split(' ')[1]}}</span>
                        </div>
                    </div>
                    <div v-if="this.$store.state.partDetail && this.$store.state.partDetail.length > 0" style="font-size: 14px; color: #2E292D; font-weight: 600; margin-top: 40px; margin-bottom: 10px">
                        <span class="bold">{{$t("message.part.color")}}</span>
                    </div>
                    <div v-if="this.$store.state.parDetail.type !== '2'" style="width: 100%; overflow: auto; zoom: 1">
                        <div v-for="item in this.$store.state.partDetail"
                             @click="checkItem(item)"
                             style="margin-right: 15px; float: left" :style="{height: $i18n.locale === 'zh-CN' ? getSize(160) : getSize(114)}">
                            <div :style="{width: getSize(100), flexFlow: 'column', justifyContent: 'center', alignItems: 'center', marginBottom: getSize(15), float: 'left'}">
                                <div :style="{ width: '32px', height: '32px', borderRadius: '16px',  margin: '0 auto', display: 'flex', border: item.status ? '1px solid #000000' : '1px solid #ffffff', alignItems: 'center', justifyContent: 'center', }">
                                    <div :style="{display: 'inline-block', width: '24px', borderRadius: '12px', height: '24px'}">
                                        <img width="100%" :src="item.colourUrl" alt="">
                                    </div>
                                </div>
                                <div v-if="$i18n.locale === 'zh-CN'" style="font-size: 12px; margin-top: 10px; text-align: center; color: #2E292D">
                                    <span>{{item.colourName}}</span>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div v-if="this.$store.state.parDetail.type === '2'" style="width: 100%; overflow: auto; zoom: 1">
                        <div v-for="item in this.$store.state.partDetail"
                             @click="checkItem(item)"
                             v-if="$store.state.partType == item.productType"
                             style="margin-right: 15px; float: left" :style="{height: $i18n.locale === 'zh-CN' ? getSize(160) : getSize(114), minHeight: $i18n.locale === 'zh-CN' ? getSize(160) : getSize(114)}">
                            <div :style="{width: getSize(120), flexFlow: 'column', justifyContent: 'center', alignItems: 'center', marginBottom: getSize(15), float: 'left'}">
                                <div :style="{ width: '32px', height: '32px', borderRadius: '16px', margin: '0 auto', display: 'flex', border: item.status ? '1px solid #000000' : '1px solid #ffffff', alignItems: 'center', justifyContent: 'center', }">
                                    <div :style="{display: 'inline-block', margin: '0 auto', width: '24px', borderRadius: '12px', height: '24px'}">
                                        <img width="100%" :src="item.colourUrl" alt="">
                                    </div>
                                </div>
                            </div>
                            <div v-if="$i18n.locale === 'zh-CN'" style="font-size: 12px; margin-top: 10px; text-align: center; color: #2E292D">
                                <span>{{item.colourName}}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-html="this.$store.state.parDetail.contentMobile" class="detailMobile ql-snow ql-editor" :style="{height: 'auto', marginBottom: getSize(80)}">
                    {{this.$store.state.parDetail.contentMobile}}
                </div>
            </div>
        </section>
    </div>
</template>

<script>
    // @ is an alias to /src

    import PartSlider from "@/components/PartSlider.vue";
    import Dialog from '@/components/Dialog.vue'
    import SwiperMobile from "@/components/SwiperMobile2.vue";

    export default {
        components: {
            Dialog,
            PartSlider,
            SwiperMobile
        },
        name: "Dermis",
        data() {
            return {
                height: null,
                dialogVisible: false,
                pro: [
                    {id: 1, name: "A5", status: true},
                    {id: 2, name: "A6", status: false}
                ],
                firstImg: '',
                width: null,
                bannerImg: '',
                type: 'fraction',
                dataVal: {
                    language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',
                    id: this.$route.query.id,
                },
            };
        },
        created() {
            this.$store.dispatch('partDetail', this.dataVal)
            this.$store.dispatch('parDetail', this.dataVal)
            this.$store.dispatch('parts', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en'})
        },
        watch: {
            '$route'(to,from){
                this.$store.state.parDetail.mutiUrl = ''
                this.dataVal.id = to.query.id
                this.$store.dispatch('partDetail', this.dataVal)
                this.$store.dispatch('parDetail', this.dataVal)
                window.scrollTo(0,0)
                this.firstImg = ''
                this.bannerImg = ''
            },
        },
        computed: {
            data() {
                return this.$store.state.partDetail, this.$store.state.parDetail, this.$store.state.partType, this.$store.state.partsList
            }
        },
        mounted() {
            this.productType = this.$store.state.partTyp
            if(this.$route.query.id) {
                this.dataVal.id = this.$route.query.id
            }
            this.height = window.innerHeight
            window.scrollTo(0, 0);

            if (window.innerWidth < 961) {
                this.width = 750;
            } else {
                this.width = 1920;
            }
        },
        methods: {
            getSize(num) {
                if (window.innerWidth < 961) {
                    return window.innerWidth * num / 750 + "px";
                } else {
                    return window.innerWidth * num / 1920 + "px";
                }
            },
            visibleDialog() {
                this.dialogVisible = !this.dialogVisible
            },

            checkItem(item) {
                this.$store.state.partDetail.map(ite => {
                    if (item.id === ite.id) {
                        ite.status = true;
                        if (this.width === 750) {
                            this.bannerImg = ite.productUrl
                        } else {
                            this.firstImg = ite.productUrl;
                        }
                    } else {
                        ite.status = false;
                    }
                });
            },
            checkPro2(item) {
                this.$store.state.parDetail.productList.map(ite => {
                    if (item.productType === ite.productType) {
                        ite.status = true;
                        this.$store.state.partType = item.productType
                    } else {
                        ite.status = false;
                    }
                });
            },
            showImg(e) {
                this.firstImg = e;
            },
            goPart(id) {
                this.$router.push({path: '/part', query: {id: id}})
            }
        },
        destroyed() {
        }
    };
</script>
<style lang="less">
    #dermis {
        .footer {
            background: #111111
        }

        section {
            .product {
                display: flex;
                justify-content: flex-start;

                .product-swiper {
                    position: relative;
                }

                .product-content {
                    .product-content-html {
                        p {
                            img {
                                /*width: 100%;*/
                            }
                        }
                    }
                    div {
                        text-align: left;
                        color: #000000;
                    }
                }
            }

            .detail {
                overflow: hidden;
                text-align: center;
                h1{
                    flex-flow: column;
                    justify-items: center;
                    align-content: center;
                    img {
                        width: 100%;
                    }
                }
                h2{
                    flex-flow: column;
                    justify-items: center;
                    align-content: center;
                    img {
                        width: 100%;
                    }
                }
                p {
                    flex-flow: column;
                    justify-items: center;
                    align-content: center;
                    img {
                        width: 100%;
                    }
                }
            }
            .detailMobile {
                h1{
                    flex-flow: column;
                    justify-items: center;
                    align-content: center;
                    img {
                        width: 100%;
                    }
                }
                h2{
                    flex-flow: column;
                    justify-items: center;
                    align-content: center;
                    img {
                        width: 100%;
                    }
                }
                p {
                    flex-flow: column;
                    justify-items: center;
                    align-content: center;
                    img {
                        width: 100%;
                    }
                }
            }
            .recommend {
                display: flex;
                /*justify-content: space-between;*/
                padding: 0 13%;
                background: #F1F1F1;

                div {
                    text-align: left;
                    color: #000000;
                }

                .left {
                    font-weight: 600;
                }

                .right {
                    display: flex;
                    justify-content: flex-start;

                    div {
                        text-align: left;
                        cursor: pointer;
                    }
                }
            }
        }
    }

</style>
