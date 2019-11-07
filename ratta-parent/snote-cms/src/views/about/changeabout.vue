<template>
  <div class="updateabout">
    <div class="addhead">
      <el-form
        :model="form"
        :rules="formRules"
        ref="form"
        :status-icon="true"
        hide-required-asterisk
        class="form"
      >
            <el-form-item label="标题CN" :label-width="formLabelWidth" prop="titleCn">
              <el-input v-model="form.titleCn" autocomplete="off" type="text" maxlength="50"></el-input>
            </el-form-item>
            <el-form-item label="标题EN" :label-width="formLabelWidth" prop="titleEn">
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
            show-word-limit
          ></quill-editor>
        </li>
        <li>
          <h5>PC端描述EN</h5>
          <quill-editor
            v-model="form.descriptionPcEn"
            ref="myQuillEditor2"
            :options="editorOption"
            show-word-limit
          ></quill-editor>
        </li>
        <li>
          <h5>移动端描述CN</h5>
          <quill-editor
            v-model="form.descriptionMobileCn"
            ref="myQuillEditor3"
            :options="editorOption"
            show-word-limit
          ></quill-editor>
        </li>
        <li>
          <h5>移动端描述EN</h5>
          <quill-editor
            v-model="form.descriptionMobileEn"
            ref="myQuillEditor4"
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
import { quillEditor } from "vue-quill-editor";
import * as Quill from "quill";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import { queryAboutAll, updateAbout, upload } from "../../api/abouts.js";
export default {
  components: {
    quillEditor
  },
  data() {
    return {
      value: "",
      id: "",
      formDate: [],
      form: {
        descriptionMobileCn: "",
        descriptionMobileEn: "",
        descriptionPcCn: "",
        descriptionPcEn: "",
        titleCn: "",
        titleEn: "",
        // seoDescriptionCn: "",
        // seoDescriptionEn: "",
        // seoKeywordCn: "",
        // seoKeywordEn: ""
      },
      queryDate: {
        pageNo: 1,
        pageSize: 10,
        id: ""
      },
      formLabelWidth: "120px",
      formRules: {
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

  created() {
    this.id = this.$route.query.id;
    this.getList();
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
  },
  methods: {
    getList() {
      this.loading = true;
      this.queryDate.id = this.id;
      queryAboutAll(this.queryDate).then(res => {
        this.form = res.voList[0];
        this.loading = false;
      });
    },

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
        if (valid) {
          // 表单验证通过之后的操作
          // this.isRepeat = true;
          if (
            this.form.descriptionMobileCn == "" ||
            this.form.descriptionMobileEn == "" ||
            this.form.descriptionPcCn == "" ||
            this.form.descriptionPcEn == ""
          ) {
            this.msgTip("warning", "所有的描述都不能为空");
            return false;
          }
          this.isDisable = true;
          updateAbout(this.form, this.id).then(res => {
            if (res.success) {
              this.msgTip("success", "修改成功");
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
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
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
