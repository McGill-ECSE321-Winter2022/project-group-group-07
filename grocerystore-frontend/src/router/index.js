import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/views/Hello'
import AccountInfo from '@/views/AccountInfo'
import Cart from '@/views/Cart'
import Checkout from '@/views/Checkout'
import ManageEmployees from '@/views/ManageEmployees'
import StoreInfo from '@/views/StoreInfo'
import Terminal from '@/views/Terminal'
import AccountInfoEmployee from '@/components/AccountInfoEmployee'
import Delivery from '@/components/Delivery'
import PickUp from '@/components/PickUp'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
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
    }
    
  ]
})