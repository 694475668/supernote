<template>
  <div class="app-container">
    <h5>角色名称</h5>
    <div class="head">
      <el-input class="name" v-model="param.roleName"></el-input>
      <el-button
        type="primary"
        style="margin-left:20px"
        @click="search"
        @keyup.enter.native="search"
      >查询</el-button>
      <el-button type="primary" @click="empty">清空</el-button>
      <el-button type="primary" @click="handleAddRole" v-permit="'/role/add'">新增角色</el-button>
    </div>
    <el-table :data="rolesLists" style="width: 100%;margin-top:30px;" border v-loading="loading">
      <el-table-column align="center" label="角色名称" width="220" prop="name"></el-table-column>
      <el-table-column align="center" show-overflow-tooltip label="拥有资源" width="500">
        <template slot-scope="scope">
          <span
            class="resource"
            v-for="(item,index) in scope.row.resourceVOList"
            :key="index"
          >{{item.name}},</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="备注" prop="remark"></el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <div class="function-icon">
            <el-tooltip
              class="item"
              effect="dark"
              content="修改"
              placement="bottom"
              v-permit="'/role/update'"
            >
              <img @click="change(scope.row)" src="../../assets/iconfont/amend.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="授权"
              placement="bottom"
              v-permit="'/role/grant'"
            >
              <img @click="accredit(scope.row)" src="../../assets/iconfont/accredit.png" alt />
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除"
              placement="bottom"
              v-permit="'/role/delete'"
            >
              <img @click="dele(scope.row)" src="../../assets/iconfont/delete.png" alt />
            </el-tooltip>
          </div>
        </template>
      </el-table-column>
    </el-table>

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
      :visible.sync="dialogVisible"
      :title="dialogType==='edit'?'Edit Role':'新增角色'"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form :model="role" label-width="80px" ref="role" label-position="left">
        <el-form-item label="角色名称">
          <el-input maxlength="25" v-model="role.name" type="text" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            maxlength="100"
            v-model="role.remark"
            :autosize="{ minRows: 2, maxRows: 4}"
            type="textarea"
          />
        </el-form-item>
        <div style="overflow-y:auto;height:450px;">
          <el-form-item label="权限">
            <el-tree
              ref="tree"
              :data="routesData"
              :props="defaultProps"
              show-checkbox
              node-key="id"
              class="permission-tree"
              @check-change="handleCheckChange"
            ></el-tree>
          </el-form-item>
        </div>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="close">取消</el-button>
        <el-button type="primary" @click="confirmRole" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="修改角色"
      :visible.sync="dialogFormVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form :model="form">
        <el-form-item label="角色名称" :label-width="formLabelWidth">
          <el-input maxlength="25" v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input maxlength="100" v-model="form.remark" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所属角色" :label-width="formLabelWidth">
          <div style="overflow-y:auto; height:440px;">
            <el-tree
              ref="tree"
              :data="routesData"
              :props="defaultProps"
              show-checkbox
              node-key="id"
              class="permission-tree"
              :default-expanded-keys="idList"
              :default-checked-keys="idList"
              @check-change="getChecked"
            ></el-tree>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="sure" :disabled="isDisable">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="授权"
      :visible.sync="dialogFormsVisible"
      @close="close"
      :close-on-click-modal="false"
    >
      <div style="overflow-y:auto; height:500px;">
        <el-tree
          ref="accreditTree"
          :data="routesData"
          :props="defaultProps"
          show-checkbox
          node-key="id"
          class="permission-tree"
          :default-expanded-keys="idList"
          :default-checked-keys="idList"
          @check-change="accreditCheck"
        ></el-tree>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="keep" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import path from "path";
import { deepClone } from "@/utils";
// import { getRoutes, addRole, deleteRole, updateRole } from '@/api/role'
import {
  getRole,
  getSearch,
  deleteUser,
  amendUser,
  getList,
  idUser,
  addUser,
  authorUser
} from "@/api/systems";
import { idList } from "../../api/systems";
import { setTimeout } from "timers";
// import i18n from '@/lang'

