<template>
  <div class="enter">
    <div class="enter-head">
      <ul>
        <li>
          <h5>版本号</h5>
          <el-input v-model="value"></el-input>
        </li>
        <li>
          <h5>状态</h5>
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
          <h5>起始日期</h5>
          <div class="block">
            <el-date-picker v-model="value1" type="date" placeholder="选择日期"></el-date-picker>
          </div>
        </li>
        <li>
          <h5>截止日期</h5>
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
        <li>
          <img src="../../assets/iconfont/audit.png" alt="">
          <span>审核测试</span>
        </li>
        <li>
          <img src="../../assets/iconfont/check.png" alt="">
          <span>审核</span>
        </li>
      </ol>
    </div>
    <div class="tab">
       <el-table :data="tableData" style="width: 100%" border>
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column type="expand">
          <template>
            <el-table :data="tableData" style="width: 100%" border>
              <el-table-column type="index" :index="indexMethod"></el-table-column>
              <el-table-column prop="date" label="文件名"></el-table-column>
              <el-table-column prop="name" label="版本号"></el-table-column>
              <el-table-column label="操作">
                <template>
                  <div class="function-icon">
                    <el-tooltip class="item" effect="dark" content="版本信息" placement="bottom">
                      <img src="../../assets/iconfont/mess.png" alt>
                    </el-tooltip>
                  </div>
                </template>
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column label="区域" prop="id"></el-table-column>
        <el-table-column label="定制" prop="name"></el-table-column>
        <el-table-column label="配置"  prop="desc"></el-table-column>
        <el-table-column label="版本号" width='250px' prop="desc"></el-table-column>
        <el-table-column label="状态" prop="desc"></el-table-column>
        <el-table-column label="审核标识" prop="desc"></el-table-column>
        <el-table-column label="审核内容" prop="desc"></el-table-column>
        <el-table-column label="审核人员" prop="desc"></el-table-column>
        <el-table-column label="审核时间" prop="desc"></el-table-column>
        <el-table-column label="操作">
          <template>
            <div class="function-icon">
              <el-tooltip class="item" effect="dark" content="版本信息" placement="bottom">
                <img src="../../assets/iconfont/mess.png" alt>
              </el-tooltip>
            </div>
          </template>
        </el-table-column>
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
          id: "12987125",
          name: "好滋好味鸡蛋仔",
          category: "江浙小吃、小吃零食",
          desc: "SN078.B001.1906260211.151_root",
          address: "上海市普陀区真北路",
          shop: "王小虎夫妻店",
          shopId: "10333",
          date:'2019-07-11'
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

