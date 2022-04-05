import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/views/Hello'
import Welcome from '@/views/Login'
import Signup from '@/views/Signup'
import AccountInfo from '@/views/AccountInfo'
import AccountInfoEmployee from '@/views/AccountInfoEmployee'
import Delivery from '@/views/Delivery'
import PickUp from '@/views/PickUp'
import Cart from '@/views/Cart'
import Checkout from '@/views/Checkout'
import ManageEmployees from '@/views/ManageEmployees'
import StoreInfo from '@/views/StoreInfo'
import Terminal from '@/views/Terminal'
import StatusOrder from '@/views/StatusOrder'
import EditProfile from '@/views/EditProfile'
import ManageInventory from '@/views/ManageInventory'
import Report from '@/views/Report'
import Catalogue from '@/views/Catalogue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/Signup',
      name: 'Signup',
      component: Signup
    },
    {
      path: '/Login',
      name: 'Login',
      component: Welcome
    },
    {
      path: '/AccountInfo',
      name: 'AccountInfo',
      component: AccountInfo
    },
    {
      path: '/Cart',
      name: 'Cart',
      component: Cart
    },
    {
      path: '/Checkout',
      name: 'Checkout',
      component: Checkout
    },
    {
      path: '/ManageEmployees',
      name: 'ManageEmployees',
      component: ManageEmployees
    },
    {
      path: '/StoreInfo',
      name: 'StoreInfo',
      component: StoreInfo
    },
    {
      path: '/Terminal',
      name: 'Terminal',
      component: Terminal
    },
    {
      path: '/AccountInfoEmployee',
      name: 'AccountInfoEmployee',
      component: AccountInfoEmployee
    },
    {
      path: '/Delivery',
      name: 'Delivery',
      component: Delivery
    },
    {
      path: '/PickUp',
      name: 'PickUp',
      component: PickUp
     },
    { path: '/StatusOrder',
      name: 'StatusOrder',
      component: StatusOrder
    },
    { path: '/EditProfile',
      name: 'EditProfile',
      component: EditProfile
    },
    {
      path: '/ManageInventory',
      name: 'ManageInventory',
      component: ManageInventory
    },
    { path: '/Report',
      name: 'Report',
      component: Report
    },
    {
      path: '/Catalogue',
      name: 'Catalogue',
      component: Catalogue
    },
  ]
})