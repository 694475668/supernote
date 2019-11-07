<template>
  <div class="type">
    <div class="type-head">
      <ul>
        <li>
          <h5>类型编号</h5>
          <el-input v-model="form.typeId"></el-input>
        </li>
        <li>
          <el-button type="primary" @click="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
        <li v-permit="'/feedback/type/add'">
          <el-button type="primary" @click="add">添加</el-button>
        </li>
        <li v-permit="'/feedback/type/update'">
          <el-button type="primary" @click="edit">修改</el-button>
        </li>
        <li v-permit="'/feedback/type/delete'">
          <el-button type="primary" @click="delet">删除</el-button>
        </li>
      </ul>
    </div>

    <el-dialog
      title="添加"
      :visible.sync="dialogFormVisible"
      @close="cancel('data')"
      :close-on-click-modal="false"
    >
      <el-form
        :model="data"
        :rules="rulesadd"
        ref="data"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="类型编号" :label-width="formLabelWidth" required="true">
          <el-input
            type="text"
            v-model="data.typeId"
            autocomplete="off"
            maxlength="20"
            show-word-limit="true"
          ></el-input>
        </el-form-item>
        <el-form-item label="中文描述" :label-width="formLabelWidth"  required="true">
          <el-input v-model="data.valueCn" autocomplete="off" maxlength="50" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="英文描述" :label-width="formLabelWidth" required="true" >
          <el-input v-model="data.valueEn" autocomplete="off" maxlength="50" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="日文描述" :label-width="formLabelWidth" prop="valueJa">
          <el-input v-model="data.valueJa" autocomplete="off" maxlength="50" show-word-limit></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('data')">取 消</el-button>
        <el-button type="primary" @click="save('data')" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="修改"
      :visible.sync="dialogVisible"
      @close="cancel('updateData')"
      :close-on-click-modal="false"
    >
      <el-form
        :model="updateData"
        :rules="rulesupdate"
        ref="updateData"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="类型编号" :label-width="formLabelWidth">
          <el-input :disabled="true" v-model="updateData.typeId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="中文描述" :label-width="formLabelWidth" prop="valueCn">
          <el-input v-model="updateData.valueCn" autocomplete="off" maxlength="50" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="英文描述" :label-width="formLabelWidth" prop="valueEn">
          <el-input v-model="updateData.valueEn" autocomplete="off" maxlength="50" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="日文描述" :label-width="formLabelWidth" prop="valueJa">
          <el-input v-model="updateData.valueJa" autocomplete="off" maxlength="50" show-word-limit></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('updateData')">取 消</el-button>
        <el-button type="primary" @click="update('updateData')" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
        highlight-current-row
        @current-change="currentChange"
        @row-click="getRow"
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="typeId" label="类型编号" width="150px"></el-table-column>
        <el-table-column prop="valueCn" label="中文描述"></el-table-column>
        <el-table-column prop="valueEn" label="英文描述"></el-table-column>
        <el-table-column prop="valueJa" label="日文描述"></el-table-column>
        <el-table-column prop="opUser" label="操作员" width="200px"></el-table-column>
        <el-table-column prop="opTime" label="操作时间" width="200px"></el-table-column>
      </el-table>
    </div>
    <div class="page">
      <el-pagination
        @current-change="handleCurrentChange"
        :hide-on-single-page="value"
        :current-page="currentPage"
        :page-size="10"
        layout="total, prev, pager, next"
        :total="total"
        background
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import {
  getFeedbackTypeList,
  addFeedvackType,
  getFeedbackTypeById,
  updateFeedbackType,
  deletFeedbackType,
  dataManipulation
} from "../../api/feedback.js";
import { close } from "fs";
export default {
  data() {
    return {
      isDisable: false,
      value: "",
      value1: "",
      value2: "",
      rowid: "",
      valueCn: "",
      valueEn: "",
      valueJa: "",
      currentPage: 0,
      total: 0,
      tableData: [],
      rowData: [],
      loading: false,
      isRepeat: false,
      form: {
        pageNo: 1,
        pageSize: 10,
        typeId: ""
      },
      data: {
        typeId: "",
        valueCn: "",
        valueEn: "",
        valueJa: ""
      },
      updateData: {
        typeId: "",
        valueCn: "",
        valueEn: "",
        valueJa: ""
      },
      updateRowData: {
        typeId: "",
        valueCn: "",
        valueEn: "",
        valueJa: ""
      },
      Ctor: "",
      dialogFormVisible: false,
      dialogVisible: false,
      formLabelWidth: "120px",
      rulesadd: {
        typeId: [{ required: true, trigger: "blur" }],
        valueCn: [{ required: true, trigger: "blur" }],
        valueEn: [{ required: true, trigger: "blur" }],
        valueJa: [{ required: true, trigger: "blur" }]
      },
      rulesupdate: {
        valueCn: [{ required: true, trigger: "blur" }],
        valueEn: [{ required: true, trigger: "blur" }],
        valueJa: [{ required: true, trigger: "blur" }]
      }
    };
  },

  created() {
    this.getList();
  },

  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        getFeedbackTypeList(this.form).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.loading = false;
          this.isRepeat = false;
        });
      }, 500);
    },

    //添加
    add() {
      this.dialogFormVisible = true;
      this.isDisable = false;
    },
    save(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.isRepeat = true;
          this.isDisable = true;
          addFeedvackType(this.data).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "添加成功",
                center: true
              });
              this.$refs[formName].resetFields();
              this.empty();
              this.dialogFormVisible = false;
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
          // console.log("error submit!!");
          // return false;
        }
      });
    },

    //修改
    edit() {
      if (this.currentRow == null) {
        this.$message({
          type: "warning",
          message: "请选择一条数据进行修改",
          center: true
        });
        return;
      }
      this.dialogVisible = true;
      this.isDisable = false;
      this.updateData.typeId = this.updateRowData.typeId;
      this.updateData.valueCn = this.updateRowData.valueCn;
      this.updateData.valueEn = this.updateRowData.valueEn;
      this.updateData.valueJa = this.updateRowData.valueJa;
    },
    update(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.isRepeat = true;
          this.isDisable = true;
          updateFeedbackType({
            typeId: this.updateData.typeId,
            valueCn: this.updateData.valueCn,
            valueEn: this.updateData.valueEn,
            valueJa: this.updateData.valueJa
          }).then(res => {
            this.dialogVisible = false;
            if (res.success) {
              this.$message({
                type: "success",
                message: "修改成功",
                center: true
              });
              this.updateRowData.typeId = this.updateData.typeId;
              this.updateRowData.valueCn = this.updateData.valueCn;
              this.updateRowData.valueEn = this.updateData.valueEn;
              this.updateRowData.valueJa = this.updateData.valueJa;
              // 获取当前日期时间
              dataManipulation(
                this.tableData,
                this.rowData,
                this.updateData.valueCn,
                this.updateData.valueEn,
                this.updateData.valueJa,
                getDate()
              );
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
          console.log("error submit!!");
          return false;
        }
      });
    },

    //查询
    search() {
      this.loading = true;
      setTimeout(() => {
        getFeedbackTypeList({
          pageNo: 1,
          pageSize: 10,
          typeId: this.form.typeId
        }).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.currentPage = 1;
          this.loading = false;
        });
      }, 500);
    },

    //清空
    empty() {
      this.form.typeId = "";
      this.getList();
    },
    //取消
    cancel(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible = false;
      this.dialogVisible = false;
    },
    //获取页码
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.form.pageNo = currentPage;
      this.getList();
    },
    //行选择
    currentChange(val) {
      this.currentRow = val;
    },

    //点击获取这行数据
    getRow(row) {
      this.rowData = row;
      this.rowid = row.typeId;
      this.updateRowData.typeId = row.typeId;
      this.updateRowData.valueCn = row.valueCn;
      this.updateRowData.valueEn = row.valueEn;
      this.updateRowData.valueJa = row.valueJa;
    },
    //删除
    delet() {
      if (this.currentRow == null) {
        this.$message({
          type: "warning",
          message: "请选择一条数据进行删除",
          center: true
        });
        return;
      }
      this.$confirm("此操作将永久删除该数据, 是否继续?", "删除", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.isRepeat = true;
          deletFeedbackType(this.rowid).then(res => {
            this.$message({
              type: "success",
              message: "删除成功!"
            });
            this.getList();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
          this.isRepeat = false;
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
.type {
  list-style: none;
  .type-head {
    padding-right: 20px;
    ul {
      width: 100%;
      padding-left: 0;
      display: flex;
      align-items: flex-end;
      li {
        margin-left: 20px;
      }
    }
  }
  .function-icon {
    img {
      cursor: pointer;
      width: 14px;
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
.page {
  text-align: center;
  margin-top: 20px;
}
</style>

