<template>
<div class="AccountInfo">
    <div class = "navbar">
        <label>AppName</label>
        <div>
            <button>Catalog</button> 
            <button>Cart/Checkout</button> 
            <button>Order Status</button>
            <button>Account Information</button>
        </div>
        <div><button>Logout</button></div>
    </div>
     <h1 style="margin-top:1%;">Account Informations</h1>

    <br>

    <div>
        <label>UserName: </label>
        <label id = "username" > {{customerUsername}} </label><br>
        <label>Name: </label>
        <label id = "name" > {{customerName}} </label>
        <button @click="changeName()"> Change Name </button>
        <button @click="changePassword()"> Change Password </button><br>
        <span v-if="errorName" style="color:red">Error: {{errorName}} </span>
        
        <br>
        
        <label>Address: </label>
        <label id = "address" > {{customerAddress}} </label>
        <button @click="changeAddress()"> Change Address </button><br>

        <br>
        <label>Current Points: </label>
        <label id = "points" > {{customerPoints}} </label><br>
        <label>Current Points to Cash Ratio: </label>
        <label id = "ratio" > {{storePointsRatio}} </label>

    </div>

</div>
</template>
<script src="./AccountInfoScript.js">
</script>
<script src="./NavbarScript.js">
</script>

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