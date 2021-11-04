import Vue from 'vue'
import Vuex from 'vuex'

import axios from '../axios/index'
import router from '../router/index'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo:{
      userid:null,
      profileImg:null,
      nickname:null,
      type:null,
      introduce:null,
    },
    page:0,
    questions:[
      {
        q:'오랜만에 찾아온 휴일, 어떻게 보낼까?',
        a:[
          {text:'집에서 쉬는게 최고야! 넷플릭스 정주행 해야지!~', value:'cat'},
          {text:'무조껀 밖에 나가서 놀아야지!', value:'dog'}
        ]
      },
      {
        q:'처음 만난 모임 자리, 내 행동은?',
        a:[
          {text:'먼저 말을 걸어줄때까지 기다린다', value:'i'},
          {text:'어색한건 못참아! 내가먼저 다가간다', value:'e'}
        ]
      },
      {
        q:'야근하고 돌아온 당신, 갑자기 친구에게서 힘들다며 연락이 왔다.',
        a:[
          {text:'아무리 힘들어도 친구의 고민이 우선! 당장 약속을 잡는다.', value:'a'},
          {text:'친구야 미안해 오늘은 나도 피곤하다ㅠㅠ 다음에 만난다.', value:'c'}
        ]
      },
      {
        q:'체육대회날 나는?',
        a:[
          {text:'모든 종목에 다 참가한다. 1등은 내꺼!', value:'a'},
          {text:'나는 응원이 좋아..', value:'c'}
        ]
      },
      {
        q:'친구와의 약속을 위해 준비를 다 마친 당신, 출발 직전에 친구에게서 취소하자는 연략이 왔다!',
        a:[
          {text:'뭘 할지는 모르겠지만, 기왕 준비한거 나가본다', value:'a'},
          {text:'아싸! 쉬고 싶었는데 잘 됐다! 라고 생각하고 집에서 쉰다', value:'c'}
        ]
      },
      {
        q:'팀프로젝트 회의 첫날, 팀장을 정해야 하는 상황에서 나는?',
        a:[
          {text:'팀장 좋아! 제가할께요~ 자발적으로 팀장을 지원한다', value:'i'},
          {text:'팀장만은 피할래.. 다른 사람이 지원할때 까지 기다린다', value:'e'}
        ]
      },
      {
        q:'시험기간이 얼마 남지 않았다. 나는 공부할때?',
        a:[
          {text:'혼자 공부하는게 좋아', value:'i'},
          {text:'난 친구들이랑 같이 스터디하는게 좋아!', value:'e'}
        ]
      },
    ],
    result:{'cat':0,'dog':0,'a':0,'c':0,'i':0,'e':0}
  },
  mutations: {
    SET_USER_TYPE(state,payload){
      state.result[payload] += 1;
      state.page += 1
    },
    RESET_PAGE(state){
      state.page = 0;
      state.result = {'cat':0,'dog':0,'a':0,'c':0,'i':0,'e':0};
    },
    SET_MY_TYPE(state,payload){
      state.userInfo.type = payload;
    },
    SET_USER_INFO(state,payload){
      state.userInfo.nickname = payload.nickname;
      state.userInfo.thumbnamil = payload.thumbnamil;
      state.userInfo.introduce = payload.introduce;
      state.userInfo.userid = payload.userid;
    }
  },
  actions: {
    requestSignIn(state,payload){
      axios({
        url:'/users/login',
        method:'post',
        data:payload
      })
      .then(res=>{
        if (res.data.statusCode == '200'){
          const token = res.data.token
          localStorage.setItem("token",token);
          router.push({name:'PetBTI'});
          const user = payload.get('userId');
          localStorage.setItem('userid',user);
          this.dispatch('requestUser',user);
        }
        else {

          alert(res.data.message);
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
        router.push({name:'SignIn'})
      })
      .catch(err=>{
        console.log(err)
      })
    },
    nickCheck(state,payload){
      return axios({
        url: '/users/checkNICK',
        method:'get',
        params:{
          nickname: payload
        }
      })
    },
    idCheck(state,payload){
      return axios({
        url: '/users/checkID',
        method:'get',
        params:{
          userId: payload
        }
      })
    },
    requestUser(state,payload){

      axios({
        url:`/users/search/${payload}`,
        method:'get',
      })
        .then(res=>{
          const data = {
            nickname: res.data.nickname,
            thumbnamil: res.data.thumbnamil,
            introduce: res.data.introduce,
            userid: res.data.userId
          }
          this.commit('SET_USER_INFO',data);
        })
        .catch(err=>{
          console.log(err);
        })
    },
    decideAns({commit},payload){
      commit('SET_USER_TYPE',payload)
    },
    detailSBoard(state,payload){
      return axios({
        url:`/sns/${payload}`,
        method:'get'
      })
    },
    bringSBoardComments(state,payload){
      return axios({
        url:`/scomments/${payload}`,
        method:'get'
      })
    },
    requestSComment(state,payload){
      axios({
        url:`/scomments/${payload.sboardid}`,
        method:'post',
        data:payload,
      })
      .then(res=>{
        console.log(res);
        this.dispatch('bringSBoardComments',payload.sboardid)
      })
    }
    // bringWrites(state,payload){
    //   return axios({
    //     url:'/sns/'
    //   })
    // }

  },
  modules: {
  }
})
