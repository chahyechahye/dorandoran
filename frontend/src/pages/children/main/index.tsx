import { useEffect, useState } from "react";

import styled, { keyframes } from "styled-components";
import castle from "@/assets/img/childMain/castle.png";
import postOffice from "@/assets/img/childMain/postoffice.png";
import camera from "@/assets/img/childMain/camera.png";
import books from "@/assets/img/childMain/books.png";
import Album from "@/components/album";
import character from "@/assets/img/fox.png";
import ProfileCircle from "@/components/profileCircle";
import Lottie from "lottie-react";
import letterEffect from "@/assets/img/gif/letter.json";
import letterImage from "@/assets/img/letter/letterImage.png";
import exitBtn from "@/assets/img/exitBtn.png";
import arrowLeft from "@/assets/img/fairytale/arrowLeft.png";
import arrowRight from "@/assets/img/fairytale/arrowRight.png";
import { ButtonEffect } from "@/styles/buttonEffect";

import { useNavigate } from "react-router-dom";
import { useFairytaleList } from "@/apis/children/fairytale/Queries/useFariytaleList";
import { useRecoilState, useRecoilValue } from "recoil";
import { profileState, selectAnimalState } from "@/states/children/info";
import { useGetLetterList } from "@/apis/common/letter/Queries/useGetLetter";
import { usePostLetterRead } from "@/apis/common/letter/Mutations/usePostLetterRead";

import movables from "@/assets/img/movables.png";

const castleAnimation = keyframes`
  0% {
    transform: translateY(-2.5vh);
  }
  50% {
    transform: translateY(2.5vh);
  }
  90% {
    transform: translateY(-2.5vh);
  }
  100% {
    transform: translateY(-2.5vh);
  }
`;

const Movables = styled.img`
  width: 100%;
  position: fixed;
  z-index: 2;
  bottom: 0;
  left: 0;
`;

const ContentContainer = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
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
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  position: fixed;
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
  position: fixed;
  z-index: 1;
  top: 3.1vh;
  left: 5.2vh;
`;

const Body = styled.div`
  background: #f9fbfc;
  border-radius: 40% 30% 20% 50%;
  width: 13vh;
  height: 2.6vh;
  position: fixed;
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
  position: fixed;
  display: flex;
  justify-content: space-around;
  align-items: center;
`;

const Castle = styled.img`
  width: 100%;
  animation: ${castleAnimation} 3s infinite;
`;

const Camera = styled.img`
  width: 22%;
  z-index: 5;
  position: absolute;
  top: 35%;
  left: 44%;

  ${ButtonEffect}
`;
const PostOffice = styled.img`
  width: 24%;
  position: absolute;
  z-index: 5;
  top: 20%;
  left: 12%;

  ${ButtonEffect}
`;
const Books = styled.img`
  width: 20%;
  position: absolute;
  z-index: 5;
  top: 17%;
  left: 71%;

  ${ButtonEffect}
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
  left: 89%;
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

const ArrowBox = styled.div`
  position: absolute;
  top: 50%;
  width: 100%;
  display: flex;
  align-items: center;
  padding: 0 1%;
  z-index: 999;
`;

const ArrowLeft = styled.img`
  position: absolute;
  left: 2vh;
  width: 20vh;
`;

const ExitBtn = styled.img`
  width: 15vh;
`;

const ArrowRight = styled.img`
  position: absolute;
  right: 2vh;
  width: 20vh;
`;

const Overlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); // 반투명한 검은색 배경
  z-index: 0; // 모달보다 낮은 z-index를 가지게 하여, 모달 뒤에 위치하게 합니다.
`;

const ChildrenMainPage = () => {
  const [isLetter, setIsLetter] = useState(false);
  const [isEnvelope, setIsEnvelope] = useState(false);
  const [readLetter, setReadLetter] = useState(false);
  const [flag, setFlag] = useState(false);
  const [isLetterPage, setIsLetterPage] = useState(0);
  const [hasFetchedData, setHasFetchedData] = useState(false);
  const [isOpenAlbum, setIsOpenAlbum] = useState(false);

  const LetterList = useGetLetterList();
  const letterSize = LetterList.data.size;
  const letterContent = LetterList.data.letterResDtoList;
  const readLetterList = usePostLetterRead();

  const profile = useRecoilValue(profileState);

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
    readLetterList.mutateAsync();
  };

  useEffect(() => {
    if (letterSize !== 0 && !flag) {
      setHasFetchedData(true);
      openLetter();
    }
    setFlag(true);
  }, [letterSize, flag, readLetterList]);

  const handleLeftClick = () => {
    if (isLetterPage > 0) {
      setIsLetterPage(isLetterPage - 1);
    }
  };

  const handleRightClick = () => {
    if (isLetterPage < letterSize) {
      setIsLetterPage(isLetterPage + 1);
    }
  };

  const handleOpenAlbum = () => {
    setIsOpenAlbum(true);
  };

  const handleCloseAlbum = () => {
    setIsOpenAlbum(false);
  };

  console.log(profile);

  return (
    <>
      <ContentContainer>
        {(isLetter || isEnvelope || readLetter) && <BlackGround />}
        <LetterGif isLetter={isLetter}>
          {isLetter && <Lottie animationData={letterEffect} />}
        </LetterGif>
        {isEnvelope && <LetterImg src={letterImage} onClick={clickLetter} />}
        {readLetter && (
          <>
            <div
              style={{
                display: "flex",
                position: "absolute",
                right: 0,
                top: 0,
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "flex",
                margin: "4vh",
                zIndex: "999",
              }}
              onClick={CloseLetter}
            >
              <ExitBtn src={exitBtn}></ExitBtn>
              <p style={{ fontSize: "7vh" }}>나가기</p>
            </div>
            <LetterRead src={letterContent[isLetterPage].contentUrl} />
            <ArrowBox>
              {isLetterPage === 0 && (
                <ArrowRight src={arrowRight} onClick={handleRightClick} />
              )}
              {isLetterPage === letterSize - 1 && (
                <ArrowLeft src={arrowLeft} onClick={handleLeftClick} />
              )}
              {isLetterPage !== 0 && isLetterPage !== letterSize - 1 && (
                <>
                  <ArrowLeft src={arrowLeft} onClick={handleLeftClick} />
                  <ArrowRight src={arrowRight} onClick={handleRightClick} />
                </>
              )}
            </ArrowBox>
          </>
        )}
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
          <PostOffice
            src={postOffice}
            onClick={() => navigate("/children/sketch")}
          />
          <Books src={books} onClick={goFairytale} />
          <Camera src={camera} onClick={handleOpenAlbum} />
        </CastleContainer>
        <Profile>
          <ProfileCircle type="child" />
        </Profile>
        <Character src={profile.animal.imgUrl} />
        <Movables src={movables} />
      </ContentContainer>
      {isOpenAlbum && <Overlay onClick={handleCloseAlbum} />}
      {isOpenAlbum && <Album onClose={handleCloseAlbum} type="child" />}
    </>
  );
};

export default ChildrenMainPage;
