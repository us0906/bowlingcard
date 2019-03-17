import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Game from '@/components/Game'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/game',
      name: 'Game',
      component: Game
    },
    {
      path: '/',
      name: 'Hello',
      component: Hello
    }
  ]
})
