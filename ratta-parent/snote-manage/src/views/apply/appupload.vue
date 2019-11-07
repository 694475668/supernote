<template>
  <div class="appupload">
    <div class="appupload-head">
      <ul>
        <li>
          <h5>应用名称</h5>
          <el-input v-model="param.appName"></el-input>
        </li>
        <li>
          <h5>版本号</h5>
          <el-input v-model="param.appVersion"></el-input>
        </li>
        <li>
          <h5>连接环境</h5>
          <el-input v-model="param.appEnvironment"></el-input>
        </li>
        <li>
          <h5>是否强制</h5>
          <el-select v-model="param.updateFlag" placeholder="请选择">
            <el-option
              v-for="item in updateFlags"
              :key="item.updateFlag"
              :label="item.label"
              :value="item.updateFlag"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>审核标识</h5>
          <el-select v-model="param.auditingFlag" placeholder="请选择">
            <el-option
              v-for="item in auditingFlags"
              :key="item.auditingFlag"
              :label="item.label"
              :value="item.auditingFlag"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>发布标识</h5>
          <el-select v-model="param.deployFlag" placeholder="请选择">
            <el-option
              v-for="item in deployFlags"
              :key="item.deployFlag"
              :label="item.label"
              :value="item.deployFlag"
            ></el-option>
          </el-select>
        </li>
      </ul>
    </div>
    <div class="function">
      <ol>
        <li @click="search">
          <img src="../../assets/iconfont/find.png" alt />
          <span>查询</span>
        </li>
        <li>
          <img src="../../assets/iconfont/empty.png" alt />
          <span @click="empty">清空</span>
        </li>
        <li v-permit="'/app/version/upload'">
          <img src="../../assets/iconfont/upload.png" alt />
          <span @click="appupload">版本上传</span>
        </li>
        <li v-permit="'/app/version/download'">
          <img src="../../assets/iconfont/down.png" alt />
          <span @click="download">下载</span>
        </li>
        <li v-permit="'/app/version/undo'">
          <img src="../../assets/iconfont/repeal.png" alt />
          <span @click="undo">撤销</span>
        </li>
        <li v-permit="'/app/version/auditing'">
          <img src="../../assets/iconfont/audit.png" alt />
          <span @click="audit">审核</span>
        </li>
        <li v-permit="'/app/version/gray'">
          <img src="../../assets/iconfont/gray.png" alt />
          <span @click="grays">灰度</span>
        </li>
        <li v-permit="'/app/version/deploy'">
          <img src="../../assets/iconfont/log.png" alt />
          <span @click="deploy">发布</span>
        </li>
        <li v-permit="'/app/version/delete'">
          <img src="../../assets/iconfont/delete.png" alt />
          <span @click="del">删除</span>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        highlight-current-row
        v-loading="loading"
        @row-click="getRow"
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="appName" label="应用名称"></el-table-column>
        <el-table-column prop="appVersion" label="版本号" width="120px"></el-table-column>
        <el-table-column prop="versionNo" label="内部版本号" width="100px"></el-table-column>
        <el-table-column prop="packageName" label="应用包名"></el-table-column>
        <el-table-column prop="updateFlag" label="是否强制" :formatter="updateFlagFormat" width="100px"></el-table-column>
        <el-table-column
          prop="auditingFlag"
          label="审核标识"
          :formatter="auditingFlagFormat"
          width="120px"
        ></el-table-column>
        <el-table-column prop="deployFlag" label="发布标识" :formatter="deployFlagFormat" width="120px"></el-table-column>
        <el-table-column prop="fileName" label="文件名" width="350px"></el-table-column>
        <el-table-column prop="appEnvironment" label="连接环境" width="150px"></el-table-column>
        <el-table-column prop="opUser" label="操作员" width="120px"></el-table-column>
        <el-table-column prop="opTime" label="上传时间" show-overflow-tooltip></el-table-column>
      </el-table>
    </div>
    <div class="page">
      <el-pagination
        background
        layout="total, prev, pager, next"
        :page-size="10"
        :total="total"
        :current-page="currentPage"
        @current-change="changepage"
      ></el-pagination>
    </div>

    <el-dialog
      title="审核"
      :visible.sync="dialogFormVisible"
      width="25%"
      :close-on-click-modal="false"
    >
      <el-select v-model="param.auditingFlag" placeholder="请选择">
        <el-option
          v-for="item in auditingFlagStatus"
          :key="item.auditingFlag"
          :label="item.label"
          :value="item.auditingFlag"
        ></el-option>
      </el-select>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save" :disabled="isDisable">保存</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="版本信息" :visible.sync="dialogFormsVisible" :close-on-click-modal="false">
      <el-form :model="upload" class="form">
        <el-form-item label="是否强制" :label-width="formLabelWidth">
          <el-select v-model="upload.updateFlag" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in update"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="版本上传" :label-width="formLabelWidth" v-loading="loading">
          <!-- <el-upload class="upload" ref='file'  action="UploadUrl()" :limit="1">
            <el-button
              style="margin-left: 10px;"
              size="small"
              type="success"
              @click="submitUpload"
              :beforeUpload="beforeAvatarUpload"
            >选择文件</el-button>
          </el-upload>-->
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
            <el-button class="exprot-btn" size="small" type="primary">
              <span>{{uplaodApp}}</span>
            </el-button>
          </div>
        </el-form-item>
        <el-form-item label="文件名" :label-width="formLabelWidth">
          <el-input v-model="upload.fileName" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="应用名称" :label-width="formLabelWidth">
          <el-input v-model="upload.appName" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="版本号" :label-width="formLabelWidth">
          <el-input v-model="upload.appVersion" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="应用包名" :label-width="formLabelWidth">
          <el-input v-model="upload.packageName" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="keep" :disabled="disabledKeep">保存</el-button>
        <el-button @click="dialogFormsVisible=false">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="灰度" :visible.sync="dialogGray" class="test" :close-on-click-modal="false">
      <div style="height:510px;overflow-y:auto;">
        <div class="gray-head">
          <ul>
            <li>
              <h5>手机号</h5>
              <el-input v-model="gray.telephone"  @input="getInputGray"></el-input>
            </li>
            <li>
              <h5>邮箱</h5>
              <el-input v-model="gray.email"  @input="getInputGray"></el-input>
            </li>
            <li>
              <el-button type="primary" @click="graySearch" :disabled="graySearchDisabled">查询</el-button>
            </li>
            <li>
              <el-button type="primary" @click="grayEmpty">清空</el-button>
            </li>
            <li>
              <el-button type="primary" @click="addGray">添加</el-button>
            </li>
          </ul>
        </div>
        <div class="tab">
          <el-table
            :data="grayData"
            style="width: 100%"
            border
            highlight-current-row
            @selection-change="handleSelectionChange"
          >
            <el-table-column type="index" :index="indexMethod"></el-table-column>
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="telephone" label="手机号"></el-table-column>
            <el-table-column prop="email" label="电子邮箱"></el-table-column>
          </el-table>
        </div>
        <div class="table">
          <h5>灰度任务列表</h5>
          <el-table :data="grayTable" style="width: 100%" border>
            <el-table-column type="index" :index="indexMethod"></el-table-column>
            <el-table-column prop="telephone" label="手机号"></el-table-column>
            <el-table-column prop="email" label="电子邮箱"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-tooltip class="item" effect="dark" content="删除" placement="bottom">
                  <img
                    style="width: 15px;vertical-align: middle;"
                    @click="dele(scope.row)"
                    src="../../assets/iconfont/delete.png"
                    alt
                  />
                </el-tooltip>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogGray=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  searchAppVersion,
  download,
  deleteApp,
  undo,
  audit,
  deploy,
  getCheckUpload,
  getUpload,
  addGray,
  querGray,
  delGray,
  dataManipulation
} from "../../api/app.js";
import { getUsercontrol } from "../../api/login.js";
export default {
  data() {
    return {
      isDisable: false,
      isFlag: true,
      dialogFormVisible: false,
      graySearchDisabled: true,
      tableData: [],
      currentRow: "",
      total: 0,
      currentPage: 1,
      auditingFlag: "",
      deployFlag: "",
      updateFlag: "",
      loading: false,
      isRepeat: false,
      disabledKeep: true,
      active: "",
      uplaodApp: "点击上传",
      transmissionParam: {
        id: 0,
        auditingFlag: ""
      },
      param: {
        appEnvironment: "",
        appName: "",
        appVersion: "",
        auditingFlag: "",
        deployFlag: "",
        pageNo: 1,
        pageSize: 10,
        updateFlag: ""
      },
      updateFlags: [
        {
          updateFlag: "",
          label: "所有"
        },
        {
          updateFlag: "0",
          label: "不强制"
        },
        {
          updateFlag: "1",
          label: "强制"
        }
      ],
      auditingFlags: [
        {
          auditingFlag: "",
          label: "所有"
        },
        {
          auditingFlag: "1",
          label: "待审核"
        },
        {
          auditingFlag: "2",
          label: "通过"
        },
        {
          auditingFlag: "3",
          label: "不通过"
        }
      ],
      auditingFlagStatus: [
        {
          auditingFlag: "2",
          label: "通过"
        },
        {
          auditingFlag: "3",
          label: "不通过"
        }
      ],
      deployFlags: [
        {
          deployFlag: "",
          label: "所有"
        },
        {
          deployFlag: "1",
          label: "待发布"
        },
        {
          deployFlag: "2",
          label: "灰度"
        },
        {
          deployFlag: "3",
          label: "发布"
        },
        {
          deployFlag: "4",
          label: "不发布"
        }
      ],
      dialogFormsVisible: false,
      formLabelWidth: "120px",
      //版本上传
      update: [
        {
          label: "不强制",
          value: "0"
        },
        {
          label: "强制",
          value: "1"
        }
      ],
      upload: {
        updateFlag: "",
        appName: "",
        appVersion: "",
        fileName: "",
        packageName: ""
      },

      //灰度
      dialogGray: false,
      gray: {
        telephone: "",
        email: "",
        createTimeEnd: "",
        createTimeStart: "",
        isNormal: "",
        pageNo: 1,
        pageSize: 10,
        userName: ""
      },
      adgray: {
        id: "",
        userIdList: []
      },
      grayData: [],
      grayTable: [],
      grayRow: "",
      importFileUrl: ""
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
      this.transmissionParam.id = row.id;
      this.auditingFlag = row.auditingFlag;
      this.deployFlag = row.deployFlag;
      this.updateFlag = row.updateFlag;
    },
    getList() {
      this.param.auditingFlag = "";
      this.loading = true;
      setTimeout(() => {
        searchAppVersion(this.param).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          //把原来选中行的数据清除
          this.currentRow = "";
          this.transmissionParam.id = "";
          this.auditingFlag = "";
          this.deployFlag = "";
          this.updateFlag = "";
          this.loading = false;
          this.isRepeat = false;
        });
      }, 1000);
    },
    //每页显示的条数
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.param.pageSize = currentPage;
      this.getList();
    },
    //根据参数进行查询
    search() {
      //参数查询数据
      this.loading = true;
      setTimeout(() => {
        searchAppVersion({
          appEnvironment: this.param.appEnvironment,
          appName: this.param.appName,
          appVersion: this.param.appVersion,
          auditingFlag: this.param.auditingFlag,
          deployFlag: this.param.deployFlag,
          pageNo: 1,
          pageSize: 10,
          updateFlag: this.param.updateFlag
        }).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.currentPage = 1;
          this.loading = false;
        });
      }, 500);
    },
    //清空
    empty() {
      this.param.appEnvironment = "";
      this.param.appName = "";
      this.param.appVersion = "";
      this.param.auditingFlag = "";
      this.param.deployFlag = "";
      this.param.updateFlag = "";
      this.getList();
    },
    updateFlagFormat(row, column) {
      if (row.updateFlag == "0") {
        return "不强制";
      } else if (row.updateFlag == "1") {
        return "强制";
      }
    },
    auditingFlagFormat(row, column) {
      if (row.auditingFlag == "1") {
        return "待审核";
      } else if (row.auditingFlag == "2") {
        return "通过";
      } else if (row.auditingFlag == "3") {
        return "不通过";
      }
    },
    deployFlagFormat(row, column) {
      if (row.deployFlag == "1") {
        return "待发布";
      } else if (row.deployFlag == "2") {
        return "灰度";
      } else if (row.deployFlag == "3") {
        return "发布";
      } else if (row.deployFlag == "4") {
        return "不发布";
      }
    },
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },
    //下载
    download() {
      if (this.currentRow == "" || this.currentRow == null) {
        this.msgTip("error", "请选择需要下载的记录");
      } else {
        if (this.isFlag) {
          this.isFlag = false;
          download(this.transmissionParam.id).then(res => {
            if(res.success){
                window.location.href = res.url;
            }
            if (!res.success) {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
            }
            this.isFlag = true;
            this.getList();
          });
        }
      }
    },
    //删除
    del() {
      if (this.currentRow == "" || this.currentRow == null) {
        this.msgTip("error", "请选择需要删除的记录");
      } else {
        if (this.auditingFlag != "1" || this.deployFlag != "1") {
          this.$message({
            type: "warning",
            message: "仅能删除待发布且未审核的版本",
            center: true
          });
        } else {
          this.$confirm("您是否要删除当前选中的记录?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() => {
            //进行删除
            deleteApp(this.transmissionParam.id).then(res => {
              if (res.success) {
                this.$message({
                  type: "success",
                  message: "操作成功",
                  center: true
                });
                this.getList();
              } else {
                //删除失败
                this.$message({
                  type: "warning",
                  message: "操作失败",
                  center: true
                });
              }
            });
          });
        }
      }
    },
    //撤销
    undo() {
      if (this.currentRow == "" || this.currentRow == null) {
        this.msgTip("error", "请选择需要撤销的记录");
      } else {
        if (this.deployFlag != "3") {
          this.$message({
            type: "warning",
            message: "仅能撤销发布的版本",
            center: true
          });
        } else {
          if (this.isFlag) {
            this.isFlag = false;
            //撤销操作
            undo(this.transmissionParam.id).then(res => {
              if (res.success) {
                this.$message({
                  type: "success",
                  message: "操作成功",
                  center: true
                });
                // 获取当前日期时间
                dataManipulation(
                  this.tableData,
                  this.currentRow,
                  "",
                  4,
                  getDate()
                );
                this.isFlag = true;
              } else {
                //删除失败
                this.$message({
                  type: "warning",
                  message: "操作失败",
                  center: true
                });
                this.isFlag = true;
              }
            });
          }
        }
      }
    },
    //审核点击按钮
    audit() {
      if (this.currentRow == "" || this.currentRow == null) {
        this.msgTip("error", "请选择需要审核的记录");
      } else {
        console.log(this.auditingFlag)
        console.log(this.deployFlag)
        if (this.auditingFlag != "1" || this.deployFlag != "2") {
          this.$message({
            type: "warning",
            message: "仅能审核状态为待审核及发布状态是灰度的版本",
            center: true
          });
        } else {
          //显示审核框
          this.dialogFormVisible = true;
          this.isDisable = false;
        }
      }
    },
    //审核修改事件
    save() {
      if (this.param.auditingFlag == null || this.param.auditingFlag == "") {
        this.$message({
          type: "warning",
          message: "审核操作不能为空",
          center: true
        });
      } else {
        //把下拉框的值赋值给传输对象
        this.transmissionParam.auditingFlag = this.param.auditingFlag;
        this.isRepeat = true;
        this.isDisable = true;
        audit(this.transmissionParam).then(res => {
          if (res.success) {
            this.$message({
              type: "success",
              message: "操作成功",
              center: true
            });
            this.auditingFlag = "2"
            // 获取当前日期时间
            dataManipulation(
              this.tableData,
              this.currentRow,
              this.transmissionParam.auditingFlag,
              "",
              getDate()
            );
            this.dialogFormVisible = false;
          } else {
            this.$message({
              type: "warning",
              message: "操作失败",
              center: true
            });
            this.isRepeat = false;
            this.isDisable = false;
          }
        });
      }
    },
    //发布
    deploy() {
      if (this.currentRow == "" || this.currentRow == null) {
        this.msgTip("error", "请选择需要发布的记录");
      } else {
        if (this.auditingFlag != "2" || this.deployFlag != 2) {
          this.$message({
            type: "warning",
            message: "仅能发布灰度且审核通过的版本",
            center: true
          });
        } else {
          if (this.isFlag) {
            this.isFlag = false;
            deploy(this.transmissionParam.id).then(res => {
              if (res.success) {
                this.$message({
                  type: "success",
                  message: "操作成功",
                  center: true
                });
                this.deployFlag = "3"
                // 获取当前日期时间
                dataManipulation(
                  this.tableData,
                  this.currentRow,
                  "",
                  "3",
                  getDate()
                );
                this.dialogFormVisible = false;
                this.isFlag = true;
              } else {
                this.$message({
                  type: "warning",
                  message: "操作失败",
                  center: true
                });
                this.isFlag = true;
              }
            });
          }
        }
      }
    },

    //版本上传
    appupload() {
      this.upload.updateFlag = "";
      this.upload.appName = "";
      this.upload.appVersion = "";
      this.upload.fileName = "";
      this.upload.packageName = "";
      this.upload.updateFlag = "";
      this.uplaodApp = "点击上传";
      this.disabledKeep == true;
      this.dialogFormsVisible = true;
    },
    submitUpload() {
      const fileData = this.$refs.uploadFile.files[0];
      let formFile = new FormData();
      formFile.append("file", fileData);
      this.loading = true;
      getCheckUpload(formFile).then(res => {
        if (res.success) {
          this.msgTip("success", "上传成功");
          this.uplaodApp = "已上传";
          this.$refs.uploadFile.value = null;
          this.upload.fileName = res.voT.fileName;
          this.upload.appName = res.voT.appName;
          this.upload.appVersion = res.voT.appVersion;
          this.upload.packageName = res.voT.packageName;
          this.loading = false;
          this.disabledKeep = false;
        } else {
          this.msgTip("error", res.errorMsg);
          this.loading = false;
          this.$refs.uploadFile.value = null;
        }
      });
    },
    keep() {
      if (this.upload.updateFlag == "") {
        this.msgTip("warning", "请选择是否强制！");
      } else {
        if (this.disabledKeep == true) {
          return;
        }
        this.isRepeat = true;
        this.loading = true;
        this.disabledKeep = true;
        getUpload(this.upload).then(res => {
          if (res.success) {
            this.dialogFormsVisible = false;
            this.loading = false;
            this.msgTip("success", "版本上传成功！");
            this.$refs.uploadFile.value = [];
            this.empty();
          } else {
            this.msgTip("error", res.errorMsg);
            this.$refs.uploadFile.value = [];
            this.loading = false;
            this.isRepeat = false;
          }
        });
      }
    },

    //灰度
    grays() {
      if (this.currentRow == "" || this.currentRow == null) {
        this.msgTip("error", "请选择需要灰度的记录！");
      } else {
         this.grayTable = [];
         this.querGrayById(this.currentRow.id);
         this.grayData = [];
         this.dialogGray = true;
         this.graySearchDisabled = true;
         this.gray.telephone = "";
         this.gray.email = "";
      }
    },
    querGrayById(id){
      querGray(id).then(res => {
        if (res.success) {
            this.grayTable = res.voList;
        } else {
            this.msgTip("error", res.errorMsg);
        }
     })
    },
    //灰度查询
    graySearch() {
      getUsercontrol(this.gray).then(res => {
        if (res.success) {
          this.grayData = res.voList;
        } else {
          this.msgTip("error", res.errorMsg);
        }
      });
    },
    //灰度清空
    grayEmpty() {
      this.gray.telephone = "";
      this.gray.email = "";
      this.graySearchDisabled = true;
    },
    //灰度添加
    addGray() {
      if (this.grayRow == "") {
        this.msgTip("warning", "请选择需要添加的记录！");
      } else {
        this.adgray.id = this.currentRow.id;
        addGray(this.adgray).then(res => {
          if (res.success) {
            this.deployFlag = 2;
            this.querGrayById(this.currentRow.id);
            // 获取当前日期时间
            dataManipulation(this.tableData, this.currentRow, "", 2, getDate());
          } else {
            this.msgTip("error", res.errorMsg);
          }
        });
      }
    },
    //灰度删除
    dele(row) {
      delGray(row.id).then(res => {
        if (res.success) {
          querGray(this.currentRow.id).then(res => {
            if (res.success) {
              this.grayTable = res.voList;
            } else {
              this.msgTip("error", res.errorMsg);
            }
          });
        }
      });
    },
    handleSelectionChange(val) {
      this.grayRow = val;
      for (var i = 0; i < val.length; i++) {
        this.adgray.userIdList.push(val[i].userId);
      }
    },
    getInputGray(event){
      if(this.gray.telephone != "" || this.gray.email != ""){
        this.graySearchDisabled = false
      }else{
        this.graySearchDisabled = true
      }
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
.appupload {
  list-style: none;
  .appupload-head {
    padding-right: 20px;
    ul {
      width: 100%;
      display: flex;
      padding-left: 0;
      li {
        flex: 1;
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
  .gray-head {
    ul {
      width: 100%;
      padding-left: 0;
      display: flex;
      align-items: flex-end;
      li {
        margin-left: 20px;
      }
      li:nth-child(1) {
        margin-left: 0;
      }
    }
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
}
</style>
<style>
.equiinfo .el-table td {
  width: 300px;
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
.test .el-dialog__body {
  padding: 0 20px !important;
}
</style>

