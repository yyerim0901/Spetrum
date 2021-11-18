<template>
  <div style="display: flex; justify-content: flex-start; font-size: 13.3px; width:420px;">
      <p style="font-size: 13.3px;">{{ comment.content }} - {{ comment.user.userId }}</p>
        <StyledButton v-if="comment.user.userId != this.userid" btype="realsmall" bcolor="babypink" @click="chatstart(comment.user.userId)">채팅</StyledButton>
      <input v-if="commentFlag" type="text" :placeholder=comment.content v-model="changedComment">
      <div v-if="comment.user.userId == this.userid">
        <StyleButton v-if="!commentFlag" @click="deleteComment">&nbsp;삭제&nbsp;</StyleButton>|
        <StyleButton v-if="!commentFlag" @click="changFlag">&nbsp;수정</StyleButton>
        <StyleButton v-if="commentFlag" @click="changeComment">&nbsp;수정완료</StyleButton>
      </div>
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