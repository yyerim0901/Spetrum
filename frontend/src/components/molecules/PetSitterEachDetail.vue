<template>
    <div class="PET-Wrapper">
        <Header :isLogo="false" :isBack="false" title="겟!시터"/>
        <hr>
        <div class="detail-box">
            <img class="detail-img-box" :src="fullURL(board.data.picture)" alt="이미지가 없습니다">
            <div>
                <span>{{ board.data.title }}</span>
                <i class="fas fa-edit" style="margin:0 0 0 10px;" @click="moveChangePetSitter"></i>
                <i class="fas fa-trash" style="margin:0 0 0 10px;" @click="deletePetSitter"></i>
            </div>
            <p style="width: 362px; text-align:right;">작성일자 : {{ board.data.created.substr(0,10) }}</p>
            <span style="text-align:start; border:solid; border-width:1px 0; border-color:#E5EAEF;">{{board.data.content}}</span>
            <p style="width: 362px; font-size:0.8rem;">{{ board.data.content }}</p>

            <PetSitterCommentDetail v-for="(comment, idx) in comments" :key="idx" :comment="comment" />
            <input placeholder="댓글을 입력해주세요" type="text" v-model="inputContent">
            <button @click="createComment">댓글작성</button>
            <div>
                <i class="far fa-comment-dots" style="margin:0 5px 0 0; font-size:1rem"></i>
                <CommentInput :value="comment" v-model="inputContent" />
                <StyledButton btype="realsmall" bcolor="babypink" @click="createComment()">작성</StyledButton>
            </div>
        </div>
        <Footer :isActive="isActive"/>
        <hr class="fott">
    </div>
</template>

<script>
import Header from '../../components/molecules/Header';
import Footer from '../../components/molecules/Footer';
import axios from '../../axios/index';
import PetSitterCommentDetail from '../../components/molecules/PetSitterCommentDetail';
export default {
    name: "PetSitterEachDetail",
    components: {
        Header,
        Footer,
        PetSitterCommentDetail,
    },
    data() {
        return {
            boardId: this.$route.params.board_id,
            board: {},
            comments: {},
            isActive: 3,
            inputContent: '',
            BASE_URL : 'https://spetrum.io/resources/',
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
            const full = this.BASE_URL + url;
            return full;
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
        width: 362px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .detail-img-box{
        width: 362px;
        height: 362px;
        align-content: center;
    }
</style>