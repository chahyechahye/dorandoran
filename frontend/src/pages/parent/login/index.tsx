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
  const onSocialButtonClick = () => {
    const OAUTH2_REDIERECT_URI = `${process.env.REACT_APP_BASE_URL}/oauth/redirect`;
    const API_KEY = "f9a75e5f47148dc0aa986669c29c0d03";
    const AUTH_URL = `${process.env.REACT_APP_SERVER_URL}?response_type=code&client_id=${API_KEY}&redirect_uri=${OAUTH2_REDIERECT_URI}`;
    window.location.href = AUTH_URL;

    const code = new URL(window.location.href).searchParams.get("code");
    console.log(code);
  };

  return (
    <Container>
      <Image src={Logo} alt="Background" />
      <LoginImage
        src={kakaoLogo}
        alt="Background"
        onClick={onSocialButtonClick}
      />
      <LoginImage src={googleLogo} alt="Background" />
    </Container>
  );
};

export default ParentLoginPage;
