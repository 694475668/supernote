<template>
    <div id="search">
        <div class="banner flex" :style="{ marginBottom: width===750? getSize(60) : getSize(80)}">
            <el-input
                    v-model="search"
                    :placeholder="$t('message.service.search')"
                    class="search"
                    @keyup.enter.native="handleIconClick"
                    @keyup.enter="handleIconClick"
                    :style="{width: getSize(600), background: '#EFF0F4 !important', borderRadius:  getSize(30),  height: width===750? getSize(80) : getSize(50), marginTop: getSize(50), opacity: 0.75}">
                <div :style="{height: width===750? getSize(80) : getSize(50), cursor: 'pointer', lineHeight: width===750? getSize(80) : getSize(50), marginRight: getSize(10)}" slot="suffix"
                     @click="handleIconClick">
                    <img :style="{width: width===750? getSize(35) : getSize(25), height: width===750? getSize(35) : getSize(25)}"
                         src="../assets/service/icon_sousuo_nor@2x.png" alt="">
                </div>
            </el-input>
        </div>
        <section :style="{paddingLeft: width===750?getSize(50):'13%',paddingRight: width===750?getSize(50):'13%',}">
            <div style="color: #000000" :style="{paddingTop: getSize(20), paddingBottom: getSize(40), borderTop: '1px solid #DBDBDB'}">
                <div :style="{fontSize: getSize(24)}"><span class="bold">“<span class="bold" style="color: red">{{search}}</span>”{{$t('message.search.result')}}</span></div>
                <div :style="{fontSize: getSize(14), marginTop: getSize(10)}"><span class="bold">{{$t('message.search.to')}}{{$store.state.searchTotal}}{{$t('message.search.end')}}</span></div>
            </div>
            <div v-if="width === 1920" v-for="item in this.$store.state.searchList" class="downloadItem"
                 :style="{ paddingBottom: getSize(53), marginBottom: getSize(53)}">
                <div>
                    <div :style="{ width: getSize(600), overflow: 'hidden', textOverflow:'ellipsis', whiteSpace: 'nowrap', fontSize: getSize(18), fontSize: '500', marginBottom: getSize(15)}">
                        <span class="bold" :style="{fontSize: getSize(18),}">{{item.title}}</span></div>
                    <div class="ql-snow ql-editor" :style="{height: 'auto', fontSize: getSize(14), width: getSize(755), marginBottom: getSize(30)}" v-html="item.description">{{item.description}}</div>
                </div>
                <a v-if="item.url" :href="item.url">
                    <div :style="{ width: getSize(144), height: getSize(42), textAlign: 'center', lineHeight: getSize(42), marginTop: getSize(35), background: '#000000', borderRadius: getSize(60), color: '#ffffff', marginLeft: getSize(54)}">
                        <span class="bold">{{$t('message.download')}}</span>
                    </div>
                </a>
            </div>
            <div v-if="width===750" v-for="item in this.$store.state.searchList"
                 :style="{ paddingBottom: getSize(40), marginBottom: getSize(40), borderBottom: '1px solid #DBDBDB'}">
                <div style="display: flex;width: 100%">
                    <div :style="{width: getSize(400)}">
                        <div :style="{ overflow: 'hidden', textOverflow:'ellipsis', whiteSpace: 'nowrap', fontSize: getSize(28), fontSize: '500', marginBottom: getSize(15)}">
                            <span style="color: #000000">{{item.title}}</span></div>
                        <div class="ql-snow ql-editor" :style="{height: 'auto', fontSize: getSize(24), color: '#777777', marginBottom: getSize(30)}" v-html="item.description">{{item.description}}</div>
                    </div>
                </div>

                <a v-if="item.url" :href="item.url"
                   :style="{display: 'block', width: getSize(467), marginLeft: getSize(150), marginTop: getSize(50)}">
                    <div :style="{ height: getSize(78), fontSize: getSize(28), lineHeight: getSize(78), textAlign: 'center',  background: '#000000', borderRadius: getSize(60), color: '#ffffff', }">
                        <span>{{$t('message.download')}}</span>
                    </div>
                </a>
            </div>
            <div v-if="width>750" class="pagination" :style="{ paddingBottom: getSize(80),}">
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page.sync="currentPage"
                        :page-size="dataVal.pageSize"
                        layout=" prev, pager, next"
                        :total="parseInt($store.state.searchTotal)">
                </el-pagination>
                <span style="padding: 0 15px; font-size: 14px; color: #000000">{{$t('message.pagination.go')}}</span>
                <el-input class="goPage" v-model="pageVal" type="text"></el-input>
                <span style="padding: 0  18px 0 12px; font-size: 14px; color: #000000">{{$t('message.pagination.page')}}</span>
                <el-button @click="goPage">{{$t('message.pagination.btn')}}</el-button>
            </div>
            <div v-if="width===750" class="pagination" :style="{ paddingBottom: getSize(120),}">

            </div>
        </section>
    </div>
