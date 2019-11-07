<template>
  <div class="log">
    <div class="log-head">
      <ul>
        <li>
          <h5>设备号</h5>
          <el-input v-model="param.equipmentNumber"></el-input>
        </li>
        <li>
          <h5>固件版本号</h5>
          <el-input v-model="param.firmwareVersion"></el-input>
        </li>
        <li>
          <h5>日志类型</h5>
          <el-select v-model="param.type" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.type"
              :label="item.label"
              :value="item.type"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>下载标识</h5>
          <el-select v-model="param.isDownload" placeholder="请选择">
            <el-option
              v-for="item in downloads"
              :key="item.isDownload"
              :label="item.label"
              :value="item.isDownload"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>标识</h5>
          <el-select v-model="param.flag" placeholder="请选择">
            <el-option
              v-for="item in flags"
              :key="item.flag"
              :label="item.label"
              :value="item.flag"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>起始时间</h5>
          <div class="block">
            <el-date-picker v-model="param.startDate" type="date" value-format="yyyy-MM-dd"></el-date-picker>
          </div>
        </li>
        <li>
          <h5>截止时间</h5>
          <div class="block">
            <el-date-picker v-model="param.endDate" type="date" value-format="yyyy-MM-dd"></el-date-picker>
          </div>
        </li>
      </ul>
    </div>
    <div class="function">
      <ol>
        <li>
          <img src="../../assets/iconfont/find.png" alt />
          <span @click="search">查询</span>
        </li>
        <li>
          <img src="../../assets/iconfont/empty.png" alt />
          <span @click="empty">清空</span>
        </li>
        <li v-permit="'/equipment/log/delete'">
          <img src="../../assets/iconfont/delete.png" alt />
          <span @click="delet">删除</span>
        </li>
        <li v-permit="'/equipment/log/download'">
          <img src="../../assets/iconfont/down.png" alt />
          <span @click="download">日志下载</span>
        </li>
        <li v-permit="'/equipment/log/remark'">
          <img src="../../assets/iconfont/remark.png" alt />
          <span @click="remarks">备注</span>
        </li>
        <li v-permit="'/equipment/log/passed'">
          <img src="../../assets/iconfont/approve.png" alt />
          <span @click="review">审阅</span>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
        highlight-current-row
        @row-click="getRow"
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="equipmentNumber" label="设备号"></el-table-column>
        <el-table-column prop="firmwareVersion" label="固件版本号" width="300px"></el-table-column>
        <el-table-column prop="logName" label="日志名" width="350px"></el-table-column>
        <el-table-column prop="type" label="日志类型" :formatter="typeFormatter"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="isDownload" label="下载标识" :formatter="isDownloadFormatter"></el-table-column>
        <el-table-column prop="flag" label="标识" :formatter="flagFormatter"></el-table-column>
        <el-table-column label="备注">
          <template slot-scope="scope">
            <el-button
              v-if="scope.row['remark']"
              type="primary"
              @click="tickets"
              icon="el-icon-tickets"
              circle
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="page">
      <el-pagination
        background
        layout="total, prev, pager, next"
        :page-size="10"
        @size-change="handleSizeChange"
        :total="total"
        :current-page="currentPage"
        @current-change="changepage"
      ></el-pagination>
    </div>
    <!-- 备注 -->
    <el-dialog title="备注" :visible.sync="dialogVisible1" width="40%" :close-on-click-modal="false">
      <div style="margin:left;width:400px;margin-top:-20px">
        <el-tag>日志名: {{delParam.logName}}</el-tag>
      </div>
      <div slot="footer" class="dialog-footer">
        <!-- 图片上传组件辅助-->
        <el-upload
          class="avatar-uploader"
          :action="serverUrl"
          name="img"
          :headers="header"
          :show-file-list="false"
          :on-success="uploadSuccess"
          :on-error="uploadError"
          :before-upload="beforeUpload"
        ></el-upload>
        <quill-editor
          id="ql-editor"
          v-model="content"
          ref="myQuillEditor"
          :options="editorOption"
          @blur="onEditorBlur($event)"
          @change="onEditorChange($event)"
        ></quill-editor>
        <br />
        <br />
        <div style="text-align: right;">
          <el-button @click="dialogVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="save" :disabled="isDisable">保存</el-button>
        </div>
      </div>
    </el-dialog>

    <!-- 备注信息查看 -->
    <el-dialog title="备注" :visible.sync="dialogVisible3" width="40%" :close-on-click-modal="false">
      <div style="margin:left;width:400px;margin-top:-20px">
        <el-tag>日志名: {{delParam.logName}}</el-tag>
      </div>
      <div slot="footer" class="dialog-footer">
        <quill-editor
          id="ql-editor"
          v-model="delParam.remark"
          ref="myQuillEditor"
          :options="editorOption"
          @blur="onEditorBlur($event)"
          @focus="onEditorFocus($event)"
          @change="onEditorChange($event)"
          show-word-limit
        ></quill-editor>
        <br />
        <br />
        <div style="text-align: right;">
          <el-button @click="dialogVisible3 = false">取 消</el-button>
        </div>
      </div>
    </el-dialog>

    <!-- 审阅 -->
    <el-dialog title="审阅" :visible.sync="dialogVisible2" width="40%" :close-on-click-modal="false">
      <div style="margin:left;width:400px;margin-top:-20px">
        <el-tag>日志名: {{delParam.logName}}</el-tag>
      </div>
      <div slot="footer" class="dialog-footer">
        <quill-editor
          id="ql-editor1"
          v-model="delParam.remark"
          ref="myQuillEditor"
          :options="editorOption"
          @blur="onEditorBlur($event)"
          @focus="onEditorFocus($event)"
          @change="onEditorChange($event)"
          show-word-limit
        ></quill-editor>
        <br />
        <br />
        <div style="text-align: right;">
          <el-button @click="dialogVisible2 = false">取 消</el-button>
          <el-button type="primary" @click="trial" :disabled="isDisable">审阅</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { quillEditor } from "vue-quill-editor";
