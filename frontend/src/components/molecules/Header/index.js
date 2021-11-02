import styled from 'vue-styled-components'
import theme from '../../../styles/common'

const HeadProps = {
  isBack: Boolean,
  isLogo: Boolean,
  title: String,
}

export const Head = styled.div`
  height: 50px;
  width:100%;
  text-align:center;
  display:flex;
  flex-direction: column-reverse;
`;

export const Container = styled('div',HeadProps)`
  position: fixed;
  z-index:9999;
  top:0;
  height: 50px;
  display:flex;
  width: 500px;
  align-items:center;
  justify-content: start;
  background-color: white;

  i {
    display: ${(props) => (props.isBack ? "block" : "none")};
    font-size: ${theme.fontSizes.xxl};
    font-weight: bold;
    margin:0 10px;
  };

  `
  
  export const LogoTitle = styled('div',HeadProps)`
  width : 150px;
  text-align: center;
  width: 300px;

  img{
    width:110px;
    display: ${(props) => (props.isLogo ? "block" : "none")};
    margin:0 170px ;

  }
  p {
    display: ${(props) => (props.isLogo ? "none" : "block")};
    font-size: ${theme.fontSizes.xxl};
    color: ${theme.colors.black};
    font-weight: bold;
    margin:0 0px 0 165px ;

  }
`;