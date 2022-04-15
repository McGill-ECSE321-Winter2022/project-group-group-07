<template>
  <div class="checkout" id="checkout">
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
      <div><button @click="logout()">Logout</button></div>
    </div>

    <div class="column">
      <div class="one">
        <h2 class="checkout_label">Checkout</h2>
        <div class="Discount">
          <h3>Discount points</h3>
          <p class="points">
            <b>Points Available:</b>
            <b style="color: red">{{ customerAccount.pointBalance }}</b>
          </p>
          <div>
            <CustomInput
              placeholder="enter amount of points"
              type="number"
              ref="points"
              :value="points"
              @change="v => points = v"
            />
          </div>
          <div style="margin-top: 10px;">
            <Button text="apply" color="black" @btn-click="applyPoints" />
          </div>
        </div>
        <div class="information">
          <h2>Order Type</h2>
          <input
            type="radio"
            ref="delivery"
            name="payment"
            @click="updateDelivery(1)"
          />
          <label for="delivery">Delivery</label><br />
          <div class="information" v-show="delivery_option == 1">
            <div class="information">
              <CustomInput
                placeholder="Address"
                type="text"
                :value="address"
                @change="v => (address = v)"
              /><br /><br />

              <p style="color: darkgreen"><b>Choose delivery date</b></p>
              <input
                @change="computeHours('deliveryDate')"
                type="date"
                id="deliveryDate"
                name="deliveryDate"
                min="2021-04-01"
                max="2021-04-30"
              /><br /><br />
              <label>Time Slot:</label><br />
              <select
                name="deliveryTime"
                ref="deliveryTime"
                style="width:200px; height:30px;"
              >
                <option
                  v-for="timeslot in this.timeslots"
                  :key="timeslot"
                  :value="timeslot"
                  >{{ timeslot }}</option
                >
              </select>
            </div>
          </div>

          <input
            type="radio"
            ref="pickup"
            name="payment"
            @click="updateDelivery(2)"
          />
          <label for="pickup">PickUp</label><br />
          <div class="delivery_info" v-show="delivery_option === 2">
            <div class="information">
              <div>
                <label> Store Address: {{ store.address }}</label>
              </div>
              <div>
                <b style="color: darkgreen">Choose pickup date</b><br /><br />
              </div>
              <input
                @change="computeHours('pickupDate')"
                type="date"
                name="pickupDate"
                id="pickupDate"
                min="2021-04-01"
                max="2021-04-30"
              /><br /><br />
              <label>Time Slot:</label><br />
              <select
                name="pickupTime"
                ref="pickupTime"
                style="width:200px; height:30px;"
              >
                <option
                  v-for="timeslot in this.timeslots"
                  :key="timeslot"
                  :value="timeslot"
                  >{{ timeslot }}</option
                >
              </select>
            </div>
          </div>
        </div>

        <form class="paymentMethod">
          <h2>Payment method</h2>
          <label for="CreditCard">Credit Card</label><br />
          <div class="payment_info">
            <div class="infor_label">
              <label> CardHolder Name </label><br /><br />
              <label> Credit Card number </label><br /><br />
              <label> Expiration Date </label><br /><br />
              <label> CVV </label><br /><br />
            </div>

            <div class="info">
              <input
                type="text"
                required
                id="cardHolder"
                name="cardHolder"
                v-model="cardHolder"
              /><br /><br />
              <input
                id="ccn"
                type="tel"
                inputmode="numeric"
                pattern="[0-9\s]{13,19}"
                autocomplete="cc-number"
                maxlength="19"
                v-model="ccn"
              /><br /><br />
              <input
                type="month"
                id="Expiration Date"
                name="Expiration Date"
                v-model="expirationDate"
              />
              <br /><br />
              <input
                type="password"
                style="width: 4em"
                name="pincode"
                maxlength="3"
                pattern="[0-9]"
                required
                v-model="cvv"
              /><br /><br />
            </div>
          </div>
        </form>
      </div>

      <div class="two">
        <div class="order_label">
          <h2>Order summary</h2>
        </div>
        <section class="products">
          <div v-for="product in products" :key="product.id" class="product">
            <div class="product_image">
              <img
                :src="product.imageLink"
                :alt="product.productName"
                class="product_image"
                style="width: 100px; min-width: 100px;"
              />
            </div>
            <div class="product_content">
              <div class="content_label">
                <p><b>Name</b></p>
                <p><b>Price</b></p>
              </div>
              <div class="content_value">
                <p>{{ product.productName }}</p>
                <p>{{ product.price }}</p>
              </div>
            </div>
          </div>
          <div class="orderSummary">
            <p>Subtotal: {{ calculateSum(products).toFixed(2) }} CAD</p>
            <p>Discount: {{ discount.toFixed(2) }} CAD</p>
            <p>
              <b>
                TOTAL: {{ (calculateSum(products) - discount).toFixed(2) }} CAD
              </b>
            </p>
          </div>
        </section>
        <Button
          text="Place Order"
          @btn-click="checkout"
          style="width: 150px, height: 40px;"
        />
      </div>
    </div>
  </div>
