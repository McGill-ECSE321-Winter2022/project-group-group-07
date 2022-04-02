<template>
  <div class="Cart">
    <div class = "navbar">
      <label>AppName</label>
      <div>
        <button>Catalog</button>
        <button onclick="location.href='http://127.0.0.1:8087/#/Cart'">Cart/Checkout</button>
        <button>Order Status</button>
        <button>Account Information</button>
      </div>
      <div><button>Logout</button></div>
    </div>

    <div class="column">
      <div class='one'>
        <div class='checkout_label'>
          <h2>My cart</h2>
        </div>
        <section class="products" v-if="products.length>0">
          <div v-for="product in products" :key="product.id" class="product">
            <Product :product="product" @remove="updateCart(product, 'remove')"
                     @add="updateCart(product, 'add')"
                     @subtract="updateCart(product, 'subtract')" />
          </div>
        </section>
        <h2 v-else> Cart is lonely... </h2>
      </div>
      <div class='two'>
        <div class='order_label'>
          <h2>Order summary</h2>
        </div>
        <div class='orderSummary'>
          <p> Subtotal: {{ calculateSum(products) }} CAD </p>
          <p> Discount: {{ (discount).toFixed(2) }} CAD </p>
          <p> Total: {{ (calculateSum(products) - (discount)).toFixed(2) }} CAD </p>
          <checkout username='matt' />
          <button class = "checkout_buttons" onclick="location.href='http://127.0.0.1:8087/#/Checkout'">Proceed to checkout</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Product from './Product'
export default {
  name: 'hello',
  components:{
    Product,
  },
  data () {
    return {
      payMethod: 0,
      points: 10000,
      discount: 0,
      products: [
        {
          id: 1,
          name: 'Product 1',
          description: 'This is an incredibly awesome product',
          quantity: 1,
          price: 100,
          inStock: true,
          online: false,
          inventory: 10,
          image: 'https://via.placeholder.com/150',
        },
        {
          id: 2,
          name: 'Product 2',
          description: 'This is an incredibly awesome product',
          quantity: 1,
          price: 10,
          inStock: true,
          online: true,
          inventory: 7,
          image: 'https://via.placeholder.com/150',
        },
        {
          id: 3,
          name: 'Product 3',
          description: 'This is an incredibly awesome product',
          quantity: 1,
          price: 50,
          inStock: false,
          online: false,
          inventory: 7,
          image: 'https://via.placeholder.com/150',
        },
        {
          id: 4,
          name: 'Product 1',
          description: 'This is an incredibly awesome product',
          quantity: 1,
          price: 100,
          inStock: true,
          online: false,
          inventory: 4,
          image: 'https://via.placeholder.com/150',
        },
        {
          id: 5,
          name: 'Product 2',
          description: 'This is an incredibly awesome product',
          quantity: 1,
          price: 10,
          inStock: true,
          online: false,
          inventory: 7,
          image: 'https://via.placeholder.com/150',
        },
        {
          id: 6,
          name: 'Product 3',
          description: 'This is an incredibly awesome product',
          quantity: 1,
          price: 50,
          inStock: true,
          online: false,
          inventory: 7,
          image: 'https://via.placeholder.com/150',
        },
        {
          id: 7,
          name: 'Product 1',
          description: 'This is an incredibly awesome product',
          quantity: 1,
          price: 100,
          inStock: false,
          online: true,
          inventory: 7,
          image: 'https://via.placeholder.com/150',
        },
        {
          id: 8,
          name: 'Product 2',
          description: 'This is an incredibly awesome product',
          quantity: 1,
          price: 10.8,
          inStock: false,
          online: false,
          inventory: 7,
          image: 'https://via.placeholder.com/150',
        },
        {
          id: 9,
          name: 'Product 3',
          description: 'This is an incredibly awesome product',
          quantity: 1,
          price: 50,
          inStock: true,
          online: true,
          inventory: 7,
          image: 'https://via.placeholder.com/150',
        }
      ],
    }
  },
  methods: {
    calculateSum: function (products) {
      var sum = 0;
      for (var i = 0; i < products.length; i++){
        sum += (products[i].price * products[i].quantity)
      }
      return sum;
    },
    nothing: function() {
      return 0;
    },

    updateCart(product, updateType) {
      for (let i = 0; i < this.products.length; i++) {
        if (this.products[i].id === product.id) {
          if (updateType === 'subtract') {
            if (this.products[i].quantity !== 0) {
              this.products[i].quantity--;
            }
          } else if (updateType === 'add'){
            if (this.products[i].quantity < this.products[i].inventory) {
              this.products[i].quantity++;
            }
          } else if (updateType === 'remove'){
            this.products.splice(i,1);
          }

          break;
        }
      }
    },
    applyPoints() {
      let dis =  document.getElementById("points").value;
      if (this.points >= dis) {
        this.discount += dis * 0.01;
        this.points -= dis;
      }
    },
    updatePayment(newMethod){
      this.payMethod = newMethod
    }
  }
}
</script>

<style scoped>
h1, h2 {
  font-weight: normal;
}

.checkout_label {
  background-color:  rgb(40, 50, 50); color: white;
}
.order_label{
  background-color: whitesmoke;
  border-top: 5px solid rgb(40, 50, 50);
  border-bottom: 5px solid rgb(40, 50, 50);
}
.column {
  padding: 10px;
  display: flex;
}
.one{
  background-color: white;
  flex: 2;
  border-right: 5px solid rgb(40, 50, 50);
}
.two{
  background-color: white;
  flex: 1;
  display: flex;
  height: 30%;
  flex-direction: column;
  gap: 10px;
}
.orderSummary{
  background-color: whitesmoke;
  height: 150px;
  flex: 1;
  padding: 10px;
}
.products{
  display: grid;
  grid-template-columns: 1fr;
  grid-row-gap: 10px;
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
.navbar{
  height:  auto;
  background-color: rgb(40, 50, 50);
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: azure;
  display: inline-flex;
  width: 100%;
}
.checkout_buttons{
  border-radius: 8px;
  background-color: darkgreen;
  border: darkgreen;
  color: white;
  height: 40px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  cursor: pointer;
}
</style>
