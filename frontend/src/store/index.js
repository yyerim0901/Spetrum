import Vue from 'vue'
import Vuex from 'vuex'

import axios from '../axios/index'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
    requestSignIn(state,payload){
      // console.log({state});
      console.log(typeof(payload.userId));
      axios({
        url:'/users',
        method:'post',
        data:payload
      })
      .then(res=>{
        if (res.data.statusCode == '200'){
          const token = res.data.accesstoken
          localStorage.setItem("token",token);
        }
        else {
          console.log(res);
          alert('로그인 실패!');
        }

      })


    },
    requestSignup(state,payload){
      axios({
        url: '/users/regist',
        method:'post',
        data:payload
      })
      .then(res=>{
        console.log(res);
        this.$router.push({name:'SignIn'})
      })
      .catch(err=>{
        console.log(err)
      })
    }

  },
  modules: {
  }
})
