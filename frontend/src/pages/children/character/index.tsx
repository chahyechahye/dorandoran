import styled from "styled-components";
import background from "@/assets/img/background/background.jpg";
import CharacterPage from "@/pages/common/character";
import ClickButton from "@/components/clickButton";
import { useNavigate } from "react-router-dom";

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

const CharacterModal = styled.div``;

const ModalBlack = styled.div`
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
`;

const ClickButtonContainer = styled.div`
  position: relative;
  z-index: 1;
  margin-top: 7vh;
`;

const ChildrenCharacterPage = () => {
  const navigate = useNavigate();

  const goMain = () => {
    navigate("/children/main");
  };

  return (
    <Background>
      <ContentContainer>
        <CharacterModal>
          <ModalBlack />
          <CharacterPage />

          <ClickButtonContainer>
            <ClickButton
              width="70vh"
              height="9vh"
              backgroundColor="#FC7292"
              fontColor="white"
              fontSize="4.5vh"
              text="이 친구랑 놀래요!"
              onClick={goMain}
            ></ClickButton>
          </ClickButtonContainer>
        </CharacterModal>
      </ContentContainer>
    </Background>
  );
};

export default ChildrenCharacterPage;
