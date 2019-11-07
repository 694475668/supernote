<template>
  <div class="partsMess">
    <div class="headMess">
      <ul>
        <li>
          <h5>产品编号</h5>
          <el-select v-model="form.productNumber" placeholder="请选择" style="width:100%">
            <el-option
              v-for="(item,index) in product"
              :key="index"
              :label="item.value"
              :value="item.value"
            ></el-option>
          </el-select>
          <!-- <el-autocomplete
            v-model="form.productNumber"
            @select="e => handleSelect(e, 'productNumber')"
            :fetch-suggestions="(queryString, cb) => querySearch(queryString, cb, 'proList')"
            placeholder="请输入内容"
            style="width:100%"
          ></el-autocomplete>-->
        </li>
        <li>
          <h5>系列编号</h5>
          <el-select v-model="form.serialNumber" placeholder="请选择" style="width:100%">
            <el-option
              v-for="(item,index) in serial"
              :key="index"
              :label="item.value"
              :value="item.value"
            ></el-option>
          </el-select>
          <!-- <el-autocomplete
            v-model="form.serialNumber"
            @select="e => handleSelect(e, 'serialNumber')"
            :fetch-suggestions="(queryString, cb) => querySearch(queryString, cb, 'newList')"
            placeholder="请输入内容"
            style="width:100%"
          ></el-autocomplete>-->
        </li>
        <li>
          <h5>首页显示</h5>
          <el-select v-model="form.homeFlag" placeholder="请选择">
            <el-option
              v-for="(item,index) in homeFlag"
              :key="index"
              :label="item.name"
              :value="item.type"
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
          <h5>产品</h5>
          <el-select v-model="form.productType" placeholder="请选择">
            <el-option
              v-for="(item,index) in productType"
              :key="index"
              :label="item.valueCn"
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
        <li>
          <img src="../../assets/iconfont/add.png" alt />
          <span @click="add" v-permit="'/part/add'">新增</span>
        </li>
      </ol>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%;margin-top:30px;"
      border
      @cell-click="cell"
      v-loading="loading"
    >
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
      <el-table-column align="center" label="产品编号" prop="productNumber"></el-table-column>
      <el-table-column align="center" label="产品名称CN" prop="nameCn"></el-table-column>
      <!-- <el-table-column align="center" label="产品名称EN" width="150" prop="nameEn"></el-table-column> -->
      <el-table-column align="center" label="颜色名称CN" prop="colourNameCn"></el-table-column>
      <!-- <el-table-column align="center" label="颜色名称EN" width="150" prop="colourNameEn"></el-table-column> -->
      <el-table-column
        align="center"
        label="产品型号"
        width="200"
        show-overflow-tooltip
        prop="productType"
      ></el-table-column>
      <el-table-column align="center" label="上线/下线" prop="line" :formatter="state"></el-table-column>
      <el-table-column align="center" label="首页显示标志" :formatter="stateFormat" prop="homeFlag"></el-table-column>
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
              v-permit="'/part/update'"
            >
              <img @click="change(scope.row.id)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="上线"
              placement="bottom"
              v-permit="'/part/online'"
            >
              <img @click="on(scope.row)" src="../../assets/iconfont/online.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="下线"
              placement="bottom"
              v-permit="'/part/unline'"
            >
              <img @click="off(scope.row)" src="../../assets/iconfont/offline.png" alt />
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
              v-permit="'/part/delete'"
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
import { getLists, changeOn, delePart, changeSeq } from "../../api/part.js";
import { searchDiclist } from "../../api/dictionary.js";
import { stopClick } from "../../utils/index.js";
export default {
  data() {
    return {
      form: {
        homeFlag: "",
        line: "",
        pageNo: 1,
        pageSize: 10,
        productNumber: "",
        productType: "",
        serialNumber: ""
      },
      forms: {
        homeFlag: "",
        line: "",
        pageNo: 1,
        pageSize: 10,
        productNumber: "",
        productType: "",
        serialNumber: ""
      },
      tableData: [],
      homeFlag: [
        {
          name: "是",
          type: "0"
        },
        {
          name: "否",
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
      productType: [],
      loading: false,
      total: 0,
      currentPage: 1,
      isShow: false,
      newList: [],
      proList: [],
      product: [],
      serial: []
    };
  },
  created() {},
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
      this.productType = res.voList;
    });
    this.loadAll();
  },
  methods: {
    // //模糊搜索
    //   querySearch(queryString, cb, list){
    //     console.log(queryString, cb, list)
    //     this.loadAll();
    //     var restaurants = this[list];
    //     var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
    //     // 调用 callback 返回建议列表的数据
    //     cb(results);
    //   },
    //   createFilter(queryString) {
    //     return (restaurant) => {
    //       return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
    //     };
    //   },
    //   handleSelect(e, type){
    //     this.form[type] = e.value
    //   },
    loadAll() {
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
          this.proList = b["PART"].map(v => {
            return {
              ...v,
              value: v.value + "_" + v.valueCn
            };
          });
          this.product = this.proList;
        }
      });
    },
    //列表
    getList() {
      this.loading = true;
      setTimeout(() => {
        const form = { ...this.form };
        form.productNumber = form.productNumber.split("_")[0];
        form.serialNumber = form.serialNumber.split("_")[0];
        this.forms.productNumber = form.productNumber;
        this.forms.serialNumber = form.serialNumber;
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
      if (row.homeFlag === "0") {
        return "是";
      } else {
        return "否";
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
    //新增
    add() {
      this.$router.push({ path: "addmess" });
    },
    //查询
    search() {
      this.forms.pageNo = 1;
      this.currentPage = 1;
      this.forms.homeFlag = this.form.homeFlag;
      this.forms.productNumber = this.form.productNumber;
      this.forms.productType = this.form.productType;
      this.forms.homeFlag = this.form.homeFlag;
      this.forms.serialNumber = this.form.serialNumber;
      this.forms.line = this.form.line;
      this.getList();
    },
    //清空
    empty() {
      this.form.homeFlag = "";
      this.currentPage = 1;
      this.form.line = "";
      this.form.productNumber = "";
      this.form.productType = "";
      this.form.serialNumber = "";
      this.forms.homeFlag = "";
      this.forms.line = "";
      this.forms.productNumber = "";
      this.forms.productType = "";
      this.forms.serialNumber = "";
      this.forms.pageNo = 1;
      this.getList();
    },
    //修改
    change(id) {
      this.$router.push({ path: "changemess", query: { id: id } });
    },
    //修改序号
    changeSeq(e) {
      const url = stopClick("/part/seq");
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
    //下线
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
    off(row) {
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
    },
    cell(row, column, cell, event) {
      // console.log(row,'1')
      // console.log(column,'2')
      // if(column.label =='序号'){
      //     row.name = '444'
      // }
      // console.log(cell,'3')
      // console.log(event,'4')
    }
  }
};
</script>

<style scoped lang='scss'>
.partsMess {
  .headMess {
    padding-right: 20px;
    ul {
      width: 100%;
      display: flex;
      padding-left: 29px;
      li {
        width: 20%;
        margin-left: 10px;
        &:nth-child(1) {
          margin-left: 0;
        }
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
        span {
          font-size: 14px;
        }
        img {
          width: 15px;
          vertical-align: middle;
          margin-right: 5px;
        }
      }
      li:hover {
        background: #ccc;
      }
    }
  }

  // .el-table .cell{
  //   padding:0!important;
  //   height:50px;
  //   line-height: 50px;
  // }
  // .el-table td, .el-table th{
  //   padding:0;
  //   height:50px;
  //   min-height:50px;
  //   line-height: 50px
  // }
  // .el-table .el-input{
  //   padding:5px 5px;
  // }

  // .el-table span{
  //   cursor: pointer;
  //   height:50px;
  //   line-height: 50px;
  // }
}
</style>