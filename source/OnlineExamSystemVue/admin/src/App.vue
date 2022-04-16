<template>
  <el-menu
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router="$router">
    <el-menu-item index="/">题目管理</el-menu-item>
    <el-menu-item index="/exam">试卷管理</el-menu-item>
    <el-menu-item index="/subject">学科管理</el-menu-item>
    <el-menu-item index="/student">学生管理</el-menu-item>
  </el-menu>
  <router-view></router-view>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>
