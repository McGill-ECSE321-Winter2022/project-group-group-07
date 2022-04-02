<template>
  <div class = "product">
    <div class="product_image">
      <img
        :src= "product.image"
        :alt="product.name"
        class="product_image"
        style="width: 150px; min-width: 150px;"
      >
    </div>
    <div class="product_content">
      <div class="product_status">
        <h3 class="product_header">{{ product.name }}</h3>
        <p v-if="product.inStock" class="product_instock"><b> INSTOCK </b></p>
        <p v-else class="product_out_of_stock"><b> OUT OF STOCK </b></p>
        <p v-if="!product.online & product.inStock" class="product_instore_only"><b> STORE PICKUP ONLY </b></p>
      </div>
      <div class="product_cart">
        <p><b class = product_price>{{ product.price }} CAD</b></p>
        <button
          @click="updateCart( 'subtract')"
          class="product_button"
        >
          -
        </button>
        <span class="cart_quantity">{{ product.quantity }}</span>
        <button
          @click="updateCart( 'add')"
          class="product_button"
        >
          +
        </button>
        <button
          @click="updateCart( 'remove')"
          class="product_remove"
        >
          x
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["product"],
  methods:{
    updateCart(updateType){
      this.$emit(updateType);
    }
  }
}
</script>
<style scoped>
.product{
  display: flex;
  background-color: whitesmoke;
  border-radius: 10px;
  padding: 5px;
  text-align: left;
}
.product_image{
  text-align: center;
  flex: 1
}
.product_content{
  flex: 9
}
.product_cart{
  padding-top: 30px;
  text-align: right;
  font-size:large;
}
.product_price{
  padding-right: 30px;
  font-size: 20px;
  color: darkgreen;
}
.product_status{
  padding-left: 15px;
}
.product_header{
}
.product_instock{
  background-color: green;
  width: 150px;
  text-align: center;
  color: white;
  font-size: 15px;
}
.product_out_of_stock{
  background-color: red;
  width: 150px;
  text-align: center;
  color: white;
  font-size: 15px;
}
.product_instore_only{
  font-size: 13px;
  color: red;
}
.product_button{
  background-color: forestgreen;
  border: none;
  color: white;
  height: 30px;
  width: 30px;
  border-radius: 4px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  cursor: pointer;
}
.product_remove{
  background-color: transparent;
  border: transparent;
  color: red;
  height: 30px;
  width: 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  cursor: pointer;
}
</style>
