<template>
  <div class="addpro">
    <div class="addhead">
      <el-form
        :model="form"
        :rules="formRules"
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
          <el-form-item label="产品型号" :label-width="formLabelWidth" prop="type">
            <el-select v-model="form.type" placeholder="请选择" style="width:100%">
              <el-option
                v-for="(item,index) in type"
                :key="index"
                :label="item.valueCn"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="详情名称CN" :label-width="formLabelWidth" prop="titleCn">
            <el-input v-model="form.titleCn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="详情名称EN" :label-width="formLabelWidth" prop="titleEn">
            <el-input v-model="form.titleEn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
        </div>
          <!-- <div class="commont">
          <el-form-item label="SEO标题CN" :label-width="formLabelWidth">
            <el-input v-model="form.seoTitleCn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="SEO标题EN" :label-width="formLabelWidth">
            <el-input v-model="form.seoTitleEn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO关键字CN" :label-width="formLabelWidth">
            <el-input v-model="form.seoKeywordCn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字EN" :label-width="formLabelWidth">
            <el-input v-model="form.seoKeywordEn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
        </div>
        <div class="commont">
          <el-form-item label="SEO描述CN" :label-width="formLabelWidth">
            <el-input
              v-model="form.seoDescriptionCn"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
          <el-form-item label="SEO描述EN" :label-width="formLabelWidth">
            <el-input
              v-model="form.seoDescriptionEn"
              autocomplete="off"
              type="text"
            ></el-input>
          </el-form-item>
        </div> -->
        <el-form-item label="产品图片(353*482)" :label-width="formLabelWidth">
          <el-upload
            class="avatar-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :before-upload="beforeUploadPicture"
          >
            <img v-if="imageUrl1" :src="imageUrl1" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        
        
      </el-form>
      <ul>
        <li>
          <h5>PC描述CN</h5>
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
            v-model="form.descriptionPcCn"
            ref="myQuillEditor"
            :options="editorOption"
            show-word-limit
          ></quill-editor>
        </li>
        <li>
          <h5>PC英文描述</h5>
          <quill-editor
            v-model="form.descriptionPcEn"
            ref="myQuillEditor1"
            :options="editorOption"
            show-word-limit
          ></quill-editor>
        </li>
        <li>
          <h5>移动端描述CN</h5>
          <quill-editor
            v-model="form.descriptionMobileCn"
            ref="myQuillEditor2"
            :options="editorOption"
            show-word-limit
          ></quill-editor>
        </li>
        <li>
          <h5>移动端描述EN</h5>
          <quill-editor
            v-model="form.descriptionMobileEn"
            ref="myQuillEditor3"
            :options="editorOption"
            show-word-limit
          ></quill-editor>
        </li>
      </ul>
    </div>
    <div class="btn">
      <el-button type="primary" @click="submit" :disabled="isDisable">提交</el-button>
    </div>
  </div>
</template>

