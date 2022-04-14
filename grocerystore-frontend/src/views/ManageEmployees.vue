<template>
  <div>
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
    <div class="column1">
      <form style="margin-top:20px; margin-left:40px;">
        <label style="font-size: 24px">Create Employee Account</label>
        <CustomInput
          label="username"
          type="text"
          :value="username"
          @change="v => (username = v)"
        />
        <CustomInput
          label="password"
          type="password"
          :value="password"
          @change="v => (password = v)"
        />
        <CustomInput
          label="name"
          type="text"
          :value="name"
          @change="v => (name = v)"
        />
        <div><label>Employee position</label></div>
        <div>
          <select name="Role" id="Role" style="width:200px; height:30px;">
            <option value="Cashier">Cashier</option>
            <option value="DeliveryPerson">Delivery Person</option>
            <option value="Clerk">Clerk</option>
          </select>
        </div>
        <label style="font-size: 20px; margin-top:1em;">Address</label>
        <CustomInput
          label="Bulding No."
          type="number"
          :value="buildingNumber"
          @change="v => (buildingNumber = v)"
        />
        <CustomInput
          label="Street"
          type="text"
          :value="street"
          @change="v => (street = v)"
        />
        <CustomInput
          label="Town"
          type="text"
          :value="town"
          @change="v => (town = v)"
        />
        <Button
          @btn-click="createEmployeeAccount"
          text="Create"
          color="black"
          style="margin-top:10px; width:203px;"
        />
      </form>
    </div>
    <div class="vl"></div>
    <div class="column2">
      <label style="font-size: 24px; margin-top: 20px;">Manage Employee</label>
      <select
        @change="refreshEmployeeSchedule()"
        name="employees"
        id="employeeDisplay"
        ref="employeeDisplay"
        size="10"
        style="width:100%"
      >
      </select>
      <div class="column3">
        <label style="font-size: 20px">{{ currentEmployee }} Schedule</label>
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
      <div class="column3">
        <div>
          <Button
            @btn-click="fireEmlployee"
            text="Fire Employee"
            color="red"
            style="float: right;"
          />
        </div>
        <form style="padding:10px; margin-top:2em;">
          <div>
            <div>
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
          <div>
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
          <div style="margin-top: 10px;">
            <Button text="Add" color="black" @btn-click="addWorkingHour()" />
            <Button
              text="Update"
              color="black"
              @btn-click="updateWorkingHour()"
            />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import CustomInput from "../components/CustomInput.vue";
