import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import AccountInfo from '@/components/AccountInfo'
import Terminal from '@/components/Terminal'
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
