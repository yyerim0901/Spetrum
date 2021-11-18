<template>
  <div style="justify-content:space-between; font-size: 13.3px; padding:6px;margin-right:7px;">
      <span style="font-size: 13.3px;"><span style="background-color:#EE9CA7; border-radius:5px; padding:2px; color:white; font-weight:bold;">{{ comment.user.userId }}</span>&nbsp;&nbsp;<span>{{ comment.content }}</span></span>
        <StyledButton v-if="comment.user.userId != this.userid" btype="realsmall" bcolor="babypink" @click="chatstart(comment.user.userId)">채팅</StyledButton>
      <input v-if="commentFlag" type="text" :placeholder=comment.content v-model="changedComment">
      <span style="float:right;" v-if="comment.user.userId == this.userid">
        <StyleButton style="" v-if="!commentFlag" @click="deleteComment">&nbsp;삭제&nbsp;</StyleButton>
        <StyleButton v-if="!commentFlag" @click="changFlag">&nbsp;수정</StyleButton>
        <StyleButton v-if="commentFlag" @click="changeComment">&nbsp;수정완료</StyleButton>
      </span>
  </div>
</template>

<script>
// import { use } from 'vue/types/umd';
import axios from '../../axios/index';
import StyledButton from '../atoms/StyledButton'
export default {
    data() {
        return {
            userid: localStorage.getItem('userid'),
            commentFlag: false,
            changedComment: '',
        }
    },
    components: {
        StyledButton
    },
    props: {
        comment: {
            type:Object,
        }
    },
    methods: {
        deleteComment() {
            axios({
                method: "DELETE",
                url: '/pcomment',
                headers: {
                    "Authorization": localStorage.getItem("toekn")
                },
                params: {
                    commentId: this.comment.id
                }
            }).then(res => {
                console.log(res)
                this.$router.go();
            }).catch(err => {
                console.log(err)
            })
        },
        changFlag() {
            this.commentFlag = true
        },
        changeComment() {
            axios({
                method: "PUT",
                url: '/pcomment',
                headers: {
                    "Authorization": localStorage.getItem("token")
                },
                params: {
                    PCommentId: this.comment.id,
                    content: this.changedComment,
                }
            }).then(res => {
                console.log(res)
                this.$router.go();
            }).catch(err => {
                console.log(err)
            })
        },
        chatstart(userid)
        {
            if(this.userid != userid)
            {
                console.log(userid);
                this.$router.push({
                    name: "Chat",
                    params: {
                        roomname: this.userid+"_"+userid,
                    }
                })
            }
        }
    }

}
</script>

<style>

</style>