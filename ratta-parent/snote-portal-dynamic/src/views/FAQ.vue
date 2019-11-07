<template>
    <div id="faq">
        <div class="banner flex"
             :style="{height: getSize(460),background: width===750? 'url('+background+')' : 'url('+background2+')', marginBottom: width===750?getSize(60):getSize(80)}">
            <div>
                <span class="searchTitle bold"
                      :style="{fontSize: getSize(36),}">{{$t('message.service.faq.listTitle')}}</span>
            </div>
        </div>
        <section
                :style="{paddingBottom: getSize(22), paddingLeft: width===750?getSize(50):'15%', paddingRight: width===750?getSize(50):'15%'}">
            <div v-if="width>750" class="faqList">
                <div v-for="item in this.$store.state.faqList" :style="{marginBottom: getSize(58), width: '33.33%', cursor: 'pointer'}">
                    <div @click="$router.push({path: '/service/faq/detail', query: {title: item.title}})">
                        <span class="bold" :style="{fontSize: getSize(24), fontWeight: '500'}">{{item.title}}</span>
                        <div :style="{width: getSize(40), height:'2px', background: '#000000', marginTop: getSize(8), marginBottom: getSize(10)}"></div>
                        <div v-for="(i, index) in item.itemList" v-if="index < 7" style="display:flex; alignItems: flex-start" :style="{ marginBottom: getSize(5)}">
                            <span :style="{fontSize: getSize(14), display: 'inline-block', display: 'flex', alignItems: 'flex-start'}">-</span>
                            <span :style="{fontSize: getSize(14), display: 'inline-block'}">{{i.question.replace('Q：','').replace('Q:','')}}</span>
                        </div>
                    </div>
                    <div v-if="item.itemList.length > 6"  @click="$router.push({path: '/service/faq/detail', query: {title: item.title, item: JSON.stringify(item.itemList)}})">
                        <span :style="{fontSize: getSize(14), color: '#C9A060'}">{{$t('message.more')}}</span>
                        <img :style="{width: getSize(20), height: getSize(20)}" src="../assets/icon/icon_more.svg"
                             alt="">
                    </div>
                </div>
            </div>
            <div v-if="width === 750">
                <div v-for="item in this.$store.state.faqList" :style="{marginBottom: getSize(50), cursor: 'pointer'}">
                    <div @click="$router.push({path: '/service/faq/detail', query: {title: item.title}})">
                        <span class="bold" :style="{fontSize: getSize(36), fontWeight: '500'}">{{item.title}}</span>
                        <div v-for="i in item.itemList"
                             :style="{paddingTop: getSize(20),paddingBottom: getSize(20), minHeight: getSize(68), display:'flex', justifyContent: 'space-between', alignItems: 'center', borderBottom: '1px solid #E5E5E5'}">
                            <span :style="{fontSize: getSize(28),}">{{i.question}}</span>
                            <img :style="{width:getSize(14), height:getSize(22)}"
                                 src="../assets/mobile/mobile-icon/icon_arrow.png" alt="">
                        </div>
                    </div>

                </div>
            </div>
        </section>
    </div>
</template>
<script>

    export default {
        components: {},
        name: 'FAQ',
        data() {
            return {
                search: '',
                imgWH: {
                    width: this.getSize(68),
                    height: this.getSize(68),
                },
                width: null,
                dialogVisible: false,
                background: require("../assets/mobile/other/banner-down.png"),
                background2: require("../assets/service/banner_changjianwenti.jpg"),
                dataVal: {
                    language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',
                    type: '0',
                }
            }
        },
        created() {
            this.$store.dispatch('seoAll', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en', pageSerial: '2'})
            this.$store.dispatch('faq', this.dataVal)
        },
        computed: {
            data() {
                return this.$store.state.faqList
            }
        },
        mounted() {
            window.scrollTo(0, 0);
            if (window.innerWidth < 961) {
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
            handleIconClick(ev) {
                console.log(ev)
            },
            visibleDialog(type) {
                if (type) {
                    this.dialogVisible = true
                    document.querySelector('body, html').style.overflow = 'hidden'
                } else {
                    this.dialogVisible = false
                    document.querySelector('body, html').style.overflow = 'auto'
                }

            }
        },
    }
</script>

<style lang="less">
    #faq {
        .banner {
            width: 100%;
            background: url("../assets/service/banner_changjianwenti.jpg");
            background-size: cover;

            .searchTitle {
                color: #ffffff;
                font-weight: 500
            }

            span {
                display: block;
            }
        }

        .flex {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        section {
            background: #ffffff;
            overflow-y: auto;

            span {
                color: #000000
            }

            .faqList {
                display: flex;
                flex-wrap: wrap;

                div {
                    text-align: left;
                    width: 32%;

                    div {
                        width: 100%;
                    }
                }
            }
        }

        .footer {
            background: #111111
        }
    }


</style>
