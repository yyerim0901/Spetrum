<template>
    <div class="sitter-detail-box">
        <img class="img-box" :src="fullURL(board.picture)" alt="이미지가 없습니다">
        <div class="board-box" @click="moveEachDetail">
            <p style="font-size:1rem; font-weight: bold;">{{ board.title }}</p>
            <p style="font-size:0.8rem;">작성일 {{ board.created.substr(0,10) }}</p>
            <p style="font-size:0.8rem;">작성자 : {{ board.user.userId }}</p>
        </div>
    </div>
</template>

<script>


export default {
    name: "PetSitterDetail",
    data () {
        return{
            BASE_URL : 'https://spetrum.ddns.net/resources/',
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
        if (url !== null){
                var full = this.BASE_URL + url;
            } else{
                full = require('@/assets/noimage.png')
            }
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
        width: 100px;
        height: 100px;
    }
    .board-box{
        display: inline;
        margin-top: 1rem;
        margin-left: 1rem;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
</style>