import styled from "styled-components";

import ChildCard from "@/components/childCard";
import ProfileCircle from "@/components/profileCircle";
import Album from "@/components/album";

import background from "@/assets/img/backgroundMain.jpg";
import Logo from "@/assets/img/Logo.png";
import smile from "@/assets/img/smile.png";

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

const Content = styled.main`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10vh;
`;

const Image = styled.img`
  width: 45%;
  padding-bottom: 8vh;
`;

const ParentProfilePage = () => {
  return (
    <>
      <Container>
        <Image src={Logo} alt="Background" />
        <Content>
          <ChildCard img={smile} backgroundColor="#78BFFC" text="손수형" />
          <div
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              width: "30vh",
              height: "30vh",
              paddingBottom: "12vh",
            }}
          >
            <ProfileCircle />
          </div>
        </Content>
      </Container>
    </>
  );
};

export default ParentProfilePage;
