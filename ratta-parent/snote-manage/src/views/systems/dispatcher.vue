<template>
  <div class="dispatcher">
    <div class="disp-head">
      <ul>
        <li>
          <h5>任务名称</h5>
          <el-input v-model="form.name"></el-input>
        </li>
        <li>
          <h5>任务状态</h5>
          <el-select v-model="form.status" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
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
        <li v-permit="'/schedule/open'">
          <img src="../../assets/iconfont/start.png" alt />
          <span @click="start">启用</span>
        </li>
        <li v-permit="'/schedule/close'">
          <img src="../../assets/iconfont/end.png" alt />
          <span @click="disableItem">停用</span>
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
        <el-table-column prop="date" label="执行日志" width="150px">
          <template slot-scope="scope">
            <div class="watch" @click="see(scope.row)">查看日志</div>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="任务名称" width="200px"></el-table-column>
        <el-table-column prop="status" label="任务状态" :formatter="stateFormat" width="150px"></el-table-column>
        <el-table-column prop="cron" label="调度时间" width="200px"></el-table-column>
        <el-table-column prop="remark" label="任务描述"></el-table-column>
        <el-table-column label="操作" width="150px">
          <template slot-scope="scope">
            <div class="function-icon">
              <el-tooltip
                class="item"
                effect="dark"
                content="修改"
                placement="bottom"
                v-permit="'/schedule/update'"
              >
                <img @click="change(scope.row)" src="../../assets/iconfont/amend.png" alt />
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="执行"
                placement="bottom"
                v-permit="'/schedule/execute'"
              >
                <img @click="execute(scope.row)" src="../../assets/iconfont/execute.png" alt />
              </el-tooltip>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      title="查看日志"
      :visible.sync="dialogTableVisible"
      width="65%"
      :close-on-click-modal="false"
    >
      <div class="diaHead">
        <ol>
          <li>
            <div>起始时间</div>
            <el-date-picker
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              v-model="startValue"
              type="date"
              :editable="false"
              placeholder="选择日期"
            ></el-date-picker>
          </li>
          <li>
            <div>截止日期</div>
            <el-date-picker
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              :editable="false"
              v-model="endValue"
              type="date"
              placeholder="选择日期"
            ></el-date-picker>
          </li>
          <li>
            <el-button type="primary" @click="searchLog">查询</el-button>
          </li>
          <li>
            <el-button type="primary" @click="emptys">清空</el-button>
          </li>
        </ol>
      </div>
      <el-table :data="gridData" height="250" border>
        <el-table-column property="id" label="ID" width="150"></el-table-column>
        <el-table-column property="ksrq" label="开始时间" width="200" format="yyyy/MM/dd"></el-table-column>
        <el-table-column property="jsrq" label="结束时间" format="yyyy/MM/dd"></el-table-column>
        <el-table-column property="result" label="执行结果" :formatter="resultState"></el-table-column>
      </el-table>
      <div class="page">
        <el-pagination
          background
          layout="total, prev, pager, next"
          :page-size="10"
          :total="totals"
          :current-page="currentPage"
          @current-change="changepages"
        ></el-pagination>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改任务" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="form">
        <el-form-item label="任务名称" :label-width="formLabelWidth">
          <el-input v-model="username" :disabled="true" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="调度任务" :label-width="formLabelWidth">
          <div class="cron">
            <el-popover v-model="cronPopover">
              <cron @change="changeCron" @close="cronPopover=false" i18n="cn"></cron>
              <el-input :readonly="true" slot="reference" @click="cronPopover=true" v-model="task"></el-input>
            </el-popover>
          </div>
        </el-form-item>
        <el-form-item label="任务描述" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="describe"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
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
  </div>
</template>

