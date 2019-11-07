<template>
  <div class="dealer">
    <div class="dealer-head">
      <ul>
        <li>
          <h5>经销商名称</h5>
          <el-input v-model="tab.dealerName"></el-input>
        </li>
        <li>
          <h5>联系人</h5>
          <el-input v-model="tab.contact"></el-input>
        </li>
        <li>
          <h5>联系电话</h5>
          <el-input v-model="tab.phone"></el-input>
        </li>
        <li>
          <h5>经销商类型</h5>
          <el-select v-model="tab.dealerType" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>起始时间</h5>
          <div class="block">
            <el-date-picker
              v-model="tab.applicationTimeStart"
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              type="date"
              placeholder="选择日期"
            ></el-date-picker>
          </div>
        </li>
        <li>
          <h5>截止时间</h5>
          <div class="block">
            <el-date-picker
              v-model="tab.applicationTimeEnd"
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
        <li v-permit="'/dealer/add'">
          <img src="../../assets/iconfont/add.png" alt />
          <span @click="add">添加</span>
        </li>
        <li v-permit="'/dealer/update'">
          <img src="../../assets/iconfont/amend.png" alt />
          <span @click="change">修改</span>
        </li>
        <li v-permit="'/dealer/delete'">
          <img src="../../assets/iconfont/delete.png" alt />
          <span @click="dele">删除</span>
        </li>
        <li v-permit="'/dealer/model/download'">
          <img src="../../assets/iconfont/down.png" alt />
          <span @click="download">模板下载</span>
        </li>
        <li v-permit="'/dealer/import'">
          <div class="upload-wrapper">
            <img src="../../assets/iconfont/to.png" alt />
            <input
              ref="uploadFile"
              class="upload-file"
              id="file"
              type="file"
              name="file"
              multiple
              @change="submitUpload"
            />
            <span>导入</span>
          </div>
        </li>
        <li v-permit="'/dealer/export'">
          <div class="upload-wrapper">
            <img src="../../assets/iconfont/derive.png" alt />
            <span @click="derives">导出</span>
          </div>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        v-loading="loading"
        border
        highlight-current-row
        @current-change="handleSelectionChange"
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="dealerName" label="经销商名称"></el-table-column>
        <el-table-column prop="dealerType" label="经销商类型"></el-table-column>
        <el-table-column prop="contact" label="联系人" width="150px"></el-table-column>
        <el-table-column prop="phone" label="联系电话" width="160px"></el-table-column>
        <el-table-column prop="address" label="仓库地址"></el-table-column>
        <el-table-column prop="applicationTime" label="申请时间" width="160px"></el-table-column>
        <el-table-column prop="salesman" label="业务员" width="150px"></el-table-column>
        <el-table-column prop="remark" label="备注" show-overflow-tooltip></el-table-column>
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
    <el-dialog
      title="添加用户"
      :visible.sync="dialogVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="form"
        :rules="rules"
        ref="form"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="经销商类别" :label-width="formLabelWidth" prop="dealerType">
          <el-select v-model="form.dealerType" placeholder="请选择" style="width:100%">
            <el-option
              v-for="(item,index) in options"
              :key="index"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="经销商名称" :label-width="formLabelWidth" prop="dealerName">
          <el-input v-model="form.dealerName" autocomplete="off" maxlength="64"></el-input>
        </el-form-item>
        <el-form-item label="联系人" :label-width="formLabelWidth" prop="contact">
          <el-input v-model="form.contact" autocomplete="off" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth">
          <el-input v-model="form.phone" autocomplete="off" maxlength="15"></el-input>
        </el-form-item>
        <el-form-item label="仓库地址" :label-width="formLabelWidth" prop="address">
          <el-input v-model="form.address" autocomplete="off" maxlength="50"></el-input>
        </el-form-item>
        <el-form-item label="业务员" :label-width="formLabelWidth" prop="salesman">
          <el-input v-model="form.salesman" autocomplete="off" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="申请时间" :label-width="formLabelWidth" prop="applicationTime">
          <el-date-picker
            v-model="form.applicationTime"
            format="yyyy/MM/dd"
            value-format="yyyy-MM-dd"
            type="date"
            placeholder="选择日期"
            style="width:100%"
            :editable="false"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input v-model="form.remark" autocomplete="off" maxlength="100"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="save" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="修改用户"
      :visible.sync="dialogVisibles"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="form"
        :rules="rules"
        ref="form"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="经销商类别" :label-width="formLabelWidth" prop="dealerType">
          <el-select v-model="form.dealerType" placeholder="请选择" style="width:100%">
            <el-option
              v-for="(item,index) in options"
              :key="index"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="经销商名称" :label-width="formLabelWidth" prop="dealerName">
          <el-input v-model="form.dealerName" autocomplete="off" maxlength="64"></el-input>
        </el-form-item>
        <el-form-item label="联系人" :label-width="formLabelWidth" prop="contact">
          <el-input v-model="form.contact" autocomplete="off" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="form.phone" autocomplete="off" maxlength="15"></el-input>
        </el-form-item>
        <el-form-item label="仓库地址" :label-width="formLabelWidth" prop="address">
          <el-input v-model="form.address" autocomplete="off" maxlength="50"></el-input>
        </el-form-item>
        <el-form-item label="业务员" :label-width="formLabelWidth" prop="salesman">
          <el-input v-model="form.salesman" autocomplete="off" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="申请时间" :label-width="formLabelWidth" prop="applicationTime">
          <el-date-picker
            v-model="form.applicationTime"
            format="yyyy/MM/dd"
            value-format="yyyy-MM-dd"
            type="date"
            placeholder="选择日期"
            style="width:100%"
            :editable="false"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input v-model="form.remark" autocomplete="off" maxlength="255"></el-input>
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
  getDealer,
  addDealer,
  exchangeDealer,
  deleteDealer,
  templateDownload,
  toLead,
  derive,
  getDealerAlone,
  dataManipulation
} from "../../api/dealer.js";
import { searchDiclist } from "../../api/dictionary.js";
import { getLanList } from "../../api/firmwarepair.js";
export default {
  data() {
    return {
      isDisable: false,
      rules: {
        dealerType: [{ required: true, trigger: "blur" }],
        dealerName: [{ required: true, trigger: "blur" }],
        contact: [{ required: true, trigger: "blur" }],
        phone: [{ required: true, trigger: "blur" }],
        address: [{ required: true, trigger: "blur" }],
        salesman: [{ required: true, trigger: "blur" }],
        applicationTime: [{ required: true, trigger: "blur" }]
      },
      options: [],
      tableData: [],
      loading: false,
      isRepeat: false,
      form: {
        address: "",
        applicationTime: "",
        contact: "",
        dealerName: "",
        dealerType: "",
        phone: "",
        remark: "",
        salesman: ""
      },
      tab: {
        applicationTimeEnd: "",
        applicationTimeStart: "",
        contact: "",
        dealerName: "",
        dealerType: "",
        pageNo: 1,
        pageSize: 10,
        phone: ""
      },
      dialogVisible: false,
      formLabelWidth: "100px",
      multipleSelection: [],
      total: 0,
      currentPage: 1,

      //修改
      dialogVisibles: false
    };
  },
  created() {
    this.getList();
    getLanList({
      name: "DEALERS_TYPE",
      value: ""
    }).then(res => {
      this.options = res.dictionaryVOList;
    });
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    getList() {
      this.loading = true;
      setTimeout(() => {
        getDealer(this.tab).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.loading = false;
          this.isRepeat = false;
        });
      }, 1000);
    },
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.tab.pageNo = currentPage;
      this.getList();
    },
    //查询
    search() {
      this.loading = true;
      setTimeout(() => {
        getDealer({
          applicationTimeEnd: this.tab.applicationTimeEnd,
          applicationTimeStart: this.tab.applicationTimeStart,
          contact: this.tab.contact,
          dealerName: this.tab.dealerName,
          dealerType: this.tab.dealerType,
          pageNo: 1,
          pageSize: 10,
          phone: this.tab.phone
        }).then(res => {
          this.tab.pageNo = 1;
          this.tableData = res.voList;
          this.total = res.total;
          this.currentPage = 1;
          this.loading = false;
        });
      }, 500);
    },

    //清空
    empty() {
      this.tab.dealerName = "";
      this.tab.contact = "";
      this.tab.phone = "";
      this.tab.dealerType = "";
      this.tab.applicationTimeStart = "";
      this.tab.applicationTimeEnd = "";
      this.getList();
    },

    //弹框关闭
    close() {
      this.dialogVisible = false;
      this.dialogVisibles = false;
      this.$refs.form.resetFields();
      this.form.remark = "";
    },
    //添加
    add() {
      this.dialogVisible = true;
      this.form.phone = "";
      this.isDisable = false;
    },
    //保存
    save() {
      if (this.form.phone == "" || this.form.phone == null) {
        this.msgTip("warning", "联系电话不能为空");
        return;
      }
      if (!/^[-\d]+$/.test(this.form.phone) || this.form.phone.length < 8) {
        this.msgTip("warning", "请填写正确的联系电话");
        return;
      }
      this.$refs.form.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          this.isRepeat = true;
          this.isDisable = true;
          addDealer(this.form).then(res => {
            if (res.success) {
              this.msgTip("success", "添加成功");
              this.dialogVisible = false;
              this.empty();
            } else {
              this.isRepeat = false;
              this.msgTip("error", res.errorMsg);
              this.isDisable = false;
            }
          });
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
        }
      });
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
    //修改
    change() {
      if (this.multipleSelection == null || this.multipleSelection == "") {
        this.msgTip("error", "请选择需要修改的记录");
      } else {
        this.dialogVisibles = true;
        this.isDisable = false;
        getDealerAlone(this.multipleSelection.id).then(res => {
          this.form.dealerType = res.voT.dealerType;
          this.form.applicationTime = res.voT.applicationTime;
          this.form.contact = res.voT.contact;
          this.form.dealerName = res.voT.dealerName;
          this.form.phone = res.voT.phone;
          this.form.salesman = res.voT.salesman;
          this.form.address = res.voT.address;
          this.form.remark = res.voT.remark;
        });
      }
    },
    keep() {
      if (this.form.phone == "" || this.form.phone == null) {
        this.msgTip("warning", "联系电话不能为空");
        return;
      }
      console.log(/^[-\d]+$/.test(this.form.phone), "9090");
      if (!/^[-\d]+$/.test(this.form.phone) || this.form.phone.length < 8) {
        this.msgTip("warning", "请填写正确的联系电话");
        return;
      }
      let flag = true;
      let dealerName = this.form.dealerName;
      let id = this.multipleSelection.id;
      this.tableData.forEach(element => {
        if (dealerName == element.dealerName) {
          if (id != element.id) {
            this.msgTip("error", "经销商名称已存在");
            flag = false;
            return;
          }
        }
      });
      if (flag) {
        this.isRepeat = true;
        this.isDisable = true;
        exchangeDealer(this.multipleSelection.id, this.form).then(res => {
          if (res.success) {
            this.$message({
              type: "success",
              message: "操作成功",
              center: true
            });
            this.dialogVisibles = false;
            dataManipulation(
              this.tableData,
              this.multipleSelection,
              this.form.dealerType,
              this.form.dealerName,
              this.form.contact,
              this.form.phone,
              this.form.address,
              this.form.salesman,
              this.form.applicationTime,
              this.form.remark
            );
            //this.getList();
          } else {
            this.$message({
              type: "warning",
              message: res.errorMsg,
              center: true
            });
            this.isRepeat = false;
            this.isDisable = false;
          }
        });
      }
    },

    //删除
    dele() {
      if (this.multipleSelection == null || this.multipleSelection == "") {
        this.msgTip("error", "请选择需要删除的记录");
      } else {
        this.$confirm("您是否要删除当前选中的记录？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          console.log(this.multipleSelection, "9090");
          this.isRepeat = true;
          deleteDealer(this.multipleSelection.id).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "删除成功",
                center: true
              });
              this.getList();
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
              this.isRepeat = false;
            }
          });
        });
      }
    },
    //模板下载
    download() {
      templateDownload().then(res => {
        console.log(res, "00000");
        var data = new Blob([res], { type: "application/vnd.ms-excel" });
        var downloadUrl = window.URL.createObjectURL(data);
        var anchor = document.createElement("a");
        anchor.href = downloadUrl;
        anchor.download = "DealerTemplate.xlsx";
        anchor.click();
        window.URL.revokeObjectURL(anchor.href);
      });
    },
    //导入
    submitUpload() {
      const fileData = this.$refs.uploadFile.files[0];
      let formFile = new FormData(); //加入文件对象
      formFile.append("file", fileData);
      const extension = fileData.name.split(".")[1] === "xlsx";
      if (!extension) {
        this.msgTip("error", "上传模板只能是xlsx格式!");
        return;
      } else {
        toLead(formFile).then(res => {
          if (res.success) {
            this.msgTip("success", "导入成功");
            this.$refs.uploadFile.value = [];
            this.getList();
          } else {
            this.msgTip("error", res.errorMsg);
            this.$refs.uploadFile.value = [];
          }
        });
      }
    },

    //导出
    derives() {
      derive(this.tab).then(res => {
        var data = new Blob([res], { type: "application/vnd.ms-excel" });
        var downloadUrl = window.URL.createObjectURL(data);
        var result = document.getElementById("result");
        var anchor = document.createElement("a");
        anchor.href = downloadUrl;
        anchor.download = "DealerRecharge.xlsx";
        anchor.click();
        window.URL.revokeObjectURL(data);
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
.dealer {
  list-style: none;
  .dealer-head {
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
      flex-wrap: wrap;
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
  .page {
    text-align: center;
    margin-top: 20px;
  }
  .upload-wrapper {
    position: relative;
    padding-right: 5px;
    cursor: pointer;
    .upload-file {
      position: absolute;
      width: 33px;
      height: 33px;
      opacity: 0;
      border: none;
      cursor: pointer;
    }
  }
}
</style>
<style>
.el-table th {
  text-align: center;
}
.el-table td {
  text-align: center;
}
</style>

