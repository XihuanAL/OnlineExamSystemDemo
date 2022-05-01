<template>
  <el-menu
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router="$router">
    <el-menu-item index="/subject">学科管理</el-menu-item>
    <el-menu-item index="/question">题目管理</el-menu-item>
    <el-menu-item index="/exam">试卷管理</el-menu-item>
    <el-menu-item index="/student">学生管理</el-menu-item>
    <el-menu-item @click="logout">退出登录</el-menu-item>
  </el-menu>
  <router-view></router-view>
</template>

<script>
export default {
  name: "dashboard",
  methods: {
    logout() {
      this.$confirm('此操作将退出账号, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/logout").then(res => {
          localStorage.clear()
          sessionStorage.clear()
          this.$store.commit("resetState")
          this.$router.push("/login")
        });
      }).catch(() => {
      });
    }
  }
}
</script>

<style scoped>

</style>