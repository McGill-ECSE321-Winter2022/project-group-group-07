//import router from "../router"
export default {
    name: 'NavbarScript',
    data () {
        return {
        }
      },

      created: function () {
      
      },
    
    methods: {
        logout: function(){
            if (confirm("Press OK to logout")) {
                this.$router.push('/Signup');
            }
        },
        AccInfo: function(){
            this.$router.push('/AccountInfo');
        }
    }
}
