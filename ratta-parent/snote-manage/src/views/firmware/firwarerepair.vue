<template>
  <div class="firwarerepair">
    <div class="firwarerepair-head">
      <ul>
        <li>
          <h5>版本号</h5>
          <el-input v-model="form.firmwareVersion"></el-input>
        </li>
        <li>
          <h5>语言</h5>
          <el-select v-model="form.lan" placeholder="请选择" @focus="getLan()">
            <el-option
              v-for="item in lanData"
              :key="item.value"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <el-button type="primary" @click="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
        <li>
          <el-button type="primary" @click="add" v-permit="'/firmware/fixPoint/add'">添加</el-button>
        </li>
        <li>
          <el-button type="primary" @click="edit" v-permit="'/firmware/fixPoint/update'">修改</el-button>
        </li>
      </ul>
    </div>

    <!-- 新增 -->
    <el-dialog
      title="添加"
      :visible.sync="dialogFormVisible"
      @close="cancel('data')"
      :close-on-click-modal="false"
    >
      <el-form :model="data" :rules="rulesadd" ref="data" :status-icon="true" :show-message="false">
        <el-form-item
          label="语言: "
          :label-width="formLabelWidth"
          prop="lan"
          class="dialogApp"
          style="padding-right:8px"
        >
          <el-select class="selectWidth" v-model="data.lan" placeholder="请选择" @focus="getLan()">
            <el-option
              v-for="item in lanData"
              :key="item.value"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="版本号: "
          :label-width="formLabelWidth"
          prop="firmwareVersion"
          class="dialogApp"
          style="padding-left:8px"
        >
          <el-select
            class="selectWidth"
            v-model="data.firmwareVersion"
            filterable
            placeholder="请选择"
            @focus="getVersion()"
          >
            <el-option v-for="item in versionData" :key="item" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="fixPoint" class="dialogEditor">
          <quill-editor
            v-model="data.fixPoint"
            ref="data"
            :options="editorOption"
            @blur="onEditorBlur($event)"
            @focus="onEditorFocus($event)"
            @change="onEditorChange($event)"
            prop="fixPoint"
          ></quill-editor>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('data')">取 消</el-button>
        <el-button type="primary" @click="save('data')" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <!-- 修改 -->
    <el-dialog title="修改" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form
        :model="updateData"
        :rules="rulesadd"
        ref="updateData"
        :status-icon="true"
        :show-message="false"
      >
        <el-form-item
          label="语言: "
          :label-width="formLabelWidth"
          class="dialogApp"
          style="padding-right:8px"
        >
          <el-input v-model="updateData.lan" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item
          label="版本号: "
          :label-width="formLabelWidth"
          class="dialogApp"
          style="padding-left:8px"
        >
          <el-input v-model="updateData.firmwareVersion" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" prop="fixPoint" class="dialogEditor">
          <quill-editor
            v-model="updateData.fixPoint"
            ref="updateData"
            :options="editorOption"
            @blur="onEditorBlur($event)"
            @focus="onEditorFocus($event)"
            @change="onEditorChange($event)"
            show-word-limit
          ></quill-editor>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('updateData')">取 消</el-button>
        <el-button type="primary" @click="update" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
        highlight-current-row
        @current-change="currentChange"
        @row-click="getRow"
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="id" v-if="show"></el-table-column>
        <el-table-column prop="firmwareVersion" label="版本号" show-overflow-tooltip min-width="25%"></el-table-column>
        <el-table-column prop="fixPoint" show-overflow-tooltip label="修复点"></el-table-column>
        <el-table-column prop="lan" label="语言" show-overflow-tooltip min-width="10%"></el-table-column>
        <el-table-column prop="opUser" label="上传人员" show-overflow-tooltip min-width="10%"></el-table-column>
        <el-table-column prop="opTime" label="上传时间" show-overflow-tooltip min-width="20%"></el-table-column>
      </el-table>
    </div>
    <div class="page">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :hide-on-single-page="value"
        :current-page="currentPage4"
        :page-size="10"
        layout="total,prev, pager, next"
        :total="total"
        background
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import { quillEditor } from "vue-quill-editor"; //调用编辑器
import {
  getFirmwareFixpointList,
  getLanList,
  getFirmwareVersionList,
  addFirmwareFixpoint,
  updateFirmwareFixpoint,
  dataManipulation
} from "../../api/firmwarepair.js";
import { close } from "fs";
export default {
  components: { quillEditor },
  data() {
    return {
      isDisable: false,
      value: "",
      value1: "",
      value2: "",
      currentPage4: 0,
      total: 0,
      id: "",
      tableData: [],
      rowData: [],
      lanData: [],
      versionData: [],
      dialogFormVisible: false,
      dialogVisible: false,
      formLabelWidth: "100px",
      loading: false,
      isRepeat: false,
      form: {
        pageNo: 1,
        pageSize: 10,
        lan: "",
        firmwareVersion: ""
      },
      data: {
        firmwareVersion: "",
        fixPoint: "",
        lan: ""
      },
      updateData: {
        firmwareVersion: "",
        fixPoint: "",
        lan: ""
      },
      updateRowData: {
        firmwareVersion: "",
        fixPoint: "",
        lan: ""
      },
      show: false,
      rulesadd: {
        lan: [{ required: true, trigger: "change" }],
        firmwareVersion: [{ required: true, trigger: "change" }]
      },
      editorOption: {
        modules: {
          toolbar: [
            ["bold", "italic", "underline", "strike"], //加粗，斜体，下划线，删除线
            ["blockquote", "code-block"], //引用，代码块
            [{ header: 1 }, { header: 2 }], // 标题，键值对的形式；1、2表示字体大小
            [{ list: "ordered" }, { list: "bullet" }], //列表
            [{ script: "sub" }, { script: "super" }], // 上下标
            [{ indent: "-1" }, { indent: "+1" }], // 缩进
            [{ direction: "rtl" }], // 文本方向
            [{ size: ["small", false, "large", "huge"] }], // 字体大小
            [{ header: [1, 2, 3, 4, 5, 6, false] }], //几级标题
            [{ color: [] }, { background: [] }], // 字体颜色，字体背景颜色
            [{ font: [] }], //字体
            [{ align: [] }], //对齐方式
            ["clean"] //清除字体样式 //['image','video']        //上传图片、上传视频
          ]
        },
        theme: "snow"
      }
    };
  },

  created() {
    this.getList();
  },

  methods: {
    onEditorReady(editor) {}, // 准备编辑器
    onEditorBlur() {}, // 失去焦点事件
    onEditorFocus() {}, // 获得焦点事件
    onEditorChange() {}, // 内容改变事件

    getList() {
      this.loading = true;
      setTimeout(() => {
        getFirmwareFixpointList(this.form).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.loading = false;
          this.isRepeat = false;
        });
      }, 500);
    },

    //添加
    add() {
      this.dialogFormVisible = true;
      this.isDisable = false;
    },
    save(formName) {
      this.$refs[formName].validate(valid => {
        if (this.data.fixPoint == "" || this.data.fixPoint == null) {
          this.data.fixPoint =
            '<p><strong style="color: rgb(230, 0, 0);"><em>请输入修复点信息(不可为空)</em></strong></p>';
          return false;
        }
        if (valid) {
          this.isRepeat = true;
          this.isDisable = true;
          addFirmwareFixpoint(this.data).then(res => {
            this.dialogFormVisible = false;
            if (res.success) {
              this.$message({
                type: "success",
                message: "添加成功",
                center: true
              });
              this.$refs[formName].resetFields();
              this.empty();
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
              this.isRepeat = false;
              this.isDisable = false;
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    //修改
    edit() {
      if (this.currentRow == null) {
        this.$message({
          type: "warning",
          message: "请选择一条数据进行修改",
          center: true
        });
        return;
      }
      this.dialogVisible = true;
      this.isDisable = false;
      this.updateData.firmwareVersion = this.updateRowData.firmwareVersion;
      this.updateData.fixPoint = this.updateRowData.fixPoint;
      this.updateData.lan = this.updateRowData.lan;
    },
    update() {
      if (this.updateData.fixPoint == "" || this.updateData.fixPoint == null) {
        this.updateData.fixPoint =
          '<p><strong style="color: rgb(230, 0, 0);"><em>请输入修复点信息(不可为空)</em></strong></p>';
        return false;
      }
      this.isRepeat = true;
      this.isDisable = true;
      updateFirmwareFixpoint(this.updateData, this.id).then(res => {
        this.dialogVisible = false;
        if (res.success) {
          this.$message({
            type: "success",
            message: "修改成功",
            center: true
          });
            this.updateRowData.firmwareVersion = this.updateData.firmwareVersion;
            this.updateRowData.fixPoint = this.updateData.fixPoint;
            this.updateRowData.lan = this.updateData.lan;
          // 获取当前日期时间
          dataManipulation(
            this.tableData,
            this.rowData,
            this.updateData.fixPoint,
            getDate()
          );
        } else {
          this.$message({
            type: "warning",
            message: res.errorMsg,
            center: true
          });
          this.isRepeat = false;
          this.isDisable = false;
        }
      });
    },

    //查询固件版本号列表
    getVersion() {
      getFirmwareVersionList({ status: "2" }).then(res => {
        this.versionData = res.voList;
      });
    },
    getLan() {
      getLanList({ name: "lan" }).then(res => {
        this.lanData = res.dictionaryVOList;
      });
    },
    //查询
    search() {
      this.loading = true;
      setTimeout(() => {
        getFirmwareFixpointList({
          pageNo: 1,
          pageSize: 10,
          lan: this.form.lan,
          firmwareVersion: this.form.firmwareVersion
        }).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.currentPage4 = 1;
          this.loading = false;
        });
      }, 500);
    },

    //清空
    empty() {
      this.form.pageNo = 1;
      this.currentPage4 = 1;
      this.form.firmwareVersion = "";
      this.form.lan = "";
      this.getList();
    },
    //取消
    cancel(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible = false;
      this.dialogVisible = false;
    },

    //获取每页条数
    handleSizeChange(val) {
      this.form.pageSize = val;
      this.getList();
    },
    //获取页码
    handleCurrentChange(val) {
      this.form.pageNo = val;
      this.currentPage4 = 0;
      this.getList();
    },
    //行选择
    currentChange(val) {
      this.currentRow = val;
    },

    //点击获取这行数据
    getRow(row) {
      this.rowData = row;
      this.id = row.id;
      this.updateRowData.firmwareVersion = row.firmwareVersion;
      this.updateRowData.fixPoint = row.fixPoint;
      this.updateRowData.lan = row.lan;
    },
    indexMethod(index) {
      return index + 1;
    },
    handleClick(row) {
      console.log(row);
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

<style lang="scss" scoped>
.firwarerepair {
  list-style: none;
  .firwarerepair-head {
    padding-right: 20px;
    ul {
      width: 100%;
      padding-left: 0;
      display: flex;
      align-items: flex-end;
      li {
        margin-left: 20px;
      }
    }
  }
  .function-icon {
    img {
      cursor: pointer;
      width: 14px;
    }
  }
}
</style>
<style>
.el-table th {
  text-align: center;
}
.el-table td {
  text-align: center;
}
.page {
  text-align: center;
  margin-top: 20px;
}
.dialogApp {
  float: left;
  width: 50%;
}
.dialogEditor {
  clear: both;
}
.selectWidth {
  width: 100%;
}
</style>

<style>
.editor {
  line-height: normal !important;
  height: 500px;
}
.ql-snow .ql-tooltip[data-mode="link"]::before {
  content: "请输入链接地址:";
}
.ql-snow .ql-tooltip.ql-editing a.ql-action::after {
  border-right: 0px;
  content: "保存";
  padding-right: 0px;
}

.ql-snow .ql-tooltip[data-mode="video"]::before {
  content: "请输入视频地址:";
}

.ql-snow .ql-picker.ql-size .ql-picker-label::before,
.ql-snow .ql-picker.ql-size .ql-picker-item::before {
  content: "14px";
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="small"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="small"]::before {
  content: "10px";
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="large"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="large"]::before {
  content: "18px";
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="huge"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="huge"]::before {
  content: "32px";
}

.ql-snow .ql-picker.ql-header .ql-picker-label::before,
.ql-snow .ql-picker.ql-header .ql-picker-item::before {
  content: "文本";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
  content: "标题1";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
  content: "标题2";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
  content: "标题3";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
  content: "标题4";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
  content: "标题5";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
  content: "标题6";
}

.ql-snow .ql-picker.ql-font .ql-picker-label::before,
.ql-snow .ql-picker.ql-font .ql-picker-item::before {
  content: "标准字体";
}
.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="serif"]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="serif"]::before {
  content: "衬线字体";
}
.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="monospace"]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="monospace"]::before {
  content: "等宽字体";
}
</style>
