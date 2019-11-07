<template>
  <div class="login-container">
    <div class="login">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">Supernote 管理平台</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleLogin"
      >登录</el-button>
    </el-form>
  </div>
    <el-dialog
      title="修改密码"
      :visible.sync="dialogVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="form"
        ref="form"
      >
        <el-form-item label="登录名" :label-width="formLabelWidth">
          <el-input v-model="form.username" :disabled='true'></el-input>
        </el-form-item>
        <el-form-item label="原密码" :label-width="formLabelWidth" prop="password1">
          <el-input v-model="form.oldPassword" autocomplete="off" type="password"></el-input>
        </el-form-item>
        <el-form-item label="新密码" :label-width="formLabelWidth" prop="newPassword">
          <el-input v-model="form.newPassword" autocomplete="off" type="password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" :label-width="formLabelWidth" prop="repeatPassword">
          <el-input v-model="form.repeatPassword" autocomplete="off" type="password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save">修改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { validUsername } from "@/utils/validate";
import {
  verifvPassword,
  amendPasw,
  checkOldPwd
} from "../../api/systemuser.js";
import { removeToken } from "@/utils/auth";
export default {
  name: "Login",
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error("请填写正确的用户名"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error("请输入正确的密码"));
      } else {
        callback();
      }
    };
    return {
      dialogVisible: false,
      form: {
        username: "",
        oldPassword: "",
        newPassword: "",
        repeatPassword: ""
      },
      loginForm: {
        username: "",
        password: ""
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请填写用户名" }
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePassword }
        ]
      },
      loading: false,
      passwordType: "password",
      redirect: undefined,
      formLabelWidth:'90px'
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  methods: {
    close() {
      this.loading = false;
    },
    save() {
      checkOldPwd({
        oldPwd: this.form.oldPassword,
        password: "1"
      }).then(res => {
        if (!res.success) {
          this.$message({
            type: "error",
            message: "原密码不一致",
            center: true
          });
          return;
        } else {
          if (
            !/^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,20}$/.test(
              this.form.newPassword
            )
          ) {
            this.$message({
              type: "error",
              message: "请输入密码为6到20位且由数字大小写字母组成",
              center: true
            });
            return;
          }
          if (this.form.newPassword != this.form.repeatPassword) {
            this.$message({
              type: "error",
              message: "确认密码与新密码不一样",
              center: true
            });
            return;
          }
          amendPasw({
            oldPwd: this.form.oldPassword,
            password: this.form.newPassword
          }).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "更新密码成功,请重新登陆",
                center: true
              });
              this.dialogVisible = false;
            } else {
              this.$message({
                type: "error",
                message: res.errorMsg,
                center: true
              });
            }
          });
        }
      });
    },
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          this.$store.dispatch("user/login", this.loginForm).then(res => {
            if (res.success) {
              window.localStorage.setItem("token", res.token);
              window.localStorage.setItem("username", this.loginForm.username);
              window.localStorage.setItem("name", res.name);
              verifvPassword().then(res => {
                if (res.success) {
                  this.$router.push({ path: this.redirect || "/" });
                  this.loading = false;
                } else {
                  this.form.username = this.loginForm.username;
                  this.loginForm.username = "";
                  this.loginForm.password = "";
                  this.form.oldPassword = "";
                  this.form.newPassword = "";
                  this.form.repeatPassword = "";
                  removeToken();
                  this.dialogVisible = true;
                }
              });
            } else {
              this.$message({
                type: "error",
                message: res.errorMsg,
                center: true
              });
              this.loading = false;
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    }
  }
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  // background-color: $bg;
  background: url("../../assets/img/cat.jpg") no-repeat center center;
  background-size: cover;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
