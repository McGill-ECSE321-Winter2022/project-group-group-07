<template>
  <div class="Cart">
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
        <button
          v-if="owner"
          onclick="location.href = '/#/AccountInfoEmployee';"
        >
          Account Information
        </button>
      </div>
      <div><button @click="logout()">Logout</button></div>
    </div>

    <div class="column">
      <div class="one">
        <div class="checkout_label">
          <h2>My cart</h2>
        </div>
        <section class="products" v-if="products.length > 0">
          <div v-for="product in products" :key="product.id" class="product">
            <Product
              :product="product"
              cart="true"
              @remove="updateCart(product, 'remove')"
              @add="updateCart(product, 'add')"
              @subtract="updateCart(product, 'subtract')"
            />
          </div>
        </section>
        <h2 v-else>Cart is lonely :( ...</h2>
      </div>
      <div class="two">
        <div class="order_label">
          <h2>Order summary</h2>
        </div>
        <div class="orderSummary">
          <p>Subtotal: {{ calculateSum(products) }} CAD</p>
          <p>Discount: {{ discount.toFixed(2) }} CAD</p>
          <p>Total: {{ (calculateSum(products) - discount).toFixed(2) }} CAD</p>
          <Button
            text="Proceed to Checkout"
            color="black"
            @btn-click="routeToCheckout"
          />
        </div>
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
  created: function() {
    this.clerk = localStorage.getItem("role").includes("Clerk");
    this.deliveryPerson = localStorage
      .getItem("role")
      .includes("DeliveryPerson");
    this.cashier = localStorage.getItem("role").includes("Cashier");
    this.owner = localStorage.getItem("role").includes("Owner");
    this.customer = localStorage.getItem("role").includes("Customer");
    this.refreshCart();
  },
  data() {
    return {
      clerk: false,
      deliveryPerson: false,
      cashier: false,
      owner: false,
      customer: true,
      payMethod: 0,
      points: 0,
      discount: 0,
      products: []
    };
  },
  methods: {
    refreshCart(){
      var username = localStorage.getItem("token");
        AXIOS.get("/api/cart/cart/" + username).then(response => {
          this.products=response.data.items;
        }).catch(e => {
          window.alert(e.response.data);
        })
    },
    calculateSum: function(products) {
      var sum = 0;
      for (var i = 0; i < products.length; i++) {
        sum += products[i].price;
      }
      return sum;
    },
    nothing: function() {
      return 0;
    },
  routeToCheckout(){
    this.$router.push("/Checkout");
  },
    updateCart(product, updateType) {
      for (let i = 0; i < this.products.length; i++) {
        if (this.products[i].id === product.id) {
          if (updateType === "subtract") {
            if (this.products[i].quantity !== 0) {
              this.products[i].quantity--;
            }
          } else if (updateType === "add") {
            if (this.products[i].quantity < this.products[i].inventory) {
              this.products[i].quantity++;
            }
          } else if (updateType === "remove") {
            this.products.splice(i, 1);
          }

          break;
        }
      }
    },
    applyPoints() {
      let dis = document.getElementById("points").value;
      if (this.points >= dis) {
        this.discount += dis * 0.01;
        this.points -= dis;
      }
    },
    updatePayment(newMethod) {
      this.payMethod = newMethod;
    },
    logout: function() {
      if (confirm("Press OK to logout")) {
        localStorage.removeItem("role");
        localStorage.removeItem("token");
        this.$router.push("/Login");
      }
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
