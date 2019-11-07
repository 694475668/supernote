<template>
    <div class="listItem" @click="$router.push({path: '/blog/detail', query: {id: item.id}})"
         :style="{width: width=== 750 ? '100%' : getSize(420), height: 'auto'}">
        <span v-if="width === 1920" :style="{width: getSize(420), height: getSize(280)}"><img
                width="100%" :src="item.urlPc" alt=""></span>
        <div v-if="width === 750">
            <img width="100%" :src="item.urlPc" alt="">
        </div>
        <div :style="{paddingLeft: getSize(11), paddingRight: getSize(11)}">
            <span class="bold"
                  :style="{fontSize: getSize(24), textAlign: 'left', fontWeight: '500', marginTop: getSize(20), marginBottom: getSize(15)}">{{item.title.slice(0,13)}}...</span>
            <div class="argument">
                <div :style="countStyle">
                    <img :style="imgStyle" src="../assets/icon/icon_look_nor.svg" alt="">
                    <span>{{item.viewCount}}</span>
                </div>
                <!--                <div :style="countStyle">-->
                <!--                    <img :style="imgStyle" src="../assets/icon/icon_comment_nor@2x.png" alt="">-->
                <!--                    <span>{{item.commentsCount}}</span>-->
                <!--                </div>-->
                <!--                <div :style="countStyle">-->
                <!--                    <img :style="imgStyle" src="../assets/icon/icon_nor@2x.png" alt="">-->
                <!--                    <span>{{item.thumbsCount}}</span>-->
                <!--                </div>-->
            </div>
        </div>
        <div class="last"
             :style="{paddingLeft: getSize(11), paddingRight: getSize(11), marginTop: getSize(15), borderTop: '1px solid #F4F4F4', height: width=== 750 ? getSize(70) : getSize(50)}">
            <span :style="{fontSize: this.getSize(20), color: '#333333', overflow: 'hidden'}">{{item.author}}</span>
            <span :style="{fontSize: this.getSize(14), color: '#717379'}">{{item.updataTime}}</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'ListItem',
        props: ['item'],
        data() {
            return {
                countStyle: {
                    marginRight: this.getSize(26),
                    fontSize: this.getSize(16),
                    display: 'flex',
                    alignItems: 'center'
                },
                imgStyle: {
                    width: this.getSize(24),
                    height: this.getSize(24),
                    marginRight: this.getSize(5)
                },
                width: null,
                dataVal: {
                    language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',
                    pageNo: 0,
                    pageSize: 0,
                    sortField: '',
                    sortRules: '',
                    id: this.item.id
                },
            }
        },
        watch: {
            '$route'(to, from) {
                this.dataVal.id = this.item.id
                this.$store.dispatch('blogDetail', this.dataVal)
                this.$store.dispatch('blogCommentList', this.dataVal)
            },
        },
        mounted() {
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

        },
    }
</script>

<style scoped lang="less">
    .listItem {
        background: #ffffff;

        span {
            display: block;
            color: #2E292D;
        }

        .argument {
            display: flex;

            span {
                display: inline-block;
                color: #717379;
            }
        }

        .last {
            display: flex;
            justify-content: space-between;
            align-items: center;

            span {
                display: inline-block;
            }
        }
    }
</style>
