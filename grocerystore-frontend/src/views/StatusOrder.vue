<template>
<div class="AccountInfo">
    <div class = "navbar">
        <label>AppName</label>
        <div>
            <button @click="Catalog()">Catalog</button> 
            <button @click="Cart()">Cart/Checkout</button> 
            <button>Order Status</button>
            <button  @click="AccInfo()">Account Information</button>
        </div>
        <div><button @click="logout()">Logout</button></div>
    </div>
     <h1 style="margin-top:1%;">Order Status</h1>

    <br>

    <div>
        <table>
            <tr>
                <th><label style = "margin-left: 15px">Delivery Orders: </label></th>
            </tr>
            <tr v-for="delivery in deliveries" :key=delivery.orderID>
                <td style = "margin-left: 25px">{{ delivery.orderID }}     {{ delivery.status }}</td>
            </tr>

            <tr>
                 <th><label style = "margin-left: 8px">Pick-Up Orders: </label></th>
            </tr>
            <tr v-for="pickup in pickups" :key=pickup.orderID>
                <td style = "margin-left: 18px">{{ pickup.orderID }}     {{ pickup.status }}</td>
            </tr>
        </table>
         <span v-if="errorName" style="color:red">Error: {{ errorName }} </span>
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
    name: 'StatusOrderScript',

    data () {
        return {
            deliveries: [],
            pickups: [],
            erorrName: ''
        }
      },

      created: function () {
        var username = localStorage.getItem('token')
        //not yet written --> get deliveries of one customer
        AXIOS.get('/api/order/deliveryOrders/'.concat(username))
        .then(response => {
            this.deliveries.push(response.data)
        })
        .catch(e => {
           var errorMsg = e.response.data.message
           console.log(errorMsg)
           this.errorName = errorMsg
        }),
         //not yet written --> get pickup of one customer
        AXIOS.get('/api/order/pickupOrders/'.concat(username))
        .then(response => {
            this.pickups.push(response.data)
        })
        .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorName = errorMsg
        })
      },
    
    methods: {
       logout: function(){
            if (confirm("Press OK to logout")) {
                this.$router.push('/Signup');
                localStorage.removeItem('token');
            }
        },
        AccInfo: function(){
            this.$router.push('/AccountInfo');
        },
         Cart: function(){
            this.$router.push('/Cart');
        }
    }
}
</script>

<style scoped>
</style>
<style>
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
</style>