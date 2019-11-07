<template>
    <div id="about">
        <div v-if="width> 750" class="banner flex" :style="{height: getSize(460)}">
            <div>
                <span class="bold searchTitle"
                      :style="{fontSize: getSize(36),}">{{$t('message.service.bug.title')}}</span>
            </div>
        </div>
        <section :style="{paddingLeft: width===750?getSize(50):'13%', paddingRight: width===750?getSize(50):'13%'}">
            <div class="content" :style="{marginTop: getSize(80), marginBottom: getSize(80)}">
                <div v-if="width> 750" class="left" :style="{width: getSize(200)}">
                    <div v-for="item in this.$store.state.faqList" :style="{marginBottom: item.status ? getSize(35) : getSize(40)}"
                         @click="tabChage(item)">
                        <span :class="item.status ? 'bold' : ''"
                              :style="{fontWeight: item.status ? '500' : 'normal', fontSize: item.status ? getSize(24) : getSize(18)}">{{item.title}}</span>
                        <div v-if="item.status"
                             :style="{width: getSize(160), borderBottom: '1px solid #000000', marginTop: getSize(15)}">
                            <div :style="{width: getSize(40), height: '2px', background: '#000000'}"></div>
                        </div>
                    </div>
                </div>
                <div class="right" :style="{width: getSize(1020)}">
                    <div :style="{fontSize: width===750?getSize(36):getSize(24), textAlign: 'left !important', marginBottom: getSize(25)}">
                        <span v-for="item in this.$store.state.faqList" class="bold" v-if="item.status">{{item.title}}</span></div>
                    <div class="question-answer" v-for="item in this.$store.state.faqList" v-if="item.status && width===750" :style="{fontSize: width===750?getSize(28):getSize(18), paddingBottom: getSize(15), textAlign: 'left !important', marginBottom: getSize(20)}">
                        <div class="question-answer-item" v-for="ite in item.itemList" :style="{marginBottom: getSize(20)}">
                            <span><b class="bold">{{ite.question}}</b></span>
                            <div :style="{marginTop: getSize(5)}">
                                <span style="display: inline-block" v-html="ite.answer">{{'A：'+ite.answer}}</span>
                            </div>
                        </div>
                    </div>
                    <div class="question-answer" v-for="item in this.$store.state.faqList" v-if="width===1920 && item.status"
                         :style="{fontSize: getSize(18), paddingBottom: getSize(15), textAlign: 'left !important', marginBottom: getSize(20)}">
                        <div class="question-answer-item" v-for="ite in item.itemList"  :style="{marginBottom: getSize(20)}">
                            <span><b class="bold">{{ite.question}}</b></span>
                            <div :style="{marginTop: getSize(5)}">
                                <span style="display: inline-block" v-html="ite.answer">{{'A：'+ite.answer}}</span>
                            </div>
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
        name: 'About',
        data() {
            return {
                imgWH: {
                    width: this.getSize(68),
                    height: this.getSize(68),
                },
                font: {
                    fontSize: this.getSize(14)
                },
                title: '',
                dialogVisible: false,
                faqId: null,
                width: null,
                dataVal: {
                    language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',
                    type: '1',
                    title: this.$route.query.title
                }
            }
        },
        created() {
            this.$store.dispatch('seoAll', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en', pageSerial: '3'})
            this.$store.dispatch('faq', this.dataVal)
        },
        computed: {
            data() {
                return this.$store.state.faqList
            }
        },
        mounted() {
            this.title = this.$route.query.title
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
            tabChage(item) {
                this.title = item.title
                this.dataVal.title = item.title
                this.$store.dispatch('faq', this.dataVal)
            },
        },
    }
</script>

<style lang="less">
    #about {
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

            .content {
                display: flex;
                justify-content: space-between;

                .left {
                    div {
                        cursor: pointer;
                        text-align: left;
                        color: #000000;
                    }
                }

                span {
                    color: #000000;
                }

                .right {

                    div {
                        text-align: left;

                        span {
                            color: #000000;
                            font-weight: 500;
                        }
                    }

                    .question-answer {
                        border-bottom: 1px solid #E7E7E7;

                        &:last-child {
                            border: 0;
                        }
                        .question-answer-item {
                            border-bottom: 1px solid #E7E7E7;

                            &:last-child {
                                border: 0;
                            }
                        }
                        span {
                            font-weight: normal;
                            display: block;
                        }
                    }

                }
            }

        }

        .footer {
            background: #111111
        }
    }


</style>
