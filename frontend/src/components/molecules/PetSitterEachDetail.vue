<template>
    <div class="PET-Wrapper">
        <Header :isLogo="false" :isBack="true" title="겟!시터"/>
        <div class="detail-box">
            <img class="detail-img-box" :src="fullURL(board.data.picture)" alt="이미지가 없습니다">
            <div style="width: 420px; padding:5px;">
                <span style="font-weight: bolder; font-size:20px;">{{ board.data.title }}</span>
                <span v-if="board.data.user.userId == this.userid">
                    <i class="fas fa-edit" style="margin:5px 0 0 10px; float:right;" @click="moveChangePetSitter"></i>
                    <i class="fas fa-trash" style="margin:5px 0 0 10px; float:right;" @click="checkdelete"></i>
                </span>
                <div>
                    <p @click="chatting()" style="width: 420px; text-align:right;">작성일자 : {{ board.data.created.substr(0,10) }}<span style="color:#FFDDE1;"> | </span>작성자 : {{ board.data.user.userId }} </p>
                    <!-- <p style="width: 420px; text-align:right;">작성일자 : {{ board.data.created.substr(0,10) }}</p> -->
                </div>
            </div>
            <p @click="chatting()" style="width: 420px; text-align:right;">작성자 : {{ board.data.user.userId }}</p>
            <p style="width: 420px; text-align:right;">작성일자 : {{ board.data.created.substr(0,10) }}</p>
            <p style="text-align:start; border:solid; border-width:1px 0; border-color:#E5EAEF; width: 420px; height: 120px;">{{ board.data.content }}</p>

            <PetSitterCommentDetail v-for="(comment, idx) in comments" :key="idx" :comment="comment" />
            <div>
                <i class="far fa-comment-dots" style="margin:0 5px 0 0; font-size:1rem"></i>
                <CommentInput :value="comment" v-model="inputContent" />
                <StyledButton btype="realsmall" bcolor="babypink" @click="createComment()">작성</StyledButton>
            </div>
        </div>
        <Modal v-if="showModal" @close="showModal=false" @ok="deletePetSitter">
          <h3 slot="header">
            게시글을 정말 삭제하시겠냥?
          </h3>
          <div slot="body"></div>
        </Modal>
        <Footer :isActive="isActive"/>
    </div>
</template>

<script>
import Header from '../../components/molecules/Header';
import Footer from '../../components/molecules/Footer';
import axios from '../../axios/index';
import PetSitterCommentDetail from '../../components/molecules/PetSitterCommentDetail';
import CommentInput from '../atoms/CommentInput'
import StyledButton from '../atoms/StyledButton'
import Modal from './Modal.vue'

export default {
    name: "PetSitterEachDetail",
    components: {
        Header,
        Footer,
        PetSitterCommentDetail,
        CommentInput,
        StyledButton,
        Modal
    },
    data() {
        return {
            userid: localStorage.getItem('userid'),
            boardId: this.$route.params.board_id,
            board: {},
            comments: {},
            isActive: 3,
            inputContent: '',
            BASE_URL : 'https://spetrum.io/resources/',
            myid:"",
            showModal:false,
        }
    },
    methods: {
        getPetSitterDetail() {
            axios({
                method: "GET",
                url: `/pboard/detail/${this.boardId}`,
                headers: {
                    "Authorization": localStorage.getItem("token")
                },
            }).then(res => {
                this.board = res.data
                console.log(res);
            }).catch(err => {
                console.log(err)
            })
        },
        getComments() {
            axios({
                method: "GET",
                url: '/pcomment',
                headers: {
                    "Authorization": localStorage.getItem("token")
                },
                params: {
                    pboardId: this.boardId
                }
            }).then(res => {
                // console.log(res.data.data), data.data가 object 이게 하나씩 쌓여서 들어옴 array에
                this.comments = res.data.data
            }).catch(err => {
                console.log(err)
            })
        },
        createComment() {
            if (this.inputContent){
                axios({
                    method: "POST",
                    url: '/pcomment/',
                    headers: {
                        "Authorization": localStorage.getItem("token")
                    },
                    params: {
                        content : this.inputContent,
                        pboardId : this.boardId
                    }
                }).then(res => {
                    console.log(res.data)
                    this.$router.go();
                }).catch(err => {
                    console.log(err)
                })
            } else{
                alert('댓글을 입력해주세요')
            }
        },
        fullURL(url){
            if (url !== null){
                var full = this.BASE_URL + url;
            } else{
                full = require('@/assets/noimage.png')
            }
            return full;
            
        },
        checkdelete(){
            this.showModal = true;
        },
        deletePetSitter() {
            axios({
                method: "DELETE",
                url: "/pboard/",
                params: {
                    petSitterId : this.boardId
                }
            }).then(res => {
                console.log(res.data)
                this.$router.push({name:'PetSitter'})
            }).catch(err => {
                console.log(err)
            })
        },
        moveChangePetSitter() {
            this.$router.push({
                name: "ChangePetSitter",
                params: {
                    board_id: this.boardId,
                }
            })
        },
        chatting()
        {
            if(this.userid != this.board.data.user.userId)
            {
                axios({
                    method: "GET",
                    url: '/users/me',
                    headers: {
                        "Authorization": localStorage.getItem("token")
                    },
                }).then(res => {
                    this.myid = res.data.user.userId;

                    this.$router.push({
                    name: "Chat",
                    params: {
                        roomname: this.board.data.user.userId+"_"+this.myid,
                    }
                })
                })
            }
        }
    },
    created() {
        this.getPetSitterDetail()
        this.getComments()
    }
}
</script>

<style>
    .detail-box{
        width: 100%;
        margin-top : 20px;
        margin-bottom: 50px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .detail-img-box{
        width: 420px;
        height: 420px;
        align-content: center;
        /* font-size: ; */
    }
</style>