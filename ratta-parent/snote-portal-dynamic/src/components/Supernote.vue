<template>
    <div class="suoernote">
        <div class="productDesc"
             :style="{width: widthNormal +'px', height: width===750 ? 'auto' : getSize(1000), background: width===750 ? 'url('+banner_2+')' : 'url('+banner_1+')', 'background-size': 'cover', position: 'relative', }">
            <img :style="{width: width=== 750 ? widthNormal +'px' : '100%', height: width=== 750 ? widthNormal*2.04 +'px': 'auto'}" :src="width===750 ? banner_2 : banner_1" alt="">
            <dl :style="{width: width===750 ? '70%':'30%', position: 'absolute', top: width===750 ?'137px':getSize(150), left: width===750 ? '20%':'51%'}">
                <dt class="title bold" :class=" width===750 ? '' : 'bold'"
                    :style="{fontSize: width===750 ?'24px':getSize(32),}">{{$t('message.nav.a5')}}
                </dt>
                <dd class="desc"
                    :style="{fontSize: width===750 ?'14px':getSize(22), marginTop: width===750 ?'5px':getSize(18)}">
                    {{$t('message.content.a5')}}
                </dd>
                <dd :style="{fontSize: getSize(18), cursor: 'pointer', marginTop: width===750 ?'5px':getSize(15)}"
                    @click="$router.push({path: '/product', query: {value: 'SN-A5'}})">
                    <a :style="{fontSize: width===750 ?'14px':getSize(18)}">{{$t('message.button.detail')}}</a>
                </dd>
            </dl>
            <dl :style="{width: width===750 ? '70%':'30%', position: 'absolute', top: width===750 ?'241px' : getSize(280), left: width===750 ? '20%':'31%'}">
                <dt class="title bold" :class=" width===750 ? '' : 'bold'"
                    :style="{fontSize: width===750 ?'24px':getSize(32),}">{{$t('message.content.a6Tiltle')}}
                </dt>
                <dd class="desc"
                    :style="{fontSize: width===750 ?'14px': getSize(22), marginTop:width===750 ?'5px': getSize(18)}">
                    {{$t('message.content.a6')}}
                </dd>
                <dd :style="{marginTop: width===750 ?'5px':getSize(15), cursor: 'pointer'}"
                    @click="$router.push({path: '/product', query: {value: 'SN-A6'}})">
                    <a :style="{fontSize: width===750 ?'14px':getSize(18)}">{{$t('message.button.detail')}}</a>
                </dd>
            </dl>
        </div>
        <div v-if="width===1920" class="lowLatency"
             :style="{height: getSize(720), background: 'url('+banner_3+')', backgroundSize: 'cover'}">
            <div class="right" :style="{marginTop: getSize(200), marginRight: getSize(80), width: getSize(587)}">
                <div class="commonStyle" :style="{paddingTop: getSize(60), paddingLeft: getSize(115)}">
                    <span class="bold" :style="{fontSize: getSize(32),}">{{$t('message.lowLatency.title')}}</span>
                    <span class="bold" :style="{fontSize: getSize(32), marginTop: getSize(10)}">{{$t('message.lowLatency.desc')}}</span>
<!--                    <span @click="$router.push('/a5')" class="btn medium"-->
<!--                          :style="{height: getSize(54), borderRadius: getSize(54), marginTop: getSize(60), fontSize: getSize(18), width: getSize(160)}">{{$t('message.button.look')}}</span>-->
                </div>
            </div>
        </div>
        <div v-if="width===750" style="width: 100%; background: none; position: relative">
            <img width="100%" :src="banner_4" alt="">
            <div :style="{position: 'absolute', top: getSize(100), width: '100%', textAlign: 'center'}">
                <div>
                    <span class="bold" :style="{fontSize: width===750 ?'18px':getSize(20), color: '#000000'}">{{$t('message.lowLatency.title')}}</span>
                </div>
                <div :style="{marginTop: getSize(8), marginBottom: getSize(8)}">
                    <span class="bold" :style="{fontSize: width===750 ?'18px': getSize(20), color: $i18n.locale==='en-US'?'#C9A060':'#000000'}">{{$t('message.lowLatency.desc')}}</span>
                </div>
