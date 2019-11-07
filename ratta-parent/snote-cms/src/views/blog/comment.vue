<template>
  <div class="comment">
    <div class="head">
      <ul>
        <li>
          <h5>置顶标识</h5>
          <el-select v-model="form.roofPlacement" placeholder="请选择">
            <el-option
              v-for="item in recommendFlags"
              :key="item.recommendFlag"
              :label="item.label"
              :value="item.recommendFlag"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>审核标识</h5>
          <el-select v-model="form.audit" placeholder="请选择">
            <el-option
              v-for="item in audits"
              :key="item.audit"
              :label="item.label"
              :value="item.audit"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>博客标题</h5>
          <el-select v-model="form.titleId" placeholder="请选择">
            <el-option v-for="item in titleCns" :key="item.id" :label="item.title" :value="item.id"></el-option>
          </el-select>
        </li>
        <li>
          <el-button type="primary" @click="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
      </ul>
    </div>
    <el-table :data="tableData" style="width: 100%;margin-top:30px;" border v-loading="loading">
      <el-table-column align="center" label="序号" prop="seq"></el-table-column>
      <el-table-column align="center" label="博客" show-overflow-tooltip prop="title"></el-table-column>
      <el-table-column align="center" label="评论人" show-overflow-tooltip prop="userName"></el-table-column>
      <el-table-column align="center" label="评论内容" show-overflow-tooltip prop="content"></el-table-column>
      <el-table-column
        align="center"
        label="置顶标识"
        :formatter="roofPlacementFormatter"
        prop="roofPlacement"
      ></el-table-column>
      <el-table-column
        align="center"
        width="100"
        label="审核标识"
        :formatter="auditFormatter"
        prop="audit"
      ></el-table-column>
      <el-table-column align="center" label="评论时间" prop="createTime"></el-table-column>
      <el-table-column align="center" label="操作时间" prop="updateTime"></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <div class="function-icon">
            <el-tooltip
              class="item"
              effect="dark"
              content="置顶"
              placement="bottom"
              v-permit="'/comment/top'"
            >
              <img @click="stick(scope.row)" src="../../assets/iconfont/stick.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="审核"
              placement="bottom"
              v-permit="'/comment/audit'"
            >
              <img @click="remark(scope.row)" src="../../assets/iconfont/remark.png" alt />
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
      title="审核"
      width="400px"
      :visible.sync="dialogFormVisible"
      @close="addClose"
      :close-on-click-modal="false"
    >
      <el-form :model="form" :status-icon="true" hide-required-asterisk>
        <el-select v-model="forms.audit" placeholder="请选择" style="width: 100%">
          <el-option
            v-for="item in auditflags"
            :key="item.auditflag"
            :label="item.label"
            :value="item.auditflag"
          ></el-option>
        </el-select>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addClose">取 消</el-button>
        <el-button type="primary" @click="sure" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  queryComment,
  updateLowerLine,
  updateSeq,
  dataManipulation,
  updateAudit
} from "../../api/comment.js";
import { queryBlog } from "../../api/blog.js";
export default {
  data() {
    return {
      forms: {
        audit: ""
      },
      isDisable: false,
      auditId: "",
      dialogFormVisible: false,
      isFlag: true,
      currentPage: 1,
      total: 0,
      loading: false,
      seqStatus: {
        seq: "1",
        blogId: ""
      },
      lineStatus: {
        line: ""
      },
      recommendFlags: [
        {
          recommendFlag: "",
          label: "所有"
        },
        {
          recommendFlag: "0",
          label: "置顶"
        },
        {
          recommendFlag: "1",
          label: "不置顶"
        }
      ],
      audits: [
        {
          audit: "",
          label: "所有"
        },
        {
          audit: "0",
          label: "待审核"
        },
        {
          audit: "1",
          label: "审核通过"
        },
        {
          audit: "2",
          label: "审核不通过"
        }
      ],
      auditflags: [
        {
          auditflag: "1",
          label: "审核通过"
        },
        {
          auditflag: "2",
          label: "审核不通过"
        }
      ],
      value: "",
      titleCns: [],
      form: {
        language: "",
        roofPlacement: "",
        audit: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: "",
        titleId: ""
      },
      forms: {
        language: "",
        roofPlacement: "",
        audit: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: "",
        titleId: ""
      },
      forms1: {
        language: "",
        line: "",
        titleCn: "",
        recommendFlag: "",
        pageNo: 0,
        pageSize: 0,
        sortField: "",
        sortRules: ""
      },
      value1: true,
      tableData: [
        {
          name: "123",
          remark: "123"
        }
      ]
    };
  },
  created() {},
  mounted() {
    this.getList();
  },
  methods: {
    //新增关闭按钮
    addClose() {
      this.dialogFormVisible = false;
    },
    sure() {
      if (this.forms.audit == null || this.forms.audit == "") {
        this.msgTip("warning", "审核标识不能为空");
        return;
      }
      this.isDisable = true;
      updateAudit(this.forms, this.auditId).then(res => {
        if (res.success) {
          this.msgTip("success", "操作成功");
          // 前端刷新
          dataManipulation(
            this.tableData,
            this.auditId,
            "",
            this.forms.audit,
            "",
            getDate(),
            window.localStorage.getItem("username")
          );
          this.dialogFormVisible = false;
          this.isFlag = true;
        } else {
          this.$message({
            type: "warning",
            message: res.errorMsg,
            center: true
          });
          this.dialogFormVisible = false;
          this.isDisable = false;
        }
      });
    },
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    getList() {
      this.loading = true;
      setTimeout(() => {
        queryComment(this.forms).then(res => {
          this.tableData = res.voList;
          this.total = Number(res.total);
          this.loading = false;
        });
        queryBlog(this.forms1).then(res => {
          this.titleCns = res.voList;
        });
      }, 500);
    }, //查询
    search() {
      this.loading = true;
      this.forms.pageNo = 1;
      this.currentPage = 1;
      this.forms.titleId = this.form.titleId;
      this.forms.audit = this.form.audit;
      this.forms.recommendFlag = this.form.recommendFlag;
      this.forms.line = this.form.line;
      setTimeout(() => {
        queryComment({
          line: this.form.line,
          pageNo: 1,
          pageSize: 10,
          sortField: "",
          language: "",
          sortRules: "",
          roofPlacement: this.form.roofPlacement,
          audit: this.form.audit,
          titleId: this.form.titleId
        }).then(res => {
          this.form.pageNo = 1;
          this.tableData = res.voList;
          this.total = res.total;
        });
        this.currentPage = 1;
        this.loading = false;
      }, 500);
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.forms.pageNo = currentPage;
      this.getList();
    },
    empty() {
      this.form.line = "";
      this.form.audit = "";
      this.form.roofPlacement = "";
      this.form.titleId = "";
      this.forms.line = "";
      this.forms.audit = "";
      this.forms.roofPlacement = "";
      this.forms.titleId = "";
      this.forms.pageNo = 1;
      this.getList();
    },
    roofPlacementFormatter(row, column) {
      if (row.roofPlacement == "0") {
        return "置顶";
      } else if (row.roofPlacement == "1") {
        return "不置顶";
      }
    },
    auditFormatter(row, column) {
      if (row.audit == "0") {
        return "待审核";
      } else if (row.audit == "1") {
        return "审核通过";
      } else if (row.audit == "2") {
        return "审核不通过";
      }
    },
    //审核
    remark(v) {
      this.auditId = v.id;
      this.isDisable = false;
      this.dialogFormVisible = true;
    },
    //置顶
    stick(v) {
      if (v.audit == 2) {
        this.msgTip("warning", "审核未通过不能置顶");
        return;
      }
      if (v.audit == 0) {
        this.msgTip("warning", "未审核不能置顶");
        return;
      }
      if (this.isFlag) {
        this.isFlag = false;
        this.seqStatus.blogId = v.blogId;
        updateSeq(this.seqStatus, v.id).then(res => {
          if (res.success) {
            this.msgTip("success", "置顶成功");
            this.empty();
            this.isFlag = true;
          } else {
            this.$message({
              type: "warning",
              message: res.errorMsg,
              center: true
            });
            this.isFlag = true;
          }
        });
      }
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

<style scoped lang='scss'>
.page {
  text-align: center;
  margin-top: 20px;
}
.head {
  margin: 20px 0 0 20px;
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

