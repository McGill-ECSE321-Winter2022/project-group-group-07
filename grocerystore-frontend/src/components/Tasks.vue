<template>
<div class="Tasks">
    <div class = "navbar">
        <label>AppName</label>
        <div>
            <button onclick="location.href = '/#/Tasks';">Tasks</button> 
            <button onclick="location.href = '/#/Schedule';">Schedule</button>
            <button onclick="location.href = '/#/AccountInfoEmployee';">Account Information</button>
        </div>
        <div><button>Logout</button></div>
    </div>
     <h1 style="margin-top:1%;">Tasks</h1>

    <br>

    <div align="left">
        <label>Cashier:</label>
        <button onclick="location.href = '/#/Terminal';">Terminal</button>
        <br>
        <label>Clerk:</label>
        <button onclick="location.href = '/#/Terminal';">Terminal</button>
        <br>
        <label>Delivery:</label>
        <button onclick="location.href = '/#/Delivery';">Delivery</button>
        <br>
    </div>
    <br>

</div>
</template>


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
.center {
    margin-left: auto;
    margin-right: auto;
}

</style>