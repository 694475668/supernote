<template>
  <div class="changeblog">
    <el-form
      :model="addblog"
      :rules="rules"
      ref="addblog"
      :status-icon="true"
      hide-required-asterisk
    >
      <div class="commont">
        <el-form-item label="显示浏览数" :label-width="formLabelWidth">
          <el-input v-model="addblog.viewCount" autocomplete="off" type="text" maxlength="9"></el-input>
        </el-form-item>
        <el-form-item label="浏览数" :label-width="formLabelWidth">
          <el-input
            v-model="addblog.viewCountReal"
            disabled="true"
            autocomplete="off"
            type="text"
            maxlength="9"
          ></el-input>
        </el-form-item>
      </div>
      <div class="commont">
        <el-form-item label="显示评论数" :label-width="formLabelWidth">
          <el-input v-model="addblog.commentsCount" autocomplete="off" type="text" maxlength="9"></el-input>
        </el-form-item>
        <el-form-item label="评论数" :label-width="formLabelWidth">
          <el-input
            v-model="addblog.commentsCountReal"
            autocomplete="off"
            type="text"
            disabled="true"
            maxlength="9"
          ></el-input>
        </el-form-item>
      </div>
      <div class="commont">
        <el-form-item label="显示点赞数" :label-width="formLabelWidth">
          <el-input v-model="addblog.thumbsCount" autocomplete="off" type="text" maxlength="9"></el-input>
        </el-form-item>
        <el-form-item label="点赞数" :label-width="formLabelWidth">
          <el-input
            v-model="addblog.thumbsCountReal"
            autocomplete="off"
            disabled="true"
            type="text"
            maxlength="9"
          ></el-input>
        </el-form-item>
      </div>
      <div class="commont">
        <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
          <el-input v-model="addblog.title" autocomplete="off" type="text" maxlength="50"></el-input>
        </el-form-item>
        <el-form-item label="作者" :label-width="formLabelWidth" prop="author">
          <el-input v-model="addblog.author" autocomplete="off" type="text" maxlength="40"></el-input>
        </el-form-item>
      </div>
      <div class="commont">
        <el-form-item label="博客版本" :label-width="formLabelWidth" prop="authorCn">
          <el-radio v-model="addblog.language" label="ZH-CN">中文版</el-radio>
          <el-radio v-model="addblog.language" label="EN">英文版</el-radio>
        </el-form-item>
        <el-form-item label="推荐标志" :label-width="formLabelWidth">
        <el-radio v-model="addblog.recommendFlag" label="Y">推荐</el-radio>
        <el-radio v-model="addblog.recommendFlag" label="N">不推荐</el-radio>
      </el-form-item>
      </div>
      <div class="commont">
        <el-form-item label="pc图片" :label-width="formLabelWidth">
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
        <el-form-item label="移动端图片" :label-width="formLabelWidth">
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
          <el-form-item label="SEO标题" :label-width="formLabelWidth">
            <el-input v-model="addblog.seoTitle" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字" :label-width="formLabelWidth">
            <el-input v-model="addblog.seoKeyword" autocomplete="off" type="text" maxlength="255"></el-input>
          </el-form-item>
        </div>
        <el-form-item label="SEO描述" :label-width="formLabelWidth">
          <el-input
            v-model="addblog.seoDescription"
            autocomplete="off"
            type="text"
          ></el-input>
        </el-form-item>
      <ul>
        <li>
          <h5>博客内容</h5>
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
            v-model="addblog.content"
            ref="myQuillEditor"
            :options="editorOption"
            show-word-limit
          ></quill-editor>
        </li>
      </ul>
    </el-form>
    <div class="put" style="heigh">
      <el-button @click="back('addblog')">返回</el-button>
      <el-button type="primary" @click="save('addblog')">提交</el-button>
    </div>
  </div>
</template>

