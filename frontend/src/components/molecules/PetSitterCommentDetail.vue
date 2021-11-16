<template>
  <div style="display: flex; justify-content: flex-end; font-size: 13.3px;">
      <p style="font-size: 13.3px;">{{ comment.content }} - {{ comment.user.userId }}</p>
      <input v-if="commentFlag" type="text" :placeholder=comment.content v-model="changedComment">
      <div v-if="comment.user.userId == this.userid">
        <button v-if="!commentFlag" @click="deleteComment">&nbsp;삭제&nbsp;</button>|
        <button v-if="!commentFlag" @click="changFlag">&nbsp;수정</button>
        <button v-if="commentFlag" @click="changeComment">수정완료</button>
      </div>
  </div>
</template>

<script>
import axios from '../../axios/index';
export default {
    data() {
        return {
            userid: localStorage.getItem('userid'),
            commentFlag: false,
            changedComment: '',
        }
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
        }
    }

}
</script>

<style>

</style>