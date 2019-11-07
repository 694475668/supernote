<template>
    <div id="app" v-if="isRouterAlive">
        <HeaderMobile v-if="mobile2Show === true"/>
        <Header v-if="mobileShow === true"/>
        <router-view/>
        <Footer v-if="mobileShow === true" class="footer"/>
        <FooterMobile v-if="mobile2Show === true" class="footer"/>
    </div>
</template>
<script>
    // @ is an alias to /src
    import Header from "@/components/Header.vue";
    import HeaderMobile from "@/components/HeaderMobile.vue";
    import FooterMobile from "@/components/FooterMobile.vue";
    import Footer from "@/components/Footer.vue";

    export default {
        components: {
            Header,
            HeaderMobile,
            FooterMobile,
            Footer
        },
        provide() {
            return {
                reload: this.reload
            };
        },
        data() {
            return {
                isRouterAlive: true,
                mobileShow: false,
                mobile2Show: false,
                toData: {}
            };
        },
        mounted() {
            this.$router.afterEach((to) => {
                this.toData = to
                // console.log(to)
            });
            window.scrollTo(0, 0);
            window.addEventListener("scroll", this.handleScroll);
            this.height = window.innerHeight;
            if (window.innerWidth > 960) {
                this.mobileShow = true;
            } else {
                this.mobile2Show = true;
            }
        },
        methods: {
            handleScroll() {
                let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
                if (scrollTop > 0) {
                    if(this.toData.name === 'A5' || this.toData.name === 'A6'){
                        if (window.innerWidth > 960) {
                            document.querySelector("#header").style.position = "unset";
                        } else {
                            document.querySelector("#header-mobile").style.position = "unset";
                        }
                        document.querySelector(".sub-nav").style.position = "fixed";
                        document.querySelector(".sub-nav").style.top = "0";
                    } else {
                        if (window.innerWidth > 960) {
                            document.querySelector("#header").style.position = "fixed";
                        } else {
                            document.querySelector("#header-mobile").style.position = "fixed";
                        }
                    }
                } else {
                    if(this.toData.name === 'A5' || this.toData.name === 'A6'){
                        if (window.innerWidth > 960) {
                            document.querySelector("#header").style.position = "unset";
                        } else {
                            document.querySelector("#header-mobile").style.position = "unset";
                        }
                        document.querySelector(".sub-nav").style.position = "unset";
                        document.querySelector(".sub-nav").style.top = "unset";
                    } else {
                        if (window.innerWidth > 960) {
                            document.querySelector("#header").style.position = "unset";
                        } else {
                            document.querySelector("#header-mobile").style.position = "unset";
                        }
                    }

                }
            },
            reload() {
                this.isRouterAlive = false;
                this.$nextTick(function() {
                    this.isRouterAlive = true;
                });
            }
        },
        destroyed() {
            window.removeEventListener("scroll", this.handleScroll);
        }
    };
</script>

<style lang="less">
    #app {
        color: #2c3e50;
        .footer {
            background: #111111
        }
    }
    @font-face {
        font-family: 'PingFang Bold';  //重命名字体名
        src: url('./styles/fonts/Bold/PingFangMedium.ttf');  //引入字体
    }
    @font-face {
        font-family: 'PingFang Medium';  //重命名字体名
        src: url('./styles/fonts/Medium/PingFangBlock.ttf');  //引入字体
    }
    @font-face {
        font-family: 'PingFang Regular';  //重命名字体名
        src: url('./styles/fonts/Regular/PingFangRegular.ttf');  //引入字体
    }

    * {
        font-family: 'PingFang Regular', "微软雅黑", 'Songti TC', "Helvetica Neue", Helvetica, Arial, sans-serif;
    ;
    }
    .medium {
        font-family: 'PingFang Medium', "微软雅黑", 'Songti TC', "Helvetica Neue", Helvetica, Arial, sans-serif;  //重命名字体名
    }
    .bold {
        font-family: 'PingFang Bold', "微软雅黑", 'Songti TC', "Helvetica Neue", Helvetica, Arial, sans-serif;  //重命名字体名
    }
    .song {
        font-family: 'Songti TC', "微软雅黑" , "Helvetica Neue", Helvetica, Arial, sans-serif;  //重命名字体名
    }
    .question-input .question-input-item .el-input .el-input__inner, .el-textarea .el-textarea__inner {
        height: 100% !important;
        line-height: unset !important;
        background: #EFF0F4 !important;
        border: 0;
    }

    .question-input .question-input-item .el-input.el-input--suffix {
        height: 100%;
    }

    .question-input .question-input-item .el-input .el-input__inner:hover, .question-input .question-input-item .el-input .el-input__inner:focus, .el-textarea .el-textarea__inner:hover, .el-textarea .el-textarea__inner:focus {
        border: 0;
    }

    .el-icon-arrow-up:before {
        content: "\E78F" !important;
    }

    .question-input-item .el-input.el-input--suffix .el-input__icon {
        line-height: unset;
    }
    .question-input-item .phone .el-input .el-input__inner {
        padding-left: 50px !important;
    }

    #a5 section .question-dialog .question-dialog-body div .question-input div {
        height: 100%
    }

    .el-input__suffix {
        right: 0;
    }
	
	.el-drawer__body {
		flex: unset;
		overflow: scroll
	}
    .el-textarea .el-textarea__inner { // 然后找到对应的类名，在这里将拉伸去掉即可
        resize: none;
    }
    .el-textarea .el-input__count {
        background: 0 !important;
    }
    .el-form-item__content {
        line-height: unset !important;
    }
    .el-form-item {
        margin-bottom: 0 !important;
    }
    .el-dialog__wrapper:focus {
        border: 0 !important;
        box-shadow: unset !important;
        outline: unset !important;
    }
    .v-modal:focus{
        outline: unset !important;
        border: 0 !important;
        box-shadow: unset !important;
    }
    .swiper-pagination-clickable .swiper-pagination-bullet {
        margin: 0 4px !important;
    }
</style>
