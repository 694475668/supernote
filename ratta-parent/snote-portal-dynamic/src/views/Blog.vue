<template>
    <div id="blog">
        <div v-if="width===1920" class="banner">
            <img v-if="$i18n.locale==='zh-CN'" width="100%" src="../assets/blog/banner.png" alt="">
            <img v-if="$i18n.locale==='en-US'" width="100%" src="../assets/blog/EN_blog_banner.jpg" alt="">
        </div>
        <Section v-if="width===1920" :style="{paddingTop: getSize(80)}">
            <div class="list-items">
                <ListItem v-for="item in this.$store.state.blogList"
                          :item="item"
                          :style="{cursor: 'pointer', marginBottom: getSize(80), width: '33.333%'}"/>
            </div>
            <div v-if="$store.state.blogTotal > 0" class="pagination" :style="{ paddingBottom: getSize(80),}">
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page.sync="currentPage"
                        :page-size="dataVal.pageSize"
                        layout=" prev, pager, next"
                        :total="$store.state.blogTotal">
                </el-pagination>
                <span style="padding: 0 15px; font-size: 14px; color: #000000">{{$t('message.pagination.go')}}</span>
                <el-input class="goPage" v-model="pageVal" type="text"></el-input>
                <span style="padding: 0  18px 0 12px; font-size: 14px; color: #000000">{{$t('message.pagination.page')}}</span>
                <el-button @click="goPage">{{$t('message.pagination.btn')}}</el-button>
            </div>
        </Section>
        <Section v-if="width===750" :style="{padding: getSize(50)}">
            <div>
                <ListItem v-for="item in this.$store.state.blogList"
                          :item="item"
                          :style="{cursor: 'pointer', marginBottom: getSize(50) }"/>
            </div>

        </Section>
    </div>
</template>

<script>
    import ListItem from '@/components/ListItem.vue'

    export default {
        components: {
            ListItem,
        },
        name: 'Blog',
        data() {
            return {
                total: 0,
                pageSize: 9,
                currentPage: 1,
                PageIndex: 1,
                pageVal: null,
                width: null,
                dataVal: {
                    language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',
                    pageNo: 1,
                    pageSize: 9,
                    sortField: '',
                    sortRules: '',
                }
            }
        },
        mounted() {
            this.total = this.$store.state.blogTotal
            window.scrollTo(0, 0);
            if (this.list && this.list.length > 0) {
                this.total = this.list.length
            }
            if (window.innerWidth < 961) {
                window.addEventListener('scroll', this.scrollBottom)
                this.width = 750

            } else {
                this.width = 1920

            }
        },
        created() {
            this.$store.dispatch('blog', this.dataVal)
            this.$store.dispatch('seoAll', {language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en', pageSerial: '1'})
        },
        computed: {
            data() {
                return this.$store.state.blogList, this.$store.state.blogTotal
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

            handleSizeChange(val) {
                // console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                // console.log(`当前页: ${val}`);
                this.dataVal.pageNo = val
                this.$store.dispatch('blog', this.dataVal)
            },
            goPage() {
                if (Number(this.pageVal) > 0) {
                    this.dataVal.pageNo = this.pageVal
                    this.currentPage = Number(this.pageVal)
                    if (this.$store.state.blogTotal > (this.pageVal - 1) * 9) {
                        this.$store.dispatch('blog', this.dataVal)
                    }
                    this.pageVal = null
                } else {
                    this.pageVal = null
                }
            },
            scrollBottom() {
                let scrollH = document.body.scrollTop || document.documentElement.scrollTop;
                if (scrollH > this.PageIndex*(window.innerHeight) && this.$store.state.blogTotal > this.PageIndex * 10) {
                    this.PageIndex += 1
                    this.dataVal.pageSize = this.PageIndex * 10
                    this.$store.dispatch('blog', this.dataVal)
                }

            },
        },
        beforeDestroy() {
            if (this.width === 750) {
                window.removeEventListener('scroll', this.scrollBottom)
            }
        },
    }
</script>

<style lang="less">
    #blog {
        section {
            background: #F7F7F7;
            padding-left: 13%;
            padding-right: 13%;

            .list-items {
                display: flex;
                flex-wrap: wrap;
                align-content: flex-start;
                /*justify-content: space-between;*/
            }

            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;

                .inputPage {

                }
            }
        }

        .footer {
            background: #111111
        }
    }

    .goPage.el-input {
        width: auto !important
    }

    .goPage .el-input__inner:hover, .el-button {
        border-color: #D9D9D9 !important
    }

    .el-button {
        height: 35px !important;
        line-height: 35px !important;
        padding: 0 !important;
        width: 70px;
        font-size: 14px !important;
        font-weight: normal !important;
        color: #000000 !important;
    }

    .goPage .el-input__inner {
        width: 60px !important;
        height: 35px !important;
        line-height: 35px !important;
        padding-left: 15px !important;
        border-color: #D9D9D9 !important;
    }

    .el-pager li {
        width: 35px !important;
        height: 35px !important;
        line-height: 35px !important;
        padding: 0 !important;
        text-align: center;
        border: 1px solid #D9D9D9;
        border-radius: 4px;
        margin-left: 10px !important;
        color: #000000
    }

    .el-pagination {
        font-weight: normal !important;
    }

    .el-pagination .btn-next, .el-pagination .btn-prev, .inputPage {
        border: 1px solid #D9D9D9;
        border-radius: 4px;
    }

    .el-pagination button {
        width: 35px !important;
        height: 35px !important;
        line-height: 35px !important;
        padding: 0 !important;
        text-align: center;
    }

    .el-pagination .btn-next {
        margin-left: 10px !important;
    }

    .el-pager li.active {
        color: #ffffff !important;
        background: #C9A060 !important;
        border-color: #C9A060 !important;
    }

    .el-pager li.active + li {
        border-left: 1px solid #D9D9D9 !important;
    }

    .el-button:hover, .el-pager li:hover {
        color: #ffffff !important;
        background: #C9A060 !important;
        border-color: #C9A060 !important;
    }

    .el-button:focus {
        color: #000000 !important;
        background: #ffffff !important;
        border-color: #D9D9D9 !important;
    }

    .el-pager li.btn-quicknext, .el-pagination .btn-next, .el-pager li.btn-quickprev, .el-pagination .btn-prev {
        color: #000000 !important;
    }

    .el-pagination button:hover {
        color: #4e4e4e !important;
    }

    .pagination {
        margin-bottom: 0 !important;
    }
</style>
