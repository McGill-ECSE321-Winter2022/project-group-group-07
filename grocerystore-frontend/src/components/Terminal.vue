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
  <input type="number" min="0" style="width:100%; text-align: center;"></input>
  <button style="margin-top:1%;">Add Item</button>
  </div>
  <div style="display:table; padding: 5%;">
  <label>Item Summary</label>
  <select overflow="scroll" size="10" id="itemslists" style="width:100%; text-align: center;">
  </select>
  <button>Remove Item</button>
  </div>
  </div>
  <div>
  <button>Submit to Payment</button>
  </div>
  </div>
</template>

<script>
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
<style>
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
