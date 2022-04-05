<template>
  <div class="checkout" id="checkout">
    <p>{{ username }}</p>
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

    <div class="column">
      <div class="one">
        <h2 class="checkout_label">Checkout</h2>
        <div class="Discount">
          <h3>Discount points</h3>
          <p class="points">
            <b>POINTS AVAILABLE:</b> <b style="color: red">{{ points }}</b>
          </p>
          <span class="coupon"></span>
          <input
            type="text"
            required
            id="points"
            name="points"
            placeholder="Enter amount of points"
          />
          <button class="checkout_buttons" type="button" @click="applyPoints()">
            Apply
          </button>
        </div>
        <div class="information">
          <h2>Order information</h2>
          <div class="personal_info">
            <div class="infor_label">
              <b>First Name</b><br /><br />
              <b>Last Name</b><br /><br />
              <b>Phone Number</b><br /><br />
            </div>
            <div class="info">
              <input
                type="text"
                required
                id="firstname"
                name="firstname"
                :value="prevInfo.FirstName"
                placeholder="First Name"
              /><br /><br />
              <input
                type="text"
                required
                id="lasttname"
                name="lasttname"
                :value="prevInfo.LastName"
                placeholder="Last Name"
              /><br /><br />
              <input
                type="tel"
                required
                id="phone"
                name="phone"
                :value="prevInfo.phoneNumber"
                placeholder="Phone Number"
              /><br /><br />
            </div>
          </div>

          <input
            type="radio"
            id="delivery"
            name="payment"
            @click="updateDelivery(1)"
          />
          <label for="delivery">Delivery</label><br />
          <div class="delivery_info" v-show="delivery_option === 1">
            <div class="infor_label">
              <b>Street Address</b><br /><br />
              <b>Building number</b><br /><br />
              <br /><br />
              <b>Delivery date</b><br /><br />
              <b>Delivery time slot</b><br /><br />
            </div>
            <div class="info">
              <input
                type="text"
                required
                id="address"
                name="address"
                :value="prevInfo.address"
                placeholder="Street Address"
              /><br /><br />
              <input
                type="text"
                required
                id="building_number"
                name="building_number"
                :value="prevInfo.build_number"
                placeholder="Building number"
              /><br /><br />
              <p style="color: darkgreen"><b>Choose delivery date</b></p>
              <input
                type="date"
                name="pickup_date"
                min="2021-04-01"
                max="2021-04-30"
              /><br /><br />
              <select
                class="timeslots_delivery"
                name="timeslots_delivery"
                v-if="timeslots_delivery.length > 0"
              >
                <option
                  v-for="timeslot in timeslots_delivery"
                  :key="timeslot.startDate"
                  class="timeslot"
                >
                  <div>
                    {{ "Time:" + timeslot.startTime + "-" + timeslot.endTime }}
                  </div>
                </option>
              </select>
            </div>
          </div>

          <input
            type="radio"
            id="pickup"
            name="payment"
            @click="updateDelivery(2)"
          />
          <label for="pickup">PickUp</label><br />
          <div class="delivery_info" v-show="delivery_option === 2">
            <div class="infor_label">
              <b>Store Address</b><br /><br />
              <br /><br />
              <b>Delivery date</b><br /><br />
              <b>Delivery time slot</b><br /><br />
            </div>
            <div class="info">
              {{ store_address }}<br /><br />
              <b style="color: darkgreen">Choose pickup date</b><br /><br />
              <input
                type="date"
                name="pickup_date"
                min="2021-04-01"
                max="2021-04-30"
              /><br /><br />
              <select
                class="timeslots"
                name="timeslot"
                v-if="timeslots.length > 0"
              >
                <option
                  v-for="timeslot in timeslots"
                  :key="timeslot.startDate"
                  class="timeslot"
                >
                  <div>
                    {{ "Time:" + timeslot.startTime + "-" + timeslot.endTime }}
                  </div>
                </option>
              </select>
            </div>
          </div>

          <button type="submit" class="checkout_buttons" onclick="doThing">
            Save
          </button>
        </div>

        <form class="paymentMethod">
          <h2>Payment method</h2>
          <input
            type="radio"
            id="Previous"
            name="payment"
            @click="updatePayment(1)"
          />
          <label for="Previous">Previous Payment Method</label><br />
          <div class="payment_info" v-show="payMethod === 1">
            <div class="infor_label">
              <label> CardHolder Name </label><br /><br />
              <label> Credit Card number </label><br /><br />
              <label> Expiration Date </label><br /><br />
              <label> CVV </label><br /><br />
            </div>

            <div class="info">
              <label>{{ prev.name }}</label
              ><br /><br />
              <label>{{ prev.credit }}</label
              ><br /><br />
              <label>{{ prev.expiration }}</label
              ><br /><br />
              <input
                type="password"
                style="width: 4em"
                name="pincode"
                maxlength="3"
                pattern="[0-9]"
                required
              /><br /><br />
            </div>
          </div>

          <input
            type="radio"
            id="CreditCard"
            name="payment"
            @click="updatePayment(2)"
          />
          <label for="CreditCard">Credit Card</label><br />
          <div class="payment_info" v-show="payMethod === 2">
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
              /><br /><br />
              <input
                id="ccn"
                type="tel"
                inputmode="numeric"
                pattern="[0-9\s]{13,19}"
                autocomplete="cc-number"
                maxlength="19"
              /><br /><br />
              <input type="month" id="Expiration Date" name="Expiration Date" />
              <br /><br />
              <input
                type="password"
                style="width: 4em"
                name="pincode"
                maxlength="3"
                pattern="[0-9]"
                required
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
                :src="product.image"
                :alt="product.name"
                class="product_image"
                style="width: 100px; min-width: 100px;"
              />
            </div>
            <div class="product_content">
              <div class="content_label">
                <p><b>Name</b></p>
                <p><b>Qty</b></p>
                <p><b>Unit Price</b></p>
                <p><b>Sub Total</b></p>
              </div>
              <div class="content_value">
                <p>{{ product.name }}</p>
                <p>{{ product.quantity }}</p>
                <p>{{ product.price }}</p>
                <p>{{ product.price * product.quantity }}</p>
              </div>
            </div>
          </div>
          <div class="orderSummary">
            <p>Subtotal: {{ calculateSum(products) }} CAD</p>
            <p>Discount: {{ discount.toFixed(2) }} CAD</p>
            <p>
              <b>
                TOTAL: {{ (calculateSum(products) - discount).toFixed(2) }} CAD
              </b>
            </p>
          </div>
        </section>
        <button class="checkout_buttons" type="button" @click="applyPoints()">
          Place Order
        </button>
      </div>
    </div>
  </div>
