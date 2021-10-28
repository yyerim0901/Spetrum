import styled from 'vue-styled-components'
import theme from '../../../styles/common'

const HeadProps = {
  isBack: Boolean,
  isLogo: Boolean,
  title: String,
}

export const Head = styled.div`
  width: 100%;
  height: 60px;
  text-align:center;
  display:flex;
  flex-direction: column-reverse;

`;

export const Container = styled('div',HeadProps)`
  position: fixed;
  z-index:9999;
  top:0;
  height: 60px;
  display:flex;
  align-items:center;
  justify-content: center;
  background-color: white;
  margin: 0 10px;

  i {
    display: ${(props) => (props.isBack ? "block" : "none")};
    font-size: ${theme.fontSizes.xxl};
    font-weight: bold;

  };
  
  `
  
  export const LogoTitle = styled('div',HeadProps)`
  width : 150px;
  text-align: center;
  width: 300px;

  img{
    width:110px;
    display: ${(props) => (props.isLogo ? "block" : "none")};
    margin: 0 0 0 120px;
  }
  p {
    display: ${(props) => (props.isLogo ? "none" : "block")};
    font-size: ${theme.fontSizes.xxl};
    font-weight: bold;
  }
`;