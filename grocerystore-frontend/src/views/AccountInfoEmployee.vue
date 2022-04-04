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
        <a href="/#/EditProfile"><button>Edit Account Informations</button></a><br />

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
            this.variable=localStorage.getItem('role').localeCompare('Clerk')==0;
            this.variable1=localStorage.getItem('role').localeCompare('DeliveryPerson')==0;
      },
    
    methods: {
        
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