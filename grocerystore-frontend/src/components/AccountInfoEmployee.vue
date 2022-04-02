<template>
<div class="AccountInfoEmployee">
    <div class = "navbar">
        <label>AppName</label>
        <div>
            <button onclick="location.href = '/#/Tasks';">Tasks</button> 
            <button onclick="location.href = '/#/Schedule';">Schedule</button>
            <button onclick="location.href = '/#/AccountInfoEmployee';">Account Information</button>
        </div>
        <div><button>Logout</button></div>
    </div>
     <h1 style="margin-top:1%;">Account Information</h1>

    <br>

    <div align="left">
        <label>UserName: </label>
        <label id = "username" > {{customerUsername}} </label>
        <br>
        <label>Name: </label>
        <label id = "name" > {{customerName}} </label><br>
        <button @click="changePassword()" class="block magenta margin-auto"> Change Password </button>
        
        <span v-if="errorName" style="color:red">Error: {{errorName}} </span>
        <br>
        
        <label>Address: </label>
        <label id = "address" > {{customerAddress}} </label><br>
        <button @click="changeAddress()"> Change Address </button><br>

        <br>

    </div>
    <br>

</div>
</template>
<script src="./AccountInfoScript.js">
</script>

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
.center {
    margin-left: auto;
    margin-right: auto;
}
.margin-auto {
    margin: 0 auto;
}
</style>