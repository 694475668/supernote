<template>
  <div class="equiinfo">
    <div class="equiinfo-head">
      <ul>
        <li>
          <h5>设备型号</h5>
          <el-select v-model="form.equipmentModel" placeholder="请选择">
            <el-option
              v-for="item in option"
              :key="item.value"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>设备号</h5>
          <el-input v-model="form.equipmentNumber"></el-input>
        </li>
        <li>
          <h5>批次号</h5>
          <el-input v-model="form.batchNo"></el-input>
        </li>
        <li>
          <h5>固件版本号</h5>
          <el-input v-model="form.firmwareVersion"></el-input>
        </li>
        <li>
          <h5>发布版本</h5>
          <el-input v-model="form.deployVersion"></el-input>
        </li>
        <li>
          <h5>出入库标识</h5>
          <el-select v-model="form.flag" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>健康状态</h5>
          <el-select v-model="form.healthyState" placeholder="请选择">
            <el-option
              v-for="item in health"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>入库起始时间</h5>
          <div class="block">
            <el-date-picker
              v-model="form.startTime"
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              type="date"
              :editable="false"
            ></el-date-picker>
          </div>
        </li>
        <li>
          <h5>入库截止时间</h5>
          <div class="block">
            <el-date-picker
              v-model="form.endTime"
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              type="date"
              :editable="false"
            ></el-date-picker>
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
        <li v-permit="'/stock/in'">
          <img src="../../assets/iconfont/put.png" alt />
          <span @click="tolead">入库</span>
        </li>
        <li v-permit="'/stock/out'">
          <img src="../../assets/iconfont/out.png" alt />
          <span @click="stock">出库</span>
        </li>
        <li v-permit="'/stock/undo'">
          <img src="../../assets/iconfont/repeal.png" alt />
          <span @click="repeal">撤销</span>
        </li>
        <li v-permit="'/stock/model/download/undo'">
          <img src="../../assets/iconfont/repeal.png" alt />
          <span @click="repealModel">批撤模板</span>
        </li>
        <li v-permit="'/stock/batch/undo'">
          <img src="../../assets/iconfont/repeal.png" alt />
          <div class="upload-wrapper">
            <input
              ref="repealFile"
              class="upload-file"
              id="file"
              type="file"
              name="file"
              multiple
              @change="repealAll"
            />
            <span>批量撤销</span>
          </div>
        </li>
        <li v-permit="'/stock/model/download/in'">
          <img src="../../assets/iconfont/lead.png" alt />
          <span @click="inmodel">批入模板</span>
        </li>
        <li v-permit="'/stock/batch/in'">
          <img src="../../assets/iconfont/leads.png" alt />
          <span @click="intos">批入</span>
        </li>
        <li v-permit="'/stock/model/download/out'">
          <img src="../../assets/iconfont/export.png" alt />
          <span @click="outmodel">批出模板</span>
        </li>
        <li v-permit="'/stock/batch/out'">
          <img src="../../assets/iconfont/exports.png" alt />
          <span @click="out">批出</span>
        </li>
        <li v-permit="'/stock/export'">
          <img src="../../assets/iconfont/derive.png" alt />
          <span @click="derive">导出</span>
        </li>
        <li v-permit="'/stock/healthy/state'">
          <img src="../../assets/iconfont/state.png" alt />
          <span @click="headlthy">健康状态</span>
        </li>
        <li v-permit="'/stock/purpose'">
          <img src="../../assets/iconfont/exchange.png" alt />
          <span @click="equipmentPurpo">设备用途</span>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
        @current-change="handleClick"
        highlight-current-row
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="equipmentModel" label="设备型号" width="160px"></el-table-column>
        <el-table-column prop="batchNo" label="批次号"></el-table-column>
        <el-table-column prop="equipmentNumber" label="设备号" width="160px"></el-table-column>
        <el-table-column prop="flag" label="出入库标识" :formatter="stateFormat"></el-table-column>
        <el-table-column prop="state" label="是否激活" :formatter="state"></el-table-column>
        <el-table-column prop="healthyState" label="健康状态"></el-table-column>
        <el-table-column prop="dealerId" label="经销商" show-overflow-tooltip></el-table-column>
        <el-table-column prop="equipmentPurpose" label="设备用途"></el-table-column>
        <el-table-column prop="remark" label="备注" show-overflow-tooltip></el-table-column>
        <el-table-column prop="firmwareVersion" label="固件版本号" width="280px"></el-table-column>
        <el-table-column prop="deployVersion" label="发布固件版本" width="160px"></el-table-column>
        <el-table-column prop="opUser" label="操作人"></el-table-column>
        <el-table-column prop="opTime" label="操作时间" show-overflow-tooltip></el-table-column>
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
      title="入库"
      :visible.sync="dialogFormsVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="lead"
        :rules="toRules"
        ref="lead"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="设备型号" :label-width="formLabelWidth" prop="equipmentModel">
          <el-select v-model="lead.equipmentModel" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in option"
              :key="item.value"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="批次号" :label-width="formLabelWidth" prop="batchNo">
          <el-input maxlength="30" v-model="lead.batchNo"></el-input>
        </el-form-item>
        <el-form-item label="固件版本号" :label-width="formLabelWidth" prop="firmwareVersion">
          <el-input maxlength="50" v-model="lead.firmwareVersion"></el-input>
        </el-form-item>
        <el-form-item label="设备号" :label-width="formLabelWidth" prop="equipmentNumber">
          <el-input maxlength="30" v-model="lead.equipmentNumber"></el-input>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input maxlength="255" v-model="lead.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="save" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="出库"
      :visible.sync="dialogFormVisible"
      @close="stockclose"
      :close-on-click-modal="false"
    >
      <el-form
        :model="stockOut"
        :rules="stockRules"
        ref="stockOut"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="设备用途" :label-width="formLabelWidth" prop="equipmentPurpose">
          <el-select v-model="stockOut.equipmentPurpose" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in purpostData"
              :key="item.value"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="经销商名称" :label-width="formLabelWidth" prop="dealerId">
          <el-select
            v-model="stockOut.dealerId"
            placeholder="请选择"
            style="width:100%"
            @change="handleChangeOption"
          >
            <el-option
              v-for="item in option2"
              :key="item.id"
              :label="item.dealerName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系人" :label-width="formLabelWidth">
          <el-input v-model="dealer.name" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="dealer.telephone" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="仓库地址" :label-width="formLabelWidth">
          <el-input v-model="dealer.address" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="设备号" :label-width="formLabelWidth" prop="equipmentNumber">
          <el-input maxlength="30" v-model="stockOut.equipmentNumber"></el-input>
        </el-form-item>
        <el-form-item label="固件版本号" :label-width="formLabelWidth" prop="firmwareVersion">
          <el-input maxlength="50" v-model="stockOut.firmwareVersion"></el-input>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input maxlength="255" v-model="stockOut.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="stockclose">取 消</el-button>
        <el-button type="primary" @click="keep" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="批入"
      :visible.sync="dialogVisible"
      @close="intoclose"
      :close-on-click-modal="false"
    >
      <el-form :model="form">
        <el-form-item label="设备型号" :label-width="formLabelWidth">
          <el-select v-model="into.equipmentModel" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in option"
              :key="item.value"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input maxlength="255" v-model="into.remark"></el-input>
        </el-form-item>
        <el-form-item label="设备导入" :label-width="formLabelWidth">
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
              <span>点击上传</span>
            </el-button>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="intoclose">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="批出"
      :visible.sync="dialogsVisible"
      @close="outclose"
      :close-on-click-modal="false"
    >
      <el-form :model="form">
        <el-form-item label="设备用途" :label-width="formLabelWidth">
          <el-select v-model="outs.equipmentPurpose" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in purpostData"
              :key="item.value"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="经销商名称" :label-width="formLabelWidth">
          <el-select
            v-model="outs.dealerId"
            placeholder="请选择"
            style="width:100%"
            @change="handleFirst"
          >
            <el-option
              v-for="item in dealerData"
              :key="item.id"
              :label="item.dealerName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth">
          <el-input v-model="user" type="text" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="telephone" type="text" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="仓库地址" :label-width="formLabelWidth">
          <el-input v-model="address" type="text" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input v-model="outs.remark" maxlength="255" type="text"></el-input>
        </el-form-item>
        <el-form-item label="设备导入" :label-width="formLabelWidth">
          <div class="upload-wrapper">
            <input
              ref="outFile"
              class="upload-file"
              id="file"
              type="file"
              name="file"
              multiple
              @change="outUpload"
            />
            <el-button class="exprot-btn" size="small" type="primary">
              <span>点击上传</span>
            </el-button>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="outclose">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="健康状态"
      :visible.sync="dialogVisibles"
      @close="headlthyClose"
      :close-on-click-modal="false"
    >
      <el-form :model="form">
        <el-form-item label="设备号" :label-width="formLabelWidth">
          <el-input v-model="healthData.equipmentNumber" disabled></el-input>
        </el-form-item>
        <el-form-item label="健康状态" :label-width="formLabelWidth">
          <el-select v-model="healthData.healthyState" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in health"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="headlthyClose">取 消</el-button>
        <el-button type="primary" @click="headlthySave" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="设备用途"
      :visible.sync="dialogEquipmentPurpo"
      @close="equipmentPurpoClose"
      :close-on-click-modal="false"
    >
      <el-form :model="form">
        <el-form-item label="设备号" :label-width="formLabelWidth">
          <el-input v-model="equipmentPurpoData.equipmentNumber" disabled></el-input>
        </el-form-item>
        <el-form-item label="设备用途" :label-width="formLabelWidth">
          <el-select
            v-model="equipmentPurpoData.equipmentPurpose"
            placeholder="请选择"
            style="width:100%"
          >
            <el-option
              v-for="item in purpostData"
              :key="item.value"
              :label="item.valueCn"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="equipmentPurpoClose">取 消</el-button>
        <el-button type="primary" @click="equipmentPurpoSave">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getAllinfo,
  toLead,
  stock,
  backout,
  toLeadall,
  stockAll,
  inModeldownload,
  inmodel,
  outModeldownload,
  outmodel,
  stockExport,
  headlthystate,
  undoModeldownload,
  undoAlldownload,
  dataManipulation,
  equipmentPurpostate
} from "../../api/equipment.js";
import { searchDiclist, typeDiclist } from "../../api/dictionary.js";
import { getDealerAll, getDealerAlone, getDealer } from "../../api/dealer.js";
import { getLanList } from "../../api/firmwarepair.js";

