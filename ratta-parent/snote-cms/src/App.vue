<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {};
  },
  mounted() {
    window.scrollTo(0, 0);
  }
};
</script>
<style lang='scss'>
/* .el-table__body tr.current-row>td{
      background-color: rgb(81, 198, 245);
      color: #fff;
      border-right:1px solid #ccc
    }
    .el-table--enable-row-hover .el-table__body tr:hover>td {
       background-color: rgb(81, 198, 245);
      color: #fff;
    } */
.head {
  margin: 20px 0 0 20px;
  display: flex;
}
.function-icon img {
  cursor: pointer;
  width: 14px;
}
.commont {
  display: flex;
  justify-content: space-between;
}
.commont .el-form-item {
  width: 50%;
}
.ql-editor {
  height: 450px;
}
.btn {
  width: 100%;
  text-align: center;
  margin: 20px 0;
}
.page {
  text-align: center;
  margin-top: 20px;
}
.el-table .cell {
  padding: 0 !important;
  height: 50px;
  line-height: 50px;
}
.el-table td,
.el-table th .cell {
  padding: 0 !important;
  height: 30px;
  min-height: 50px;
  line-height: 50px;
  text-align: center;
}
.el-table th {
  padding: 0 !important;
}
.el-table .el-input {
  padding: 0 5px !important;
}
.el-table p {
  cursor: pointer;
  width: 100%;
  height: 50px;
  margin: 0;
}
// .el-scrollbar__wrap .el-select-dropdown__item span {
//   display: inline-block;
//   width: 270px;
//   overflow: hidden;
//   text-overflow: ellipsis;
//   white-space: nowrap;
// }
.head {
  ol {
    width: 100%;
    display: flex;
    padding-left: 0;
    align-items: flex-end;
    li {
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
.v-note-img-wrapper,.dropdown-images{
  display: none!important;
}

</style>

