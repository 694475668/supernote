<template>
  <div class="record">
    <div class="record-head">
      <ul>
        <li>
          <h5>设备号</h5>
          <el-input v-model="form.equipmentNumber"></el-input>
        </li>
        <li>
          <h5>联系方式</h5>
          <el-input v-model="form.contact"></el-input>
        </li>
        <li>
          <h5>一级问题类型</h5>
          <el-select 
            v-model="selectFirst" 
            placeholder="请选择" 
            @focus="getFirst()"
            @change="handleChangeFirst"
          >
            <el-option
              v-for="item in options"
              :key="item.typeId"
              :label="item.value"
              :value="item.typeId"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5 v-if="selectFirst!=''">二级问题类型</h5>
          <el-select 
            v-if="selectFirst!=''"
            v-model="selectSecond" 
            placeholder="请选择" 
            @focus="getSecond()"
            @change="handleChangeSecond"
          >
            <el-option
              v-for="item in options"
              :key="item.typeId"
              :label="item.value"
              :value="item.typeId"
            ></el-option>
          </el-select>
        </li>
        <li>
          <h5 v-if="selectSecond!=''">三级问题类型</h5>
          <el-select 
            v-if="selectSecond!=''"
            v-model="selectThird" 
            placeholder="请选择" 
            @focus="getThird()"
            @change="handleChangeThird"  
          >
            <el-option
              v-for="item in options"
              :key="item.typeId"
              :label="item.value"
              :value="item.typeId"
            ></el-option>
          </el-select>
        </li>
      </ul>
    </div>
    <div class="function">
      <ol>
        <li>
          <img src="../../assets/iconfont/find.png" alt="">
          <span @click="search">查询</span>
        </li>
        <li>
           <img src="../../assets/iconfont/empty.png" alt="">
          <span @click="empty">清空</span>
        </li>
        <li v-permit ="'/feedback/record/export'">
          <img src="../../assets/iconfont/derive.png" alt="">
          <span @click="exportAll">导出</span>
        </li>
      </ol>
    </div>
    <div class="tab">
      <el-table :data="tableData" style="width: 100%" border v-loading="loading">
        <el-table-column type="index" :index="indexMethod"></el-table-column>
        <el-table-column prop="valueOne" label="一级问题类型"></el-table-column>
        <el-table-column prop="valueTwo" label="二级问题类型"></el-table-column>
        <el-table-column prop="valueThree" label="三级问题类型"></el-table-column>
        <el-table-column prop="equipmentNumber" label="设备号" width="200px"></el-table-column>
        <el-table-column prop="contact" label="联系方式" width="200px"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="200px"></el-table-column>
        <el-table-column prop="description" label="描述" show-overflow-tooltip></el-table-column>
      </el-table>
    </div>
    <div class="page">
            <el-pagination
                @current-change="handleCurrentChange"
                :hide-on-single-page="value"
                :current-page="currentPage"
                :page-size="10"
                layout="total, prev, pager, next"
                :total=total
                background>
            </el-pagination>
    </div>
  </div>
</template>

<script>

import {
  getFeedbackRecodList,
  getFeedbackRecodFirst,
  getFeedbackRecodSecond,
  getFeedbackRecodThird,
  exportFeedbackRecodList
} 
from "../../api/feedbackRecod.js";
import { close } from 'fs';
export default {
  data() {
    return {
      value: "",
      value1: "",
      value2: "",
      typeId1: "",
      typeId2: "",
      total:0,
      selectFirst: '',
      selectSecond: '',
      selectThird: '',
      currentPage:0,
      loading: false,
      options: [
        {
          typeId: "",
          valueCn: ""
        }
      ],
      form: {
        pageNo: 1,
        pageSize: 10,
        equipmentNumber: '',
        contact: '',
        valueOne: '',
        valueTwo: '',
        valueThree: ''
      },
      tableData: [],
      state: {},
    };
  },


  created() {
    this.getList();
  },


  methods: {

    getList() {
      this.loading = true
      setTimeout(() =>{
        getFeedbackRecodList(this.form).then(res => {
        this.tableData = res.voList;
        this.total = res.total;
        this.loading = false;
        this.isRepeat = false;
      });
      },500)
    },

      handleSizeChange(val) {
        this.form.pageSize=val;
        this.getList(); 
      },
      handleCurrentChange(currentPage) {
        this.currentPage = currentPage
        this.form.pageNo=currentPage;
        this.getList(); 
      },
    getFirst(){
      getFeedbackRecodFirst({typeId:'',lan:'zh-CN'}).then(res => {
        this.selectSecond = "";this.selectThird = "";
        this.form.valueThree = ""; this.form.valueTwo = "",
        this.options = res.voList
      })
    },

    getSecond(){
      getFeedbackRecodSecond({typeId:this.typeId1,lan:'zh-CN'}).then(res => {
        this.selectThird = "";
        this.form.valueThree = "";
        this.options = res.voList
      })
    },

    getThird(){
      getFeedbackRecodThird({typeId:this.typeId2,lan:'zh-CN'}).then(res => {
        this.options = res.voList
      })
    },

    handleChangeFirst(value) {
        this.typeId1 = value
        this.form.valueOne = value
      },
    handleChangeSecond(value) {
        this.typeId2 = value
        this.form.valueTwo = value
      },
    handleChangeThird(value) {
        this.form.valueThree = value
      },
    //导出
    exportAll(){
        exportFeedbackRecodList(this.form).then(res => {
        var data = new Blob([res],{type:"application/vnd.ms-excel"});
        var downloadUrl = window.URL.createObjectURL(data);
        var result=document.getElementById("result");    
        var anchor = document.createElement("a");
        anchor.href = downloadUrl;
        anchor.download = "FeedbackRecordRecharge.xlsx";
        anchor.click();
        window.URL.revokeObjectURL(data); 
        })
    },
    //查询
    search() {
      this.loading = true
      setTimeout(() =>{
        getFeedbackRecodList({
        pageNo: 1,
        pageSize: 10,
        equipmentNumber: this.form.equipmentNumber,
        contact: this.form.contact,
        valueOne: this.form.valueOne,
        valueTwo: this.form.valueTwo,
        valueThree: this.form.valueThree
      }).then(res => {
        this.tableData = res.voList;
        this.total = res.total
        this.currentPage =1;
        this.loading = false
      })
      },500)
    },
    //清空
    empty() { 
      this.form.equipmentNumber = "";
      this.form.contact = "";
      this.form.valueOne = "";
      this.form.valueTwo = "";
      this.form.valueThree = "";
      this.selectFirst = "";
      this.selectSecond = "";
      this.selectThird = "";
      this.getList();  
    },
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
.record {
  list-style: none;
  .record-head {
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
        img{
          width:15px;
          vertical-align: middle;
          margin-right:5px;
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
    .page{
      text-align: center;
      margin-top:20px;
    }
</style>

