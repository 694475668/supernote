<template>
    <div class="honor" :style="{height: getSize(1040)}" v-if="honor && honor.length > 0">
        <swiper :options="swiperOption" class="swiper-container">
            <!-- slides -->
            <swiper-slide class="swiper-item" v-for="item in honor" :style="{height: getSize(1040)}">
                <div style="text-align: center" :style="{height: getSize(1040), lineHeight: getSize(1040)}">
                    <img width="80%" :src="item.urlMobile" alt="">
                </div>
            </swiper-slide>
        </swiper>
    </div>
</template>
<script>
    import '../../public/swiper2.css'
    import Swiper from 'swiper';

    export default {
        name: "CenterSlider",
        data() {
            return {
                current: null,
                width: null,
                swiperOption: {
                    loop: true,
                    speed: 1000,
                    slidesPerView: 'auto',
                    spaceBetween: 0,
                    centeredSlides: true,
                    slideToClickedSlide: true,
                }
            }
        },
        props: ['honor'],
        mounted() {
            if (window.innerWidth < 1080) {
                this.width = 750;
            } else {
                this.width = 1920;
            }
        },
        methods: {
            getSize(num) {
                return window.innerWidth * num / 750 + 'px'
            }
        },
    }
</script>
<style scoped lang="less">
    .honor {
        background: url("../assets/mobile/index/bg_honor.jpg");
        background-size: cover;
        position: relative;

        .shadow {
            width: 100%;
            height: 100%;
            background: #000000;
            opacity: 0.3
        }
        .swiper-container2 {
            width: 100%;
            height: 100%;
            display: flex;
            align-items: center;
            overflow: hidden;
            .swiper-slide {
                display: flex;
                align-items: center;
            }
        }
    }
    /*.swiper-container2 .swiper-slide {*/
    /*    transition: 300ms;*/
    /*    transform: scale(0.8);*/
    /*    !*opacity: 0.05;*!*/
    /*}*/
    /*.swiper-container2 .swiper-slide-active {*/
    /*    width: 90% !important;*/
    /*    opacity: 1;*/
    /*}*/
    /*.swiper-container2 .swiper-slide-prev {*/
    /*    width: 85% !important;*/
    /*    margin-left: -65%;*/
    /*    opacity: 0.8;*/
    /*}*/
    /*.swiper-container2 .swiper-slide-next {*/
    /*    width: 85% !important;*/
    /*    margin-left: 0;*/
    /*    opacity: 0.8;*/
    /*}*/
</style>
