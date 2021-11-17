<template>
  <div class="Dogging-Wrapper">
      <Header :isLogo="false" :isBack="false" title="도깅"></Header>
      <h3>오늘의 도깅</h3>
      <StyledButton btype="medium" bcolor="babypink">공유하개</StyledButton>
      <StyledButton btype="medium" bcolor="babypink">저장하개</StyledButton>
  </div>
</template>

<script>
import Header from '../components/molecules/Header.vue'
import StyledButton from '../components/atoms/StyledButton'
export default {
    name: "TodayDogging",
    components: {
        Header,
        StyledButton,
    }
}
</script>

<style>

</style>