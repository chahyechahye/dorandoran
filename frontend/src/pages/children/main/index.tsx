import { useEffect, useState } from "react";

import styled, { keyframes, css } from "styled-components";

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
import { useSoundEffect } from "@/components/sounds/soundEffect";

import { useNavigate } from "react-router-dom";
import { useFairytaleList } from "@/apis/children/fairytale/Queries/useFariytaleList";
import { useRecoilState, useRecoilValue } from "recoil";
import { profileState, selectAnimalState } from "@/states/children/info";
import { useGetLetterList } from "@/apis/common/letter/Queries/useGetLetter";
import { usePostLetterRead } from "@/apis/common/letter/Mutations/usePostLetterRead";
import useSound from "use-sound";
import textballoon from "@/assets/img/childMain/textballoon.png";
import { SoundState } from "@/states/common/voice";

import movables from "@/assets/img/movables.png";

const castleAnimation = keyframes`
  0%, 100% {
    transform: translateY(-2.5vh);
  }
  50% {
    transform: translateY(2.5vh);
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
  z-index: 5;
  position: fixed;
  display: flex;
  justify-content: space-around;
  align-items: center;
  animation: ${castleAnimation} 3s infinite;
`;

const Castle = styled.img<{ isCastleClicked: boolean }>`
  width: 100%;
  opacity: ${(props) => (props.isCastleClicked ? 0.8 : 1)};
  filter: ${(props) => (props.isCastleClicked ? "grayscale(100%)" : "none")};
  transition: opacity 0.3s ease; // 부드러운 전환을 위한 트랜지션 추가
`;

const zoomInAnimation = keyframes`
  0% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.2);
  }
  100% {
    transform: scale(1);
  }
`;

const swingAnimation = keyframes`
  0% {
    transform: rotate(-5deg);
  }
  50% {
    transform: rotate(5deg);
  }
  100% {
    transform: rotate(-5deg);
  }
`;

const Camera = styled.img<{ isCastleClicked: boolean }>`
  width: 22%;
  z-index: 5;
  position: absolute;
  top: 32%;
  left: 44%;

  ${ButtonEffect}

  animation: ${(props) =>
    props.isCastleClicked
      ? css`
          ${swingAnimation} 1s infinite, ${zoomInAnimation} 0.5s ease
        `
      : "none"};

  ${ButtonEffect}

  &:active {
    /* Add ButtonEffect styles for active state here */
    transform: scale(0.9);
    background-color: darken($button-bg, 5%);
    box-shadow: 0 2px 25px rgba(255, 0, 130, 0.2);
    animation: none; /* Disable animation during active state */
  }
`;

const PostOffice = styled.img<{ isCastleClicked: boolean }>`
  width: 24%;
  position: absolute;
  z-index: 5;
  top: 20%;
  left: 12%;

  animation: ${(props) =>
    props.isCastleClicked
      ? css`
          ${swingAnimation} 1s infinite, ${zoomInAnimation} 0.5s ease
        `
      : "none"};

  ${ButtonEffect}

  &:active {
    /* Add ButtonEffect styles for active state here */
    transform: scale(0.9);
    background-color: darken($button-bg, 5%);
    box-shadow: 0 2px 25px rgba(255, 0, 130, 0.2);
    animation: none; /* Disable animation during active state */
  }
`;

const Books = styled.img<{ isCastleClicked: boolean }>`
  width: 20%;
  position: absolute;
  z-index: 5;
  top: 17%;
  left: 71%;

  ${ButtonEffect}

  animation: ${(props) =>
    props.isCastleClicked
      ? css`
          ${swingAnimation} 1s infinite, ${zoomInAnimation} 0.5s ease
        `
      : "none"};

  ${ButtonEffect}

  &:active {
    /* Add ButtonEffect styles for active state here */
    transform: scale(0.9);
    background-color: darken($button-bg, 5%);
    box-shadow: 0 2px 25px rgba(255, 0, 130, 0.2);
    animation: none; /* Disable animation during active state */
  }
`;

const Character = styled.img`
  width: 15%;
  position: absolute;
  z-index: 6;
  top: 71%;
  left: 82%;

  ${ButtonEffect}
`;

const Profile = styled.div`
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 6;
  top: 2%;
  left: 82%;
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

const TutorialBlackGround = styled.div`
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 25%);
  position: absolute;
  z-index: 5;
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

const fadeInOut = keyframes`
  0%, 100% {
    opacity: 0;
    visibility: hidden;
  }
  20% {
    opacity: 0;
    visibility: hidden;
  }
  25% {
    opacity: 1;
    visibility: visible;
  }
  40% {
    opacity: 1;
    visibility: visible;
  }
  55% {
    opacity: 1;
    visibility: visible;
  }
  60% {
    opacity: 0;
    visibility: hidden;
  }
`;

