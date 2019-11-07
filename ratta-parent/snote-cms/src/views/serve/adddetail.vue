<template>
  <div class="add">
    <el-form
      :model="form"
      :rules="addRules"
      ref="form"
      :status-icon="true"
      :show-message="false"
      hide-required-asterisk
    >
      <el-form-item label="序号" :label-width="formLabelWidth">
        <el-input v-model="form.seq" autocomplete="off" type="text" style="width:46%" maxlength="9"></el-input>
      </el-form-item>
      <el-form-item label="类型" :label-width="formLabelWidth" prop="type">
        <el-select v-model="form.type" @change="changes"  placeholder="请选择" style="width:46%">
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
      <el-form-item label="固件/说明书" :label-width="formLabelWidth">
        <div class="upload-wrapper">
          <input
            ref="uploadFile"
            class="upload-file"
            id="file"
            type="file"
            name="file"
            multiple
            @change="submitUpload"
          />
          <el-button
            class="exprot-btn"
            size="small"
            type="primary"
            v-loading="loading"
          >{{buttonUpload}}</el-button>
        </div>
      </el-form-item>
      <el-form-item v-if='show' label="固件/说明书(EN)" :label-width="formLabelWidth">
        <div class="upload-wrapper">
          <input
            ref="uploadFiles"
            class="upload-files"
            id="files"
            type="file"
            name="files"
            multiple
            @change="submitUploads"
          />
          <el-button
            class="exprot-btn"
            size="small"
            type="primary"
            v-loading="loadings"
          >{{buttonUploads}}</el-button>
        </div>
      </el-form-item>
      </div>
      <div class="commont">
        <el-form-item label="版本号(CN)" :label-width="formLabelWidth" prop="versionCn">
          <el-input v-model="form.versionCn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
        <el-form-item label="版本号(EN)" :label-width="formLabelWidth" prop="versionEn">
          <el-input v-model="form.versionEn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
       
      </div>
      <div class="commont">
         <el-form-item label="文件大小(CN)" :label-width="formLabelWidth">
          <el-input
            v-model="showSize"
            autocomplete="off"
            type="text"
            maxlength="255"
            :disabled="true"
          ></el-input>
        </el-form-item>
        <el-form-item label="文件大小(EN)" :label-width="formLabelWidth">
          <el-input
            v-model="showSizes"
            autocomplete="off"
            type="text"
            maxlength="255"
            :disabled="true"
          ></el-input>
        </el-form-item>
      </div> 
      <div class="commont">
        <el-form-item label="标题(CN)" :label-width="formLabelWidth" prop="titleCn">
          <el-input v-model="form.titleCn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
        <el-form-item label="标题(EN)" :label-width="formLabelWidth" prop="titleEn">
          <el-input v-model="form.titleEn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
      </div>
      <div class="commont" v-show="flag">
        <el-form-item label="下载地址(CN)" :label-width="formLabelWidth" prop="urlCn">
          <el-input
            v-model="form.urlCn"
            :disabled="true"
            autocomplete="off"
            type="text"
            maxlength="255"
          ></el-input>
        </el-form-item>
        <el-form-item label="下载地址(EN)" :label-width="formLabelWidth" prop="urlEn">
          <el-input
            v-model="form.urlEn"
            :disabled="true"
            autocomplete="off"
            type="text"
            maxlength="255"
          ></el-input>
        </el-form-item>
      </div>
      <!-- <div class="commont">
        <el-form-item label="SEO关键字(CN)" :label-width="formLabelWidth">
          <el-input v-model="form.seoKeywordCn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
        <el-form-item label="SEO关键字(EN)" :label-width="formLabelWidth">
          <el-input v-model="form.seoKeywordEn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
      </div>
      <div class="commont">
        <el-form-item label="SEO描述CN" :label-width="formLabelWidth">
          <el-input v-model="form.seoDescriptionCn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item>
        <el-form-item label="SEO描述EN" :label-width="formLabelWidth">
          <el-input v-model="form.seoDescriptionEn" autocomplete="off" type="text" maxlength="255"></el-input>
        </el-form-item> -->
      <!-- </div> -->
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
      <el-button type="primary" @click="submit" :disabled="savedisab">提交</el-button>
    </div>
  </div>
</template>

