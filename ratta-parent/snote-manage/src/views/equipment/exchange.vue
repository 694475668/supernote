<template>
  <div class="exchange">
    <div class="exchange-head">
      <ul>
        <li>
          <h5>设备号</h5>
          <el-input v-model="form.equipmentNumber"></el-input>
        </li>
        <li>
          <h5>健康状态</h5>
          <el-select v-model="form.healthyState" placeholder="请选择">
            <el-option
              v-for="item in health"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>类型</h5>
          <el-select v-model="form.type" placeholder="请选择">
            <el-option
              v-for="item in option"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>起始时间</h5>
          <div class="block">
            <el-date-picker
              v-model="form.startTime"
              type="date"
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
              :editable="false"
            ></el-date-picker>
          </div>
        </li>
        <li>
          <h5>截止时间</h5>
          <div class="block">
            <el-date-picker
              v-model="form.endTime"
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
        <li v-permit="'/goods/return'">
          <img src="../../assets/iconfont/return.png" alt />
          <span @click="salesReturn">退货</span>
        </li>
        <li v-permit="'/goods/exchange'">
          <img src="../../assets/iconfont/exchange.png" alt />
          <span @click="barter">换货</span>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
        @current-change="handleClick"
        highlight-current-row
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="equipmentNumber" label="设备号"></el-table-column>
        <el-table-column prop="serrialNumber" label="编号"></el-table-column>
        <el-table-column prop="returnTime" label="时间"></el-table-column>
        <el-table-column prop="businessHandler" label="业务受理人"></el-table-column>
        <el-table-column prop="exchangeEquipmentNumber" label="换货设备号"></el-table-column>
        <el-table-column prop="type" label="类型" :formatter="stateFormat"></el-table-column>
        <el-table-column prop="healthyState" label="健康状态"></el-table-column>
        <el-table-column prop="remark" label="备注" show-overflow-tooltip></el-table-column>
        <el-table-column prop="opUser" label="创建人"></el-table-column>
        <el-table-column prop="opTime" label="操作时间"></el-table-column>
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
      title="退货"
      :visible.sync="dialogFormsVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="returns"
        :rules="returnRules"
        ref="returns"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="设备号" :label-width="formLabelWidth" prop="equipmentNumber">
          <el-input v-model="returns.equipmentNumber"></el-input>
        </el-form-item>
        <el-form-item label="健康状态" :label-width="formLabelWidth" prop="healthyState">
          <el-select v-model="returns.healthyState" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in health"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="单号" :label-width="formLabelWidth" prop="serrialNumber">
          <el-input v-model="returns.serrialNumber" type="text" maxlength="20"></el-input>
        </el-form-item>
        <el-form-item label="时间" :label-width="formLabelWidth" prop="returnTime">
          <el-date-picker
            v-model="returns.returnTime"
            type="date"
            format="yyyy/MM/dd"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
            style="width:100%"
            :editable="false"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="业务受理人" :label-width="formLabelWidth" prop="businessHandler">
          <el-input v-model="returns.businessHandler" type="text" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input v-model="returns.remark" type="text" maxlength="255"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="save" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="换货"
      :visible.sync="dialogFormVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="exchange"
        :rules="exchangeRules"
        ref="exchange"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="设备号" :label-width="formLabelWidth" prop="equipmentNumber">
          <el-input v-model="exchange.equipmentNumber"></el-input>
        </el-form-item>
        <el-form-item label="健康状态" :label-width="formLabelWidth" prop="healthyState">
          <el-select v-model="exchange.healthyState" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in health"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="单号" :label-width="formLabelWidth" prop="serrialNumber">
          <el-input v-model="exchange.serrialNumber" type="text"></el-input>
        </el-form-item>
        <el-form-item label="时间" :label-width="formLabelWidth" prop="returnTime">
          <el-date-picker
            v-model="exchange.returnTime"
            type="date"
            format="yyyy/MM/dd"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
            style="width:100%"
            :editable="false"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="业务受理人" :label-width="formLabelWidth" prop="businessHandler">
          <el-input v-model="exchange.businessHandler" type="text" maxlength="25"></el-input>
        </el-form-item>
        <el-form-item label="换货设备号" :label-width="formLabelWidth" prop="exchangeEquipmentNumber">
          <el-input v-model="exchange.exchangeEquipmentNumber" type="text"></el-input>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input v-model="exchange.remark" type="text" maxlength="255"></el-input>
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
import { getExchange, getBarter, getReturn } from "../../api/return.js";
export default {
  data() {
    return {
      isDisable: false,
      value: "",
      value1: "",
      value2: "",
      loading: false,
      isRepeat: false,
      health: [
        {
          value: "0",
          label: "正常"
        },
        {
          value: "1",
          label: "维修中"
        },
        {
          value: "2",
          label: "报废"
        }
      ],
      option: [
        {
          value: 1,
          label: "退货"
        },
        {
          value: 2,
          label: "换货"
        }
      ],
      tableData: [],
      multipleSelection: [],
      form: {
        endTime: "",
        equipmentNumber: "",
        healthyState: "",
        pageNo: 1,
        pageSize: 10,
        startTime: "",
        type: ""
      },
      formLabelWidth: "100px",
      //退货
      dialogFormsVisible: false,
      returns: {
        equipmentNumber: "",
        healthyState: "",
        businessHandler: "",
        remark: "",
        returnTime: "",
        serrialNumber: ""
      },
      returnRules: {
        equipmentNumber: [{ required: true, trigger: "blur" }],
        healthyState: [{ required: true, trigger: "blur" }],
        businessHandler: [{ required: true, trigger: "blur" }],
        returnTime: [{ required: true, trigger: "blur" }],
        serrialNumber: [{ required: true, trigger: "blur" }]
      },

      //换货
      dialogFormVisible: false,
      exchange: {
        businessHandler: "",
        equipmentNumber: "",
        exchangeEquipmentNumber: "",
        healthyState: "",
        remark: "",
        returnTime: "",
        serrialNumber: ""
      },
      exchangeRules: {
        equipmentNumber: [{ required: true, trigger: "blur" }],
        healthyState: [{ required: true, trigger: "blur" }],
        businessHandler: [{ required: true, trigger: "blur" }],
        returnTime: [{ required: true, trigger: "blur" }],
        serrialNumber: [{ required: true, trigger: "blur" }],
        exchangeEquipmentNumber: [{ required: true, trigger: "blur" }]
      },
      total: 0,
      currentPage: 1
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        getExchange(this.form).then(res => {
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
      this.form.pageNo = currentPage;
      this.getList();
    },
    handleClick(val) {
      this.currentrow = val;
    },
    stateFormat(row) {
      if (row.type == "1") {
        return "退货";
      } else if (row.type == "2") {
        return "换货";
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
        getExchange({
          endTime: this.form.endTime,
          equipmentNumber: this.form.equipmentNumber,
          healthyState: this.form.healthyState,
          pageNo: 1,
          pageSize: 10,
          startTime: this.form.startTime,
          type: this.form.type
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
      this.form.equipmentNumber = "";
      this.form.healthyState = "";
      this.form.type = "";
      this.form.startTime = "";
      this.form.endTime = "";
      this.getList();
    },

    //退货
    salesReturn() {
      this.returns.equipmentNumber = "";
      this.returns.healthyState = "";
      this.returns.businessHandler = "";
      this.returns.remark = "";
      this.returns.returnTime = "";
      this.returns.serrialNumber = "";
      this.dialogFormsVisible = true;
      this.isDisable = false;
      //this.returns.equipmentNumber = this.currentrow.equipmentNumber;
    },
    save() {
      this.$refs.returns.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          this.isRepeat = true;
          this.isDisable = true;
          getReturn(this.returns).then(res => {
            if (res.success) {
              this.msgTip("success", "退货成功");
              this.dialogFormsVisible = false;
              this.empty();
              this.$refs.returns.resetFields();
            } else {
              this.msgTip("error", res.errorMsg);
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
    close() {
      this.dialogFormsVisible = false;
      this.dialogFormVisible = false;
      this.$refs.returns.resetFields();
    },
    //换货
    barter() {
      this.exchange.businessHandler = "";
      this.exchange.equipmentNumber = "";
      this.exchange.exchangeEquipmentNumber = "";
      this.exchange.healthyState = "";
      this.exchange.remark = "";
      this.exchange.returnTime = "";
      this.exchange.serrialNumber = "";
      this.dialogFormVisible = true;
      this.isDisable = false;
      this.returns.equipmentNumber = this.currentrow.equipmentNumber;
    },
    keep() {
      this.$refs.exchange.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          this.isRepeat = true;
          this.isDisable = true;
          getBarter(this.exchange).then(res => {
            if (res.success) {
              this.msgTip("success", "换货成功");
              this.dialogFormVisible = false;
              this.empty();
              this.$refs.returns.resetFields();
            } else {
              this.msgTip("error", res.errorMsg);
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
    indexMethod(index) {
      return index + 1;
    }
  }
};
</script>

<style lang="scss" scoped>
.exchange {
  list-style: none;
  .exchange-head {
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

