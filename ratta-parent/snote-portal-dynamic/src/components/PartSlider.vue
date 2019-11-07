<template>
    <div class="partSlide">
        <div class="swiper-container-part" :style="{width: part && part.length < 4 ? getSize(parseInt(640/4*(part && part.length)) + 100) : getSize(740)}">
            <div class="swiper-wrapper" :style="{width: part && part.length < 4 ? getSize(640/4*(part && part.length)) : getSize(640)}">
                <div class="swiper-slide" v-for="item in part" @click="$emit('img', item)">
                    <div :style="{width: getSize(160), height: getSize(120)}">
                        <img width="100%" :src="item" alt="">
                    </div>
                </div>
            </div>
            <!-- Add Pagination -->
            <div v-if="part && part.length > 4" class="swiper-button-prev" :style="btn2">
                <img :style="{width: getSize(24), height: getSize(24)}" src="../assets/icon/icon_prev_nor.svg"
                     alt="">
            </div><!--左箭头。如果放置在swiper-container外面，需要自定义样式。-->
            <div v-if="part && part.length > 4" class="swiper-button-next" :style="btn">
                <img :style="{width: getSize(24), height: getSize(24)}" src="../assets/icon/icon_next_nor.svg"
                     alt="">
            </div><!--右箭头。如果放置在swiper-container外面，需要自定义样式。-->
        </div>
    </div>
</template>
<script>
  import "swiper/dist/css/swiper.css";
  import Swiper from "swiper";

  export default {
    name: "PartSlide",
    props: ["part"],
    data () {
      return {
        current: null,

        btn: {
          width: this.getSize(40),
          height: this.getSize(40),
          lineHeight: this.getSize(40),
          textAlign: "center",
          borderRadius: "50%",
          position: "absolute",
          top: this.getSize(65),
          right: 0
        },
        btn2: {
          width: this.getSize(40),
          height: this.getSize(40),
          lineHeight: this.getSize(40),
          textAlign: "center",
          borderRadius: "50%",
          position: "absolute",
          top: this.getSize(70),
          left: 0
        },
      };
    },
    mounted () {
      new Swiper(".swiper-container-part", {
        // loop: true,
        speed: 1000,
        slidesPerView: "auto",
        spaceBetween: 0,
        // slideToClickedSlide: true,
        // 如果需要分页器
        // 如果需要前进后退按钮
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev"
        }
        // 如果需要滚动条
        // scrollbar: '.swiper-scrollbar',
      });
    },
    methods: {
      getSize (num) {
        return window.innerWidth * num / 1920 + "px";
      }

    }
  };
</script>
<style scoped lang="less">
    .partSlide {
        display: flex;
        align-items: center;
        justify-content: center;
        .swiper-container-part {
            overflow: hidden;
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            .swiper-slide {
                cursor: pointer;
            }
        }
    }

    .swiper-container-part .swiper-slide {
        transition: 300ms;
        transform: scale(0.8);
        width: auto !important
    }

    .partSlide .swiper-slide {

    }

    .partSlide .swiper-slide-next {
        /*margin-left: 5%;*/
    }

    .partSlide .swiper-button-prev, .product .swiper-button-next {
        background: #F7F7F7 !important;
    }

    .swiper-button-prev :focus, .swiper-button-next :focus {
        border: 1px !important;
        box-shadow: 1px 1px 5px #ffffff;
    }

    .partSlide .swiper-wrapper {
        /*transform: translate3d(-140px, 0, 0) !important;*/
    }

    .partSlide .swiper-button-next {
    }
</style>
