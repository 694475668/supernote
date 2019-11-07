<template>
    <div class="question-dialog" v-if="dialogVisible">
        <div class="question-dialog-shadow" @click="visibleDialog()"></div>
        <div class="question-dialog-body" v-if="!success" :style="{width: getSize(637), margin: '0 auto'}">
                    <span class="closeBtn"
                          :style="{width: getSize(30), height: getSize(30), cursor: 'pointer', top: getSize(10), right: getSize(10)}"
                          @click="visibleDialog()">
                        <img width="100%" src="../assets/service/icon_close_nor.png" alt="">
                    </span>
            <div :style="{paddingTop: getSize(40), paddingRight: getSize(70), paddingLeft: getSize(70), paddingBottom: getSize(30)}">
                <div style="text-align: center">
                    <span class="title" :style="{fontSize: getSize(38), color: '#222328'}">{{title}}</span>
                </div>
                <el-form :model="ruleForm" :status-icon="false" :rules="rules" ref="ruleForm" @keyup.enter="postBug('ruleForm')">
                    <div class="question-input">
                        <div class="question-input-item" :style="{marginTop: getSize(30), }">
                            <div>
                                <span>{{$t("message.service.question.name")}}</span><span
                                    style="color: #C9A060">*</span>
                            </div>
                            <el-form-item prop="name">
                                <el-input
                                        v-model="ruleForm.name"
                                        :placeholder="$t('message.service.question.placeholderName')"
                                        class="search question"
                                        :style="{width: getSize(320), height: getSize(50), overflow: 'hidden', borderRadius: getSize(25), marginTop: getSize(10)}">
                                </el-input>
                            </el-form-item>
                        </div>
                        <div class="question-input-item" :style="{marginTop: getSize(20)}">
                            <div>
                                <span>{{$t("message.service.question.mail")}}</span><span
                                    style="color: #C9A060">*</span>
                            </div>
                            <el-form-item prop="email">
                                <el-input
                                        v-model="ruleForm.email"
                                        :placeholder="$t('message.service.question.placeholderPhone')"
                                        class="search question"
                                        :style="{width: getSize(320), height: getSize(50), overflow: 'hidden', borderRadius: getSize(25), marginTop: getSize(10)}">
                                </el-input>
                            </el-form-item>
                        </div>
                        <div class="question-input-item" style="display: flex; align-items: center"
                             :style="{width: getSize(150), marginTop: getSize(20)}">
                            <div>
                                <div>
                                    <span>{{$t("message.service.question.code")}}</span>
                                </div>
                                <div :style="{marginTop: getSize(10)}">
                                    <el-form-item>
                                        <el-select
                                                :style="{width: getSize(140), height: getSize(50), overflow: 'hidden', borderRadius: getSize(25),}"
                                                v-model="ruleForm.codeValue" @change="changeCode"
                                                placeholder="">
                                            <el-option
                                                    v-for="(item, index) in countryCode"
                                                    :key="index"
                                                    :label="item.value"
                                                    :value="item.code+','+item.value">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>
                                </div>
                            </div>
                            <div :style="{marginLeft: getSize(10)}">
                                <div>
                                    <div>
                                        <span>{{$t("message.service.question.phone")}}</span>
                                    </div>
                                    <div class="phone" style="position: relative; display: flex; align-items: center">
                                        <el-form-item prop="phone">
                                            <el-input
                                                    v-model="_phone"
                                                    class="search question"
                                                    minlength="8"
                                                    maxlength="15"
                                                    :style="{width: getSize(290), height: getSize(50), overflow: 'hidden', borderRadius: getSize(25), marginTop: getSize(10)}">
                                            </el-input>
                                        </el-form-item>
                                        <div :style="{position: 'absolute', left: getSize(10), height: getSize(50) +' !important', display: 'flex', alignItems: 'center', top: getSize(10)}">
                                            <span>{{code}}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div v-if="showSort" class="question-input-item" :style="{marginTop: getSize(20), }">
                            <div>
                                <span>{{$t("message.service.question.sort")}}</span><span
                                    style="color: #C9A060">*</span>
                            </div>
                            <div style="display: flex; align-items: center">
                                <el-form-item prop="sort">
                                    <el-input
                                            v-model="ruleForm.sort"
                                            :placeholder="$t('message.service.question.sortPlaceholder')"
                                            class="search question"
                                            :style="{width: getSize(320), height: getSize(50), overflow: 'hidden', borderRadius: getSize(25), marginTop: getSize(10)}">
                                    </el-input>
                                </el-form-item>
                                <!--                            <div :style="{marginLeft: getSize(5)}">-->
                                <!--                                <img :style="{width: getSize(20), height: getSize(20)}"-->
                                <!--                                     src="../assets/service/icon_tanhao.png" alt="">-->
                                <!--                                <span :style="{fontSize: getSize(7), color: '#666666'}">{{$t('message.service.question.sortSon')}}</span>-->
                                <!--                            </div>-->
                            </div>
                        </div>
                    </div>
                    <div :style="{marginTop: getSize(20), height: getSize(180), position: 'relative'}">
                        <div>
                            <span>{{$t("message.service.question.describe2")}}</span><span
                                style="color: #C9A060">*</span>
                        </div>
                        <el-form-item prop="textarea">
                            <el-input
                                    maxlength="500"
                                    type="textarea"
                                    v-model="ruleForm.textarea"
                                    :placeholder="$t('message.service.question.text')"
                                    class="search question"
                                    show-word-limit
                                    :style="{width: '100%', height: getSize(100), marginTop: getSize(10)}">
                            </el-input>
                        </el-form-item>
                    </div>
                    <div @click="btnClick?null:postBug('ruleForm')"
                         :style="{width: getSize(250), margin: '0 auto', color: '#ffffff', cursor: 'pointer', fontSize: getSize(24), background: '#C9A060', borderRadius: getSize(25), height: getSize(48), display: 'flex', alignItems: 'center', justifyContent: 'center'}">
                        <span>{{$t("message.service.question.button")}}</span>
                    </div>
                </el-form>
            </div>
        </div>
        <div class="question-dialog-body" v-if="success"
             :style="{width: getSize(637), height: getSize(500), minHeight: getSize(500),  margin: '0 auto'}">
            <div :style="{width: getSize(60), height: getSize(60), margin: '0 auto', marginTop: getSize(60)}">
                <img src="../assets/icon/icon_tijiaochenggong.svg" alt="">
            </div>
            <div :style="{textAlign: 'center', fontSize: getSize(42), marginTop: getSize(50)}">
                <span>{{$t('message.service.question.sucTitle')}}</span>
            </div>
            <div v-if="!chose" :style="{textAlign: 'center', fontSize: getSize(24), marginTop: getSize(28)}">
                <span>{{$t('message.service.question.sucMeg').split('！')[0]}}！</span><br>
                <span>{{$t('message.service.question.sucMeg').split('！')[1]}}</span>
            </div>
            <div v-if="chose" :style="{textAlign: 'center', fontSize: getSize(24), marginTop: getSize(28)}">
                <span>{{$t('message.service.question.suc2Meg').split('！')[0]}}！</span><br>
                <span>{{$t('message.service.question.suc2Meg').split('！')[1]}}</span>
            </div>
            <div :style="{textAlign: 'center', fontSize: getSize(14), marginTop: getSize(18), marginBottom: getSize(50)}">
                <span v-if="$i18n.locale === 'zh-CN'">{{count}}{{$t('message.service.question.closeMsg')}}</span>
                <span v-if="$i18n.locale === 'en-US'">{{$t('message.service.question.closeMsg')}}{{count+'-sec'}}</span>
            </div>
            <div @click="$emit('sortShow', false)"
                 :style="{width: getSize(250), margin: '0 auto', color: '#ffffff', cursor: 'pointer', fontSize: getSize(24), background: '#C9A060', borderRadius: getSize(25), height: getSize(48), display: 'flex', alignItems: 'center', justifyContent: 'center'}">
                <span>{{$t("message.service.question.close")}}</span>
            </div>
        </div>
    </div>
