<template>
  <div class="AccountInfoEmployee">
    <div class="navbar">
      <label>AppName</label>
      <div>
        <button v-if="variable1" onclick="location.href = '/#/PickUp';">
          Pickup Orders
        </button>
        <button v-if="variable" onclick="location.href = '/#/Delivery';">
          Delivery Orders
        </button>
        <button onclick="location.href = '/#/AccountInfoEmployee';">
          Account Information
        </button>
      </div>
      <div><button>Logout</button></div>
    </div>
    <h1 style="margin-top:1%;">Account Information</h1>

    <br />

    <div align="left">
      <a href="/#/EditProfile"><button>Edit Account Informations</button></a
      ><br />

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
  name: "PickUp",
  data() {
    return {
      variable: true,
      variable1: false,
      MondayH: { startTime: "", endTime: "" },
      TuesdayH: { startTime: "", endTime: "" },
      WednesdayH: { startTime: "", endTime: "" },
      ThursdayH: { startTime: "", endTime: "" },
      FridayH: { startTime: "", endTime: "" },
      SaturdayH: { startTime: "", endTime: "" },
      SundayH: { startTime: "", endTime: "" }
    };
  },

  created: function() {
    this.refreshEmployeeSchedule();
    this.variable = localStorage.getItem("role").localeCompare("Clerk") == 0;
    this.variable1 =
      localStorage.getItem("role").localeCompare("DeliveryPerson") == 0;
  },

  methods: {
     refreshEmployeeSchedule() {
       var username = localStorage.getItem('token');
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
        AXIOS.get(
          "api/schedule/workingHour/" + username + "?dayOfWeek=Wednesday"
        )
          .then(response => {
            this.WednesdayH = response.data;
          })
          .catch(e => {
            window.alert(e.response.data);
          });
        AXIOS.get(
          "api/schedule/workingHour/" + username + "?dayOfWeek=Thursday"
        )
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
        AXIOS.get(
          "api/schedule/workingHour/" + username + "?dayOfWeek=Saturday"
        )
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
      }
    },
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
