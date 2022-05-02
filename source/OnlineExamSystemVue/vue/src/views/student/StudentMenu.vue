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
    <span class="username">
      {{username}}
    </span>
  </el-menu>
  <router-view></router-view>
</template>

<script>
export default {
  name: "StudentMenu",
  data () {
    return {
      username: 'undefined'
    }
  },
  created() {
    this.getUsername();
  },
  methods: {
    getUsername() {
      this.$axios.get('/student/users/currentUsername').then(res => {
        this.username = res.data.data;
        //console.log(this.username);
      });
    }
  }
}
</script>

<style scoped>
.username {
  float: right;
  width: 100px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  font-size: 20px;
  font-weight: bold;
  color: white;
}
</style>