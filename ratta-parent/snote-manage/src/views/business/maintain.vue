<template>
  <div class="maintain">
    <div class="maintain-head">
      <ul>
        <li>
          <h5>类型</h5>
          <el-input v-model="value"></el-input>
        </li>
        <li>
            <el-button type="primary">查询</el-button>
        </li>
        <li>
            <el-button type="primary">清空</el-button>
        </li>
        <li>
            <el-button type="primary">添加</el-button>
        </li>
        <li>
            <el-button type="primary">修改</el-button>
        </li>
        <li>
            <el-button type="primary">删除</el-button>
        </li>
      </ul>
    </div>
    <div class="tab">
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="name" label="类型"></el-table-column>
        <el-table-column prop="name" label="SN头"></el-table-column>
        <el-table-column prop="name" label="SN总长度"></el-table-column>
        <el-table-column prop="name" label="SN偏移"></el-table-column>
        <el-table-column prop="name" label="SN偏移长度"></el-table-column>
        <el-table-column prop="name" label="规格简述"></el-table-column>
        <el-table-column prop="name" label="操作员"></el-table-column>
        <el-table-column prop="date" label="操作时间"></el-table-column>
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
.maintain {
  list-style: none;
  .maintain-head {
    padding-right: 20px;
    ul {
      width: 100%;
      padding-left: 0;
      display: flex;
      align-items: flex-end;
      li {
        &:first-child{
            width:230px;
        }
        margin-left: 20px;
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

