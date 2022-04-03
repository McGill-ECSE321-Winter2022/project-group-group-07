<template>
  <div class="order">
    <div class="order_content">
      <div class="order_cart">
           <h3 class="order_header">Order {{ order.orderID }}</h3>
        <h3>
          <b class="order_price">{{ order.timeSlot.startDate}}: {{ order.timeSlot.startTime  }}</b>
        </h3>
        <button @click="updateCart('remove')" class="order_remove">
          Order Has Been Completed
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["order"],
  methods: {
    updateCart(updateType) {
      this.$emit(updateType);
    }
  }
};
</script>
<style scoped>
.order {
  display: flex;
  flex-direction: row;
  background-color: whitesmoke;
  border-radius: 0px;
  padding: 0px;
  text-align: left;
}
.order_header {
    padding-top: 3px;
    font-size: 15px;
    color: black;
}
.order_image {
  text-align: center;
  flex: 1;
}
.order_content {
  flex: 2;
  display: flex;
  flex-direction: row;
}
.order_cart {
  padding-top: 0px;
  text-align: left;
  font-size: small;
}
.order_price {
  padding-left: 0px;
  font-size: 15px;
  color: blue;
}
.order_status {
  padding-left: 1px;
}
.order_instock {
  background-color: green;
  width: 150px;
  text-align: center;
  color: white;
  font-size: 15px;
}
.order_out_of_stock {
  background-color: red;
  width: 300px;
  text-align: left;
  color: white;
  font-size: 10px;
}
.order_instore_only {
  font-size: 13px;
  color: red;
}
.order_button {
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
.order_remove {
  background-color: transparent;
  border: transparent;
  color: green;
  height: 30px;
  width: 300px;
  text-align: left;
  text-decoration: none;
  display: inline-block;
  font-size: 10px;
  cursor: pointer;
}
</style>
