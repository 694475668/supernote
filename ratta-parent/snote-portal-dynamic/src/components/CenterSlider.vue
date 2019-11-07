<template>
    <div class="honor" :style="{height: getSize(880)}" v-if="honor && honor.length > 0">
        <swiper :options="swiperOption" class="swiper-container">
            <!-- slides -->
            <swiper-slide class="swiper-item" v-for="item in honor" :style="{width: getSize(1500)+' !important', height: getSize(880), lineHeight: getSize(880)}">
                <div style="text-align: center;" :style="{overflow: 'hidden', margin: '0 auto', height: getSize(720)}">
                    <img height="100%" :src="item.urlPc" alt="">
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
                    slidesPerView: 3,
                    spaceBetween: 0,
                    centeredSlides: true,
                    slideToClickedSlide: true,
                    pagination:'.swiper-pagination',
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
                return window.innerWidth * num / 1920 + 'px'
            }
        },
    }
</script>
<style scoped lang="less">
    .honor {
        background: url("../assets/index/img_homepage_4.png");
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
    .honor .swiper-container .swiper-slide-active {
        width: 85% !important;
        opacity: 1;
    }
    .honor .swiper-container .swiper-slide-prev {
        width: 80% !important;
        margin-left: -72%;
        opacity: 0.8;
    }
    .honor .swiper-container .swiper-slide-next {
        width: 80% !important;
        margin-left: 0;
        opacity: 0.8;
    }
    .honor .swiper-container .swiper-slide-duplicate-active, .honor .swiper-container .swiper-slide-duplicate-next, .honor .swiper-container .swiper-slide-duplicate-prev, .honor .swiper-container .swiper-slide-duplicate-prev {
        opacity: 0;
    }
</style>
