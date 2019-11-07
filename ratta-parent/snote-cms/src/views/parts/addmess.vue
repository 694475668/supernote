<template>
  <div class="add">
    <el-form
      :model="form"
      :rules="addRules"
      ref="form"
      :status-icon="true"
      :show-message="false"
      hide-required-asterisk
      class="form"
    >
      <ol>
        <li>
          <el-form-item label="序号" :label-width="formLabelWidth">
            <el-input v-model="form.seq" autocomplete="off" type="text" maxlength="9"></el-input>
          </el-form-item>
          <el-form-item label="系列编号" :label-width="formLabelWidth" prop="serialNumber">
            <el-select v-model="form.serialNumber" placeholder="请选择" style="width:100%">
              <el-option
                v-for="(item,index) in serial"
                :key="index"
                :label="item.value + '_'+ item.valueCn"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="产品编号" :label-width="formLabelWidth" prop="productNumber">
            <el-select v-model="form.productNumber" placeholder="请选择" style="width:100%">
              <el-option
                v-for="(item,index) in product"
                :key="index"
                :label="item.value + '_' +item.valueCn"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item> 
          <div class="commont">
            <div>
              <el-form-item label="配件产品图(1200*800)" :label-width="formLabelWidth">
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
              <el-form-item label="配件选配图(A5:415*511 A6:376*466 笔:53*330)" :label-width="formLabelWidth">
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
            <div>
              <el-form-item label="色块图(32*32)" :label-width="formLabelWidth">
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
              <el-form-item label="首页缩略图(150*150)" :label-width="formLabelWidth">
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
          </div>
        </li>
        <li>
          <el-form-item label="首页显示" :label-width="formLabelWidth">
            <el-select v-model="form.homeFlag" placeholder="请选择" style="width:100%">
              <el-option
                v-for="(item,index) in homeFlag"
                :key="index"
                :label="item.name"
                :value="item.type"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="产品类型" :label-width="formLabelWidth" prop="productType">
            <el-select v-model="form.productType" style="width:100%" multiple placeholder="请选择">
              <el-option
                v-for="item in productType"
                :key="item.value"
                :label="item.valueCn"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="颜色名称CN" :label-width="formLabelWidth" prop="colourNameCn">
            <el-input v-model="form.colourNameCn" maxlength="40" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="颜色名称EN" :label-width="formLabelWidth" prop="colourNameEn">
            <el-input v-model="form.colourNameEn" maxlength="40" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <!-- <el-form-item label="SEO关键字CN" :label-width="formLabelWidth">
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
          <el-form-item label="SEO描述CN" :label-width="formLabelWidth">
            <el-input v-model="form.seoKeywordCn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item>
          <el-form-item label="SEO描述EN" :label-width="formLabelWidth">
            <el-input v-model="form.seoKeywordEn" maxlength="255" autocomplete="off" type="text"></el-input>
          </el-form-item> -->
        </li>
      </ol>
    </el-form>
    <div class="btn">
      <el-button type="primary" @click="submit">提交</el-button>
    </div>
  </div>
</template>

<script>
import { addList } from "../../api/part.js";
import { uploads } from "../../api/upload.js";
import { searchDiclist } from "../../api/dictionary.js";
export default {
  data() {
    return {
      formLabelWidth: "120px",
      dialogImageUrl: "",
      dialogVisible: false,
      form: {
        colourNameCn: "",
        colourNameEn: "",
        colourUrl: "",
        homeFlag: "1",
        homeUrl: "",
        line: "2",
        matchUrl: "",
        productNumber: "",
        productType: [],
        productUrl: "",
        seq: "",
        serialNumber: ""
      },
      addRules: {
        seq: [{ required: true, trigger: "blur" }],
        line: [{ required: true, trigger: "change" }],
        productNumber: [{ required: true, trigger: "change" }],
        productType: [{ required: true, trigger: "change" }],
        serialNumber: [{ required: true, trigger: "change" }],
        colourNameCn: [{ required: true, trigger: "blur" }],
        colourNameEn: [{ required: true, trigger: "blur" }],
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
      homeFlag: [
        {
          name: "是",
          type: "0"
        },
        {
          name: "否",
          type: "1"
        }
      ],
      productType: [],
      imageUrl1: "",
      imageUrl2: "",
      imageUrl3: "",
      imageUrl4: "",
      rules: [],
      newList: [],
      proList: [],
      serial: [],
      product: []
    };
  },
  created() {
    this.loadAll();
  },
  mounted() {
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
  },
  methods: {
    loadAll() {
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
          this.proList = b["PART"].map(v => {
            return {
              ...v,
              value: v.value
            };
          });
          this.product = this.proList;
        }
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
      let size = file.size / 1024 / 1024 / 2;
      if (size > 2) {
        this.msgTip("warning", "图片大小必须小于2M");
        return;
      }
      let fd = new FormData();
      fd.append("file", file); //传文件
      uploads(fd).then(res => {
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
      let size = file.size / 1024 / 1024 / 2;
      if (size > 2) {
        this.msgTip("warning", "图片大小必须小于2M");
        return;
      }
      let fd = new FormData();
      fd.append("file", file); //传文件
      uploads(fd).then(res => {
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
      let size = file.size / 1024 / 1024 / 2;
      if (size > 2) {
        this.msgTip("warning", "图片大小必须小于2M");
        return;
      }
      let fd = new FormData();
      fd.append("file", file); //传文件
      uploads(fd).then(res => {
        this.imageUrl4 = res.voT.downloadUrl;
      });
      return false; //屏蔽了action的默认上传
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
      this.form.productUrl = this.imageUrl1;
      this.form.matchUrl = this.imageUrl2;
      this.form.colourUrl = this.imageUrl3;
      this.form.homeUrl = this.imageUrl4;
      if (!/(^[1-9]\d*$)/.test(this.form.seq) && this.form.seq !== "") {
        this.msgTip("warning", "序号只能为正整数");
        return false;
      }
      if (this.form.productUrl == "") {
        this.msgTip("warning", "请上传配件产品图");
        return;
      }
      if (this.form.matchUrl == "") {
        this.msgTip("warning", "请选择配件选配图");
        return false;
      }
      if (this.form.colourUrl == "") {
        this.msgTip("warning", "请上传色块图");
        return false;
      }
      this.$refs.form.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          // this.isRepeat = true;
          this.isDisable = false;
          this.form.productType = this.form.productType.toString();
          this.form.productNumber = this.form.productNumber.split("_")[0];
          this.form.serialNumber = this.form.serialNumber.split("_")[0];
          addList(this.form).then(res => {
            if (res.success) {
              this.msgTip("success", "添加成功");
              this.$store
                  .dispatch("tagsView/delView", this.$route)
                  .then(() => {
                    this.$nextTick(() => {
                      this.$router.replace({
                        path: "/parts/partsMess"
                      });
                    });
                  });
            } else {
              this.msgTip("error", res.errorMsg);
              // this.isRepeat = false;
              this.isDisable = false;
              this.form.productType = []
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
.add {
  margin-top: 20px;
  padding-right: 20px;
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
    width: 140px;
    height: 140px;
    line-height: 140px;
    text-align: center;
    border: 1px dashed #d9d9d9;
  }
  .avatar {
    width: 140px;
    height: 140px;
    display: block;
  }
  .el-scrollbar__wrap .el-select-dropdown__item span {
    display: inline-block;
    width: 270px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}
</style>
