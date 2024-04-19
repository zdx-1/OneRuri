<template>
  <div class="home">
    <el-container>
      <el-header>
        <el-row :gutter="10">
          <el-col :span="6">
            <div class="logo">
              <img src="../assets/logo.png" alt="">
            </div>
          </el-col>
          <el-col :span="6" :offset="12">
            <div class="user">
              <span>{{loginForm.username}}</span>
              <el-button @click="quit">返回登录页面</el-button>
            </div>
          </el-col>
        </el-row>

      </el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src


import router from "@/router";

export default {
  name: 'HomeView',
  components: {

  },
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      }
    }
  },
  mounted() {
    let userInfo = JSON.parse(sessionStorage.getItem("UserInfo"));
    if (userInfo == null) {
      this.$router.push("/")
    }
    console.log(userInfo)
    this.loginForm.username = userInfo.username
  },
  methods: {
    quit(){
     this.$router.push("/")
    }
  }
}
</script>

<style scoped>
.home {
  height: 100vh;
}
.el-header {
  background-color: #b3c0d1;
}
.el-aside {
  background-color: #d3dce6;
  height: 95vh;
}
.el-main {
  background-color: #e9eef3;
}
.logo{
  display: flex;
  align-items: center;
  margin-right: 20px;
  margin-left: 20px;
  img{
    width: 50px;
    height: 50px;
  }
}
.user{
}
</style>
