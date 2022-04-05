<template>
  <div class="report" overflow="auto" style="background-color:white; height:100%; position:fixed; width:100%;">
  <div class = "navbar">
  <label>AppName</label>
  <div>
  <button>Button 1</button> 
  <button>Button 2</button>
  <button>Button 3</button>
  </div>
  <div><button>Button 4</button></div>
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
  <button @click="genReport()" style="margin-top:1%;">Generate Report</button>
  <div>
      <section class="report" v-if="reportID">
                    <label>ReportID: {{ reportID }}</label>
                    <label>ReportID: {{ total }}</label>
                    <label>ReportID: {{ reportID }}</label>
                    <div v-for="order in orders" :key="order.id" class="product">
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
    components: {
            Order
        },
    data() {
        return {
            start:null,
            end:null,
            reportID: null,
            reportTotalValue: null,
            orders: [],
        };
    },
    created: function () {
    },
    methods: {
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
                        break;
                    }
                }
                if (!b) {
                    AXIOS.post("/api/report/report?startDate=" + this.start + "&endDate=" + this.end)
                    .then(response => {
                        this.reportID = response.data.reportID;
                        this.reportTotalValue = response.data.totalValue;
                        this.orders = response.data.orders;
                    })
                    .catch(e => {
                    window.alert("Network Error.");
                    return;
                    });
                }
                else {
                    //show report
                    this.reportID = report.reportID;
                    this.reportTotalValue = report.totalValue;
                    this.orders = report.orders;
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
</style>
