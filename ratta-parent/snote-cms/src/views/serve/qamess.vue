<template>
  <div class="qamess">
    <div class="head">
      <ol>
        <li>
          <h5>类型</h5>
          <el-select v-model="table.type" placeholder="请选择" @change="handleChangeFirst">
            <el-option
              v-for="(item,index) in types"
              :key="index"
              :label="item.name"
              :value="item.type"
            ></el-option>
          </el-select>
        </li>
        <li v-if="table.type !== ''">
          <h5>标题</h5>
          <el-select v-model="table.faqTitleId" placeholder="请选择">
            <el-option
              v-for="(item,index) in tit"
              :key="index"
              :label="item.titleCn"
              :value="item.id"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>上/下线</h5>
          <el-select v-model="table.line" placeholder="请选择">
            <el-option
              v-for="(item,index) in option"
              :key="index"
              :label="item.name"
              :value="item.type"
            ></el-option>
          </el-select>
        </li>
        <li>
          <el-button
            type="primary"
            style="margin-left:20px"
            @click="search"
            @keyup.enter.native="search"
          >查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
        <li>
          <el-button type="primary" @click="add" v-permit="'/faq/item/add'">新增</el-button>
        </li>
      </ol>
    </div>
    <el-table :data="tableData" style="width: 100%;margin-top:30px;" border v-loading="loading">
      <el-table-column align="center" label="序号" prop="seq">
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
          <p v-show="isShow != scope.row.id" @click="changeSeq(scope.row)">{{scope.row.seq}}</p>
        </template>
      </el-table-column>
      <el-table-column align="center" label="类型" prop="type" :formatter="stateFormat"></el-table-column>
      <el-table-column align="center" label="标题CN" prop="titleCn"></el-table-column>
      <el-table-column align="center" label="问题CN" prop="questionCn"></el-table-column>
      <!-- <el-table-column align="center" label="问题EN" prop="questionEn"></el-table-column> -->
      <el-table-column align="center" label="答案CN" show-overflow-tooltip prop="answerCn"></el-table-column>
      <!-- <el-table-column
        align="center"
        label="答案EN"
        show-overflow-tooltip
        width="150"
        prop="answerEn"
      ></el-table-column>-->
      <!-- <el-table-column align="center" label="SEO描述CN" width="170" prop="seoDescriptionCn"></el-table-column>
      <el-table-column align="center" label="SEO描述EN" width="170" prop="seoDescriptionEn"></el-table-column>
      <el-table-column align="center" label="SEO关键字CN" width="170" prop="seoKeywordCn"></el-table-column>
      <el-table-column align="center" label="SEO关键字EN" width="170" prop="seoKeywordEn"></el-table-column>-->
      <el-table-column align="center" label="上线/下线" prop="line" :formatter="state"></el-table-column>
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
              v-permit="'/faq/item/update'"
            >
              <img @click="change(scope.row.id)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="上线"
              placement="bottom"
              v-permit="'/faq/item/online'"
            >
              <img @click="on(scope.row)" src="../../assets/iconfont/online.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="下线"
              placement="bottom"
              v-permit="'/faq/item/unline'"
            >
              <img @click="off(scope.row)" src="../../assets/iconfont/offline.png" alt />
            </el-tooltip>
             <el-tooltip
              class="item"
              effect="dark"
              content="预览"
              placement="bottom"
            >
              <img @click="preview(scope.row)" src="../../assets/iconfont/view.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除"
              placement="bottom"
              v-permit="'/faq/item/delete'"
            >
              <img @click="dele(scope.row.id)" src="../../assets/iconfont/delete.png" alt />
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
  getLists,
  addList,
  changeOn,
  changeSeq,
  aloneFaq,
  changeFaq,
  delefaq
} from "../../api/faqmess.js";
import { faqType } from "../../api/faq.js";
import { stopClick } from "../../utils/index.js";
export default {
  data() {
    return {
      tableData: [],
      table: {
        faqTitleId: "",
        line: "",
        type: "",
        pageNo: 1,
        pageSize: 10
      },
      tables: {
        faqTitleId: "",
        line: "",
        type: "",
        pageNo: 1,
        pageSize: 10
      },
      types: [
        {
          type: 0,
          name: "FAQ"
        },
        {
          type: 1,
          name: "故障排除"
        }
      ],
      tit: [],
      option: [
        {
           type: 0,
          name: "上线"
        },
        {
          type: 1,
          name: "预览"
        },
        {
          type: 2,
          name: "下线"
        }
      ],
      total: 0,
      currentPage: 1,
      loading: false,
      options: [],
      flag: false,
      isShow: false
    };
  },
  ceated() {},
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
    handleClick(tab, event) {},
    //列表
    getList() {
      this.loading = true;
      setTimeout(() => {
        getLists(this.tables).then(res => {
          if (res.voList == null || res.voList == "") {
            this.tables.pageNo = 1;
            this.currentPage = 1;
            getLists(this.tables).then(res => {
              this.tableData = res.voList;
              this.total = res.total;
              this.loading = false;
            });
          }
          this.tableData = res.voList;
          this.total = res.total;
          this.loading = false;
        });
      }, 500);
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.tables.pageNo = currentPage;
      this.getList();
    },
    //判断类型
    stateFormat(row) {
      if (row.type === "0") {
        return "FAQ";
      } else {
        return "故障排除";
      }
    },
    //判断上下线
    state(row) {
      if (row.line === "0") {
        return "上线";
      } else if(row.line === '1') {
        return "预览";
      } else if(row.line === '2'){
        return "下线";
      }
    },
    //下拉改变出现标题
    handleChangeFirst(value) {
      this.table.faqTitleId = "";
      faqType(this.table.type).then(res => {
        if (res.success) {
          this.tit = res.voList;
          console.log(res.voList);
        }
      });
    },
    //搜索
    search() {
      this.tables.pageNo = 1;
      this.currentPage = 1;
      this.tables.line = this.table.line;
      this.tables.type = this.table.type;
      this.tables.faqTitleId = this.table.faqTitleId;
      this.getList();
    },
    //清空
    empty() {
      this.table.type = "";
      this.currentPage = 1;
      this.table.titleCn = "";
      this.table.line = "";
      this.tables.type = "";
      this.tables.pageNo = 1;
      this.tables.faqTitleId = "";
      this.tables.line = "";
      this.getList();
    },
    //新增
    add() {
      this.$router.push({ path: "addqamess" });
    },
    //修改
    change(id) {
      this.$router.push({ path: "changeqamess", query: { id: id } });
    },
    //修改序号
    changeSeq(e) {
      const url = stopClick("/faq/item/seq");
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
        changeSeq({ seq: val.seq }, val.id).then(res => {
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
    //弹框封装
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //上线
    on(row) {
      if (row.line == "0") {
        this.msgTip("warning", "该数据已上线");
      } else {
        changeOn({ line: 0 }, row.id).then(res => {
          if (res.success) {
            this.msgTip("success", "上线成功");
            this.getList();
          } else {
            this.msgTip("error", res.errorMsg);
          }
        });
      }
    },
    //预览
    preview(row){
      if(row.line == '1'){
        this.msgTip("warning", "该数据已预览");
      } else{
        changeOn({ line: 1 }, row.id).then(res => {
          if (res.success) {
            this.msgTip("success", "操作成功");
            this.getList();
          } else {
            this.msgTip("error", res.errorMsg);
          }
        });
      }
    },
    //下
    off(row) {
      if (row.line == "2") {
        this.msgTip("warning", "该数据已下线");
      } else {
        changeOn({ line: 2 }, row.id).then(res => {
          if (res.success) {
            this.msgTip("success", "下线成功");
            this.getList();
          } else {
            this.msgTip("error", res.errorMsg);
          }
        });
      }
    },

    //删除
    dele(id) {
      this.$confirm("您是否要删除当前选中的记录?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        delefaq(id).then(res => {
          if (res.success) {
            this.msgTip("success", "删除成功");
            this.getList();
          } else {
            this.msgTip("error", res.errorMsg);
          }
        });
      });
    }
  }
};
</script>

<style scoped lang='scss'>
.qamess {
  .head {
    ol {
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
}
</style>
<style>
.qamess .el-tabs {
  padding-left: 20px;
  margin-top: 20px;
}
</style>

