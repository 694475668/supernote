<template>
  <div class="addabout">
    <div class="addhead">
      <el-form
        :model="form"
        :rules="formRules"
        ref="form"
        :status-icon="true"
        hide-required-asterisk
        class="form"
      >
        
            <el-form-item label="序号" :label-width="formLabelWidth" :show-message="false">
              <el-input v-model="form.seq" autocomplete="off" type="text" maxlength="9"></el-input>
            </el-form-item>
            <el-form-item
              label="标题CN"
              :label-width="formLabelWidth"
              prop="titleCn"
              :show-message="false"
            >
              <el-input v-model="form.titleCn" autocomplete="off" type="text" maxlength="50"></el-input>
            </el-form-item>
            <el-form-item
              label="标题EN"
              :label-width="formLabelWidth"
              prop="titleEn"
              :show-message="false"
            >
              <el-input v-model="form.titleEn" autocomplete="off" type="text" maxlength="50"></el-input>
            </el-form-item>
      </el-form>
      <ul>
        <li>
          <h5>PC端描述CN</h5>
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
            ref="myQuillEditor1"
            :options="editorOption"
            prop="descriptionPcCn"
            @change="alertValue($event,strValue,decimalNum)"
            show-word-limit
          ></quill-editor>
        </li>
        <li>
          <h5>PC端描述EN</h5>
          <quill-editor
            v-model="form.descriptionPcEn"
            ref="myQuillEditor2"
            :options="editorOption"
            prop="descriptionPcEn"
            show-word-limit
          ></quill-editor>
        </li>
        <li>
          <h5>移动端描述CN</h5>
          <quill-editor
            v-model="form.descriptionMobileCn"
            ref="myQuillEditor3"
            :options="editorOption"
            prop="descriptionMobileCn"
            show-word-limit
          ></quill-editor>
        </li>
        <li>
          <h5>移动端描述EN</h5>
          <quill-editor
            v-model="form.descriptionMobileEn"
            ref="myQuillEditor4"
            :options="editorOption"
            prop="descriptionMobileEn"
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
import { quillEditor } from "vue-quill-editor";
import * as Quill from "quill";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import { addAbout, upload } from "../../api/abouts.js";
 // 标题
       const titleConfig=[
		    {Choice:'.ql-bold',title:'加粗'},
		    {Choice:'.ql-italic',title:'斜体'},
		    {Choice:'.ql-underline',title:'下划线'},
		    {Choice:'.ql-header',title:'段落格式'},
		    {Choice:'.ql-strike',title:'删除线'},
		    {Choice:'.ql-blockquote',title:'块引用'},
		    {Choice:'.ql-code',title:'插入代码'},
		    {Choice:'.ql-code-block',title:'插入代码段'},
		    {Choice:'.ql-font',title:'字体'},
		    {Choice:'.ql-size',title:'字体大小'},
		    {Choice:'.ql-list[value="ordered"]',title:'编号列表'},
		    {Choice:'.ql-list[value="bullet"]',title:'项目列表'},
		    {Choice:'.ql-direction',title:'文本方向'},
		    {Choice:'.ql-header[value="1"]',title:'h1'},
		    {Choice:'.ql-header[value="2"]',title:'h2'},
		    {Choice:'.ql-align',title:'对齐方式'},
		    {Choice:'.ql-color',title:'字体颜色'},
		    {Choice:'.ql-background',title:'背景颜色'},
		    {Choice:'.ql-image',title:'图像'},
		    {Choice:'.ql-video',title:'视频'},
		    {Choice:'.ql-link',title:'添加链接'},
		    {Choice:'.ql-formula',title:'插入公式'},
		    {Choice:'.ql-clean',title:'清除字体格式'},
		    {Choice:'.ql-script[value="sub"]',title:'下标'},
		    {Choice:'.ql-script[value="super"]',title:'上标'},
		    {Choice:'.ql-indent[value="-1"]',title:'向左缩进'},
		    {Choice:'.ql-indent[value="+1"]',title:'向右缩进'},
		    {Choice:'.ql-header .ql-picker-label',title:'标题大小'},
		    {Choice:'.ql-header .ql-picker-item[data-value="1"]',title:'标题一'},
		    {Choice:'.ql-header .ql-picker-item[data-value="2"]',title:'标题二'},
		    {Choice:'.ql-header .ql-picker-item[data-value="3"]',title:'标题三'},
		    {Choice:'.ql-header .ql-picker-item[data-value="4"]',title:'标题四'},
		    {Choice:'.ql-header .ql-picker-item[data-value="5"]',title:'标题五'},
		    {Choice:'.ql-header .ql-picker-item[data-value="6"]',title:'标题六'},
		    {Choice:'.ql-header .ql-picker-item:last-child',title:'标准'},
		    {Choice:'.ql-size .ql-picker-item[data-value="small"]',title:'小号'},
		    {Choice:'.ql-size .ql-picker-item[data-value="large"]',title:'大号'},
		    {Choice:'.ql-size .ql-picker-item[data-value="huge"]',title:'超大号'},
		    {Choice:'.ql-size .ql-picker-item:nth-child(2)',title:'标准'},
		    {Choice:'.ql-align .ql-picker-item:first-child',title:'居左对齐'},
		    {Choice:'.ql-align .ql-picker-item[data-value="center"]',title:'居中对齐'},
		    {Choice:'.ql-align .ql-picker-item[data-value="right"]',title:'居右对齐'},
		    {Choice:'.ql-align .ql-picker-item[data-value="justify"]',title:'两端对齐'}
		];
