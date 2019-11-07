<template>
  <div class="addlist">
    <el-form
      :model="form"
      :rules="addRules"
      ref="form"
      :status-icon="true"
      :show-message="false"
      hide-required-asterisk
      class="form"
    >
    <div class="commont">
      <el-form-item label="序号" :label-width="formLabelWidth">
            <el-input v-model="form.seq" autocomplete="off" type="text" maxlength="9"></el-input>
          </el-form-item>
          <el-form-item label="系列编号" :label-width="formLabelWidth" prop="serialNumber">
            <el-select v-model="form.serialNumber" placeholder="请选择" style="width:100%">
              <el-option
                v-for="(item,index) in serial"
                :key="index"
                :label="item.value+'_' + item.valueCn"
                :value="item.value"
              ></el-option>
            </el-select>
            <!-- <el-autocomplete v-model.trim="form.serialNumber"  @select="handleSelect"  :fetch-suggestions="querySearch" placeholder="请输入内容" style="width:100%"></el-autocomplete> -->
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="系列类型" :label-width="formLabelWidth" prop="type">
            <el-select v-model="form.type" placeholder="请选择" style="width:100%">
              <el-option
                v-for="(item,index) in type"
                :key="index"
                :label="item.name"
                :value="item.type"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="推荐类型" :label-width="formLabelWidth">
            <el-select v-model="form.recommendId" style="width:100%" multiple placeholder="请选择">
              <el-option
                v-for="item in recommend"
                :key="item.recommendId"
                :label="item.recommendName"
                :value="item.recommendId"
              ></el-option>
            </el-select>
          </el-form-item>
        </div>
          <div class="commont">
            <el-form-item label="按钮名称CN" :label-width="formLabelWidth" prop='buttonNameCn'>
            <el-input v-model="form.buttonNameCn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
           <el-form-item label="按钮名称EN" :label-width="formLabelWidth" prop='buttonNameEn'>
            <el-input v-model="form.buttonNameEn" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          </div>
           
          <div class="commont">
            <el-form-item label="按钮链接CN" :label-width="formLabelWidth" prop='buttonLinkCn'>
              <el-input v-model="form.buttonLinkCn" maxlength="255" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="按钮链接EN" :label-width="formLabelWidth" prop='buttonLinkEn'>
              <el-input v-model="form.buttonLinkEn" maxlength="255" autocomplete="off" type="text"></el-input>
            </el-form-item>
          </div>
         <div class="commont">
           <el-form-item label="首页系列描述CN" :label-width="formLabelWidth" prop="homeDescriptionCn">
            <el-input
              v-model="form.homeDescriptionCn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="首页系列描述EN" :label-width="formLabelWidth" prop="homeDescriptionEn">
            <el-input
              v-model="form.homeDescriptionEn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
         </div>
         <div class="commont">
            <el-form-item label="价格CN" :label-width="formLabelWidth">
            <el-input v-model="form.priceCn" maxlength="50" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="价格EN" :label-width="formLabelWidth">
            <el-input v-model="form.priceEn" maxlength="50" autocomplete="off" type="text"></el-input>
          </el-form-item>
         </div>
         <div class="commont">
            <el-form-item label="SEO标题CN" :label-width="formLabelWidth">
            <el-input
              v-model="form.seoTitleCn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO标题EN" :label-width="formLabelWidth">
            <el-input
              v-model="form.seoTitleEn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
         </div>
         <div class="commont">
            <el-form-item label="SEO关键字CN" :label-width="formLabelWidth">
            <el-input
              v-model="form.seoDescriptionCn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字EN" :label-width="formLabelWidth">
            <el-input
              v-model="form.seoDescriptionEn"
              maxlength="255"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
         </div>
         <div class="commont">
            <el-form-item label="SEO描述CN" :label-width="formLabelWidth">
            <el-input v-model="form.seoKeywordCn"  autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="SEO描述EN" :label-width="formLabelWidth">
            <el-input v-model="form.seoKeywordEn" autocomplete="off" type="text"></el-input>
          </el-form-item>
         </div>
         <div class="commont">
           <el-form-item label="推荐缩略图" :label-width="formLabelWidth">
            <el-upload
              accept=".png, .gif, .jpg, .jpeg"
              :action="uploadUrl"
              :headers="uplaod_data"
              list-type="picture-card"
              :on-success="handleUploadsSuccess"
              :limit="4"
            >
              <i class="el-icon-plus"/>
            </el-upload>
          </el-form-item>
          <el-form-item label="多图上传" :label-width="formLabelWidth">
            <el-upload
              accept=".png, .gif, .jpg, .jpeg"
              :action="uploadUrl"
              :headers="uplaod_data"
              list-type="picture-card"
              :on-success="handleUploadSuccess"
              :limit="4"
            >
              <i class="el-icon-plus"/>
            </el-upload>
          </el-form-item>
         </div>
          
    </el-form>

    <ul>
      <li>
        <h5>pc中文</h5>
        <el-upload
          class="avatar-uploader"
          action
          :http-request="to_upload_img"
          :show-file-list="false"
          style="display: none"
        >
          >
          <i class="el-icon-plus avatar-uploader-icon" id="imgInput"></i>
        </el-upload>
        <quill-editor
          v-model="form.contentPcCn"
          ref="myQuillEditor"
          :options="editorOption"
          show-word-limit
        ></quill-editor>
      </li>
      <li>
        <h5>pc英文</h5>
        <quill-editor
          v-model="form.contentPcEn"
          ref="myQuillEditor1"
          :options="editorOption"
          show-word-limit
        ></quill-editor>
      </li>
      <li>
        <h5>移动端中文</h5>
        <quill-editor
          v-model="form.contentMobileCn"
          ref="myQuillEditor2"
          :options="editorOption"
          show-word-limit
        ></quill-editor>
      </li>
      <li>
        <h5>移动端英文</h5>
        <quill-editor
          v-model="form.contentMobileEn"
          ref="myQuillEditor3"
          :options="editorOption"
          show-word-limit
        ></quill-editor>
      </li>
      <li>
        <h5>系列描述CN</h5>
        <quill-editor
          v-model="form.descriptionCn"
          ref="myQuillEditor4"
          :options="editorOption"
          show-word-limit
        ></quill-editor>
      </li>
      <li>
        <h5>系列描述EN</h5>
        <quill-editor
          v-model="form.descriptionEn"
          ref="myQuillEditor5"
          :options="editorOption"
          show-word-limit
        ></quill-editor>
      </li>
    </ul>
    <div class="btn">
      <el-button type="primary" @click="submit" :disabled="isDisable">提交</el-button>
    </div>
  </div>