<script>
import * as Quill from "quill";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import { addList } from "../../api/detail.js";
import { uploads } from "../../api/upload.js";
import { searchDiclist } from "../../api/dictionary.js";
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
      editorOption: {
        placeholder: "请输入文本信息..."
      },
      formLabelWidth: "120px",
      flags: false,
      flag: true,
      loading: false,
      loadings:false,
      disab: false,
      savedisab: true,
      savedisabs: true,
      show:false,
      buttonUpload: "上传",
      buttonUploads:'上传',
      showSize: "",
      showSizes:'',
      strName: "",
      form: {
        descriptionCn: "",
        descriptionEn: "",
        line: "2",
        seq: "",
        sizeCn: "",
        sizeEn: "",
        titleCn: "",
        titleEn: "",
        type: "",
        urlCn: "",
        urlEn: "",
        versionCn: "",
        versionEn:'',
        thumbnailUrl:''
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
      addRules: {
        descriptionCn: [{ required: true, trigger: "blur" }],
        descriptionEn: [{ required: true, trigger: "blur" }],
        titleCn: [{ required: true, trigger: "blur" }],
        titleEn: [{ required: true, trigger: "blur" }],
        type: [{ required: true, trigger: "blur" }],
        urlCn: [{ required: true, trigger: "blur" }],
        versionCn:[{ required: true, trigger: "blur" }],
        versionEn: [{ required: true, trigger: "blur" }]
      },
      productType: [],
      imageUrl1:'',
      detailId:''
    };
  },
  created() {},
  mounted() {
    // 为图片ICON绑定事件  getModule 为编辑器的内部属性
    this.$refs.myQuillEditor.quill
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
    this.$refs.myQuillEditor1.quill
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
      this.productType = res.voList;
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
    //下拉框内容判断
    changes(v){
        
        this.detailId = v
        if(v == 0){
            this.show = true
        } else{
            this.show = false
        }
           this.buttonUpload = '上传'
           this.buttonUploads = '上传'
           this.showSize= ''
           this.showSizes = ''
           this.form.urlCn = ''
           this.form.urlEn = ''
    },
    //上传固件包
    submitUpload() {
      const fileData = this.$refs.uploadFile.files[0];
      let formFile = new FormData(); //加入文件对象
      formFile.append("file", fileData);
      var index = fileData.name.lastIndexOf(".");
      var str = fileData.name.substring(index + 1, fileData.name.length);
      const extension = str === "zip";
      const extension1 = str === "pdf";
      this.strName = str;
      if (extension || extension1) {
        this.loading = true;
        this.savedisab = true;
        uploads(formFile).then(res => {
          if (res.success) {
            this.loading = false;
            this.savedisab = false;
            this.$refs.uploadFile.value = null;
            this.form.urlCn = res.voT.downloadUrl;
            if(this.detailId == '1'){
              this.form.urlEn = res.voT.downloadUrl;
              this.form.sizeEn = (fileData.size).toString()
              this.showSizes = this.change(fileData.size)
            }
            this.showSize = this.change(fileData.size);
            this.form.sizeCn = (fileData.size).toString();
            this.buttonUpload = fileData.name;
          } else {
            this.loading = false;
            this.msgTip("error", res.errorMsg);
            this.$refs.uploadFile.value = null;
          }
        });
      } else {
        this.msgTip("error", "上传文件只能是zip/pdf格式!");
        return;
      }
    },
    submitUploads(){
      const fileDatas = this.$refs.uploadFiles.files[0];
      let formFiles = new FormData(); //加入文件对象
      formFiles.append("file", fileDatas);
      var index = fileDatas.name.lastIndexOf(".");
      var str = fileDatas.name.substring(index + 1, fileDatas.name.length);
      const extension1 = str === "pdf";
      this.strName = str;
      if (extension1) {
        this.loadings = true;
        this.savedisabs = true;
        uploads(formFiles).then(res => {
          if (res.success) {
            this.loadings = false;
            this.savedisabs = false;
            this.$refs.uploadFiles.value = null;
            this.form.urlEn = res.voT.downloadUrl;
            this.buttonUploads = fileDatas.name;
            this.showSizes = this.change(fileDatas.size)
            this.form.sizeEn = (fileDatas.size).toString()
          } else {
            this.loadings = false;
            this.msgTip("error", res.errorMsg);
            this.$refs.uploadFiles.value = null;
          }
        });
      } else {
        this.msgTip("error", "上传文件只能是zip/pdf格式!");
        return;
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
      uploads(fd).then(res => {
        this.imageUrl1 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
    },
    //提交
    submit() {
      this.form.thumbnailUrl = this.imageUrl1
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.type == 0 || this.form.type == "0") {
            if (this.strName != "pdf") {
              this.msgTip("error", "说明书只能是pdf格式!");
              return false;
            }
          }
          if (this.form.type == 1 || this.form.type == "1") {
            if (this.strName != "zip") {
              this.msgTip("error", "固件只能是zip格式!");
              return false;
            }
          }
          if (!/(^[1-9]\d*$)/.test(this.form.seq) && this.form.seq !== "") {
            this.msgTip("warning", "序号只能为正整数");
            return false;
          }
          if (this.form.descriptionCn == "" || this.form.descriptionEn == "") {
            this.msgTip("warning", "描述不能为空");
            return false;
          }
          if (this.form.thumbnailUrl == "") {
            this.msgTip("warning", "缩略图不能为空");
            return false;
          }
          // 表单验证通过之后的操作
          addList(this.form).then(res => {
            if (res.success) {
              this.msgTip("success", "添加成功");
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
    },

    change(limit) {
      var size = "";
      if (limit < 0.1 * 1024) {
        //小于0.1KB，则转化成B
        size = limit.toFixed(2) + "B";
      } else if (limit < 0.1 * 1024 * 1024) {
        //小于0.1MB，则转化成KB
        size = (limit / 1024).toFixed(2) + "KB";
      } else if (limit < 0.1 * 1024 * 1024 * 1024) {
        //小于0.1GB，则转化成MB
        size = (limit / (1024 * 1024)).toFixed(2) + "MB";
      } else {
        //其他转化成GB
        size = (limit / (1024 * 1024 * 1024)).toFixed(2) + "GB";
      }

      var sizeStr = size + ""; //转成字符串
      var index = sizeStr.indexOf("."); //获取小数点处的索引
      var dou = sizeStr.substr(index + 1, 2); //获取小数点后两位的值
      if (dou == "00") {
        //判断后两位是否为00，如果是则删除00
        return sizeStr.substring(0, index) + sizeStr.substr(index + 3, 2);
      }
      return size;
    }
  }
};
</script>

<style scoped lang='scss'>
.add {
  padding-top: 20px;
  padding-right: 20px;
  padding-left:20px;
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
      height: 33px;
      opacity: 0;
      border: none;
    }
    .upload-files {
      position: absolute;
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
