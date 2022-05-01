<template>
  <el-menu
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router="$router">
    <el-menu-item index="/student/exam">试卷列表</el-menu-item>
    <el-menu-item index="/student/set">个人中心</el-menu-item>
  </el-menu>
  <router-view></router-view>
</template>

<script>
export default {
  name: "StudentMenu"
}
</script>

<style scoped>

</style>