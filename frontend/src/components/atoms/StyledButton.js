import styled from 'vue-styled-components'
import theme from '../../styles/common'

const buttonProps = {
  bcolor: String,
  btype: String,
}

const sizes = {
  large: {
    width: "100%",
    height:"50px",
    size:"1rem",
  },
  medium : {
    width: "140px",
    height:"40px",
    size : "0.875rem",
  },
  small : {
    width:"40px",
    height:"30px",
    size : "0.625rem"
  }
}
const StyledButton = styled('button', buttonProps)`
  height: ${sizes.large.height};
  color: ${theme.colors.white};
  border-radius: 6px;
  background-color: ${(props) => theme.colors[props.bcolor]};
  width: ${(props) => sizes[props.btype].width};
  height: ${(props) => sizes[props.btype].height};
  font-size: ${(props) => sizes[props.btype].size};
  margin: 10px 0 0 0;

`

export default StyledButton;