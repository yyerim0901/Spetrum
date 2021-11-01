import styled from 'vue-styled-components'
import theme from '../../styles/common'

const InputProps = {
  value:String,

}

const StyledInput = styled('input', InputProps)`
  outline: none;
  font-size: ${theme.fontSizes.md};
  height: 40px;
  padding: 0 10px;
  width: 100%;
  background-color: white;
  border-color: ${theme.colors.Gray};
  border-width: 0 0 1px;
  value: ${props => props.value};
  color: ${theme.colors.darkGray};
`

export default StyledInput;