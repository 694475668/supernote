<template>
  <div class="bloglist">
    <div class="head">
      <ul>
        <li>
          <h5>上下线</h5>
          <el-select v-model="form.line" placeholder="请选择">
            <el-option
              v-for="item in lines"
              :key="item.line"
              :label="item.label"
              :value="item.line"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>推荐博客标识</h5>
          <el-select v-model="form.recommendFlag" placeholder="请选择">
            <el-option
              v-for="item in recommendFlags"
              :key="item.recommendFlag"
              :label="item.label"
              :value="item.recommendFlag"
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
        <li v-permit="'/blog/add'">
          <el-button type="primary" @click="add">新 增</el-button>
        </li>
      </ul>
    </div>
    <el-table :data="tableData" style="width: 100%;margin-top:30px;" border v-loading="loading">
      <el-table-column align="center" label="序号">
        <template slot-scope="scope">
          <el-input
            type="text"
            v-if="isShow == scope.row.id"
            v-model="scope.row.seq"
            @blur="bindSeq(scope.row)"
            :autofocus="true"
            v-focus
            maxlength="9"
          ></el-input>
          <p v-show="isShow  != scope.row.id" @click="changeSeq(scope.row)">{{scope.row.seq}}</p>
        </template>
      </el-table-column>
      <el-table-column align="center" label="标题" show-overflow-tooltip prop="title"></el-table-column>
      <el-table-column align="center" label="博客内容" show-overflow-tooltip prop="content"></el-table-column>
      <el-table-column align="center" label="作者" show-overflow-tooltip prop="author"></el-table-column>
      <el-table-column align="center" label="上下线标识" prop="line" :formatter="lineFormatter"></el-table-column>
      <el-table-column align="center" label="显示浏览数" prop="viewCount"></el-table-column>
      <el-table-column align="center" label="浏览数" prop="viewCountReal"></el-table-column>
      <el-table-column align="center" label="显示评论数" prop="commentsCount"></el-table-column>
      <el-table-column align="center" label="评论数" prop="commentsCountReal"></el-table-column>
      <el-table-column align="center" label="显示点赞数" prop="thumbsCount"></el-table-column>
      <el-table-column align="center" label="点赞数" prop="thumbsCountReal"></el-table-column>
      <el-table-column
        align="center"
        label="推荐标识"
        prop="recommendFlag"
        :formatter="recommendFlagformatter"
      ></el-table-column>
      <el-table-column align="center" label="博客版本" :formatter="languageFormatter" prop="language"></el-table-column>
      <el-table-column align="center" label="操作时间" width="170" prop="updateTime"></el-table-column>
      <el-table-column align="center" label="操作人" prop="updateUser"></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <div class="function-icon">
            <el-tooltip
              class="item"
              effect="dark"
              content="修改"
              placement="bottom"
              v-permit="'/blog/update'"
            >
              <img @click="change(scope.row)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>

            <el-tooltip
              class="item"
              effect="dark"
              content="上线"
              placement="bottom"
              v-permit="'/blog/online'"
            >
              <img @click="online(scope.row)" src="../../assets/iconfont/online.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="下线"
              placement="bottom"
              v-permit="'/blog/unline'"
            >
              <img @click="offline(scope.row)" src="../../assets/iconfont/offline.png" alt />
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="预览" placement="bottom">
              <img @click="preview(scope.row)" src="../../assets/iconfont/view.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除"
              placement="bottom"
              v-permit="'/blog/delete'"
            >
              <img @click="dele(scope.row)" src="../../assets/iconfont/delete.png" alt />
            </el-tooltip>
          </div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="推荐">
        <template slot-scope="scope">
          <div class="function-icon">
            <el-tooltip
              class="item"
              effect="dark"
              content="是否为推荐博客开关"
              placement="bottom"
              v-permit="'/blog/recommend'"
            >
              <el-switch
                v-if="scope.row['recommendFlag']=='Y'"
                v-model="scope.row.recommendFlagType"
                @change="recommendeTrue(scope.row)"
                active-color="#13ce66"
                inactive-color="#ff4949"
              ></el-switch>
              <el-switch
                v-if="scope.row['recommendFlag']=='N'"
                v-model="scope.row.recommendFlagType"
                @change="recommendeFalse(scope.row)"
                active-color="#13ce66"
                inactive-color="#ff4949"
              ></el-switch>
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
  </div>
