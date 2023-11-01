import styled from "styled-components";
import replayImg from "@/assets/img/fairytale/replay.png";
import bookImg from "@/assets/img/fairytale/book.png";

const Background = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 60%);
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 5;
`;

const ContentContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

const ImgContainer = styled.img`
  margin: 5vh 10vh;
  height: 30vh;
  width: 60%;
`;

const TextContainer = styled.div`
  font-size: 7.5vh;
  color: white;
  text-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5);
`;

const IconContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const ReplayModal = () => {
  return (
    <Background>
      <ContentContainer>
        <IconContainer>
          <ImgContainer src={replayImg} />
          <TextContainer>다시 볼래요</TextContainer>
        </IconContainer>
        <IconContainer>
          <ImgContainer src={bookImg} />
          <TextContainer>다른 책 볼래요</TextContainer>
        </IconContainer>
      </ContentContainer>
    </Background>
  );
};

export default ReplayModal;