</template>

<script>
    import countryCode from "../../config/countryCode";
    import {feedback, loveBuy} from '../../config/api.js'

    export default {
        name: "Dialog",
        data() {
            var inputPhone = (rule, value, callback) => {
                if (value) {
					let sortPre = value.slice(0, 5)
					console.log(sortPre)
					if(sortPre ==='SN100' || sortPre ==='SN078'){
						callback();
					} else {
						
						callback(new Error(this.$i18n.t('message.service.question.sortSon')));
					}
                    
                } else {
                    callback(new Error(this.$i18n.t('message.service.question.sortPlaceholder')));
                }
                ;
            }
            return {
                dialogVisible: true,
                success: false,
                countryCode: countryCode,
                ruleForm: {
                    email: "",
                    name: "",
                    phone: "",
                    textarea: "",
                    sort: "",
                    codeNumber: null,
                    codeValue: null,
                },
                btnClick: false,
                count: 15,
                code: "",
                width: null,
                rules: {
                    name: [{
                        required: true,
                        message: this.$i18n.t('message.service.question.nameVal'),
                        trigger: 'blur'
                    }],
                    email: [
                        {
                            required: true,
                            message: this.$i18n.t('message.service.question.emailVal'),
                            trigger: 'blur'
                        },
                        {
                            type: 'email',
                            message: this.$i18n.t('message.service.question.trueMail'),
                            trigger: ['blur', 'change']
                        }
                    ],
                    sort: [{
						validator: inputPhone,
                        trigger: 'blur'
                    }],
                    textarea: [{
                        required: true,
                        message: this.$i18n.t('message.service.question.textareaVal'),
                        trigger: 'blur'
                    }],
                },
            }
        },
        computed: {
            _phone: {
                set: function (value) {
                    this.ruleForm.phone = value;
                }
                ,
                get: function () {
                    return this.ruleForm.phone.replace(/[^0-9]+/g, '')
                }
            }
        }
        ,
        props: ['sortShow', 'title', 'chose', 'showSort'],
        mounted() {
            if (window.innerWidth < 961) {
                this.width = 750;

            } else {
                this.width = 1920;

            }
        }
        ,
        methods: {
            getSize(num) {
                if (window.innerWidth < 961) {
                    return window.innerWidth * num / 750 + "px";
                } else {
                    return window.innerWidth * num / 1920 + "px";
                }
            }
            ,
            visibleDialog() {
                this.$emit('sortShow', false)
            }
            ,
            changeCode(e) {
                this.code = "+" + e.split(",")[0];
                this.ruleForm.codeNumber = e.split(",")[0];
            },
            postBug(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.ruleForm)
                        // alert('submit!');
                        this.btnClick = true
                        if (this.chose) {
                            let obj = {
                                area: this.ruleForm.codeValue ? this.ruleForm.codeValue.split(',')[1] : null,
                                countryCode: this.ruleForm.codeNumber,
                                description: this.ruleForm.textarea,
                                email: this.ruleForm.email,
                                equipmentNumber: this.ruleForm.sort,
                                name: this.ruleForm.name,
                                userName: this.ruleForm.name,
                                telephone: this.ruleForm.phone,
                                coverColor: this.chose.coverColor,
                                penColor: this.chose.penColor,
                                product: this.chose.product
                            }
                            loveBuy(obj).then(res => {
                                console.log(res, '000')
                                if (res.status === 200) {
                                    this.success = true
                                    this.ruleForm = {
                                        email: "",
                                        name: "",
                                        phone: "",
                                        textarea: "",
                                        sort: "",
                                        codeNumber: null,
                                        codeValue: null,
                                    }
									let clock = window.setInterval(() => {
										this.count--
									    if (this.count < 1) {
									        window.clearInterval(clock);
									              this.count = 15;
									              this.$emit('sortShow', false)
									        }
									    }, 1000);

                                }
                            })
                        } else {
                            let obj = {
                                area: this.ruleForm.codeValue ? this.ruleForm.codeValue.split(',')[1] : null,
                                countryCode: this.ruleForm.codeNumber,
                                description: this.ruleForm.textarea,
                                email: this.ruleForm.email,
                                equipmentNumber: this.ruleForm.sort,
                                name: this.ruleForm.name,
                                userName: this.ruleForm.name,
                                telephone: this.ruleForm.phone,
                            }
                            feedback(obj).then(res => {
                                console.log(res, '000')
                                if (res.status === 200) {
                                    this.success = true
                                    this.ruleForm = {
                                        email: "",
                                        name: "",
                                        phone: "",
                                        textarea: "",
                                        sort: "",
                                        codeNumber: null,
                                        codeValue: null,
                                    }
									let clock = window.setInterval(() => {
										this.count--
									    if (this.count < 1) {
									        window.clearInterval(clock);
									              this.count = 15;
									              this.$emit('sortShow', false)
									        }
									    }, 1000);

                                }
                            })
                        }


                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
			
        }
    }
    ;
</script>

<style lang="less" scoped>
    .question-dialog {
        position: fixed;
        width: 100%;
        height: 100%;
        top: 0;
        left: 0;
        z-index: 1000;
        display: flex;
        align-items: center;
        justify-content: center;

        .question-dialog-shadow {
            background: #000000;
            position: absolute;
            width: 100%;
            height: 100%;
            opacity: 0.5;
            top: 0;
            left: 0;
            z-index: -10;
        }

        .question-dialog-body {
            background: #ffffff;
            /*border-radius: 10px;*/
            box-shadow: 0 10px 15px #333333;
            position: relative;
            top: 20px;
            color: #131313;

            .closeBtn {
                position: absolute;
                display: flex;
            }

            div {
                .question-input {
                    div {
                        text-align: left;
                        color: #000000;
                    }

                    .question-input-item {

                    }
                }
            }
        }
    }

    .el-textarea__inner::-webkit-input-placeholder {
        color: #A8ACB9;
    }

    .el-textarea__inner:-moz-placeholder {
        color: #A8ACB9;
    }

    .el-textarea__inner:-ms-input-placeholder {
        color: #A8ACB9;
    }

</style>
