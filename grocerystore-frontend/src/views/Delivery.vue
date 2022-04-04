<template>
    <div class="Delivery">
        <div class = "navbar">
            <label>AppName</label>
            <div>
                <button v-if="variable" onclick= "location.href = '/#/PickUp';">Pickup Orders</button> 
                <button v-if="variable1" onclick="location.href = '/#/Delivery';">Delivery Orders</button> 
                <button onclick="location.href = '/#/AccountInfoEmployee';">Account Information</button>
            </div>
            <div><button>Logout</button></div>
        </div>

        <h1 style="margin-top:1%">Delivery</h1>
        <button @click="updateOrders()" style="margin-top:1%;">Update Orders</button>
        <div align="left"><label>Orders to be fulfilled</label></div>
            <div class="one">
                <section class="products" v-if="orders.length > 0">
                    <div v-for="order in orders" :key="order.id" class="product">
                        <Order
                            :order="order"
                            @remove="updateCart(order)"
                        />
                    </div>
                </section>
            </div>
        </div>
    </div>
</template>


<script>
    import Order from "../components/Order.vue";
    import axios from 'axios'
    import { ListGroupPlugin } from 'bootstrap-vue'
    var config = require('../../config')
    var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
    var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

    var AXIOS = axios.create({
        baseURL: backendUrl,
        headers: { 'Access-Control-Allow-Origin': frontendUrl }
    })
    export default{
        name: "Delivery",
        components: {
            Order
        },
        data(){
            return {
                variable: true,
                variable1: false,
                orders : []
            }
        },
        created: function() {
            this.variable=localStorage.getItem('role').localeCompare('Clerk')==0;
            this.variable1=localStorage.getItem('role').localeCompare('DeliveryPerson')==0;
        },
        methods: {
            async updateOrders(){
                          AXIOS.get('/api/order/pendingDeliveryOrders')
                          .then(response => {
                            this.orders = response.data;
                          })
                          .catch((e) => {
                            window.alert("Failed to update.");
                            return;
                          })
                        },
            async updateCart(order) {
                for (let i = 0; i < this.orders.length; i++) {
                    if (this.orders[i].orderID === order.orderID) {
                        console.log(this.orders[i].orderID);
                        AXIOS.put('/api/order/deliveryUpdate/'+this.orders[i].orderID+"?status=Delivered")
                        .then(function (response) {
                          console.log(response);
                        })
                        .catch(function (error) {
                          console.log(error);
                        });
                        this.orders.splice(i, 1);
                    }
                    break;
                }
            },
            
        },
        
        
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
.center {
    margin-left: auto;
    margin-right: auto;
}
h1,
h2 {
  font-weight: normal;
}

.checkout_label {
  background-color: rgb(40, 50, 50);
  color: white;
}
.order_label {
  background-color: whitesmoke;
  border-top: 5px solid rgb(40, 50, 50);
  border-bottom: 5px solid rgb(40, 50, 50);
}
.column {
  padding: 10px;
  display: flex;
}
.one {
  background-color: white;
  flex: 2;
  border-right: 5px solid rgb(40, 50, 50);
}
.two {
  background-color: white;
  flex: 1;
  display: flex;
  height: 30%;
  flex-direction: column;
  gap: 10px;
}
.orderSummary {
  background-color: whitesmoke;
  height: 150px;
  flex: 1;
  padding: 10px;
}
.products {
  display: grid;
  grid-template-columns: 1fr;
  grid-row-gap: 10px;
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
.navbar {
  height: auto;
  background-color: rgb(40, 50, 50);
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: azure;
  display: inline-flex;
  width: 100%;
}
.checkout_buttons {
  border-radius: 8px;
  background-color: darkgreen;
  border: darkgreen;
  color: white;
  height: 40px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  cursor: pointer;
}
</style>