</template>

<script>
import * as Quill from "quill";
import "quill/dist/quill.snow.css";
import { addList, recommend } from "../../api/partlist.js";
import { uploads } from "../../api/upload.js";
import { searchDiclist } from "../../api/dictionary.js";
// 标题
const titleConfig = [
  { Choice: ".ql-bold", title: "加粗" },
  { Choice: ".ql-italic", title: "斜体" },
  { Choice: ".ql-underline", title: "下划线" },
  { Choice: ".ql-header", title: "段落格式" },
  { Choice: ".ql-strike", title: "删除线" },
  { Choice: ".ql-blockquote", title: "块引用" },
  { Choice: ".ql-code", title: "插入代码" },
  { Choice: ".ql-code-block", title: "插入代码段" },
  { Choice: ".ql-font", title: "字体" },
  { Choice: ".ql-size", title: "字体大小" },
  { Choice: '.ql-list[value="ordered"]', title: "编号列表" },
  { Choice: '.ql-list[value="bullet"]', title: "项目列表" },
  { Choice: ".ql-direction", title: "文本方向" },
  { Choice: '.ql-header[value="1"]', title: "h1" },
  { Choice: '.ql-header[value="2"]', title: "h2" },
  { Choice: ".ql-align", title: "对齐方式" },
  { Choice: ".ql-color", title: "字体颜色" },
  { Choice: ".ql-background", title: "背景颜色" },
  { Choice: ".ql-image", title: "图像" },
  { Choice: ".ql-video", title: "视频" },
  { Choice: ".ql-link", title: "添加链接" },
  { Choice: ".ql-formula", title: "插入公式" },
  { Choice: ".ql-clean", title: "清除字体格式" },
  { Choice: '.ql-script[value="sub"]', title: "下标" },
  { Choice: '.ql-script[value="super"]', title: "上标" },
  { Choice: '.ql-indent[value="-1"]', title: "向左缩进" },
  { Choice: '.ql-indent[value="+1"]', title: "向右缩进" },
  { Choice: ".ql-header .ql-picker-label", title: "标题大小" },
  { Choice: '.ql-header .ql-picker-item[data-value="1"]', title: "标题一" },
  { Choice: '.ql-header .ql-picker-item[data-value="2"]', title: "标题二" },
  { Choice: '.ql-header .ql-picker-item[data-value="3"]', title: "标题三" },
  { Choice: '.ql-header .ql-picker-item[data-value="4"]', title: "标题四" },
  { Choice: '.ql-header .ql-picker-item[data-value="5"]', title: "标题五" },
  { Choice: '.ql-header .ql-picker-item[data-value="6"]', title: "标题六" },
  { Choice: ".ql-header .ql-picker-item:last-child", title: "标准" },
  { Choice: '.ql-size .ql-picker-item[data-value="small"]', title: "小号" },
  { Choice: '.ql-size .ql-picker-item[data-value="large"]', title: "大号" },
  { Choice: '.ql-size .ql-picker-item[data-value="huge"]', title: "超大号" },
  { Choice: ".ql-size .ql-picker-item:nth-child(2)", title: "标准" },
  { Choice: ".ql-align .ql-picker-item:first-child", title: "居左对齐" },
  {
    Choice: '.ql-align .ql-picker-item[data-value="center"]',
    title: "居中对齐"
  },
  {
    Choice: '.ql-align .ql-picker-item[data-value="right"]',
    title: "居右对齐"
  },
  {
    Choice: '.ql-align .ql-picker-item[data-value="justify"]',
    title: "两端对齐"
  }
];
export default {
  data() {
    return {
      formLabelWidth: "120px",
      editorOption: {},
      restaurants: [],
      state: "",
      timeout: null,
      isDisable: false,
      form: {
        contentMobileCn: "",
        contentMobileEn: "",
        contentPcCn: "",
        contentPcEn: "",
        descriptionCn: "",
        descriptionEn: "",
        line: "2",
        mutiUrl: [],
        priceCn: "",
        priceEn: "",
        seq: "",
        serialNumber: "",
        type: "",
        homeDescriptionCn: "",
        homeDescriptionEn: "",
        recommendId: "",
        recommendUrl: [],
        buttonNameCn:'',
        buttonNameEn:'',
        buttonLinkCn:'',
        buttonLinkEn:'',
         seoTitleCn: "",
        seoTitleEn: "",
        seoKeywordCn: "",
        seoKeywordEn: "",
        seoDescriptionCn: "",
        seoDescriptionEn: ""
      },
      addRules: {
        serialNumber: [{ required: true, trigger: "change" }],
        type: [{ required: true, trigger: "blur" }],
        homeDescriptionCn: [{ required: true, trigger: "blur" }],
        homeDescriptionEn: [{ required: true, trigger: "blur" }],
        buttonNameCn:[{ required: true, trigger: "blur" }],
        buttonNameEn:[{ required: true, trigger: "blur" }],
        buttonLinkCn:[{ required: true, trigger: "blur" }],
        buttonLinkEn:[{ required: true, trigger: "blur" }]
      },
      options: [
        {
          name: "上线",
          type: "0"
        },
        {
          name: "下线",
          type: "1"
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
      editorOption: {
        placeholder: "请输入文本信息..."
      },
      addRange: [],
      addRange1: [],
      addRange2: [],
      addRange3: [],
      addRange4: [],
      addRange5: [],
      Rangeflag: false,
      Rangeflag1: false,
      Rangeflag2: false,
      Rangeflag3: false,
      Rangeflag4: false,
      Rangeflag5: false,
      forms: {
        name: "",
        pageNo: 1,
        pageSize: 100,
        sortField: "",
        sortRules: "",
        valueMeaning: ""
      },
      rules: [],
      restaurants: [],
      imageUrl1: "",
      multiple: true,
      uploadUrl: `${process.env.VUE_APP_BASE_API}cms/picture/upload`,
      uplaod_data: {
        "x-access-token": window.localStorage.getItem("token")
      },
      serial: [],
      recommend: []
    };
  },
  created() {
    this.loadAll();
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
            value: v.value
          };
        });
        this.serial = this.newList;
      }
    });

    recommend().then(res => {
      if (res.success) {
        this.recommend = res.voList;
      }
    });
  },
  mounted() {
    // 为图片ICON绑定事件  getModule 为编辑器的内部属性
    this.$refs.myQuillEditor.quill
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
    this.$refs.myQuillEditor1.quill
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
    this.$refs.myQuillEditor2.quill
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
    this.$refs.myQuillEditor3.quill
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
    this.$refs.myQuillEditor4.quill
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
    this.$refs.myQuillEditor5.quill
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
      autotip:{
			      document.getElementsByClassName('ql-editor')[0].dataset.placeholder=''
			      for(let item of titleConfig){
			          let tip = document.querySelector('.quill-editor '+ item.Choice)
			          if (!tip) continue
			          tip.setAttribute('title',item.title)
			      }
			  }
  },
  methods: {
    //上传之前的钩子
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
      let size = file.size / 1024 / 1024 / 2;
      if (size > 2) {
        this.msgTip("warning", "图片大小必须小于2M");
        return;
      }
      let fd = new FormData();
      fd.append("file", file); //传文件
      uploads(fd).then(res => {
        this.imageUrl1 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
    },
    handleUploadSuccess(res, file) {
      this.form.mutiUrl.push(res.voT.downloadUrl);
    },
    handleUploadsSuccess(res, file) {
      this.form.recommendUrl.push(res.voT.downloadUrl);
    },
    loadAll() {
      let newList = [];
      searchDiclist({
        name: "",
        pageNo: 1,
        pageSize: 100,
        sortField: "",
        sortRules: "",
        valueMeaning: ""
      }).then(res => {
        if (res.success) {
          res.voList.map(partItem => {
            if (partItem.name == "PART_SERIES") {
              newList.push(partItem);
              return newList;
            }
          });
          this.restaurants = newList;
        } else {
          this.restaurants = [];
        }
      });
    },
    imgHandler(state) {
      this.addRange = this.$refs.myQuillEditor.quill.getSelection();
      if (this.addRange != "" && this.addRange != null) {
        this.Rangeflag = true;
      }
      this.addRange1 = this.$refs.myQuillEditor1.quill.getSelection();
      if (this.addRange1 != "" && this.addRange1 != null) {
        this.Rangeflag1 = true;
      }
      this.addRange2 = this.$refs.myQuillEditor2.quill.getSelection();
      if (this.addRange2 != "" && this.addRange2 != null) {
        this.Rangeflag2 = true;
      }
      this.addRange3 = this.$refs.myQuillEditor3.quill.getSelection();
      if (this.addRange3 != "" && this.addRange3 != null) {
        this.Rangeflag3 = true;
      }
      this.addRange4 = this.$refs.myQuillEditor4.quill.getSelection();
      if (this.addRange4 != "" && this.addRange4 != null) {
        this.Rangeflag4 = true;
      }
      this.addRange5 = this.$refs.myQuillEditor5.quill.getSelection();
      if (this.addRange5 != "" && this.addRange5 != null) {
        this.Rangeflag5 = true;
      }
      if (state) {
        let fileInput = document.getElementById("imgInput");
        fileInput.click(); // 加一个触发事件
      }
    },
    to_upload_img(formdata) {
      return new Promise((resolve, reject) => {
        let fd = new FormData();
        fd.append("file", formdata.file);
        uploads(fd).then(res => {
          if (this.Rangeflag) {
            this.addRange = this.$refs.myQuillEditor.quill.getSelection();
            this.$refs.myQuillEditor.quill.insertEmbed(
              this.addRange !== null ? this.addRange.index : 0,
              "image",
              res.voT.downloadUrl,
              Quill.sources.USER
            ); // 调用编辑器的 insertEmbed 方法，插入URL
            this.Rangeflag = false;
          }
          if (this.Rangeflag1) {
            this.addRange1 = this.$refs.myQuillEditor1.quill.getSelection();
            this.$refs.myQuillEditor1.quill.insertEmbed(
              this.addRange1 !== null ? this.addRange1.index : 0,
              "image",
              res.voT.downloadUrl,
              Quill.sources.USER
            ); // 调用编辑器的 insertEmbed 方法，插入URL
            this.Rangeflag1 = false;
          }
          if (this.Rangeflag2) {
            this.addRange2 = this.$refs.myQuillEditor2.quill.getSelection();
            this.$refs.myQuillEditor2.quill.insertEmbed(
              this.addRange2 !== null ? this.addRange2.index : 0,
              "image",
              res.voT.downloadUrl,
              Quill.sources.USER
            ); // 调用编辑器的 insertEmbed 方法，插入URL
            this.Rangeflag2 = false;
          }
          if (this.Rangeflag3) {
            this.addRange3 = this.$refs.myQuillEditor3.quill.getSelection();
            this.$refs.myQuillEditor3.quill.insertEmbed(
              this.addRange3 !== null ? this.addRange3.index : 0,
              "image",
              res.voT.downloadUrl,
              Quill.sources.USER
            ); // 调用编辑器的 insertEmbed 方法，插入URL
            this.Rangeflag3 = false;
          }
          if (this.Rangeflag4) {
            this.addRange4 = this.$refs.myQuillEditor4.quill.getSelection();
            this.$refs.myQuillEditor4.quill.insertEmbed(
              this.addRange4 !== null ? this.addRange4.index : 0,
              "image",
              res.voT.downloadUrl,
              Quill.sources.USER
            ); // 调用编辑器的 insertEmbed 方法，插入URL
            this.Rangeflag4 = false;
          }
          if (this.Rangeflag5) {
            this.addRange5 = this.$refs.myQuillEditor5.quill.getSelection();
            this.$refs.myQuillEditor5.quill.insertEmbed(
              this.addRange5 !== null ? this.addRange5.index : 0,
              "image",
              res.voT.downloadUrl,
              Quill.sources.USER
            ); // 调用编辑器的 insertEmbed 方法，插入URL
            this.Rangeflag5 = false;
          }
        });
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
    submit() {
      if (!/(^[1-9]\d*$)/.test(this.form.seq) && this.form.seq !== "") {
        this.msgTip("warning", "序号只能为正整数");
        return false;
      }
      if (this.form.contentPcCn == "" || this.form.contentPcEn == "") {
        this.msgTip("warning", "pc端不能为空");
      } else if (
        this.form.contentMobileCn == "" ||
        this.form.contentMobileEn == ""
      ) {
        this.msgTip("warning", "移动端不能为空");
      } else if (this.form.mutiUrl == "") {
        this.msgTip("warning", "多图上传不能为空");
      } else if (this.form.recommendUrl == "") {
        this.msgTip("warning", "推荐缩略图不能为空");
      } else if (
        this.form.descriptionCn == "" ||
        this.form.descriptionEn == ""
      ) {
        this.msgTip("warning", "系列描述不能为空");
      } else {
        this.$refs.form.validate(valid => {
          if (valid) {
            // 表单验证通过之后的操作
            // this.isRepeat = true;
            this.isDisable = false;
            this.form.mutiUrl = this.form.mutiUrl.toString();
            this.form.recommendUrl = this.form.recommendUrl.toString();
            this.form.recommendId = this.form.recommendId.toString();
            this.form.serialNumber = this.form.serialNumber.split("_")[0];
            addList(this.form).then(res => {
              if (res.success) {
                this.msgTip("success", "添加成功");
                this.$store
                  .dispatch("tagsView/delView", this.$route)
                  .then(() => {
                    this.$nextTick(() => {
                      this.$router.replace({
                        path: "/parts/list"
                      });
                    });
                  });
                  this.recommend = []
              } else {
                this.msgTip("error", res.errorMsg);
                this.form.recommendId = []
                // this.isRepeat = false;
                // this.isDisable = true;

              }
            });
          } else {
            this.msgTip("warning", "请填写完整");
            return false;
          }
        });
      }
    }
  }
};
</script>

<style scoped lang='scss'>
.addlist {
  margin-top: 20px;
  padding-right: 20px;
  padding-left: 20px;
  ol {
    display: flex;
    padding-left: 0;
    li {
      width: 49%;
      margin-left: 20px;
      &:nth-child(1) {
        margin-left: 0;
      }
    }
  }
  ul {
    padding-right: 20px;
    li {
      width: 100%;
    }
  }
  //   .avatar-uploader .el-upload {

  //   border-radius: 6px;
  //   cursor: pointer;
  //   position: relative;
  //   overflow: hidden;
  // }
  // .avatar-uploader .el-upload:hover {
  //   border-color: #409eff;
  // }
  // .avatar-uploader-icon {
  //   font-size: 28px;
  //   color: #8c939d;
  //   width: 100px;
  //   height: 100px;
  //   line-height: 100px;
  //   text-align: center;
  //   border: 1px dashed #d9d9d9;
  // }
  // .avatar {
  //   width: 100px;
  //   height: 100px;
  //   display: block;
  // }
  .el-scrollbar__wrap .el-select-dropdown__item span {
  display: inline-block;
  width: 270px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
}
</style>
