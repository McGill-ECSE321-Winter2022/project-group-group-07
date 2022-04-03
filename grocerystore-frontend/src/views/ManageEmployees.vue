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
          type="text"
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
          text="Create"
          color="black"
          style="margin-top:10px; width:203px;"
        />
      </form>
    </div>
    <div class="vl"></div>
    <div class="column2">
      <label style="font-size: 24px; margin-top: 20px;">Manage Employee</label>
      <select name="employees" id="employees" size="10" style="width:100%">
      </select>
      <div class="column3">
        <label style="font-size: 20px">Schedule</label>
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
      <div class="column3">
        <div>
          <Button text="Fire Employee" color="red" style="float: right;" />
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
            <Button text="Add" color="black" />
            <Button text="Update" color="black" />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import CustomInput from "../components/CustomInput.vue";
import Button from "../components/Button.vue";
export default {
  name: "ManageEmployees",
  components: {
    CustomInput,
    Button
  },
  data() {
    return {
      username: "",
      password: "",
      name: "",
      buildingNumber: null,
      street: "",
      towm: ""
    };
  },
  method: {}
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
