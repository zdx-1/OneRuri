<template>
  <div class="center">

    <h1>Hello World</h1>

    <div class="login">
      <div :class="loginOrRegister">
        <div class="loginOrRegister-signIn" v-if="disFlex === 0">
          <h2 class="loginOrRegisterH2">登录</h2>
          <el-form :model="LoginOrRegisterFrom" ref="LoginOrRegisterFrom" @submit.native.prevent="handleLogin">
            <el-form-item><el-input v-model="LoginOrRegisterFrom.username" type="text"  placeholder="username"></el-input></el-form-item>
            <el-form-item><el-input v-model="LoginOrRegisterFrom.password" type="password" placeholder="password"></el-input></el-form-item>
            <h3 @click="forgotPassword">Forgot your password?</h3>
            <el-button class="inputButton" type="primary" native-type="submit">登录</el-button>
          </el-form>

        </div>
        <div class="loginOrRegister-Signup" v-if="disFlex === 1">
          <h2 class="loginOrRegisterH2">注册</h2>
          <el-form :model="LoginOrRegisterFrom2" ref="LoginOrRegisterFrom2">
            <el-form-item><el-input v-model="LoginOrRegisterFrom2.username" type="text" placeholder="username"></el-input></el-form-item>
            <el-form-item><el-input  type="password" placeholder="password"></el-input></el-form-item>
            <el-form-item><el-input v-model="LoginOrRegisterFrom2.password" type="password" placeholder="password_again"></el-input></el-form-item>
            <el-form-item><el-button class="inputButton" type="primary" @click="registerBtn">登录</el-button></el-form-item>
          </el-form>
        </div>
      </div>
      <div :class="information">
        <div class="information-signIn" v-if="disFlex === 0">
          <h2 class="informationH2">Hello,Friend!</h2>
          <p class="informationP">
            欢迎回来！
          </p>
          <el-button class="goOtherBtn" @click="signIn">注册</el-button>
        </div>
        <div class="information-Signup" v-if="disFlex === 1">
          <h2 class="informationH2">欢迎加入我们</h2>
          <p class="informationP">we are glad to have you</p>
          <el-button class="goOtherBtn" @click="Signup">登录</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginOrRegister: 'loginOrRegister',
      information: 'information',
      disFlex: 0,
      LoginOrRegisterFrom:{
        username:'',
        password:'',
      },
      LoginOrRegisterFrom2:{
        username:'',
        password:'',
        passwordAgain:''
      }
    }
  },
  methods: {
    handleLogin() {
      this.$refs.LoginOrRegisterFrom.validate(valid => {
        console.log(valid)
        console.log(this.LoginOrRegisterFrom)
        if(valid){
          this.$axios.post('/user/login',this.LoginOrRegisterFrom).then(res => {
            res = res.data
            console.log(res)
            if (res.code === "0") {
              sessionStorage.setItem("UserInfo", JSON.stringify(res.data));
              this.$router.push('/home')
              this.$message({
                message: res.msg,
                type: 'success'
              });
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    signIn() {
      this.loginOrRegister = "loginOrRegisterLeft"
      this.information = "informationRight"
      setTimeout(() => {
        this.disFlex = 1
      }, 200)
    },
    Signup() {
      this.loginOrRegister = "loginOrRegisterRight"
      this.information = "informationLeft"

      setTimeout(() => {
        this.disFlex = 0
      }, 200)
    },
    forgotPassword(){
      this.$router.push({
        path: '/forgotPassword'
      })
      }
    },
}
</script>



<style>

.center {
  width: 100vw;
  height: 100vh;
  //background-image: url('https://gd-hbimg.huaban.com/9165dc3c0f8279ae5402d1f47212847bff68b49f3cb547-QEli8R');
  background-image: url("@/assets/bac01.jpg");
  background-size: 100% 100%;
  background-repeat: no-repeat;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
h1 {
  font-size: 30px;
  color: black;
}

.login {
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  /* position: relative;
  overflow: hidden; */
  width: 768px;
  max-width: 100%;
  min-height: 480px;
  margin-top: 20px;
  display: flex;
  background: #fff -webkit-linear-gradient(right, #4284db, #29eac4);
}

.loginOrRegister {
  border-radius: 10px 0 0 10px;
  width: 50%;
  height: 100%;
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
}

.loginOrRegisterLeft {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: #fff;
  transform: translateX(100%);
  transition: transform 0.6s ease-in-out;
  display: flex;
  align-items: center;
  justify-content: center;
}

.loginOrRegisterRight {
  border-radius: 10px 0 0 10px;
  width: 50%;
  height: 100%;
  background-color: #fff;
  transform: translateX(0%);
  transition: transform 0.6s ease-in-out;
  display: flex;
  align-items: center;
  justify-content: center;
}

.information {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0);
  display: flex;
  align-items: center;
  justify-content: center;
}


.informationH2 {
  font-size: 30px;
  color: #fff;
  margin-top: 20px;
}

.informationP {
  font-size: 15px;
  color: #fff;
  margin-top: 20px;
}

.informationLeft {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0);
  display: flex;
  align-items: center;
  justify-content: center;
  transform: translateX(0%);
  transition: transform 0.6s ease-in-out;
}

.informationRight {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0);
  display: flex;
  align-items: center;
  justify-content: center;
  transform: translateX(-100%);
  transition: transform 0.6s ease-in-out;
}

.information-signIn {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.information-Signup {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.goOtherBtn {
  width: 180px;
  height: 40px;
  border-radius: 50px;
  border: 1px solid #fff;

  font-size: 15px;
  text-align: center;
  line-height: 20px;
  margin-top: 40px;
}

.loginOrRegisterH2 {
  font-size: 25px;
  color: black;
  /* width: 250px; */
}

.loginOrRegister-signIn {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.loginOrRegister-Signup {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 10px 0;
  width: 240px;
}
h3{
  font-size: 10px;
  margin-top: 10px;
  cursor: pointer;
}
.inputButton{
  background-color: #29eac4;
  border: none;
  width: 180px;
  height: 40px;
  border-radius: 50px;
  font-size: 15px;
  color: #fff;
  text-align: center;
  line-height: 20px;
  margin-top: 30px;
}

</style>
