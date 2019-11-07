<template>
    <div class="product" v-if="part && part.length > 0" :style="{paddingTop: getSize(55), paddingBottom: getSize(100)}">
        <div class="swiper-container3">
            <swiper :options="swiperOption" v-if="part && part.length > 0" class="swiper-container">
                <!-- slides -->
                <swiper-slide class="swiper-item" v-for="item in part" :key='item.id'>
                    <div :style="{width: getSize(300), cursor: 'pointer',}"
                         @click="$router.push({path: '/part', query: {id: item.serialNumber}})">
                        <div :style="{background: '#F7F7F7', transform: item.type === '0' ? 'rotate(30deg)': '', borderRadius: '50%', width: getSize(180), marginTop: getSize(90), textAlign: 'center', height: getSize(180)}">
                            <img :style="{height: item.type === '0' ? '150%' : '110%', marginTop: item.type === '0' ? '-'+getSize(45) : '-'+getSize(15)}"
                                 :src="item.homeUrl" alt="">
                        </div>
                        <div class="bold"
                             :style="{marginTop: getSize(30), fontSize: getSize(20), color: '#2E292D', fontWeight: '500'}">
                            {{item.name}}
                        </div>
                        <div :style="{marginTop: getSize(8), fontSize: getSize(16), color: '#2E292D', marginBottom: getSize(15)}">
                            {{item.homeDescription}}
                        </div>
                        <div style="display: flex; align-items: center; flex-wrap: wrap" :style="{width: getSize(220)}">
                            <div v-for="ite in item.colourUrlList"
                                 :style="{width: getSize(16), marginRight: getSize(20), marginBottom: getSize(10), height: getSize(16), borderRadius: '50%'}">
                                <img width="100%" :src="ite" alt="">
                            </div>
                        </div>
                    </div>
                </swiper-slide>
            </swiper>
            <!-- Add Pagination -->

        </div>
        <div class="swiper-button-prev" :style="btn">
            <img :style="{width: getSize(24), height: getSize(24)}" src="../assets/icon/icon_prev_nor.svg" alt="">
        </div><!--左箭头。如果放置在swiper-container外面，需要自定义样式。-->
        <div class="swiper-button-next" :style="btn2">
            <img :style="{width: getSize(24), height: getSize(24)}" src="../assets/icon/icon_next_nor.svg" alt="">
        </div><!--右箭头。如果放置在swiper-container外面，需要自定义样式。-->
        <div :style="{position: 'absolute', width: '74%', height: '2px', backgroundRepeat: 'repeat', background: '#F2F1F9', top: getSize(242), left: '13%' }"></div>
    </div>
</template>
<script>
    import '../../public/swiper2.css'
    export default {
        name: "SliderMore",
        data() {
            return {
                current: null,
                btn: {
                    width: this.getSize(40),
                    height: this.getSize(40),
                    lineHeight: this.getSize(40),
                    borderRadius: '50%',
                    position: 'absolute',
                    top: this.getSize(242),
                    textAlign: 'center',
                },
                btn2: {
                    width: this.getSize(40),
                    height: this.getSize(40),
                    lineHeight: this.getSize(40),
                    borderRadius: '50%',
                    position: 'absolute',
                    top: this.getSize(242),
                    textAlign: 'center',
                },
                swiperOption: {
                    pagination: '.swiper-pagination',
                    loop: true,
                    speed: 2000,
                    slidesPerView: 'auto',
                    spaceBetween: 0,
                    slideToClickedSlide: true,
                    // 如果需要分页器
                    // 如果需要前进后退按钮
                    navigation: {
                        nextEl: '.swiper-button-next',
                        prevEl: '.swiper-button-prev',
                    },
                }
            }
        },
        props: ['part'],
        mounted() {
        },
        methods: {
            getSize(num) {
                if(window.innerWidth > 1750){
                    return window.innerWidth * num / 1920 + 'px'
                } else {
                    return window.innerWidth * num / 1360 + 'px'
                }
            }
        },
    }
</script>
<style scoped lang="less">
    .product {
        padding: 0 20%;
        position: relative;
        display: flex;
        align-items: center;

        .swiper-container3 {
            position: relative;
            overflow: hidden;
            width: 100%
        }
    }

    .swiper-container3 .swiper-slide {
        transition: 300ms;
        transform: scale(0.8);
        width: auto !important
    }

    .product .swiper-slide {

    }

    .product .swiper-slide-next {
        /*margin-left: 5%;*/
    }

    .product .swiper-button-prev, .product .swiper-button-next {
        background: #F7F7F7 !important;
    }

    .swiper-button-prev :focus, .swiper-button-next :focus {
        border: 0 !important;
        box-shadow: none !important;
    }

    .product .swiper-button-prev {
        margin-left: 15%;
    }

    .product .swiper-button-next {
        margin-right: 15%;
    }
</style>
