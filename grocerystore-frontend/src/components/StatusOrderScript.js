import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'StatusOrderScript',

    data () {
        return {
            orders: []
        }
      },

      created: function () {
        //not yet written
        AXIOS.get('/api/...')
        .then(response => {

        })
        .catch(e => {
          
        })
      },
    
    methods: {
       
    }
}