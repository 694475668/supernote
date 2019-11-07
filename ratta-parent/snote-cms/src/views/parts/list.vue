<template>
  <div class="list">
    <div class="head">
      <ol>
        <li>
          <h5>系列编号</h5>
          <el-select v-model="form.serialNumber" placeholder="请选择">
            <el-option
              v-for="(item,index) in serial"
              :key="index"
              :label="item.value"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
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
          <h5>系列类型</h5>
          <el-select v-model="form.type" placeholder="请选择">
            <el-option
              v-for="(item,index) in type"
              :key="index"
              :label="item.name"
              :value="item.type"
            ></el-option>
          </el-select>
        </li>
        <li>
          <el-button type="primary" @click="search" @keyup.enter.native="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
        <li>
          <el-button type="primary" @click="add" v-permit="'/part/series/add'">新增</el-button>
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
      <el-table-column align="center" label="系列编号" prop="serialNumber"></el-table-column>
      <el-table-column align="center" label="配件系列名称CN" prop="nameCn"></el-table-column>
      <el-table-column align="center" label="系列类型" prop="type" :formatter="stateFormat"></el-table-column>
      <el-table-column align="center" label="按钮名称CN" prop="buttonNameCn"></el-table-column>
      <el-table-column align="center" label="按钮名称EN" prop="buttonNameEn"></el-table-column>
      <el-table-column align="center" label="首页系列描述CN" show-overflow-tooltip prop="homeDescriptionCn"></el-table-column>
      <el-table-column align="center" label="配件系列描述CN" show-overflow-tooltip width="200" prop="descriptionCn"></el-table-column>
      <el-table-column align="center" label="价格CN" prop="priceCn"></el-table-column>
      <el-table-column align="center" label="价格EN" prop="priceEn"></el-table-column>
      <el-table-column align="center" label="状态" prop="line" :formatter="state"></el-table-column>
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
              v-permit="'/part/series/update'"
            >
              <img @click="change(scope.row.id)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="上线"
              placement="bottom"
              v-permit="'/part/series/online'"
            >
              <img @click="online(scope.row)" src="../../assets/iconfont/online.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="下线"
              placement="bottom"
              v-permit="'/part/series/unline'"
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
              v-permit="'/part/series/delete'"
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
  searchSeq,
  changePart,
  delePart,
  addList,
  changeSeq
} from "../../api/partlist.js";
import { searchDiclist } from "../../api/dictionary.js";
import { stopClick } from "../../utils/index.js";
export default {
  data() {
    return {
      tableData: [],
      form: {
        line: "",
        pageNo: 1,
        pageSize: 10,
        serialNumber: "",
        type: ""
      },
      forms: {
        line: "",
        pageNo: 1,
        pageSize: 10,
        serialNumber: "",
        type: ""
      },
      seq: "",
      options: [
        {
            name:'上线',
            type:'0'
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
      type: [
        {
          name: "笔",
          type: "0"
        },
        {
          name: "笔芯",
          type: "1"
        },
        {
          name: "封套",
          type: "2"
        }
      ],
      total: 0,
      currentPage: 1,
      loading: false,
      isShow: false,
      serial: [],
      newList: [],
      proList: []
    };
  },
  created() {},
  mounted() {
    this.getList();
    searchDiclist({
      name: "",
      pageNo: 1,
      pageSize: 100,
      sortField: "",
      sortRules: "",
      valueMeaning: ""
    }).then(res => {
      if (res.success) {
        var b = {};
        res.voList.forEach(function(obj) {
          var array = b[obj["name"]] || [];
          array.push(obj);
          b[obj["name"]] = array;
        });
        this.newList = b["PART_SERIES"].map(v => {
          return {
            ...v,
            value: v.value + "_" + v.valueCn
          };
        });
        this.serial = this.newList;
      }
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
    //列表
    getList() {
      this.loading = true;
      setTimeout(() => {
        this.form.serialNumber = this.form.serialNumber.split("_")[0];
        this.forms.serialNumber = this.form.serialNumber;
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
        return "笔";
      } else if (row.type === "1") {
        return "笔芯";
      } else {
        return "封套";
      }
    },
    //判断上下线
    state(row) {
      if (row.line === "0") {
        return "上线";
      } else if (row.line === "1") {
        return "预览";
      } else {
        return "下线";
      }
    },
    //搜索
    search() {
      this.forms.pageNo = 1;
      this.currentPage = 1;
      this.forms.line = this.form.line;
      this.forms.serialNumber = this.form.serialNumber;
      this.forms.type = this.form.type;
      this.getList();
    },
    //清空
    empty() {
      this.form.serialNumber = "";
      this.currentPage = 1;
      this.form.line = "";
      this.form.type = "";
      this.forms.serialNumber = "";
      this.forms.line = "";
      this.forms.type = "";
      this.forms.pageNo = 1;
      this.getList();
    },
    //修改序号
    changeSeq(e) {
      const url = stopClick("/part/series/seq");
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
    //新增
    add() {
      this.$router.push({ path: "addlist" });
    },
    //修改
    change(id) {
      this.$router.push({ path: "changelist", query: { id: id } });
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
    preview(row) {
      if (row.line == "1") {
        this.msgTip("warning", "该数据已预览");
      } else {
        changeOn(
          {
            line: 1
          },
          row.id
        ).then(res => {
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
        delePart(id).then(res => {
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

<style>
</style>
