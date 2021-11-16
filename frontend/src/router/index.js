import Vue from 'vue'
import VueRouter from 'vue-router'
import PageNotFound from '../views/PageNotFound'
import SignIn from '../views/SignIn'
import Signup from '../views/Signup'
import PetBTI from '../views/PetBTI'
import Moment from '../views/Moment'
import Dogging from '../views/Dogging'
import MyPage from '../views/MyPage'
import PetTest from '../views/PetTest'
import PetSitter from '../views/PetSitter'
import PetSitterEachDetail from '../components/molecules/PetSitterEachDetail'
import TestResult from '../views/TestResult'
import YourMoment from '../views/YourMoment'
import AddMoment from '../views/AddMoment'
import MDetail from '../views/MDetail'
import MUpdate from '../views/UpdateMoment'
import AddPetSitter from '../views/AddPetSitter'
import ChangePetSitter from '../views/ChangePetSitter'
import UpdateUserInfo from '../views/UpdateUserInfo'
import MyDoggingList from '../views/MyDoggingList'
import MyPetsitterList from '../views/MyPetsitterList'
import Chat from '../views/Chat'

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
  {
    path: '/mymoment',
    name:'Moment',
    component: Moment,
  },
  {
    path: '/mymoment/write',
    name:'AddMoment',
    component: AddMoment,
  },
  {
    path: '/moment/board/:boardid',
    name:'MDetail',
    component: MDetail,
  },
  {
    path: '/moment/update/:boardid',
    name:'MUpdate',
    component: MUpdate,
  },
  {
    path: '/moment/:userid',
    name:'YourMoment',
    component: YourMoment,
  },
  {
    path: '/dogging',
    name:'Dogging',
    component: Dogging
  },
  {
    path: '/mypage',
    name:'MyPage',
    component: MyPage
  },
  {
    path: '/petbti/test',
    name:'PetTest',
    component: PetTest
  },
  {
    path: '/petsitter',
    name:'PetSitter',
    component: PetSitter
  },
  {
    path: '/petsitter/:board_id',
    name:'PetSitterEachDetail',
    component: PetSitterEachDetail
  },
  {
    path: '/petbti/result',
    name:'TestResult',
    component: TestResult
  },
  {
    path: '/write/petsitter',
    name:'AddPetSitter',
    component: AddPetSitter
  },
  {
    path: '/change/petsitter',
    name:'ChangePetSitter',
    component: ChangePetSitter
  },
  {
    path: '/update/user',
    name: 'UpdateUserInfo',
    component : UpdateUserInfo
  },
  {
    path: '/mydogging',
    name: 'MyDoggingList',
    component : MyDoggingList
  },
  {
    path: '/mypetsitter',
    name: 'MyPetsitterList',
    component : MyPetsitterList
  },
  {
    path: '/chat',
    name: 'Chat',
    component : Chat
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 최종 배포 전에 주석 풀기
// router.beforeEach((to, from, next) => {
//   if (to.name !== 'SignIn' && to.name !== 'SignUp' && !localStorage.getItem('token')) {
//     next({ name: 'SignIn' }); 
//   }
//   else if ( to.name == 'SignIn' && localStorage.getItem('token')){
//     next({ name: 'PetBTI'});
//   }
//   else if ( to.name == 'SignUp' && localStorage.getItem('token')){
//     next({ name: 'PetBTI'});
//   }

//   else if (to.name == 'SignUp' && from.name == 'SignIn') {
//     next();
//   }
  
//   else next()
// })
export default router
