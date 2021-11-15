<template>
    <div class="sitter-detail-box">
        <img class="img-box" :src="fullURL(board.picture)" alt="이미지가 없습니다">
        <div class="board-box" @click="moveEachDetail">
            <p>{{ board.title }}</p>
            <p>{{ board.created }}</p>
            <p>{{ board.user.userId }}</p>
        </div>
    </div>
</template>

<script>
export default {
    name: "PetSitterDetail",
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