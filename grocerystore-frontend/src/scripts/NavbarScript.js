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
                localStorage.removeItem('token');
            }
        },
        AccInfo: function(){
            this.$router.push('/AccountInfo');
        },
        StatsOrder: function(){
            this.$router.push('/StatusOrder');
        },
        Cart: function(){
            this.$router.push('/Cart');
        },
        Catalog: function(){
            this.$router.push('/Catalog');
        }
    }
}