<script>
import * as Quill from "quill";
import { addList } from "../../api/productMess.js";
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
  props: {
    color: {
      type: String,
      default: "#1890ff"
    }
  },
  data() {
    return {
      content: "",
      value: "",
      type: [],
      form: {
        descriptionMobileCn: "",
        descriptionMobileEn: "",
        descriptionPcCn: "",
        descriptionPcEn: "",
        seq: "",
        titleCn: "",
        titleEn: "",
        type: "",
        productPictureUrl: "",
        // seoTitleCn: "",
        // seoTitleEn: "",
        // seoKeywordCn: "",
        // seoKeywordEn: "",
        // seoDescriptionCn: "",
        // seoDescriptionEn: ""
      },
      formLabelWidth: "120px",
      formRules: {
        seq: [{ required: true, trigger: "blur" }],
        titleCn: [{ required: true, trigger: "blur" }],
        titleEn: [{ required: true, trigger: "blur" }],
        type: [{ required: true, trigger: "change" }]
      },
      isDisable: false,
      editorOption: {
        placeholder: "请输入文本信息..."
      },
      addRange: [],
      addRange1: [],
      addRange2: [],
      addRange3: [],
      Rangeflag: false,
      Rangeflag1: false,
      Rangeflag2: false,
      Rangeflag3: false,
      imageUrl1: "",
      dialogVisible: false,
      listObj: {},
      fileList: []
    };
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
    autotip: {
      document.getElementsByClassName("ql-editor")[0].dataset.placeholder = "";
      for (let item of titleConfig) {
        let tip = document.querySelector(".quill-editor " + item.Choice);
        if (!tip) continue;
        tip.setAttribute("title", item.title);
      }
    }
  },

  methods: {
    checkAllSuccess() {
      return Object.keys(this.listObj).every(
        item => this.listObj[item].hasSuccess
      );
    },
    handleSubmit() {
      const arr = Object.keys(this.listObj).map(v => this.listObj[v]);
      if (!this.checkAllSuccess()) {
        this.$message(
          "Please wait for all images to be uploaded successfully. If there is a network problem, please refresh the page and upload again!"
        );
        return;
      }
      this.$emit("successCBK", arr);
      this.listObj = {};
      this.fileList = [];
      this.dialogVisible = false;
    },
    handleSuccess(response, file) {
      const uid = file.uid;
      const objKeyArr = Object.keys(this.listObj);
      for (let i = 0, len = objKeyArr.length; i < len; i++) {
        if (this.listObj[objKeyArr[i]].uid === uid) {
          this.listObj[objKeyArr[i]].url = response.files.file;
          this.listObj[objKeyArr[i]].hasSuccess = true;
          return;
        }
      }
    },
    handleRemove(file) {
      const uid = file.uid;
      const objKeyArr = Object.keys(this.listObj);
      for (let i = 0, len = objKeyArr.length; i < len; i++) {
        if (this.listObj[objKeyArr[i]].uid === uid) {
          delete this.listObj[objKeyArr[i]];
          return;
        }
      }
    },
    beforeUpload(file) {
      const _self = this;
      const _URL = window.URL || window.webkitURL;
      const fileName = file.uid;
      this.listObj[fileName] = {};
      return new Promise((resolve, reject) => {
        const img = new Image();
        img.src = _URL.createObjectURL(file);
        img.onload = function() {
          _self.listObj[fileName] = {
            hasSuccess: false,
            uid: file.uid,
            width: this.width,
            height: this.height
          };
        };
        resolve(true);
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
            this.addRange1 = this.$refs.myQuillEditor2.quill.getSelection();
            this.$refs.myQuillEditor2.quill.insertEmbed(
              this.addRange2 !== null ? this.addRange2.index : 0,
              "image",
              res.voT.downloadUrl,
              Quill.sources.USER
            ); // 调用编辑器的 insertEmbed 方法，插入URL
            this.Rangeflag2 = false;
          }
          if (this.Rangeflag3) {
            this.addRange1 = this.$refs.myQuillEditor3.quill.getSelection();
            this.$refs.myQuillEditor3.quill.insertEmbed(
              this.addRange3 !== null ? this.addRange3.index : 0,
              "image",
              res.voT.downloadUrl,
              Quill.sources.USER
            ); // 调用编辑器的 insertEmbed 方法，插入URL
            this.Rangeflag3 = false;
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
    //图片上传
    beforeUploadPicture(file) {
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
      uploads(fd).then(res => {
        this.imageUrl1 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
    },
    //提交
    submit() {
      this.form.productPictureUrl = this.imageUrl1;
      if (!/(^[1-9]\d*$)/.test(this.form.seq) && this.form.seq !== "") {
        this.msgTip("warning", "序号只能为正整数");
        return false;
      }
      if (
        this.form.descriptionMobileCn == "" ||
        this.form.descriptionMobileEn == ""
      ) {
        this.msgTip("warning", "移动端描述不能为空");
      } else if (
        this.form.descriptionPcCn == "" ||
        this.form.descriptionPcEn == ""
      ) {
        this.msgTip("warning", "pc端描述不能为空");
      } else if (this.form.productPictureUrl == "") {
        this.msgTip("warning", "产品图片不能为空");
      } else {
        this.$refs.form.validate(valid => {
          if (valid) {
            // 表单验证通过之后的操作
            // this.isRepeat = true;
            this.isDisable = true;
            addList(this.form).then(res => {
              if (res.success) {
                this.msgTip("success", "添加成功");
                this.$store
                  .dispatch("tagsView/delView", this.$route)
                  .then(() => {
                    this.$nextTick(() => {
                      this.$router.replace({
                        path: "/product/summarize"
                      });
                    });
                  });
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
      }
    }
  }
};
</script>

<style scoped lang="scss">
.addhead {
  margin-top: 20px;
  padding-right: 20px;
  ul {
    padding-right: 20px;
    li {
      width: 100%;
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
