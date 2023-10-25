import styled from "styled-components";

import background from "@/assets/img/background.jpg";
import Logo from "@/assets/img/Logo.png";
import kakaoLogo from "@/assets/img/kakao.png";
import googleLogo from "@/assets/img/google.png";

const Container = styled.div`
  position: fixed;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url(${background});
  background-size: cover;
`;

const Image = styled.img`
  width: 50%;
`;

const LoginImage = styled.img`
  width: 30%;
  margin-bottom: 2%;
  box-shadow: rgba(0, 0, 0, 0.3) 0px 5px 15px;
  border-radius: 2vh;
`;

const ParentLoginPage = () => {
  return (
    <Container>
      <Image src={Logo} alt="Background" />
      <LoginImage src={kakaoLogo} alt="Background" />
      <LoginImage src={googleLogo} alt="Background" />
    </Container>
  );
};

export default ParentLoginPage;
