import styled from "styled-components";

import background from "@/assets/img/backgroundMain.jpg";
import Logo from "@/assets/img/Logo.png";

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

const ParentMainPage = () => {
  return (
    <Container>
      <Image src={Logo} alt="Background" />
      <div>안녕하세요</div>
    </Container>
  );
};

export default ParentMainPage;
