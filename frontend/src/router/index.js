import Vue from 'vue'
import VueRouter from 'vue-router'
import PageNotFound from '../views/PageNotFound'
import SignIn from '../views/SignIn'
import Signup from '../views/Signup'
Vue.use(VueRouter)

const routes = [
  {
    path: '*',
    name: '404',
    component: PageNotFound,
  },

  {
    path: '/',
    name: 'SignIn',
    component: SignIn
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: Signup
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
