<template>
  <div class="firwareupload">
    <div class="firwareupload-head">
      <ul>
        <li>
          <h5>版本号</h5>
          <el-input v-model="form.firmwareVersion"></el-input>
        </li>
        <li>
          <h5>状态</h5>
          <el-select v-model="form.status" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>跳过</h5>
          <el-select v-model="form.isStride" placeholder="请选择">
            <el-option
              v-for="item in isStrides"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>起始时间</h5>
          <div class="block">
            <el-date-picker v-model="form.beginTime" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
          </div>
        </li>
        <li>
          <h5>截止时间</h5>
          <div class="block">
            <el-date-picker v-model="form.endTime" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
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
        <li v-permit="'/firmware/upload'">
          <img src="../../assets/iconfont/upload.png" alt />
          <span @click="upload">上传</span>
        </li>
        <li v-permit="'/firmware/auditTest'">
          <img src="../../assets/iconfont/log.png" alt />
          <span @click="stride">跳过</span>
        </li>
        <li v-permit="'/firmware/delete'">
          <img src="../../assets/iconfont/delete.png" alt />
          <span @click="dele">删除</span>
        </li>
        <li v-permit="'/firmware/delete'">
          <img src="../../assets/iconfont/amend.png" alt />
          <span @click="change">修改固件设备</span>
        </li>
        <li v-permit="'/firmware/auditTest'">
          <img src="../../assets/iconfont/audit.png" alt />
          <span @click="checkTest">审核测试</span>
        </li>
        <li v-permit="'/firmware/auditTest'">
          <img src="../../assets/iconfont/check.png" alt />
          <span @click="check">审核</span>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
        @expand-change="rowExpand"
        highlight-current-row
        @row-click="handleClick"
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-table :data="props.row.children" style="width: 100%" border>
              <el-table-column type="index" :index="indexMethod"></el-table-column>
              <el-table-column prop="type" label="文件名"></el-table-column>
              <el-table-column prop="version" label="版本号"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <div class="function-icon">
                    <el-tooltip class="item" effect="dark" content="版本信息" placement="bottom">
                      <img
                        @click="childrenDetailed(scope.row)"
                        src="../../assets/iconfont/mess.png"
                        alt
                      />
                    </el-tooltip>
                  </div>
                </template>
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column label="区域" prop="area"></el-table-column>
        <el-table-column label="定制" prop="custom" width="100px"></el-table-column>
        <el-table-column label="配置" prop="configuration" width="100px"></el-table-column>
        <el-table-column label="版本号" width="250px" prop="firmwareVersion"></el-table-column>
        <el-table-column label="跳过" prop="isStride" :formatter="isStrideFormat" width="60px"></el-table-column>
        <el-table-column label="状态" prop="status" width="100px"></el-table-column>
        <el-table-column label="审核标识" prop="auditFlag" :formatter="stateFormat" width="100px"></el-table-column>
        <el-table-column label="审核信息" prop="auditInfo" show-overflow-tooltip></el-table-column>
        <el-table-column label="审核人" prop="auditUser"></el-table-column>
        <el-table-column label="审核时间" prop="auditTime"></el-table-column>
        <el-table-column label="上传时间" prop="opTime"></el-table-column>
        <el-table-column label="上传人员" prop="opUser"></el-table-column>
        <el-table-column label="操作" width="100px">
          <template slot-scope="scope">
            <div class="function-icon">
              <el-tooltip class="item" effect="dark" content="版本信息" placement="bottom">
                <img @click="detailed(scope.row)" src="../../assets/iconfont/mess.png" alt />
              </el-tooltip>
            </div>
          </template>
        </el-table-column>
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

    <el-dialog title="版本信息" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="form" class="form">
        <el-form-item label="版本号" :label-width="formLabelWidth">
          <el-input v-model="info.firmwareVersion" autocomplete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="修改点" :label-width="formLabelWidth">
          <p class="exchange">{{info.modifyPoint}}</p>
        </el-form-item>
        <el-form-item label="影响范围" :label-width="formLabelWidth">
          <el-input v-model="info.impactScope" autocomplete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="版本目的" :label-width="formLabelWidth">
          <el-input v-model="info.versionPurpose" autocomplete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="升级范围" :label-width="formLabelWidth">
          <el-input v-model="info.upgrageScope" autocomplete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="合入版本" :label-width="formLabelWidth">
          <div class="version-content">
            <div class="version-title">
              <div>模块名字</div>
              <div>版本号</div>
            </div>
            <div class="version">
              <div class="version-list">
                <div>app</div>
                <div>{{app}}</div>
              </div>
              <div class="version-list">
                <div>app_lib</div>
                <div>{{app_lib}}</div>
              </div>
              <div class="version-list">
                <div>system</div>
                <div>{{system}}</div>
              </div>
              <div class="version-list">
                <div>system_lib</div>
                <div>{{system_lib}}</div>
              </div>
            </div>
          </div>
        </el-form-item>
        <el-form-item label="打包时间" :label-width="formLabelWidth">
          <el-input v-model="info.packagingTime" autocomplete="off" :readonly="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible=false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="版本信息" :visible.sync="dialogVisibles" :close-on-click-modal="false">
      <el-form :model="form" class="form">
        <el-form-item label="版本号：" :label-width="formLabelWidth">
          <el-input v-model="info.version" autocomplete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="修改点：" :label-width="formLabelWidth">
          <p class="exchange">{{info.modifyPoint}}</p>
        </el-form-item>
        <el-form-item label="影响范围：" :label-width="formLabelWidth">
          <el-input v-model="info.impactScope" autocomplete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="版本目的：" :label-width="formLabelWidth">
          <el-input v-model="info.versionPurpose" autocomplete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="升级范围：" :label-width="formLabelWidth">
          <el-input v-model="info.upgrageScope" autocomplete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="打包时间：" :label-width="formLabelWidth">
          <el-input v-model="info.packagingTime" autocomplete="off" :readonly="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibles=false">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="上传固件包"
      :visible.sync="dialogFormVisible"
      @close="uploadClose"
      :close-on-click-modal="false"
      width = "32%"
    >
      <el-form
        v-loading="loading"
        :model="uploads"
        :rules="uploadRules"
        ref="uploads"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="请输入密码：" :label-width="formLabelWidth" prop="password" style="width:100%">
          <el-input
            v-model="uploads.password"
            autocomplete="off"
            type="password"
            :disabled="passdis"
          ></el-input>
        </el-form-item>
        <el-form-item label="跳过：" :label-width="formLabelWidth">
          <el-select v-model="uploads.isStride" placeholder="请选择" prop="isStride" style="width:100%">
            <el-option
              v-for="item in isStrides"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备型号：" :label-width="formLabelWidth">
          <el-checkbox-group @change="this.typeChange" v-model="checkList" width="50%">
            <el-checkbox
              style="width:200px;height:30px;"
              v-for="(item, i) in type"
              :label="item.value"
              name="type"
              :key="i"
            >{{item.valueCn}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="上传固件包：" :label-width="formLabelWidth">
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
            <el-button class="exprot-btn" size="small" type="primary" :disabled="this.disab">
              <span>{{buttonUpload}}</span>
            </el-button>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save" :disabled="savedisab">{{saveButton}}</el-button>
        <el-button @click="uploadClose">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="跳过"
      :visible.sync="dialogStride"
      @close="strideClose"
      :close-on-click-modal="false"
    >
      <el-form :model="form">
        <el-form-item label="版本号：" :label-width="formLabelWidth">
          <el-input v-model="strideForm.firmwareVersion" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="是否跳过：" :label-width="formLabelWidth">
          <el-select v-model="strideForm.isStride" placeholder="请选择">
            <el-option
              v-for="item in isStrides"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="strideSave" :disabled="saveButtonDisable">保存</el-button>
        <el-button @click="strideClose">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="修改固件设备"
      :visible.sync="dialogFromsVisible"
      @close="infoClose"
      :close-on-click-modal="false"
    >
      <el-form :model="form">
        <el-form-item label="版本号：" :label-width="formLabelWidth">
          <el-input v-model="info.version" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="设备型号：" :label-width="formLabelWidth">
          <el-checkbox-group
            @change="typeChange"
            v-for="(item, i) in type"
            v-model="checkList"
            :key="i"
          >
            <el-checkbox
              :label="item.value"
              name="type"
              :disabled="!!(oldCheckList.indexOf(item.value) !== -1)"
            >{{item.valueCn}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="exchangeSave" :disabled="isDisable">保存</el-button>
        <el-button @click="infoClose">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="审核测试"
      :visible.sync="dialogTest"
      @close="testClose"
      class="test"
      :close-on-click-modal="false"
    >
      <div v-loading="testloading">
        <div class="test-head">
          <ul>
            <li>
              <h5>设备型号</h5>
              <el-select
                v-model="test.equipmentModel"
                placeholder="请选择"
                @focus="getFirst()"
                @change="handleFirst"
                filterable
                style="width:100%"
              >
                <el-option
                  v-for="item in model"
                  :key="item.equipmentModel"
                  :label="item.equipmentModelName"
                  :value="item.equipmentModel"
                ></el-option>
              </el-select>
            </li>
            <li>
              <h5 v-if="test.equipmentModel!=''">设备用途</h5>
              <el-select
                v-if="test.equipmentModel!=''"
                v-model="test.equipmentPurpose"
                @focus="getSecond()"
                placeholder="请选择"
                style="width:100%"
              >
                <el-option
                  v-for="item in purpose"
                  :key="item.equipmentPurposeValue"
                  :label="item.equipmentPurposeName"
                  :value="item.equipmentPurposeValue"
                ></el-option>
              </el-select>
            </li>
            <li>
              <h5>设备号</h5>
              <el-input v-model="test.equipmentNumber"></el-input>
            </li>
            <li>
              <el-button type="primary" @click="testSearch">查询</el-button>
            </li>
            <li>
              <el-button type="primary" @click="testEmpty">清空</el-button>
            </li>
            <li>
              <el-button type="primary" @click="addTest">添加</el-button>
            </li>
          </ul>
        </div>
        <div style="overflow-y:auto; height:505px;">
          <div class="tab">
            <el-table
              :data="testData"
              style="width: 100%"
              border
              highlight-current-row
              @selection-change="handleSelectionChange"
            >
              <el-table-column type="index" :index="indexMethod"></el-table-column>
              <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column prop="equipmentNumber" width="230" label="设备号"></el-table-column>
              <el-table-column prop="equipmentModel" label="设备型号"></el-table-column>
              <el-table-column prop="equipmentPurpose" label="设备用途"></el-table-column>
            </el-table>
          </div>
          <div class="table">
            <h5>审核任务列表</h5>
            <el-table :data="testTable" style="width: 100%" border>
              <el-table-column type="index" :index="indexMethod"></el-table-column>
              <el-table-column prop="equipmentNumber" label="设备号"></el-table-column>
              <el-table-column prop="firmwareVersion" label="当前版本"></el-table-column>
              <el-table-column prop="updateStatus" label="更新固件标识" :formatter="statusData"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope" v-show="flag">
                  <el-tooltip
                    v-if="scope.row['updateStatus']!=2"
                    class="item"
                    effect="dark"
                    content="删除"
                    placement="bottom"
                    v-permit="'/firmware/auditTest/delete'"
                  >
                    <img
                      style="width: 15px;vertical-align: middle;cursor:pointer"
                      @click="testDele(scope.row)"
                      src="../../assets/iconfont/delete.png"
                      alt
                    />
                  </el-tooltip>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="testClose">完 成</el-button>
      </div>
    </el-dialog>

    <el-dialog title="审核" :visible.sync="testDialog" @close="close" :close-on-click-modal="false">
      <el-form :model="form">
        <el-form-item label="审核标识：" :label-width="formLabelWidth">
          <el-select v-model="tests.auditFlag" style="width:100%">
            <el-option
              v-for="item in testOption"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注：" :label-width="formLabelWidth">
          <el-input maxlength="100" v-model="tests.auditInfo" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="testSave" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getFirware,
  getFirwarechildren,
  firwareUpload,
  deleteFirware,
  changeFirware,
  getDetailed,
  getChildDetailed,
  checkValidity,
  searchEquipment,
  getTest,
  getTestlists,
  finishTest,
  getUpload,
  testDelete,
  dataManipulation,
  stride
} from "../../api/firwareupload.js";
import { searchDiclist } from "../../api/dictionary.js";
import { getAllinfo, getStockInfo } from "../../api/equipment.js";
import { getPurpose, getModel } from "../../api/firwaretask.js";
import { getLanList } from "../../api/firmwarepair.js";
export default {
  data() {
    return {
      isDisable: false,
      form: {
        firmwareVersion: "",
        status: "",
        beginTime: "",
        endTime: "",
        isStride: "",
        pageNo: 1,
        pageSize: 10
      },
      options: [
        {
          value: 0,
          label: "未审核"
        },
        {
          value: 1,
          label: "审核测试中"
        },
        {
          value: 2,
          label: "已审核"
        }
      ],
      isStrides: [
        {
          value: 0,
          label: "否"
        },
        {
          value: 1,
          label: "是"
        }
      ],
      tableData: [],
      rowData: [],
      rowid: "",
      buttonUpload: "点击上传",
      saveButton: "保存",
      disab: false,
      savedisab: true,
      loading: false,
      isRepeat: false,
      passdis: false,
      testloading: false,
      dialogStride: false,
      saveButtonDisable: false,
      //展开行数据
      tableDatas: [],

      //版本信息
      info: {
        firmwareVersion: "",
        impactScope: "",
        modifyPoint: "",
        upgrageScope: "",
        versionPurpose: "",
        packagingTime: "",
        version: ""
      },
      strideForm: {
        firmwareVersion: "",
        isStride: "否"
      },
      //版本详细信息
      dialogVisible: false,
      formLabelWidth: "100px",
      infoTable: [],
      system_lib: "",
      app: "",
      app_lib: "",
      system: "",

      //子版本详细信息
      dialogVisibles: false,

      //上传固件包
      checkList: [],
      oldCheckList: [],
      password: "",
      dialogFormVisible: false,
      fileName: "",
      type: [],
      uploads: {
        password: "",
        isStride: ""
      },
      typeVal: [],
      uploadRules: {
        password: [{ required: true, trigger: "blur" }]
      },
      firmwareVersion: "",
      //修改固件设备
      dialogFromsVisible: false,
      total: 0,
      currentPage: 1,
      //审核测试
      dialogTest: false,
      test: {
        equipmentNumberList: [],
        batchNo: "",
        deployVersion: "",
        endTime: "",
        equipmentModel: "",
        equipmentNumber: "",
        equipmentPurpose: "",
        firmwareVersion: "",
        flag: "",
        healthyState: "",
        pageNo: 1,
        pageSize: 10,
        startTime: ""
      },
      model: [],
      purpose: [],
      testData: [],
      testRow: "",
      flag: false,
      testDialog: false,
      //审核
      testTable: [],
      tests: {
        auditFlag: "同意",
        auditInfo: ""
      },
      testOption: [
        {
          label: "同意",
          value: "Y"
        },
        {
          label: "不同意",
          value: "N"
        }
      ]
    };
  },
  created() {
    this.getList();
    getLanList({
      name: "EQUIPMENT_MODEL",
      value: ""
    }).then(res => {
      res.dictionaryVOList.forEach(v => {
        v.value = v.value * 1;
      });
      this.type = res.dictionaryVOList;
      console.log(this.type);
    });
  },
  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        getFirware(this.form).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.loading = false;
          this.isRepeat = false;
        });
      }, 500);
    },
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.form.pageNo = currentPage;
      this.getList();
    },
    rowExpand(row, expandedRows) {
      this.rowid = row.id;
      if (!row.children) {
        getFirwarechildren({ id: this.rowid, pageNo: 1, pageSize: 10 }).then(
          res => {
            this.tableDatas = res.voList;
            this.tableData.map(item => {
              if (row.id == item.id) {
                item.children = res.voList;
              }
            });
          }
        );
      }
    },
    handleClick(val) {
      this.rowData = val;
      this.current = val;
      this.strideForm.firmwareVersion = val.firmwareVersion;
      if (val.isStride == 1 || val.isStride == "1") {
        this.strideForm.isStride = "是";
      }
    },
    stateFormat(row, column) {
      if (row.auditFlag === "Y") {
        return "同意";
      } else if (row.auditFlag === "N") {
        return "不同意";
      }
    },

    isStrideFormat(row) {
      if (row.isStride === "0" || row.isStride === 0) {
        return "否";
      } else if (row.isStride === "1" || row.isStride === 1) {
        return "是";
      }
    },
    //信息提示框
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },

    //查询
    search() {
      this.loading = true;
      setTimeout(() => {
        getFirware({
          firmwareVersion: this.form.firmwareVersion,
          status: this.form.status,
          beginTime: this.form.beginTime,
          endTime: this.form.endTime,
          isStride: this.form.isStride,
          pageNo: 1,
          pageSize: 10
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
      this.form.firmwareVersion = "";
      this.form.status = "";
      this.form.beginTime = "";
      this.form.endTime = "";
      this.form.isStride = "";
      this.current = [];
      this.getList();
    },
    uploadClose() {
      this.dialogFormVisible = false;
      this.checkList = [];
      this.uploads.isStride = "";
      this.$refs.uploads.resetFields();
    },
    infoClose() {
      this.dialogFromsVisible = false;
      this.checkList = [];
    },
    //多选框
    typeChange(val) {},
    //上传
    upload() {
      this.buttonUpload = "点击上传";
      this.saveButton = "保存";
      this.disab = false;
      this.savedisab = true;
      this.passdis = false;
      this.dialogFormVisible = true;
      this.isDisable = false;
    },
    submitUpload() {
      const fileData = this.$refs.uploadFile.files[0];
      console.log(fileData, "0000");
      let formFile = new FormData(); //加入文件对象
      formFile.append("file", fileData);
      if (this.uploads.password === "") {
        this.msgTip("error", "请输入密码!");
      } else {
        if (this.buttonUpload != "点击上传") {
          return;
        }
        var index = fileData.name.lastIndexOf(".");
        var str = fileData.name.substring(index + 1, fileData.name.length);
        const extension = str === "zip";
        if (!extension) {
          this.msgTip("error", "上传固件只能是zip格式!");
          return;
        } else {
          this.loading = true;
          checkValidity(this.uploads.password, formFile).then(res => {
            if (res.success) {
              this.firmwareVersion = res.voT.firmwareVersion;
              this.buttonUpload = res.voT.firmwareVersion;
              this.disab = true;
              this.loading = false;
              this.passdis = true;
              this.savedisab = false;
              this.$refs.uploadFile.value = null;
            } else {
              this.loading = false;
              this.msgTip("error", res.errorMsg);
              this.$refs.uploadFile.value = null;
            }
          });
        }
      }
    },
    save() {
      if (this.checkList == null || this.checkList == "") {
        this.msgTip("error", "设备型号不能为空!");
        return;
      }
      if (this.uploads.password === "") {
        this.msgTip("error", "请输入密码!");
      } else {
        if (this.uploads.isStride === "") {
          this.msgTip("error", "请选择是否跳过");
          return;
        }
        if (this.savedisab == true) {
          return;
        }
        this.savedisab = true;
        this.saveButton = "正在保存";
        this.isRepeat = true;
        this.loading = true;
        getUpload({
          equipmentModel: this.checkList,
          firmwareVersion: this.firmwareVersion,
          password: this.uploads.password,
          isStride: this.uploads.isStride
        }).then(res => {
          if (res.success) {
            this.msgTip("success", "上传成功");
            this.$refs.uploadFile.value = [];
            this.dialogFormVisible = false;
            this.uploadClose();
            this.empty();
          } else {
            this.msgTip("error", res.errorMsg);
            this.$refs.uploadFile.value = [];
            this.buttonUpload = "点击上传";
            this.saveButton = "保存";
            this.isRepeat = false;
            this.loading = false;
          }
        });
      }
    },

    //跳过
    stride() {
      if (this.current == undefined || this.current == "") {
        this.msgTip("error", "请选择一条数据！");
      } else {
        this.saveButtonDisable = false;
        this.dialogStride = true;
      }
    },
    strideSave() {
      if (this.strideForm.isStride == "否") {
        this.strideForm.isStride = "0";
      }
      if (this.strideForm.isStride == "是") {
        this.strideForm.isStride = "1";
      }
      this.saveButtonDisable = true;
      stride({
        firmwareVersion: this.strideForm.firmwareVersion,
        isStride: this.strideForm.isStride
      }).then(res => {
        if (res.success) {
          this.dialogStride = false;
          // 获取当前日期时间
          dataManipulation(
            this.tableData,
            this.rowData,
            "",
            "",
            "",
            this.strideForm.isStride,
            ""
          );
          this.msgTip("success", "修改成功");
        } else {
          this.msgTip("error", res.errorMsg);
          this.isRepeat = false;
          this.saveButtonDisable = false;
        }
      });
    },
    //删除
    dele() {
      if (this.current == undefined) {
        this.msgTip("error", "请选择需要删除的数据！");
      } else {
        this.$confirm("您是否要删除当前选中的记录?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.isRepeat = true;
          deleteFirware(this.current.id).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "删除成功",
                center: true
              });
              this.getList();
            } else {
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
    },

    //修改固件设备
    change() {
      if (this.current == undefined || this.current == "") {
        this.msgTip("error", "请选择需要修改的数据！");
      } else {
        this.info.version = this.current.firmwareVersion;
        this.dialogFromsVisible = true;
        this.isDisable = false;
        searchEquipment({
          equipmentModel: "",
          firmwareVersion: this.current.firmwareVersion,
          pageNo: "",
          pageSize: ""
        }).then(res => {
          this.checkList = res.voList.map(v => v.equipmentModel);
          this.oldCheckList = this.checkList;
          console.log(this.checkList);
          console.log(this.oldCheckList, "asd");
        });
      }
    },
    //修改保存
    exchangeSave() {
      this.isRepeat = true;
      this.isDisable = true;
      changeFirware({
        firmwareVersion: this.current.firmwareVersion,
        equipmentModelList: this.checkList
      }).then(res => {
        if (res.success) {
          this.dialogFromsVisible = false;
          this.msgTip("success", "修改成功");
        } else {
          this.msgTip("error", res.errorMsg);
          this.isRepeat = false;
          this.isDisable = false;
        }
      });
    },

    //查看父元素详细信息
    detailed(row) {
      this.dialogVisible = true;
      getDetailed(row.id).then(res => {
        const list = res.voT;
        this.info.firmwareVersion = list.firmwareVersion;
        this.info.modifyPoint = list.modifyPoint;
        this.info.impactScope = list.impactScope;
        this.info.versionPurpose = list.versionPurpose;
        this.info.upgrageScope = list.upgrageScope;
        this.info.packagingTime = list.packagingTime;
        this.infoTable = list.mergeVersion;
        this.app = list.mergeVersion.app;
        this.app_lib = list.mergeVersion.app_lib;
        this.system = list.mergeVersion.system;
        this.system_lib = list.mergeVersion.system_lib;
        this.strideForm.firmwareVersion = list.firmwareVersion;
        this.strideForm.isStride = list.isStride;
      });
    },
    childrenDetailed(row) {
      this.dialogVisibles = true;
      getChildDetailed(row.id).then(res => {
        const list = res.voT;
        this.info.version = list.version;
        this.info.modifyPoint = list.modifyPoint;
        this.info.impactScope = list.impactScope;
        this.info.versionPurpose = list.versionPurpose;
        this.info.upgrageScope = list.upgrageScope;
        this.info.packagingTime = list.packagingTime;
        this.infoTable = list.mergeVersion;
      });
    },

    //审核测试
    checkTest() {
      if (this.current == undefined || this.current == null) {
        this.msgTip("error", "请选择需要审核测试的固件！");
      } else {
        if (this.current.status == "已审核") {
          this.msgTip("warning", "已审核，不允许再次被审核测试和审核！");
        } else {
          this.getTestlist();
          this.dialogTest = true;
        }
      }
    },
    testClose() {
      this.dialogTest = false;
      this.testData = [];
      this.test.equipmentModel = "";
      this.test.equipmentPurpose = "";
      this.test.equipmentNumber = "";
      this.testTable = [];
    },
    getFirst() {
      getModel({
        firmwareVersion: this.current.firmwareVersion
      }).then(res => {
        this.test.equipmentPurpose = "";
        this.model = res.voList;
      });
    },
    handleFirst(val) {
      this.test.equipmentModel = val;
    },
    getSecond() {
      getPurpose(this.test.equipmentModel).then(res => {
        this.purpose = res.voList;
      });
    },
    testSearch() {
      this.test.equipmentNumberList = [];
      if (this.testTable != null && this.testTable != "") {
        this.testTable.forEach(element => {
          this.test.equipmentNumberList.push(element.equipmentNumber);
        });
      }
      if (this.test.equipmentModel == "" && this.test.equipmentPurpose == "") {
        this.msgTip("warning", "设备用途和设备型号必须同时勾选");
      } else if (
        this.test.equipmentModel == "" ||
        this.test.equipmentPurpose == ""
      ) {
        this.msgTip("warning", "设备用途和设备型号必须同时勾选");
      } else {
        getStockInfo(this.test).then(res => {
          this.testData = res.voList;
        });
      }
    },
    testEmpty() {
      this.test.equipmentModel = "";
      this.test.equipmentPurpose = "";
      this.test.equipmentNumber = "";
      this.testData = [];
    },
    handleSelectionChange(val) {
      this.testRow = val;
    },
    addTest() {
      const arr = [];
      this.testRow.map(res => {
        arr.push(res.equipmentNumber);
        this.test.equipmentNumberList.push(res.equipmentNumber);
      });
      if (this.testRow == "") {
        this.msgTip("warning", "请选择设备序列号");
      } else {
        this.testloading = true;
        getTest({
          equipmentModel: this.test.equipmentModel,
          equipmentNumber: this.test.equipmentNumber,
          equipmentNumberList: arr,
          equipmentPurpose: this.test.equipmentPurpose,
          firmwareVersion: this.current.firmwareVersion
        }).then(res => {
          if (res.success) {
            this.msgTip("success", "添加成功");
            this.testloading = false;
            this.isRepeat = false;
            this.testData = this.testData.filter(
              v => !this.testRow.includes(v)
            );
            this.getTestlist();
            this.getList();
          } else {
            this.testloading = false;
            this.isRepeat = false;
            this.msgTip("error", res.errorMsg);
          }
        });
      }
    },
    testDele(val) {
      const equipment = val.equipmentNumber;
      testDelete(equipment).then(res => {
        if (res.success) {
          this.getTestlist();
          this.msgTip("success", "删除成功");
          this.getList();
          this.test.equipmentNumberList = [];
          if (this.testTable != null && this.testTable != "") {
            this.testTable.forEach(element => {
              if (element.equipmentNumber !== val.equipmentNumber) {
                this.test.equipmentNumberList.push(element.equipmentNumber);
              }
            });
          }
          if (this.testData && this.testData.length > 0) {
            getStockInfo(this.test).then(res => {
              if (res.data.success) {
                this.testData = res.voList;
              }
            });
          }
        }
      });
    },
    getTestlist() {
      getTestlists({
        firmwareVersion: this.current.firmwareVersion,
        pageNo: 1,
        pageSize: 10
      }).then(res => {
        this.testTable = res.voList;
      });
    },
    statusData(row) {
      console.log(row.updateStatus, "00000");
      if (row.updateStatus == 0) {
        this.flag = false;
        return "初始版本";
      } else if (row.updateStatus == 1) {
        this.flag = true;
        return "未更新";
      } else if (row.updateStatus == 2) {
        this.flag = false;
        return "已更新";
      }
    },
    //审核
    check() {
      if (this.current == undefined || this.current == null) {
        this.msgTip("warning", "请选择需要审核测试的固件！");
      } else {
        if (this.current.status == "已审核") {
          this.msgTip("warning", "已审核，不允许再次被审核测试和审核！");
        } else {
          this.tests.auditFlag = "Y";
          this.testDialog = true;
          this.isDisable = false;
        }
      }
    },
    close() {
      this.testDialog = false;
      this.tests.auditInfo = "";
    },
    strideClose() {
      this.dialogStride = false;
    },
    testSave() {
      this.isDisable = true;
      finishTest({
        auditFlag: this.tests.auditFlag,
        auditInfo: this.tests.auditInfo,
        id: this.current.id
      }).then(res => {
        if (res.success) {
          this.msgTip("success", "审核通过");
          this.testDialog = false;
          // 获取当前日期 时间
          dataManipulation(
            this.tableData,
            this.rowData,
            "已审核",
            this.tests.auditFlag,
            this.tests.auditInfo,
            "",
            getDate()
          );
        } else {
          this.msgTip("error", res.errorMsg);
          this.isDisable = false;
        }
      });
    },
    indexMethod(index) {
      return index + 1;
    }
  }
};

/**
 * 获取当前时间.
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
.firwareupload {
  list-style: none;
  .firwareupload-head {
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
  .exchange {
    margin-top: 0;
  }
  .page {
    text-align: center;
    margin-top: 20px;
  }
  .test-head {
    ul {
      width: 100%;
      padding-left: 0;
      display: flex;
      align-items: flex-end;
      li {
        margin-right: 20px;
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
  .version-content {
    width: 200px;
    border: 1px solid;
    border-bottom: none;
    .version-title {
      display: flex;
      background: rgba(237, 237, 237, 1);
    }
    .version-title,
    .version-list {
      border-bottom: 1px solid;
      div {
        flex: 1;
        text-align: center;
      }
    }
    .version {
      .version-list {
        display: flex;
      }
    }
  }
}
</style>
<style>
.form .el-input__inner {
  border: none;
}
.el-table th {
  text-align: center;
}
.el-table td {
  text-align: center;
}
.test > .el-dialog > .el-dialog__body {
  padding: 0 20px 20px 20px;
}
</style>

