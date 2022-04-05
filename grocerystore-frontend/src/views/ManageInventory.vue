<template>
  <div>
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
    <div class="column1">
      <form style="margin-top:20px; margin-left:40px;">
        <label style="font-size: 24px">Add New Item To Shop</label>
        <CustomInput
          label="name"
          type="text"
          :value="name"
          @change="v => (name = v)"
        />
        <CustomInput
          label="price"
          type="number"
          step="0.01"
          :value="price"
          @change="v => (price = v)"
        />
        <div>
          <div><label style="margin-top: 10px;">Availabe Online</label></div>
          <select
            name="AvailableOnline"
            id="AvailableOnline"
            style="width:200px; height:30px;  margin-bottom:10px;"
          >
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </div>
        <CustomInput
          label="Point Per Item"
          type="number"
          :value="pointPerItem"
          @change="v => (pointPerItem = v)"
        />
        <CustomInput
          label="Image Link"
          type="text"
          :value="imageLink"
          @change="v => (imageLink = v)"
        />
        <div><label style="margin-top: 10px;">Category</label></div>
        <select
          name="ItemCategory"
          ref="ItemCategory"
          id="ItemCategory"
          style="width:200px; height:30px;"
        >
          <option value="BakedGoods">BakedGoods</option>
          <option value="FruitsAndVegetables">FruitsAndVegetables</option>
          <option value="Toiletries">Toiletries</option>
          <option value="Pantry">Pantry</option>
          <option value="MeatsAndFish">MeatsAndFish</option>
          <option value="Furniture">Furniture</option>
          <option value="Clothing">Clothing</option>
          <option value="Others">Others</option>
        </select>
        <div><label style="margin-top: 10px;">Returnable</label></div>
        <select
          name="Returnable"
          ref="Returnable"
          id="Returnable"
          style="width:200px; height:30px;"
        >
          <option value="True">True</option>
          <option value="False">False</option>
        </select>
        <Button
          @btn-click="createItem"
          text="Add"
          color="black"
          style="margin-top:10px; width:203px;"
        />
      </form>
    </div>
    <div class="vl"></div>
    <form>
      <div class="column2">
        <label style="font-size: 24px; margin-top: 20px;"
          >Manage Inventory</label
        >
        <select
          name="Items"
          ref="itemDisplay"
          id=""
          size="20"
          style="width:100%"
        >
        </select>
      </div>
      <div>
        <CustomInput
          label="Quantity"
          type="number"
          :value="quantity"
          @change="v => (quantity = v)"
          style="float: left; margin-left:10px;"
        />
        <div>
          <Button
            @btn-click="restock"
            text="Restock"
            color="black"
            style="margin-top:15px; width:203px;"
          />
        </div>
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
  name: "ManageInventory",
  components: {
    CustomInput,
    Button
  },
  data() {
    return {
      clerk: false,
      deliveryPerson: false,
      cashier: false,
      owner:false,
      customer: true,
      name: "",
      price: null,
      pointPerItem: null,
      imageLink: "",
      quantity: null
    };
  },
  created: function() {
      this.clerk = localStorage.getItem("role").includes("Clerk");
    this.deliveryPerson = localStorage.getItem("role").includes("DeliveryPerson") ;
    this.cashier = localStorage.getItem("role").includes("Cashier") ;
    this.owner = localStorage.getItem("role").includes("Owner") ;
    this.customer = localStorage.getItem("role").includes("Customer") ;
    this.refreshItems();
    this.clearFields();

  },
  methods: {
    refreshItems() {
      AXIOS.get("/api/item/items/")
        .then(response => {
          var lst = this.$refs.itemDisplay;
          this.removeOptions(lst);
          const opt = document.createElement("option");
          opt.textContent = "<none>";
          lst.appendChild(opt);
          for (var i = 0; i < response.data.length; i++) {
            var itm = response.data[i];
            const opt = document.createElement("option");
            opt.textContent =
              "Name: " +
              itm.productName +
              " , ID: " +
              itm.itemID +
              " , " +
              itm.price +
              " CAD, Category: " +
              itm.category +
              " In stock: " +
              itm.numInStock;
            lst.appendChild(opt);
          }
        })
        .catch(e => {
          window.alert(e.response.data);
          return;
        });
    },
    createItem() {
      var cat = document.getElementById("ItemCategory");
      var category = cat.options[cat.selectedIndex].value;
      var ret = document.getElementById("Returnable");
      var returnable = ret.options[ret.selectedIndex].value;
      var ava = document.getElementById("AvailableOnline");
      var available = ava.options[ava.selectedIndex].value;
      if (returnable == "True") {
        AXIOS.post(
          "api/item/nonperishable?" +
            "productName=" +
            this.name +
            "&price=" +
            this.price +
            "&availableOnline=" +
            available +
            "&pointPerItem=" +
            this.pointPerItem +
            "&imageLink=" +
            this.imageLink +
            "&category=" +
            category
        )
          .then(response => {
            this.refreshItems();
            this.clearFields();
          })
          .catch(e => {
            window.alert(e.response.data);
          });
      } else {
        AXIOS.post(
          "api/item/perishable?" +
            "productName=" +
            this.name +
            "&price=" +
            this.price +
            "&availableOnline=" +
            available +
            "&pointPerItem=" +
            this.pointPerItem +
            "&imageLink=" +
            this.imageLink +
            "&category=" +
            category
        )
          .then(response => {
            this.refreshItems();
            this.clearFields();
          })
          .catch(e => {
            window.alert(e.response.data);
          });
      }
    },
    restock() {
      const lst = this.$refs.itemDisplay;
      const itm = lst.children[lst.selectedIndex];
      var str = itm.textContent.split(" ");
      var id = str[4];
      if (id == null) {
        window.alert("pick an item");
      } else if (this.quantity == null) {
        window.alert("enter a quantity");
      } else {
        AXIOS.put(
          "api/item/restock?" + "id=" + id + "&quantity=" + this.quantity
        )
          .then(response => {
            this.refreshItems();
            this.clearFields();
          })
          .catch(e => {
            window.alert(e.response.data);
          });
      }
    },
    removeOptions(selectElement) {
      var i,
        L = selectElement.options.length - 1;
      for (i = L; i >= 0; i--) {
        selectElement.remove(i);
      }
    },
    clearFields() {
      (this.name = ""),
        (this.price = null),
        (this.pointPerItem = null),
        (this.imageLink = ""),
        (this.quantity = null);
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
