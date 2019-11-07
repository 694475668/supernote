<template>
  <div class="operation">
    <div class="opera-head">
      <ul>
        <li>
          <h5>操作员</h5>
          <el-input v-model="value"></el-input>
        </li>
        <li>
          <h5>客户端IP</h5>
          <el-input v-model="value"></el-input>
        </li>
        <li>
          <h5>操作类型</h5>
          <el-select v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5>修改开始时间</h5>
          <div class="block">
            <el-date-picker v-model="value1" type="date" placeholder="选择日期"></el-date-picker>
          </div>
        </li>
        <li>
          <h5>修改结束时间</h5>
          <div class="block">
            <el-date-picker v-model="value2" type="date" placeholder="选择日期"></el-date-picker>
          </div>
        </li>
      </ul>
    </div>
    <div class="function">
      <ol>
        <li>
          <img src="../../assets/iconfont/find.png" alt="">
          <span>查询</span>
        </li>
        <li>
           <img src="../../assets/iconfont/empty.png" alt="">
          <span>清空</span>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="date" label="用户名"></el-table-column>
        <el-table-column prop="name" label="操作员"></el-table-column>
        <el-table-column prop="date" label="操作时间"></el-table-column>
        <el-table-column prop="name" label="客户端IP"></el-table-column>
        <el-table-column prop="address" label="操作类型"></el-table-column>
        <el-table-column prop="address" label="操作事项"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: "",
      value1: "",
      value2: "",
      options: [
        {
          value: "选项1",
          label: "黄金糕"
        }
      ],
      tableData: [{
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333,
          tag: '家'
        }],
    };
  },
  methods: {
      indexMethod(index) {
        return index +1;
      },
      handleClick(row) {
        console.log(row);
      }
  }
};
</script>

<style lang="scss" scoped>
.operation {
  list-style: none;
  .opera-head {
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
        img{
          width:15px;
          vertical-align: middle;
          margin-right:5px;
        }
        span {
          font-size: 14px;
        }
      }
    }
  }
  .function-icon{
      img{
          cursor: pointer;
          width:14px;
      }
  }
}
</style>
<style>
    .el-table th{
        text-align: center;
    }
    .el-table td{
        text-align: center;
    }
</style>

