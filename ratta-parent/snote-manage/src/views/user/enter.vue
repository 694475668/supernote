<template>
  <div class="enter">
    <div class="enter-head">
      <ul>
        <li>
          <h5>手机号</h5>
          <el-input v-model="form.telephone"></el-input>
        </li>
        <li>
          <h5>邮箱</h5>
          <el-input v-model="form.email"></el-input>
        </li>
        <li>
          <h5>登录方式</h5>
          <el-select v-model="form.loginMethod" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>设备</h5>
          <el-select v-model="form.equipment" placeholder="请选择">
            <el-option
              v-for="item in option"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>起始日期</h5>
          <div class="block">
            <el-date-picker
              v-model="form.createTimeStart"
              type="date"
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
            ></el-date-picker>
          </div>
        </li>
        <li>
          <h5>截止日期</h5>
          <div class="block">
            <el-date-picker
              v-model="form.createTimeEnd"
              type="date"
              format="yyyy/MM/dd"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
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
      </ol>
    </div>
    <div class="tab">
      <el-table :data="tableData" style="width: 100%" border v-loading="loading">
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="telephone" label="手机号"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="loginMethod" label="登录方式"></el-table-column>
        <el-table-column prop="ip" label="IP"></el-table-column>
        <el-table-column prop="equipment" label="设备"></el-table-column>
        <el-table-column prop="browser" label="浏览器"></el-table-column>
        <el-table-column prop="createTime" label="登录时间"></el-table-column>
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
import { getLogin } from "../../api/login.js";
export default {
  data() {
    return {
      options: [
        {
          value: "1",
          label: "手机"
        },
        {
          value: "2",
          label: "邮箱"
        },
        {
          value: "3",
          label: "微信"
        }
      ],
      option: [
        {
          value: "1",
          label: "网页端云盘"
        },
        {
          value: "2",
          label: "APP"
        },
        {
          value: "4",
          label: "主账户"
        },
        {
          value: "3",
          label: "终端"
        }
      ],
      tableData: [],
      loading: false,
      form: {
        createTimeEnd: "",
        createTimeStart: "",
        telephone: "",
        email: "",
        equipment: "",
        loginMethod: "",
        pageNo: 1,
        pageSize: 10,
        userName: ""
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
      this.loading = true
      setTimeout(() =>{
      getLogin(this.form).then(res => {
        this.tableData = res.voList;
        this.total = Number(res.total);
        this.loading = false;
      });
      },500)
    },
    //查询
    search() {
      this.loading = true
      setTimeout(() =>{
      getLogin({
        createTimeEnd: this.form.createTimeEnd,
        createTimeStart: this.form.createTimeStart,
        equipment: this.form.equipment,
        loginMethod: this.form.loginMethod,
        telephone: this.form.telephone,
        email: this.form.email,
        pageNo: 1,
        pageSize: 10,
        userName: ""
      }).then(res => {
        this.tableData = res.voList;
        this.total = Number(res.total);
        this.currentPage = 1;
        this.loading = false;
      });
      },500)
    },

    //清空
    empty() {
      this.form.createTimeEnd = "";
      this.form.createTimeStart = "";
      this.form.equipment = "";
      this.form.loginMethod = "";
      this.form.userName = "";
      this.form.telephone = "";
      this.form.email = "";
      this.getList();
    },
    //分页
    changepage(currentPage) {
      this.currentPage = currentPage;
      this.form.pageNo = currentPage;
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
.enter {
  list-style: none;
  .enter-head {
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
        padding:0 10px;
        img {
          width: 15px;
          vertical-align: middle;
          margin-right: 5px;
        }
        span {
          font-size: 14px;
        }
      }
    	li:hover{
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