</template>

<script>

    export default {
        components: {},
        name: 'Search',
        data() {
            return {
                imgWH: {
                    width: this.getSize(68),
                    height: this.getSize(68),
                },
                PageIndex: 1,
                search: this.$route.query.name,
                dialogVisible: false,
                total: this.$store.state.searchTotal,
                currentPage: 1,
                pageVal: null,
                width: null,
                dataVal: {
                    language: localStorage.getItem("language") === 'zh-CN' ? 'zh-CN' : 'en',
                    pageNo: 1,
                    pageSize: 10,
                    key: this.$route.query.name,
                }
            }
        },
        created() {
            this.$store.dispatch('search', this.dataVal)
        },
        computed: {
            data() {
                return this.$store.state.searchTotal, this.$store.state.searchList
            }
        },
        mounted() {
            window.scrollTo(0, 0);
            if (window.innerWidth < 960) {
                window.addEventListener('scroll', this.scrollBottom)
                this.width = 750;
            } else {
                this.width = 1920;
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
            handleIconClick() {
                if(this.search){
                    this.dataVal.key = this.search
                    this.$store.dispatch('search', this.dataVal)
                }
            },
            visibleDialog(type) {
                if (type) {
                    this.dialogVisible = true
                    document.querySelector('body, html').style.overflow = 'hidden'
                } else {
                    this.dialogVisible = false
                    document.querySelector('body, html').style.overflow = 'auto'
                }

            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`)
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`)
                this.dataVal.pageNo = val
                this.$store.dispatch('search', this.dataVal)

            },
            goPage() {
                if (Number(this.pageVal) > 0) {
                    this.dataVal.pageNo = this.pageVal
                    this.currentPage = Number(this.pageVal)
                    if(this.$store.state.searchTotal > (this.pageVal - 1)*10) {
                        this.$store.dispatch('search', this.dataVal)
                    }
                    this.pageVal = null
                } else {
                    this.pageVal = null
                }
            },
            scrollBottom() {
                let scrollH = document.body.scrollTop || document.documentElement.scrollTop;
                if (scrollH > this.PageIndex*(window.innerHeight) && this.$store.state.searchTotal > this.PageIndex * 10) {
                    this.PageIndex += 1
                    this.dataVal.pageSize = this.PageIndex * 10
                    this.$store.dispatch('search', this.dataVal)
                }

            },
        },
        beforeDestroy() {
            if(this.width===750){
                window.removeEventListener('scroll', this.scrollBottom)
            }
        },
    }
</script>

<style lang="less">
    #search {
        .banner {
            width: 100%;

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

            .downloadItem {
                border-bottom: 1px solid #E7E7E7;
                display: flex;
                justify-content: space-between;

                div {
                    div {
                        text-align: left;
                        color: #000000;
                    }
                }
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
    .search .el-input__inner:hover {
        border-color: #ffffff !important;
    }

    .search .el-input__inner {
        border-color: #ffffff !important;
        border-radius: 20px;
        color: #333333;
    }

    .banner .el-input__inner {
        background: #EFF0F4 !important;
    }
</style>
