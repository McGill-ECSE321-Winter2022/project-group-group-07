<template>
  <div class="PickUp">
    <div class="navbar">
      <label>AppName</label>
      <div>
        <button v-if="customer" onclick="location.href = '/#/Catalogue';">
          Catalogue
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
    
        <button v-if="clerk" onclick="location.href = '/#/PickUp';">
          Pickup Orders
        </button>
         <button v-if="cashier" onclick="location.href = '/#/PickUp';">
          Pickup Orders
        </button>
        <button v-if="deliveryPerson" onclick="location.href = '/#/Delivery';">
          Delivery Orders
        </button>
        <button
          v-if="clerk"
          onclick="location.href = '/#/AccountInfoEmployee';"
        >
          Account Information
        </button>
        <button
          v-if="cashier"
          onclick="location.href = '/#/AccountInfoEmployee';"
        >
          Account Information
        </button>
        <button
          v-if="deliveryPerson"
          onclick="location.href = '/#/AccountInfoEmployee';"
        >
          Account Information
        </button>
        
        <button v-if="owner" onclick="location.href = '/#/Report';">
          Generate Report
        </button>
        
        <button v-if="owner" onclick="location.href = '/#/ManageEmployees';">
          Manage Employees
        </button>
        <button v-if="owner" onclick="location.href = '/#/ManageInventory';">
          Manage Inventory
        </button>
         <button v-if="owner" onclick="location.href = '/#/StoreInfo';">
          Store Info
        </button>
      </div>
      <div><button @click="logout()">Logout</button></div>
    </div>

    <h1 style="margin-top:1%">Pick Up</h1>
    <Button @btn-click="updateOrders()" style="margin-top:1%;" text="Update Orders" color="black" />
    <div align="left"><label>Orders to be fulfilled</label></div>
    <div class="one">
      <section class="products" v-if="orders.length > 0">
        <div v-for="order in orders" :key="order.ID" class="product">
          <Order :order="order" @remove="updateCart(order, 'remove')" />
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import Button from '../components/Button.vue'
import Order from "../components/Order.vue";
import axios from "axios";
import { ListGroupPlugin } from "bootstrap-vue";
var config = require("../../config");

var frontendUrl = "https://" + config.build.host + ":" + config.build.port;
var backendUrl =
  "https://" + config.build.backendHost + ":" + config.build.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});
export default {
  name: "hello",

  components: {
    Order,
    Button
  },
  created: function() {
    this.clerk = localStorage.getItem("role").includes("Clerk");
    this.deliveryPerson = localStorage
      .getItem("role")
      .includes("DeliveryPerson");
    this.cashier = localStorage.getItem("role").includes("Cashier");
    this.owner = localStorage.getItem("role").includes("Owner");
    this.customer = localStorage.getItem("role").includes("Customer");
  },
  data() {
    return {
      clerk: false,
      deliveryPerson: false,
      cashier: false,
      owner: false,
      customer: true,
      orders: []
    };
  },

  methods: {
    async updateCart(order) {
      for (let i = 0; i < this.orders.length; i++) {
        if (this.orders[i].orderID === order.orderID) {
          console.log(this.orders[i].orderID);
          AXIOS.put(
            "/api/order/PickUpUpdate/" +
              this.orders[i].orderID +
              "?status=Ready"
          )
            .then(function(response) {
              console.log(response);
            })
            .catch(function(error) {
              console.log(error);
            });
          this.orders.splice(i, 1);
        }
        break;
      }
    },
    async updateOrders() {
      AXIOS.get("/api/order/pendingPickUpOrders")
        .then(response => {
          this.orders = response.data;
        })
        .catch(e => {
          window.alert("Update Failed.");
          return;
        });
    },
      logout: function() {
      if (confirm("Press OK to logout")) {
        localStorage.removeItem("role");
        localStorage.removeItem("token");
        localStorage.removeItem("pointBalance")
        this.$router.push("/Login");
      }
    }
  }
};
</script>

<style scoped>
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
