import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import AccountInfo from '@/components/AccountInfo'
import Terminal from '@/components/Terminal'
import AccountInfoEmployee from '@/components/AccountInfoEmployee'
import Schedule from '@/components/Schedule'
import Tasks from '@/components/Tasks'
import Delivery from '@/components/Delivery'
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
      path: '/Schedule',
      name: 'Schedule',
      component: Schedule
    },
    {
      path: '/Tasks',
      name: 'Tasks',
      component: Tasks
    },
    {
      path: '/Delivery',
      name: 'Delivery',
      component: Delivery
    }
    
  ]
})
