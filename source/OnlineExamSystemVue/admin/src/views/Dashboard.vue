<template>
  <el-menu
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router="$router">
    <el-menu-item index="/question">题目管理</el-menu-item>
    <el-menu-item index="/exam">试卷管理</el-menu-item>
    <el-menu-item index="/subject">学科管理</el-menu-item>
    <el-menu-item index="/student">学生管理</el-menu-item>
    <el-menu-item @click="logout">退出</el-menu-item>
  </el-menu>
  <router-view></router-view>
</template>

<script>
export default {
  name: "dashboard",
  methods: {
    logout() {
      console.log("logout");
      this.$axios.post("/logout").then(res => {
        localStorage.clear()
        sessionStorage.clear()
        this.$store.commit("resetState")
        this.$router.push("/login")
      });
    }
  }
}
</script>

<style scoped>

</style>