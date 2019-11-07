<template>
  <div class="banner">
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
        <li>
          <el-button type="primary" @click="add">新增</el-button>
        </li>
      </ol>
    </div>
    <el-table :data="tableData" style="width: 100%;margin-top:30px;" border v-loading="loading">
      <el-table-column align="center" label="产品型号" prop="type"></el-table-column>
      <el-table-column align="center" label="图片标题CN" prop="titleCn" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="图片描述CN" prop="descriptionCn" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="图片描述详情CN" prop="detailCn" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="按钮名称CN" prop="buttonNameCn"></el-table-column>
      <el-table-column align="center" label="按钮名称EN" prop="buttonNameEn"></el-table-column>
      <el-table-column align="center" label="按钮链接" show-overflow-tooltip prop="buttonLinkCn"></el-table-column>
      <el-table-column align="center" label="按钮链接" show-overflow-tooltip prop="buttonLinkEn"></el-table-column>
      <el-table-column align="center" label="上线/下线" prop="line" :formatter="state"></el-table-column>
      <el-table-column align="center" label="操作人" prop="updateUser"></el-table-column>
      <el-table-column align="center" label="操作时间" prop="updateTime"></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <div class="function-icon">
            <el-tooltip class="item" effect="dark" content="修改" placement="bottom">
              <img @click="change(scope.row.id)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="上线" placement="bottom">
              <img @click="online(scope.row)" src="../../assets/iconfont/online.png" alt />
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="下线" placement="bottom">
              <img @click="offline(scope.row)" src="../../assets/iconfont/offline.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="预览"
              placement="bottom"
            >
              <img @click="preview(scope.row)"  src="../../assets/iconfont/view.png" alt />
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="删除" placement="bottom">
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
        <el-form-item label="产品类型" :label-width="formLabelWidth" prop="type">
          <el-select v-model="addform.type" placeholder="请选择" style="width:100%">
            <el-option
              v-for="(item,index) in type"
              :key="index"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="按钮链接CN" :label-width="formLabelWidth">
          <el-input v-model="addform.buttonLinkCn" maxlength="255" autocomplete="off" type="text"></el-input>
        </el-form-item>
        <el-form-item label="按钮链接EN" :label-width="formLabelWidth">
          <el-input v-model="addform.buttonLinkEn" maxlength="255" autocomplete="off" type="text"></el-input>
        </el-form-item>
        <div class="commont">
          <el-form-item label="pc图片(1840*840)" :label-width="formLabelWidth">
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :before-upload="beforeUploadPicture1"
            >
              <img v-if="imageUrl1" :src="imageUrl1" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="移动端图片(750*1530)" :label-width="formLabelWidth">
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :before-upload="beforeUploadPicture2"
            >
              <img v-if="imageUrl2" :src="imageUrl2" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="标题CN" :label-width="formLabelWidth" prop="titleCn">
            <el-input v-model="addform.titleCn" maxlength="50" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="标题EN" :label-width="formLabelWidth" prop="titleEn">
            <el-input v-model="addform.titleEn" maxlength="50" autocomplete="off" type="text"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="描述CN" :label-width="formLabelWidth" prop="descriptionCn">
            <el-input v-model="addform.descriptionCn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="描述EN" :label-width="formLabelWidth" prop="descriptionEn">
            <el-input v-model="addform.descriptionEn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
        </div>
          <el-form-item label="描述详情CN" :label-width="formLabelWidth" prop="descriptionCn">
            <el-input v-model="addform.detailCn"  autocomplete="off" type="textarea"></el-input>
          </el-form-item>
          <el-form-item label="描述详情EN" :label-width="formLabelWidth" prop="descriptionEn">
            <el-input v-model="addform.detailEn" autocomplete="off" type="textarea"></el-input>
          </el-form-item>

        <div class="commont">
          <el-form-item label="按钮名称CN" :label-width="formLabelWidth">
            <el-input v-model="addform.buttonNameCn" maxlength="10" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="按钮名称EN" :label-width="formLabelWidth">
            <el-input v-model="addform.buttonNameEn" maxlength="10" autocomplete="off" type="text"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO标题(CN)" :label-width="formLabelWidth">
            <el-input v-model="addform.seoTitleCn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="SEO标题(EN)" :label-width="formLabelWidth">
            <el-input v-model="addform.seoTitleEn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO关键字(CN)" :label-width="formLabelWidth">
            <el-input v-model="addform.seoKeywordCn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字(EN)" :label-width="formLabelWidth">
            <el-input v-model="addform.seoKeywordEn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO描述(CN)" :label-width="formLabelWidth">
            <el-input
              v-model="addform.seoDescriptionCn"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO描述(EN)" :label-width="formLabelWidth">
            <el-input
              v-model="addform.seoDescriptionEn"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
        </div>
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
        <el-form-item label="产品类型" :label-width="formLabelWidth">
          <el-select v-model="changeform.type" disabled placeholder="请选择" style="width:100%">
            <el-option
              v-for="(item,index) in type"
              :key="index"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="按钮链接CN" :label-width="formLabelWidth">
          <el-input
            v-model="changeform.buttonLinkCn"
            maxlength="255"
            autocomplete="off"
            type="text"
          ></el-input>
        </el-form-item>
        <el-form-item label="按钮链接EN" :label-width="formLabelWidth">
          <el-input
            v-model="changeform.buttonLinkEn"
            maxlength="255"
            autocomplete="off"
            type="text"
          ></el-input>
        </el-form-item>
        <div class="commont">
          <el-form-item label="pc图片(1840*840)" :label-width="formLabelWidth">
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :before-upload="beforeUploadPicture1"
            >
              <img v-if="imageUrl1" :src="imageUrl1" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="移动端图片(750*1530)" :label-width="formLabelWidth">
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :before-upload="beforeUploadPicture2"
            >
              <img v-if="imageUrl2" :src="imageUrl2" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="标题CN" :label-width="formLabelWidth">
            <el-input v-model="changeform.titleCn" maxlength="50" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="标题EN" :label-width="formLabelWidth">
            <el-input v-model="changeform.titleEn" maxlength="50" autocomplete="off" type="text"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="描述CN" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.descriptionCn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="描述EN" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.descriptionEn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
        </div>
       
          <el-form-item label="描述详情CN" :label-width="formLabelWidth">
            <el-input v-model="changeform.detailCn"  autocomplete="off" type="textarea"></el-input>
          </el-form-item>
          <el-form-item label="描述详情EN" :label-width="formLabelWidth">
            <el-input v-model="changeform.detailEn" autocomplete="off" type="textarea"></el-input>
          </el-form-item>
        <div class="commont">
          <el-form-item label="按钮名称CN" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.buttonNameCn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="按钮名称EN" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.buttonNameEn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO标题(CN)" :label-width="formLabelWidth">
            <el-input v-model="changeform.seoTitleCn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="SEO标题(EN)" :label-width="formLabelWidth">
            <el-input v-model="changeform.seoTitleEn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO关键字(CN)" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.seoKeywordCn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字(EN)" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.seoKeywordEn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO描述(CN)" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.seoDescriptionCn"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO描述(EN)" :label-width="formLabelWidth">
            <el-input
              v-model="changeform.seoDescriptionEn"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="changeClose">取 消</el-button>
        <el-button type="primary" @click="keep" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getLists,
  addList,
  changeOn,
  deleBanner,
  changeBanner
} from "../../api/product.js";
import { changeList } from "../../api/faq";
import { uploads } from "../../api/upload.js";
import { searchDiclist } from "../../api/dictionary.js";
export default {
  data() {
    return {
      tableData: [],
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
      searchBtn: false,
      total: 0,
      currentPage: 1,
      loading: false,
      isDisable: false,
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
      value: "",
      dialogFormVisible: false,
      flag: false,
      addform: {
        buttonLinkCn: "",
        buttonLinkEn: "",
        buttonNameCn: "",
        buttonNameEn: "",
        descriptionCn: "",
        descriptionEn: "",
        detailCn: "",
        detailEn: "",
        titleCn: "",
        titleEn: "",
        type: "",
        urlMobile: "",
        urlPc: "",
        seoTitleCn: "",
        seoTitleEn: "",
        seoKeywordCn: "",
        seoKeywordEn: "",
        seoDescriptionCn: "",
        seoDescriptionEn: ""
      },
      addRules: {
        descriptionCn: [{ required: true, trigger: "blur" }],
        descriptionEn: [{ required: true, trigger: "blur" }],
        titleCn: [{ required: true, trigger: "blur" }],
        titleEn: [{ required: true, trigger: "blur" }],
        type: [{ required: true, trigger: "change" }],
        urlMobile: [{ required: true, trigger: "blur" }],
        urlPc: [{ required: true, trigger: "blur" }]
      },
      formLabelWidth: "120px",
      dialogFormVisibles: false,
      changeform: {
        buttonLinkCn: "",
        buttonLinkEn: "",
        buttonNameCn: "",
        buttonNameEn: "",
        descriptionCn: "",
        descriptionEn: "",
        detailCn: "",
        detailEn: "",
        seoTitleCn: "",
        seoTitleEn: "",
        seoDescriptionCn: "",
        seoDescriptionEn: "",
        seoKeywordCn: "",
        seoKeywordEn: "",
        titleCn: "",
        titleEn: "",
        type: "",
        urlMobile: "",
        urlPc: ""
      },
      changeRules: {
        descriptionCn: [{ required: true, trigger: "blur" }],
        descriptionEn: [{ required: true, trigger: "blur" }],
        titleCn: [{ required: true, trigger: "blur" }],
        titleEn: [{ required: true, trigger: "blur" }],
        type: [{ required: true, trigger: "blur" }],
        type: [{ required: true, trigger: "blur" }],
        urlMobile: [{ required: true, trigger: "blur" }],
        urlPc: [{ required: true, trigger: "blur" }]
      },
      changeid: "",
      radio: "",
      imageUrl2: "",
      imageUrl1: ""
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
    //搜索
    search() {
      this.searchBtn = true;
      this.currentPage = 1;
      this.forms.pageNo = 1;
      this.forms.id = this.form.id;
      this.forms.line = this.form.line;
      this.forms.type = this.form.type;
      this.getList();
    },
    //判断类型
    stateFormat(row) {
      if (row.buttonFlag === "Y") {
        return "是";
      } else {
        return "否";
      }
    },
    //判断上下线
    state(row) {
      if (row.line === "0") {
        return "上线";
      } else if(row.line === "1") {
        return "预览";
      } else if(row.line === "2"){
         return "下线";
      }
    },
    //清空
    empty() {
      this.form.line = "";
      this.form.type = "";
      this.forms.line = "";
      this.forms.type = "";
      this.currentPage = 1;
      this.forms.pageNo = 1;
      this.searchBtn = false;
      this.getList();
    },
    //弹框封装
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //新增
    add() {
      this.isDisable = false;
      this.dialogFormVisible = true;
    },

    //新增关闭按钮
    addClose() {
      this.dialogFormVisible = false;
      this.$refs.addform.resetFields();
      this.addform.type = "";
      this.addform.buttonNameCn = "";
      this.addform.buttonNameEn = "";
      this.addform.detailCn = "";
      this.addform.detailEn = "";
      this.addform.buttonLinkCn = "";
      this.addform.buttonLinkEn = "";
       this.addform.seoTitleCn = "";
        this.addform.seoTitleEn = "";
      this.addform.seoDescriptionCn = "";
      this.addform.seoDescriptionEn = "";
      this.addform.seoKeywordCn = "";
      this.addform.seoKeywordEn = "";
      this.addform.urlMobile = "";
      this.addform.urlPc = "";
      this.imageUrl2 = "";
      this.imageUrl1 = "";
    },
    //新增确定按钮
    sure() {
      this.addform.urlPc = this.imageUrl1;
      this.addform.urlMobile = this.imageUrl2;
      if (this.imageUrl1 == "") {
        this.msgTip("warning", "必须上传PC端图片");
        return;
      }
      if (this.imageUrl2 == "") {
        this.msgTip("warning", "必须上传手机端图片");
        return;
      }
      if (this.addform.buttonFlag == "Y" && this.addform.buttonLink == "") {
        this.msgTip("warning", "请输入按钮链接");
        return;
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
            } else {
              this.$message({ 
                type: "warning",
                message: res.errorMsg,
                center: true
              });
              // this.isRepeat = false;
              // this.isDisable = false;
            }
          });
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
        }
      });
    },
    //修改
    change(id) {
      this.isDisable = false;
      this.dialogFormVisibles = true;
      getLists({
        id: id,
        line: "",
        pageNo: 1,
        pageSize: 10,
        type: ""
      }).then(res => {
        let item = res.voList[0];
        this.changeform.buttonLinkCn = item.buttonLinkCn;
        this.changeform.buttonLinkEn = item.buttonLinkEn;
        this.changeform.buttonNameCn = item.buttonNameCn;
        this.changeform.buttonNameEn = item.buttonNameEn;
        this.changeform.descriptionCn = item.descriptionCn;
        this.changeform.descriptionEn = item.descriptionEn;
        this.changeform.detailCn = item.detailCn;
        this.changeform.detailEn = item.detailEn;
        this.changeform.seoTitleCn = item.seoTitleCn;
        this.changeform.seoTitleEn = item.seoTitleEn;
        this.changeform.seoDescriptionCn = item.seoDescriptionCn;
        this.changeform.seoDescriptionEn = item.seoDescriptionEn;
        this.changeform.seoKeywordCn = item.seoKeywordCn;
        this.changeform.seoKeywordEn = item.seoKeywordEn;
        this.changeform.titleCn = item.titleCn;
        this.changeform.titleEn = item.titleEn;
        this.imageUrl2 = item.urlMobile;
        this.imageUrl1 = item.urlPc;
        this.changeform.type = item.type;
      });
      this.changeid = id;
    },
    //修改关闭按钮
    changeClose() {
      this.dialogFormVisibles = false;
      this.$refs.changeform.resetFields();
      this.imageUrl1 = "";
      this.imageUrl2 = "";
    },
    //修改确定按钮
    keep() {
      this.changeform.urlMobile = this.imageUrl2;
      this.changeform.urlPc = this.imageUrl1;
      if (
        this.changeform.buttonFlag == "Y" &&
        this.changeform.buttonLink == ""
      ) {
        this.msgTip("warning", "请输入按钮链接");
        return;
      }
      this.$refs.changeform.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          // this.isRepeat = true;
          this.isDisable = true;
          changeBanner(this.changeform, this.changeid).then(res => {
            if (res.success) {
              this.msgTip("success", "修改成功");
              this.dialogFormVisibles = false;
              this.empty();
              this.getList();
            } else {
              this.msgTip("error", res.errorMsg);
              // this.isRepeat = false;
              this.isDisable = false;
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
        deleBanner(id).then(res => {
          if (res.success) {
            this.msgTip("success", "删除成功");
            this.getList();
          } else {
            this.msgTip("error", res.errorMsg);
          }
        });
      });
    },
    // 上传文件之前的钩子
    beforeUploadPicture2(file) {
      if (
        !(
          file.type === "image/png" ||
          file.type === "image/gif" ||
          file.type === "image/jpg" ||
          file.type === "image/jpeg"
        )
      ) {
        this.msgTip("warning", "请上传格式为png,gif,jpg,jpeg的图片");
        return;
      }
      // let size = file.size / 1024 / 1024 / 2;
      // if (size > 2) {
      //    this.msgTip("warning", "图片大小必须小于2M");
      //   return;
      // }
      let fd = new FormData();
      fd.append("file", file); //传文件
      uploads(fd).then(res => {
        this.imageUrl2 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
    }, // 上传文件之前的钩子
    beforeUploadPicture1(file) {
      if (
        !(
          file.type === "image/png" ||
          file.type === "image/gif" ||
          file.type === "image/jpg" ||
          file.type === "image/jpeg"
        )
      ) {
        this.msgTip("warning", "请上传格式为png, gif, jpg, jpeg的图片");
        return;
      }
      // let size = file.size / 1024 / 1024 / 2;
      // if (size > 2) {
      //    this.msgTip("warning", "图片大小必须小于2M");
      //   return;
      // }
      let fd = new FormData();
      fd.append("file", file); //传文件
      uploads(fd).then(res => {
        this.imageUrl1 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
    }
  }
};
</script>

<style scoped>
.head {
  margin: 20px 0 0 20px;
}
.avatar-uploader .el-upload {
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
  border: 1px dashed #d9d9d9;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
