<template>
  <div class="warehouse">
    <div class="warehouse-head">
      <ul>
        <li>
          <h5>设备号</h5>
          <el-input v-model="form.equipmentNumber"></el-input>
        </li>
        <li>
          <h5>手机号</h5>
          <el-input v-model="form.telephone"></el-input>
        </li>
        <li>
          <h5>电子邮箱</h5>
          <el-input v-model="form.email"></el-input>
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
      </ol>
    </div>
    <div class="tab">
      <el-table :data="tableData" style="width: 100%" border v-loading="loading">
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="equipmentNumber" label="设备号"></el-table-column>
        <el-table-column prop="name" label="设备名"></el-table-column>
        <el-table-column prop="firmwareVersion" label="版本号"></el-table-column>
        <el-table-column prop="createTime" label="绑定时间"></el-table-column>
        <el-table-column prop="telephone" label="手机号"></el-table-column>
        <el-table-column prop="email" label="电子邮箱"></el-table-column>
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
  </div>
</template>

<script>
import { getInfomationList } from "../../api/equimentInformation.js";
export default {
  data() {
    return {
      isDisable: false,
      value: "",
      loading: false,
      tableData: [],
      form: {
        countryCode: "",
        email: "",
        equipmentNumber: "",
        pageNo: 1,
        pageSize: 10,
        telephone: ""
      },
      currentPage: 1,
      total: 0
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      setTimeout(() => {
        getInfomationList(this.form).then(res => {
          this.tableData = res.voList;
          this.total = res.total;
          this.loading = false;
          this.isRepeat = false;
        });
      }, 500);
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.form.pageNo = currentPage;
      this.getList();
    },
    //查询
    search() {
      this.loading = true;
      setTimeout(() => {
        getInfomationList({
          countryCode: "",
          email: this.form.email,
          equipmentNumber: this.form.equipmentNumber,
          pageNo: 1,
          pageSize: 10,
          telephone: this.form.telephone
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
      this.form.equipmentNumber = "";
      this.form.telephone = "";
      this.form.email = "";
      this.getList();
    },
    indexMethod(index) {
      return index + 1;
    },
    handleClick(row) {
      console.log(row);
    }
  }
};
</script>

<style lang="scss" scoped>
.warehouse {
  list-style: none;
  .warehouse-head {
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

