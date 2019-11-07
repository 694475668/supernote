<template>
  <div class="detail">
    <div class="head">
      <ol>
        <li>
          <h5>类型</h5>
          <el-select v-model="form.type" placeholder="请选择">
            <el-option
              v-for="(item,index) in option"
              :key="index"
              :label="item.name"
              :value="item.type"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>上线/下线</h5>
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
        <li v-permit="'/firmware/manual/add'">
          <el-button type="primary" @click="add">新增</el-button>
        </li>
      </ol>
    </div>
    <el-table :data="tableData" style="width: 100%;margin-top:30px;" border v-loading="loading">
      <el-table-column align="center" label="序号" show-overflow-tooltip>
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
      <el-table-column align="center" label="类型" :formatter="stateFormat" prop="type"></el-table-column>
      <el-table-column align="center" label="标题CN" prop="titleCn"></el-table-column>
      <el-table-column
        align="center"
        label="描述CN"
        width="200"
        show-overflow-tooltip
        prop="descriptionCn"
      ></el-table-column>
      <el-table-column align="center" label="上线/下线" :formatter="state" prop="line"></el-table-column>
      <el-table-column align="center" label="版本号CN" width="200" show-overflow-tooltip prop="versionCn"></el-table-column>
      <el-table-column align="center" label="版本号EN" width="200" show-overflow-tooltip prop="versionEn"></el-table-column>
      <el-table-column align="center" label="文件大小CN" width="170" prop="sizeCn"></el-table-column>
      <el-table-column align="center" label="文件大小EN" width="170" prop="sizeEn"></el-table-column>
      <el-table-column align="center" label="操作人" prop="updateUser"></el-table-column>
      <el-table-column align="center" label="操作时间" width="170" prop="updateTime"></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <div class="function-icon">
            <el-tooltip
              class="item"
              effect="dark"
              content="修改"
              placement="bottom"
              v-permit="'/firmware/manual/update'"
            >
              <img @click="change(scope.row.id)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="上线"
              placement="bottom"
              v-permit="'/firmware/manual/online'"
            >
              <img @click="on(scope.row)" src="../../assets/iconfont/online.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="下线"
              placement="bottom"
              v-permit="'/firmware/manual/unline'"
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
              v-permit="'/firmware/manual/delete'"
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
  search,
  changeSeq,
  aloneMess,
  changeMess,
  deleMess
} from "../../api/detail.js";
export default {
  data() {
    return {
      tableData: [],
      activeName: "first",
      loading: false,
      isShow: false,
      form: {
        line: "",
        type: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: ""
      },
      forms: {
        line: "",
        type: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: ""
      },
      option: [
        {
          name: "说明书",
          type: "0"
        },
        {
          name: "固件",
          type: "1"
        }
      ],
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
      total: 0,
      currentPage: 1
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
    handleClick(tab, event) {
      console.log(tab, event);
    },

    //列表
    //请求列表数据
    getList() {
      this.loading = true;
      setTimeout(() => {
        getLists(this.forms).then(res => {
          if (res.voList == null || res.voList == "") {
            this.forms.pageNo = 1;
            this.currentPage = 1;
            getLists(this.forms).then(res => {
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
      this.forms.pageNo = currentPage;
      this.getList();
    },
    //判断类型
    stateFormat(row) {
      if (row.type === "0") {
        return "说明书";
      } else {
        return "固件";
      }
    },
    //判断上下线
    state(row) {
      if (row.line === "0") {
        return "上线";
      }else if(row.line === '1'){
        return "预览";
      } else if(row.line === '2') {
        return "下线";
      }
    },
    //修改序号
    changeSeq(e) {
      this.isShow = e.id;
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
    //搜索
    search() {
      this.currentPage = 1;
      this.forms.pageNo = 1;
      this.forms.type = this.form.type;
      this.forms.line = this.form.line;
      this.getList();
    },
    //清空
    empty() {
      this.forms.type = "";
      this.currentPage = 1;
      this.form.type = "";
      this.forms.pageNo = 1;
      this.form.line = "";
      this.forms.line = "";
      this.getList();
    },
    //新增
    add() {
      this.$router.push({ path: "adddetail" });
    },
    //修改
    change(id) {
      this.$router.push({ path: "changedetail", query: { id: id } });
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
    //弹框封装
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
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
        deleMess(id).then(res => {
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

<style scoped>
</style>
<style>
.detail .el-tabs {
  padding-left: 20px;
  margin-top: 20px;
}
</style>

