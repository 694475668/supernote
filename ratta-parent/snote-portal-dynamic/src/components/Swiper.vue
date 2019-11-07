<template>
    <div class="swiper-container">
        <swiper :options="swiperOption"  v-if="banner && banner.length > 0" class="swiper-container">
            <!-- slides -->
            <swiper-slide class="swiper-item" v-for="item in banner" v-if="item.urlPc">
                <div :style="{height: getSize(1000),}">
                    <img width="100%" :src="item.urlPc" alt="">
                    <div :style="{position: 'absolute', top: getSize(300), left: getSize(250), textAlign: 'left', color: '#ffffff'}">
                        <div class="song" :style="{width: getSize(600), fontSize: getSize(64), lineHeight: 1.1, color: item.colorPc}" v-html="$i18n.locale==='zh-CN'?item.nameCn:item.nameEn">{{$i18n.locale==='zh-CN'?item.nameCn:item.nameEn}}</div>
                        <div :style="{fontSize: getSize(32), width: getSize(500), color: item.colorPc, marginBottom: getSize(90), marginTop: getSize(24),}">
                            <span v-html="$i18n.locale==='zh-CN'?item.descriptionCn:item.descriptionEn">{{$i18n.locale==='zh-CN'?item.descriptionCn:item.descriptionEn}}</span>
                        </div>
                        <div v-if="item.buttonType" @click="buttonTy(item)"
                             :style="{cursor: 'pointer', display: 'inline-block', paddingLeft: getSize(50), paddingRight: getSize(50), boxShadow: '10px 10px 20px 0 rgba(0,0,0,0.1)', fontSize: getSize(24), cursor: 'pointer', borderRadius: getSize(30), background: '#C9A060', height: getSize(60), lineHeight: getSize(60), textAlign: 'center'}">
                            <span class="bold">{{$i18n.locale==='zh-CN'?item.buttonNameCn:item.buttonNameEn}}</span>
                        </div>
                        <a target="_blank" v-if="$i18n.locale==='zh-CN'?item.buttonLinkCn:item.buttonLinkEn" :href="$i18n.locale==='zh-CN'?item.buttonLinkCn:item.buttonLinkEn"
                           :style="{display: 'inline-block', textDecoration: 'none', color: '#ffffff', cursor: 'pointer', paddingLeft: getSize(50), paddingRight: getSize(50), boxShadow: '10px 10px 20px 0 rgba(0,0,0,0.1)', fontSize: getSize(24), cursor: 'pointer', borderRadius: getSize(30), background: '#C9A060', height: getSize(60), lineHeight: getSize(60), textAlign: 'center'}">
                            <span class="bold">{{$i18n.locale==='zh-CN'?item.buttonNameCn:item.buttonNameEn}}</span>
                        </a>
                    </div>
                </div>
            </swiper-slide>
        </swiper>
        <!-- 如果需要分页器 -->
        <div class="swiper-pagination"></div>
    </div>
</template>
<script>
    import '../../public/swiper2.css'
    export default {
        name: "Slider",
        props: ['banner'],
        data() {
            return {
                current: null,
                swiperOption: {
                    // 参数选项,显示小点 pagination:'.swiper-pagination', //循环 loop:true, //每张播放时长3秒，自动播放 autoplay:2000, //滑动速度 speed:1000, // delay:1000 },作者：fenerchen
                    loop: true,
                    observeParents:true,
                    observer:true,
                    autoplay: {
                        delay: 3000,
                        disableOnInteraction: false,
                    },
                    // 如果需要分页器
                    speed: 2000,
                    pagination: {
                        el: '.swiper-pagination',
                        clickable: true,
                        type: 'custom',
                        renderCustom: (swiper, current) => {
                            this.current = current
                            let height = window.innerHeight * 0.556
                            let lineHeight = height * 0.8
                            document.getElementsByClassName('swiper-pagination-bullet').addClass = 'height'
                            let round = '<div class="roundLine" style="height:' + lineHeight + 'px">\n' +
                                '<div style="width: 2px; height: ' + lineHeight * 0.1667 + 'px; background: #ffffff; margin: 0 auto"></div>\n' +
                                '<div style="width: ' + lineHeight * 0.21 + 'px;height: ' + lineHeight * 0.21 + 'px;border: 1.5px solid #ffffff; ' +
                                ' margin-left: -' + lineHeight * 0.21 / 2 + 'px;display: flex;align-items: center;justify-content: center;border-radius: 50%;">' +
                                '<span style="color: #ffffff; font-size: 2.4rem">0' + current + '</span></div>' +
                                '<div style="width:2px; height: ' + lineHeight * 0.625 + 'px; background: #ffffff;"></div></div><div class="swiper-page-active-item">'
                            for (let i = 0; i < this.banner.length; i++) {
                                if (i + 1 == current) {
                                    round += '<span class="swiper-pagination-bullet-item swiper-pagination-bullet-item-active swiper-pagination-bullet swiper-pagination-bullet' + current + '" style="height: 30px; opacity: 1"></span>'
                                } else {
                                    round += '<span class="swiper-pagination-bullet-item swiper-pagination-bullet swiper-pagination-bullet' + current + '"></span>'
                                }
                            }
                            return round + '</div>'
                        }

                    },
                }
            }
        },
        mounted() {

        },
        methods: {
            getSize(num) {
                return window.innerWidth * num / 1920 + 'px'
            },
            buttonTy(item) {
                if (item.buttonType === '0') {
                    this.$router.push({path: '/product', query: {value: item.productId}})
                } else {

                    this.$router.push({path: '/part', query: {id: item.productId}})
                }
            }
        }
    }
</script>
<style scoped>
    .swiper-container {
        width: auto;
        height: 100%;
        margin: 0;
        padding: 0;
    }

    .swiper-wrapper {
    }

    .swiper-container .swiper-slide img {
        width: 100%;
        height: 100%;
    }

    .swiper-container .swiper-slide {
        /* Center slide text vertically */

    }

    .swiper-container .swiper-pagination-bullet {
        cursor: pointer;
    }

    .swiper-container .swiper-pagination {
        text-align: center;
        height: 55%
    }

    .roundLine {
        position: absolute;
        top: 0;
        z-index: 10000
    }
</style>
