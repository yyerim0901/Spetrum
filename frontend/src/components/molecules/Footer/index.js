import styled from 'vue-styled-components'
import theme from '../../../styles/common'

export const Foot = styled.div`
  height: 50px;
  width:100%;
  text-align:center;
  display:flex;
  flex-direction: column-reverse;
  position:absolute;
  bottom:0;
`;

export const Container = styled.div`
  position:absolute;
  bottom:0;
  width:500px;
  display:flex;
  justify-content: center;
  justify-items:center;
  align-itmes: center;
  background-color: white;
  padding-top:7px;
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