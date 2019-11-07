<template>
  <div class="awards">
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
          <el-button type="primary" @click="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
        <li v-permit="'/home/award/add'">
          <el-button type="primary" @click="add">新增</el-button>
        </li>
      </ul>
    </div>
    <el-table :data="tableData" style="width: 100%;margin-top:30px;" border v-loading="loading">
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
      <el-table-column align="center" label="颁奖机构CN" show-overflow-tooltip prop="mechanismCn"></el-table-column>
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
              v-permit="'/home/award/update'"
            >
              <img @click="change(scope.row)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除"
              placement="bottom"
              v-permit="'/home/award/delete'"
            >
              <img @click="dele(scope.row)" src="../../assets/iconfont/delete.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="上线"
              placement="bottom"
              v-permit="'/home/award/online'"
            >
              <img @click="online(scope.row)" src="../../assets/iconfont/online.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="下线"
              placement="bottom"
              v-permit="'/home/award/unline'"
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
    <el-dialog
      title="新增"
      :visible.sync="dialogFormVisible"
      @close="addClose('addform')"
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
        <el-form-item label="序号" :label-width="formLabelWidth">
          <el-input v-model="addform.seq" autocomplete="off" maxlength="9"></el-input>
        </el-form-item>
        <div class="commont">
          <el-form-item label="pc图片CN(1920*720)" :label-width="formLabelWidth">
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
          <el-form-item label="pc图片EN(1920*720)" :label-width="formLabelWidth">
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :before-upload="beforeUploadPicture3"
            >
              <img v-if="imageUrl3" :src="imageUrl3" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="移动端图片CN(550*324)" :label-width="formLabelWidth">
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
          <el-form-item label="移动端图片EN(550*324)" :label-width="formLabelWidth">
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :before-upload="beforeUploadPicture4"
            >
              <img v-if="imageUrl4" :src="imageUrl4" class="avatar" />
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
        <div class="commont">
          <el-form-item label="颁奖机构（CN）" :label-width="formLabelWidth">
            <el-input v-model="addform.mechanismCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="颁奖机构（EN）" :label-width="formLabelWidth">
            <el-input v-model="addform.mechanismEn" autocomplete="off" type="text" maxlength="255"></el-input>
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
        <el-button type="primary" @click="sure('addform')">保存</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="修改"
      :visible.sync="dialogFormVisible2"
      @close="changeClose('addform')"
      :close-on-click-modal="false"
    >
      <el-form
        :model="addform"
        :rules="rules"
        ref="addform"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <div class="commont">
          <el-form-item label="pc图片CN(1920*720)" :label-width="formLabelWidth">
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
          <el-form-item label="pc图片EN(1920*720)" :label-width="formLabelWidth">
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :before-upload="beforeUploadPicture3"
            >
              <img v-if="imageUrl3" :src="imageUrl3" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="移动端图片CN(550*324)" :label-width="formLabelWidth">
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
          <el-form-item label="移动端图片EN(550*324)" :label-width="formLabelWidth">
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :before-upload="beforeUploadPicture4"
            >
              <img v-if="imageUrl4" :src="imageUrl4" class="avatar" />
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
        <div class="commont">
          <el-form-item label="颁奖机构（CN）" :label-width="formLabelWidth">
            <el-input v-model="addform.mechanismCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="颁奖机构（EN）" :label-width="formLabelWidth">
            <el-input v-model="addform.mechanismEn" autocomplete="off" type="text" maxlength="255"></el-input>
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
        <el-button type="primary" @click="keep('addform')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  queryAward,
  addAward,
  updateAward,
  getAwardById,
  updateLowerLine,
  updateSeq,
  delAward,
  updateRecommend,
  dataManipulation,
  upload
} from "../../api/awards.js";
import { stopClick } from "../../utils/index.js";
export default {
  data() {
    return {
      isShow: false,
      rules: {
        seq: [{ required: true, trigger: "blur" }],
        nameCn: [{ required: true, trigger: "blur" }],
        nameEn: [{ required: true, trigger: "blur" }],
        descriptionCn: [{ required: true, trigger: "blur" }],
        descriptionEn: [{ required: true, trigger: "blur" }]
      },
      imageUrl2: "",
      imageUrl1: "",
      imageUrl3: "",
      imageUrl4: "",
      addform: {
        id: "",
        seq: "",
        urlMobileCn: "",
        urlMobileEn: "",
        urlPcCn: "",
        urlPcEn: "",
        nameCn: "",
        nameEn: "",
        descriptionCn: "",
        descriptionEn: "",
        mechanismCn: "",
        mechanismEn: "",
        line: "2"
      },
      isDisable: false,
      addApprove: {
        seq: ""
      },
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogFormVisible2: false,
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
      tableData: [],
      currentPage: 1,
      total: 0,
      loading: false,
      form: {
        language: "",
        line: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: ""
      },
      forms: {
        language: "",
        line: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: ""
      },
      lineStatus: {
        line: ""
      },
      isFlag: true,
      value: "",
      dialogFormVisible: false,
      addRules: [],
      formLabelWidth: "170px",
      dialogFormVisibles: false,
      changeRules: []
    };
  },
  created() {},
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
    getList() {
      this.loading = true;
      setTimeout(() => {
        queryAward(this.forms).then(res => {
          if (res.voList == null || res.voList == "") {
            this.forms.pageNo = 1;
            this.currentPage = 1;
            queryAward(this.forms).then(res => {
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
    //查询
    search() {
      this.loading = true;
      this.currentPage = 1;
      this.forms.pageNo = 1;
      this.forms.line = this.form.line;
      setTimeout(() => {
        queryAward({
          line: this.form.line,
          pageNo: 1,
          pageSize: 10,
          sortField: "",
          language: "",
          sortRules: ""
        }).then(res => {
          this.form.pageNo = 1;
          this.tableData = res.voList;
          this.total = res.total;
        });
        this.currentPage = 1;
        this.loading = false;
      }, 500);
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.forms.pageNo = currentPage;
      this.getList();
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
    // 上传文件之前的钩子
    beforeUploadPicture3(file) {
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
        this.imageUrl3 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
    },
    // 上传文件之前的钩子
    beforeUploadPicture4(file) {
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
        this.imageUrl4 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
    },
    //删除
    dele(v) {
      this.$confirm("您是否要删除当前选中的记录?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        delAward(v.id).then(res => {
          if (res.success) {
            this.msgTip("success", "操作成功");
            this.getList();
          } else {
            this.msgTip("warning", res.errorMsg);
          }
        });
      });
    },
    addApproveClose(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible1 = false;
    },
    empty() {
      this.forms.pageNo = 1;
      this.currentPage = 1;
      this.forms.line = "";
      this.form.line = "";
      this.getList();
    },
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
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
    //新增
    add() {
      this.addform.seq = "";
      this.addform.urlPcCn = "";
      this.addform.urlPcEn = "";
      this.addform.urlMobileCn = "";
      this.addform.urlMobileEn = "";
      this.imageUrl2 = "";
      this.imageUrl1 = "";
      this.imageUrl4 = "";
      this.imageUrl3 = "";
      this.addform.nameCn = "";
      this.addform.nameEn = "";
      this.addform.descriptionCn = "";
      this.addform.descriptionEn = "";
      this.addform.mechanismCn = "";
      this.addform.mechanismEn = "";
      this.addform.seoKeywordCn = "";
      this.addform.seoKeywordEn = "";
      this.addform.seoDescriptionCn = "";
      this.addform.seoDescriptionEn = "";
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
          if (
            !/^[1-9]{1,}[\d]*$/.test(this.addform.seq) &&
            this.addform.seq !== ""
          ) {
            this.msgTip("warning", "序号只能为正整数");
            return;
          }
          if (this.imageUrl1 == "") {
            this.msgTip("warning", "必须上传PC端中文图片");
            return;
          }
          if (this.imageUrl2 == "") {
            this.msgTip("warning", "必须上传手机端中文图片");
            return;
          }
          if (this.imageUrl3 == "") {
            this.msgTip("warning", "必须上传PC端英文图片");
            return;
          }
          if (this.imageUrl4 == "") {
            this.msgTip("warning", "必须上传手机端英文图片");
            return;
          }
          this.addform.urlPcCn = this.imageUrl1;
          this.addform.urlPcEn = this.imageUrl3;
          this.addform.urlMobileCn = this.imageUrl2;
          this.addform.urlMobileEn = this.imageUrl4;
          this.isDisable = true;
          addAward(this.addform).then(res => {
            if (res.success) {
              this.msgTip("success", "添加成功");
              this.empty();
              this.addform.seq = "";
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
      this.dialogFormVisible2 = true;
      this.isDisable = false;
      getAwardById(v.id).then(res => {
        this.addform.id = res.id;
        this.addform.urlPcCn = res.urlPcCn;
        this.addform.urlPcEn = res.urlPcEn;
        this.addform.urlMobileCn = res.urlMobileCn;
        this.addform.urlMobileEn = res.urlMobileEn;
        this.imageUrl2 = res.urlMobileCn;
        this.imageUrl1 = res.urlPcCn;
        this.imageUrl3 = res.urlPcEn;
        this.imageUrl4 = res.urlMobileEn;
        this.addform.nameCn = res.nameCn;
        this.addform.nameEn = res.nameEn;
        this.addform.descriptionCn = res.descriptionCn;
        this.addform.descriptionEn = res.descriptionEn;
        this.addform.mechanismCn = res.mechanismCn;
        this.addform.mechanismEn = res.mechanismEn;
        this.addform.seoKeywordCn = res.seoKeywordCn;
        this.addform.seoKeywordEn = res.seoKeywordEn;
        this.addform.seoDescriptionCn = res.seoDescriptionCn;
        this.addform.seoDescriptionEn = res.seoDescriptionEn;
        this.addform.line = res.line;
        this.addform.buttonLink = res.buttonLink;
      });
    },
    //修改关闭按钮
    changeClose(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible2 = false;
    },
    //修改确定按钮
    keep(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.imageUrl1 == "") {
            this.msgTip("warning", "必须上传PC端中文图片");
            return;
          }
          if (this.imageUrl2 == "") {
            this.msgTip("warning", "必须上传手机端中文图片");
            return;
          }
          if (this.imageUrl3 == "") {
            this.msgTip("warning", "必须上传PC端英文图片");
            return;
          }
          if (this.imageUrl4 == "") {
            this.msgTip("warning", "必须上传手机端英文图片");
            return;
          }
          this.isDisable = true;
          this.addform.urlPcCn = this.imageUrl1;
          this.addform.urlPcEn = this.imageUrl3;
          this.addform.urlMobileCn = this.imageUrl2;
          this.addform.urlMobileEn = this.imageUrl4;
          updateAward(this.addform).then(res => {
            if (res.success) {
              this.msgTip("success", "修改成功");
              this.empty();
              this.dialogFormVisible2 = false;
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
    //修改序号
    changeSeq(e) {
      const url = stopClick("/home/award/seq");
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
