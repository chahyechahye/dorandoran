import styled, { keyframes } from "styled-components";
import castle from "@/assets/img/childMain/castle.png";
import postOffice from "@/assets/img/childMain/postoffice.png";
import camera from "@/assets/img/childMain/camera.png";
import books from "@/assets/img/childMain/books.png";
import character from "@/assets/img/fox.png";
import ProfileCircle from "@/components/profileCircle";
import Lottie from "lottie-react";
import letterEffect from "@/assets/img/gif/letter.json";
import letterImage from "@/assets/img/letter/letterImage.png";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useFairytaleList } from "@/apis/children/fairytale/Queries/useFariytaleList";
import { useRecoilState } from "recoil";
import { bookListState } from "@/states/children/info";

import movables from "@/assets/img/movables.png";

const Movables = styled.img`
  width: 100%;
  position: absolute;
  z-index: 2;
  bottom: 0;
  left: 0;
`;

const ContentContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

/*----------------------------------
	Main
----------------------------------*/
const Container = styled.div`
  background: linear-gradient(
    180deg,
    #1d95f5 0%,
    rgba(255, 255, 255, 0) 78.65%
  );
  width: 100%;
  height: 100%;
  position: absolute;
  overflow: hidden;
`;

/*----------------------------------
	Cloud
----------------------------------*/
const cloudAnimation = keyframes`
  0% {
    left: 7%;
  }
  50% {
    left: 80%;
  }
  100% {
    left: 7%;
  }
`;

const Cloud = styled.div`
  background: #d2efff;
  display: block;
  position: absolute;
  border-radius: 100%;
  width: 17vh;
  height: 6vh;
  z-index: 1;

  &::before {
    content: "";
    background: #d2efff;
    display: block;
    position: absolute;
    border-radius: 50%;
    width: 12.5vh;
    height: 6.5vh;
  }

  &::after {
    content: "";
    background: #d2efff;
    display: block;
    position: absolute;
    border-radius: 100%;
    top: -3vh;
    left: 3vh;
    width: 10vh;
    height: 9vh;
  }

  &:nth-child(1) {
    left: 100%;
    top: 15%;
    animation: ${cloudAnimation} 15s linear infinite;
  }
  // Define other clouds using Cloud component, adjusting properties as needed.
  &:nth-child(2) {
    top: 30%;
    width: 21vh;
    height: 10vh;
    &::before {
      top: 40%;
      left: -20%;
      width: 28vh;
      height: 10vh;
    }
    &::after {
      left: 20%;
      width: 13vh;
      height: 6.5vh;
    }
    animation: ${cloudAnimation} 12s linear infinite;
  }
  &:nth-child(3) {
    left: 60%;
    top: 25%;
    width: 11vh;
    height: 6vh;
    &::before {
      width: 15vh;
      height: 7vh;
    }
    &::after {
      width: 9vh;
      height: 8vh;
    }
    animation: ${cloudAnimation} 10s linear infinite;
  }
`;

/*----------------------------------
	Airplane
----------------------------------*/
const planeFlyAnimation = keyframes`
  0% {
    left: -10%;
    transform: scale(0.4) rotateY(0deg);
  }
  50% {
    left: 110%;
    transform: scale(1) rotateY(0deg);
  }
  51% {
    transform: scale(1) rotateY(180deg);
  }
  100% {
    left: -10%;
    transform: scale(4) rotateY(180deg);
  }
`;

const Airplane = styled.div`
  position: absolute;
  left: 40%;
  top: 10%;
  z-index: 3;
  animation: ${planeFlyAnimation} 10s linear infinite;
  transform-origin: center center;
`;

const Head = styled.div`
  background: #f9fbfc;
  border-radius: 100%;
  width: 6vh;
  height: 2.5vh;
  position: absolute;
  z-index: 1;
  top: 3.1vh;
  left: 5.2vh;
`;

const Body = styled.div`
  background: #f9fbfc;
  border-radius: 40% 30% 20% 50%;
  width: 13vh;
  height: 2.6vh;
  position: absolute;
  top: 3vh;
  left: -2.5vh;
  z-index: 1;
  transform: rotate(0deg);
  transform-origin: center center;
`;

const LeftWing = styled.div`
  background: #f9fbfc;
  height: 2.7vh;
  width: 3.5vh;
  border-radius: 0.5vh;
  position: absolute;
  top: 1.3vh;
  left: 3vh;
  z-index: 0;
  -webkit-transform: skew(51deg, 0deg);
  -ms-transform: skew(51deg, 0deg);
  -o-transform: skew(51deg, 0deg);
  transform: skew(51deg, 0deg);
`;

const RightWing = styled.div`
  background: #f9fbfc;
  position: absolute;
  top: 4.8vh;
  left: 3vh;
  height: 2.5vh;
  width: 3.5vh;
  border-radius: 0.5vh;
  z-index: 1;
  box-shadow: 0px 6px 4px rgba(0, 0, 0, 0.16);
  -webkit-transform: skew(-49deg, 0deg);
  -ms-transform: skew(-49deg, 0deg);
  -o-transform: skew(-49deg, 0deg);
  transform: skew(-49deg, 0deg);
`;