<script>
import * as Quill from "quill";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import { upload, getBlogById, updateBlog } from "../../api/blog.js";
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
      rules: {
        seq: [{ required: true, message: "序号不能为空", trigger: "blur" }],
        title: [{ required: true, message: "标题不能为空", trigger: "blur" }],
        author: [{ required: true, message: "作者不能为空", trigger: "blur" }]
      },
      imageUrl2: "",
      imageUrl1: "",
      addRange: [],
      Rangeflag: false,
      formLabelWidth: "200px",
      editorOption: {
        placeholder: "请输入文本信息..."
      },
      addblog: {
        id: "",
        seq: "",
        language: "",
        author: "",
        title: "",
        content: "",
        urlPc: "",
        urlMobile: "",
        seoTitle:'',
        seoKeyword: "",
        seoDescription: "",
        line: "",
        viewCount: "",
        commentsCount: "",
        thumbsCount: "",
        viewCountReal: "",
        commentsCountReal: "",
        thumbsCountReal: "",
        recommendFlag: ""
      },
      value: "",
      formLabelWidth: "200px",
      editorOption: {}
    };
  },
  created() {
    this.getParams();
  },
  methods: {
    onEditorBlur(v) {},
    onEditorFocus(v) {},
    onEditorChange(v) {},
    onEditorBlur1(v) {},
    onEditorFocus1(v) {},
    onEditorChange1(v) {},
    getParams() {
      // 将数据放在当前组件的数据内
      this.addblog.id = this.$route.query.id;
      getBlogById(this.addblog.id).then(res => {
        this.addblog.title = res.title;
        this.addblog.recommendFlag = res.recommendFlag;
        this.addblog.author = res.author;
        this.imageUrl1 = res.urlPc;
        this.imageUrl2 = res.urlMobile;
        this.addblog.seoTitle = res.seoTitle;
        this.addblog.seoKeyword = res.seoKeyword;
        this.addblog.seoDescription = res.seoDescription;
        this.addblog.viewCount = res.viewCount;
        this.addblog.line = res.line;
        this.addblog.language = res.language;
        this.addblog.commentsCount = res.commentsCount;
        this.addblog.thumbsCount = res.thumbsCount;
        this.addblog.viewCountReal = res.viewCountReal;
        this.addblog.commentsCountReal = res.commentsCountReal;
        this.addblog.thumbsCountReal = res.thumbsCountReal;
        this.addblog.content = res.content;
      });
    },
    // 点击图片ICON触发事件
    imgHandler(state) {
      this.addRange = this.$refs.myQuillEditor.quill.getSelection();
      if (this.addRange != "" && this.addRange != null) {
        this.Rangeflag = true;
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
        upload(fd).then(res => {
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
        this.msgTip("warning", "请上传格式为png, gif, jpg, jpeg的图片");
        return;
      }
      let fd = new FormData();
      fd.append("file", file); //传文件
      upload(fd).then(res => {
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
      let fd = new FormData();
      fd.append("file", file); //传文件
      upload(fd).then(res => {
        this.imageUrl1 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
    },
    back(formName) {
      this.$refs[formName].resetFields();
      this.$store.dispatch("tagsView/delView", this.$route).then(() => {
        this.$nextTick(() => {
          this.$router.replace({
            path: "/blog/blogList"
          });
        });
      });
    },
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    save(formName) {
      this.addblog.urlPc = this.imageUrl1;
      this.addblog.urlMobile = this.imageUrl2;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.addblog.viewCount != "") {
            if (!/^[0-9]{1,}[\d]*$/.test(this.addblog.viewCount)) {
              this.msgTip("warning", "浏览数只能为正整数");
              return;
            }
          }
          if (this.addblog.commentsCount != "") {
            if (!/^[0-9]{1,}[\d]*$/.test(this.addblog.commentsCount)) {
              this.msgTip("warning", "评论数只能为正整数");
              return;
            }
          }
          if (this.addblog.thumbsCount != "") {
            if (!/^[0-9]{1,}[\d]*$/.test(this.addblog.thumbsCount)) {
              this.msgTip("warning", "点赞数只能为正整数");
              return;
            }
          }
          if (this.imageUrl1 == "") {
            this.msgTip("warning", "必须上传PC端图片");
            return;
          }
          if (this.imageUrl2 == "") {
            this.msgTip("warning", "必须上传手机端图片");
            return;
          }
          updateBlog(this.addblog).then(res => {
            if (res.success) {
              this.msgTip("success", "操作成功");
              this.$refs[formName].resetFields();
              this.$store.dispatch("tagsView/delView", this.$route).then(() => {
                this.$nextTick(() => {
                  this.$router.replace({
                    path: "/blog/blogList"
                  });
                });
              });
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
            }
          });
        }
      });
    }
  },
  mounted() {
    // 为图片ICON绑定事件  getModule 为编辑器的内部属性
    this.$refs.myQuillEditor.quill
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
    autotip: {
      document.getElementsByClassName("ql-editor")[0].dataset.placeholder = "";
      for (let item of titleConfig) {
        let tip = document.querySelector(".quill-editor " + item.Choice);
        if (!tip) continue;
        tip.setAttribute("title", item.title);
      }
    }
  },
  watch: {
    // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
    $route: "getParams"
  }
};
</script>

<style scoped lang='scss'>
.changeblog {
  margin-top: 20px;
  padding-right: 20px;
  .commont {
    display: flex;
    justify-content: space-between;
  }
  .commont .el-form-item {
    width: 50%;
    .el-input {
      width: 100%;
    }
  }
  ul {
    padding-right: 20px;
    li {
      width: 100%;
    }
  }
}
.put {
  width: 100%;
  text-align: center;
  padding-bottom: 20px;
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