</template>

<script>
import Button from "../components/Button.vue";
import CustomInput from "../components/CustomInput.vue";

import axios from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "Checkout",
  components: {
    Button,
    CustomInput
  },
  data() {
    return {
      clerk: false,
      deliveryPerson: false,
      cashier: false,
      owner: false,
      customer: true,
      delivery_option: 0,
      points: 0,
      appliedPoints: 0,
      discount: 0,
      address: "",
      store: {
        name: "",
        address: "",
        phoneNumber: "",
        email: "",
        employeeDiscountRate: "",
        pointToCashRatio: "",
        holidays: null,
        businessHours: null
      },
      customerAccount: { username: "", name: "", pointBalance: "", role: "" },
      customerAddress: { buildingNo: "", street: "", town: "", account: null },
      timeslots: [],
      products: [],
      deliveryTime: null,
      pickupTime: null,
      expirationDate: null,
      cvv: null,
      ccn: null,
      cardHolder: null,
      hours: []
    };
  },
  created: function() {
    this.clerk = localStorage.getItem("role").includes("Clerk");
    this.deliveryPerson = localStorage
      .getItem("role")
      .includes("DeliveryPerson");
    this.cashier = localStorage.getItem("role").includes("Cashier");
    this.owner = localStorage.getItem("role").includes("Owner");
    this.customer = localStorage.getItem("role").includes("Customer");
    this.refresh();
  },
  methods: {
    computeHours(id) {
      const array = [
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
      ];
      var day = new Date(document.getElementById(id).value);

      for (var i = 0; i < this.store.businessHours.length; i++) {
        if (
          array[day.getDay()].localeCompare(
            this.store.businessHours[i].dayOfWeek
          ) == 0
        ) {
          var startTime = this.store.businessHours[i].startTime;
          var endTime = this.store.businessHours[i].endTime;
          var startHour = parseInt(startTime.split(":")[0]);
          var endHour = parseInt(endTime.split(":")[0]);
          this.timeslots = [];
          for (var i = startHour; i < endHour; i++) {
            this.timeslots.push(
              i.toString() + ":00:00 - " + (i + 1).toString() + ":00:00"
            );
          }
          break;
        }
      }
    },
    refresh() {
      var username = localStorage.getItem("token");
      AXIOS.get("/api/cart/cart/" + username)
        .then(response => {
          this.products = response.data.items;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("/api/store/store")
        .then(response => {
          this.store = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("/api/account/".concat(username))
        .then(response => {
          this.customerAccount = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
          return;
        });
      AXIOS.get("/api/address/address/".concat(username))
        .then(response => {
          this.customerAddress = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
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

    updateCart(product, updateType) {
      for (let i = 0; i < this.products.length; i++) {
        if (this.products[i].id === product.id) {
          if (updateType === "subtract") {
            if (this.products[i].quantity !== 0) {
              this.products[i].quantity--;
            }
          } else if (updateType === "add") {
            this.products[i].quantity++;
          } else if (updateType === "remove") {
            this.products.splice(i, 1);
          }

          break;
        }
      }
    },
    applyPoints() {
    
      if (this.customerAccount.pointBalance >= this.points) {
        this.discount += parseInt(this.points) * (1 / this.store.pointToCashRatio);
        if (this.discount <= this.calculateSum(this.products)) {
          this.customerAccount.pointBalance -= parseInt(this.points);
          this.appliedPoints += parseInt(this.points);    
          console.log(this.appliedPoints);
      } else {
          this.discount -= p * (1 / this.store.pointToCashRatio);
          window.alert("Can't apply discount as it is greater than the subtotal")
        }  
      }else{
      window.alert("Not enough points in your balance")}
    },
    updatePayment(newMethod) {
      this.payMethod = newMethod;
    },
    updateDelivery(newMethod) {
      this.delivery_option = newMethod;
    },
    checkout() {
      if (this.cardHolder == null) {
        window.alert("Please insert card holder name");
      } else if (this.ccn == null) {
        window.alert("Please insert credit card number");
      } else if (this.expirationDate == null) {
        window.alert("Please pick an expiration date");
      } else if (this.cvv == null) {
        window.alert("Please enter a cvv");
      }else if( (parseInt(this.calculateSum(this.products)) - parseInt(this.discount)) < 10){
          window.alert("Minimum of 10 dollars must be met for delivery orders");
      } 
      else if (this.$refs.delivery.checked) 
      {
        AXIOS.put(
          "/api/cart/chooseOrderType/" +
            this.customerAccount.username +
            "?orderType=Delivery"
        )
          .then(response => {
            var date = document.getElementById("deliveryDate");
            const timeslot = this.$refs.deliveryTime.children[
              this.$refs.deliveryTime.selectedIndex
            ];
            var startTime = timeslot.textContent.split("-")[0].trim();
            var endTime = timeslot.textContent.split("-")[1].trim();
            AXIOS.put(
              "/api/cart/pickTimeSlot/" +
                this.customerAccount.username +
                "?startDate=" +
                date.value +
                "&endDate=" +
                date.value +
                "&startTime=" +
                startTime +
                "&endTime=" +
                endTime
            )
              .then(response => {
                AXIOS.post(
                  "/api/order/checkout/" +
                    this.customerAccount.username +
                    "?points=" +
                    this.points
                )
                  .then(response => {
                    console.log(response.data);
                  })
                  .catch(e => {
                    window.alert(e.response.data);
                  });
              })
              .catch(e => {
                window.alert(e.response.data);
              });
          })
          .catch(e => {
            window.alert(e.response.data);
          });
      } else if(this.$refs.pickup.checked) {
        AXIOS.put(
          "/api/cart/chooseOrderType/" +
            this.customerAccount.username +
            "?orderType=PickUp"
        )
          .then(response => {
            var date = document.getElementById("pickupDate");
            const timeslot = this.$refs.pickupTime.children[
              this.$refs.pickupTime.selectedIndex
            ];
            var startTime = timeslot.textContent.split("-")[0].trim();
            var endTime = timeslot.textContent.split("-")[1].trim();
            AXIOS.put(
              "/api/cart/pickTimeSlot/" +
                this.customerAccount.username +
                "?startDate=" +
                date.value +
                "&endDate=" +
                date.value +
                "&startTime=" +
                startTime +
                "&endTime=" +
                endTime
            )
              .then(response => {
                AXIOS.post(
                  "/api/order/checkout/" +
                    this.customerAccount.username +
                    "?points=" +
                    this.points
                )
                  .then(response => {
                    console.log(response.data);
                  })
                  .catch(e => {
                    window.alert(e.response.data);
                  });
              })
              .catch(e => {
                window.alert(e.response.data);
              });
          })
          .catch(e => {
            window.alert(e.response.data);
          });
      } else{
        window.alert("please select an order type");
      }
    },
    logout: function() {
      if (confirm("Press OK to logout")) {
        localStorage.removeItem("role");
        localStorage.removeItem("token");
        localStorage.removeItem("pointBalance");
        this.$router.push("/Login");
      }
    }
  }
};
</script>

<style scoped>
.btn {
  margin-top: 10 px;
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

.two {
  background-color: white;
  flex: 1;
}

.one {
  background-color: white;
  flex: 4;
  display: flex;
  height: 30%;
  flex-direction: column;
  gap: 10px;
  border-right: 5px solid rgb(40, 50, 50);
}

.Discount {
  background-color: whitesmoke;
  height: 150px;
  flex: 2;
  padding: 10px;
}

.orderSummary {
  background-color: whitesmoke;
  height: 120px;
  flex: 1;
  padding: 10px;
}

.information {
  background-color: whitesmoke;
  flex: 3;
}

.paymentMethod {
  background-color: whitesmoke;
  flex: 3;
}

.payment_info {
  display: flex;
}
.products {
  display: grid;
  grid-template-columns: 1fr;
  grid-row-gap: 10px;
}

.product {
  display: flex;
  background-color: whitesmoke;
  border-radius: 10px;
  padding: 5px;
  text-align: left;
  /*width: 200px;*/
  /*height: 250px;*/
}

.product {
  display: flex;
  background-color: whitesmoke;
  border-radius: 10px;
  padding: 5px;
  text-align: left;
}

.product_image {
  text-align: center;
  flex: 1;
}

.product_content {
  flex: 9;
  display: flex;
}

.content_value {
  flex: 1;
  line-height: 10px;
}

.content_label {
  flex: 1;
  line-height: 10px;
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

.points {
  font-size: 20px;
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
.personal_info {
  display: flex;
}
.delivery_info {
  display: flex;
}
.infor_label {
  flex: 1;
  text-align: right;
  padding-right: 10px;
}
.info {
  flex: 1;
  text-align: left;
}
</style>
