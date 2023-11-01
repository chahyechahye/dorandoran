import styled from "styled-components";
import background from "@/assets/img/background/background.jpg";
import logoImageSrc from "@/assets/img/logo/logo.png";
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

const LogoImage = styled.img`
  width: 80vh;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 2vh;
`;

const InviteCode = styled.input`
  width: 33vh;
  height: 6.5vh;
  background-color: white;
  border-radius: 5vh;
  border-color: transparent;
  box-shadow: 0 0 1vh rgba(0, 0, 0, 0.1);
  &::placeholder {
    padding-left: 3vh;
    font-size: 2vh;
    font-family: Katuri;
    color: #999999;
  }
`;

const ChildrenLoginPage = () => {
  const navigate = useNavigate();

  const goMain = () => {
    navigate("/children/profile");
  };

  return (
    <Background>
      <ContentContainer>
        <LogoImage src={logoImageSrc} />
        <InviteCode placeholder="초대코드"></InviteCode>
        <ClickButton
          width="33vh"
          height="6.5vh"
          backgroundColor="#EB9F4A"
          fontColor="white"
          fontSize="3vh"
          text="모험을 떠나요"
          onClick={goMain}
        ></ClickButton>
      </ContentContainer>
    </Background>
  );
};

export default ChildrenLoginPage;
