import styled from 'vue-styled-components'
// import theme from '../../../styles/common'

// const FooterProps = {
//   isClicked:Number,
// }

export const Foot = styled.div`
  width: 100%;
  height: 60px;
`;

export const Container = styled.div`
  position: fixed;
  z-index:9999;
  bottom:0;
  height: 60px;
  display:flex;
  align-items:center;
  justify-content: center;
  background-color: white;
  margin: 0 10px;
  img{
    width:30px;
  }

`