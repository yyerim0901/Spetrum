<template>
  <div>
      <h4>댓글!!</h4>
      <p>{{ comment.content }}</p>
      <input v-if="commentFlag" type="text" :placeholder=comment.content v-model="changedComment">
      <button v-if="!commentFlag" @click="deleteComment">삭제</button>
      <button v-if="!commentFlag" @click="changFlag">수정</button>
      <button v-if="commentFlag" @click="changeComment">수정완료</button>
  </div>
</template>

<script>
import axios from '../../axios/index';
export default {
    data() {
        return {
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