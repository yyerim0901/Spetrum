import styled from 'vue-styled-components'
import theme from '../../styles/common'


const CommentInput = styled.input`
  outline: none;
  font-size: ${theme.fontSizes.sm};
  height: 30px;
  padding: 0 20px;
  width: 310px;
  background-color:${theme.colors.strawberry};
  border-radius:6px;
  border:none;
  placeholder:'댓글을 입력해주세요!';
  color: ${theme.colors.darkGray};
  margin:10px 2px 0 0;
`

export default CommentInput;