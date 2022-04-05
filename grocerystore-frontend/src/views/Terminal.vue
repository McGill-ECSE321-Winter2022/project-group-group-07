<template>
  <div class="terminal" style="background-color: grey; height:100%; position:fixed; width:100%;">
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
  <div class="modal" ref="modal">
    <div class="payment_info">
            <div style="margin-top:10%;" class="infor_label">
              <label> CardHolder Name </label><br><br>
              <label> Credit Card number </label><br><br>
              <label> Expiration Date </label><br><br>
              <label> CVV </label><br><br>
            </div>

            <div style="margin-top:10%;" class="info">
              <input v-model="cardholder" type="text" name="cardHolder"><br><br>
              <input v-model="ccn" type="tel" inputmode="numeric" pattern="[0-9\s]{13,19}" autocomplete="cc-number"
                     maxlength="19"><br><br>
              <input v-model="exp" type="month" name="Expiration Date"> <br><br>
              <input v-model="cvv" type="password" style="width: 4em" name="pincode" maxlength="3" pattern="[0-9]" required/><br><br>
            </div>
      </div>
      <div>
        <button style="margin-left:3%; font-size:large;" @click="cancelpay()">Cancel</button>
        <button style="margin-left:3%; font-size:large;" @click="pay()">Proceed</button>
      </div>
  </div>
  
  <h1 style="margin-top:1%; background-color:whitesmoke; border-style:solid;">Cashier Terminal</h1>
  <div style="display:inline-flex; margin-top:1%; padding: 2%; width: 80%; background-color:whitesmoke;">
  <div style="display:table; padding: 5%; width:50%;">
  <label>Item ID:</label>
  <input v-model="id" type="number" min="0" style="width:100%; text-align: center;">
  <button @click="additem()" style="margin-top:1%;">Add Item</button>
  <p></p>
  <p></p>
  <label>Username (Optional):</label>
  <input v-model="user" style="width:100%; text-align: center;">
  </div>
  <div style="display:table; padding: 5%; width:50%;">
  <label>Item Summary</label>
  <select ref="selecter" v-model="sel" overflow="scroll" size="10" style="width:100%; text-align: center;"></select>
  <button @click="removeitem()">Remove Item</button>
  <p></p>
  <label style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: large;
    font-weight: bold;">Total Price: {{ total }} CAD</label>
  </div>
  </div>
  <div>
  <button @click="topayscreen()" style="margin-top:1%;">Submit to Payment</button>
  </div>
  </div>
  </template>
</template>

<script>
import axios from 'axios'
import { ListGroupPlugin } from 'bootstrap-vue'
import Button from '../components/Button.vue'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
export default {
    name: "Terminal",
    data() {
        return {
          clerk: false,
          deliveryPerson: false,
          cashier: false,
          owner:false,
          customer: true,
            id: null,
            item: null,
            user: null,
            sel: null,
            cardholder: null,
            user2: null,
            cvv: null,
            ccn: null,
            exp: null,
            total: 0
        };
    },
    created: function () {
      this.clerk = localStorage.getItem("role").includes("Clerk");
    this.deliveryPerson = localStorage.getItem("role").includes("DeliveryPerson") ;
    this.cashier = localStorage.getItem("role").includes("Cashier") ;
    this.owner = localStorage.getItem("role").includes("Owner") ;
    this.customer = localStorage.getItem("role").includes("Customer") ;
    },
    methods: {
        additem: function () {
            var itm;
            const lst = this.$refs.selecter;
            AXIOS.get("/api/item/items/id:" + this.id)
                .then(response => {
                itm = response.data; //If successful, store response 
                this.total += itm.price;
                const opt = document.createElement("option");
                opt.textContent = itm.productName + ", " + itm.itemID + ", " + itm.price + " CAD";
                lst.appendChild(opt);
            })
                .catch((e) => {
                window.alert(e.response.data); //If not successful, print error response text.
                return;
            });
        },
        removeitem: function () {
            const inf = this.$refs.selecter;
            const opt = inf.children[inf.selectedIndex];
            var str = opt.textContent.split(" ");
            var price = str[str.length - 2];
            var prc = parseFloat(price);
            this.total -= prc;
            opt.remove();
        },
        topayscreen: function () {
            var accounts = [];
            if (this.total <= 0) {
                window.alert("No items have been added to this order.");
                return;
            }
            if (this.user) {
                AXIOS.get("/api/account/accounts")
                    .then(response => {
                    var b = false;
                    for (var i = 0; i < response.data.length; i++) {
                        if (response.data[i].username.localeCompare(this.user) == 0) {
                            b = true;
                            break;
                        }
                    }
                    if (!b) {
                        window.alert("There is no user with that username.");
                        return;
                    }
                    else {
                        this.item = '';
                        this.user2 = this.user;
                        for(var i = 0; i < this.$refs.selecter.children.length; i++){
                         this.item += ("&items=" + this.$refs.selecter.children[i].textContent.split(",")[1].trim());
                        }
                        this.$refs.modal.style.display = "block";
                    }
                })
                    .catch(e => {
                    window.alert("Network Error.");
                    return;
                });
            }
            else {
                    this.item = '';
                    this.user2 = this.user;
                        for(var i = 0; i < this.$refs.selecter.children.length; i++){
                         this.item += ("&items=" + this.$refs.selecter.children[i].textContent.split(",")[1].trim());
                    }
                this.$refs.modal.style.display = "block";
            }
        },
        cancelpay: function () {
            this.$refs.modal.style.display = "none";
        },
        pay: function () {
            if(!this.cardholder){
              window.alert("Please enter cardholder name.");
              return;
            }
            if(!this.ccn){
              window.alert("Please enter card number.");
              return;
            }
            if(!this.exp){
              window.alert("Please enter expiry date.");
              return;
            }
            if(!this.cvv){
              window.alert("Please enter card pin code.");
              return;
            }
            var currentdate = new Date();
            var datestr =  currentdate.toISOString().split("T")[0];
            var timestr = currentdate.toISOString().split("T")[1].split(".")[0];
            var str = "?date=" + datestr + "&purchaseTime=" + timestr;
            str += this.item;
            if(!this.user2){
              
              AXIOS.post('/api/order/createInStoreOrder' + str)
              .then(response => {
               })
            .catch(e => {
              window.alert("Error Processing Transaction.");
              return;
              })
            }
           else{
             AXIOS.post('/api/order/createInStoreOrder/' + this.user2 + str)
              .then(response => {
               })
            .catch(e => {
              window.alert("Error Processing Transaction.");
              return;
              })
           } 
            window.alert("Transaction Successful!");
            this.$refs.modal.style.display = "none";
            this.user2 = null;
        },
        logout: function(){
            if (confirm("Press OK to logout")) {
                localStorage.removeItem('role');
                localStorage.removeItem('token');
                this.$router.push('/Login');

            }
        },
    },
    components: { Button }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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
.modal{
  background-color: whitesmoke;
   height:100%;
    position:fixed;
    display: none;
    z-index: 1;
     width:100%;
}
.payment_info {
  display: flex;
}
.infor_label{
  flex: 1;
  text-align: right;
  padding-right: 10px;
}
.info{
  flex: 1;
  text-align: left;
}
</style>