<script>
import { cron } from "vue-cron";
import {
  getDislist,
  searchDislist,
  executeTime,
  startDislist,
  amendDislist,
  amendsDislist,
  dataManipulation
} from "../../api/dispatcher.js";
export default {
  components: { cron },
  data() {
    return {
      isDisable: false,
      isFlag: true,
      startValue: "",
      endValue: "",
      options: [
        {
          label: "启用",
          value: 0
        },
        {
          label: "停用",
          value: 1
        }
      ],
      tableData: [],
      gridData: [],
      dialogTableVisible: false,
      dialogVisible: false,
      loading: false,
      isRepeat: false,
      form: {
        name: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: "",
        status: ""
      },
      cronPopover: false,
      cron: "",
      username: "",
      task: "",
      describe: "",
      multipleSelection: [],
      formLabelWidth: "120px",
      saveid: "",
      total: 0,
      currentPage: 1,
      totals: 0,
      pageNo: 1,
      localId: ""
    };
  },
  created() {
    this.getList();
  },
  methods: {
    changeCron(val) {
      this.task = val;
    },
    getList() {
      this.loading = true;
      setTimeout(() => {
        getDislist(this.form).then(res => {
          this.tableData = res.voList;
          this.total = Number(res.total);
          this.loading = false;
          this.isRepeat = false;
        });
      }, 500);
    },
    stateFormat(row) {
      if (row.status === "0") {
        return "启用";
      } else {
        return "停用";
      }
    },
    //执行结果
    resultState(row) {
      if (row.result === "0") {
        return "失败";
      } else {
        return "成功";
      }
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.form.pageNo = currentPage;
      this.getList();
    },
    changepages(currentPage) {
      this.pageNo = currentPage;
      searchDislist({
        jsrq: this.endValue,
        ksrq: this.startValue,
        pageNo: this.pageNo,
        pageSize: 10,
        sortField: "",
        sortRules: "",
        taskId: this.localId
      }).then(res => {
        this.gridData = res.voList;
        this.totals = res.total;
      });
    },
    //查询
    search() {
      this.loading = true;
      setTimeout(() => {
        getDislist({
          name: this.form.name,
          pageNo: 1,
          pageSize: 10,
          sortField: "",
          sortRules: "",
          status: this.form.status
        }).then(res => {
          this.form.pageNo = 1;
          this.tableData = res.voList;
          this.total = Number(res.total);
          this.currentPage = 1;
          this.loading = false;
        });
      }, 500);
    },
    //查看日志
    see(v) {
      this.dialogTableVisible = true;
      this.localId = v.id;
      searchDislist({
        jsrq: "",
        ksrq: "",
        pageNo: this.pageNo,
        pageSize: 10,
        sortField: "",
        sortRules: "",
        taskId: this.localId
      }).then(res => {
        this.gridData = res.voList;
        this.totals = res.total;
      });
    },
    //查看日志里面进行查询
    searchLog() {
      searchDislist({
        jsrq: this.endValue,
        ksrq: this.startValue,
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: "",
        taskId: this.localId
      }).then(res => {
        this.gridData = res.voList;
        this.currentPage = 1;
        this.totals = res.total;
      });
    },
    //清空
    empty() {
      this.form.name = "";
      this.form.status = "";
      this.getList();
    },
    //清空查看日志中的日期
    emptys() {
      this.startValue = "";
      this.endValue = "";
      this.searchLog();
    },
    handleSelectionChange(val) {
      console.log(val, "每列的数据");
      this.multipleSelection = val;
      console.log(this.multipleSelection.length);
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
    //启用
    start() {
      if (!this.findSelectData()) {
        this.msgTip("error", "至少选择一条数据");
      } else {
        if (this.isFlag) {
          this.isFlag = false;
          let arr = [];
          this.multipleSelection.map(res => {
            arr.push(JSON.stringify(res.id));
          });
          this.isRepeat = true;
          startDislist({ idList: arr, status: 0 }).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "启用成功",
                center: true
              });
              dataManipulation(this.tableData, this.multipleSelection, "0");
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
    },
    // 停用
    disableItem() {
      if (!this.findSelectData()) {
        this.msgTip("error", "至少选择一条数据");
      } else {
        if (this.isFlag) {
          this.isFlag = false;
          let arr = [];
          this.multipleSelection.map(res => {
            arr.push(res.id);
          });
          this.isRepeat = true;
          startDislist({ idList: arr, status: 1 }).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "停用成功",
                center: true
              });
              dataManipulation(this.tableData, this.multipleSelection, "1");
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
    },
    //修改
    change(v) {
      this.dialogVisible = true;
      this.isDisable = false;
      amendsDislist(v.id).then(res => {
        this.username = res.name;
        this.task = res.cron;
        this.describe = res.remark;
      });
      this.saveid = v.id;
    },
    save() {
      this.isRepeat = true;
      this.isDisable = true;
      amendDislist({
        cron: this.task,
        id: this.saveid,
        remark: this.describe
      }).then(res => {
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
          this.isRepeat = false;
          this.isDisable = false;
        }
      });
    },
    //执行
    execute(v) {
      if (this.isFlag) {
        this.isFlag = false;
        this.isRepeat = true;
        executeTime(v.id).then(res => {
          if (res.success) {
            this.$message({
              type: "success",
              message: "已执行定时任务",
              center: true
            });
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
.dispatcher {
  .disp-head {
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
    background: #eee;
    line-height: 40px;
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
          width: 14px;
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
  .watch {
    color: #1296db;
    cursor: pointer;
  }
  .diaHead {
    ol {
      display: flex;
      padding-left: 0;
      li {
        display: flex;
        align-items: center;
        margin-left: 20px;
        &:first-child {
          margin-left: 0;
        }
        div {
          margin-right: 10px;
        }
      }
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

