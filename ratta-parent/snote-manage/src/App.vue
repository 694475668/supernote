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
  }
};
</script>

<style>
.el-table__body tr.current-row > td {
  background-color: #409eff;
  color: #fff;
  border-right: 1px solid #a5cffa;
}
.el-table--enable-row-hover .el-table__body tr:hover > td {
  background-color: #a5cffa;
  color: #fff;
}
</style>
