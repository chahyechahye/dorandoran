import styled from "styled-components";

import background from "@/assets/img/background/background.jpg";
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
  const onSocialKakaoClick = () => {
    const clientId = `${process.env.REACT_APP_RESTAPI_KAKAO_APP_KEY}`;
    const REDIRECT_URL = `${process.env.REACT_APP_BASE_URL}/oauth/redirect/kakao`;

    const AUTH_URL = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${clientId}&redirect_uri=${REDIRECT_URL}`;

    window.location.href = AUTH_URL;
  };

  const onSocialGoogleClick = () => {
    const clientId = `${process.env.REACT_APP_RESTAPI_KAKAO_APP_KEY}`;
    const REDIRECT_URL = `${process.env.REACT_APP_BASE_URL}/oauth/redirect/google`;

    const AUTH_URL = `https://accounts.google.com/o/oauth2/auth?client_id=${clientId}&redirect_uri=${REDIRECT_URL}&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile`;

    window.location.href = AUTH_URL;
  };

  return (
    <Container>
      <Image src={Logo} alt="Background" />
      <LoginImage
        src={kakaoLogo}
        alt="Background"
        onClick={onSocialKakaoClick}
      />
      <LoginImage
        src={googleLogo}
        alt="Background"
        onClick={onSocialGoogleClick}
      />
    </Container>
  );
};

export default ParentLoginPage;
