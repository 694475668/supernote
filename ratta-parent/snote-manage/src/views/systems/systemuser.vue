<template>
  <div class="systemuser">
    <div class="user-head">
      <ul>
        <li>
          <h5>用户名</h5>
          <el-input v-model="form.username"></el-input>
        </li>
        <li>
          <h5>所属角色</h5>
          <el-select v-model="form.name" placeholder="请选择">
            <el-option
              v-for="(item,index) in options"
              :key="index"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>状态</h5>
          <el-select v-model="form.status" placeholder="请选择">
            <el-option
              v-for="item in option"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>修改开始时间</h5>
          <div class="block">
            <el-date-picker
              v-model="form.startDate"
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              type="date"
              placeholder="选择日期"
              :editable="false"
            ></el-date-picker>
          </div>
        </li>
        <li>
          <h5>修改结束时间</h5>
          <div class="block">
            <el-date-picker
              v-model="form.endDate"
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              type="date"
              placeholder="选择日期"
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
        <li v-permit="'/system/user/add'">
          <img src="../../assets/iconfont/add.png" alt />
          <span @click="add">添加</span>
        </li>
        <li v-permit="'/system/user/grant'">
          <img src="../../assets/iconfont/accredit.png" alt />
          <span @click="getauth">批量授权</span>
        </li>
        <li v-permit="'/system/user/open'">
          <img src="../../assets/iconfont/start.png" alt />
          <span @click="enableItem">启用</span>
        </li>
        <li v-permit="'/system/user/close'">
          <img src="../../assets/iconfont/end.png" alt />
          <span @click="disableItem">停用</span>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        v-loading="loading"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="username" label="用户名" width="120px"></el-table-column>
        <el-table-column prop="name" label="姓名" width="120px"></el-table-column>
        <el-table-column prop="address" label="所属角色名称" show-overflow-tooltip>
          <template slot-scope="scope">
            <span
              class="resource"
              v-for="(item,index) in scope.row.roleVOList"
              :key="index"
            >{{item.name}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号" width="200"></el-table-column>
        <el-table-column prop="email" label="电子邮箱" width="230px"></el-table-column>
        <el-table-column prop="updateUser" label="修改人" width="120px"></el-table-column>
        <el-table-column prop="updateTime" label="修改时间" width="170px"></el-table-column>
        <el-table-column prop="status" label="状态" :formatter="stateFormat" width="70px"></el-table-column>
        <el-table-column label="操作" width="150px">
          <template slot-scope="scope">
            <div class="function-icon">
              <el-tooltip
                class="item"
                effect="dark"
                content="修改"
                placement="bottom"
                v-permit="'/system/user/update'"
              >
                <img @click="amend(scope.row)" src="../../assets/iconfont/amend.png" alt />
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="解锁"
                placement="bottom"
                v-permit="'/system/user/unlock'"
              >
                <img @click="unlock(scope.row)" src="../../assets/iconfont/unlock.png" alt />
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="授权"
                placement="bottom"
                v-permit="'/system/user/grant'"
              >
                <img @click="accredit(scope.row)" src="../../assets/iconfont/accredit.png" alt />
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="删除"
                placement="bottom"
                v-permit="'/system/user/delete'"
              >
                <img @click="dele(scope.row)" src="../../assets/iconfont/delete.png" alt />
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="修改密码"
                placement="bottom"
                v-permit="'/system/user/update/password'"
              >
                <img @click="pasd(scope.row)" src="../../assets/iconfont/pasd.png" alt />
              </el-tooltip>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      title="修改密码"
      :visible.sync="dialogFormVisible"
      @close="pasdclose"
      :close-on-click-modal="false"
    >
      <el-form
        :model="pasds"
        :rules="pasdRules"
        ref="pasds"
        :status-icon="true"
        hide-required-asterisk
      >
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="pasds.usernames" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
          <el-input v-model="pasds.password" autocomplete="off" type="password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="pasdclose">取 消</el-button>
        <el-button type="primary" @click="sure" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
    <el-dialog title="授权" :visible.sync="dialogFormsVisible" :close-on-click-modal="false">
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="authname" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="所属角色" :label-width="formLabelWidth">
          <el-select
            v-model="authvalue"
            multiple
            filterable
            allow-create
            default-first-option
            style="width:100%"
            :clearable="false"
            @change="pickid"
            placeholder="选择角色"
          >
            <el-option
              v-for="(item,index) in authors"
              :key="index"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormsVisible = false">取 消</el-button>
        <el-button type="primary" @click="author" :disabled="isDisable">授权</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="添加用户"
      :visible.sync="dialogVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="addform"
        :rules="addRules"
        ref="addform"
        :status-icon="true"
        hide-required-asterisk
      >
        <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
          <el-input v-model="addform.username" :maxlength="20"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
          <el-input v-model="addform.password" type="password"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="addform.name" autocomplete="off" :maxlength="20"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="addform.phone" autocomplete="off" maxlength="11"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" :label-width="formLabelWidth">
          <el-input v-model="addform.email" autocomplete="off" :maxlength="50"></el-input>
        </el-form-item>
        <el-form-item label="所属角色" :label-width="formLabelWidth">
          <el-select
            v-model="authors"
            multiple
            placeholder="请选择"
            style="width:100%"
            @change="pickid(authvalue)"
          >
            <el-option v-for="item in user" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="save" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="修改用户"
      :visible.sync="dialogVisibles"
      @close="exchangeClose"
      :close-on-click-modal="false"
    >
      <el-form
        :model="exchange"
        ref="exchange"
        :rules="exchangeRules"
        :status-icon="true"
        hide-required-asterisk
      >
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="exchange.username" :disabled="true" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="exchange.name" autocomplete="off" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="exchange.phone" autocomplete="off" maxlength="11"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" :label-width="formLabelWidth">
          <el-input v-model="exchange.email" autocomplete="off" maxlength="40"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="exchangeClose">取 消</el-button>
        <el-button type="primary" @click="keep" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
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
  </div>
</template>

<script>
import {
  getUsermess,
  unblockUsermess,
  deleteUsermess,
  getIduser,
  pasdUsermess,
  startUsermess,
  closeUsermess,
  getCheckuser,
  authorUsermess,
  amendUsermess,
  addUsermess,
  unblockUser,
  dataManipulation
} from "@/api/systemuser";
import { getAlluser } from "@/api/systems";
import { validatePassword } from "@/utils/validate";
export default {
  data() {
    const validatePassword = (rule, value, callback) => {
      if (!/^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,20}$/.test(value)) {
        callback(new Error("请输入密码为6到20位且由数字大小写字母组成"));
      } else {
        callback();
      }
    };
    return {
      isDisable: false,
      isFlag: true,
      form: {
        username: "",
        name: "",
        status: "",
        startDate: "",
        endDate: "",
        roleId: "",
        pageNo: 1,
        pageSize: 10,
        sortField: "",
        sortRules: ""
      },
      //修改用户
      exchange: {
        username: "",
        name: "",
        phone: "",
        email: ""
      },
      exchangeRules: {
        name: [{ required: true, trigger: "blur" }]
      },
      //添加用户
      addform: {
        email: "",
        name: "",
        password: "",
        phone: "",
        username: ""
      },
      addRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePassword }
        ],
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }]
      },
      //修改密码
      pasds: {
        usernames: "",
        password: ""
      },
      pasdRules: {
        password: [
          { required: true, trigger: "blur", validator: validatePassword }
        ]
      },
      changeid: "",
      dialogFormVisible: false,
      dialogVisible: false,
      dialogFormsVisible: false,
      dialogVisibles: false,
      loading: false,
      isRepeat: false,
      options: [],
      option: [
        {
          value: 0,
          label: "锁定"
        },
        {
          value: 1,
          label: "正常"
        },
        {
          value: 2,
          label: "停用"
        }
      ],
      tableData: [],
      formLabelWidth: "120px",
      multipleSelection: [],

      authname: [], //授权用户名
      authvalue: [], //授权所属角色
      authors: [], //所属角色数组
      authorid: [],
      keepid: "",
      userIdList: [],
      roleldList: [],
      total: 0,
      currentPage: 1,
      user: []
    };
  },
  created() {
    this.getList();
    getAlluser().then(res => {
      this.options = res.listVO;
    });
  },
  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        getUsermess(this.form).then(res => {
          this.tableData = res.voList;
          this.total = Number(res.total);
          this.loading = false;
          this.isRepeat = false;
        });
      }, 500);
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.form.pageNo = currentPage;
      this.getList();
    },
    stateFormat(row) {
      if (row.status === "0") {
        return "锁定";
      } else if (row.status === "1") {
        return "正常";
      } else {
        return "停用";
      }
    },
    close() {
      this.$refs.addform.resetFields();
      this.authors = [];
      this.dialogVisible = false;
      this.addform.phone = "";
      this.addform.email = "";

      this.dialogFormsVisible = false;
    },
    pasdclose() {
      this.$refs.pasds.resetFields();
      this.dialogFormVisible = false;
    },
    exchangeClose() {
      this.$refs.exchange.resetFields();
      this.dialogVisibles = false;
      this.exchange.phone = "";
      this.exchange.email = "";
    },
    // 启用
    enableItem() {
      if (!this.findSelectData()) {
        this.msgTip("error", "至少选择一条数据");
      } else {
        if (this.isFlag) {
          this.isFlag = false;
          let arr = [];
          this.multipleSelection.map(res => {
            arr.push(JSON.stringify(res.id));
          });
          this.isRepeat = true;
          startUsermess({ idList: arr }).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "批量启用成功",
                center: true
              });
              // 前端刷新
              dataManipulation(
                this.tableData,
                this.multipleSelection,
                "",
                "",
                "",
                window.localStorage.getItem("username"),
                getDate(),
                "1"
              );
              this.isFlag = true;
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
              this.isRepeat = false;
              this.isFlag = true;
            }
          });
        }
      }
    },
    // 停用
    disableItem() {
      if (!this.findSelectData()) {
        this.msgTip("error", "至少选择一条数据");
      } else {
        if (this.isFlag) {
          this.isFlag = false;
          let arr = [];
          this.multipleSelection.map(res => {
            arr.push(res.id);
          });
          this.isRepeat = true;
          closeUsermess({ idList: arr }).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "批量停用成功",
                center: true
              });
              // 前端刷新
              dataManipulation(
                this.tableData,
                this.multipleSelection,
                "",
                "",
                "",
                window.localStorage.getItem("username"),
                getDate(),
                "2"
              );
              this.isFlag = true;
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
              this.isRepeat = false;
              this.isFlag = true;
            }
          });
        }
      }
    },
    // 查找有没有选中数据，没有则返回false
    findSelectData() {
      if (!this.multipleSelection.length) {
        // 无选中的数据
        return false;
      } else {
        // 有选中的数据
        return true;
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
        if (this.form.startDate == null) {
          this.form.startDate = "";
        }
        if (this.form.endDate == null) {
          this.form.endDate = "";
        }
        getUsermess({
          username: this.form.username,
          roleId: this.form.name,
          status: this.form.status,
          startDate: this.form.startDate,
          endDate: this.form.endDate,
          pageNo: 1,
          pageSize: 10,
          sortField: "",
          sortRules: ""
        }).then(res => {
          this.form.pageNo = 1;
          this.tableData = res.voList;
          this.currentPage = 1;
          this.loading = false;
          this.total = res.total;
        });
      }, 500);
    },

    //清空
    empty() {
      this.form.username = "";
      this.form.name = "";
      this.form.status = "";
      this.form.startDate = "";
      this.form.endDate = "";
      this.form.pageNo = 1;
      this.getList();
    },
    //批量授权
    getauth() {
      if (!this.findSelectData()) {
        this.msgTip("error", "至少选择一条数据");
      } else {
        this.dialogFormsVisible = true;
        this.isDisable = false;
        let arr = [];
        this.authname = [];
        this.authvalue = [];
        console.log(this.multipleSelection);
        this.multipleSelection.map(res => {
          arr.push(res.id);
          this.authname.push(res.username);
          res.roleVOList.map(item => {
            this.authvalue = item.id.split(",");
          });
          this.userIdList = this.authvalue;
        });
        this.authvalue = Array.from(new Set(this.authvalue));
        this.userIdList = this.authvalue;

        this.authorid = arr;
        // getCheckuser(arr).then(res => {
        //   this.authname.push(res.username);
        // });
        getAlluser().then(res => {
          this.authors = res.listVO;
        });
      }
    },

    //修改用户
    amend(v) {
      this.isDisable = false;
      this.dialogVisibles = true;
      getIduser(v.id).then(res => {
        this.exchange.username = res.username;
        this.exchange.name = res.name;
        this.exchange.phone = res.phone;
        this.exchange.email = res.email;
      });
      this.keepid = v.id;
    },
    keep() {
      if (this.exchange.phone != "") {
        if (!/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.exchange.phone)) {
          this.msgTip("warning", "请输入正常的手机号");
          return;
        }
      }
      if (this.exchange.email != "") {
        if (
          !/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(
            this.exchange.email
          )
        ) {
          this.msgTip("warning", "请输入正常的邮箱");
          return;
        }
      }
      this.$refs.exchange.validate(valid => {
        if (valid) {
          this.isRepeat = true;
          this.isDisable = true;
          amendUsermess(this.keepid, this.exchange).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "修改成功",
                center: true
              });
              this.dialogVisibles = false;
              this.getList();
            } else {
              this.$message({
                type: "warning",
                message: res.errorMsg,
                center: true
              });
              this.isRepeat = false;
              this.isDisable = true;
            }
          });
        } else {
          this.msgTip("warning", "请填写姓名");
          return false;
        }
      });
    },

    //解锁
    unlock(v) {
      unblockUser(v.id).then(res => {
        if (res.success) {
          this.isRepeat = true;
          unblockUsermess(v.id).then(res => {
            if (res.success) {
              this.$message({
                type: "success",
                message: "解锁成功",
                center: true
              });
              this.form.status = "";
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
        } else {
          this.$message({
            type: "error",
            message: res.errorMsg,
            center: true
          });
        }
      });
    },
    //授权
    accredit(v) {
      this.isDisable = false;
      this.authorid = [];
      this.dialogFormsVisible = true;
      getCheckuser(v.id).then(res => {
        console.log(res);
        this.authname = res.username;
        let arr = [];
        let obj = [];
        res.roleVOList.map(res => {
          obj.push(res.id);
        });
        this.authvalue = obj;
        this.userIdList = obj;
        console.log(this.userIdList);
      });
      console.log();
      //获取所有用户权限
      getAlluser().then(res => {
        this.authors = res.listVO;
      });
      this.authorid.push(v.id);
    },
    //查找id
    pickid(names) {
      this.userIdList = names;
    },
    //添加用户
    add() {
      this.isDisable = false;
      this.dialogVisible = true;
      this.addform.username = "";
      this.addform.password = "";
      this.authors = [];
      getAlluser().then(res => {
        this.user = res.listVO;
      });
    },
    save() {
      if (this.addform.phone != "") {
        if (!/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.addform.phone)) {
          this.msgTip("warning", "请输入正常的手机号");
          return;
        }
      }
      if (this.addform.email != "") {
        if (
          !/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(
            this.addform.email
          )
        ) {
          this.msgTip("warning", "请输入正常的邮箱");
          return;
        }
      }
      if (this.authors.length == 0) {
        this.msgTip("warning", "请选择角色");
      } else {
        this.$refs.addform.validate(valid => {
          if (valid) {
            this.isRepeat = true;
            this.isDisable = true;
            addUsermess({
              email: this.addform.email,
              name: this.addform.name,
              password: this.addform.password,
              phone: this.addform.phone,
              username: this.addform.username,
              idList: this.authors
            }).then(res => {
              if (res.success) {
                this.msgTip("success", "添加成功");
                this.dialogVisible = false;
                this.empty();
              } else {
                this.msgTip("error", res.errorMsg);
                this.isRepeat = false;
                this.isDisable = false;
              }
            });
          } else {
            return false;
          }
        });
      }
    },
    author() {
      if (this.authvalue == null || this.authvalue == "") {
        this.$message({
          type: "warning",
          message: "一个用户至少有一个角色",
          center: true
        });
        return;
      }
      this.roleldList = this.authorid;
      this.isRepeat = true;
      this.isDisable = true;
      authorUsermess({
        roleIdList: this.userIdList,
        userIdList: this.roleldList
      }).then(res => {
        if (res.success) {
          this.$message({
            type: "success",
            message: "授权成功",
            center: true
          });
          this.dialogFormsVisible = false;
          this.getList();
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
    //删除
    dele(v) {
      this.$confirm("您是否要删除当前选中的记录？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.isRepeat = true;
        deleteUsermess(v.id).then(res => {
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
    },

    //修改密码
    pasd(v) {
      this.isDisable = false;
      this.dialogFormVisible = true;
      getIduser(v.id).then(res => {
        console.log(res);
        this.pasds.usernames = res.username;
        this.changeid = res.id;
      });
    },
    sure() {
      this.$refs.pasds.validate(valid => {
        if (valid) {
          this.isRepeat = true;
          this.isDisable = true;
          pasdUsermess({
            id: this.changeid,
            password: this.pasds.password
          }).then(res => {
            if (res.success) {
              this.msgTip("success", "修改成功");
              this.dialogFormVisible = false;
            } else {
              this.$message({
                type: "success",
                message: res.errorMsg,
                center: true
              });
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
.systemuser {
  list-style: none;
  .user-head {
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
        span {
          font-size: 14px;
        }
        img {
          width: 15px;
          vertical-align: middle;
          margin-right: 5px;
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
}
</style>
<style>
.el-table th {
  text-align: center;
}
.el-table td {
  text-align: center;
}
</style>

