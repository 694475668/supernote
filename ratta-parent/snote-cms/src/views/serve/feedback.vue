<template>
  <div class="feedback">
    <div class="head">
      <ul>
        <li>
          <h5>手机号</h5>
          <el-input v-model="form.telephone"></el-input>
        </li>
        <li>
          <h5>邮箱</h5>
          <el-input v-model="form.email"></el-input>
        </li>
        <li>
          <h5>设备号</h5>
          <el-input v-model="form.equipmentNumber"></el-input>
        </li>
        <li>
          <h5>反馈内容</h5>
          <el-input v-model="form.description"></el-input>
        </li>
        <li>
          <h5>处理状态</h5>
          <el-select v-model="form.status" placeholder="请选择" style="width:100%">
            <el-option
              v-for="(item,index) in option"
              :key="index"
              :label="item.name"
              :value="item.status"
            ></el-option>
          </el-select>
        </li>
        <li style="margin-left:40px;">
          <el-button type="primary" @click="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
      </ul>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%;margin-top:30px;"
      border
      v-loading="loading"
    >
      <el-table-column
        align="center"
        label="设备号"
        show-overflow-tooltip
        width="170"
        prop="equipmentNumber"
      ></el-table-column>
      <el-table-column align="center" label="姓名" show-overflow-tooltip prop="name"></el-table-column>
      <el-table-column align="center" label="地区" show-overflow-tooltip prop="area"></el-table-column>
      <el-table-column align="center" label="国家码" show-overflow-tooltip prop="countryCode"></el-table-column>
      <el-table-column
        align="center"
        label="手机号"
        show-overflow-tooltip
        width="170"
        prop="telephone"
      ></el-table-column>
      <el-table-column align="center" label="邮箱" show-overflow-tooltip width="200" prop="email"></el-table-column>
      <el-table-column align="center" label="反馈内容" show-overflow-tooltip prop="description"></el-table-column>
      <el-table-column align="center" label="反馈时间" width="170" prop="createTime"></el-table-column>
      <el-table-column align="center" label="处理状态" prop="status" :formatter="stateFormat"></el-table-column>
      <el-table-column align="center" label="备注" show-overflow-tooltip prop="remark"></el-table-column>
      <el-table-column align="center" label="操作人" prop="updateUser"></el-table-column>
      <el-table-column align="center" label="操作时间" width="170" prop="updateTime"></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <div class="function-icon">
            <el-tooltip
              class="item"
              effect="dark"
              content="备注"
              placement="bottom"
              v-permit="'/feedback/record/update'"
            >
              <img @click="change(scope.row)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
          </div>
        </template>
      </el-table-column>
    </el-table>
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
      title="修改"
      :visible.sync="dialogFormVisible"
      @close="changeClose"
      :close-on-click-modal="false"
    >
      <el-form
        :model="remarkForm"
        ref="remarkForm"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="状态" :label-width="formLabelWidth">
          <el-select v-model="remarkForm.status" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="remarkForm.remark" maxlength="255"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="changeClose">取 消</el-button>
        <el-button type="primary" @click="keep" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getLists, addList, changeList } from "../../api/feedback.js";
export default {
  data() {
    return {
      remarkForm: {
        remark: "",
        status: ""
      },
      formLabelWidth: "100px",
      options: [
        {
          value: "0",
          label: "待处理"
        },
        {
          value: "1",
          label: "追踪中"
        },
        {
          value: "2",
          label: "已处理"
        }
      ],
      dialogFormVisible: false,
      form: {
        description: "",
        equipmentNumber: "",
        pageNo: 1,
        pageSize: 10,
        status: "",
        email: "",
        telephone: ""
      },
      forms: {
        description: "",
        equipmentNumber: "",
        pageNo: 1,
        pageSize: 10,
        status: "",
        email: "",
        telephone: ""
      },
      forms1: {
        pageNo: 0,
        pageSize: 0,
        id: ""
      },
      addform: {
        name: "",
        contact: "",
        equipmentNumber: "",
        description: ""
      },
      value: false,
      isDisable: false,
      loading: false,
      isShow: false,
      tableData: [],
      total: 0,
      currentPage: 1,
      option: [
        {
          name: "待处理",
          status: "0"
        },
        {
          name: "追踪中",
          status: "1"
        },
        {
          name: "已处理",
          status: "2"
        }
      ],
      status: "",
      id: ""
    };
  },
  created() {},
  mounted() {
    this.getList();
  },
  directives: {
    // 注册一个局部的自定义指令 v-focus
    focus: {
      // 指令的定义
      inserted: function(el) {
        // 聚焦元素
        el.querySelector("input").focus();
      }
    }
  },
  methods: {
    //列表
    getList() {
      this.loading = true;
      setTimeout(() => {
        getLists(this.forms).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.loading = false;
        });
      }, 500);
    },
    //弹框关闭
    changeClose() {
      this.dialogFormVisible = false;
    },
    //查询
    search() {
      this.forms.pageNo = 1;
      this.forms.description = this.form.description;
      this.forms.equipmentNumber = this.form.equipmentNumber;
      this.forms.status = this.form.status;
      this.forms.telephone = this.form.telephone;
      this.forms.email = this.form.email;
      this.getList();
    },
    //判断类型
    stateFormat(row) {
      if (row.status == "0") {
        return "待处理";
      } else if (row.status == "1") {
        return "追踪中";
      } else if (row.status == "2") {
        return "已处理";
      }
    },
    //清空
    empty() {
      this.form.description = "";
      this.form.equipmentNumber = "";
      this.form.status = "";
      this.form.telephone = "";
      this.form.email = "";
      this.forms.description = "";
      this.forms.equipmentNumber = "";
      this.forms.status = "";
      this.forms.telephone = "";
      this.forms.email = "";
      this.forms.pageNo = 1;
      this.getList();
    },
    change(e) {
      this.dialogFormVisible = true;
      this.id = e.id;
      this.forms1.id = e.id;
      this.isDisable = false;
      getLists(this.forms1).then(res => {
        this.remarkForm = res.voList[0];
      });
    },
    keep() {
      this.isDisable = true;
      this.loading = true;
      changeList(this.id, this.remarkForm).then(res => {
        if (res.success) {
          this.$message({
            type: "success",
            message: "修改成功",
            center: true
          });
          this.loading = false;
          this.dialogFormVisible = false;
          this.getList();
        } else {
          this.$message({
            type: "error",
            message: res.errorMsg,
            center: true
          });
          this.loading = false;
          this.isDisable = false;
        }
      });
    },
    // //新增
    // add(){

    // },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.forms.pageNo = currentPage;
      this.getList();
    },
    //弹框封装
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //审核
    isopen(status, id) {
      changeList(id, { status }).then(res => {
        if (res.success) {
          this.msgTip("success", "审核成功");
          // this.status = status
          this.getList();
        } else {
          this.msgTip("error", res.errorMsg);
        }
      });
    }
  }
};
</script>

<style scoped lang='scss'>
.feedback {
  .head {
    margin: 20px 0 0 20px;
    ul {
      width: 100%;
      display: flex;
      padding-left: 0;
      align-items: flex-end;
      li {
        margin-left: 20px;
        &:nth-child(1) {
          margin-left: 0px;
        }
        .el-select .el-input {
          width: 100% !important;
        }
        .el-date-editor {
          width: 100%;
        }
        .el-input {
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
        span {
          font-size: 14px;
        }
        img {
          width: 15px;
          vertical-align: middle;
          margin-right: 5px;
        }
      }
      li:hover {
        background: #ccc;
      }
    }
  }
}
</style>
