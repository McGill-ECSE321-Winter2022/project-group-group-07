<template>
  <div class="AccountInfoEmployee">
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
    <h1 style="margin-top:1%;">Account Information</h1>

    <br />

    <div>
      <div>
        <label>UserName: </label>
        <label id="username"> {{ employeeAccount.username }} </label><br />
        <label>Name: </label>
        <label id="name"> {{ employeeAccount.name }} </label>
        <!--button @click="changeName()">Change Name</button>
      <button @click="changePassword()">Change Password</button><br />-->

        <br />

        <label>Address: </label>
        <label id="address">
          {{ employeeAddress.buildingNo }}, {{ employeeAddress.street }},
          {{ employeeAddress.town }}
        </label>
        <!--button @click="changeAddress()">Change Address</button><br />-->

        <br />
        <label>Current Points: </label>
        <label id="points"> {{ employeeAccount.pointBalance }} </label><br />
        <br />

        <br />
      </div>
      <Button
        @btn-click="routeToEdit"
        text="Edit Account Information"
        color="black"
        style="width: auto"
      /><br />

      <br />
    </div>
    <br />
    <label style="font-size: 24px">Schedule</label>
    <table>
      <tr>
        <th>Day</th>
        <th>Start Time</th>
        <th>End Time</th>
      </tr>
      <tr>
        <td>Monday</td>
        <td>{{ MondayH.startTime }}</td>
        <td>{{ MondayH.endTime }}</td>
      </tr>
      <tr>
        <td>Tuesday</td>
        <td>{{ TuesdayH.startTime }}</td>
        <td>{{ TuesdayH.endTime }}</td>
      </tr>
      <tr>
        <td>Wednesday</td>
        <td>{{ WednesdayH.startTime }}</td>
        <td>{{ WednesdayH.endTime }}</td>
      </tr>
      <tr>
        <td>Thursday</td>
        <td>{{ ThursdayH.startTime }}</td>
        <td>{{ ThursdayH.endTime }}</td>
      </tr>
      <tr>
        <td>Friday</td>
        <td>{{ FridayH.startTime }}</td>
        <td>{{ FridayH.endTime }}</td>
      </tr>
      <tr>
        <td>Saturday</td>
        <td>{{ SaturdayH.startTime }}</td>
        <td>{{ SaturdayH.endTime }}</td>
      </tr>
      <tr>
        <td>Sunday</td>
        <td>{{ SundayH.startTime }}</td>
        <td>{{ SundayH.endTime }}</td>
      </tr>
    </table>
  </div>
</template>
<script>
import Button from "../components/Button.vue";
import axios from "axios";
var config = require("../../config");


var frontendUrl = "https://" + config.build.host + ":" + config.build.port;
var backendUrl =
  "https://" + config.build.backendHost + ":" + config.build.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});
export default {
  name: "AccountInfoEmployee",
  components: {
    Button
  },
  data() {
    return {
      clerk: false,
      deliveryPerson: false,
      cashier: false,
      owner: false,
      customer: true,
      MondayH: { startTime: "", endTime: "" },
      TuesdayH: { startTime: "", endTime: "" },
      WednesdayH: { startTime: "", endTime: "" },
      ThursdayH: { startTime: "", endTime: "" },
      FridayH: { startTime: "", endTime: "" },
      SaturdayH: { startTime: "", endTime: "" },
      SundayH: { startTime: "", endTime: "" },
      employeeAddress: { buildingNo: "", street: "", town: "", account: null },
      employeeAccount: { username: "", name: "", pointBalance: "", role: "" }
    };
  },
  created: function() {
    this.refreshEmployeeSchedule();
    var username = localStorage.getItem("token");
    this.clerk = localStorage.getItem("role").includes("Clerk");
    this.deliveryPerson = localStorage
      .getItem("role")
      .includes("DeliveryPerson");
    this.cashier = localStorage.getItem("role").includes("Cashier");
    this.owner = localStorage.getItem("role").includes("Owner");
    this.customer = localStorage.getItem("role").includes("Customer");
    AXIOS.get("/api/account/".concat(username))
      .then(response => {
        this.employeeAccount = response.data;
        console.log(response.data);
      })
      .catch(e => {
        window.alert(e.response.data);
        return;
      }),
      AXIOS.get("/api/address/address/".concat(username))
        .then(response => {
          this.employeeAddress = response.data;
        })
        .catch(e => {
          var errorMsg = e.response.data;
          console.log(errorMsg);
        });
  },

  methods: {
    refreshEmployeeSchedule() {
      var username = localStorage.getItem("token");
      AXIOS.get("api/schedule/workingHour/" + username + "?dayOfWeek=Monday")
        .then(response => {
          this.MondayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/schedule/workingHour/" + username + "?dayOfWeek=Tuesday")
        .then(response => {
          this.TuesdayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/schedule/workingHour/" + username + "?dayOfWeek=Wednesday")
        .then(response => {
          this.WednesdayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/schedule/workingHour/" + username + "?dayOfWeek=Thursday")
        .then(response => {
          this.ThursdayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/schedule/workingHour/" + username + "?dayOfWeek=Friday")
        .then(response => {
          this.FridayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/schedule/workingHour/" + username + "?dayOfWeek=Saturday")
        .then(response => {
          this.SaturdayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/schedule/workingHour/" + username + "?dayOfWeek=Sunday")
        .then(response => {
          this.SundayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      return;
    },
    logout: function() {
      if (confirm("Press OK to logout")) {
        localStorage.removeItem("role");
        localStorage.removeItem("token");
        localStorage.removeItem("pointBalance");
        this.$router.push("/Login");
      }
    },
    routeToEdit() {
      this.$router.push("/EditProfile");
    }
  }
};
</script>

<style scoped>
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
table {
  margin-left: 2em;
  width: 90%;
  border-collapse: collapse;
}
th,
td {
  border: 1px solid black;
  padding: 5px;
  text-align: left;
}
</style>