<!--                <div>-->
<!--                    <a @click="$router.push('/a5')" :style="{ fontSize: width===750 ?'12px':getSize(14)}">{{$t('message.button.look')}}</a>-->
<!--                </div>-->
            </div>
        </div>
        <div v-if="width===1920" class="highQuality"
             :style="{width: getSize(1420), height: getSize(480), paddingTop: getSize(60), paddingRight: getSize(100), paddingBottom: getSize(60), paddingLeft: getSize(100), marginTop: '-'+ getSize(60)}">
            <div class="commonStyle" :style="{marginTop: getSize(60), width: getSize(260)}">
                <span class="bold" :style="{fontSize: getSize(32),}">{{$t('message.highQuality.title')}}</span>
                <span class="bold" :style="{fontSize: getSize(32), marginTop: getSize(10)}">{{$t('message.highQuality.desc')}}</span>
                <span :style="{fontSize: getSize(18), marginTop: getSize(40)}">{{$t('message.highQuality.content').split(',')[0]}}<br>{{$t("message.highQuality.content").split(",")[1]}}</span>
            </div>
            <div class="right">
                <div class="highQualityRight" v-for="item in highQualityRight"
                     :style="{width: getSize(300), background: '#ffffff', height: getSize(360), padding: getSize(20)}">
                    <span>
                        <img width="100%" :src="item.img" alt="">
                    </span>
                    <span class="bold"
                          :style="{fontSize: getSize(20), color: '#2E292D', fontWeight: '500', marginTop: getSize(25)}">{{item.title}}</span>
                    <span :style="{fontSize: getSize(14), color: '#838082', fontWeight: '400', marginTop: getSize(5)}">{{item.desc}}</span>
                </div>
            </div>
        </div>
        <div v-if="width===750"
             :style="{background: '#FFFFFF',paddingTop: getSize(40), position: 'relative', paddingRight: getSize(35), paddingBottom: getSize(30), paddingLeft: getSize(35),}">
            <div :style="{position: 'absolute', width: getSize(680), background: '#ffffff', height: '21px', top: '-19px', zIndex: 100}"></div>
            <div :style="{textAlign: 'left'}">
                <span class="bold"
                      :style="{display: 'inline-block', fontSize: '18px', fontWeight: '500', color: '#000000'}">{{$t('message.highQuality.title')}}</span>
                    <span class="bold"
                          :style="{display: 'inline-block', fontSize: '18px', fontWeight: '500', color: $i18n.locale==='en-US'?'#C9A060':'#000000'}">{{$t('message.highQuality.desc')}}</span><br>
                <span :style="{display: 'inline-block',fontSize: '12px', marginTop: getSize(10), marginBottom: getSize(40), color: '#777777'}">{{$t('message.highQuality.content').split(',')[0]}}{{$t("message.highQuality.content").split(",")[1]}}</span>
            </div>
            <div class="highQualityRight" v-for="item in highQualityRight">
                    <span>
                        <img width="100%" :src="item.img" alt="">
                    </span>
                <span class="bold"
                      :style="{display: 'inline-block',fontSize: '18px', color: '#2E292D', fontWeight: '500', marginTop: getSize(10)}">{{item.title}}</span><br>
                <span :style="{display: 'inline-block', fontSize: '12px', color: '#838082', marginTop: getSize(10), marginBottom: getSize(20), fontWeight: '400'}">{{item.desc}}</span>
            </div>
        </div>
        <div v-if="width === 1920" class="highQuality"
             :style="{background: '#ffffff', alignItems: 'flex-end', width: getSize(1420), height: getSize(520),}">
            <div class="commonStyle" :style="{marginTop: getSize(60), width: getSize(504)}">
                <span class="bold" :style="{fontSize: getSize(32),}">{{$t('message.richPart.title')}}</span>
                <span class="bold" :style="{fontSize: getSize(32), marginTop: getSize(10)}">{{$t('message.richPart.desc')}}</span>
                <span :style="{fontSize: getSize(18), marginTop: getSize(40)}">{{$t('message.richPart.content').split(',')[0]}}<br>{{$t('message.richPart.content').split(',')[1]}}</span>
                <span @click="$router.push({path: '/product', query: {checkStatus: '1111', value: 'SN-A5'}})"  class="medium btn"
                      :style="{height: getSize(54), borderRadius: getSize(54), marginTop: getSize(40), marginBottom: getSize(40), fontSize: getSize(18), width: $i18n.locale === 'zh-CN' ? getSize(160) : getSize(220)}">{{$t('message.button.check')}}</span>
            </div>
            <div class="right" :style="{width: getSize(700), height: getSize(380),}">
                <img width="100%" src="../assets/index/img_homepage_4-2.png" alt="">
            </div>
        </div>
        <div v-if="width === 750"
             :style="{background: '#F7F7F7',paddingTop: getSize(50), paddingRight:'25px',paddingLeft: '25px',}">
            <div class="commonStyle" :style="{}">
                <span class="bold" :style="{fontSize: '18px', justifyContent: 'center', fontWeight: '500'}">{{$t('message.richPart.title')}}</span>
                <span class="bold" :style="{fontSize: '18px', justifyContent: 'center', fontWeight: '500', color: '#C9A060'}">{{$t('message.richPart.desc')}}</span>
                <span :style="{fontSize: '12px', color: '#777777', textAlign: 'center', justifyContent: 'center', marginTop: getSize(10)}">{{$t('message.richPart.content').split(',')[0]}}&nbsp;&nbsp;{{$t('message.richPart.content').split(',')[1]}}</span>
                <div @click="$router.push({path: '/product', query: {checkStatus: '1111', value: 'SN-A5'}})" style="display: flex; justify-content: center">
                    <span class="btn"
                          :style="{height: '34px', borderRadius:'17px', marginTop: getSize(15), marginBottom: '25px', fontSize: '14px'}">{{$t('message.button.check')}}</span>
                </div>
            </div>
            <div>
                <img width="100%" src="../assets/index/img_homepage_4-2.png" alt="">
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Suoernote',
        props: {},
        data() {
            return {
                product: [
                    {
                        id: 1,
                        title: this.$i18n.t('message.nav.a5'),
                        desc: this.$i18n.t('message.content.a5'),
                        href: this.$i18n.t('message.button.detail')
                    },
                    {
                        id: 2,
                        title: this.$i18n.t('message.content.a6Tiltle'),
                        desc: this.$i18n.t('message.content.a6'),
                        href: this.$i18n.t('message.button.detail')
                    },
                ],
                highQualityRight: this.$i18n.t('message.highQualityRight'),
                highQuality2Right: this.$i18n.t('message.highQuality2Right'),
                banner_1: require('../assets/index/banner_homepage_2.png'),
                banner_2: require('../assets/mobile/index/banner_2@2x.png'),
                banner_3: require('../assets/index/banner_homepage_3.jpg'),
                banner_4: require('../assets/mobile/index/desc-1@2x.png'),
                width: null,
                widthNormal: null
            }
        },
        mounted() {
            this.widthNormal = window.innerWidth
            if (window.innerWidth < 1080) {
                this.width = 750

            } else {
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
        },
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
    .pad {
        padding: 0 13%;
    }

    .suoernote {
        a {
            text-decoration: underline;
            color: #C9A060
        }

        .productDesc {
            display: flex;
            background-size: cover !important;

            dl {
                margin-bottom: 0;

                dt, dd {
                    text-align: left;
                }

                dd {
                    color: #333333;
                    font-weight: 400;

                    a {
                        text-decoration: underline;
                        color: #C9A060
                    }
                }

                dt.title {
                    color: #2E292D;
                    font-weight: 500;
                }
            }
        }

        .lowLatency {
            display: flex;
            justify-content: flex-end;

            .right {
            }
        }

        .commonStyle {
            span {
                font-weight: 500;
                display: flex;
                text-align: left;

                &:nth-child(1) {
                    color: #2E292D;
                }

                &:nth-child(2) {
                    color: #C9A060;
                }

                &:nth-child(3) {
                    color: #333333;
                }
            }

            span.btn {
                background: #C9A060;
                color: #ffffff;
                display: flex;
                justify-content: center;
                align-items: center;
            }
        }

        .highQuality {
            background: #F7F7F7;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;

            .right {
                display: flex;

                .highQualityRight {
                    cursor: pointer;
                    margin-right: 1px;
                    span {
                        display: flex;
                        text-align: left;
                    }
                    &:hover {
                        box-shadow: 0px 0px 40px 0px rgba(28,25,74,0.1),0px 0px 3px 0px rgba(0,0,0,0.1)
                    }
                }
            }
        }
    }
</style>
