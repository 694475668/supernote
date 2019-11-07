<template>
  <div class="home">
    <div class="head">
      <ol>
        <li>
          <h5>页面</h5>
          <el-select v-model="form.pageSerial" placeholder="请选择">
              <el-option
                v-for="(item,index) in pageSerial"
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
        <li v-permit="'/overall/seo/add'">
          <el-button type="primary" @click="add">新增</el-button>
        </li>
      </ol>
    </div>
    <el-table :data="tableData" style="width: 100%;margin-top:30px;" border v-loading="loading">
      <el-table-column align="center" label="页面" prop="pageSerial"></el-table-column>
      <el-table-column align="center" label="seo标题（CN）" prop="seoTitleCn"></el-table-column>
      <el-table-column align="center" label="seo关键字（CN）" prop="seoKeywordCn"></el-table-column>
      <!-- <el-table-column align="center" label="seo关键字（EN）" prop="seoKeywordCn"></el-table-column> -->
      <el-table-column align="center" label="seo描述中文（CN）" prop="seoDescriptionCn"></el-table-column>
      <!-- <el-table-column align="center" label="seo描述中文（EN）" prop="seoDescriptionEn"></el-table-column> -->
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
              v-permit="'/overall/seo/update'"
            >
              <img @click="change(scope.row)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除"
              placement="bottom"
              v-permit="'/overall/seo/delete'"
            >
              <img @click="dele(scope.row)" src="../../assets/iconfont/delete.png" alt />
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

    <el-dialog title="新增" :visible.sync="dialogFormVisible" @close="addClose('addform')">
      <el-form
        :model="addform"
        ref="addform"
        :rules="rules"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
          <el-form-item label="页面编号" :label-width="formLabelWidth" prop="pageSerial">
            <el-select v-model="addform.pageSerial" placeholder="请选择" style="width:100%">
              <el-option
                v-for="(item,index) in pageSerial"
                :key="index"
                :label="item.valueCn"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>

        <div class="commont">
          <el-form-item label="SEO标题（CN）" :label-width="formLabelWidth" prop="seoTitleCn">
            <el-input v-model="addform.seoTitleCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="SEO标题（EN）" :label-width="formLabelWidth" prop="seoTitleEn">
            <el-input v-model="addform.seoTitleEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>

        <div class="commont">
          <el-form-item label="SEO关键字（CN）" :label-width="formLabelWidth" prop="seoKeywordCn">
            <el-input v-model="addform.seoKeywordCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字（EN）" :label-width="formLabelWidth" prop="seoKeywordEn">
            <el-input v-model="addform.seoKeywordEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="seo描述（CN）" :label-width="formLabelWidth" prop="seoDescriptionCn">
            <el-input
              v-model="addform.seoDescriptionCn"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO描述（EN）" :label-width="formLabelWidth" prop="seoDescriptionEn">
            <el-input
              v-model="addform.seoDescriptionEn"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addClose('addform')">取 消</el-button>
        <el-button type="primary" @click="sure('addform')" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="修改"
      :visible.sync="dialogFormVisibles"
      @close="changeClose('addform')"
      :close-on-click-modal="false"
    >
      <el-form
        :model="addform"
        ref="addform"
        :rules="rules"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="页面编号" :label-width="formLabelWidth" prop="pageSerial">
            <el-select v-model="addform.pageSerial" disabled placeholder="请选择" style="width:100%">
              <el-option
                v-for="(item,index) in pageSerial"
                :key="index"
                :label="item.valueCn"
                :value="item.value"
              ></el-option>
            </el-select>
        </el-form-item>
        <div class="commont">
          <el-form-item label="SEO标题（CN）" :label-width="formLabelWidth" prop="seoTitleCn">
            <el-input v-model="addform.seoTitleCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="SEO标题（EN）" :label-width="formLabelWidth" prop="seoTitleEn">
            <el-input v-model="addform.seoTitleEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>

        <div class="commont">
          <el-form-item label="SEO关键字（CN）" :label-width="formLabelWidth" prop="seoKeywordCn">
            <el-input v-model="addform.seoKeywordCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字（EN）" :label-width="formLabelWidth" prop="seoKeywordEn">
            <el-input v-model="addform.seoKeywordEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO描述（CN）" :label-width="formLabelWidth" prop="seoDescriptionCn">
            <el-input
              v-model="addform.seoDescriptionCn"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO描述（EN）" :label-width="formLabelWidth" prop="seoDescriptionEn">
            <el-input
              v-model="addform.seoDescriptionEn"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="changeClose('addform')">取 消</el-button>
        <el-button type="primary" @click="keep('addform')" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  querySeo,
  addSeo,
  updateSeo,
  getSeoById,
  delSeo,
  dataManipulation
} from "../../api/overallseo.js";
import { searchDiclist } from "../../api/dictionary.js";
export default {
  data() {
    return {
      rules: {
        seoTitleCn: [{ required: true, trigger: "blur" }],
        seoTitleEn: [{ required: true, trigger: "blur" }],
        seoKeywordCn: [{ required: true, trigger: "blur" }],
        seoKeywordEn: [{ required: true, trigger: "blur" }],
        seoDescriptionCn: [{ required: true, trigger: "blur" }],
        seoDescriptionEn: [{ required: true, trigger: "blur" }]
      },
      form: {
        pageNo: 1,
        pageSize: 10,
        pageSerial: ""
      },
      seoId: "",
      pageSerial: [],
      currentPage: 1,
      total: 0,
      isDisable: false,
      addform: {
        seoTitleCn: "",
        seoTitleEn: "",
        pageSerial: "",
        seoKeywordCn: "",
        seoKeywordEn: "",
        seoDescriptionCn: "",
        seoDescriptionEn: ""
      },
      loading: false,
      tableData: [],
      multipleSelection: [],
      value: "",
      dialogFormVisible: false,
      addRules: [],
      formLabelWidth: "150px",
      dialogFormVisibles: false,
      changeRules: [],
      dialogVisible: false,
      limitNum: 1,
      isFlag: true
    };
  },
  created() {
    //this.getList();
  },
  mounted() {
    searchDiclist({
      name: "PAGE_SERIAL",
      pageNo: 1,
      pageSize: 10,
      sortField: "",
      sortRules: "",
      valueMeaning: ""
    }).then(res => {
      this.pageSerial = res.voList;
    });

    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      querySeo(this.form).then(res => {
          this.tableData = res.voList;
          this.loading = false;
      });
    },
    //搜索
    search() {
      this.getList();
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.forms.pageNo = currentPage;
      this.getList();
    },
    //清空
    empty() {
      this.form.pageSerial = "";
      this.getList();
    },
    //新增
    add() {
      this.dialogFormVisible = true;
      this.isDisable = false;
    },
    //新增关闭按钮
    addClose(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible = false;
    },
    //新增确定按钮
    sure(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if(this.addform.pageSerial == ""){
            this.msgTip("warning", "请选择页面编号");
            return false;
          }
          this.isDisable = true;
          addSeo(this.addform).then(res => {
            if (res.success) {
              this.msgTip("success", "添加成功");
              this.empty();
              this.dialogFormVisible = false;
              this.$refs[formName].resetFields();
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
              this.isDisable = false;
            }
          });
        }
      });
    },
    //修改
    change(v) {
      this.dialogFormVisibles = true;
      this.isDisable = false;
      getSeoById(v.id).then(res => {
        this.seoId = res.voT.id;
        this.addform.seoTitleCn = res.voT.seoTitleCn;
        this.addform.seoTitleEn = res.voT.seoTitleEn;
        this.addform.pageSerial = res.voT.pageSerial; 
        this.addform.seoKeywordCn = res.voT.seoKeywordCn;
        this.addform.seoKeywordEn = res.voT.seoKeywordEn;
        this.addform.seoDescriptionCn = res.voT.seoDescriptionCn;
        this.addform.seoDescriptionEn = res.voT.seoDescriptionEn;
      });
    },
    //修改关闭按钮
    changeClose(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisibles = false;
    },
    //修改确定按钮
    keep(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.isDisable = true;
          updateSeo(this.addform, this.seoId).then(res => {
            if (res.success) {
              this.msgTip("success", "修改成功");
              this.empty();
              this.dialogFormVisibles = false;
              this.$refs[formName].resetFields();
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
              this.isDisable = false;
            }
          });
        }
      });
    },
    //删除
    dele(v) {
      this.$confirm("您是否要删除当前选中的记录?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        delSeo(v.id).then(res => {
          if (res.success) {
            this.msgTip("success", "操作成功");
            this.empty();
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