import {
  searchEquipmentLogList,
  deleteEquipmentLog,
  download,
  updateIsDownload,
  getEquipmentLog,
  addRemark,
  passed
} from "../../api/equipmentlog.js";
import { all } from "q";
export default {
  components: { quillEditor },
  data() {
    return {
      isDisable: false,
      isFlag: true,
      content: "",
      editorOption: {},
      type: "",
      currentRow: "",
      dialogVisible1: false,
      dialogVisible2: false,
      dialogVisible3: false,
      isDownload: "",
      flag: "",
      loading: false,
      isRepeat: false,
      delParam: {
        id: "",
        logName: "",
        flag: "",
        remark: ""
      },
      param: {
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: "",
        equipmentNumber: "",
        firmwareVersion: "",
        flag: "",
        isDownload: "",
        startDate: "",
        endDate: "",
        type: ""
      },
      options: [
        {
          type: "",
          label: "所有"
        },
        {
          type: "1",
          label: "普通日志"
        },
        {
          type: "2",
          label: "错误日志"
        }
      ],
      downloads: [
        {
          isDownload: "",
          label: "所有"
        },
        {
          isDownload: "0",
          label: "未下载"
        },
        {
          isDownload: "1",
          label: "已下载"
        }
      ],
      flags: [
        {
          flag: "",
          label: "所有"
        },
        {
          flag: "0",
          label: "未查看"
        },
        {
          flag: "1",
          label: "已查看"
        },
        {
          flag: "2",
          label: "已审阅"
        }
      ],
      tableData: [],
      total: 0,
      currentPage: 1
    };
  },
  //刷新
  created() {
    this.getList();
  },
  methods: {
    //点击获取当前点击行的数据
    getRow(row) {
      this.currentRow = row;
      this.delParam.id = row.id;
      this.delParam.logName = row.logName;
      this.delParam.remark = row.remark;
      this.isDownload = row.isDownload;
      this.flag = row.flag;
      this.type = row.type;
    },
    getList() {
      this.loading = true;
      setTimeout(() => {
        searchEquipmentLogList(this.param).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          //把原来选中行的数据清除
          this.currentRow = "";
          this.isDownload = "";
          this.flag = "";
          this.delParam.id = "";
          this.delParam.logName = "";
          this.delParam.remark = "";
          this.type = "";
          this.loading = false;
          this.isRepeat = false;
        });
      }, 100);
    },
    //判断日志类型
    typeFormatter(row, column) {
      if (row.type == "1") {
        return "普通日志";
      } else if (row.type == "2") {
        return "错误日志";
      }
    },
    //判断下载状态
    isDownloadFormatter(row, column) {
      if (row.isDownload == "0") {
        return "未下载";
      } else if (row.isDownload == "1") {
        return "已下载";
      }
    },
    //判断标识
    flagFormatter(row, column) {
      if (row.flag == "0") {
        return "未查看";
      } else if (row.flag == "1") {
        return "已查看";
      } else if (row.flag == "2") {
        return "已审阅";
      }
    },
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //当前页码
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.param.pageNo = currentPage;
      this.getList();
    },
    //每页显示的条数
    handleSizeChange(pageSize) {
      this.param.pageSize = pageSize;
      this.getList();
    },
    //根据参数进行查询
    search() {
      //参数查询数据
      this.loading = true;
      setTimeout(() => {
        searchEquipmentLogList({
          pageNo: 1,
          pageSize: 10,
          sortField: "",
          sortRules: "",
          equipmentNumber: this.param.equipmentNumber,
          firmwareVersion: this.param.firmwareVersion,
          flag: this.param.flag,
          isDownload: this.param.isDownload,
          startDate: this.param.startDate,
          endDate: this.param.endDate,
          type: this.param.type
        }).then(res => {
          //第一页开始显示
          this.tableData = res.voList;
          this.total = res.total;
          this.currentPage = 1;
          this.loading = false;
        });
      }, 500);
    },
    //清空
    empty() {
      this.param.equipmentNumber = "";
      this.param.firmwareVersion = "";
      this.param.flag = "";
      this.param.isDownload = "";
      this.param.startDate = "";
      this.param.endDate = "";
      this.param.type = "";
      this.getList();
    },
    indexMethod(index) {
      return index + 1;
    },
    //删除
    delet() {
      if (this.currentRow == "" || this.currentRow == null) {
        this.msgTip("error", "请选择需要删除的记录");
      } else {
        if (this.type != "1" && this.flag != "2") {
          this.$message({
            type: "warning",
            message: "仅允许删除普通日志或已审阅的错误日志！",
            center: true
          });
        } else {
          this.$confirm("您是否要删除当前选中的记录?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() => {
            this.isRepeat = true;
            deleteEquipmentLog(this.delParam).then(res => {
              if (res.success) {
                this.$message({
                  type: "success",
                  message: "删除成功",
                  center: true
                });
                this.param.pageNo = 1;
                this.getList();
              } else {
                //删除失败
                this.$message({
                  type: "warning",
                  message: res.errorMsg,
                  center: true
                });
                this.isRepeat = false;
              }
            });
          });
        }
      }
    },
    //日志下载点击事件
    download() {
      if (this.currentRow == "" || this.currentRow == null) {
        this.msgTip("error", "请选择需要下载的记录");
      } else {
        if (this.isFlag) {
          this.isFlag = false;
          download(this.delParam).then(res => {
            console.log(res.url, "111");
            if (res.success) {
              window.location.href = res.url;
              this.delParam.flag = "true";
              //修改下载状态
              updateIsDownload(this.delParam).then(res => {
                if (res.success) {
                  //刷新
                  //this.getList();
                  this.tableData.map(item => {
                    if (item.id == this.currentRow.id) {
                      item.isDownload = 1;
                      this.isDownload = 1;
                    }
                  });
                  this.isFlag = true;
                } else {
                  this.$message({
                    type: "warning",
                    message: res.errorMsg,
                    center: true
                  });
                  this.isFlag = true;
                }
              });
            }
          });
        }
      }
    },
    //备注点击按钮
    remarks() {
      if (this.currentRow == "" || this.currentRow == null) {
        this.msgTip("error", "请选择需要添加备注的记录");
      } else {
        if (this.isDownload == "0") {
          this.$message({
            type: "warning",
            message: "未下载的记录不允许添加备注",
            center: true
          });
        } else {
          if (this.currentRow.flag == 2) {
            this.msgTip("error", "已审阅的不能在备注");
            return;
          }
          //显示备注框
          this.dialogVisible1 = true;
          this.isDisable = false;
          this.content = this.delParam.remark;
        }
      }
    },
    //审阅点击按钮
    review() {
      if (this.currentRow == "" || this.currentRow == null) {
        this.msgTip("error", "请选择需要审阅的记录");
      } else {
        if (this.flag != "1") {
          this.$message({
            type: "warning",
            message: "仅允许审阅已查看的记录",
            center: true
          });
        } else {
          //显示备注框
          this.dialogVisible2 = true;
          this.isDisable = false;
        }
      }
    },
    //备注信息查看
    tickets() {
      this.dialogVisible3 = true;
    },
    //审阅通过按钮
    trial() {
      this.isRepeat = true;
      this.isDisable = true;
      passed(this.delParam).then(res => {
        if (res.success) {
          this.$message({
            type: "success",
            message: "请求成功",
            center: true
          });
          //隐藏富文本框
          this.dialogVisible2 = false;
          //刷新
          this.tableData.map(item => {
            if (item.id == this.currentRow.id) {
              item.flag = 2;
              this.flag = 2;
            }
          });
        } else {
          //请求失败
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
    //保存备注按钮
    save() {
      if (this.content == null || this.content == "") {
        this.$message({
          type: "warning",
          message: "备注信息不能为空",
          center: true
        });
        return;
      }
      this.delParam.remark = this.content;
      this.isRepeat = true;
      this.isDisable = true;
      addRemark(this.delParam).then(res => {
        if (res.success) {
          this.$message({
            type: "success",
            message: "请求成功",
            center: true
          });
          //this.getList();
          //隐藏富文本框
          this.dialogVisible1 = false;
          //刷新
          this.tableData.map(item => {
            if (item.id === this.currentRow.id) {
              item.flag = "1";
              this.flag = 1;
              item.remark = this.content;
            }
          });
        } else {
          //请求失败
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
    // 准备编辑器
    onEditorReady(editor) {
      alert("cc");
    },
    onEditorBlur() {
      alert("失去焦点");
    }, // 失去焦点事件
    onEditorFocus(editor) {
      editor.enable(false);
    }, // 获得焦点事件
    onEditorChange(editor) {
      alert(editor.ql - image);
      console.log(this.content);
    } // 内容改变事件
  }
};
</script>

<style lang="scss" scoped>
.log {
  list-style: none;
  .log-head {
    padding-right: 20px;
    ul {
      width: 100%;
      display: flex;
      padding-left: 0;
      li {
        width: 20%;
        margin-left: 20px;
        .el-select {
          width: 100%;
        }
        .el-date-editor {
          width: 100%;
        }
      }
    }
  }
  .function {
    width: 100%;
    height: 40px;
    line-height: 40px;
    background: #eee;
    ol {
      display: flex;
      padding-left: 0;
      li {
        margin-left: 20px;
        cursor: pointer;
        display: flex;
        align-items: center;
        padding: 0 10px;
        img {
          width: 15px;
          vertical-align: middle;
          margin-right: 5px;
        }
        span {
          font-size: 14px;
        }
      }
      li:hover {
        background: #ccc;
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
.ql-snow {
  text-align: left;
}
#ql-editor1 {
  margin-top: -15px;
}
#ql-editor {
  margin-top: -15px;
}
/* 富文本高度 */
.ql-editor {
  height: 320px;
}
.ql-editor1 {
  height: 320px;
}
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
</style>

