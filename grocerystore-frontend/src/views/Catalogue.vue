<template>
  <div class="Cart">
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
        <button v-if="cashier" onclick="location.href = '/#/Terminal';">
          Terminal
        </button>
        <button v-if="clerk" onclick="location.href = '/#/PickUp';">
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
      <div>
        <button v-if="loggedin" @click="logout()">Logout</button
        ><button v-if="!loggedin" @click="login()">Login</button>
      </div>
    </div>
    <div class="navbar">
      <label></label>
      <div style="width: 500px;">
        <label>Search:</label>
        <input type="text" placeholder="item name" class="searchBar" />
      </div>
      <div>
        <button>Cart ({{ cartNumOfItems }})</button>
      </div>
    </div>

    <div class="column">
      <div class="one">
        <section class="products" v-if="items.length > 0">
          <div v-for="item in items" :key="item.id" class="product">
            <Product
              @addToCart="(v,num) => addToCart(v,num)"
              :product="item"
              catalogue="true"
            />
          </div>
        </section>
        <h2 v-else>No items to display</h2>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

import Product from "../components/Product.vue";
import Button from "../components/Button.vue";
export default {
  name: "hello",
  components: {
    Product,
    Button
  },
  data() {
    return {
      clerk: false,
      deliveryPerson: false,
      cashier: false,
      owner: false,
      customer: false,
      payMethod: 0,
      points: 10000,
      discount: 0,
      items: [],
      cartNumOfItems: 0,
      loggedin: false
    };
  },
  created() {
    if (localStorage.getItem("token") == null) {
      this.loggedin = false;
    } else {
      this.loggedin = true;
      this.clerk = localStorage.getItem("role").includes("Clerk");
      this.deliveryPerson = localStorage
        .getItem("role")
        .includes("DeliveryPerson");
      this.cashier = localStorage.getItem("role").includes("Cashier");
      this.owner = localStorage.getItem("role").includes("Owner");
      this.customer = localStorage.getItem("role").includes("Customer");
      var username = localStorage.getItem("token");
      if (username == null) {
        this.$router.push("/");
      }
    }
    this.refreshItems();
    if (this.loggedin) {
      this.refreshCartNumber();
    }
  },
  methods: {
    login() {
      this.$router.push("/Login");
    },
    logout: function() {
      if (confirm("Press OK to logout")) {
        localStorage.removeItem("role");
        localStorage.removeItem("token");
        localStorage.removeItem("pointBalance");
        this.$router.push("/Login");
      }
    },
    routeToCheckout: function() {
      this.$router.push("/Checkout");
    },
    refreshItems() {
      AXIOS.get("/api/item/items")
        .then(response => {
          this.items = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    },
    addToCart(id,numInStock) {
      var username = localStorage.getItem("token");
      if(username == null){
        window.alert("You must login first to add to cart.")
        return;
      }
      if(numInStock == 0){
        window.alert("Item is out of stock. Please try again at a later date.")
        return;
      }
      AXIOS.put("/api/cart/addToCart/" + id + "?username=" + username)
        .then(response => {
          this.refreshItems();
          this.refreshCartNumber();
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    },
    refreshCartNumber() {
      var username = localStorage.getItem("token");
      AXIOS.get("/api/cart/cart/" + username)
        .then(response => {
          this.cartNumOfItems = response.data.numOfItems;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    }
  }
};
</script>

<style scoped>
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
.searchBar {
  border-radius: 5px;
  width: 50%;
  display: inline;
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
</style>
