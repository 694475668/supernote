<template>
  <div class="config">
    <h5>业务码</h5>
    <div class="head">
      <el-input class="name" v-model="forms.name"></el-input>
      <el-button type="primary" style="margin-left:20px" @click="search">查询</el-button>
      <el-button type="primary" @click="empty">清空</el-button>
      <el-button type="primary" @click="add" v-permit="'/reference/add'">添加</el-button>
    </div>
    <div class="tab">
      <el-table :data="tableData" style="width: 100%" border v-loading="loading">
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="serial" label="编码"></el-table-column>
        <el-table-column prop="name" label="业务码"></el-table-column>
        <el-table-column prop="value" label="变量值"></el-table-column>
        <el-table-column prop="valueCn" label="参数名"></el-table-column>
        <el-table-column prop="opUser" label="操作员"></el-table-column>
        <el-table-column prop="opTime" label="操作时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <div class="function-icon">
              <el-tooltip
                class="item"
                effect="dark"
                content="修改"
                placement="bottom"
                v-permit="'/reference/update'"
              >
                <img @click="change(scope.row)" src="../../assets/iconfont/amend.png" alt />
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="删除"
                placement="bottom"
                v-permit="'/reference/delete'"
              >
                <img @click="dele(scope.row)" src="../../assets/iconfont/delete.png" alt />
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
        <el-form-item label="业务码" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="编码" :label-width="formLabelWidth" prop="serial">
          <el-input v-model="form.serial" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="变量值" :label-width="formLabelWidth" prop="value">
          <el-input v-model="form.value" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="参数名" :label-width="formLabelWidth" prop="valueCn">
          <el-input v-model="form.valueCn" autocomplete="off"></el-input>
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
        <el-form-item label="业务码" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="编码" :label-width="formLabelWidth" prop="serial">
          <el-input v-model="form.serial" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="变量值" :label-width="formLabelWidth" prop="value">
          <el-input v-model="form.value" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="参数名" :label-width="formLabelWidth" prop="valueCn">
          <el-input v-model="form.valueCn" autocomplete="off"></el-input>
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
  getreferlist,
  addList,
  changeList,
  updateList,
  deletaList
} from "../../api/reference.js";
export default {
  data() {
    return {
      isDisable: false,
      value: "",
      total: 0,
      currentPage: 1,
      form: {
        name: "",
        remark: "",
        serial: "",
        value: "",
        valueCn: ""
      },
      forms: {
        name: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: ""
      },
      tableData: [],
      dialogFormVisible: false,
      dialogVisible: false,
      formLabelWidth: "120px",
      saveid: "",
      loading: false,

      //添加
      addForm: {
        value: [{ required: true, trigger: "blur" }],
        name: [{ required: true, trigger: "blur" }],
        serial: [{ required: true, trigger: "blur" }],
        valueCn: [{ required: true, trigger: "blur" }]
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
        getreferlist(this.forms).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.loading = false;
        });
      }, 500);
    },
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.forms.pageNo = currentPage;
      this.getList();
    },
    //查询
    search() {
      this.loading = true;
      setTimeout(() => {
        getreferlist({
          name: this.forms.name,
          pageNo: 1,
          pageSize: 10,
          sortField: "",
          sortRules: ""
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
      this.forms.name = "";
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
          this.isDisable = true;
          // 表单验证通过之后的操作
          addList(this.form).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "添加成功",
                center: true
              });
              this.dialogFormVisible = false;
              this.getList();
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
            }
          });
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
          this.isDisable = false;
        }
      });
    },
    //弹框关闭
    close() {
      this.dialogFormVisible = false;
      this.dialogVisible = false;
      this.$refs.form.resetFields();
    },

    //修改
    change(v) {
      this.dialogVisible = true;
      this.isDisable = false;
      changeList(v.id).then(res => {
        this.form.name = res.name;
        this.form.serial = res.serial;
        this.form.value = res.value;
        this.form.valueCn = res.valueCn;
      });
      this.saveid = v.id;
    },
    save() {
      this.$refs.form.validate(valid => {
        if (valid) {
          this.isDisable = true;
          // 表单验证通过之后的操作
          updateList(this.saveid, this.form).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "修改成功",
                center: true
              });
              this.dialogVisible = false;
              this.getList();
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
              this.isDisable = true;
            }
          });
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
        }
      });
    },

    //删除
    dele(v) {
      this.$confirm("您是否要删除当前选中的记录?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        deletaList(v.id).then(res => {
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
          }
        });
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

<style lang='scss' scoped>
.config {
  h5 {
    margin-left: 20px;
  }
  .head {
    display: flex;
    margin-left: 20px;
  }
  .name {
    width: 200px;
  }
  .tab {
    margin-top: 20px;
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

