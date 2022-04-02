<template>
  <div class="terminal">
  <div class = "navbar">
  <label>AppName</label>
  <div>
  <button>Button 1</button> 
  <button>Button 2</button>
  <button>Button 3</button>
  </div>
  <div><button>Button 4</button></div>
  </div>
  <h1 style="margin-top:1%;">Cashier Terminal</h1>
  <div style="display:inline-flex; padding: 5%;">
  <div style="display:table; padding: 5%;">
  <label>Item ID:</label>
  <input v-model="id" type="number" min="0" style="width:100%; text-align: center;">
  <button @click="additem()" style="margin-top:1%;">Add Item</button>
  </div>
  <div style="display:table; padding: 5%;">
  <label>Item Summary</label>
  <select ref="selecter" v-model="sel" overflow="scroll" size="10" id="itemslists" style="width:100%; text-align: center;"></select>
  <button @click="removeitem()">Remove Item</button>
  <p></p>
  <label style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: large;
    font-weight: bold;">Total Price: {{ total }} CAD</label>
  </div>
  </div>
  <div>
  <button>Submit to Payment</button>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
import { ListGroupPlugin } from 'bootstrap-vue'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
export default {
    name: 'Terminal',
    data () {
        return {
          id: null,
          sel: null,
          total: 0.00
        }
      },

      created: function () {
        
      },
    
    methods: {
        additem: function(){
            var itm;
            var lst = document.getElementById("itemslists");
            AXIOS.get('/api/item/items/id:'+ this.id)
            .then(response => {
              itm = response.data;
              this.total += itm.price; 
              const opt = document.createElement("option");
              opt.textContent = itm.productName +", "+ itm.itemID +", "+ itm.price + " CAD";
              lst.appendChild(opt);
             })
            .catch(e => {
              window.alert("There is no item with this ID.");
              return;
            })
        },
        removeitem: function(){
           const inf = this.$refs.selecter;
           const opt = inf.children[inf.selectedIndex];
           var str = opt.textContent.split(' ');
           var price = str[str.length - 2];
           var prc = parseFloat(price);
           this.total -= prc;
           opt.remove();
        }
      }
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
