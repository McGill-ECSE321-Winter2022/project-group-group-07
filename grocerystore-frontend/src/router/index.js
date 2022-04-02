import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import AccountInfo from '@/components/AccountInfo'
import Terminal from '@/components/Terminal'

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
    { path: '/Terminal',
      name: 'Terminal',
      component: Terminal
    }
  ]
})
