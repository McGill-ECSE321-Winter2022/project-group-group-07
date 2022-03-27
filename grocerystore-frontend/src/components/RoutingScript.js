import router from "../router"

export default {
    name: 'WelcomeScript',

    data () {
        return {
        }
      },

      created: function () {
        
      },
    
    methods: {
        routeToSignup: function(){
            this.$router.push('/Signup');
        },
        routeToGuest: function(){
            this.$router.push('/Hello');
        }
    }
}