import styled from "styled-components";
import background from "@/assets/img/bookview.png";
import arrowLeft from "@/assets/img/fairytale/arrowLeft.png";
import arrowRight from "@/assets/img/fairytale/arrowRight.png";

const Background = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url(${background});
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const TextContainer = styled.div`
  position: absolute;
  bottom: 5vh;
  text-align: center;
`;

const TextBox = styled.div`
  font-size: 7.5vh;
  color: white;
  -webkit-text-stroke: 0.02em black;
  text-shadow: 0.04em 0.04em 0.04em rgba(0, 0, 0, 0.4);
`;

const ArrowBox = styled.div`
  position: absolute;
  top: 42%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 3%;
`;

const ArrowLeft = styled.img``;

const ArrowRight = styled.img``;

const ChildrenProfilePage = () => {
  return (
    <Background>
      <ContentContainer>
        <ArrowBox>
          <ArrowLeft src={arrowLeft} />
          <ArrowRight src={arrowRight} />
        </ArrowBox>
        <TextContainer>
          <TextBox>거북이와 토끼가 경주를 시작했어요!</TextBox>
        </TextContainer>
      </ContentContainer>
    </Background>
  );
};

export default ChildrenProfilePage;
