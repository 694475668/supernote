<template>
  <div class="custom">
    <div class="head">
      <ul>
        <li>
          <h5>姓名</h5>
          <el-input v-model="form.userName" maxlength="40"></el-input>
        </li>
        <li>
          <h5>手机号</h5>
          <el-input v-model="form.telephone" maxlength="15"></el-input>
        </li>
        <li>
          <h5>邮箱</h5>
          <el-input v-model="form.email" maxlength="30"></el-input>
        </li>
        <li>
          <h5>产品</h5>
          <el-select v-model="form.product" placeholder="请选择">
            <el-option
              v-for="(item,index) in productType"
              :key="index"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>处理状态</h5>
          <el-select v-model="form.flag" placeholder="请选择" style="width:100%">
            <el-option
              v-for="(item,index) in optionStatus"
              :key="index"
              :label="item.name"
              :value="item.status"
            ></el-option>
          </el-select>
        </li>
        <li>
          <el-button type="primary" @click="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
        <li v-permit="'/customize/open'">
          <el-button type="primary" @click="upcustomization">启用定制</el-button>
        </li>
        <li v-permit="'/customize/close'">
          <el-button type="primary" @click="offcustomization">停用定制</el-button>
        </li>
      </ul>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        highlight-current-row
        v-loading="loading"
      >
        <el-table-column prop="id" v-if="show"></el-table-column>
        <el-table-column align="center" label="姓名" show-overflow-tooltip prop="userName"></el-table-column>
        <el-table-column align="center" label="地区" show-overflow-tooltip prop="area"></el-table-column>
        <el-table-column align="center" label="邮箱" show-overflow-tooltip width="180px" prop="email"></el-table-column>
        <el-table-column
          align="center"
          label="国家码"
          show-overflow-tooltip
          width="70px"
          prop="countryCode"
        ></el-table-column>
        <el-table-column
          align="center"
          label="手机号"
          show-overflow-tooltip
          width="170"
          prop="telephone"
        ></el-table-column>
        <el-table-column
          align="center"
          label="产品类型"
          show-overflow-tooltip
          width="200"
          prop="product"
        ></el-table-column>
        <el-table-column align="center" label="封套" show-overflow-tooltip prop="coverColor"></el-table-column>
        <el-table-column align="center" label="笔编" show-overflow-tooltip prop="penColor"></el-table-column>
        <el-table-column
          align="center"
          label="描述"
          width="200"
          show-overflow-tooltip
          prop="description"
        ></el-table-column>
        <el-table-column align="center" label="备注" show-overflow-tooltip prop="remark"></el-table-column>
        <el-table-column align="center" label="备注人" prop="remarkUser"></el-table-column>
        <el-table-column align="center" label="备注时间" width="200" prop="remarkTime"></el-table-column>
        <el-table-column align="center" label="状态" :formatter="state" prop="flag"></el-table-column>
        <el-table-column align="center" label=" 创建时间" width="200" prop="createTime"></el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <div class="function-icon">
              <el-tooltip
                class="item"
                effect="dark"
                content="备注"
                placement="bottom"
                v-permit="'/customize/update'"
              >
                <img @click="change(scope.row)" src="../../assets/iconfont/amend.png" alt />
              </el-tooltip>
            </div>
          </template>
        </el-table-column>
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

    <el-dialog
      title="修改"
      :visible.sync="dialogFormVisible"
      @close="changeClose"
      :close-on-click-modal="false"
    >
      <el-form
        :model="remarkForm"
        :rules="changeRules"
        ref="remarkForm"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="状态" :label-width="formLabelWidth">
          <el-select v-model="remarkForm.flag" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in option"
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
import {
  queryAboutAll,
  updateCustomizeRemark,
  dataManipulation,
  updateProductCustomizeFlag
} from "../../api/customization.js";
import { searchDiclist } from "../../api/dictionary.js";
export default {
  data() {
    return {
      optionStatus: [
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
      value: "",
      show: "",
      currentPage: 1,
      total: 0,
      isDisable: false,
      tableData: [],
      form: {
        pageNo: 1,
        pageSize: 10,
        id: "",
        telephone: "",
        email: "",
        userName: "",
        product: "",
        flag: ""
      },
      forms: {
        pageNo: 1,
        pageSize: 10,
        id: "",
        telephone: "",
        email: "",
        userName: "",
        product: "",
        flag: ""
      },
      remarkForm: {
        remark: "",
        flag: ""
      },
      dialogFormVisible: false,
      formLabelWidth: "100px",
      id: "",
      option: [
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
      changeRules: [],

      dialogFormVisibles: false,
      addRules: [],
      productType: []
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    searchDiclist({
      name: "PRODUCT_TYPE",
      pageNo: 1,
      pageSize: 100,
      sortField: "",
      sortRules: "",
      valueMeaning: ""
    }).then(res => {
      this.productType = res.voList;
    });
  },
  methods: {
    //查询
    search() {
      this.loading = true;
      this.forms.pageNo = 1;
      this.currentPage = 1;
      this.forms.telephone = this.form.telephone;
      this.forms.userName = this.form.userName;
      this.forms.email = this.form.email;
      this.forms.product = this.form.product;
      this.forms.flag = this.form.flag;
      queryAboutAll(this.forms).then(res => {
        this.tableData = res.voList;
        this.total = res.total;
        this.loading = false;
      });
    },
    //清空
    empty() {
      this.form.telephone = "";
      this.form.userName = "";
      this.form.flag = "";
      this.form.email = "";
      this.forms.telephone = "";
      this.forms.flag = "";
      this.forms.pageNo = 1;
      this.forms.userName = "";
      this.forms.email = "";
      this.forms.product = "";
      this.form.product = "";
      this.search();
    },
    upcustomization() {
      updateProductCustomizeFlag("Y").then(res => {
        if (res.success) {
          this.$message({
            type: "success",
            message: "启用成功",
            center: true
          });
        } else {
          this.$message({
            type: "error",
            message: res.errorMsg,
            center: true
          });
        }
      });
    },
    offcustomization() {
      updateProductCustomizeFlag("N").then(res => {
        if (res.success) {
          this.$message({
            type: "success",
            message: "停用成功",
            center: true
          });
        } else {
          this.$message({
            type: "error",
            message: res.errorMsg,
            center: true
          });
        }
      });
    },
    getList() {
      this.loading = true;
      queryAboutAll(this.forms).then(res => {
        this.tableData = res.voList;
        this.total = res.total;
        this.loading = false;
      });
    },
    //判断类型
    state(row) {
      if (row.flag == "0") {
        return "待处理";
      } else if (row.flag == "1") {
        return "追踪中";
      } else if (row.flag == "2") {
        return "已处理";
      }
    },
    //修改
    change(row) {
      this.form.id = row.id;
      queryAboutAll(this.form).then(res => {
        this.remarkForm = res.voList[0];
        this.isDisable = false;
        this.dialogFormVisible = true;
        this.id = row.id;
        this.form.id = "";
      });
    },
    keep() {
      this.isDisable = true;
      this.loading = true;
      updateCustomizeRemark(this.remarkForm, this.id).then(res => {
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
    //弹框关闭
    changeClose() {
      this.dialogFormVisible = false;
    },

    //获取页码
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.forms.pageNo = currentPage;
      this.getList();
    }
  }
};
</script>

<style scoped lang='scss'>
.custom h5 {
  margin-left: 20px;
}
.head {
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
</style>
