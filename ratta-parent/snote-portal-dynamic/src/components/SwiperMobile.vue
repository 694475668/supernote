<template>
    <div class="swiper-container5">
        <swiper :options="swiperOption" class="swiper-container" v-if="banner && banner.length > 0">
            <!-- slides -->
            <swiper-slide class="swiper-item" v-for="item in banner" v-if="item.urlMobile">
                <img :src="item.urlMobile" alt="">
                <div :style="{position: 'absolute', top: '81px' , width: '100%', textAlign: 'center', color: '#ffffff'}">
                    <div class="bold" :style="{fontSize: '24px', color: item.colorMobile}" v-html="$i18n.locale==='zh-CN'?item.nameCn:item.nameEn">{{$i18n.locale==='zh-CN'?item.nameCn:item.nameEn}}</div>
                    <div v-if="$i18n.locale==='zh-CN'?item.descriptionCn:item.descriptionEn"
                         :style="{ width: '60%',marginLeft: '20%', fontSize: '14px', color: item.colorMobile, marginBottom: '20px', marginTop: '20px',}">
                        <span v-html="$i18n.locale==='zh-CN'?item.descriptionCn:item.descriptionEn">{{$i18n.locale==='zh-CN'?item.descriptionCn:item.descriptionEn}}</span>
                    </div>
                    <div v-if="item.buttonType" @click="buttonTy(item)"
                         :style="{margin: '0 auto', cursor: 'pointer', boxShadow: '10px 10px 20px 0 rgba(0,0,0,0.1)', fontSize: '14px', cursor: 'pointer', width: '140px', borderRadius: '17px', background: '#C9A060', height: '34px', lineHeight: '34px', textAlign: 'center'}">
                        <span>{{$i18n.locale==='zh-CN'?item.buttonNameCn:item.buttonNameEn}}</span>
                    </div>
                    <a target="_blank" v-if="$i18n.locale==='zh-CN'?item.buttonLinkCn:item.buttonLinkEn" :href="$i18n.locale==='zh-CN'?item.buttonLinkCn:item.buttonLinkEn"
                       :style="{display: 'block', margin: '0 auto', cursor: 'pointer', boxShadow: '10px 10px 20px 0 rgba(0,0,0,0.1)', fontSize: '14px', cursor: 'pointer', width: '140px', borderRadius: '17px', background: '#C9A060', height: '34px', lineHeight: '34px', textAlign: 'center'}">
                        <span style=" color: #ffffff !important">{{$i18n.locale==='zh-CN'?item.buttonNameCn:item.buttonNameEn}}</span>
                    </a>

                </div>
            </swiper-slide>
        </swiper>
        <!-- 如果需要分页器 -->
        <div class="swiper-pagination"></div>

        <!-- 如果需要导航按钮 -->
        <!--        <div class="swiper-button-prev"></div>-->
        <!--        <div class="swiper-button-next"></div>-->
        <!-- 如果需要滚动条 -->
        <!--        <div class="swiper-scrollbar"></div>-->
    </div>
</template>
<script>
    import "swiper/dist/css/swiper.css";
    import Swiper from "swiper";

    export default {
        name: "SwiperMobile",
        props: ["type", "banner",],
        data() {
            return {
                current: null,
                swiperOption: {
                    loop: true,
                    observeParents:true,
                    observer:true,
                    autoplay: {
                        delay: 2000,
                        disableOnInteraction: false,
                    },
                    // 如果需要分页器
                    speed: 2000,
                    pagination: {
                        el: ".swiper-pagination",
                        clickable: true,
                        type: this.type,
                    }
                }
            };
        },
        mounted() {

        },
        methods: {
            getSize(num) {
                return window.innerWidth * num / 1920 + "px";
            },
            buttonTy(item) {
                if (item.buttonType === '0') {
                    this.$router.push({path: '/product', query: {value: item.productId}})
                } else {
                    this.$router.push({path: '/part', query: {id: item.productId}})
                }
            }
        }
    };
</script>
<style scoped>
    .swiper-container5 {
        width: 100%;
        height: 100%;
        margin: 0;
        padding: 0;
        overflow: hidden;
        position: relative;
    }

    .swiper-wrapper {
    }

    .swiper-container5 .swiper-slide img {
        width: 100%;
        height: 100%;
    }

    .swiper-container5 .swiper-slide {
        background-size: cover !important;
        /* Center slide text vertically */

    }

    .swiper-container5 .swiper-pagination-bullet {
        cursor: pointer;
    }

    .swiper-pagination-fraction, .swiper-container5.swiper-container-horizontal > .swiper-pagination-bullets {
        top: unset !important;
        right: unset !important;
        bottom: 10px !important;
        width: 100% !important;
    }

    .swiper-pagination-fraction {
        padding: 0 5%;
        color: #ffffff;
        text-align: right;
    }

    .swiper-pagination {
        bottom: 50px;
        width: 100%;
    }

    .swiper-pagination-clickable .swiper-pagination-bullet {
        margin: 0 4px !important;
    }
</style>
