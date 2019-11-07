<template>
  <div class="abouts">
    <div class="head">
      <ul>
        <li>
          <h5>状态</h5>
          <el-select v-model="form.line" placeholder="请选择">
            <el-option
              v-for="(item,index) in options"
              :key="index"
              :label="item.name"
              :value="item.type"
            ></el-option>
          </el-select>
        </li>
        <li>
          <el-button type="primary" @click="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
        <li v-permit="'/about/add'">
          <el-button type="primary" @click="add">新增</el-button>
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
        <el-table-column align="center" label="序号" prop="seq">
          <template slot-scope="scope">
            <el-input
              type="text"
              v-if="isShow == scope.row.id"
              v-model="scope.row.seq"
              maxlength="9"
              @blur="bindSeq(scope.row)"
              :autofocus="true"
              v-focus
            ></el-input>
            <p v-show="isShow != scope.row.id" @click="changeSeq(scope.row)">{{scope.row.seq}}</p>
          </template>
        </el-table-column>
        <el-table-column align="center" label="标题CN" prop="titleCn"></el-table-column>
        <!-- <el-table-column align="center" label="标题EN" prop="titleEn"></el-table-column> -->
        <el-table-column align="center" label="状态" :formatter="state" prop="line"></el-table-column>
        <el-table-column
          align="center"
          label="移动端描述CN"
          show-overflow-tooltip
          prop="descriptionMobileCn"
        ></el-table-column>
        <!-- <el-table-column
          align="center"
          label="移动端描述EN"
          width="150"
          show-overflow-tooltip
          prop="descriptionMobileEn"
        ></el-table-column>-->
        <el-table-column
          align="center"
          label="PC端描述CN"
          show-overflow-tooltip
          prop="descriptionPcCn"
        ></el-table-column>
        <!-- <el-table-column
          align="center"
          label="PC端描述EN"
          width="150"
          show-overflow-tooltip
          prop="descriptionPcEn"
        ></el-table-column>-->
        <!-- <el-table-column
          align="center"
          label="SEO关键字CN"
          width="150"
          show-overflow-tooltip
          prop="seoKeywordCn"
        ></el-table-column>
        <el-table-column
          align="center"
          label="SEO关键字EN"
          width="150"
          show-overflow-tooltip
          prop="seoKeywordEn"
        ></el-table-column>
        <el-table-column
          align="center"
          label="SEO描述CN"
          width="150"
          show-overflow-tooltip
          prop="seoDescriptionCn"
        ></el-table-column>
        <el-table-column
          align="center"
          label="SEO描述EN"
          width="150"
          show-overflow-tooltip
          prop="seoDescriptionEn"
        ></el-table-column>-->

        <el-table-column align="center" label="操作人" prop="updateUser"></el-table-column>
        <el-table-column align="center" label="操作时间" prop="updateTime"></el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <div class="function-icon">
              <el-tooltip
                class="item"
                effect="dark"
                content="修改"
                placement="bottom"
                v-permit="'/about/update'"
              >
                <img @click="change(scope.row.id)" src="../../assets/iconfont/amend.png" alt />
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="上线"
                placement="bottom"
                v-permit="'/about/online'"
              >
                <img @click="online(scope.row)" src="../../assets/iconfont/online.png" alt />
              </el-tooltip>
            <el-tooltip
                class="item"
                effect="dark"
                content="下线"
                placement="bottom"
                v-permit="'/about/unline'"
            >
               <img @click="offline(scope.row)" src="../../assets/iconfont/offline.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="预览"
              placement="bottom"
            >
              <img  @click="preview(scope.row)" src="../../assets/iconfont/view.png" alt />
            </el-tooltip>
            <el-tooltip
                class="item"
                effect="dark"
                content="删除"
                placement="bottom"
                v-permit="'/about/delete'"
              >
                <img @click="dele(scope.row.id)" src="../../assets/iconfont/delete.png" alt />
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
  </div>
</template>

