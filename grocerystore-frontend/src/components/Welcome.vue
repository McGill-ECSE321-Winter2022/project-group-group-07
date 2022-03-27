<template>
  <div id="Welcome">
    <h2>Welcome to Waxi</h2>
      <div>
              <button>Login</button>
              <button @click="routeToSignup()">Sign up</button>
              <button @click="routeToGuest()">Continue as Guest</button>
      </div>
  </div>
</template>

<style>
  #Welcome {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #e8eff2;
  }
</style>

<script src="./WelcomeScript.js">
</script>
<script src="./RoutingScript.js">
</script>