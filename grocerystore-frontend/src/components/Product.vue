<template>
  <div class="product">
    <div class="product_image">
      <img
        :src="product.imageLink"
        :alt="product.productName"
        class="product_image"
        style="width: 150px; min-width: 150px;"
      />
    </div>
    <div class="product_content">
      <div class="product_status">
        <label class="product_header">{{ product.productName }}</label>
        <p v-if="numInStock()" class="product_instock"><b> IN STOCK. </b></p>
        <p v-else class="product_out_of_stock"><b> OUT OF STOCK </b></p>
        <p
          v-if="!product.availableOnline && numInStock()"
          class="product_instore_only"
        >
          <b> In Store Only </b>
        </p>
      </div>

      <div class="product_cart">
        
        <p>
          <b class="product_price">{{ product.price }} CAD</b>
        </p>
       
        <Button
          v-if="!cart && product.availableOnline"
          @btn-click="addToCart()"
          text="Add to cart"
          color="black"
        />
      </div>
    </div>
    <img v-if="cart" src="https://cdn-icons-png.flaticon.com/512/458/458594.png" alt="x button to remove" style="width: 2em; height: 2em; float: right;" @click="updateCart('remove-item')"> 
  </div>
</template>

<script>
import Button from "./Button.vue";
export default {
  components: { Button },
  props: ["product", "cart"],
  methods: {
    updateCart(updateType) {
      this.$emit(updateType);
    },
    addToCart() {
      this.$emit("addToCart", this.product.itemID, this.product.numInStock);
    },
    numInStock() {
      if (this.product.numInStock > 0) {
        return true;
      } else {
        return false;
      }
    }
  }
};
</script>
<style scoped>
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
.product {
  border-radius: 25px;
  border: 2px solid #000;
  display: flex;
  flex-direction: row;
  background-color: whitesmoke;
  padding: 5px;
  text-align: left;
}
.product_image {
  border-radius: 25px;
  text-align: center;
  flex: 1;
}
.product_content {
  flex: 9;
  display: flex;
  flex-direction: row;
}
.product_cart {
  padding-top: 50px;
  text-align: right;
  font-size: large;
  margin-inline-start: auto;
}
.product_price {

  padding-right: 30px;
  font-size: 20px;
  color: darkgreen;
}
.product_status {
  padding-left: 15px;
}
.product_instock {
  width: 150px;
  text-align: left;
  color: rgb(0, 168, 0);
  font-size: 15px;
  font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
}
.product_out_of_stock {
    width: 150px;
  text-align: left;
  color: rgb(109, 66, 1);
  font-size: 15px;
  font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
}
.product_instore_only {
  width: 150px;
  text-align: left;
  color: rgb(255, 0, 0);
  font-size: 15px;
  font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
}
.product_button {
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
.product_remove {
  background-color: transparent;
   padding: 2%;
  color: red;
  height: 30px;
  width: 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  cursor: pointer;
}
.product_header {
  font-size: 24px;
  font-family: inherit;
}
</style>