export default {
  components: {
    quillEditor
  },
  data() {
    return {
      value: "",
      form: {
        descriptionMobileCn: "",
        descriptionMobileEn: "",
        descriptionPcCn: "",
        descriptionPcEn: "",
        seq: "",
        titleCn: "",
        titleEn: "",
        // seoDescriptionCn: "",
        // seoDescriptionEn: "",
        // seoKeywordCn: "",
        // seoKeywordEn: ""
      },
      formLabelWidth: "120px",
      formRules: {
        seq: [{ required: true, trigger: "blur" }],
        titleCn: [{ required: true, trigger: "blur" }],
        titleEn: [{ required: true, trigger: "blur" }],
        descriptionMobileCn: [{ required: true, trigger: "blur" }],
        descriptionMobileEn: [{ required: true, trigger: "blur" }],
        descriptionPcCn: [{ required: true, trigger: "blur" }],
        descriptionPcEn: [{ required: true, trigger: "blur" }]
      },
      isDisable: false,
      editorOption: {
        placeholder: "请输入文本信息..."
      },
      addRange1: [],
      addRange2: [],
      addRange3: [],
      addRange4: [],
      Rangeflag1: false,
      Rangeflag2: false,
      Rangeflag3: false,
      Rangeflag4: false
    };
  },
  mounted() {
    // 为图片ICON绑定事件  getModule 为编辑器的内部属性
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
    // 点击图片ICON触发事件
    imgHandler(state) {
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
        });
      });
    },
    alertValue(e, strValue, decimalNum) {
      e.quill.deleteText(decimalNum, 1, strValue); //保留 strValue 的 前 decimalNum 位字符，
    },
    //弹框封装
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //提交
    submit() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          this.msgTip("warning", "请填写完整");
          return false;
        } else {
          if (
            this.form.descriptionMobileCn == "" ||
            this.form.descriptionMobileEn == "" ||
            this.form.descriptionPcCn == "" ||
            this.form.descriptionPcEn == ""
          ) {
            this.msgTip("warning", "所有的描述都不能为空");
            return false;
          }
          if (!/(^[1-9]\d*$)/.test(this.form.seq) && this.form.seq !== "") {
            this.msgTip("warning", "序号只能为正整数");
            return false;
          }
          // 表单验证通过之后的操作
          // this.isRepeat = true;
          this.isDisable = true;
          addAbout(this.form).then(res => {
            if (res.success) {
              this.msgTip("success", "添加成功");
              this.$store.state.tagsView.visitedViews.splice(
                this.$store.state.tagsView.visitedViews.findIndex(
                  item => item.path === this.$route.path
                ),
                1
              );
              this.$router.push(
                this.$store.state.tagsView.visitedViews[
                  this.$store.state.tagsView.visitedViews.length - 1
                ].path
              );
            } else {
              this.msgTip("error", res.errorMsg);
              // this.isRepeat = false;
              this.isDisable = false;
            }
          });
        }
      });
    }
  }
};
</script>

<style scoped lang="scss">
.addhead {
  margin-top:20px;
  padding-right:20px;
  ul {
    li {
      width: 100%;
    }
  }
}
</style>
