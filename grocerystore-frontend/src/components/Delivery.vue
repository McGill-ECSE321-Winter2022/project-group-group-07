<template>
    <div class="Delivery">
        <div class = "navbar">
            <label>AppName</label>
            <div>
                <button v-if="variable" onclick= "location.href = '/#/PickUp';">Pickup Orders</button> 
                <button v-if="variable1" onclick="location.href = '/#/Delivery';">Delivery Orders</button> 
                <button onclick="location.href = '/#/AccountInfoEmployee';">Account Information</button>
            </div>
            <div><button>Logout</button></div>
        </div>

        <h1 style="margin-top:1%">Delivery</h1>
        <div align="left"><label>Orders to be fullfilled by: </label></div>
 
    </div>
</template>


<script>
    export default{
        name: "Delivery",
        data(){
        return {
            variable: true,
            variable1: false
        }    
    },
        created: function() {
            this.variable=false;
            this.variable1=true;
            console.log("Hello i am running");
        }
    }
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

</style>