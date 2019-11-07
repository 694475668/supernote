<template>
    <div id="home">
        <section>
            <Swiper v-if="width > 750" :banner="this.$store.state.bannerList"/>
            <SwiperMobile :type="type" :banner="this.$store.state.bannerList" v-if="width===750"/>
            <Supernote/>
            <SliderMore :part="this.$store.state.partList" v-if="width > 750"/>
            <SwiperMoreMobile v-if="width===750" :slidesPerView="'auto'" :part="this.$store.state.partList" :status="1"/>
            <CenterSlider v-if="width===1920" :honor="this.$store.state.honorList"/>
            <CenterSlider2 v-if="width===750" :honor="this.$store.state.honorList"/>
        </section>
    </div>
</template>

<script>
    // @ is an alias to /src
    import Swiper from "@/components/Swiper.vue";
    import SwiperMobile from "@/components/SwiperMobile.vue";
    import Supernote from "@/components/Supernote.vue";
    import CenterSlider from "@/components/CenterSlider.vue";
    import CenterSlider2 from "@/components/CenterSlider2.vue";
    import SliderMore from "@/components/SliderMore.vue";
    import SwiperMoreMobile from "@/components/SwiperMoreMobile.vue";
    import { mapState } from 'vuex'
    export default {
        components: {
            Swiper,
            SwiperMobile,
            Supernote,
            SliderMore,
            CenterSlider,
            CenterSlider2,
            SwiperMoreMobile
        },
        name: "Home",
        inject: ["reload"],
        data() {
            return {
                height: null,
                width: null,
                type: 'bullets',
                dataVal: {
                    language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',
                    pageNo: 0,
                    pageSize: 0,
                    sortField: '',
                    sortRules: '',
                }
            };
        },
        created() {
            this.$store.dispatch('banner', {
                language: this.$i18n.locale === 'zh-CN' ? 'zh-CN' : 'en',
                pageNo: 0,
                pageSize: 0,
                sortField: '',
                sortRules: '',
            })
            this.$store.dispatch('honor', {
                language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',
                pageNo: 0,
                pageSize: 0,
                sortField: '',
                sortRules: '',
            })
            this.$store.dispatch('part', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en'})
            this.$store.dispatch('seoAll', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en', pageSerial: '1'})
        },
        computed: {
            data() {
                return this.$store.state.bannerList, this.$store.state.partList, this.$store.state.honorList
            }
        },
        mounted() {
            window.scrollTo(0, 0);
            this.height = window.innerHeight;
            if (window.innerWidth < 1080) {
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
            }
        },
        destroyed() {

        }
    };
</script>
<style lang="less">
    #home {
        section {
            overflow: hidden;
        }
    }

</style>
