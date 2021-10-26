import styled from 'vue-styled-components'
import theme from '../../styles/common'


const StyledLabel = styled.label`
  display:block;
  color : ${theme.colors.darkGray};
  font-size: ${theme.fontSizes.md};
  padding: 3px 0;
`

export default StyledLabel;