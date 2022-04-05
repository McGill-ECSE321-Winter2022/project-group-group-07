<template>
  <div class="AccountInfo">
    <div class="navbar">
      <label>AppName</label>
      <div>
        <button v-if="customer" onclick="location.href = '/#/Catalog';">
          Catalog
        </button>
        <button v-if="customer" onclick="location.href = '/#/Cart';">
          Cart
        </button>
        <button v-if="customer" onclick="location.href = '/#/StatusOrder';">
          Order Status
        </button>
        <button v-if="customer" onclick="location.href = '/#/AccountInfo';">
          Account Information
        </button>
        <button v-if="cashier" onclick="location.href = '/#/Terminal';">
          Terminal
        </button>
        <button v-if="clerk" onclick="location.href = '/#/PickUp';">
          Pickup Orders
        </button>
        <button v-if="deliveryPerson" onclick="location.href = '/#/Delivery';">
          Delivery Orders
        </button>
        <button v-if="clerk" onclick="location.href = '/#/AccountInfoEmployee';">
          Account Information
        </button>
        <button v-if="cashier" onclick="location.href = '/#/AccountInfoEmployee';">
          Account Information
        </button>
        <button v-if="deliveryPerson" onclick="location.href = '/#/AccountInfoEmployee';">
          Account Information
        </button>
        <button v-if="owner" onclick="location.href = '/#/AccountInfoEmployee';">
          Account Information
        </button>
      </div>
      <div><button @click="logout()">Logout</button></div>
    </div>
    <h1 style="margin-top:1%;">Edit Account Information</h1>
    <br />
    <div class="column1">
      <form style="margin-top:20px; margin-left:40px;">
        <label style="font-size: 24px">Update Name</label>
        <CustomInput
          label="Current username:"
          type="text" 
          :value="username1"
          @change="v => (username1 = v)"
        />
         <CustomInput
          label="New name:"
          type="text" 
          :value="newName"
          @change="v => (newName = v)"
        />
        <br/>
        <button class="button" @click="changeName()">Update</button>
        <span v-if="errorName" style="color:red">Error: {{ errorName }} </span>
      </form>
    </div>
    <div class="column2">
      <form style="margin-top:20px; margin-left:40px;">
        <label style="font-size: 24px">Update Password</label>
        <CustomInput
          label="Current username:"
          type="text"
          v-bind="username2"
          @change="v => (username2 = v)"
        />
         <CustomInput
          label="Current password:"
          type="text"
          :value="oldPassword"
          @change="v => (oldPassword = v)"
        />
         <CustomInput
          label="New password:"
          type="text"
          :value="newPassword"
          @change="v => (newPassword = v)"
        />
        <br/>
        <button class="button" @click="changePassword()">Update</button>
        <span v-if="errorPassword" style="color:red">Error: {{ errorPassword }} </span>
      </form>
    </div>
    <div class="column3">
         <form style="margin-top:20px; margin-left:40px;">
        <label style="font-size: 20px; margin-top:1em;">Update Address</label>
         <CustomInput
          label="Current username"
          type="text"
          :value="username3"
          @change="v => (username3 = v)"
        />
        <CustomInput
          label="Bulding No."
          type="number"
          :value="buildingNo"
          @change="v => (buildingNo = v)"
        />
        <CustomInput
          label="Street"
          type="text" :value="street"
          @change="v => (street = v)"
        />
        <CustomInput
          label="Town"
          type="text"
          :value="town"
          @change="v => (town = v)"
        />
        <br/>
        <button class="button" @click="changeAddress()">Update</button>
        <span v-if="errorAddress" style="color:red">Error: {{ errorAddress }} </span>
         </form>
    </div>
    <br/>
</div>

</template>
<script>
import axios from 'axios'
import CustomInput from "../components/CustomInput.vue";
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'AccountInfoScript',
    components: {
        CustomInput
    },
    data () {
        return {
            clerk: false,
            deliveryPerson: false,
            cashier: false,
            owner:false,
           customer: true,
            username: '',
            newName: '',
            errorName: '',
            oldPassword: '',
            newPassword: '',
            errorPassword: '',
            buildingNo: '',
            street: '',
            town: '',
            errorAddress: ''
        }
      },
      created: function () {
        this.clerk = localStorage.getItem("role").includes("Clerk");
    this.deliveryPerson = localStorage.getItem("role").includes("DeliveryPerson") ;
    this.cashier = localStorage.getItem("role").includes("Cashier") ;
    this.owner = localStorage.getItem("role").includes("Owner") ;
    this.customer = localStorage.getItem("role").includes("Customer") ;
      },
    
    methods: {
        changeName: function(){
            var username = this.username1
            var newName = this.newName
            AXIOS.put('/api/account/updateName/'.concat(username).concat('?newName=').concat(newName))
            .then(response => {
              this.$router.push('/AccountInfo'); 
            })
            .catch(e => {
                window.alert(e.response.data)
            })
        },
        changePassword: function(){
            var username = this.username2
            var oldPassword = this.oldPassword
            var newPassword = this.newPassword
             AXIOS.put('/api/account/updatePassword/'.concat(username).concat('?oldPassword=').concat(oldPassword).concat('&newPassword=').concat(newPassword))
            .then(response => {
                this.errorPassword = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorPassword = errorMsg
                window.alert(e.response.data);
            })
        },
        changeAddress: function(){
            var username = this.username3
            var buildingNo = this.buildingNo
            var street = this.street
            var town = this.town
             AXIOS.put('/api/address/updateAddress/'.concat(username).concat('?buildingNo=').concat(buildingNo).concat('&street=').concat(street).concat('&town=').concat(town))
            .then(response => {
                this.errorAddress = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorAddress = errorMsg
            })
        },
        logout: function(){
            if (confirm("Press OK to logout")) {
                localStorage.removeItem('role');
                localStorage.removeItem('token');
                this.$router.push('/Login');

            }
        },
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
.button {
  background-color: #2a5674;
  border: none;
  color: white;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin-top:10px;
  width:150px;
  cursor: pointer;
}
</style>