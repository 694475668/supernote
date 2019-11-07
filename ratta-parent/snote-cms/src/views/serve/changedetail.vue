<template>
  <div class="change" v-loading="loading">
    <el-form
      :model="form"
      :rules="changeRules"
      ref="form"
      :status-icon="true"
      :show-message="false"
      hide-required-asterisk
    >
      <el-form-item label="类型" :label-width="formLabelWidth" prop="type">
        <el-select v-model="form.type" disabled placeholder="请选择" style="width:46%">
          <el-option
            v-for="(item,index) in option"
            :key="index"
            :label="item.name"
            :value="item.type"
          ></el-option>
        </el-select>
      </el-form-item>
     <el-form-item label="缩略图(140*181)" :label-width="formLabelWidth" prop="type">
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
      <div class="commont">
        <el-form-item label="版本号" :label-width="formLabelWidth" prop="versionCn">
          <el-input v-model="form.versionCn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
        <el-form-item label="版本号" :label-width="formLabelWidth" prop="versionEn">
          <el-input v-model="form.versionEn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
        
      </div>
      <div class="commont">
        <el-form-item label="文件大小" :label-width="formLabelWidth">
          <el-input
            v-model="form.sizeCn"
            autocomplete="off"
            type="text"
            maxlength="255"
            :disabled="true"
          ></el-input>
        </el-form-item>
        <el-form-item label="文件大小" :label-width="formLabelWidth">
          <el-input
            v-model="form.sizeEn"
            autocomplete="off"
            type="text"
            maxlength="255"
            :disabled="true"
          ></el-input>
        </el-form-item>
      </div>
      <div class="commont">
        <el-form-item label="标题CN" :label-width="formLabelWidth" prop="titleCn">
          <el-input v-model="form.titleCn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
        <el-form-item label="标题EN" :label-width="formLabelWidth" prop="titleEn">
          <el-input v-model="form.titleEn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
      </div>
      <div class="commont">
        <el-form-item label="下载地址CN" :label-width="formLabelWidth">
          <div class="upload-wrapper">
            <a :href="form.urlCn" :target="form.type === '0'?'_blank':''">
              <el-button class="exprot-btn" size="small" type="primary">下载</el-button>
            </a>
          </div>
        </el-form-item>
        <el-form-item label="下载地址EN" :label-width="formLabelWidth">
          <div class="upload-wrapper">
            <a :href="form.urlEn" :target="form.type === '0'?'_blank':''">
              <el-button class="exprot-btn" size="small" type="primary">下载</el-button>
            </a>
          </div>
        </el-form-item>
      </div>
    </el-form>
    <ul>
      <li>
        <h5>描述（CN）</h5>
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
          v-model="form.descriptionCn"
          ref="myQuillEditor"
          :options="editorOption"
          show-word-limit
        ></quill-editor>
      </li>
      <li>
        <h5>描述（EN）</h5>
        <quill-editor
          v-model="form.descriptionEn"
          ref="myQuillEditor1"
          :options="editorOption"
          show-word-limit
        ></quill-editor>
      </li>
    </ul>
    <div class="btn">
      <el-button type="primary" @click="submit">提交</el-button>
    </div>
  </div>
</template>

<script>
import * as Quill from "quill";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import { changeMess, aloneMess } from "../../api/detail.js";
import { uploads } from "../../api/upload.js";
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
  data() {
    return {
      formLabelWidth: "130px",
      id: "",
      changeRules: {
        descriptionCn: [{ required: true, trigger: "blur" }],
        descriptionEn: [{ required: true, trigger: "blur" }],
        titleCn: [{ required: true, trigger: "blur" }],
        titleEn: [{ required: true, trigger: "blur" }],
        type: [{ required: true, trigger: "blur" }],
        urlCn: [{ required: true, trigger: "blur" }],
        versionCn: [{ required: true, trigger: "blur" }],
        versionEn: [{ required: true, trigger: "blur" }]
      },
      option: [
        {
          name: "说明书",
          type: "0"
        },
        {
          name: "固件",
          type: "1"
        }
      ],
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
      editorOption: {
        placeholder: "请输入文本信息..."
      },
      flags: false,
      flag: true,
      loading: false,
      disab: false,
      savedisab: true,
      form: {
        descriptionCn: "",
        descriptionEn: "",
        line: "2",
        // seoDescriptionCn: "",
        // seoDescriptionEn: "",
        // seoKeywordCn: "",
        // seoKeywordEn: "",
        seq: "",
        sizeCn: "",
        sizeEn:'',
        titleCn: "",
        titleEn: "",
        type: "",
        urlCn: "",
        urlEn: "",
        versionCn: "",
        versionEn:'',

      },
      imageUrl1:''
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
    this.id = this.$route.query.id;
    aloneMess(this.id).then(res => {
      if (res.success) {
        this.form = res.voT;
        let item = res.voT;
        this.form.line = item.line;
        this.imageUrl1 = item.thumbnailUrl
      } else {
        this.msgTip("error", res.errorMsg);
      }
    });
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
      uploads(fd).then(res => {
        this.imageUrl1 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
    },
    //提交
    submit() {
      this.form.thumbnailUrl = this.imageUrl1
      if (this.form.descriptionCn == "" || this.form.descriptionEn == "") {
        this.msgTip("warning", "描述不能为空");
        return false;
      }
      if(this.form.thumbnailUrl == ''){
          this.msgTip("warning", "缩略图不能为空");
        return false;
      }
      this.$refs.form.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          changeMess(this.form, this.id).then(res => {
            if (res.success) {
              this.msgTip("success", "修改成功");
             this.$store
                  .dispatch("tagsView/delView", this.$route)
                  .then(() => {
                    this.$nextTick(() => {
                      this.$router.replace({
                        path: "/serve/detail"
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
};
</script>

<style scoped lang='scss'>
.change {
  padding-top: 20px;
  padding-right: 20px;
  .btn {
    width: 100%;
    text-align: center;
    margin-top: 20px;
  }
  .upload-wrapper {
    position: relative;
    margin-right: 15px;
    .upload-file {
      position: absolute;
      width: 81px;
      height: 33px;
      opacity: 0;
      border: none;
    }
  }
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
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
  border: 1px dashed #d9d9d9;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>
