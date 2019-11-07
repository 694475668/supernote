<template>
  <div class="summarize">
    <div class="head">
      <ol>
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
          <h5>产品型号</h5>
          <el-select v-model="form.type" placeholder="请选择">
            <el-option
              v-for="(item,index) in type"
              :key="index"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <el-button type="primary" @click="search" @keyup.enter.native="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
        <li v-permit="'/product/description/add'">
          <el-button type="primary" @click="add">新增</el-button>
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
            ref="ipt"
            :autofocus="true"
            v-focus
            maxlength="9"
          ></el-input>
          <p v-show="isShow != scope.row.id" @click="changeSeq(scope.row)">{{scope.row.seq}}</p>
        </template>
      </el-table-column>
      <el-table-column align="center" label="产品型号" prop="type"></el-table-column>
      <el-table-column align="center" label="上线/下线" :formatter="state" prop="line"></el-table-column>
      <el-table-column align="center" label="详情标题CN" prop="titleCn"></el-table-column>
      <!-- <el-table-column align="center" label="详情标题EN" width="120" prop="titleEn"></el-table-column> -->
      <el-table-column align="center" label="pc描述CN" show-overflow-tooltip prop="descriptionPcCn"></el-table-column>
      <!-- <el-table-column
        align="center"
        label="pc描述EN"
        width="120"
        show-overflow-tooltip
        prop="descriptionPcEn"
      ></el-table-column>-->
      <el-table-column
        align="center"
        label="移动描述CN"
        show-overflow-tooltip
        prop="descriptionMobileCn"
      ></el-table-column>
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
              v-permit="'/product/description/update'"
            >
              <img @click="change(scope.row.id)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="上线"
              placement="bottom"
              v-permit="'/product/description/online'"
            >
              <img @click="online(scope.row)" src="../../assets/iconfont/online.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="下线"
              placement="bottom"
              v-permit="'/product/description/unline'"
            >
              <img @click="offline(scope.row)" src="../../assets/iconfont/offline.png" alt />
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
              v-permit="'/product/description/delete'"
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
  changeOn,
  changeSeq,
  deleDescription
} from "../../api/productMess.js";
import { searchDiclist } from "../../api/dictionary.js";
import { stopClick } from "../../utils/index.js";
export default {
  data() {
    return {
      tableData: [],
      loading: false,
      form: {
        id: "",
        line: "",
        pageNo: 1,
        pageSize: 10,
        type: ""
      },
      forms: {
        id: "",
        line: "",
        pageNo: 1,
        pageSize: 10,
        type: ""
      },
      type: [],
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
      currentPage: 1,
      isShow: false,
      isFocus: true
    };
  },
  created() {},
  mounted() {
    this.getList();
    searchDiclist({
      name: "PRODUCT_TYPE",
      pageNo: 1,
      pageSize: 100,
      sortField: "",
      sortRules: "",
      valueMeaning: ""
    }).then(res => {
      this.type = res.voList;
    });
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
    //弹框封装
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //修改序号
    changeSeq(e) {
      const url = stopClick("/product/description/seq");
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
    //请求列表数据
    getList() {
      this.loading = true;
      setTimeout(() => {
        getLists(this.forms).then(res => {
          if (res.voList == null || res.voList == "") {
            this.forms.pageNo = 1;
            this.currentPage = 1;
            getLists(this.forms).then(res => {
              this.tableData = res.voList.map(item => {
                item.isFocus = false;
                return item;
              });
              this.total = res.total;
              this.loading = false;
            });
          }
          this.tableData = res.voList.map(item => {
            item.isFocus = false;
            return item;
          });
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
    //查找
    search() {
      this.forms.pageNo = 1;
      this.currentPage = 1;
      this.forms.id = this.form.id;
      this.forms.type = this.form.type;
      this.forms.line = this.form.line;
      this.getList();
    },

    //清空
    empty() {
      this.forms.type = "";
      this.currentPage = 1;
      this.forms.line = "";
      this.forms.pageNo = 1;
      this.form.line = "";
      this.form.type = "";
      this.getList();
    },

    //新增
    add() {
      this.$router.push({ path: "addpro" });
    },
    //修改
    change(id) {
      this.$router.push({ path: "changepro", query: { id: id } });
    },
    //判断上下线
    state(row) {
      if (row.line === "0") {
        return "上线";
      } else if(row.line=='2') {
        return "下线";
      } else if(row.line == '1'){
         return "预览";
      }
    },
    //上线
    online(row) {
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
    //下线
    offline(row) {
      if (row.line == "2") {
        this.msgTip("warning", "该数据已下线");
      } else {
        changeOn(
          {
            line: 2
          },
          row.id
        ).then(res => {
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
        deleDescription(id).then(res => {
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
