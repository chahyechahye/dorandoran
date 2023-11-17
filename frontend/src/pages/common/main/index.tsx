import styled, { keyframes } from "styled-components";
import background from "@/assets/img/background/background.jpg";
import logoImageSrc from "@/assets/img/logo/logo.png";
import LoginBox from "@/components/loginBox";
import child from "@/assets/img/login/child.png";
import parent from "@/assets/img/login/parent.png";
import { useNavigate } from "react-router-dom";

const fadeIn = keyframes`
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
    visibility: visible;
  }
`;

const fadeInAndSlideDown = keyframes`
  from {
    opacity: 0;
    transform: translateY(5vh);
  }
  to {
    opacity: 1;
    transform: translateY(-19vh);
    visibility: visible;
  }
`;

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
  animation: ${fadeInAndSlideDown} 1s ease 1.2s forwards;
  visibility: hidden;
  z-index: 2;
`;

const SelectContainer = styled.div`
  display: flex;
  gap: 3vh;
  animation: ${fadeIn} 1s ease 2s forwards;
  visibility: hidden;
  position: absolute;
  justify-content: center;
  top: 50vh;
`;

const ContentContainer = styled.div``;

const MainPage = () => {
  const navigate = useNavigate();

  const goChild = () => {
    setTimeout(() => {
      navigate("/children/login");
    }, 900);
  };

  const goParent = () => {
    navigate("/parent/login");
  };

  return (
    <Background>
      <ContentContainer>
        <LogoImage src={logoImageSrc} />
        <SelectContainer>
          <LoginBox
            imgLink={child}
            text="아이"
            onClick={() => goChild()}
          ></LoginBox>
          <LoginBox
            imgLink={parent}
            text="보호자"
            onClick={() => goParent()}
          ></LoginBox>
        </SelectContainer>
      </ContentContainer>
    </Background>
  );
};

export default MainPage;