import Button from "../components/Button.vue";
import axios, { Axios } from "axios";
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
  name: "ManageEmployees",
  components: {
    CustomInput,
    Button
  },
  data() {
    return {
      clerk: false,
      deliveryPerson: false,
      cashier: false,
      owner: false,
      customer: true,
      username: "",
      password: "",
      name: "",
      buildingNumber: null,
      street: "",
      town: "",
      startTime: null,
      endTime: null,
      MondayH: { startTime: "", endTime: "" },
      TuesdayH: { startTime: "", endTime: "" },
      WednesdayH: { startTime: "", endTime: "" },
      ThursdayH: { startTime: "", endTime: "" },
      FridayH: { startTime: "", endTime: "" },
      SaturdayH: { startTime: "", endTime: "" },
      SundayH: { startTime: "", endTime: "" },
      currentEmployee: "Store's"
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
    this.refreshEmployees();
    this.refreshSchedule();
  },
  methods: {
    createEmployeeAccount() {
      AXIOS.post(
        "/api/account/employeeAccount/" +
          this.username +
          "?name=" +
          this.name +
          "&password=" +
          this.password +
          "&role=" +
          document.getElementById("Role").value
      )
        .then(response => {
          AXIOS.post(
            "/api/address/address/" +
              this.username +
              "?buildingNo=" +
              this.buildingNumber +
              "&street=" +
              this.street +
              "&town=" +
              this.town
          )
            .then(response => {
              this.refreshEmployees();
              this.clearFields();
            })
            .catch(e => {
              window.alert(e.reponse.data);
            });
        })
        .catch(e => {
          window.alert(e.reponse.data);
        });
      return;
    },
    refreshEmployees: function() {
      AXIOS.get("api/account/employeeAccounts").then(response => {
        var lst = this.$refs.employeeDisplay;
        this.removeOptions(lst);
        const opt = document.createElement("option");
        opt.textContent = "<none>";
        lst.appendChild(opt);
        for (var i = 0; i < response.data.length; i++) {
          var employee = response.data[i];
          const opt = document.createElement("option");
          opt.textContent =
            "username: " +
            employee.username +
            " , name: " +
            employee.name +
            " , role: " +
            employee.role;
          lst.appendChild(opt);
        }
      });
      return;
    },
    removeOptions(selectElement) {
      var i,
        L = selectElement.options.length - 1;
      for (i = L; i >= 0; i--) {
        selectElement.remove(i);
      }
    },
    fireEmlployee() {
      const inf = this.$refs.employeeDisplay;
      const opt = inf.children[inf.selectedIndex];
      var str = opt.textContent.split(" ");
      var username = str[1];
      AXIOS.delete("/api/account/fireEmployee/" + username)
        .then(response => {
          this.refreshEmployees();
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    },
    refreshSchedule() {
      AXIOS.get("api/store/businessHours/Monday")
        .then(response => {
          this.MondayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Tuesday")
        .then(response => {
          this.TuesdayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Wednesday")
        .then(response => {
          this.WednesdayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Thursday")
        .then(response => {
          this.ThursdayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Friday")
        .then(response => {
          this.FridayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Saturday")
        .then(response => {
          this.SaturdayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      AXIOS.get("api/store/businessHours/Sunday")
        .then(response => {
          this.SundayH = response.data;
        })
        .catch(e => {
          window.alert(e.response.data);
        });
      return;
    },
    refreshEmployeeSchedule() {
      const inf = this.$refs.employeeDisplay;
      if (inf.selectedIndex == 0) {
        this.currentEmployee = "Store's ";
        this.refreshSchedule();
      } else {
        const inf = this.$refs.employeeDisplay;
        const opt = inf.children[inf.selectedIndex];
        var str = opt.textContent.split(" ");
        var username = str[1];
        this.currentEmployee = username + "'s ";
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
    addWorkingHour() {
      const inf = this.$refs.employeeDisplay;
      const opt = inf.children[inf.selectedIndex];
      var str = opt.textContent.split(" ");
      var username = str[1];
      var select = document.getElementById("days");
      var day = select.options[select.selectedIndex].value;
      AXIOS.put(
        "/api/schedule/updateSchedule?" +
          "username=" +
          username +
          "&dayOfWeek=" +
          day +
          "&startTime=" +
          this.startTime +
          "&endTime=" +
          this.endTime
      )
        .then(response => {
          this.refreshEmployeeSchedule();
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    },
    updateWorkingHour() {
      const inf = this.$refs.employeeDisplay;
      const opt = inf.children[inf.selectedIndex];
      var str = opt.textContent.split(" ");
      var username = str[1];
      var select = document.getElementById("days");
      var day = select.options[select.selectedIndex].value;
      AXIOS.put(
        "/api/schedule/updateWorkingHour/" +
          username +
          "?dayOfWeek=" +
          day +
          "&startTime=" +
          this.startTime +
          "&endTime=" +
          this.endTime
      )
        .then(response => {
          this.refreshEmployeeSchedule();
        })
        .catch(e => {
          window.alert(e.response.data);
        });
    },
    clearFields() {
      this.username = "";
      this.password = "";
      this.name = "";
      this.buildingNumber = null;
      this.street = "";
      this.town = "";
    },
      logout: function() {
      if (confirm("Press OK to logout")) {
        localStorage.removeItem("role");
        localStorage.removeItem("token");
        localStorage.removeItem("pointBalance")
        this.$router.push("/Login");
      }
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
  width: 30%;
  padding: 10px;
  text-align: left;
}
.column2 {
  float: left;
  width: 70%;
  padding: 10px;
  text-align: left;
}
.column3 {
  float: left;
  width: 50%;
  text-align: left;
  margin-top: 20px;
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
.vl {
  border-left: 2px solid black;
  height: 100%;
  position: absolute;
  left: 30%;
  margin-top: 52px;
  top: 0;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th,
td {
  border: 1px solid black;
  padding: 5px;
  text-align: left;
}
</style>
