<template>
  <div class="issue">
    <div class="issue-head">
      <ul>
        <li>
          <h5>设备型号</h5>
          <el-select v-model="form.equipmentModel" placeholder="请选择">
            <el-option
              v-for="item in type"
              :key="item.value"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>固件版本号</h5>
          <el-input v-model="form.firmwareVersion"></el-input>
        </li>
        <li>
          <h5>历史</h5>
          <el-select v-model="form.isHistory" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>跳过</h5>
          <el-select v-model="form.isStride" placeholder="请选择">
            <el-option
              v-for="item in isStrides"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>起始时间</h5>
          <div class="block">
            <el-date-picker v-model="form.beginTime"  type="date" value-format="yyyy-MM-dd"></el-date-picker>
          </div>
        </li>
        <li>
          <h5>截止时间</h5>
          <div class="block">
            <el-date-picker v-model="form.endTime" type="date" value-format="yyyy-MM-dd"></el-date-picker>
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
        <li v-permit="'/firmware/task/deploy'">
          <img src="../../assets/iconfont/issue.png" alt />
          <span @click="issue">发布</span>
        </li>
        <li v-permit="'/firmware/task/flash'">
          <img src="../../assets/iconfont/unload.png" alt />
          <span @click="roots">刷机</span>
        </li>
        <li v-permit="'/firmware/task/undo'">
          <img src="../../assets/iconfont/repeal.png" alt />
          <span @click="repeal">撤销</span>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
        @row-click="getRow"
        highlight-current-row
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="equipmentModel" label="设备型号" width="150px"></el-table-column>
        <el-table-column prop="area" label="区域" width="100px"></el-table-column>
        <el-table-column prop="custom" label="定制" width="100px"></el-table-column>
        <el-table-column prop="configuration" label="配置" width="70px"></el-table-column>
        <el-table-column prop="equipmentNumber" width="150px" label="设备号"></el-table-column>
        <el-table-column prop="firmwareVersion" label="发布版本" width="280px"></el-table-column>
        <el-table-column prop="type" label="任务类型" :formatter="stateFormat" width="80px"></el-table-column>
        <el-table-column prop="status" label="状态" :formatter="statusFormat" width="70px"></el-table-column>
        <el-table-column prop="equipmentPurpose" label="设备用途" width="90px"></el-table-column>
        <el-table-column prop="isStride" label="跳过" :formatter="isStrideFormat"></el-table-column>
        <el-table-column prop="createUser" label="操作员"></el-table-column>
        <el-table-column prop="createTime" label="操作时间" show-overflow-tooltip></el-table-column>
        <el-table-column prop="revokeUser" label="撤销人员"></el-table-column>
        <el-table-column prop="revokeTime" label="撤销时间" show-overflow-tooltip></el-table-column>
        <el-table-column prop="isHistory" label="历史" :formatter="historyFormat"></el-table-column>
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
      title="发布"
      :visible.sync="dialogFromVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="publice"
        :rules="publiceRules"
        ref="publice"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="发布版本：" :label-width="formLabelWidth" prop="firmwareVersion">
          <el-select
            v-model="publice.firmwareVersion"
            placeholder="请选择"
            style="width:100%"
            @focus="getFirst()"
            @change="handleFirst"
          >
            <el-option v-for="item in version" :key="item" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          v-if="publice.firmwareVersion!=''"
          label="设备型号："
          :label-width="formLabelWidth"
          prop="equipmentModel"
        >
          <el-select
            v-model="publice.equipmentModel"
            placeholder="请选择"
            @focus="getSecond()"
            @change="handleSecond"
            filterable
            style="width:100%"
          >
            <el-option
              v-for="item in model"
              :key="item.equipmentModel"
              :label="item.equipmentModelName"
              :value="item.equipmentModel"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          v-if="publice.equipmentModel!=''"
          label="设备用途："
          :label-width="formLabelWidth"
          prop="equipmentPurpose"
        >
          <el-select
            v-model="publice.equipmentPurpose"
            @focus="getThree()"
            @change="handleTree"
            placeholder="请选择"
            style="width:100%"
          >
            <el-option
              v-for="item in purpose"
              :key="item.equipmentPurposeValue"
              :label="item.equipmentPurposeName"
              :value="item.equipmentPurposeValue"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="release" :disabled="isDisable">发布</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="刷机"
      :visible.sync="dialogFromsVisible"
      @close="flagclose"
      :close-on-click-modal="false"
    >
      <el-form>
        <el-form-item label="刷机类型：" :label-width="formLabelWidth" prop="firmwareVersion">
          <el-radio v-model="radio" label="1" @change="changeRadio">单刷</el-radio>
          <el-radio v-model="radio" label="2" @change="changeRadio">批刷</el-radio>
        </el-form-item>
      </el-form>
      <div v-show="isFlag">
        <el-form
          :model="alonePublice"
          :rules="aloneRules"
          ref="alonePublice"
          :status-icon="true"
          :show-message="false"
          hide-required-asterisk
        >
          <el-form-item label="发布版本：" :label-width="formLabelWidth" prop="firmwareVersion">
            <el-select
              v-model="alonePublice.firmwareVersion"
              placeholder="请选择"
              style="width:100%"
              @focus="getFirst()"
              @change="handleFirst"
            >
              <el-option v-for="item in version" :key="item" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="设备号：" :label-width="formLabelWidth" prop="equipmentNumber">
            <el-input v-model="alonePublice.equipmentNumber" type="text" maxlength="100"></el-input>
          </el-form-item>
          <div style="display: flex;justify-content: flex-end">
            <el-button @click="dialogFromsVisible=false">取 消</el-button>
            <el-button type="primary" @click="aloneRoot" :disabled="isDisable">刷机</el-button>
          </div>
        </el-form>
      </div>

      <div v-show="flag">
        <el-form
          :model="publice"
          :rules="publiceRules"
          ref="publice"
          :status-icon="true"
          :show-message="false"
          hide-required-asterisk
        >
          <el-form-item label="发布版本：" :label-width="formLabelWidth" prop="firmwareVersion">
            <el-select
              v-model="publice.firmwareVersion"
              placeholder="请选择"
              style="width:100%"
              @focus="getFirst()"
              @change="handleFirst"
            >
              <el-option v-for="item in version" :key="item" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            v-if="publice.firmwareVersion!=''"
            label="设备型号："
            :label-width="formLabelWidth"
            prop="equipmentModel"
          >
            <el-select
              v-model="publice.equipmentModel"
              placeholder="请选择"
              @focus="getSecond()"
              @change="handleSecond"
              filterable
              style="width:100%"
            >
              <el-option
                v-for="item in model"
                :key="item.equipmentModel"
                :label="item.equipmentModelName"
                :value="item.equipmentModel"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            v-if="publice.equipmentModel!=''"
            label="设备用途："
            :label-width="formLabelWidth"
            prop="equipmentPurpose"
          >
            <el-select
              v-model="publice.equipmentPurpose"
              @focus="getThree()"
              @change="handleTree"
              placeholder="请选择"
              style="width:100%"
            >
              <el-option
                v-for="item in purpose"
                :key="item.equipmentPurposeValue"
                :label="item.equipmentPurposeName"
                :value="item.equipmentPurposeValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div style="display: flex;justify-content: flex-end">
          <el-button @click="flagclose">取 消</el-button>
          <el-button type="primary" @click="root">刷机</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getTask,
  getVersion,
  getModel,
  getPurpose,
  getDeploy,
  getRepeal,
  getFlash,
  getFlashAll,
  dataManipulation
} from "../../api/firwaretask.js";
import { searchDiclist } from "../../api/dictionary.js";
import { getLanList } from "../../api/firmwarepair.js";
export default {
  data() {
    return {
      isDisable: false,
      options: [
        {
          value: 0,
          label: "否"
        },
        {
          value: 1,
          label: "是"
        }
      ],
      isStrides: [
        {
          value: 0,
          label: "否"
        },
        {
          value: 1,
          label: "是"
        }
      ],
      type: [],
      tableData: [],
      rowData: [],
      form: {
        beginTime: "",
        endTime: "",
        equipmentModel: "",
        firmwareVersion: "",
        isHistory: "",
        isStride: "",
        pageNo: 1,
        pageSize: 10
      },
      total: 0,
      currentPage: 1,

      //发布
      dialogFromVisible: false,
      formLabelWidth: "100px",
      publice: {
        firmwareVersion: "",
        equipmentPurpose: "",
        equipmentModel: ""
      },
      version: [],
      model: [],
      purpose: [],
      publiceRules: {
        firmwareVersion: [{ required: true, trigger: "blur" }],
        equipmentModel: [{ required: true, trigger: "blur" }],
        equipmentPurpose: [{ required: true, trigger: "blur" }]
      },
      alonePublice: {
        firmwareVersion: "",
        equipmentNumber: ""
      },
      aloneRules: {
        firmwareVersion: [{ required: true, trigger: "blur" }],
        equipmentNumber: [{ required: true, trigger: "blur" }]
      },
      //刷机
      dialogFromsVisible: false,
      radio: "1",
      isFlag: true,
      flag: false,
      loading: false,
      isRepeat: false
    };
  },
  created() {
    this.getList();
    getLanList({
      name: "EQUIPMENT_MODEL",
      value: ""
    }).then(res => {
      this.type = res.dictionaryVOList;
    });
  },

  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        getTask(this.form).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.loading = false;
          this.isRepeat = false;
        });
      }, 500);
    },
    getRow(val) {
      this.rowData = val;
      this.current = val;
    },
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.form.pageNo = currentPage;
      this.getList();
    },
    stateFormat(row) {
      if (row.type === "1") {
        return "审核测试";
      } else if (row.type === "2") {
        return "发布";
      } else {
        return "刷机";
      }
    },
    statusFormat(row) {
      if (row.status === "0") {
        return "正常";
      } else if (row.status === "1") {
        return "撤销";
      }
    },
    historyFormat(row) {
      if (row.isHistory === "0") {
        return "否";
      } else if (row.isHistory === "1") {
        return "是";
      }
    },
    isStrideFormat(row) {
      if (row.isStride === "0") {
        return "否";
      } else if (row.isStride === "1") {
        return "是";
      }
    },
    //查询
    search() {
      this.loading = true;
      setTimeout(() => {
        getTask({
          beginTime: this.form.beginTime,
          endTime: this.form.endTime,
          equipmentModel: this.form.equipmentModel,
          firmwareVersion: this.form.firmwareVersion,
          isHistory: this.form.isHistory,
          isStride: this.form.isStride,
          pageNo: 1,
          pageSize: 10
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
      this.form.beginTime = "";
      this.form.endTime = "";
      this.form.equipmentModel = "";
      this.form.firmwareVersion = "";
      this.form.isHistory = "";
      this.form.isStride = "";
      this.getList();
    },
    //弹框关闭
    close() {
      this.dialogFromVisible = false;
      this.$refs.publice.resetFields();
    },
    flagclose() {
      this.dialogFromsVisible = false;
      this.$refs.publice.resetFields();
      this.$refs.alonePublice.resetFields();
    },
    //发布
    issue() {
      this.dialogFromVisible = true;
      this.isDisable = false;
    },
    getFirst() {
      getVersion({ status: 2 }).then(res => {
        this.model = [];
        this.purpose = [];
        this.publice.equipmentModel = "";
        this.publice.equipmentPurpose = "";
        this.version = res.voList;
      });
    },
    handleFirst(val) {
      this.publice.firmwareVersion = val;
    },
    getSecond() {
      getModel({ firmwareVersion: this.publice.firmwareVersion }).then(res => {
        this.purpose = [];
        this.publice.equipmentPurpose = "";
        this.model = res.voList;
      });
    },
    handleSecond(val) {
      this.publice.equipmentModel = val;
    },
    getThree() {
      getPurpose(this.publice.equipmentModel).then(res => {
        this.purpose = res.voList;
      });
    },
    handleTree(val) {
      this.publice.equipmentPurpose = val;
    },
    release() {
      this.$refs.publice.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          this.isRepeat = true;
          this.isDisable = true;
          getDeploy(this.publice).then(res => {
            if (res.success) {
              this.msgTip("success", "发布成功");
              this.dialogFromVisible = false;
              this.getList();
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

    //撤销
    repeal() {
      if (this.current == undefined || this.current == null) {
        this.msgTip("warning", "请选择需要撤销的数据");
      } else {
        console.log(this.current, "000");
        if (this.current.status == "1") {
          this.msgTip("warning", "请选择未撤销的数据！");
        } else {
          this.$confirm("您是否要撤销当前选中的记录？", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() => {
            this.isRepeat = true;
            getRepeal(this.current.id).then(res => {
              if (res.success) {
                this.msgTip("success", "撤销成功");
                // 获取当前日期时间
                dataManipulation(this.tableData, this.rowData, "1", getDate());
              } else {
                this.msgTip("error", res.errorMsg);
                this.isRepeat = false;
              }
            });
          });
        }
      }
    },
    //刷机
    roots() {
      this.dialogFromsVisible = true;
      this.isDisable = false;
    },
    changeRadio(val) {
      if (val == "1") {
        this.isFlag = true;
        this.flag = false;
      } else {
        this.isFlag = false;
        this.flag = true;
        this.publice.firmwareVersion = "";
      }
    },
    aloneRoot() {
      this.$refs.alonePublice.validate(valid => {
        if (valid) {
          this.isDisable = true;
          // 表单验证通过之后的操作
          getFlash(this.alonePublice).then(res => {
            if (res.success) {
              this.msgTip("success", "刷机成功");
              this.dialogFromsVisible = false;
              this.getList();
            } else {
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
    root() {
      this.$refs.publice.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          getFlashAll(this.publice).then(res => {
            if (res.success) {
              this.msgTip("success", "刷机成功");
              this.dialogFromsVisible = false;
              this.getList();
            } else {
              this.msgTip("error", res.errorMsg);
            }
          });
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
        }
      });
    },
    //信息提示框
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
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
.issue {
  list-style: none;
  .issue-head {
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
.page {
  text-align: center;
  margin-top: 20px;
}
.el-table th {
  text-align: center;
}
.el-table td {
  text-align: center;
}
</style>

