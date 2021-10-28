import styled from 'vue-styled-components'
import theme from '../../styles/common'

const MessageProps = {
  message:String,
}
const ErrorMessage = styled('div',MessageProps)`
  font-size: ${theme.fontSizes.xs};
  color: red;
  height: ${theme.fontSizes.lg};
  display: ${(props) => (props.message ? "block" : "none")};
`

export default ErrorMessage;