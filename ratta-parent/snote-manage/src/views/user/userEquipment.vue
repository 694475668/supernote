<template>
  <div class="userEquipment">
    <div class="user-head">
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
        <li>
          <el-button type="primary" @click="search">查询</el-button>
        </li>
        <li>
          <el-button type="primary" @click="empty">清空</el-button>
        </li>
      </ul>
    </div>
    <div class="tab">
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="equipmentNumber" label="设备号"></el-table-column>
        <el-table-column prop="name" label="设备名"></el-table-column>
        <el-table-column prop="createTime" label="绑定时间"></el-table-column>
        <el-table-column prop="countryCode" label="国家码"></el-table-column>
        <el-table-column prop="telephone" label="手机号"></el-table-column>
        <el-table-column prop="email" label="电子邮箱"></el-table-column>
        <el-table-column prop="status" label="设备状态" :formatter="stateFormat"></el-table-column>
        <el-table-column prop="updateStatus" label="更新状态" :formatter="stateFormats"></el-table-column>
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
import { getEquipment } from "../../api/equipment.js";
export default {
  data() {
    return {
      tableData: [],
      form: {
        equipmentNumber: "",
        telephone: "",
        email: "",
        pageNo: 1,
        pageSize: 10
      },
      total: 0,
      currentPage: 1
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      getEquipment(this.form).then(res => {
        this.tableData = res.voList;
        this.total = Number(res.total);
      });
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.form.pageNo = currentPage;
      this.getList();
    },
    stateFormat(row) {
      if (row.status === "Y") {
        return "正常";
      } else {
        return "锁定";
      }
    },
    stateFormats(row) {
      if (row.updateStatus === "0") {
        return "初始版本";
      } else if (row.updateStatus === "1") {
        return "未更新";
      } else {
        return "已更新";
      }
    },
    //查询
    search() {
      this.getList();
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
.userEquipment {
  list-style: none;
  .user-head {
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

