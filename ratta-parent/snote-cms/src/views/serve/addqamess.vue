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
            <el-form-item label="类型" :label-width="formLabelWidth">
              <el-select
                v-model="type"
                placeholder="请选择"
                style="width:100%"
                @change="handleChangeFirst"
              >
                <el-option
                  v-for="(item,index) in typelist"
                  :key="index"
                  :label="item.name"
                  :value="item.type"
                ></el-option>
              </el-select>
            </el-form-item>
            </div>
            
            <el-form-item
              label="标题"
              :label-width="formLabelWidth"
              prop="faqTitleId"
              v-if="type!=''"
            >
              <el-select v-model="form.faqTitleId" placeholder="请选择" style="width:100%">
                <el-option
                  v-for="(item,index) in faqTit"
                  :key="index"
                  :label="item.titleCn"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <div class="commont">
            <el-form-item label="问题CN" :label-width="formLabelWidth" prop="questionCn">
              <el-input v-model="form.questionCn" maxlength="255" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="问题EN" :label-width="formLabelWidth" prop="questionEn">
              <el-input v-model="form.questionEn" maxlength="255" autocomplete="off" type="text"></el-input>
            </el-form-item>
            </div>
            <!-- <el-form-item label="SEO关键字CN" :label-width="formLabelWidth">
              <el-input v-model="form.seoKeywordCn" maxlength="255" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="SEO关键字EN" :label-width="formLabelWidth">
              <el-input v-model="form.seoKeywordEn" maxlength="255" autocomplete="off" type="text"></el-input>
            </el-form-item>
            <el-form-item label="SEO描述CN" :label-width="formLabelWidth">
              <el-input
                v-model="form.seoDescriptionCn"
                maxlength="255"
                autocomplete="off"
                type="text"
              ></el-input>
            </el-form-item>
            <el-form-item label="SEO描述EN" :label-width="formLabelWidth">
              <el-input
                v-model="form.seoDescriptionEn"
                maxlength="255"
                autocomplete="off"
                type="text"
              ></el-input>
            </el-form-item> -->
      </el-form>
      <ul>
        <li>
          <h5>答案CN</h5>
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
            v-model="form.answerCn"
            ref="myQuillEditor"
            :options="editorOption"
            show-word-limit
            @change="onEditchange($event)"
          ></quill-editor>
        </li>
        <li>
          <h5>答案EN</h5>
          <quill-editor
            v-model="form.answerEn"
            ref="myQuillEditor1"
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
import { quillEditor } from "vue-quill-editor"; //调用编辑器
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import { upload_img } from "@/api/upload.js";
import { addList } from "../../api/faqmess.js";
import { uploads } from "../../api/upload.js";
import { faqType } from "../../api/faq.js";
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
  components: { quillEditor },
  data() {
    return {
      value: "",
      size: 0,
      line: [
        {
          name: "上线",
          type: "0"
        },
        {
          name: "下线",
          type: "1"
        }
      ],
      typelist: [
        {
          name: "FAQ",
          type: "0"
        },
        {
          name: "故障排除",
          type: "1"
        }
      ],
      faqTit: [],
      type: "",
      form: {
        answerCn: "",
        answerEn: "",
        faqTitleId: "",
        line: "2",
        seq: "",
        questionCn: "",
        questionEn: "",
      },
      formLabelWidth: "120px",
      formRules: {
        seq: [{ required: true, trigger: "blur" }],
        questionEn: [{ required: true, trigger: "blur" }],
        questionCn: [{ required: true, trigger: "blur" }],
        faqTitleId: [{ required: true, trigger: "blur" }]
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
      Rangeflag3: false
    };
  },
  mounted() {
    // 为图片ICON绑定事件  getModule 为编辑器的内部属性
    // 为图片ICON绑定事件  getModule 为编辑器的内部属性
    this.$refs.myQuillEditor.quill
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
    this.$refs.myQuillEditor1.quill
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
    imgHandler(state) {
      this.addRange = this.$refs.myQuillEditor.quill.getSelection();
      if (this.addRange != "" && this.addRange != null) {
        this.Rangeflag = true;
      }
      this.addRange1 = this.$refs.myQuillEditor1.quill.getSelection();
      if (this.addRange1 != "" && this.addRange1 != null) {
        this.Rangeflag1 = true;
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
    //获取标题
    handleChangeFirst(value) {
      faqType(value).then(res => {
        this.faqTit = res.voList;
      });
    },

    //提交
    submit() {
      if (!/(^[1-9]\d*$)/.test(this.form.seq) && this.form.seq !== "") {
        this.msgTip("warning", "序号只能为正整数");
        return false;
      }
      if (this.form.answerCn == "" || this.form.answerEn == "") {
        this.msgTip("warning", "答案不能为空");
      } else {
        this.$refs.form.validate(valid => {
          if (valid) {
            // 表单验证通过之后的操作
            // this.isRepeat = true;
            this.isDisable = false;
            addList(this.form).then(res => {
              if (res.success) {
                this.msgTip("success", "添加成功");
                 this.$store
                  .dispatch("tagsView/delView", this.$route)
                  .then(() => {
                    this.$nextTick(() => {
                      this.$router.replace({
                        path: "/serve/qamess"
                      });
                    });
                  });
              } else {
                this.msgTip("error", res.errorMsg);
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
  margin-top:20px;
  padding-right:20px;
  ul {
    li {
      width: 100%;
    }
  }
}
</style>
