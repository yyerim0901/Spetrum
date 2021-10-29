import styled from 'vue-styled-components'
import theme from '../../styles/common'


const FooterButton = styled.button`
  height: 50px;
  color: ${theme.colors.white};
  background-color: ${theme.colors.babypink};
  width: 500px;
  font-size: ${theme.fontSizes.xxl};
  display: block;
  position: fixed;
  bottom: 0;

`

export default FooterButton;