<template>
  <div class="faq">
    <div class="head">
      <ol>
        <li>
          <h5>上/下线</h5>
          <el-select v-model="form.line" placeholder="请选择">
            <el-option
              v-for="(item,index) in option"
              :key="index"
              :label="item.name"
              :value="item.type"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>类型</h5>
          <el-select v-model="form.type" placeholder="请选择">
            <el-option
              v-for="(item,index) in types"
              :key="index"
              :label="item.name"
              :value="item.value"
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
          <el-button type="primary" @click="add" v-permit="'/faq/title/add'">新增</el-button>
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
              v-permit="'/faq/title/update'"
            >
              <img @click="change(scope.row.id)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="上线"
              placement="bottom"
              v-permit="'/faq/title/online'"
            >
              <img @click="online(scope.row)" src="../../assets/iconfont/online.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="下线"
              placement="bottom"
              v-permit="'/faq/title/unline'"
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
              v-permit="'/faq/title/delete'"
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

    <el-dialog
      title="新增"
      :visible.sync="dialogFormVisible"
      @close="addClose"
      :close-on-click-modal="false"
    >
      <el-form
        :model="addform"
        :rules="addRules"
        ref="addform"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="序号" :label-width="formLabelWidth">
          <el-input v-model="addform.seq" autocomplete="off" maxlength="9"></el-input>
        </el-form-item>
        <el-form-item label="类型" :label-width="formLabelWidth" prop="type">
          <el-select v-model="addform.type" placeholder="请选择" style="width:100%">
            <el-option
              v-for="(item,index) in types"
              :key="index"
              :label="item.name"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <div class="commont">
          <el-form-item label="标题CN" :label-width="formLabelWidth" prop="titleCn">
            <el-input v-model="addform.titleCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="标题EN" :label-width="formLabelWidth" prop="titleEn">
            <el-input v-model="addform.titleEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <!-- <div class="commont">
          <el-form-item label="SEO关键字CN" :label-width="formLabelWidth">
            <el-input v-model="addform.seoKeywordCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字EN" :label-width="formLabelWidth">
            <el-input v-model="addform.seoKeywordEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO描述CN" :label-width="formLabelWidth">
            <el-input
              v-model="addform.seoDescriptionCn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO描述EN" :label-width="formLabelWidth">
            <el-input
              v-model="addform.seoDescriptionEn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
        </div> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addClose">取 消</el-button>
        <el-button type="primary" @click="sure" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="修改"
      :visible.sync="dialogFormVisibles"
      @close="changeClose"
      :close-on-click-modal="false"
    >
      <el-form
        :model="changeform"
        :rules="changeRules"
        ref="changeform"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="类型" :label-width="formLabelWidth" prop="type">
          <el-select v-model="changeform.type" disabled placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in types"
              :key="item.value"
              :label="item.name"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <div class="commont">
          <el-form-item label="标题CN" :label-width="formLabelWidth" prop="titleCn">
            <el-input v-model="changeform.titleCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="标题EN" :label-width="formLabelWidth" prop="titleEn">
            <el-input v-model="changeform.titleEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <!-- <div class="commont">
          <el-form-item label="SEO关键字CN" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.seoKeywordCn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字EN" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.seoKeywordEn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO描述CN" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.seoDescriptionCn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO描述EN" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.seoDescriptionEn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
        </div> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="changeClose">取 消</el-button>
        <el-button type="primary" @click="keep">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getLists,
  addList,
  changeList,
  changeOn,
  faqType,
  aloneTit,
  deleTit,
  changeSeq
} from "../../api/faq.js";
import { stopClick } from "../../utils/index.js";
export default {
  data() {
    return {
      value: "",
      tableData: [],
      tableData1: [],
      activeName: "first",
      formLabelWidth: "120px",
      dialogFormVisible: false,
      faq: [],
      dialogFormVisibles: false,
      loading: false,
      isDisable: false,
      flag: false,
      addform: {
        seq: "",
        line: "2",
        titleCn: "",
        titleEn: "",
        type: ""
      },
      addRules: {
        titleCn: [{ required: true, trigger: "blur" }],
        titleEn: [{ required: true, trigger: "blur" }],
        type: [{ required: true, trigger: "blur" }]
      },
      changeform: {
        seq: "",
        line: "2",
        titleCn: "",
        titleEn: "",
        type: ""
      },
      changeRules: {
        titleCn: [{ required: true, trigger: "blur" }],
        titleEn: [{ required: true, trigger: "blur" }],
        type: [{ required: true, trigger: "blur" }]
      },
      changeid: "",
      form: {
        line: "",
        type: "",
        pageNo: 1,
        pageSize: 10
      },
      forms: {
        line: "",
        type: "",
        pageNo: 1,
        pageSize: 10
      },
      types: [
        {
          value: "0",
          name: "FAQ"
        },
        {
          value: "1",
          name: "故障排除"
        }
      ],
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
    handleClick(tab, event) {
      console.log(tab, event);
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
      } else if(row.line == '1') {
        return "预览";
      } else{
         return "下线";
      }
    },
    //查询
    search() {
      this.forms.pageNo = 1;
      this.currentPage = 1;
      this.forms.line = this.form.line;
      this.forms.type = this.form.type;
      this.getList();
    },
    //清空
    empty() {
      this.form.line = "";
      this.currentPage = 1;
      this.currentPage = 1;
      this.form.type = "";
      this.forms.line = "";
      this.forms.type = "";
      this.forms.pageNo = 1;
      this.getList();
    },
    //新增
    add() {
      this.dialogFormVisible = true;
    },
    //修改序号
    changeSeq(e) {
      const url = stopClick("/faq/title/seq");
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
    //新增关闭
    addClose() {
      this.dialogFormVisible = false;
      this.$refs.addform.resetFields();
      this.addform.seoDescriptionCn = "";
      this.addform.seoDescriptionEn = "";
      this.addform.seoKeywordCn = "";
      this.addform.seoKeywordEn = "";
      this.addform.seq = "";
    },
    //新增确定
    sure() {
      if (!/(^[1-9]\d*$)/.test(this.addform.seq) && this.addform.seq !== "") {
        this.msgTip("warning", "序号只能为正整数");
        return false;
      }
      this.$refs.addform.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          // this.isRepeat = true;
          this.isDisable = false;
          addList(this.addform).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "添加成功",
                center: true
              });
              this.dialogFormVisible = false;
              this.empty();
              this.getList();
              this.addform.seq = "";
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
            }
          });
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
        }
      });
    },

    //弹框封装
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //修改
    change(id) {
      this.dialogFormVisibles = true;
      this.changeid = id;
      aloneTit(id).then(res => {
        if (res.success) {
          this.changeform = res.voT;
        }
      });
    },
    //修改关闭
    changeClose() {
      this.dialogFormVisibles = false;
      this.$refs.changeform.resetFields();
    },
    //修改确定
    keep() {
      this.$refs.changeform.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          // this.isRepeat = true;
          this.isDisable = false;
          changeList(this.changeform, this.changeid).then(res => {
            if (res.success) {
              this.msgTip("success", "修改成功");
              this.dialogFormVisibles = false;
              this.empty();
              this.getList();
            } else {
              this.msgTip("error", res.errorMsg);
              // this.isRepeat = false;
              this.isDisable = true;
            }
          });
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
        }
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
        deleTit(id).then(res => {
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
.faq {
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
.faq .el-tabs {
  padding-left: 20px;
  margin-top: 20px;
}
</style>

