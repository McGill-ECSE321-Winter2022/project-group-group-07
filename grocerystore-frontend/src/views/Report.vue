<template>
  <div class="Report" overflow="auto" style="background-color:white; height:100%; position:fixed; width:100%;">
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
        
        <button
          v-if="owner"
          onclick="location.href = '/#/Report';"
        >
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
  <h1 style="margin-top:1%;">Generate Report</h1>
  <div style="display:inline-flex; margin-top:1%; padding: 2%; width: 70%;">
  <div style="display:table; padding: 5%; width:50%;">
  <label>From Start Date:</label> <br>
  <input v-model="start" type="date">
  </div>
  <div style="display:table; padding: 5%; width:50%;">
  <label>To End Date:</label> <br>
  <input v-model="end" type="date">
  </div>
  </div>
  <div>
  <Button @btn-click="genReport()"  style="margin-top:1%;" text="Generate Report" color="black" />
  <div>
      <section style="background-color:whitesmoke;" class="reportdisplay" v-if="reportID">
                    <label>ReportID: {{ reportID }}</label>
                    <label>Start Date: {{ start }}</label>
                    <label>End Date: {{ end }}</label>
                    <label>Total Value: {{ reportTotalValue }}</label>
                    <div v-for="order in orders" :key="order.orderID" class="products">
                        <Order
                            :order="order"
                        />
                    </div>
        </section>
  </div>
  </div>
  </div>
  </template>
</template>

<script>
import Order from "../components/Order.vue";

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
    name: "Report",
    components:{
        Order,
        Button
    },
    data() {
        return {clerk: false,
          deliveryPerson: false,
          cashier: false,
          owner: false,
           customer: true,
            start:null,
            end:null,
            reportID: null,
            reportTotalValue: null,
            orders: [],
        };
    },
    created: function () {
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
    },
    methods: { 
         logout: function() {
          if (confirm("Press OK to logout")) {
               localStorage.removeItem("role");
                localStorage.removeItem("token");
                localStorage.removeItem("pointBalance")
             this.$router.push("/Login");
           }
          },
        genReport: function () {
            if(!this.start){
                window.alert("No start date was entered for the Report.")
                return;
            }
            else if(!this.end){
                window.alert("No end date was entered for the Report.")
                return;
            }
            else{
            AXIOS.get("/api/report/reports")
            .then(response => {
                var b = false;
                var report;
                for (var i = 0; i < response.data.length; i++) {
                    if (response.data[i].startDate.localeCompare(this.start) == 0 && response.data[i].endDate.localeCompare(this.end) == 0) {
                        b = true;
                        report = response.data[i];
                                            this.reportID = report.reportID;
                    this.reportTotalValue = report.totalValue;
                    this.orders = report.orders;
                        break;
                    }
                }
                if (!b) {
                    AXIOS.post("/api/report/report?startDate=" + this.start + "&endDate=" + this.end)
                    .then(response => {
                        this.reportID = response.data.reportID;
                        this.reportTotalValue = response.data.totalValue;
                        this.orders = response.data.orders;
                        console.log(this.orders);
                    })
                    .catch(e => {
                    window.alert("Network Error.");
                    return;
                    });
                }
            })
            .catch(e => {
                window.alert("Network Error.");
                return;
            });
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
.reportdisplay {
  display: grid;
  grid-template-columns: 1fr;
  grid-row-gap: 10px;
}
.reportdisplay label {
    margin-top: 2%;
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
