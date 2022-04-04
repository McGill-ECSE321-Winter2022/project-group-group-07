<template>
  <div>
    <div class="navbar">
      <label>AppName</label>
      <div>
        <button>Button 1</button>
        <button>Button 2</button>
        <button>Button 3</button>
      </div>
      <div><button>Button 4</button></div>
    </div>
    <div class="column1">
      <label style="font-size: 24px">Store Info</label>
      <label>Address: {{ Store.address }} </label>
      <label>Email: {{ Store.email }} </label>
      <label>PhoneNumber: {{ Store.phoneNumber }} </label>
      <label>EmployeeDiscountRate: {{ Store.employeeDiscountRate }} </label>
      <label>PointToCashRatio: {{ Store.pointToCashRatio }} </label>
      <label style="font-size: 24px">Business Hours</label>
      <table>
        <tr>
          <th>Day</th>
          <th>Start Time</th>
          <th>End Time</th>
        </tr>
        <tr>
          <td>Monday</td>
          <td>{{ MondayBH.startTime }}</td>
          <td>{{ MondayBH.endTime }}</td>
        </tr>
        <tr>
          <td>Tuesday</td>
          <td>{{ TuesdayBH.startTime }}</td>
          <td>{{ TuesdayBH.endTime }}</td>
        </tr>
        <tr>
          <td>Wednesday</td>
          <td>{{ WednesdayBH.startTime }}</td>
          <td>{{ WednesdayBH.endTime }}</td>
        </tr>
        <tr>
          <td>Thursday</td>
          <td>{{ ThursdayBH.startTime }}</td>
          <td>{{ ThursdayBH.endTime }}</td>
        </tr>
        <tr>
          <td>Friday</td>
          <td>{{ FridayBH.startTime }}</td>
          <td>{{ FridayBH.endTime }}</td>
        </tr>
        <tr>
          <td>Saturday</td>
          <td>{{ SaturdayBH.startTime }}</td>
          <td>{{ SaturdayBH.endTime }}</td>
        </tr>
        <tr>
          <td>Sunday</td>
          <td>{{ SundayBH.startTime }}</td>
          <td>{{ SundayBH.endTime }}</td>
        </tr>
      </table>
    </div>
    <div class="vl"></div>
    <form>
      <div class="column2" style="margin-top:4em;">
        <CustomInput
          label="Address"
          type="text"
          :value="addressValue"
          @change="v => (addressValue = v)"
        />
        <CustomInput
          label="Email"
          type="email"
          placeholder="example@email.com"
          :value="emailValue"
          @change="v => (emailValue = v)"
        />
        <CustomInput
          label="Phone Number"
          type="tel"
          pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"
          placeholder="555-555-5555"
          :value="phoneNumberValue"
          @change="v => (phoneNumberValue = v)"
        />
        <CustomInput
          label="Employee Discount Rate"
          type="number"
          :value="employeeDiscountRateValue"
          @change="v => (employeeDiscountRateValue = v)"
        />
        <CustomInput
          label="PointToCash Ratio"
          step="0.01"
          type="number"
          :value="pointToCashRatioValue"
          @change="v => (pointToCashRatioValue = v)"
        />
      </div>
      <div class="column2">
        <Button text="Set Up" color="black" @btn-click="setupStoreInfo" />
        <Button text="Update" color="black" @btn-click="updateStoreInfo" />
      </div>
    </form>
    <form>
      <div class="column2">
        <div style="margin-top:5em;">
          <div><label>Day</label></div>
          <select name="days" id="days" style="width:200px; height:30px;">
            <option value="Monday">Monday</option>
            <option value="Tuesday">Tuesday</option>
            <option value="Wednesday">Wednesday</option>
            <option value="Thursday">Thursday</option>
            <option value="Friday">Friday</option>
            <option value="Saturday">Saturday</option>
            <option value="Sunday">Sunday</option>
          </select>
        </div>
      </div>
      <div class="column2">
        <CustomInput
          label="Start Time"
          type="time"
          :value="startTime"
          @change="v => (startTime = v)"
          style="font-size: 20px"
        />
        <CustomInput
          label="End Time"
          type="time"
          :value="endTime"
          @change="v => (endTime = v)"
          style="font-size: 20px;"
        />
      </div>
      <div class="column2">
        <Button text="Add" color="black" @btn-click="addBusinessHour" />
        <Button text="Update" color="black" @btn-click="updateBusinessHour" />
      </div>
    </form>
  </div>
