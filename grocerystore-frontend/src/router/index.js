import Vue from 'vue'
import Router from 'vue-router'

import Hello from '@/views/Hello'
import StoreInfo from '@/views/StoreInfo'
import ManageEmployees from '@/views/ManageEmployees'
import AccountInfo from '@/components/AccountInfo'
import Terminal from '@/components/Terminal'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/s',
      name: 'StoreInfo',
      component: StoreInfo
    },
    {
      path: '/a',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/',
      name: 'ManageEmployees',
      component: ManageEmployees
    },
    {
      path: '/AccountInfo',
      name: 'AccountInfo',
      component: AccountInfo,
    },{
      path: '/Terminal',
      name: 'Terminal',
      component: Terminal,
    },
  ]
})