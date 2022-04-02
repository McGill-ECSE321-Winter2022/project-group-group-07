import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/views/Hello'
import StoreInfo from '@/views/StoreInfo'
import ManageEmployees from '@/views/ManageEmployees'


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
    }
  ]
})