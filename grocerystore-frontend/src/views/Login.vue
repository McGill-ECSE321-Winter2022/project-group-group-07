<template>
  <div>
    <div class = "navbar">
      <label>AppName</label>
      <div>
    </div>
  </div>
    <h1 style="margin-top:1%;">Log In</h1>
    <div>
      <form style="margin-top:20px; margin-left:40px;">
        <CustomInput
          label="Username"
          type="text"
          :value="username"
          @change="v => (username = v)"
        />
        <CustomInput
          label="Password"
          type="password"
          :value="password"
          @change="v => (password = v)"
        />
        <Button @btn-click="login()"
          text="Login"
          color="black"
          style="margin-top:10px; width:203px;"
        />
      </form>
    </div>
  </div>
</template>

<script>
import Button from '../components/Button.vue'
import CustomInput from '../components/CustomInput.vue'
import axios from 'axios'
import { ListGroupPlugin } from 'bootstrap-vue'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
export default {
  name: "Login",
  components: {
    CustomInput,
    Button
  },
  data() {
    return {
      username: "",
      password: "",
    };
  },
  created: function () {
  },
  methods: {
    routeToSignup: function(){
      this.$router.push('/Signup');
    },
    routeToCatalogue: function(){
      this.$router.push('/Hello');
    },
    login: function(){
        AXIOS.post('/api/account/login/?'+"username="+this.username+"&password="+this.password)
        .then(response => {
          console.log(response.data);
          localStorage.setItem('token',response.data.username);
          localStorage.setItem('pointBalance',response.data.pointBalance);
          localStorage.setItem('role',response.data.role);
          if (localStorage.getItem("role").localeCompare("Customer") == 0) {
            this.$router.push('/Cart');
          } else {
            this.$router.push('/AccountInfoEmployee');
          }

        })
        .catch(e => {
          window.alert(e.response.data);
        })
  }
  }
};
</script>

<style scoped>
.navbar{
    height:  auto;
    background-color: rgb(40, 50, 50);
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    color: azure;
    display: inline-flex;
    width: 100%;
}
.navbar div{
  display:inline-block;
}
.navbar div button {
    background-color: rgba(0, 0, 0, 0);
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size:large;
    color: azure;
    border-style: none;
    height: 2em;
    padding-top:0;
    margin-left:2em;
    margin-top:0px;
}
.navbar label{
   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: large;
    padding-top:0;
    margin-top:0px;
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}
.navbar button:hover{
        font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    border-style:solid;
    border-color: azure;
    border-radius: 0.5em;
}
.btn {
  display: inline-block;
  background: #000;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  text-decoration: none;
  font-size: 15px;
  font-family: inherit;
  width: 200px;
  height: 40px;
}
.column1 {
  float: left;
  width: 100%;
  padding: 10px;
  text-align: left;
}
</style>