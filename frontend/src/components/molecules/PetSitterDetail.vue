<template>
    <div class="sitter-detail-box">
        <img class="img-box" :src="fullURL(board.picture)" alt="이미지가 없습니다">
        <div class="board-box" @click="moveEachDetail">
            <p>{{ board.title }}</p>
            <p>{{ board.created }}</p>
            <p>{{ board.user }}</p>
        </div>
        <StyledButton bcolor="pink" btype="small" @click="deletePetSitter">삭제하기</StyledButton>
    </div>
</template>

<script>
import StyledButton from '../../components/atoms/StyledButton'
import axios from '../../axios/index'
export default {
    name: "PetSitterDetail",
    components: {
        StyledButton
    },
    data () {
        return{
            BASE_URL : 'https://spetrum.io/resources/',
        }
    },
    props: {
        board: {
            type:Object,
        }
    },
    methods: {
        moveEachDetail() {
            this.$router.push({
                name: "PetSitterEachDetail",
                params: {
                    board_id: this.board.id,
                }
            })
        },
        deletePetSitter() {
            axios({
                method: "DELETE",
                url: "/pboard/",
                params: {
                    petSitterId : this.board.id
                }
            }).then(res => {
                console.log(res.data)
            }).catch(err => {
                console.log(err)
            })
        },
        fullURL(url){
            const full = this.BASE_URL + url;
            return full;
        },        
    }

}
</script>

<style>
    .sitter-detail-box{
        display: flex;
    }
    .img-box{
        display: inline;
        margin-top: 1rem;
        width: 90px;
        height: 90px;
    }
    .board-box{
        display: inline;
        margin-top: 1rem;
    }
</style>