const TextBalloonContainer = styled.div`
  animation: ${fadeInOut} 10s ease-in-out infinite;
  animation-delay: 2s;
  z-index: 6;
`;

const TextBalloon = styled.div`
  position: absolute;
  width: 40vh;
  height: 24vh;
  top: 67.2vh;
  left: 81.5vh;

  background-image: url(${textballoon});
  background-size: cover;
`;

const TextBalloonText = styled.div`
  position: absolute;
  width: 40vh;
  height: 24vh;
  top: 72vh;
  left: 81.5vh;
  z-index: 7;
  font-size: 4vh;
  color: #503d00;
  white-space: pre-line;
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

const SoundBtn = styled.img`
  width: 8vh;
  height: 8vh;

  ${ButtonEffect}
`;

const ChildrenMainPage = () => {
  const [isLetter, setIsLetter] = useState(false);
  const [isEnvelope, setIsEnvelope] = useState(false);
  const [readLetter, setReadLetter] = useState(false);
  const [flag, setFlag] = useState(false);
  const [isLetterPage, setIsLetterPage] = useState(0);
  const [hasFetchedData, setHasFetchedData] = useState(false);
  const [isOpenAlbum, setIsOpenAlbum] = useState(false);
  const [isCharacterClick, setIsCharacterClick] = useState(false);
  const { playSound } = useSoundEffect();
  const profile = useRecoilValue(profileState);
  const [mainSound, setMainSound] = useRecoilState(SoundState);

  const LetterList = useGetLetterList();
  const letterSize = LetterList.data?.size || 0;
  const letterContent = LetterList.data?.letterResDtoList || [];
  const readLetterList = usePostLetterRead();

  const navigate = useNavigate();

  const [playLetterFox, { stop: stopLetterFox }] = useSound(
    "https://storage.googleapis.com/dorandoran/fox_letter.wav"
  );
  const [playAlbumFox, { stop: stopAlbumFox }] = useSound(
    "https://storage.googleapis.com/dorandoran/fox_album.wav"
  );
  const [playLibraryFox, { stop: stopLibraryFox }] = useSound(
    "https://storage.googleapis.com/dorandoran/fox_library.wav"
  );
  const [playLetterRabbit, { stop: stopLetterRabbit }] = useSound(
    "https://storage.googleapis.com/dorandoran/rabbit_letter.wav"
  );
  const [playAlbumRabbit, { stop: stopAlbumRabbit }] = useSound(
    "https://storage.googleapis.com/dorandoran/rabbit_album.wav"
  );
  const [playLibraryRabbit, { stop: stopLibraryRabbit }] = useSound(
    "https://storage.googleapis.com/dorandoran/rabbit_library.wav"
  );
  const [playLetterPeng, { stop: stopLetterPeng }] = useSound(
    "https://storage.googleapis.com/dorandoran/peng_letter.mp3"
  );
  const [playAlbumPeng, { stop: stopAlbumPeng }] = useSound(
    "https://storage.googleapis.com/dorandoran/peng_album.mp3"
  );
  const [playLibraryPeng, { stop: stopLibraryPeng }] = useSound(
    "https://storage.googleapis.com/dorandoran/peng_library.mp3"
  );
  const [playLetterPanda, { stop: stopLetterPanda }] = useSound(
    "https://storage.googleapis.com/dorandoran/newPanda_letter.wav"
  );
  const [playAlbumPanda, { stop: stopAlbumPanda }] = useSound(
    "https://storage.googleapis.com/dorandoran/newPanda_album.wav"
  );
  const [playLibraryPanda, { stop: stopLibraryPanda }] = useSound(
    "https://storage.googleapis.com/dorandoran/newPanda_library.wav"
  );

  const getSoundEffect = (animalType: string, actionType: string) => {
    switch (animalType) {
      case "여우":
        return getFoxSoundEffect(actionType);
      case "토끼":
        return getRabbitSoundEffect(actionType);
      case "펭귄":
        return getPenguinSoundEffect(actionType);
      case "판다":
        return getPandaSoundEffect(actionType);
      default:
        return getFoxSoundEffect(actionType); // Default to fox if the animal type is unknown
    }
  };

  const getFoxSoundEffect = (actionType: string) => {
    switch (actionType) {
      case "letter":
        return { play: playLetterFox, stop: stopLetterFox };
      case "album":
        return { play: playAlbumFox, stop: stopAlbumFox };
      case "library":
        return { play: playLibraryFox, stop: stopLibraryFox };
      default:
        return { play: playLetterFox, stop: stopLetterFox };
    }
  };

  const getRabbitSoundEffect = (actionType: string) => {
    switch (actionType) {
      case "letter":
        return { play: playLetterRabbit, stop: stopLetterRabbit };
      case "album":
        return { play: playAlbumRabbit, stop: stopAlbumRabbit };
      case "library":
        return { play: playLibraryRabbit, stop: stopLibraryRabbit };
      default:
        return { play: playLetterRabbit, stop: stopLetterRabbit };
    }
  };

  const getPenguinSoundEffect = (actionType: string) => {
    switch (actionType) {
      case "letter":
        return { play: playLetterPeng, stop: stopLetterPeng };
      case "album":
        return { play: playAlbumPeng, stop: stopAlbumPeng };
      case "library":
        return { play: playLibraryPeng, stop: stopLibraryPeng };
      default:
        return { play: playLetterPeng, stop: stopLetterPeng };
    }
  };

  const getPandaSoundEffect = (actionType: string) => {
    switch (actionType) {
      case "letter":
        return { play: playLetterPanda, stop: stopLetterPanda };
      case "album":
        return { play: playAlbumPanda, stop: stopAlbumPanda };
      case "library":
        return { play: playLibraryPanda, stop: stopLibraryPanda };
      default:
        return { play: playLetterPanda, stop: stopLetterPanda };
    }
  };

  const goFairytale = () => {
    const { play, stop } = getSoundEffect(profile.animal.name, "library");
    stop(); // Stop the current sound
    if (isCharacterClick) {
      play();
    } else {
      navigate("/children/fairytale");
    }
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
    if (LetterList.data.size > 0 && !flag) {
      setHasFetchedData(true);
      openLetter();
    }
    setFlag(true);
  }, [LetterList, flag, readLetterList]);

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
    const { play, stop } = getSoundEffect(profile.animal.name, "album");
    stop(); // Stop the current sound
    if (isCharacterClick) {
      play(); // Assuming you want a specific sound for opening the album
    } else {
      setIsOpenAlbum(true);
    }
  };

  const handleCloseAlbum = () => {
    setIsOpenAlbum(false);
  };

  const handleClickCharacter = () => {
    playSound();
    setIsCharacterClick(!isCharacterClick);
  };

  const handleMainSound = () => {
    setMainSound(!mainSound);
  };

  return (
    <>
      <ContentContainer>
        {(isLetter || isEnvelope || readLetter) && <BlackGround />}
        {isCharacterClick && <TutorialBlackGround />}
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
              {letterSize === 1 ? null : isLetterPage === 0 ? (
                <ArrowRight src={arrowRight} onClick={handleRightClick} />
              ) : isLetterPage === letterSize - 1 ? (
                <ArrowLeft src={arrowLeft} onClick={handleLeftClick} />
              ) : (
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
          <Castle src={castle} isCastleClicked={isCharacterClick} />
          <PostOffice
            src={postOffice}
            onClick={() => {
              const { play, stop } = getSoundEffect(
                profile.animal.name,
                "letter"
              );
              stop(); // Stop the current sound
              if (isCharacterClick) {
                play(); // Assuming you want a specific sound for opening the album
              } else {
                navigate("/children/sketch");
              }
            }}
            isCastleClicked={isCharacterClick}
          />
          <Books
            src={books}
            onClick={goFairytale}
            isCastleClicked={isCharacterClick}
          />
          <Camera
            src={camera}
            onClick={handleOpenAlbum}
            isCastleClicked={isCharacterClick}
          />
        </CastleContainer>
        <Profile>
          {mainSound ? (
            <SoundBtn src={exitBtn} onClick={handleMainSound}></SoundBtn>
          ) : (
            <SoundBtn src={arrowLeft} onClick={handleMainSound}></SoundBtn>
          )}
          <ProfileCircle type="child" />
        </Profile>
        <TextBalloonContainer>
          <TextBalloon></TextBalloon>
          <TextBalloonText>
            {isCharacterClick
              ? "설명을 다 들으면\n다시 저를 눌러주세요!"
              : "도움이 필요하면\n저를 눌러주세요!"}
          </TextBalloonText>
        </TextBalloonContainer>
        <Character src={profile.animal.imgUrl} onClick={handleClickCharacter} />
        <Movables src={movables} />
      </ContentContainer>
      {isOpenAlbum && <Overlay onClick={handleCloseAlbum} />}
      {isOpenAlbum && <Album onClose={handleCloseAlbum} type="child" />}
    </>
  );
};

export default ChildrenMainPage;
