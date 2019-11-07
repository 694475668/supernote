<template>
    <div class="listItem" @click="$router.push({path: '/blog/detail', query: {id: item.id}})"
         :style="{width: width=== 750 ? '100%' : getSize(420), height: 'auto'}">
        <div :style="{width: width===750? '100%' : getSize(420)}" style="background: #ffffff">
            <span v-if="width === 1920" :style="{width: getSize(420), height: getSize(280)}"><img
                    width="100%" :src="item.urlPc" alt=""></span>
            <img v-if="width === 750" width="100%" :src="item.urlMobile" alt="">
            <div :style="{width: width=== 750 ? '100%' : getSize(400), paddingLeft: getSize(10), paddingRight: getSize(10)}">
            <span class="bold"
                  :style="{fontSize: width === 750 ? getSize(36) : getSize(24), textAlign: 'left',color:'#2E292D', fontWeight: '500', marginTop: getSize(20), marginBottom: getSize(15)}">{{item.title.slice(0,14)}}...</span>
                <div class="argument">
                    <div :style="countStyle">
                        <img :style="imgStyle" src="../assets/icon/icon_look_nor.svg" alt="">
                        <span :style="{fontSize:  width===750?getSize(24) : getSize(12),color:'#717379'}">{{item.viewCount}}</span>
                    </div>
                    <div :style="countStyle">
                        <img :style="imgStyle" src="../assets/icon/icon_comment_nor.svg" alt="">
                        <span :style="{fontSize:  width===750?getSize(24) : getSize(12),color:'#717379'}">{{item.commentsCount}}</span>
                    </div>
                    <div :style="countStyle">
                        <img :style="imgStyle" src="../assets/icon/icon_nor.svg" alt="">
                        <span :style="{fontSize:  width===750?getSize(24) : getSize(12),color:'#717379'}">{{item.thumbsCount}}</span>
                    </div>
                </div>
            </div>
            <div class="last"
                 :style="{width: width=== 750 ? '100%' : getSize(400), paddingLeft: getSize(10), paddingRight: getSize(10), marginTop: getSize(15), borderTop: '1px solid #F4F4F4', height: width=== 750 ? getSize(70) : getSize(50)}">
                <span :style="{fontSize: width===750?getSize(28) : getSize(18), color: '#333333', overflow: 'hidden'}">{{item.author}}</span>
                <span :style="{fontSize: width===750?getSize(28) : getSize(16), color: '#717379'}">{{item.updateTime.slice(0, 10)}}</span>
            </div>
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
            }
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
        background: #f7f7f7;

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
