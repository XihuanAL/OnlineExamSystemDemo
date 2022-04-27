<template>
  <div id="app">
    <div id="admin">
      <div class="pos" v-loading="loading">
        <h1 class="adminh1">用户登录</h1>
        <el-form
            :model="user"
            status-icon
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="demo-ruleForm"
        >
          <el-form-item label="用户名：" prop="pass">
            <el-input
                prefix-icon="el-icon-user"
                style="width: 250px"
                type="text"
                v-model="user.username"
                autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="密 码：" prop="checkPass">
            <el-input
                prefix-icon="el-icon-menu"
                style="width: 250px"
                show-password
                type="password"
                v-model="user.password"
                autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')"
            >提交
            </el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "login",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户信息"));
      } else {
        if (this.user.password !== "") {
          this.$refs.user.validateField("password");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else if (value.length < 8 || value.length > 13) {
        callback(new Error("长度必须在8-12之内!"));
      } else {
        callback();
      }
    };
    return {
      loading: false,
      user: {
        username: "",
        password: "",
      },
      rules: {
        username: [{validator: validatePass, trigger: "blur"}],
        password: [{validator: validatePass2, trigger: "blur"}],
      },
    };
  },
  created() {
    let token = localStorage.getItem("token")
    if(token) {
      this.$router.push("/dashboard")
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log("submit!");
          this.$axios.post("http://localhost:80/login?username=" + this.user.username + "&password=" + this.user.password).then((res) => {
              const jwt = res.headers['authorization'];
              console.log(jwt);
              this.$store.commit("SET_TOKEN", jwt);
              this.$router.push("/dashboard");
          })
          // axios.post("http://localhost:80/login?username=" + this.user.username + "&password=" + this.user.password).then((res) => {
          //   console.log(res);
          // })
          // this.$router.push({
          //   path: "/dashboard",
          // });

        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style>
* {
  padding: 0;
  margin: 0;
}

/*body {*/
/*  background: rgb(135, 206, 235);*/
/*}*/

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  /*background: rgb(135, 206, 235);*/
}

#admin {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -200px;
  margin-left: -250px;
  width: 500px;
  height: 400px;
  background: #fff;
  border-radius: 10%;
  box-shadow: 8px 10px 10px rgb(177, 223, 242);
}

.adminh1 {
  margin: 20px 0;
  /*text-shadow: 10px 13px 3px rgb(207, 207, 207);*/
  text-align: center;
}

.pos {
  width: 450px;
  height: 350px;
  position: absolute;
  top: 25px;
  left: 25px;
}
</style>