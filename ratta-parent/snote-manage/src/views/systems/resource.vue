<template>
  <div class="resource">
    <div class="head">
      <el-button type="primary" @click="add" v-permit="'/resource/add'">添加</el-button>
      <el-button type="primary" id="fold" @click="fold">折叠</el-button>
      <el-button type="primary" @click="reload">刷新</el-button>
    </div>
    <div class="content" v-loading="loading">
      <div class="table-title">
        <div>资源名称</div>
        <div>资源路径</div>
        <div>资源类型</div>
        <div>上级资源</div>
        <div>所属系统</div>
        <div>操作</div>
        <div>备注</div>
      </div>
      <div class="table-info">
        <div class="table-item" v-for="(item, index) in  roleDate" :key="index">
          <div class="item-title">
            <div class="item" @click="pickUp(item)">
              <i v-if="item.pick_up" class="el-icon-caret-bottom"></i>
              <i v-else class="el-icon-caret-right"></i>
              {{item.label}}
            </div>
            <div class="item">{{item.attributes.url}}</div>
            <div class="item">{{item.attributes.resourceType}}</div>
            <div class="item">{{item.attributes.superior}}</div>
            <div class="item">{{item.attributes.systemType}}</div>
            <div class="item">
              <el-tooltip
                class="item"
                effect="dark"
                content="修改"
                placement="bottom"
                v-permit="'/resource/update'"
              >
                <img @click="exchange(item.id)" src="../../assets/iconfont/amend.png" alt />
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="删除"
                placement="bottom"
                v-permit="'/resource/delete'"
              >
                <img @click="dele(item.id)" src="../../assets/iconfont/delete.png" alt />
              </el-tooltip>
            </div>
            <div class="item">{{item.attributes.remark}}</div>
          </div>
          <div
            class="item-detail"
            v-for="(childItem, childIndex) in item.children"
            :key="childIndex"
          >
            <div class="detail-content" v-if="item.pick_up">
              <div class="detail-info">
                <div style="padding-left:20px" class="item" @click="pickUp(childItem)">
                  <i v-if="childItem.pick_up" class="el-icon-caret-bottom"></i>
                  <i v-else class="el-icon-caret-right"></i>
                  {{childItem.label}}
                </div>
                <div class="item">{{childItem.attributes.url}}</div>
                <div class="item">{{childItem.attributes.resourceType}}</div>
                <div class="item">{{childItem.attributes.superior}}</div>
                <div class="item">{{childItem.attributes.systemType}}</div>
                <div class="item">
                  <el-tooltip
                    class="item"
                    effect="dark"
                    content="修改"
                    placement="bottom"
                    v-permit="'/resource/update'"
                  >
                    <img @click="exchange(childItem.id)" src="../../assets/iconfont/amend.png" alt />
                  </el-tooltip>
                  <el-tooltip
                    class="item"
                    effect="dark"
                    content="删除"
                    placement="bottom"
                    v-permit="'/resource/delete'"
                  >
                    <img @click="dele(childItem.id)" src="../../assets/iconfont/delete.png" alt />
                  </el-tooltip>
                </div>
                <div class="item">{{childItem.attributes.remark}}</div>
              </div>
              <div class="item-list" v-for="(childs, indexs) in childItem.children" :key="indexs">
                <div v-if="childItem.pick_up" class="item-content">
                  <div class="item">{{childs.label}}</div>
                  <div class="item">{{childs.attributes.url}}</div>
                  <div class="item">{{childs.attributes.resourceType}}</div>
                  <div class="item">{{childs.attributes.superior}}</div>
                  <div class="item">{{childs.attributes.systemType}}</div>
                  <div class="item">
                    <el-tooltip
                      class="item"
                      effect="dark"
                      content="修改"
                      placement="bottom"
                      v-permit="'/resource/update'"
                    >
                      <img @click="exchange(childs.id)" src="../../assets/iconfont/amend.png" alt />
                    </el-tooltip>
                    <el-tooltip
                      class="item"
                      effect="dark"
                      content="删除"
                      placement="bottom"
                      v-permit="'/resource/delete'"
                    >
                      <img @click="dele(childs.id)" src="../../assets/iconfont/delete.png" alt />
                    </el-tooltip>
                  </div>
                  <div class="item">{{childs.attributes.remark}}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="table-nodata" v-if="roleDate.length == 0">暂无数据</div>
    </div>

    <el-dialog
      title="添加资源"
      :visible.sync="dialogVisibles"
      @close="close"
      :close-on-click-modal="false"
    >
      <el-form
        :model="form"
        :rules="addRules"
        ref="form"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
      >
        <el-form-item label="资源名称" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="资源路径" :label-width="formLabelWidth">
          <el-input v-model="form.url"></el-input>
        </el-form-item>
        <div class="three">
          <el-form-item label="上级资源" :label-width="formLabelWidth">
            <el-select v-model="form.pid" style="width:100%">
              <el-option :value="form.pid" style="padding:0;height: auto">
                <el-tree
                  ref="tree"
                  :data="routesData"
                  :props="defaultProps"
                  node-key="id"
                  class="permission-tree"
                  @node-click="handleNodeClick"
                  :highlight-current="true"
                ></el-tree>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="系统" :label-width="formLabelWidth" prop="systemId">
            <el-select v-model="form.systemId" style="width:100%">
              <el-option
                v-for="item in systemType"
                :key="item.value"
                :label="item.valueCn"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="three">
          <el-form-item label="资源类型" :label-width="formLabelWidth" prop="resourceTypeId">
            <el-select v-model="form.resourceTypeId" style="width:100%">
              <el-option
                v-for="item in type"
                :key="item.id"
                :label="item.valueMeaning"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="排序" :label-width="formLabelWidth">
            <el-input-number
              v-model="form.seq"
              controls-position="right"
              @change="handleChange"
              :min="100"
              style="width:100%"
            ></el-input-number>
          </el-form-item>
        </div>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="form.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibles=false">取 消</el-button>
        <el-button type="primary" @click="save" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改" :visible.sync="dialogVisible">
      <el-form
        :model="form"
        :rules="addRules"
        ref="form"
        :status-icon="true"
        :show-message="false"
        hide-required-asterisk
        :close-on-click-modal="false"
      >
        <el-form-item label="资源名称" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="资源路径" :label-width="formLabelWidth">
          <el-input v-model="form.url"></el-input>
        </el-form-item>
        <div class="three">
          <el-form-item label="上级资源" :label-width="formLabelWidth">
            <el-select v-model="form.pid" style="width:100%">
              <el-option :value="form.pid" style="padding:0;height: auto">
                <el-tree
                  ref="tree"
                  :data="routesData"
                  :props="defaultProps"
                  node-key="id"
                  class="permission-tree"
                  @node-click="handleNodeClick"
                  :highlight-current="true"
                ></el-tree>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="系统" :label-width="formLabelWidth" prop="systemId">
            <el-select v-model="form.systemId" style="width:100%">
              <el-option
                v-for="item in systemType"
                :key="item.value"
                :label="item.valueCn"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="three">
          <el-form-item label="资源类型" :label-width="formLabelWidth" prop="resourceTypeId">
            <el-select v-model="form.resourceTypeId" style="width:100%">
              <el-option
                v-for="item in type"
                :key="item.id"
                :label="item.valueMeaning"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="排序" :label-width="formLabelWidth">
            <el-input-number
              v-model="form.seq"
              controls-position="right"
              @change="handleChange"
              :min="100"
              style="width:100%"
            ></el-input-number>
          </el-form-item>
        </div>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="form.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="keep" :disabled="isDisable">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {
  getList,
  addList,
  idList,
  deleteList,
  amendList,
  getAllList
} from "../../api/systems.js";
import { typeDiclist, queryDictionaryType } from "../../api/dictionary.js";
export default {
  data() {
    return {
      systemType: [],
      isDisable: false,
      roleDate: [], //列表数据
      form: {
        systemId: "",
        name: "",
        pid: "",
        pname: "",
        remark: "",
        resourceTypeId: "",
        seq: "",
        url: ""
      },
      formLabelWidth: "100px",
      dialogVisibles: false,
      loading: false,
      isRepeat: false,
      num: 1,
      type: [],
      routesData: [],
      defaultProps: {
        children: "children",
        label: "label"
      },
      addRules: {
        name: [{ required: true, trigger: "blur" }],
        resourceTypeId: [{ required: true, trigger: "blur" }],
        systemId: [{ required: true, trigger: "blur" }]
      },
      foldItem: "",
      //修改
      dialogVisible: false,
      rowid: "",
      myid: "",
      flag: true
    };
  },
  created() {
    this.getLists();
  },
  methods: {
    getLists() {
      this.loading = true;
      setTimeout(() => {
        getAllList().then(res => {
          res.map(item => {
            if (this.flag) {
              item.pick_up = true;
            } else {
              item.pick_up = false;
            }
            item.children.map(childItem => {
              childItem.pick_up = true;
              this.loading = false;
              this.isRepeat = false;
              return childItem;
            });
            this.loading = false;
            this.isRepeat = false;
            return item;
          });
          this.roleDate = res;
        });
      }, 500);
    },
    // icon的点击child的展开及收起情况
    pickUp(item) {
      item.pick_up = !item.pick_up;
      this.foldItem = item.pick_up;
    },

    msgTip(msgType, msgInfo) {
      this.$message({
        type: msgType,
        message: msgInfo,
        center: true
      });
    },

    //添加
    handleChange(value) {},
    add() {
      this.isDisable = false;
      this.dialogVisibles = true;
      this.form.name = "";
      this.form.url = "";
      this.form.pid = "";
      this.form.resourceTypeId = "";
      this.form.seq = "";
      this.form.remark = "";
      this.form.systemId = "";
      typeDiclist().then(res => {
        this.type = res;
      });
      queryDictionaryType({ name: "SYSTEM_ID" }).then(res => {
        this.systemType = res.dictionaryVOList;
      });
      getList().then(res => {
        this.routesData = res.map(item => {
          item.children.map(res => {
            if (res.children) {
              delete res.children;
            }
          });
          item.label = item.label;
          return item;
        });
      });
    },
    handleNodeClick(data) {
      this.form.pid = data.label;
      this.myid = data.id;
    },
    save() {
      this.$refs.form.validate(valid => {
        if (valid) {
          // 表单验证通过之后的操作
          this.isRepeat = true;
          this.isDisable = true;
          addList({
            name: this.form.name,
            pid: this.myid,
            remark: this.form.remark,
            resourceTypeId: this.form.resourceTypeId,
            seq: this.form.seq,
            url: this.form.url,
            systemId: this.form.systemId
          }).then(res => {
            if (res.success) {
              this.msgTip("success", "添加成功");
              this.dialogVisibles = false;
              this.getLists();
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
    close() {
      this.form.name = "";
      this.form.url = "";
      this.form.resourceTypeId = "";
      this.form.pid = "";
      this.form.remark = "";
      this.dialogVisibles = false;
      this.dialogVisible = false;
      this.$refs.form.resetFields();
    },
    //修改
    exchange(val) {
      console.log(val, "111");
      this.isDisable = false;
      this.dialogVisible = true;
      idList(val).then(res => {
        this.form.name = res.name;
        this.form.pid = res.pname;
        this.form.pname = res.pid;
        this.form.remark = res.remark;
        this.form.seq = res.seq;
        this.form.url = res.url;
        this.form.resourceTypeId = res.tresourcetypeId;
        this.form.systemId = res.systemId;
        if (res.tresourcetypeId == "0") {
          this.form.resourceTypeId = "菜单";
        } else if (res.tresourcetypeId == "1") {
          this.form.resourceTypeId = "功能";
        }
        if (res.systemId == "0") {
          this.form.systemId = "Supernote管理系统";
        } else if (res.systemId == "1") {
          this.form.systemId = "CMS管理系统";
        }
      });
      typeDiclist().then(res => {
        this.type = res;
      });
      queryDictionaryType({ name: "SYSTEM_ID" }).then(res => {
        this.systemType = res.dictionaryVOList;
      });
      getList().then(res => {
        this.routesData = res.map(item => {
          item.children.map(res => {
            if (res.children) {
              delete res.children;
            }
          });
          item.label = item.label;
          return item;
        });
      });
      this.rowid = val;
    },
    keep() {
      this.isDisable = true;
      let myid = null;
      if (this.myid == null || this.myid == "") {
        myid = this.form.pname;
      } else {
        myid = this.myid;
      }
      if (this.form.resourceTypeId == "菜单") {
        this.form.resourceTypeId = "0";
      } else if (this.form.resourceTypeId == "功能") {
        this.form.resourceTypeId = "1";
      }
      this.isRepeat = true;
      amendList(this.rowid, {
        name: this.form.name,
        pid: myid,
        remark: this.form.remark,
        resourceTypeId: this.form.resourceTypeId,
        seq: this.form.seq,
        url: this.form.url,
        systemId: this.form.systemId
      }).then(res => {
        if (res.success) {
          this.msgTip("success", "修改成功！");
          this.dialogVisible = false;
          this.getLists();
        } else {
          this.msgTip("error", res.errorMsg);
          this.isRepeat = false;
          this.isDisable = false;
        }
      });
    },

    dele(val) {
      this.$confirm("您是否要删除当前选中的记录？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.isRepeat = true;
        deleteList(val).then(res => {
          if (res.success) {
            this.$message({
              type: "success",
              message: "删除成功",
              center: true
            });
            this.getLists();
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
    //折叠
    fold() {
      var fold = document.getElementById("fold");
      if (fold.innerText == "折叠") {
        this.flag = false;
        document.getElementById("fold").innerText = "展开";
      } else if (fold.innerText == "展开") {
        this.flag = true;
        document.getElementById("fold").innerText = "折叠";
      }
      this.getLists();
    },
    //刷新
    reload() {
      document.getElementById("fold").innerText = "折叠";
      this.flag = true;
      this.getLists();
    }
  }
};
</script>

<style lang='scss' scoped>
.resource {
  .head {
    padding: 20px 20px;
  }
  .content {
    padding: 10px;
    .table-title {
      display: flex;
      line-height: 36px;
      background: #f5f7fa;
      border: 1px solid #e4e9ee;
      div {
        flex: 1;
        padding: 8px 10px;
      }
    }
    .table-info {
      border-right: 1px solid #e4e9ee;
      border-left: 1px solid #e4e9ee;
      .table-item {
        .item-title,
        .detail-info {
          border-bottom: 1px solid #e4e9ee;
          display: flex;
          line-height: 36px;
          div {
            flex: 1;
            padding: 8px 10px;
          }
        }
        .item-list {
          .item-content {
            border-bottom: 1px solid #e4e9ee;
            display: flex;
            line-height: 36px;
            div {
              flex: 1;
              padding: 8px 10px;
            }
          }
        }
      }
      img {
        width: 15px;
        cursor: pointer;
      }
    }
    .table-nodata {
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 24px;
      color: #c0c4cc;
      width: 100%;
      height: 450px;
      border-left: 1px solid #e4e9ee;
      border-right: 1px solid #e4e9ee;
      border-bottom: 1px solid #e4e9ee;
    }
  }
  .three {
    display: flex;
    justify-content: space-between;
    .el-form-item {
      width: 50%;
    }
  }
}
</style>
<style>
</style>



