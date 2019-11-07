<template>
    <div class="swiper-container6"
         :style="{height: status === 2?'350px':'250px', minHeight:  status === 2?'350px':'250px', background:status === 1?'#F7F7F7':0, padding:status === 1?'0 25px':0}">
        <div class="swiper-wrapper">
            <div class="swiper-slide" v-for="item in part"  v-if="status === 1">
                <div @click="$router.push({path: '/part', query: {id: item.serialNumber}})">
                    <div style="width: 80px; height: 86px; text-align: center; margin: 0 auto; margin-top: 20px">
                        <img :style="{transform: item.type === '0' ? 'rotate(30deg)': '', height: '100%',}" :src="item.homeUrl"
                             alt="">
                    </div>
                    <div :style="{padding: '0 15px', marginTop: '15px', textAlign: 'left',}">
                        <div class="bold" :style="{textAlign: 'left', fontSize: '14px', color: '#000000'}">
                            {{item.name}}
                        </div>
                        <div :style="{textAlign: 'left', fontSize: '12px', color: '#777777'}">{{item.homeDescription}}</div>
                    </div>
                </div>
            </div>
            <div v-if="status === 2" class="swiper-slide" v-for="item in list" style="width: 100%">
                <div>
                    <ListItem2 :item="item"/>
                </div>
            </div>
        </div>
        <!-- 如果需要分页器 -->
        <!--        <div class="swiper-pagination"></div>-->

        <!-- 如果需要导航按钮 -->
        <!--        <div class="swiper-button-prev"></div>-->
        <!--        <div class="swiper-button-next"></div>-->
        <!-- 如果需要滚动条 -->
        <!--        <div class="swiper-scrollbar"></div>-->
    </div>
</template>
<script>
    import Swiper from "swiper";
    import ListItem2 from '@/components/ListItem2.vue'

    export default {
        components: {
            ListItem2,
        },
        name: "SwiperMoreMobile",
        props: ["type", 'status', 'list', 'slidesPerView', "part", "spaceBetween"],
        data() {
            return {
                current: null,
                dataVal: {
                    language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',
                    pageNo: 0,
                    pageSize: 0,
                    sortField: '',
                    sortRules: '',
                    id: this.$route.query.id ? this.$route.query.id : null
                },
            };
        },
        mounted() {
            new Swiper(".swiper-container6", {
                // loop: true,
                slidesPerView: this.slidesPerView,
                spaceBetween: 10,
                autoplay: {
                  delay: 2000,
                },
                // 如果需要分页器
                speed: 2000,
                // 如果需要前进后退按
            });
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
        }
    };
</script>
<style scoped>
    .swiper-container6 {
        width: 100%;
        height: 100%;
        margin: 0;
        padding: 0;
        overflow: hidden;
        position: relative;
    }

    .swiper-wrapper {
    }

    /*.swiper-container6 .swiper-slide img {*/
    /*    width: 100%;*/
    /*    height: 100%;*/
    /*}*/

    .swiper-container6 .swiper-slide {
        width: 60%;
        height: 200px;
        background: #ffffff;
        margin-top: 25px;
        margin-bottom: 25px;
        /* Center slide text vertically */

    }

    .swiper-container6 .swiper-pagination-bullet {
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
</style>
