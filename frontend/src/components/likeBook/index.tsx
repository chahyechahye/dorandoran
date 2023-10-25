import styled from "styled-components";
import likeBook from "@/assets/img/likeBook.png";

const Container = styled.div`
  padding: 1.2vh;
  margin: 30px;
  background-color: #fff;
  border-radius: 100px;
`;

const StyledImage = styled.img`
  width: 6vh;
`;

const LikeBook = () => {
  return (
    <Container>
      <StyledImage src={likeBook} alt="Background" />
    </Container>
  );
};

export default LikeBook;