</template>

<script>
import CustomInput from "../components/CustomInput.vue";
import Button from "../components/Button.vue";
import axios from "axios";
import { ListGroupPlugin } from "bootstrap-vue";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "StoreInfo",
  components: {
    CustomInput,
    Button
  },
  data() {
    return {
      Store: {
        name: "",
        address: "",
        phoneNumber: "",
        email: "",
        employeeDiscountRate: null,
        pointToCashRatio: null,
        holidays: null,
        businessHours: null
      },
      addressValue: "",
      emailValue: "",
      phoneNumberValue: null,
      employeeDiscountRateValue: null,
      pointToCashRatioValue: null,
      startTime: null,
      endTime: null,
      MondayBH: { startTime: "", endTime: "" },
      TuesdayBH: { startTime: "", endTime: "" },
      WednesdayBH: { startTime: "", endTime: "" },
      ThursdayBH: { startTime: "", endTime: "" },
      FridayBH: { startTime: "", endTime: "" },
      SaturdayBH: { startTime: "", endTime: "" },
      SundayBH: { startTime: "", endTime: "" }
    };
  },
  created: function() {
    this.refreshStoreInfo();
    this.refreshSchedule();
  },
  methods: {
    setupStoreInfo() {
      AXIOS.post(
        "api/store/setStoreInfo?" +
          "name=Bake N'Bread" +
          "&address=" +
          this.addressValue +
          "&phoneNumber=" +
          this.phoneNumberValue +
          "&email=" +
          this.emailValue +
          "&employeeDiscountRate=" +
          this.employeeDiscountRateValue +
          "&pointToCashRatio=" +
          this.pointToCashRatioValue
      )
        .then(response => {
          this.refreshStoreInfo();
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    },
    updateStoreInfo() {
      AXIOS.put(
        "api/store/updateStore?" +
          "name=Bake N'Bread" +
          "&address=" +
          this.addressValue +
          "&phoneNumber=" +
          this.phoneNumberValue +
          "&email=" +
          this.emailValue +
          "&employeeDiscountRate=" +
          this.employeeDiscountRateValue +
          "&pointToCashRatio=" +
          this.pointToCashRatioValue
      )
        .then(response => {
          this.refreshStoreInfo();
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    },
    addBusinessHour() {
      var select = document.getElementById("days");
      var day = select.options[select.selectedIndex].value;
      AXIOS.post(
        "api/store/businessHours?" +
          "dayOfWeek=" +
          day +
          "&startTime=" +
          this.startTime +
          "&endTime=" +
          this.endTime
      )
        .then(response => {
          this.refreshSchedule();
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    },
    updateBusinessHour: function() {
      var select = document.getElementById("days");
      var day = select.options[select.selectedIndex].value;
      AXIOS.put(
        "api/store/updateBusinessHour/" +
          day +
          "?startTime=" +
          this.startTime +
          "&endTime=" +
          this.endTime
      )
        .then(response => {
          this.refreshSchedule();
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    },
    refreshSchedule: function() {
      AXIOS.get("api/store/businessHours/Monday")
        .then(response => {
          this.MondayBH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Tuesday")
        .then(response => {
          this.TuesdayBH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Wednesday")
        .then(response => {
          this.WednesdayBH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Thursday")
        .then(response => {
          this.ThursdayBH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Friday")
        .then(response => {
          this.FridayBH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Saturday")
        .then(response => {
          this.SaturdayBH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Sunday")
        .then(response => {
          this.SundayBH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
        return;
    },
    refreshStoreInfo() {
      AXIOS.get("api/store/store")
        .then(response => {
          this.Store = response.data;
        })
        .catch(e => {
          return;
        });
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

.column1 {
  float: left;
  width: 50%;
  padding: 10px;
  text-align: left;
}
.column2 {
  float: left;
  width: 50%;
  padding: 10px;
  text-align: left;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-row-gap: 20px;
}

.column1 label {
  margin: 30px;
  display: block;
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
.vl {
  border-left: 2px solid black;
  height: 50rem;
  position: absolute;
  left: 50%;
  margin-top: 52px;
  top: 0;
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
