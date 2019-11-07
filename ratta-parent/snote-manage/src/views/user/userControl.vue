<template>
  <div class="userControl">
    <div class="user-head">
      <ul>
        <li>
          <h5>用户名</h5>
          <el-input v-model="form.userName"></el-input>
        </li>
        <li>
          <h5>手机号</h5>
          <el-input v-model="form.telephone"></el-input>
        </li>
        <li>
          <h5>邮箱</h5>
          <el-input v-model="form.email"></el-input>
        </li>
        <li>
          <h5>状态</h5>
          <el-select v-model="form.isNormal" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>注册开始时间</h5>
          <div class="block">
            <el-date-picker
              v-model="form.createTimeStart"
              type="date"
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
              :editable="false"
            ></el-date-picker>
          </div>
        </li>
        <li>
          <h5>注册结束时间</h5>
          <div class="block">
            <el-date-picker
              v-model="form.createTimeEnd"
              type="date"
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
              :editable="false"
            ></el-date-picker>
          </div>
        </li>
      </ul>
    </div>
    <div class="function">
      <ol>
        <li>
          <img src="../../assets/iconfont/find.png" alt />
          <span @click="search">查询</span>
        </li>
        <li>
          <img src="../../assets/iconfont/empty.png" alt />
          <span @click="empty">清空</span>
        </li>
        <li>
          <img src="../../assets/iconfont/phone.png" alt />
          <span @click="changePhone" id="changePhone">修改手机号</span>
        </li>
        <li>
          <img src="../../assets/iconfont/email.png" alt />
          <span @click="changeEmail" id="changeEmail">修改邮箱</span>
        </li>
        <li>
          <img src="../../assets/iconfont/block.png" alt />
          <span @click="freeze">冻结</span>
        </li>
        <li>
          <img src="../../assets/iconfont/unblocks.png" alt />
          <span @click="unfreeze">解冻</span>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="userName" label="昵称"></el-table-column>
        <el-table-column prop="countryCode" label="国家码"></el-table-column>
        <el-table-column prop="telephone" label="手机号"></el-table-column>
        <el-table-column prop="email" label="电子邮箱"></el-table-column>
        <el-table-column prop="createTime" label="注册时间"></el-table-column>
        <el-table-column prop="isNormal" label="状态"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <div class="function-icon">
              <el-tooltip class="item" effect="dark" content="详情" placement="bottom">
                <img @click="details(scope.row)" src="../../assets/iconfont/mess.png" alt />
              </el-tooltip>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="page">
      <el-pagination
        background
        layout="total, prev, pager, next"
        :page-size="10"
        :total="total"
        :current-page="currentPage"
        @current-change="changepage"
      ></el-pagination>
    </div>
    <el-dialog title="用户详情" :visible.sync="dialogVisibles" :close-on-click-modal="false">
      <el-form :model="form" class="form">
        <el-form-item label="性别：" :label-width="formLabelWidth">
          <el-input v-model="sex" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="生日：" :label-width="formLabelWidth">
          <el-input v-model="birthday" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="个性签名：" :label-width="formLabelWidth">
          <el-input v-model="personalSign" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="喜好：" :label-width="formLabelWidth">
          <el-input v-model="hobby" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="教育程度：" :label-width="formLabelWidth">
          <el-input v-model="education" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="职业：" :label-width="formLabelWidth">
          <el-input v-model="job" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="地址：" :label-width="formLabelWidth">
          <el-input v-model="address" :readonly="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibles = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="修改手机号"
      :visible.sync="dialogFormVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form :model="form">
        <el-form-item label="原手机号" :label-width="formLabelWidth">
          <el-input v-model="oldphone" disabled class="input-with-select">
            <el-select slot="prepend" v-model="countryCode" disabled></el-select>
          </el-input>
        </el-form-item>
        <el-form-item label="新手机号" :label-width="formLabelWidth">
          <div class="list">
            <el-select v-model="newcountry" placeholder="请选择" style="borderRight:none">
              <el-option
                v-for="item in cities"
                :key="item.value"
                :label="item.code"
                :value="item.code"
              >
                <span style="float: left">{{ item.code }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.name }}</span>
              </el-option>
            </el-select>
            <el-input v-model="newphone" maxlength="11" style="paddingLeft:0;borderLeft:none" />
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="save" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="修改邮箱"
      :visible.sync="dialogFormsVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form :model="form">
        <el-form-item label="原邮箱" :label-width="formLabelWidth">
          <el-input v-model="oldemail" disabled></el-input>
        </el-form-item>
        <el-form-item label="新邮箱" :label-width="formLabelWidth">
          <el-input v-model="newemail" maxlength="30" type="text"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="keep" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getUsercontrol,
  getUserid,
  amendPhone,
  amendEmail,
  freezes,
  getIduser,
  dataManipulation
} from "../../api/login.js";
import countryCodes from "../../countryCode.js";
import { all } from "q";
export default {
  data() {
    return {
      isDisable: false,
      isFlag: true,
      value: "",
      options: [
        {
          value: "Y",
          label: "正常"
        },
        {
          value: "N",
          label: "冻结"
        }
      ],
      sex: "",
      birthday: "",
      personalSign: "",
      hobby: "",
      education: "",
      job: "",
      address: "",
      totalCapacity: "",
      tableData: [],
      dialogVisibles: false,
      loading: false,
      isRepeat: false,
      formLabelWidth: "100px",
      form: {
        createTimeEnd: "",
        createTimeStart: "",
        email: "",
        isNormal: "",
        pageNo: 1,
        pageSize: 10,
        telephone: "",
        userName: ""
      },
      phoneFlag: true,
      emailFlag: true,
      multipleSelection: [],
      isNormal: "",
      //修改手机号
      dialogFormVisible: false,
      oldphone: "",
      newphone: "",
      countryCode: "",
      boll: false,
      vCode: "86",
      cities: countryCodes,
      newcountry: "86",
      userid: "",

      //修改邮箱
      dialogFormsVisible: false,
      oldemail: "",
      newemail: "",

      total: 0,
      currentPage: 1
    };
  },
  created() {
    this.getList();
  },
  mounted() {},
  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        getUsercontrol(this.form).then(res => {
          this.tableData = res.voList;
          this.total = Number(res.total);
          this.loading = false;
          this.isRepeat = false;
        });
      }, 500);
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      if (val == "" || val == null) {
        document.all["changePhone"].style.color = "#000000";
        document.all["changeEmail"].style.color = "#000000";
        this.PhoneFlag = true;
        this.emailFlag = true;
      } else {
        if (val[0].telephone == null) {
          this.PhoneFlag = false;
          document.all["changePhone"].style.color = "#DCDCDC";
        } else {
          this.PhoneFlag = true;
          document.all["changePhone"].style.color = "#000000";
        }

        if (val.length > 0 && val[0].email == null) {
          this.emailFlag = false;
          document.all["changeEmail"].style.color = "#DCDCDC";
        } else {
          this.emailFlag = true;
          document.all["changeEmail"].style.color = "#000000";
        }
      }
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.form.pageNo = currentPage;
      this.getList();
    },
    // 查找有没有选中数据，没有则返回false
    findSelectData() {
      if (!this.multipleSelection.length) {
        // 无选中的数据
        return false;
      } else {
        // 有选中的数据
        return true;
      }
    },
    //信息提示框
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //查询
    search() {
      this.loading = true;
      setTimeout(() => {
        getUsercontrol({
          createTimeEnd: this.form.createTimeEnd,
          createTimeStart: this.form.createTimeStart,
          email: this.form.email,
          isNormal: this.form.isNormal,
          pageNo: 1,
          pageSize: 10,
          telephone: this.form.telephone,
          userName: this.form.userName
        }).then(res => {
          this.tableData = res.voList;
          this.total = Number(res.total);
          this.currentPage = 1;
          this.loading = false;
        });
      }, 500);
    },

    //清空
    empty() {
      this.form.createTimeEnd = "";
      this.form.createTimeStart = "";
      this.form.telephone = "";
      this.form.isNormal = "";
      this.form.userName = "";
      this.form.email = "";
      this.getList();
    },

    //弹框关闭
    close() {
      this.dialogFormVisible = false;
      this.dialogFormsVisible = false;
      this.newphone = "";
      this.newemail = "";
    },
    //修改手机号
    changePhone() {
      if (this.PhoneFlag || this.PhoneFlag == undefined) {
        if (this.multipleSelection.length < 1) {
          this.msgTip("error", "请选择一条数据");
          return;
        } else if (this.multipleSelection.length > 1) {
          this.msgTip("error", "只能选择一条数据");
          return;
        }
        this.multipleSelection.map(res => {
          this.multipleSelection.map(res => {
            this.countryCode = res.countryCode;
            this.oldphone = res.telephone;
            this.userid = res.userId;
          });
          this.dialogFormVisible = true;
          this.isDisable = false;
        });
      }
    },
    save() {
      if (this.newphone == "") {
        this.$message({
          type: "warning",
          message: "请填写手机号",
          center: true
        });
      } else {
        if (this.newcountry == "86") {
          if (!/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.newphone)) {
            this.$message({
              type: "warning",
              message: "请填写正确的手机号",
              center: true
            });
          } else {
            this.isRepeat = true;
            this.isDisable = true;
            amendPhone(this.userid, {
              countryCode: this.newcountry,
              telephone: this.newphone
            }).then(res => {
              if (res.success) {
                this.$message({
                  type: "success",
                  message: "修改成功",
                  center: true
                });
                this.dialogFormVisible = false;
                var tel = this.newphone;
                tel = "" + tel;
                var tel1 = tel.substr(0, 3) + "****" + tel.substr(7);
                dataManipulation(
                  this.tableData,
                  this.multipleSelection,
                  tel1,
                  "",
                  ""
                );
                //this.getList();
              } else {
                this.$message({
                  type: "error",
                  message: res.errorMsg,
                  center: true
                });
                this.isRepeat = false;
                this.isDisable = false;
              }
            });
          }
        } else {
          if (this.newphone.length < 8 || this.newphone.length > 15) {
            this.$message({
              type: "warning",
              message: "请输入8到15位的手机号",
              center: true
            });
            return;
          }
          if (/^[0-9]*$/.test(this.newphone)) {
            this.isRepeat = true;
            amendPhone(this.userid, {
              countryCode: this.newcountry,
              telephone: this.newphone
            }).then(res => {
              if (res.success) {
                this.$message({
                  type: "success",
                  message: "修改成功",
                  center: true
                });
                this.dialogFormVisible = false;
                this.getList();
              } else {
                this.$message({
                  type: "error",
                  message: res.errorMsg,
                  center: true
                });
                this.isRepeat = false;
              }
            });
          }
        }
      }
    },
    //修改邮箱
    changeEmail() {
      if (this.emailFlag) {
        if (!this.findSelectData()) {
          this.msgTip("error", "至少选择一条数据");
        } else {
          if (this.multipleSelection.length >= 2) {
            this.msgTip("error", "只能选择一条数据");
          } else {
            this.multipleSelection.map(res => {
              if (res.email != null) {
                this.oldemail = res.email;
                this.userid = res.userId;
              }
            });
            this.dialogFormsVisible = true;
            this.isDisable = false;
          }
        }
      }
    },
    keep() {
      if (this.newemail == "") {
        this.msgTip("warning", "请填写邮箱号");
      } else {
        if (
          /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(
            this.newemail
          )
        ) {
          this.isRepeat = true;
          this.isDisable = true;
          amendEmail(this.userid, {
            email: this.newemail
          }).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "修改成功",
                center: true
              });
              this.dialogFormsVisible = false;
              var tel = this.newemail;
              tel = "" + tel;
              var ary = tel.split("");
              ary.splice(1, 2, "**");
              var tel1 = ary.join("");
              dataManipulation(
                this.tableData,
                this.multipleSelection,
                "",
                tel1,
                ""
              );
              //this.getList();
            } else {
              this.$message({
                type: "error",
                message: res.errorMsg,
                center: true
              });
              this.isRepeat = false;
              this.isDisable = false;
            }
          });
        } else {
          this.msgTip("warning", "请填写正确邮箱号");
        }
      }
    },
    //冻结
    freeze() {
      this.multipleSelection.map(res => {
        this.userid = res.userId;
        this.isNormal = res.isNormal;
      });
      if (!this.findSelectData()) {
        this.msgTip("error", "至少选择一条数据");
      } else {
        if (this.multipleSelection.length >= 2) {
          this.msgTip("error", "只能选择一条数据");
        } else {
          if (this.isNormal == "冻结") {
            this.msgTip("warning", "此条数据已经被冻结！");
          } else {
            if (this.isFlag) {
              this.isFlag = false;
              this.isRepeat = true;
              freezes({
                flag: "N",
                userId: this.userid
              }).then(res => {
                if (res.success) {
                  this.$message({
                    type: "success",
                    message: "冻结成功",
                    center: true
                  });
                  dataManipulation(
                    this.tableData,
                    this.multipleSelection,
                    "",
                    "",
                    "冻结"
                  );
                  this.isFlag = true;
                  //this.getList();
                } else {
                  this.$message({
                    type: "warning",
                    message: res.errorMsg,
                    center: true
                  });
                  this.isRepeat = false;
                  this.isFlag = true;
                }
              });
            }
          }
        }
      }
    },
    //解冻
    unfreeze() {
      this.multipleSelection.map(res => {
        console.log(res.isNormal, "123123");
        this.isNormal = res.isNormal;
        this.userid = res.userId;
      });
      if (!this.findSelectData()) {
        this.msgTip("error", "至少选择一条数据");
      } else {
        if (this.multipleSelection.length >= 2) {
          this.msgTip("error", "只能选择一条数据");
        } else {
          console.log(this.isNormal, "88888");
          if (this.isNormal == "正常") {
            this.msgTip("warning", "请选择需要解冻的数据！");
          } else {
            if (this.isFlag) {
              this.isRepeat = true;
              this.isFlag = false;
              freezes({
                flag: "Y",
                userId: this.userid
              }).then(res => {
                if (res.success) {
                  this.$message({
                    type: "success",
                    message: "解冻成功",
                    center: true
                  });
                  dataManipulation(
                    this.tableData,
                    this.multipleSelection,
                    "",
                    "",
                    "正常"
                  );
                  //this.getList();
                  this.isFlag = true;
                } else {
                  this.$message({
                    type: "warning",
                    message: res.errorMsg,
                    center: true
                  });
                  this.isRepeat = false;
                  this.isFlag = true;
                }
              });
            }
          }
        }
      }
    },
    //详情
    details(v) {
      console.log(v.userId);
      this.dialogVisibles = true;
      getIduser(v.userId).then(res => {
        this.sex = res.sex;
        this.birthday = res.birthday;
        this.personalSign = res.personalSign;
        this.hobby = res.hobby;
        this.education = res.education;
        this.job = res.job;
        this.address = res.address;
      });
    },

    indexMethod(index) {
      return index + 1;
    },
    handleClick(row) {
      console.log(row);
    }
  }
};
</script>

<style lang="scss" scoped>
.userControl {
  list-style: none;
  .user-head {
    padding-right: 20px;
    ul {
      width: 100%;
      display: flex;
      padding-left: 0;
      li {
        width: 20%;
        margin-left: 20px;
        .el-select {
          width: 100%;
        }
        .el-date-editor {
          width: 100%;
        }
      }
    }
  }
  .function {
    width: 100%;
    height: 40px;
    line-height: 40px;
    background: #eee;
    ol {
      display: flex;
      padding-left: 0;
      li {
        margin-left: 20px;
        cursor: pointer;
        display: flex;
        align-items: center;
        padding: 0 10px;
        img {
          width: 15px;
          vertical-align: middle;
          margin-right: 5px;
        }
        span {
          font-size: 14px;
        }
      }
      li:hover {
        background: #ccc;
      }
    }
  }
  .function-icon {
    img {
      cursor: pointer;
      width: 14px;
    }
  }
  .list {
    display: flex;
    .ipt {
      border-left: none;
    }
  }
  .page {
    text-align: center;
    margin-top: 20px;
  }
}
</style>
<style>
.form .el-input__inner {
  border: none;
}
.el-table th {
  text-align: center;
}
.el-table td {
  text-align: center;
}
</style>