</template>

<script>
// import Button from './Button'
export default {
  name: "hello",
  props: ["username"],
  created: function () {
     this.clerk = localStorage.getItem("role").includes("Clerk");
    this.deliveryPerson = localStorage.getItem("role").includes("DeliveryPerson") ;
    this.cashier = localStorage.getItem("role").includes("Cashier") ;
    this.owner = localStorage.getItem("role").includes("Owner") ;
    this.customer = localStorage.getItem("role").includes("Customer") ;
    },
  data() {
    return {
      clerk: false,
      deliveryPerson: false,
      cashier: false,
      owner:false,
      customer: true,
      payMethod: 0,
      delivery_option: 0,
      points: 10000,
      discount: 0,
      store_address: "McgiLL street 123",
      timeslots: [
        {
          startDate: "2022-04-01",
          endDate: "2022-04-01",
          startTime: "11:00",
          endTime: "13:00"
        },
        {
          startDate: "2022-04-01",
          endDate: "2022-04-01",
          startTime: "14:00",
          endTime: "15:00"
        },
        {
          startDate: "2022-04-01",
          endDate: "2022-04-01",
          startTime: "16:00",
          endTime: "20:00"
        }
      ],
      timeslots_delivery: [
        {
          startDate: "2022-04-01",
          endDate: "2022-04-01",
          startTime: "11:00",
          endTime: "13:00"
        },
        {
          startDate: "2022-04-01",
          endDate: "2022-04-01",
          startTime: "14:00",
          endTime: "15:00"
        },
        {
          startDate: "2022-04-01",
          endDate: "2022-04-01",
          startTime: "16:00",
          endTime: "20:00"
        }
      ],
      products: [
        {
          id: 1,
          name: "Product 1",
          description: "This is an incredibly awesome product",
          quantity: 1,
          price: 100,
          inStock: true,
          online: false,
          inventory: 7,
          image: "https://via.placeholder.com/150"
        },
        {
          id: 2,
          name: "Product 2",
          description: "This is an incredibly awesome product",
          quantity: 1,
          price: 10,
          inStock: true,
          online: false,
          inventory: 7,
          image: "https://via.placeholder.com/150"
        },
        {
          id: 3,
          name: "Product 3",
          description: "This is an incredibly awesome product",
          quantity: 1,
          price: 50,
          inStock: true,
          online: false,
          inventory: 7,
          image: "https://via.placeholder.com/150"
        }
      ],
      prev: {
        name: "Ivan Ivanovich",
        credit: "************4321",
        expiration: "11/2035"
      },
      prevInfo: {
        FirstName: "Ivan",
        LastName: "Ivanovich",
        phoneNumber: "+111111111",
        delivery_option: 1,
        address: "somthing street",
        build_number: 11
      }
    };
  },
  methods: {
    calculateSum: function(products) {
      var sum = 0;
      for (var i = 0; i < products.length; i++) {
        sum += products[i].price * products[i].quantity;
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
      let dis = document.getElementById("points").value;
      if (this.points >= dis) {
        this.discount += dis * 0.01;
        this.points -= dis;
      }
    },
    updatePayment(newMethod) {
      this.payMethod = newMethod;
    },
    updateDelivery(newMethod) {
      this.delivery_option = newMethod;
    },
    logout: function(){
            if (confirm("Press OK to logout")) {
                localStorage.removeItem('role');
                localStorage.removeItem('token');
                this.$router.push('/Login');

            }
        },
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
