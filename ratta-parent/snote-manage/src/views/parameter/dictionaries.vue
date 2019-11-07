<template>
  <div class="dictionaries">
    <div class="dic-head">
      <ul>
        <li>
          <h5>业务码</h5>
          <el-input v-model="param.name"></el-input>
        </li>
        <li>
          <h5>中文编码名称</h5>
          <el-input v-model="param.value"></el-input>
        </li>
        <li>
          <el-button type="primary" @click="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
        <li>
          <el-button type="primary" @click="add" v-permit="'/dictionary/add'">添加</el-button>
        </li>
        <li>
          <el-button type="primary" @click="amend" v-permit="'/dictionary/update'">修改</el-button>
        </li>
        <li>
          <el-button type="primary" @click="delet" v-permit="'/dictionary/delete'">删除</el-button>
        </li>
      </ul>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
        highlight-current-row
        @row-click="getRow"
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="name" label="业务码" width="230"></el-table-column>
        <el-table-column prop="remark" label="业务码名称" width="200"></el-table-column>
        <el-table-column prop="value" label="编码"></el-table-column>
        <el-table-column prop="valueCn" label="中文编码名称"></el-table-column>
        <el-table-column prop="valueEn" label="英文编码名称" width="220"></el-table-column>
        <el-table-column prop="opUser" label="操作员"></el-table-column>
        <el-table-column prop="opTime" label="操作时间" width="200"></el-table-column>
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
      title="添加"
      :visible.sync="dialogFormVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="form"
        :rules="addForm"
        ref="form"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="编码" :label-width="formLabelWidth" prop="value">
          <el-input v-model="form.value" autocomplete="off" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="业务码" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" autocomplete="off" maxlength="50"></el-input>
        </el-form-item>
        <el-form-item label="业务码名称" :label-width="formLabelWidth" prop="remark">
          <el-input v-model="form.remark" autocomplete="off" maxlength="100"></el-input>
        </el-form-item>
        <el-form-item label="中文编码名称" :label-width="formLabelWidth" prop="valueCn">
          <el-input v-model="form.valueCn" autocomplete="off" maxlength="100"></el-input>
        </el-form-item>
        <el-form-item label="英文编码名称" :label-width="formLabelWidth" prop="valueEn">
          <el-input v-model="form.valueEn" autocomplete="off" maxlength="100"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="sure" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="修改"
      :visible.sync="dialogVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="form"
        :rules="addForm"
        ref="form"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="编码" :label-width="formLabelWidth" prop="value">
          <el-input v-model="form.value" autocomplete="off" disabled maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="业务码" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" autocomplete="off" disabled maxlength="50"></el-input>
        </el-form-item>
        <el-form-item label="业务码名称" :label-width="formLabelWidth" prop="remark">
          <el-input v-model="form.remark" autocomplete="off" maxlength="100"></el-input>
        </el-form-item>
        <el-form-item label="中文编码名称" :label-width="formLabelWidth" prop="valueCn">
          <el-input v-model="form.valueCn" autocomplete="off" maxlength="100"></el-input>
        </el-form-item>
        <el-form-item label="英文编码名称" :label-width="formLabelWidth" prop="valueEn">
          <el-input v-model="form.valueEn" autocomplete="off" maxlength="100"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="save" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  searchDiclist,
  addDiclist,
  deleteDiclist,
  getDiclist,
  amendDiclist,
  dataManipulation
} from "../../api/dictionary.js";
export default {
  data() {
    return {
      isDisable: false,
      value: "",
      param: {
        name: "",
        value: ""
      },
      rowid: "",
      form: {
        name: "",
        remark: "",
        value: "",
        valueCn: "",
        valueEn: ""
      },
      forms: {
        name: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: "",
        valueMeaning: ""
      },
      dialogFormVisible: false,
      dialogVisible: false,
      tableData: [],
      formLabelWidth: "120px",
      total: 0,
      currentPage: 1,
      flag: false,
      loading: false,
      isRepeat: false,
      //添加
      addForm: {
        value: [{ required: true, trigger: "blur" }],
        name: [{ required: true, trigger: "blur" }],
        remark: [{ required: true, trigger: "blur" }],
        valueCn: [{ required: true, trigger: "blur" }],
        valueEn: [{ required: true, trigger: "blur" }]
      }
    };
  },
  created() {
    this.getList();
  },
  mounted: {},
  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        searchDiclist(this.forms).then(res => {
          this.currentrow = null;
          this.tableData = res.voList;
          this.total = res.total;
          this.loading = false;
          this.isRepeat = false;
        });
      }, 500);
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.forms.pageNo = currentPage;
      this.getList();
    },
    //查询
    search() {
      this.loading = true;
      this.forms.pageNo = 1;
      setTimeout(() => {
        if (this.param.name !== "" || this.param.value !== "") {
          this.forms.name = this.param.name;
          this.forms.valueMeaning = this.param.value;
          searchDiclist({
            name: this.param.name,
            pageNo: 1,
            pageSize: 10,
            sortField: "",
            sortRules: "",
            valueMeaning: this.param.value
          }).then(res => {
            this.forms.pageNo = 1;
            this.tableData = res.voList;
            this.total = res.total;
          });
          this.currentPage = 1;
          this.loading = false;
        }
      }, 500);
    },

    //清空
    empty() {
      this.param.name = "";
      this.param.value = "";
      this.forms.name = "";
      this.forms.valueMeaning = "";
      this.getList();
    },

    //添加
    add() {
      this.dialogFormVisible = true;
      this.isDisable = false;
    },
    sure() {
      this.$refs.form.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          this.isRepeat = true;
          this.isDisable = true;
          addDiclist(this.form).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "添加成功",
                center: true
              });
              this.dialogFormVisible = false;
              this.empty();
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
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
        }
      });
    },

    //弹框关闭
    close() {
      this.dialogFormVisible = false;
      this.dialogVisible = false;
      this.$refs.form.resetFields();
    },
    //点击获取这行数据
    getRow(row) {
      this.currentrow = row;
      this.rowid = row.id;
    },
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //修改
    amend() {
      if (this.currentrow == null) {
        this.msgTip("error", "请选择需要修改的数据");
      } else {
        this.dialogVisible = true;
        this.isDisable = false;
        getDiclist(this.rowid).then(res => {
          this.form.value = res.value;
          this.form.name = res.name;
          this.form.remark = res.remark;
          this.form.valueCn = res.valueCn;
          this.form.valueEn = res.valueEn;
        });
      }
    },
    save() {
      this.$refs.form.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          this.isRepeat = true;
          this.isDisable = true;
          amendDiclist(this.rowid, this.form).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "修改成功",
                center: true
              });
              this.dialogVisible = false;
              dataManipulation(
                this.tableData,
                this.currentrow,
                this.form.remark,
                this.form.valueCn,
                this.form.valueEn,
                window.localStorage.getItem("name"),
                getDate()
              );
              //this.getList();
            } else {
              this.$message({
                type: "success",
                message: res.errorMsg,
                center: true
              });
              this.isRepeat = false;
              this.isDisable = false;
            }
          });
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
        }
      });
    },
    //删除
    delet() {
      if (this.currentrow == null) {
        this.msgTip("error", "请选择需要删除的数据！");
      } else {
        this.$confirm("您是否要删除当前选中的记录?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.isRepeat = true;
          deleteDiclist(this.rowid).then(res => {
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
    indexMethod(index) {
      return index + 1;
    },
    handleClick(row) {
      console.log(row);
    }
  }
};
/**
 * 获取当前时间
 */
function getDate() {
  var date = new Date();
  var seperator1 = "-";
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var strDate = date.getDate();
  var hours = date.getHours(); //获取当前小时数(0-23)
  var minutes = date.getMinutes(); //获取当前分钟数(0-59)
  var seconds = date.getSeconds();
  if (month >= 1 && month <= 9) {
    month = "0" + month;
  }
  if (strDate >= 0 && strDate <= 9) {
    strDate = "0" + strDate;
  }
  var currentdate =
    year +
    seperator1 +
    month +
    seperator1 +
    strDate +
    " " +
    hours +
    ":" +
    minutes +
    ":" +
    seconds;
  return currentdate;
}
</script>

<style lang="scss" scoped>
.dictionaries {
  list-style: none;
  .dic-head {
    padding-right: 20px;
    ul {
      width: 100%;
      display: flex;
      padding-left: 0;
      align-items: flex-end;
      li {
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

