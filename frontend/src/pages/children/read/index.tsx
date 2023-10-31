import styled from "styled-components";
import background from "@/assets/img/bookview.png";
import DogLike from "@/components/dog";

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

const ChildrenProfilePage = () => {
  return (
    <Background>
      <ContentContainer>
        <TextContainer>
          <TextBox>거북이와 토끼가 경주를 시작했어요!</TextBox>
          <DogLike />
        </TextContainer>
      </ContentContainer>
    </Background>
  );
};

export default ChildrenProfilePage;
