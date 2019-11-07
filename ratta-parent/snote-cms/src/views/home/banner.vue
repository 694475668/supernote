<template>
  <div class="home">
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
          <h5>上下轮播图</h5>
          <el-select v-model="form.type" placeholder="请选择">
            <el-option
              v-for="item in types"
              :key="item.type"
              :label="item.label"
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
        <li v-permit="'/home/banner/add'">
          <el-button type="primary" @click="add">新增</el-button>
        </li>
      </ul>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%;margin-top:30px;"
      border
      v-loading="loading"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55"></el-table-column> -->
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
      <el-table-column align="center" label="图片标题CN" show-overflow-tooltip prop="nameCn"></el-table-column>
      <el-table-column align="center" label="图片描述CN" show-overflow-tooltip prop="descriptionCn"></el-table-column>
      <el-table-column
        align="center"
        label="按钮标识"
        prop="buttonFlag"
        :formatter="buttonFlagFormatter"
      ></el-table-column>
      <el-table-column align="center" label="按钮标题CN" show-overflow-tooltip prop="buttonNameCn"></el-table-column>
      <el-table-column align="center" label="按钮链接CN" show-overflow-tooltip prop="buttonLinkCn"></el-table-column>
      <el-table-column align="center" label="按钮链接EN" show-overflow-tooltip prop="buttonLinkEn"></el-table-column>
      <el-table-column align="center" label="上下标识" prop="type" :formatter="typeFormatter"></el-table-column>
      <el-table-column align="center" label="上线/下线" prop="line" :formatter="lineFormatter"></el-table-column>
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
              v-permit="'/home/banner/update'"
            >
              <img @click="change(scope.row)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除"
              placement="bottom"
              v-permit="'/home/banner/delete'"
            >
              <img @click="dele(scope.row)" src="../../assets/iconfont/delete.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="上线"
              placement="bottom"
              v-permit="'/home/banner/online'"
            >
              <img @click="online(scope.row)" src="../../assets/iconfont/online.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="下线"
              placement="bottom"
              v-permit="'/home/banner/unline'"
            >
              <img @click="offline(scope.row)" src="../../assets/iconfont/offline.png" alt />
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="预览" placement="bottom">
              <img @click="preview(scope.row)" src="../../assets/iconfont/view.png" alt />
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
        <el-form-item label="序号" :label-width="formLabelWidth">
          <el-input v-model="addform.seq" autocomplete="off" maxlength="9"></el-input>
        </el-form-item>
        <div class="commont">
          <el-form-item label="PC字体颜色" :label-width="formLabelWidth">
            <el-color-picker v-model="addform.colorPc"></el-color-picker>
          </el-form-item>
          <el-form-item label="移动端字体颜色" :label-width="formLabelWidth">
            <el-color-picker v-model="addform.colorMobile"></el-color-picker>
          </el-form-item>
        </div>
        <el-form-item label="按钮标志" :label-width="formLabelWidth">
          <el-radio v-model="addform.buttonFlag" label="Y" @change="buttonFlag">有</el-radio>
          <el-radio v-model="addform.buttonFlag" label="N">无</el-radio>
        </el-form-item>
        <el-form-item label="链接标志" :label-width="formLabelWidth" v-if="addform.buttonFlag=='Y'">
          <el-radio v-model="addform.buttonInnerOut" label="0">官网</el-radio>
          <el-radio v-model="addform.buttonInnerOut" label="1">其他</el-radio>
        </el-form-item>
        <el-form-item
          label="链接类型"
          :label-width="formLabelWidth"
          v-if="addform.buttonInnerOut=='0'&& addform.buttonFlag=='Y'"
        >
          <el-select v-model="addform.productId" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in recommendType"
              :key="item.recommendId"
              :label="item.recommendName"
              :value="item.recommendId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上下轮播图标志" :label-width="formLabelWidth">
          <el-radio v-model="addform.type" label="0">上</el-radio>
          <el-radio v-model="addform.type" label="1">下</el-radio>
        </el-form-item>
        <div class="commont">
          <el-form-item label="pc图片(1920*1000)" :label-width="formLabelWidth">
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
          <el-form-item label="移动端图片(750*1524)" :label-width="formLabelWidth">
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
          <el-form-item label="标题（CN）" :label-width="formLabelWidth" prop="nameCn">
            <el-input v-model="addform.nameCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="标题（EN）" :label-width="formLabelWidth" prop="nameEn">
            <el-input v-model="addform.nameEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="描述（CN）" :label-width="formLabelWidth" prop="descriptionCn">
            <el-input
              v-model="addform.descriptionCn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
          <el-form-item label="描述（EN）" :label-width="formLabelWidth" prop="descriptionEn">
            <el-input
              v-model="addform.descriptionEn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
        </div>
        <div
          class="commont"
          v-if="addform.buttonInnerOut=='1' && addform.buttonFlag=='Y' || addform.buttonInnerOut=='0' && addform.buttonFlag=='Y'"
        >
          <el-form-item label="按钮名称（CN）" :label-width="formLabelWidth" prop="buttonNameCn">
            <el-input v-model="addform.buttonNameCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="按钮名称（EN）" :label-width="formLabelWidth" prop="buttonNameEn">
            <el-input v-model="addform.buttonNameEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <div class="commont" v-if="addform.buttonInnerOut=='1' && addform.buttonFlag=='Y'">
          <el-form-item label="按钮链接（CN）" :label-width="formLabelWidth" prop="buttonLinkCn">
            <el-input v-model="addform.buttonLinkCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="按钮链接（EN）" :label-width="formLabelWidth" prop="buttonLinkEn">
            <el-input v-model="addform.buttonLinkEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <!-- <div class="commont">
          <el-form-item label="SEO关键字（CN）" :label-width="formLabelWidth">
            <el-input v-model="addform.seoKeywordCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字（EN）" :label-width="formLabelWidth">
            <el-input v-model="addform.seoKeywordEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO描述（CN）" :label-width="formLabelWidth">
            <el-input
              v-model="addform.seoDescriptionCn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO描述（EN）" :label-width="formLabelWidth">
            <el-input
              v-model="addform.seoDescriptionEn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
        </div>-->
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
        <div class="commont">
          <el-form-item label="PC字体颜色" :label-width="formLabelWidth">
            <el-color-picker v-model="addform.colorPc"></el-color-picker>
          </el-form-item>
          <el-form-item label="移动端字体颜色" :label-width="formLabelWidth">
            <el-color-picker v-model="addform.colorMobile"></el-color-picker>
          </el-form-item>
        </div>
        <el-form-item label="按钮标志" :label-width="formLabelWidth">
          <el-radio v-model="addform.buttonFlag" label="Y" @change="buttonFlag">有</el-radio>
          <el-radio v-model="addform.buttonFlag" label="N">无</el-radio>
        </el-form-item>
        <el-form-item label="链接标志" :label-width="formLabelWidth" v-if="addform.buttonFlag=='Y'">
          <el-radio v-model="addform.buttonInnerOut" label="0">官网</el-radio>
          <el-radio v-model="addform.buttonInnerOut" label="1">其他</el-radio>
        </el-form-item>
        <el-form-item
          label="链接类型"
          :label-width="formLabelWidth"
          v-if="addform.buttonInnerOut=='0'&& addform.buttonFlag=='Y'"
        >
          <el-select v-model="addform.productId" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in recommendType"
              :key="item.recommendId"
              :label="item.recommendName"
              :value="item.recommendId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上下轮播图标志" :label-width="formLabelWidth">
          <el-radio v-model="addform.type" label="0">上</el-radio>
          <el-radio v-model="addform.type" label="1">下</el-radio>
        </el-form-item>
        <div class="commont">
          <el-form-item label="pc图片(1920*1000)" :label-width="formLabelWidth">
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
          <el-form-item label="移动端图片(750*1524)" :label-width="formLabelWidth">
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
          <el-form-item label="标题（CN）" :label-width="formLabelWidth" prop="nameCn">
            <el-input v-model="addform.nameCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="标题（EN）" :label-width="formLabelWidth" prop="nameEn">
            <el-input v-model="addform.nameEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="描述（CN）" :label-width="formLabelWidth" prop="descriptionCn">
            <el-input
              v-model="addform.descriptionCn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
          <el-form-item label="描述（EN）" :label-width="formLabelWidth" prop="descriptionEn">
            <el-input
              v-model="addform.descriptionEn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
        </div>
        <div
          class="commont"
          v-if="addform.buttonInnerOut=='1' && addform.buttonFlag=='Y' || addform.buttonInnerOut=='0' && addform.buttonFlag=='Y'"
        >
          <el-form-item label="按钮名称（CN）" :label-width="formLabelWidth" prop="buttonNameCn">
            <el-input v-model="addform.buttonNameCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="按钮名称（EN）" :label-width="formLabelWidth" prop="buttonNameEn">
            <el-input v-model="addform.buttonNameEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <div class="commont" v-if="addform.buttonInnerOut=='1' && addform.buttonFlag=='Y'">
          <el-form-item label="按钮链接（CN）" :label-width="formLabelWidth" prop="buttonLinkCn">
            <el-input v-model="addform.buttonLinkCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="按钮链接（EN）" :label-width="formLabelWidth" prop="buttonLinkEn">
            <el-input v-model="addform.buttonLinkEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <!-- <div class="commont">
          <el-form-item label="SEO关键字（CN）" :label-width="formLabelWidth">
            <el-input v-model="addform.seoKeywordCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字（EN）" :label-width="formLabelWidth">
            <el-input v-model="addform.seoKeywordEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO描述（CN）" :label-width="formLabelWidth">
            <el-input
              v-model="addform.seoDescriptionCn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO描述（EN）" :label-width="formLabelWidth">
            <el-input
              v-model="addform.seoDescriptionEn"
              autocomplete="off"
              type="text"
              maxlength="255"
            ></el-input>
          </el-form-item>
        </div>-->
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
  queryBanner,
  addBanner,
  updateBanner,
  getBannerById,
  updateLowerLine,
  queryRecommend,
  updateSeq,
  delBanner,
  dataManipulation,
  upload
} from "../../api/banner.js";
import { stopClick } from "../../utils/index.js";
export default {
  data() {
    return {
      isShow: false,
      rules: {
        nameCn: [{ required: true, trigger: "blur" }],
        nameEn: [{ required: true, trigger: "blur" }],
        descriptionCn: [{ required: true, trigger: "blur" }],
        descriptionEn: [{ required: true, trigger: "blur" }],
        buttonLinkCn: [{ required: true, trigger: "blur" }],
        buttonLinkEn: [{ required: true, trigger: "blur" }],
        buttonNameCn: [{ required: true, trigger: "blur" }],
        buttonNameEn: [{ required: true, trigger: "blur" }]
      },
      addApprove: {
        seq: ""
      },
      dialogFormVisible1: false,
      isDisable: false,
      addform: {
        id: "",
        seq: "",
        colorPc: "",
        colorMobile: "",
        buttonFlag: "",
        urlMobile: "",
        urlPc: "",
        nameCn: "",
        nameEn: "",
        descriptionCn: "",
        descriptionEn: "",
        type: "0",
        buttonNameCn: "",
        buttonNameEn: "",
        line: "2",
        buttonLinkCn: "",
        buttonLinkEn: "",
        productId: "",
        buttonInnerOut: ""
      },
      recommendType: [],
      doUpload: "",
      pppss: "",
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
      imageUrl1: "",
      imageUrl2: "",
      types: [
        {
          type: "",
          label: "所有"
        },
        {
          type: "0",
          label: "上图"
        },
        {
          type: "1",
          label: "下图"
        }
      ],
      currentPage: 1,
      total: 0,
      loading: false,
      form: {
        language: "",
        line: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: "",
        type: ""
      },
      forms: {
        language: "",
        line: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: "",
        type: ""
      },
      lineStatus: {
        line: ""
      },
      tableData: [],
      multipleSelection: [],
      value: "",
      dialogFormVisible: false,
      addRules: [],
      formLabelWidth: "160px",
      dialogFormVisibles: false,
      changeRules: [],
      radio: "",
      dialogVisible: false,
      limitNum: 1,
      isFlag: true
    };
  },
  created() {
    //this.getList();
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
  mounted() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        queryBanner(this.forms).then(res => {
          if (res.voList == null || res.voList == "") {
            this.forms.pageNo = 1;
            this.currentPage = 1;
            queryBanner(this.forms).then(res => {
              this.tableData = res.voList;
              this.total = Number(res.total);
              this.loading = false;
            });
          }
          this.tableData = res.voList;
          this.total = Number(res.total);
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
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    buttonFlag() {
      if (this.addform.buttonInnerOut == "") {
        this.addform.buttonInnerOut = "0";
      }
    },
    //判断上下轮播图
    typeFormatter(row, column) {
      if (row.type == "0") {
        return "上轮播图";
      } else if (row.type == "1") {
        return "下轮播图";
      }
    },
    // 上传图片出错
    uploadError(err, file, fileList) {
      console.log(file);
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
    //判断按钮
    buttonFlagFormatter(row, column) {
      if (row.buttonFlag == "Y") {
        return "是";
      } else if (row.buttonFlag == "N") {
        return "否";
      }
    },
    //新增
    add() {
      this.addform.seq = "";
      this.addform.colorPc = "";
      this.addform.colorMobile = "";
      this.addform.urlPc = "";
      this.addform.urlMobile = "";
      this.imageUrl2 = "";
      this.imageUrl1 = "";
      this.addform.nameCn = "";
      this.addform.buttonFlag = "N";
      this.addform.nameEn = "";
      this.addform.descriptionCn = "";
      this.addform.descriptionEn = "";
      this.addform.buttonNameCn = "";
      this.addform.buttonNameEn = "";
      this.addform.seoKeywordCn = "";
      this.addform.seoKeywordEn = "";
      this.addform.seoDescriptionCn = "";
      this.addform.seoDescriptionEn = "";
      this.addform.buttonLinkCn = "";
      this.addform.productId = "";
      this.addform.buttonInnerOut = "0";
      this.addform.buttonLinkEn = "";
      this.dialogFormVisible = true;
      this.isDisable = false;
      queryRecommend().then(res => {
        this.recommendType = res.voList;
      });
    },
    //新增关闭按钮
    addClose(formName) {
      this.dialogFormVisible = false;
      this.$refs[formName].resetFields();
    },
    //新增确定按钮
    sure(formName) {
      if (this.addform.buttonFlag == "N") {
        this.addform.productId = "";
        this.addform.buttonInnerOut = "";
        this.addform.buttonLinkEn = "";
        this.addform.buttonLinkCn = "";
        this.addform.buttonNameCn = "";
        this.addform.buttonNameEn = "";
      }
      if (this.addform.buttonFlag == "Y") {
        if (this.addform.buttonInnerOut == "0") {
          this.addform.buttonLinkEn = "";
          this.addform.buttonLinkEn = "";
        }
        if (this.addform.buttonInnerOut == "1") {
          this.addform.productId = "";
        }
      }
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (
            !/^[1-9]{1,}[\d]*$/.test(this.addform.seq) &&
            this.addform.seq !== ""
          ) {
            this.msgTip("warning", "序号只能为正整数");
            return;
          }
          if (this.addform.colorPc == "" || this.addform.colorMobile == "") {
            this.msgTip("warning", "请选择字体颜色");
            return;
          }
          if (this.addform.buttonFlag == "Y") {
            if (this.addform.buttonInnerOut == "0") {
              if (this.addform.productId == "") {
                this.msgTip("warning", "请选择链接类型");
                return;
              }
            }
          }
          if (this.imageUrl1 == "") {
            this.msgTip("warning", "必须上传PC端图片");
            return;
          }
          if (this.imageUrl2 == "") {
            this.msgTip("warning", "必须上传移动端图片");
            return;
          }
          this.addform.urlPc = this.imageUrl1;
          this.addform.urlMobile = this.imageUrl2;
          this.isDisable = true;
          addBanner(this.addform).then(res => {
            if (res.success) {
              this.msgTip("success", "添加成功");
              this.addform.seq = "";
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
      getBannerById(v.id).then(res => {
        this.addform.id = res.id;
        this.addform.colorPc = res.colorPc;
        this.addform.colorMobile = res.colorMobile;
        this.addform.urlPc = res.urlPc;
        this.addform.urlMobile = res.urlMobile;
        this.imageUrl2 = res.urlMobile;
        this.imageUrl1 = res.urlPc;
        this.addform.nameCn = res.nameCn;
        this.addform.nameEn = res.nameEn;
        this.addform.descriptionCn = res.descriptionCn;
        this.addform.descriptionEn = res.descriptionEn;
        this.addform.type = res.type;
        this.addform.buttonFlag = res.buttonFlag;
        this.addform.buttonNameCn = res.buttonNameCn;
        this.addform.buttonNameEn = res.buttonNameEn;
        this.addform.seoKeywordCn = res.seoKeywordCn;
        this.addform.seoKeywordEn = res.seoKeywordEn;
        this.addform.seoDescriptionCn = res.seoDescriptionCn;
        this.addform.seoDescriptionEn = res.seoDescriptionEn;
        this.addform.line = res.line;
        this.addform.buttonLinkCn = res.buttonLinkCn;
        this.addform.buttonLinkEn = res.buttonLinkEn;
        this.addform.buttonInnerOut = res.buttonInnerOut;
        this.addform.productId = res.productId;
      });
      queryRecommend().then(res => {
        this.recommendType = res.voList;
      });
    },
    //修改关闭按钮
    changeClose(formName) {
      this.dialogFormVisibles = false;
      this.$refs[formName].resetFields();
    },
    //修改确定按钮
    keep(formName) {
      if (this.addform.buttonFlag == "N") {
        this.addform.productId = "";
        this.addform.buttonInnerOut = "";
        this.addform.buttonLinkEn = "";
        this.addform.buttonLinkCn = "";
        this.addform.buttonNameCn = "";
        this.addform.buttonNameEn = "";
      }
      if (this.addform.buttonFlag == "Y") {
        if (this.addform.buttonInnerOut == "0") {
          this.addform.buttonLinkCn = "";
          this.addform.buttonLinkEn = "";
        }
        if (this.addform.buttonInnerOut == "1") {
          this.addform.productId = "";
        }
      }
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.addform.colorPc == "" || this.addform.colorMobile == "") {
            this.msgTip("warning", "请选择字体颜色");
            return;
          }
          if (this.addform.buttonFlag == "Y") {
            if (this.addform.buttonInnerOut == "0") {
              if (this.addform.productId == "") {
                this.msgTip("warning", "请选择链接类型");
                return;
              }
            }
          }
          if (this.imageUrl1 == "") {
            this.msgTip("warning", "必须上传PC端图片");
            return;
          }
          if (this.imageUrl2 == "") {
            this.msgTip("warning", "必须上传移动端图片");
            return;
          }
          this.isDisable = true;
          this.addform.urlMobile = this.imageUrl2;
          this.addform.urlPc = this.imageUrl1;
          updateBanner(this.addform).then(res => {
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
        delBanner(v.id).then(res => {
          if (res.success) {
            this.msgTip("success", "操作成功");
            this.getList();
          } else {
            this.msgTip("warning", res.errorMsg);
          }
        });
      });
    },
    empty() {
      this.forms.pageNo = 1;
      this.currentPage = 1;
      this.form.line = "";
      this.form.type = "";
      this.forms.type = "";
      this.forms.line = "";
      this.getList();
    },
    //查询
    search() {
      this.loading = true;
      this.currentPage = 1;
      this.forms.pageNo = 1;
      this.forms.type = this.form.type;
      this.forms.line = this.form.line;
      setTimeout(() => {
        queryBanner({
          line: this.form.line,
          pageNo: 1,
          pageSize: 10,
          sortField: "",
          language: "",
          sortRules: "",
          type: this.form.type
        }).then(res => {
          this.form.pageNo = 1;
          this.tableData = res.voList;
          this.total = res.total;
        });
        this.currentPage = 1;
        this.loading = false;
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
            // 前端刷新
            // dataManipulation(
            //   this.tableData,
            //   v.id,
            //   "1",
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

    // 上传文件之前的钩子
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
      let fd = new FormData();
      fd.append("file", file); //传文件
      upload(fd).then(res => {
        this.imageUrl1 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
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
        this.msgTip("warning", "请上传格式为png, gif, jpg, jpeg的图片");
        return;
      }
      let fd = new FormData();
      fd.append("file", file); //传文件
      upload(fd).then(res => {
        this.imageUrl2 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
    },
    // 文件超出个数限制时的钩子
    handleExceed(files, fileList) {},
    // 文件列表移除文件时的钩子
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    // 点击文件列表中已上传的文件时的钩子
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = false;
    },
    //修改序号
    changeSeq(e) {
      const url = stopClick("/home/banner/seq");
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
  border: 1px dashed #d9d9d9;
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