<script>
import {
  queryAboutAll,
  updateAboutLine,
  updateSeq,
  deleAbouts
} from "../../api/abouts.js";
import { stopClick } from "../../utils/index.js";
export default {
  data() {
    return {
      value: "",
      show: "",
      currentPage: 1,
      total: 0,
      value1: true,
      tableData: [],
      options: [
        {
          name: "上线",
          type: "0"
        },
        {
          name: "预览",
          type: "1"
        },
        {
          name: "下线",
          type: "2"
        }
      ],
      form: {
        pageNo: 1,
        pageSize: 10,
        id: "",
        line: ""
      },
      forms: {
        pageNo: 1,
        pageSize: 10,
        id: "",
        line: ""
      },
      lineForm: {
        line: ""
      },
      isShow: false
    };
  },

  created() {
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
    //查询
    search() {
      this.currentPage = 1;
      this.forms.pageNo = 1;
      this.forms.line = this.form.line;
      this.getList();
    },
    //清空
    empty() {
      this.forms.line = "";
      this.currentPage = 1;
      this.form.line = "";
      this.forms.pageNo = 1;
      this.getList();
    },

    getList() {
      this.loading = true;
      queryAboutAll(this.forms).then(res => {
        if (res.voList == null || res.voList == "") {
          this.forms.pageNo = 1;
          this.currentPage = 1;
          queryAboutAll(this.forms).then(res => {
            this.tableData = res.voList;
            this.total = res.total;
            this.loading = false;
          });
        }
        this.tableData = res.voList;
        this.total = res.total;
        this.loading = false;
      });
    },
    //修改序号
    changeSeq(e) {
      const url = stopClick("/about/seq");
      if (url) {
        this.isShow = e.id;
      }
    },
    bindSeq(val) {
      if (val.seq == "") {
        this.msgTip("warning", "序号不能为空");
      } else if (!/(^[1-9]\d*$)/.test(val.seq)) {
        this.msgTip("warning", "序号只能为正整数");
      } else {
        updateSeq({ seq: val.seq }, val.id).then(res => {
          if (res.success) {
            this.msgTip("success", "修改成功");
            this.isShow = false;
            this.getList();
          } else {
            this.msgTip("error", res.errorMsg);
          }
        });
      }
    },
    //新增
    add() {
      this.$router.push({ path: "addabout" });
    },
    //修改
    change(id) {
      this.$router.push({ path: "changeabout", query: { id: id } });
    },
    //上线
    online(row) {
      if (row.line == "0" || row.line == 0) {
        this.msgTip("error", "该数据已上线");
        return;
      }
      this.$confirm("确定上线当前记录?", "上线", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        //上线
        this.lineForm.line = "0";
        updateAboutLine(this.lineForm, row.id).then(res => {
          if (res.success) {
            this.$message({
              type: "success",
              message: "上线成功",
              center: true
            });
            this.lineForm.line = "";
            this.getList();
          } else {
            //上线失败
            this.$message({
              type: "warning",
              message: "操作失败",
              center: true
            });
          }
        });
      });
    },
    //预览
    preview(row) {
      if (row.line == "1" || row.line == 1) {
        this.msgTip("error", "该数据已预览");
        return;
      }
      this.$confirm("确定预览当前记录?", "预览", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        //预览
        this.lineForm.line = "1";
        updateAboutLine(this.lineForm, row.id).then(res => {
          if (res.success) {
            this.$message({
              type: "success",
              message: "操作成功",
              center: true
            });
            this.lineForm.line = "";
            this.getList();
          } else {
            //下线失败
            this.$message({
              type: "warning",
              message: "操作失败",
              center: true
            });
          }
        });
      });
    },

    //下线 
    offline(row) {
      if (row.line == "2" || row.line == 2) {
        this.msgTip("error", "该数据已下线");
        return;
      }
      this.$confirm("确定下线当前记录?", "下线", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        //下线
        this.lineForm.line = "2";
        updateAboutLine(this.lineForm, row.id).then(res => {
          if (res.success) {
            this.$message({
              type: "success",
              message: "下线成功",
              center: true
            });
            this.lineForm.line = "";
            this.getList();
          } else {
            //下线失败
            this.$message({
              type: "warning",
              message: "操作失败",
              center: true
            });
          }
        });
      });
    },
    //删除
    dele(id) {
      this.$confirm("您是否要删除当前选中的记录?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        deleAbouts(id).then(res => {
          if (res.success) {
            this.msgTip("success", "删除成功");
            this.getList();
          } else {
            this.msgTip("error", res.errorMsg);
          }
        });
      });
    },
    //判断类型
    state(row) {
      if (row.line == "0") {
        return "上线";
      } else if (row.line == "1") {
        return "预览";
      } else {
        return "下线";
      }
    },

    //获取页码
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.forms.pageNo = currentPage;
      this.getList();
    },
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    }
  }
};
</script>

<style scoped lang='scss'>
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
