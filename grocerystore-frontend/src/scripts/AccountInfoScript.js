import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'WelcomeScript',

    data () {
        return {
            customerName: "getName()",
            errorName: '',
            customerUsername: "getUsername()",
            customerAddress: "getAddress()",
            customerPoints: "getPoints()",
            storePointsRatio: "getPointsRatio()"
        }
      },

      created: function () {
        //not yet written
        AXIOS.get('/api/account/currentUsername/')
        .then(response => {
            this.customerUsername.push(response.data)
        })
        .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorName = errorMsg
        }),
        //not yet written
        AXIOS.get('/api/account/currentName/')
        .then(response => {
            this.customerName.push(response.data)
        })
        .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorName = errorMsg
        })
      },
    
    methods: {
        changeName: function(){
            let username = prompt("Please enter your current Username", "Enter username");
            let person = prompt("Please enter your new Username", "Enter new name");
            AXIOS.put('/api/account/updateName/'.concat(username).concat('?newName=').concat(person))
            .then(response => {
                this.customerName.push(response.data)
                this.errorName = ''
            })
            .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorName = errorMsg
            })
        },
        changePassword: function(){
            window.alert("Plz write the function first");
        },
        getAdress: function(){
            window.alert("Plz write the function first");
        },
        changeAddress: function(){
            window.alert("Plz write the function first");
        },
        getPoints: function(){
            window.alert("Plz write the function first");
        },
        getPointsRatio: function(){
            window.alert("Plz write the function first");
        }
    }
}