const Tail = styled.div`
  background: linear-gradient(0deg, #fff, #bbdeff);
  border-radius: 0.2vh;
  width: 1.6vh;
  height: 1.6vh;
  position: absolute;
  left: -1vh;
  top: 2.2vh;
  -webkit-transform: skew(35deg, -9deg);
  -ms-transform: skew(35deg, -9deg);
  -o-transform: skew(35deg, -9deg);
  transform: skew(35deg, -9deg);
  transform-origin: center center;
`;

const Window = styled.div`
  background: #9ad0f5;
  border-radius: 30%;
  width: 0.8vh;
  height: 0.8vh;
  position: absolute;
`;

const Window1 = styled(Window)`
  top: 1vh;
  left: 4vh;
`;

const Window2 = styled(Window)`
  top: 1vh;
  left: 5vh;
`;

const Window3 = styled(Window)`
  top: 1vh;
  left: 6vh;
`;

const Window4 = styled(Window)`
  top: 1vh;
  left: 7vh;
`;

const Window5 = styled(Window)`
  top: 1vh;
  left: 8vh;
`;
const Window6 = styled(Window)`
  top: 1vh;
  left: 9vh;
`;

const Window7 = styled(Window)`
  border-top-right-radius: 8px;
  top: 1vh;
  left: 10.5vh;
`;

const CastleContainer = styled.div`
  z-index: 4;
  position: relative;
  display: flex;
  justify-content: space-around;
  align-items: center;
`;

const Castle = styled.img`
  width: 100%;
`;

const Camera = styled.img`
  width: 22%;
  z-index: 5;
  position: absolute;
  top: 35%;
  left: 44%;
`;
const PostOffice = styled.img`
  width: 24%;
  position: absolute;
  z-index: 5;
  top: 20%;
  left: 12%;
`;
const Books = styled.img`
  width: 20%;
  position: absolute;
  z-index: 5;
  top: 17%;
  left: 71%;
`;

const Character = styled.img`
  width: 15%;
  position: absolute;
  z-index: 6;
  top: 71%;
  left: 82%;
`;

const Profile = styled.div`
  position: absolute;
  z-index: 6;
  top: 2%;
  left: 90%;
`;

const LetterGif = styled.div<{ isLetter: boolean }>`
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  position: absolute;
  z-index: 8;
  display: ${(props) => (props.isLetter ? "block" : "none")};
`;

const LetterRead = styled.img`
  top: 10%;
  left: 12%;
  width: 80%;
  height: 80%;
  position: absolute;
  z-index: 8;
`;

const LetterImg = styled.img`
  top: 20%;
  left: 28%;
  width: 50%;
  height: 50%;
  position: absolute;
  z-index: 7;
`;

const BlackGround = styled.div`
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 25%);
  position: absolute;
  z-index: 7;
`;

const ChildrenMainPage = () => {
  const [isLetter, setIsLetter] = useState(false);
  const [isEnvelope, setIsEnvelope] = useState(false);
  const [readLetter, setReadLetter] = useState(false);

  const navigate = useNavigate();

  const goFairytale = () => {
    navigate("/children/fairytale");
  };

  const openLetter = () => {
    setIsLetter(true);
    setIsEnvelope(true);

    setTimeout(() => {
      setIsLetter(false);
    }, 3000);
  };

  const clickLetter = () => {
    setIsEnvelope(false);
    setReadLetter(true);
  };

  const CloseLetter = () => {
    setIsLetter(false);
    setIsEnvelope(false);
    setReadLetter(false);
  };

  return (
    <>
      <ContentContainer>
        {(isLetter || isEnvelope || readLetter) && (
          <BlackGround onClick={CloseLetter} />
        )}
        <LetterGif isLetter={isLetter}>
          {isLetter && <Lottie animationData={letterEffect} />}
        </LetterGif>
        {isEnvelope && <LetterImg src={letterImage} onClick={clickLetter} />}
        {readLetter && <LetterRead src={postOffice} />}
        <Container>
          <Cloud />
          <Cloud />
          <Cloud />
        </Container>
        <Airplane>
          <Head />
          <Body>
            <Window1 />
            <Window2 />
            <Window3 />
            <Window4 />
            <Window5 />
            <Window6 />
            <Window7 />
          </Body>
          <LeftWing />
          <RightWing />
          <Tail />
        </Airplane>
        <CastleContainer>
          <Castle src={castle} />
          <PostOffice src={postOffice} onClick={openLetter} />
          <Books src={books} onClick={goFairytale} />
          <Camera src={camera} />
        </CastleContainer>
        <Profile>
          <ProfileCircle />
        </Profile>
      </ContentContainer>
      <Character src={character} />
      <Movables src={movables} />
    </>
  );
};

export default ChildrenMainPage;