export default {
  data() {
    return {
      isDisable: false,
      isFlag: true,
      form: {
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
      options: [
        {
          value: "1",
          label: "入库"
        },
        {
          value: "2",
          label: "出库"
        }
      ],
      health: [
        {
          value: "0",
          label: "正常"
        },
        {
          value: "1",
          label: "维修中"
        },
        {
          value: "2",
          label: "报废"
        }
      ],
      dealer: {
        name: "",
        telephone: "",
        address: ""
      },
      option: [],
      option5: [],
      option2: [],
      tableData: [],

      //入库
      dialogFormsVisible: false,
      loading: false,
      isRepeat: false,
      formLabelWidth: "120px",
      lead: {
        batchNo: "",
        equipmentModel: "",
        equipmentNumber: "",
        firmwareVersion: "",
        remark: ""
      },
      stockOut: {
        dealerId: "",
        equipmentNumber: "",
        equipmentPurpose: "",
        firmwareVersion: "",
        remark: ""
      },
      toRules: {
        equipmentModel: [{ required: true, trigger: "blur" }],
        batchNo: [{ required: true, trigger: "blur" }],
        firmwareVersion: [{ required: true, trigger: "blur" }],
        equipmentNumber: [{ required: true, trigger: "blur" }]
      },
      stockRules: {
        equipmentPurpose: [{ required: true, trigger: "blur" }],
        dealerId: [{ required: true, trigger: "blur" }],
        equipmentNumber: [{ required: true, trigger: "blur" }],
        firmwareVersion: [{ required: true, trigger: "blur" }]
      },
      //出库
      dialogFormVisible: false,
      //撤销
      multipleSelection: [],
      equipmentNumber: "",

      //批入
      dialogVisible: false,
      into: {
        equipmentModel: "",
        remark: ""
      },
      //批出
      dialogsVisible: false,
      outs: {
        dealerId: "",
        equipmentPurpose: "",
        remark: ""
      },
      purpostData: [],
      dealerData: [],
      selectId: "",
      user: "",
      telephone: "",
      address: "",
      //健康状态
      dialogVisibles: false,
      dialogEquipmentPurpo: false,
      healthData: {
        equipmentNumber: "",
        healthyState: ""
      },
      equipmentPurpoData: {
        equipmentNumber: "",
        equipmentPurpose: ""
      },
      total: 0,
      currentPage: 1
    };
  },
  created() {
    this.getList();
    getLanList({
      name: "EQUIPMENT_MODEL",
      value: ""
    }).then(res => {
      this.option = res.dictionaryVOList;
    });

    getDealerAll().then(res => {
      this.option2 = res.voList;
    });
  },
  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        getAllinfo(this.form).then(res => {
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
    stateFormat(row) {
      if (row.flag == "1") {
        return "入库";
      } else if (row.flag == "2") {
        return "出库";
      }
    },
    state(row) {
      if (row.state == "Y") {
        return "已激活";
      } else {
        return "未激活";
      }
    },
    //查询
    search() {
      this.loading = true;
      setTimeout(() => {
        getAllinfo({
          batchNo: this.form.batchNo,
          deployVersion: this.form.deployVersion,
          endTime: this.form.endTime,
          equipmentModel: this.form.equipmentModel,
          equipmentNumber: this.form.equipmentNumber,
          equipmentPurpose: this.form.equipmentPurpose,
          firmwareVersion: this.form.firmwareVersion,
          flag: this.form.flag,
          healthyState: this.form.healthyState,
          pageNo: 1,
          pageSize: 10,
          startTime: this.form.startTime
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
      this.form.equipmentModel = "";
      this.form.equipmentNumber = "";
      this.form.batchNo = "";
      this.form.firmwareVersion = "";
      this.form.deployVersion = "";
      this.form.flag = "";
      this.form.healthyState = "";
      this.form.startTime = "";
      this.form.endTime = "";
      this.getList();
    },

    //弹框关闭
    close() {
      this.dialogFormsVisible = false;
      this.dialogFormVisible = false;
      this.$refs.lead.resetFields();
      this.lead.remark = "";
    },
    intoclose() {
      this.dialogVisible = false;
      this.into.equipmentModel = "";
      this.into.remark = "";
    },
    stockclose() {
      this.dialogFormVisible = false;
      this.$refs.stockOut.resetFields();
      this.dealer.name = "";
      this.dealer.telephone = "";
      this.dealer.address = "";
      this.stockOut.remark = "";
    },
    outclose() {
      this.dialogsVisible = false;
      this.outs.equipmentPurpose = "";
      this.outs.dealerId = "";
      this.outs.remark = "";
      this.user = "";
      this.telephone = "";
      this.address = "";
    },
    headlthyClose() {
      this.dialogVisibles = false;
      this.healthData.healthyState = "";
      dialogEquipmentPurpo;
    },
    equipmentPurpoClose() {
      this.dialogEquipmentPurpo = false;
      this.equipmentPurpoData.equipmentPurpo = "";
    },
    //入库
    tolead() {
      this.dialogFormsVisible = true;
      this.isDisable = false;
    },
    save() {
      this.$refs.lead.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          this.isRepeat = true;
          this.isDisable = true;
          toLead(this.lead).then(res => {
            if (res.success) {
              this.msgTip("success", "添加成功");
              this.dialogFormsVisible = false;
              this.empty();
            } else {
              this.msgTip("error", res.errorMsg);
              this.isRepeat = false;
              this.isDisable = false;
            }
          });
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
        }
      });
    },
    //出库
    stock() {
      this.dialogFormVisible = true;
      this.isDisable = false;
      getLanList({
        name: "EQUIPMENT_PURPOSE",
        value: ""
      }).then(res => {
        this.purpostData = res.dictionaryVOList;
      });
    },
    keep() {
      this.$refs.stockOut.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          this.isRepeat = true;
          this.isDisable = true;
          stock(this.stockOut).then(res => {
            if (res.success) {
              this.msgTip("success", "添加成功");
              this.dialogFormVisible = false;
              this.empty();
            } else {
              this.msgTip("error", res.errorMsg);
              this.isRepeat = false;
              this.isDisable = false;
            }
          });
        } else {
          this.msgTip("warning", "请填写完整");
          return false;
        }
      });
    },
    //撤销
    repeal() {
      if (this.currentrow == null) {
        this.msgTip("error", "请选择需要撤销的记录");
      } else {
        this.$confirm("您是否要撤销当前选中的记录？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.isRepeat = true;
          backout(this.equipmentNumber).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "撤销成功",
                center: true
              });
              this.empty();
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
    repealModel() {
      undoModeldownload().then(res => {
        var data = new Blob([res], { type: "application/vnd.ms-excel" });
        var downloadUrl = window.URL.createObjectURL(data);
        var anchor = document.createElement("a");
        anchor.href = downloadUrl;
        anchor.download = "stockBatchUndoModel.xlsx";
        anchor.click();
        window.URL.revokeObjectURL(data);
      });
    },
    //批量撤销
    repealAll() {
      const fileData = this.$refs.repealFile.files[0];
      let formFile = new FormData(); //加入文件对象
      formFile.append("file", fileData);
      const extension = fileData.name.split(".")[1] === "xlsx";
      if (!extension) {
        this.msgTip("error", "上传模板只能是xlsx格式!");
        // return;
      } else {
        this.isRepeat = true;
        undoAlldownload(formFile).then(res => {
          if (res.success) {
            this.msgTip("success", "批量撤销成功");
            this.$refs.repealFile.value = [];
            this.empty();
          } else {
            this.msgTip("error", res.errorMsg);
            this.$refs.repealFile.value = [];
            this.isRepeat = false;
          }
        });
      }
    },
    //批入模板
    inmodel() {
      inModeldownload().then(res => {
        var data = new Blob([res], { type: "application/vnd.ms-excel" });
        var downloadUrl = window.URL.createObjectURL(data);
        var anchor = document.createElement("a");
        anchor.href = downloadUrl;
        anchor.download = "stockBatchInModel.xlsx";
        anchor.click();
        window.URL.revokeObjectURL(data);
      });
    },
    //批入
    intos() {
      this.dialogVisible = true;
    },
    submitUpload() {
      const fileData = this.$refs.uploadFile.files[0];
      let formFile = new FormData(); //加入文件对象
      formFile.append("file", fileData);
      if (this.into.equipmentPurpose === "") {
        this.msgTip("error", "请选择设备型号！");
      } else {
        this.isRepeat = true;
        toLeadall(this.into.equipmentModel, formFile).then(res => {
          if (res.success) {
            this.msgTip("success", "导入成功");
            this.$refs.uploadFile.value = [];
            this.dialogVisible = false;
            this.empty();
          } else {
            this.msgTip("error", res.errorMsg);
            this.$refs.uploadFile.value = [];
            this.isRepeat = false;
          }
        });
      }
    },

    //批出模板
    outmodel() {
      outModeldownload().then(res => {
        var data = new Blob([res], { type: "application/vnd.ms-excel" });
        var downloadUrl = window.URL.createObjectURL(data);
        var anchor = document.createElement("a");
        anchor.href = downloadUrl;
        anchor.download = "stockBatchOutModel.xlsx";
        anchor.click();
        window.URL.revokeObjectURL(data);
      });
    },

    //批出
    out() {
      this.dialogsVisible = true;
      getLanList({
        name: "EQUIPMENT_PURPOSE",
        value: ""
      }).then(res => {
        this.purpostData = res.dictionaryVOList;
      });
      getDealerAll().then(res => {
        this.dealerData = res.voList;
      });
    },
    handleFirst(val) {
      getDealerAlone(val).then(res => {
        this.user = res.voT.contact;
        this.telephone = res.voT.phone;
        this.address = res.voT.address;
      });
    },
    outUpload() {
      const fileData = this.$refs.outFile.files[0];
      let formFile = new FormData(); //加入文件对象
      formFile.append("file", fileData);
      formFile.append("dealerId", this.outs.dealerId);
      formFile.append("equipmentPurpose", this.outs.equipmentPurpose);
      formFile.append("remark", this.outs.remark);
      this.isRepeat = true;
      stockAll(formFile).then(res => {
        if (res.success) {
          this.msgTip("success", "批出成功");
          this.$refs.outFile.value = [];
          this.dialogsVisible = false;
          this.empty();
        } else {
          this.msgTip("error", res.errorMsg);
          this.$refs.outFile.value = [];
          this.isRepeat = false;
        }
      });
    },
    //导出
    derive() {
      stockExport(this.form).then(res => {
        var data = new Blob([res], { type: "application/vnd.ms-excel" });
        var downloadUrl = window.URL.createObjectURL(data);
        var anchor = document.createElement("a");
        anchor.href = downloadUrl;
        anchor.download = "stockBatchOutModel.xlsx";
        anchor.click();
        window.URL.revokeObjectURL(data);
      });
    },
    //健康状态
    headlthy() {
      if (this.currentrow == null) {
        this.msgTip("error", "请选择需要修改健康状态的记录！");
      } else {
        if (this.currentrow.flag == 2) {
          this.msgTip("error", "已出库的设备不允许修改健康状态！");
        } else {
          this.dialogVisibles = true;
          this.isDisable = false;
          this.healthData.equipmentNumber = this.currentrow.equipmentNumber;
          console.log(this.currentrow.equipmentNumber, "0000");
        }
      }
    },
    headlthySave() {
      if (this.healthData.healthyState == "") {
        this.msgTip("error", "请选择设备型号");
      } else {
        this.isRepeat = true;
        this.isDisable = true;
        headlthystate(this.healthData).then(res => {
          if (res.success) {
            this.msgTip("success", "修改成功");
            this.dialogVisibles = false;
            // 获取当前日期时间
            dataManipulation(
              this.tableData,
              this.rowData,
              this.healthData.healthyState,
              "",
              getDate()
            );
          } else {
            this.msgTip("error", res.errorMsg);
            this.isRepeat = false;
            this.isDisable = false;
          }
        });
      }
    },
    //设备用途
    equipmentPurpo() {
      if (this.currentrow == null) {
        this.msgTip("error", "请选择一条已出库的数据！");
      } else {
        if (this.currentrow.flag != 2) {
          this.msgTip("error", "未出库的设备不允许修改设备用途！");
        } else {
          getLanList({ name: "EQUIPMENT_PURPOSE", value: "" }).then(res => {
            this.purpostData = res.dictionaryVOList;
          });
          this.dialogEquipmentPurpo = true;
          this.equipmentPurpoData.equipmentNumber = this.currentrow.equipmentNumber;
          this.equipmentPurpoData.equipmentPurpose = this.currentrow.equipmentPurpose;
        }
      }
    },

    equipmentPurpoSave() {
      if (this.equipmentPurpoData.equipmentPurpose == "") {
        this.msgTip("error", "请选择设备用途");
      } else {
        this.isRepeat = true;
        if (this.equipmentPurpoData.equipmentPurpose == "测试设备") {
          this.equipmentPurpoData.equipmentPurpose = "0";
        }
        if (this.equipmentPurpoData.equipmentPurpose == "售卖设备") {
          this.equipmentPurpoData.equipmentPurpose = "1";
        }
        equipmentPurpostate(this.equipmentPurpoData).then(res => {
          if (res.success) {
            this.msgTip("success", "修改成功");
            this.dialogEquipmentPurpo = false;
            // 获取当前日期时间
            dataManipulation(
              this.tableData,
              this.rowData,
              "",
              this.equipmentPurpoData.equipmentPurpose,
              getDate()
            );
          } else {
            this.msgTip("error", res.errorMsg);
            this.isRepeat = false;
          }
        });
      }
    },
    handleChangeOption(value) {
      getDealerAlone(value).then(res => {
        this.dealer.name = res.voT.contact;
        this.dealer.telephone = res.voT.phone;
        this.dealer.address = res.voT.address;
      });
    },

    indexMethod(index) {
      return index + 1;
    },
    handleClick(val) {
      this.rowData = val;
      this.currentrow = val;
      this.equipmentNumber = this.currentrow.equipmentNumber;
    },
    //信息提示框
    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
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
.equiinfo {
  list-style: none;
  .equiinfo-head {
    padding-right: 20px;
    ul {
      width: 100%;
      display: flex;
      padding-left: 0;
      li {
        width: 200px;
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
  .page {
    text-align: center;
    margin-top: 20px;
  }
  .upload-wrapper {
    position: relative;
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
</style>

