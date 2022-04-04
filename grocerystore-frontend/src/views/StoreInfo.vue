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
          <td>{{ MondayST }}</td>
          <td>{{ MondayET }}</td>
        </tr>
        <tr>
          <td>Tuesday</td>
          <td>{{ TuesdayST }}</td>
          <td>{{ TuesdayET }}</td>
        </tr>
        <tr>
          <td>Wednesday</td>
          <td>{{ WednesdayST }}</td>
          <td>{{ WednesdayET }}</td>
        </tr>
        <tr>
          <td>Thursday</td>
          <td>{{ ThursdayST }}</td>
          <td>{{ ThursdayET }}</td>
        </tr>
        <tr>
          <td>Friday</td>
          <td>{{ FridayST }}</td>
          <td>{{ FridayET }}</td>
        </tr>
        <tr>
          <td>Saturday</td>
          <td>{{ SaturdayST }}</td>
          <td>{{ SaturdayET }}</td>
        </tr>
        <tr>
          <td>Sunday</td>
          <td>{{ SundayST }}</td>
          <td>{{ SundayET }}</td>
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
          type="text"
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
          :value="EmployeeDiscountRateValue"
          @change="v => (EmployeeDiscountRateValue = v)"
        />
        <CustomInput
          label="PointToCash Ratio"
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
        <Button text="Add" color="black" />
        <Button text="Update" color="black" />
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
      EmployeeDiscountRateValue: null,
      pointToCashRatioValue: null,
      startTime: null,
      endTime: null,
      MondayST: "",
      MondayET: "",
      TuesdayST: "",
      TuesdayET: "",
      WednesdayST: "",
      WednesdayET: "",
      ThursdayST: "",
      ThursdayET: "",
      FridayST: "",
      FridayET: "",
      SaturdayST: "",
      SaturdayET: "",
      SundayST: "",
      SundayET: ""
    };
  },
  created: function() {
    AXIOS.get("api/store/store")
      .then(response => {
        this.Store = response.data;
      })
      .catch(e => {
        return;
      });
  },
  methods: {
    setupStoreInfo() {
      AXIOS.post(
        "api/store/setStoreInfo?" +
          "name=Bake N'Bread" +
          "address=" +
          this.addressValue +
          "phoneNumber=" +
          this.phoneNumberValue +
          "email=" +
          this.emailValue +
          "employeeDiscountRate=" +
          this.employeeDiscountRate +
          "pointToCashRatio=" +
          this.pointToCashRatioValue
      )
        .then(response => {
          this.Store = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    },
    updateStoreInfo() {
      AXIOS.put(
        "api/store/updateStore" +
          "name=Bake N'Bread" +
          "address=" +
          this.addressValue +
          "phoneNumber=" +
          this.phoneNumberValue +
          "email=" +
          this.emailValue +
          "employeeDiscountRate=" +
          this.employeeDiscountRate +
          "pointToCashRatio=" +
          this.pointToCashRatioValue
      )
        .then(response => {
          this.Store = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
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
