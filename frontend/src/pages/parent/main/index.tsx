import styled from "styled-components";

import LikeBook from "@/components/likeBook";
import ProfileCircle from "@/components/profileCircle";
import ParentCard from "@/components/parentCard";

import background from "@/assets/img/backgroundMain.jpg";

import photo from "@/assets/img/photo.png";
import tape from "@/assets/img/tape.png";
import post from "@/assets/img/post.png";

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

const Header = styled.header`
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
  left: 0;
  padding: 1.5vh;
`;

const Content = styled.main`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10vh;
`;

const Image = styled.img`
  width: 45%;
  padding-bottom: 7vh;
`;

const ParentMainPage = () => {
  return (
    <>
      <Container>
        <Header>
          <ProfileCircle />
          <LikeBook />
        </Header>
        <Image src={Logo} alt="Background" />
        <Content>
          <ParentCard img={tape} backgroundColor="#78BFFC" text="목소리 녹음" />
          <ParentCard img={photo} backgroundColor="#FC7292" text="사진 보기" />
          <ParentCard img={post} backgroundColor="#4FCDC7" text="편지 쓰기" />
        </Content>
      </Container>
    </>
  );
};

export default ParentMainPage;
