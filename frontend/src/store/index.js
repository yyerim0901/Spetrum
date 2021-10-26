import Vue from 'vue'
import Vuex from 'vuex'
import request from '../axios/index'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
    requestSignIn({state},payload){
      const response = request('post','/user/signin',payload)
      if (response.statusCode == '200'){
        const token = response.accessToken
        localStorage.setItem("token",token);
        // this.$router.push({name:''})
      }
      else {
        alert('로그인 실패');
      }
    },

  },
  modules: {
  }
})
