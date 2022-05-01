<template>
  <div>
    <el-row id="all" justify="center">
      <el-col :span="8">
        <h2>修改密码</h2>
        <el-form ref="form" :model="formData" label-width="80px" :rules="rules">
          <el-form-item label="旧密码:">
            <el-input v-model="formData.oldPassword"></el-input>
          </el-form-item>
          <el-form-item label="新密码:">
            <el-input v-model="formData.newPassword"></el-input>
          </el-form-item>
        </el-form>
        <el-row justify="center">
          <el-button type="primary" @click="submit">提交</el-button>
          <el-button type="danger" @click="logout">退出</el-button>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Personal",
  data() {
    return {
      formData: {
        oldPassword: '',
        newPassword: '',
      },
      rules: {
        oldPassword: [
          {required: true, message: '请输入旧密码', trigger: 'blur'},
        ],
        newPassword: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
        ],
      },
    }
  },
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
    },
    submit() {
      //TODO
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.$axios.post("/student/users/updatePassword", this.formData).then(res => {
            console.log(res)
            if(res.data.code === 200) {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.formData = {
                oldPassword: '',
                newPassword: '',
              }
            } else {
              this.$message({
                message: "修改失败",
                type: 'error'
              });
            }
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>
#all {
  height: 80vh;
  display: flex;
  align-items: center;
  text-align: center;
}
</style>