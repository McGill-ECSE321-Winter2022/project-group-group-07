<template>
<div class="AccountInfoEmployee">
    <div class = "navbar">   
        <label>AppName</label>
        <div>
            <button v-if="variable1" onclick= "location.href = '/#/PickUp';">Pickup Orders</button> 
            <button v-if="variable" onclick="location.href = '/#/Delivery';">Delivery Orders</button> 
            <button onclick="location.href = '/#/AccountInfoEmployee';">Account Information</button>
        </div>
        <div><button>Logout</button></div>
    </div>
     <h1 style="margin-top:1%;">Account Information</h1>

    <br>

    <div align="left">
        <label>UserName: </label>
        <label id = "username" > {{customerUsername}} </label>
        <br>
        <label>Name: </label>
        <label id = "name" > {{customerName}} </label>
        <button @click="changePassword()" class="block magenta margin-auto"> Change Password </button>
        
        <span v-if="errorName" style="color:red">Error: {{errorName}} </span>
        <br>
        
        <label>Address: </label>
        <label id = "address" > {{customerAddress}} </label>
        <button @click="changeAddress()"> Change Address </button><br>

        <br>

    </div>
    <br>
    <label style="font-size: 24px">Schedule</label>
      <table>
        <tr>
          <th>Day</th>
          <th>Start Time</th>
          <th>End Time</th>
        </tr>
        <tr>
          <td>Monday</td>
          <td>{{ MondayST }}</td>
          <td>{{ MondayET }}</td>
        </tr>
        <tr>
          <td>Tuesday</td>
          <td>{{ TuesdayST }}</td>
          <td>{{ TuesdayET }}</td>
        </tr>
        <tr>
          <td>Wednesday</td>
          <td>{{ WednesdayST }}</td>
          <td>{{ WednesdayET }}</td>
        </tr>
        <tr>
          <td>Thursday</td>
          <td>{{ ThursdayST }}</td>
          <td>{{ ThursdayET }}</td>
        </tr>
        <tr>
          <td>Friday</td>
          <td>{{ FridayST }}</td>
          <td>{{ FridayET }}</td>
        </tr>
        <tr>
          <td>Saturday</td>
          <td>{{ SaturdayST }}</td>
          <td>{{ SaturdayET }}</td>
        </tr>
        <tr>
          <td>Sunday</td>
          <td>{{ SundayST }}</td>
          <td>{{ SundayET }}</td>
        </tr>
      </table>
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
 export default{
        name: "PickUp",
      data () {
        return {
            variable: true,
            variable1: false,
            customerAccount: '',
            errorName: '',
            customerUsername: '',
            customerAddress: '',
            customerPoints: '',
            store: '',
            schedule: '',
        }
      },

      created: function () {
        var username = localStorage.getItem('token')
        this.customerUsername=username
        AXIOS.get('/api/account/'.concat(username))
        .then(response => {
            this.customerAccount = response.data
        })
        .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorName = errorMsg
        }),
        AXIOS.get('/api/address/'.concat(username))
        .then(response => {
            this.customerAddress = response.data
        })
        .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorName = errorMsg
        }),
        AXIOS.get('/api/store/')
        .then(response => {
            this.store = response.data
        })
        .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorName = errorMsg
        })
      },
    
    methods: {
        changePassword: function(){
            let username = prompt("Please enter your current Username", "Enter username");
            let oldPassword = prompt("Please enter your old password", "Enter old password");
            let newPassword = prompt("Please enter your new password", "Enter new password");
             AXIOS.put('/api/account/updatePassword/'.concat(username).concat('?oldPassword=').concat(oldPassword).concat('?newPassword=').concat(newPassword))
            .then(response => {
                window.alert(response.data)
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
        }
    }
}

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

table {
  margin-left: 2em;
  width: 90%;
  border-collapse: collapse;
}
th,
td {
  border: 1px solid black;
  padding: 5px;
  text-align: left;
}
</style>