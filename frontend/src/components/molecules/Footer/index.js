import styled from 'vue-styled-components'
import theme from '../../../styles/common'

export const Container = styled.div`
  position: fixed;
  z-index:9999;
  bottom:0;
  height: 60px;
  display:flex;
  justify-content: center;
  align-itmes: center;
  background-color: white;
  margin:0 10px;
  img{
    width:30px;
  }

  i{
    font-size: 1.75rem;
    margin: 0 0 5px 0;
  }
`

export const TabButton = styled.button`
  width: 90px;
  padding: 0 0 0 10px;

  p {
    font-weight: bold;
    font-size: ${theme.fontSizes.sm};
  }; 
  .active {
    color: ${theme.colors.pink};
  }
`