</template>

<script>
import {
  queryBlog,
  addBlog,
  updateBlog,
  getBlogById,
  updateLowerLine,
  updateSeq,
  delBlog,
  dataManipulation,
  updateRecommend
} from "../../api/blog.js";
import { stopClick } from "../../utils/index.js";
export default {
  data() {
    return {
      isShow: false,
      formLabelWidth: "130px",
      isDisable: false,
      addApprove: {
        seq: ""
      },
      dialogFormVisible1: false,
      titleCns: [],
      lines: [
        {
          line: "0",
          label: "上线"
        },
        {
          line: "1",
          label: "预览"
        },
        {
          line: "2",
          label: "下线"
        }
      ],
      lineStatus: {
        line: ""
      },
      recommendFlags: [
        {
          recommendFlag: "",
          label: "所有"
        },
        {
          recommendFlag: "Y",
          label: "推荐"
        },
        {
          recommendFlag: "N",
          label: "不推荐"
        }
      ],
      isFlag: true,
      currentPage: 1,
      total: 0,
      loading: false,
      form: {
        language: "",
        line: "",
        titleId: "",
        recommendFlag: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: ""
      },
      form1: {
        language: "",
        line: "",
        titleId: "",
        recommendFlag: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: ""
      },
      forms: {
        language: "",
        line: "",
        titleCn: "",
        recommendFlag: "",
        pageNo: 0,
        pageSize: 0,
        sortField: "",
        sortRules: ""
      },
      recommendParam: {
        recommendFlag: "",
        id: ""
      },
      value: "",
      value1: true,
      value2: false,
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
    //修改关闭按钮
    changeClose() {
      this.dialogFormVisible1 = false;
    },
    //修改序号
    changeSeq(e) {
      const url = stopClick("/blog/seq");
      if (url) {
        this.isShow = e.id;
      }
    },
    bindSeq(val) {
      if (val.seq == "") {
        this.msgTip("warning", "序号不能为空");
        return;
      }
      if (!/^[1-9]{1,}[\d]*$/.test(val.seq)) {
        this.msgTip("warning", "序号只能为正整数");
        return;
      }
      this.addApprove.seq = val.seq;
      updateSeq(this.addApprove, val.id).then(res => {
        if (res.success) {
          this.msgTip("success", "修改成功");
          this.empty();
          this.isShow = false;
        } else {
          this.$message({
            type: "warning",
            message: res.errorMsg,
            center: true
          });
        }
      });
    },
    //判断上下线
    lineFormatter(row, column) {
      if (row.line == "0") {
        return "上线";
      } else if (row.line == "1") {
        return "预览";
      } else if (row.line == "2") {
        return "下线";
      }
    },
    languageFormatter(row, column) {
      if (row.language == "ZH-CN") {
        return "中文版";
      } else if (row.language == "EN") {
        return "英文版";
      }
    },
    //判断上下线
    recommendFlagformatter(row, column) {
      if (row.recommendFlag == "Y") {
        return "推荐";
      } else if (row.recommendFlag == "N") {
        return "不推荐";
      }
    },
    //删除
    dele(v) {
      this.$confirm("您是否要删除当前选中的记录?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        delBlog(v.id).then(res => {
          if (res.success) {
            this.msgTip("success", "操作成功");
            this.getList();
          } else {
            this.msgTip("warning", res.errorMsg);
          }
        });
      });
    },
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //下线
    offline(v) {
      if (v.line == 2) {
        this.msgTip("error", "已是下线状态");
        return;
      }
      if (this.isFlag) {
        this.isFlag = false;
        this.lineStatus.line = 2;
        updateLowerLine(this.lineStatus, v.id).then(res => {
          if (res.success) {
            this.msgTip("success", "操作成功");
            this.empty();
            // 前端刷新
            // dataManipulation(
            //   this.tableData,
            //   v.id,
            //   "2",
            //   getDate(),
            //   window.localStorage.getItem("username")
            // );
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
    },
    //预览
    preview(v) {
      if (this.isFlag) {
        this.isFlag = false;
        this.lineStatus.line = 1;
        updateLowerLine(this.lineStatus, v.id).then(res => {
          if (res.success) {
            this.msgTip("success", "操作成功");
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
    },
    empty() {
      this.form1.pageNo = 1;
      this.currentPage = 1;
      this.form.line = "";
      this.form.recommendFlag = "";
      this.form.titleId = "";
      this.form1.line = "";
      this.form1.recommendFlag = "";
      this.form1.titleId = "";
      this.getList();
    },
    getList() {
      this.loading = true;
      setTimeout(() => {
        queryBlog(this.form1).then(res => {
          if (res.voList == null || res.voList == "") {
            this.form1.pageNo = 1;
            this.currentPage = 1;
            queryBlog(this.form1).then(res => {
              this.tableData = res.voList;
              this.tableData.map(element => {
                if (element.recommendFlag == "Y") {
                  this.$set(element, "recommendFlagType", true);
                } else if (element.recommendFlag == "N") {
                  this.$set(element, "recommendFlagType", false);
                }
              });
              this.total = Number(res.total);
              this.loading = false;
            });
          }
          this.tableData = res.voList;
          this.tableData.map(element => {
            if (element.recommendFlag == "Y") {
              this.$set(element, "recommendFlagType", true);
            } else if (element.recommendFlag == "N") {
              this.$set(element, "recommendFlagType", false);
            }
          });
          this.total = Number(res.total);
          this.loading = false;
        });
        queryBlog(this.forms).then(res => {
          this.titleCns = res.voList;
        });
      }, 500);
    },
    //上线
    online(v) {
      if (v.line == 0) {
        this.msgTip("error", "已是上线状态");
        return;
      }
      if (this.isFlag) {
        this.isFlag = false;
        this.lineStatus.line = 0;
        updateLowerLine(this.lineStatus, v.id).then(res => {
          if (res.success) {
            this.msgTip("success", "操作成功");
            this.empty();
            // 前端刷新
            // dataManipulation(
            //   this.tableData,
            //   v.id,
            //   "0",
            //   getDate(),
            //   window.localStorage.getItem("username")
            // );
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
    },
    //查询
    search() {
      this.loading = true;
      this.currentPage = 1;
      this.form1.pageNo = 1;
      this.form1.line = this.form.line;
      this.form1.titleId = this.form.titleId;
      setTimeout(() => {
        queryBlog({
          line: this.form.line,
          pageNo: 1,
          pageSize: 10,
          sortField: "",
          language: "",
          sortRules: "",
          titleId: this.form.titleId,
          recommendFlag: this.form.recommendFlag
        }).then(res => {
          this.form.pageNo = 1;
          this.tableData = res.voList;
          this.tableData.map(element => {
            if (element.recommendFlag == "Y") {
              this.$set(element, "recommendFlagType", true);
            } else if (element.recommendFlag == "N") {
              this.$set(element, "recommendFlagType", false);
            }
          });
          this.total = res.total;
        });
        this.currentPage = 1;
        this.loading = false;
      }, 500);
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.form1.pageNo = currentPage;
      this.getList();
    },
    recommendeTrue(v) {
      this.recommendParam.recommendFlag = "N";
      this.recommendParam.id = v.id;
      updateRecommend(this.recommendParam).then(res => {
        if (res.success) {
          this.getList();
          this.msgTip("success", "操作成功");
        } else {
          this.$message({
            type: "warning",
            message: res.errorMsg,
            center: true
          });
        }
      });
    },
    recommendeFalse(v) {
      this.recommendParam.recommendFlag = "Y";
      this.recommendParam.id = v.id;
      updateRecommend(this.recommendParam).then(res => {
        if (res.success) {
          this.getList();
          this.msgTip("success", "操作成功");
        } else {
          this.$message({
            type: "warning",
            message: res.errorMsg,
            center: true
          });
        }
      });
    },
    //新增
    add() {
      this.$router.push({ path: "addblog" });
    },
    //修改
    change(v) {
      this.$router.push({
        path: "changeblog",
        query: {
          id: v.id
        }
      });
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

