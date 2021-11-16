import styled from 'vue-styled-components'
import theme from '../../../styles/common'

const HeadProps = {
  isBack: Boolean,
  isLogo: Boolean,
  isSearch:Boolean,
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
  justify-content: space-between;
  background-color: white;
  border-bottom : 1px solid rgb(172,172,172);

  .ii {
    display: ${(props) => (props.isBack ? "block" : "none")};
    font-size: ${theme.fontSizes.xxl};
    font-weight: bold;
    margin:0 10px;
  };
  .iii{
    display: ${(props) => (props.isSearch ? "block" : "none")};
    font-size: ${theme.fontSizes.xxl};
    font-weight: bold;
    margin:0 10px;
  }

  `
  
  export const LogoTitle = styled('div',HeadProps)`
  text-align: center;
  width: 300px;

  img{
    width:110px;
    display: ${(props) => (props.isLogo ? "block" : "none")};
    margin:0 80px ;

  }
  p {
    display: ${(props) => (props.isLogo ? "none" : "block")};
    font-size: ${theme.fontSizes.xl};
    color: ${theme.colors.black};
    font-weight: bold;
  }
`;