const defaultRole = {
  key: "",
  // name: '',
  description: "",
  routes: []
};

export default {
  data() {
    return {
      isDisable: false,
      value: "",
      role: {},
      routes: [],
      dialogVisible: false,
      dialogFormVisible: false,
      dialogType: "new",
      checkStrictly: false,
      loading: false,
      isRepeat: false,
      defaultProps: {
        id: "id",
        children: "children",
        label: "label"
      },
      saveid: "",
      rolesLists: [],
      exchange: false,
      param: {
        pageNo: 1,
        pageSize: 10,
        roleName: "",
        sortField: "",
        sortRules: ""
      },
      form: {
        name: "",
        remark: ""
      },
      routesData: [],
      options: [],
      formLabelWidth: "100px",
      arr: [],
      arr1: [],
      arr2: [],
      total: 0,
      currentPage: 1,
      //授权
      dialogFormsVisible: false,
      idList: [],
      keepid: ""
    };
  },
  computed: {
    // routesData() {
    //   return this.routes;
    // }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        getRole(this.param).then(res => {
          this.rolesLists = res.voList;
          this.total = res.total;
          this.loading = false;
          this.isRepeat = false;
        });
      }, 500);
    },

    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.param.pageNo = currentPage;
      this.getList();
    },
    //查询角色
    search() {
      this.getList();
    },

    //清空
    empty() {
      this.param.roleName = "";
      this.getList();
    },

    //关闭事件
    close() {
      this.role = {};
      // this.routesData = ''
      this.dialogVisible = false;
      this.dialogFormVisible = false;
      this.dialogFormsVisible = false;
      this.form.name = "";
      this.form.remark = "";
      this.idList = [];
    },
    getChecked(val) {
      this.arr1 = this.$refs.tree.getCheckedKeys();
      this.arr2 = this.$refs.tree.getHalfCheckedKeys();
      this.arr = this.arr1.concat(this.arr2);
    },
    //修改
    change(v) {
      this.isDisable = false;
      getList(0).then(res => {
        this.routesData = res.map(item => {
          item.label = item.label;
          return item;
        });
      });
      v.resourceVOList.map(res => {
        this.arr.push(res.id);
      });
      idUser(v.id).then(res => {
        console.log(res, "808080");
        res.resourceVOList.map(res => {
          this.idList.push(res.id);
        });
        this.form.name = res.name;
        this.form.remark = res.remark;
      });
      setTimeout(() => {
        this.dialogFormVisible = true;
      }, 500);
      this.saveid = v.id;
    },
    sure() {
      let getCheck = this.$refs.tree.getCheckedKeys();
      if (getCheck == "" || getCheck == null) {
        this.$message({
          type: "error",
          message: "角色至少拥有一条资源权限",
          center: true
        });
        return;
      }
      this.isRepeat = true;
      this.isDisable = true;
      amendUser(this.saveid, {
        idList: this.arr,
        name: this.form.name,
        remark: this.form.remark
      }).then(res => {
        if (res.success) {
          this.$message({
            type: "success",
            message: "修改成功",
            center: true
          });
          this.dialogFormVisible = false;
          this.getList();
          this.idList = [];
          this.arr = [];
        } else {
          this.$message({
            type: "error",
            message: res.errorMsg,
            center: true
          });
          this.isRepeat = false;
          this.isDisable = false;
        }
      });
    },

    accreditCheck() {
      this.arr1 = this.$refs.accreditTree.getCheckedKeys();
      this.arr2 = this.$refs.accreditTree.getHalfCheckedKeys();
      this.arr = this.arr1.concat(this.arr2);
    },
    //授权
    accredit(v) {
      this.dialogFormsVisible = true;
      this.isDisable = false;
      getList(0).then(res => {
        this.routesData = res.map(item => {
          item.label = item.label;
          return item;
        });
      });
      v.resourceVOList.map(res => {
        this.arr.push(res.id);
      });
      idUser(v.id).then(res => {
        res.resourceVOList.map(res => {
          this.idList.push(res.id);
        });
        this.form.name = res.name;
        this.form.remark = res.remark;
      });
      this.keepid = v.id;
    },
    keep() {
      let getCheck = this.$refs.accreditTree.getCheckedKeys();
      if (getCheck == "" || getCheck == null) {
        this.$message({
          type: "error",
          message: "角色至少拥有一条资源权限",
          center: true
        });
        return;
      }
      this.isRepeat = true;
      this.isDisable = true;
      authorUser(this.keepid, {
        idList: this.arr
      }).then(res => {
        if (res.success) {
          this.$message({
            type: "success",
            message: "授权成功",
            center: true
          });
          this.dialogFormsVisible = false;
          this.getList();
          this.idList = [];
          this.arr = [];
        } else {
          this.$message({
            type: "error",
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
        deleteUser(v.id).then(res => {
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
    async getRoutes() {
      const res = await getRoutes();
      this.serviceRoutes = res.data;
      const routes = this.generateRoutes(res.data);
      this.routes = this.i18n(routes);
    },
    // async getRoles() {
    //   const res = await getRoles()
    //   this.rolesList = res.data
    // },

    i18n(routes) {
      const app = routes.map(route => {
        route.title = i18n.t(`route.${route.title}`);
        if (route.children) {
          route.children = this.i18n(route.children);
        }
        return route;
      });
      return app;
    },
    // Reshape the routes structure so that it looks the same as the sidebar
    generateRoutes(routes, basePath = "/") {
      const res = [];

      for (let route of routes) {
        // skip some route
        if (route.hidden) {
          continue;
        }

        const onlyOneShowingChild = this.onlyOneShowingChild(
          route.children,
          route
        );

        if (route.children && onlyOneShowingChild && !route.alwaysShow) {
          route = onlyOneShowingChild;
        }

        const data = {
          path: path.resolve(basePath, route.path),
          title: route.meta && route.meta.title
        };

        // recursive child routes
        if (route.children) {
          data.children = this.generateRoutes(route.children, data.path);
        }
        res.push(data);
      }
      return res;
    },
    generateArr(routes) {
      let data = [];
      routes.forEach(route => {
        data.push(route);
        if (route.children) {
          const temp = this.generateArr(route.children);
          if (temp.length > 0) {
            data = [...data, ...temp];
          }
        }
      });
      return data;
    },

    //新增角色
    handleAddRole() {
      // this.role = Object.assign({}, defaultRole);
      // if (this.$refs.tree) {
      //   this.$refs.tree.setCheckedNodes([]);
      // }
      // this.dialogType = "new";
      this.isDisable = false;
      this.dialogVisible = true;
      getList(0).then(res => {
        this.routesData = res.map(item => {
          item.label = item.label;
          return item;
        });
      });
    },
    handleCheckChange(data) {
      console.log(data, "1111");
      this.arr1 = this.$refs.tree.getCheckedKeys();
      this.arr2 = this.$refs.tree.getHalfCheckedKeys();
      this.arr = this.arr1.concat(this.arr2);
      console.log(this.arr, "");
    },
    handleEdit(scope) {
      // this.dialogType = "edit";
      this.dialogVisible = true;
      // this.checkStrictly = true;
      // this.role = deepClone(scope.row);
      // this.$nextTick(() => {
      //   const routes = this.generateRoutes(this.role.routes);
      //   this.$refs.tree.setCheckedNodes(this.generateArr(routes));
      //   // set checked state of a node not affects its father and child nodes
      //   this.checkStrictly = false;
      // });
    },
    handleDelete({ $index, row }) {
      this.$confirm("Confirm to remove the role?", "Warning", {
        confirmButtonText: "Confirm",
        cancelButtonText: "Cancel",
        type: "warning"
      })
        .then(async () => {
          await deleteRole(row.key);
          this.rolesList.splice($index, 1);
          this.$message({
            type: "success",
            message: "Delete succed!"
          });
        })
        .catch(err => {
          console.error(err);
        });
    },
    generateTree(routes, basePath = "/", checkedKeys) {
      const res = [];

      for (const route of routes) {
        const routePath = path.resolve(basePath, route.path);

        // recursive child routes
        if (route.children) {
          route.children = this.generateTree(
            route.children,
            routePath,
            checkedKeys
          );
        }

        if (
          checkedKeys.includes(routePath) ||
          (route.children && route.children.length >= 1)
        ) {
          res.push(route);
        }
      }
      return res;
    },
    async confirmRole() {
      console.log(this.arr, "1111");
      if (this.role.name === undefined) {
        this.$message({
          type: "warning",
          message: "请填写角色名称",
          center: true
        });
      } else if (this.arr.length == 0) {
        this.$message({
          type: "warning",
          message: "请选择权限",
          center: true
        });
      } else {
        this.isDisable = true;
        this.isRepeat = true;
        addUser({
          idList: this.arr,
          name: this.role.name,
          remark: this.role.remark
        }).then(res => {
          if (res.success) {
            this.$message({
              type: "success",
              message: "添加成功",
              center: true
            });
            this.param.roleName = "";
            this.role.name = "";
            this.role.remark = "";
            this.dialogVisible = false;
            this.getList();
          } else {
            this.$message({
              type: "error",
              message: res.errorMsg,
              center: true
            });
            this.isRepeat = false;
            this.isDisable = false;
          }
        });
      }

      // const isEdit = this.dialogType === "edit";

      // // const checkedKeys = this.$refs.tree.getCheckedKeys();
      // // this.role.routes = this.generateTree(
      // //   deepClone(this.serviceRoutes),
      // //   "/",
      // //   checkedKeys
      // // );

      // if (isEdit) {
      //   await updateRole(this.role.key, this.role);
      //   for (let index = 0; index < this.rolesList.length; index++) {
      //     if (this.rolesList[index].key === this.role.key) {
      //       this.rolesList.splice(index, 1, Object.assign({}, this.role));
      //       break;
      //     }
      //   }
      // } else {
      //   const { data } = await addRole(this.role);
      //   this.role.key = data.key;
      //   this.rolesList.push(this.role);
      // }

      // const { description, key, name } = this.role;
      // this.dialogVisible = false;
      // this.$notify({
      //   title: "Success",
      //   dangerouslyUseHTMLString: true,
      //   message: `
      //       <div>Role Key: ${key}</div>
      //       <div>Role Nmae: ${name}</div>
      //       <div>Description: ${description}</div>
      //     `,
      //   type: "success"
      // });
    },
    // reference: src/view/layout/components/Sidebar/SidebarItem.vue
    onlyOneShowingChild(children = [], parent) {
      let onlyOneChild = null;
      const showingChildren = children.filter(item => !item.hidden);

      // When there is only one child route, the child route is displayed by default
      if (showingChildren.length === 1) {
        onlyOneChild = showingChildren[0];
        onlyOneChild.path = path.resolve(parent.path, onlyOneChild.path);
        return onlyOneChild;
      }

      // Show parent if there are no child route to display
      if (showingChildren.length === 0) {
        onlyOneChild = { ...parent, path: "", noShowingChildren: true };
        return onlyOneChild;
      }

      return false;
    }
  }
};
</script>

<style lang="scss" scoped>
.app-container {
  .head {
    display: flex;
    .name {
      width: 200px;
    }
  }
  .roles-table {
    margin-top: 30px;
  }
  .permission-tree {
    margin-bottom: 30px;
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
