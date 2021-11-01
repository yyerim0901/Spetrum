import Vue from 'vue'
import VueRouter from 'vue-router'
import PageNotFound from '../views/PageNotFound'
import SignIn from '../views/SignIn'
import Signup from '../views/Signup'
import PetBTI from '../views/PetBTI'
Vue.use(VueRouter)

const routes = [
  {
    path: '*',
    name: '404',
    component: PageNotFound,
  },

  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn,

  },
  {
    path: '/signup',
    name: 'SignUp',
    component: Signup,
  },
  {
    path: '/',
    name: 'PetBTI',
    component: PetBTI,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.name !== 'SignIn' && to.name !== 'SignUp' && !localStorage.getItem('token')) {
    next({ name: 'SignIn' }); 
  }
  else if ( to.name == 'SignIn' && localStorage.getItem('token')){
    next({ name: 'PetBTI'});
  }
  else if ( to.name == 'SignUp' && localStorage.getItem('token')){
    next({ name: 'PetBTI'});
  }

  else if (to.name == 'SignUp' && from.name == 'SignIn') {
    next();
  }
  
  else next()
})
export default router
