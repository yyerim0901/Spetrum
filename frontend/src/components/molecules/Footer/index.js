import styled from 'vue-styled-components'
import theme from '../../../styles/common'

export const Container = styled.div`
  position: fixed;
  z-index:9999;
  bottom:0;
  height: 60px;
  width:500px;
  display:flex;
  justify-content: center;
  justify-items:center;
  align-itmes: center;
  background-color: white;
  margin:10px 10px 0px 10px;
  padding-top:1px;
  img{
    width:30px;
  }
  border-top:1px solid rgb(172,172,172);
  i{
    font-size: 1.75rem;
    margin: 0 0 5px 0;
  }
`

export const TabButton = styled.button`
  width: 90px;
  padding: 0 0 0 0px;

  p {
    font-weight: bold;
    font-size: ${theme.fontSizes.sm};
  }; 
  .active {
    color: ${theme.colors.pink};
  }
`