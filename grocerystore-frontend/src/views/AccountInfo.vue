<template>
  <div class="AccountInfo">
    <div class="navbar">
      <label>AppName</label>
      <div>
        <button>Catalog</button>
        <button @click="Cart()">Cart/Checkout</button>
        <button @click="StatsOrder()">Order Status</button>
        <button>Account Information</button>
      </div>
      <div><button @click="logout()">Logout</button></div>
    </div>
    <h1 style="margin-top:1%;">Account Informations</h1>

    <br />

    <div>
      <label>UserName: </label>
      <label id="username"> {{ customerAccount.username }} </label><br />
      <label>Name: </label>
      <label id="name"> {{ customerAccount.name }} </label>
      <!--button @click="changeName()">Change Name</button>
      <button @click="changePassword()">Change Password</button><br />-->
      <span v-if="errorName" style="color:red">Error: {{ errorName }} </span>

      <br />

      <label>Address: </label>
      <label id="address"> {{ customerAddress.buildingNo }}, {{ customerAddress.street }}, {{ customerAddress.town }} </label>
      <!--button @click="changeAddress()">Change Address</button><br />-->

      <br />
      <label>Current Points: </label>
      <label id="points"> {{ customerAccount.pointBalance }} </label><br/>
       <span v-if="errorName" style="color:red">Error: {{ errorAddress }} </span>
      <br/>
       <a href="/#/EditProfile"><button>Edit Account Informations</button></a><br />
    </div>
  </div>
</template>
<script>
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'AccountInfoScript',

    data () {
        return {
            customerAccount: { username: '', name: '', pointBalance: '', role: ''},
            errorName: '',
            customerAddress: {	buildingNo: '', street: '', town: '', account: null }
        }
      },

      created: function () {
        this.refresh();
      },
    
    methods: {
      StatsOrder: function(){
            this.$router.push('/StatusOrder');
      },
      Cart: function(){
        this.$router.push('/Cart');
      },
      logout: function(){
            if (confirm("Press OK to logout")) {
                this.$router.push('/Signup');
                localStorage.removeItem('token');
            }
        },
      refresh: function(){
      var username = (localStorage.getItem('token'));
        if(username == null){
          this.$router.push('/');
        }
        AXIOS.get('/api/account/'.concat(username))
        .then(response => {
            this.customerAccount = response.data
            console.log(response.data)
            this.errorName=''
        })
        .catch(e => {
            window.alert(e.response.data)
            return
        }),
        AXIOS.get('/api/address/address/'.concat(username))
        .then(response => {
            this.customerAddress = response.data
        })
        .catch(e => {
            var errorMsg = e.response.data
            console.log(errorMsg)
            this.errorAddress = errorMsg
        })
      }
        /*changeName: function(){
            let username = prompt("Please enter your current Username", "Enter username");
            let newName = prompt("Please enter your new Username", "Enter new name");
            AXIOS.put('/api/account/updateName/'.concat(username).concat('?newName=').concat(newName))
            .then(response => {
                this.customerName = response.data
                this.errorName = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorName = errorMsg
            })
        },
        changePassword: function(){
            let username = prompt("Please enter your current Username", "Enter username");
            let oldPassword = prompt("Please enter your old password", "Enter old password");
            let newPassword = prompt("Please enter your new password", "Enter new password");
             AXIOS.put('/api/account/updatePassword/'.concat(username).concat('?oldPassword=').concat(oldPassword).concat('?newPassword=').concat(newPassword))
            .then(response => {
                this.errorName = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorName = errorMsg
            })
        },
        changeAddress: function(){
          let username = prompt("Please enter your current Username", "Enter username");
          let buildingNo = prompt("Please enter your new Building Number", "Enter building number");
          let street = prompt("Please enter your new street name", "Enter street name");
          let town = prompt("Please enter your new town name", "Enter town name");
             AXIOS.put('/api/address/'.concat(username).concat('?buildingNo=').concat(buildingNo).concat('?street=').concat(street).concat('?town=').concat(town))
            .then(response => {
                this.errorName = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorName = errorMsg
            })
        }*/
    }
}
</script>

<style scoped>
.navbar {
  height: auto;
  background-color: rgb(40, 50, 50);
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: azure;
  display: inline-flex;
  width: 100%;
}
.navbar div {
  display: inline-block;
}
.navbar div button {
  background-color: rgba(0, 0, 0, 0);
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-size: large;
  color: azure;
  border-style: none;
  height: 2em;
  padding-top: 0;
  margin-left: 2em;
  margin-top: 0px;
}
.navbar label {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-size: large;
  padding-top: 0;
  margin-top: 0px;
  font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
}
.navbar button:hover {
  font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
  border-style: solid;
  border-color: azure;
  border-radius: 0.5em;
}
</style>
