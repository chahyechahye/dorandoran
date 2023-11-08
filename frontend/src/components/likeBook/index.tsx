import styled from "styled-components";
import likeBook from "@/assets/img/likeBook.png";
import { ButtonEffect } from "@/styles/buttonEffect";

const Container = styled.div`
  padding: 1.2vh;
  margin: 30px;
  background-color: #fff;
  border-radius: 100px;

  ${ButtonEffect}
`;

const StyledImage = styled.img`
  width: 6vh;
`;

const LikeBook = ({ onClick }: { onClick?: () => void }) => {
  return (
    <Container onClick={onClick}>
      <StyledImage src={likeBook} alt="Background" />
    </Container>
  );
};

export default LikeBook;
