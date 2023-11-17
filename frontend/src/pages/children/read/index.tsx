import styled from "styled-components";
import background from "@/assets/img/bookview.png";
import arrowLeft from "@/assets/img/fairytale/arrowLeft.png";
import arrowRight from "@/assets/img/fairytale/arrowRight.png";
import { useNavigate } from "react-router-dom";
import { useState, useRef, useEffect } from "react";
import { useRecoilState, useRecoilValue, useSetRecoilState } from "recoil";
import { MainSoundState } from "@/states/common/voice";
import {
  fairytaleContentListState,
  fairytaleReadState,
} from "@/states/children/info";
import { ButtonEffect } from "@/styles/buttonEffect";
import exitBtn from "@/assets/img/exitBtn.png";
import { useSoundBookEffect } from "@/components/sounds/soundBookEffect";
import { toast } from "react-toastify";

const Background = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: background-image 0.5s ease;

  &::after {
    content: "";
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 100%; /* Adjust the height of the gradient as needed */
    background: linear-gradient(
      180deg,
      rgba(217, 217, 217, 0) 65.68%,
      rgba(123, 123, 123, 0.43) 72.11%,
      rgba(0, 0, 0, 0.3) 75.84%
    );
  }
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: end;
  height: 100%;
  margin: 0vh 5vh;
  margin-bottom: 10vh;
`;

const TextContainer = styled.div`
  /* position: absolute; */
  bottom: 5vh;
  text-align: center;
  z-index: 10;
`;

const TextBox = styled.div`
  font-size: 7.5vh;
  color: white;
  -webkit-text-stroke: 0.02em black;
  text-shadow: 0.04em 0.04em 0.04em rgba(0, 0, 0, 0.4);
`;

const ArrowBox = styled.div`
  position: absolute;
  top: 42%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 3%;
  z-index: 11;
`;

const ArrowLeft = styled.img`
  width: 20vh;

  ${ButtonEffect}
`;

const ArrowRight = styled.img`
  width: 20vh;

  ${ButtonEffect}
`;

const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
  right: 0;
  z-index: 1;
`;

const ExitBtn = styled.img`
  width: 12vh;
  ${ButtonEffect}
`;

const FairytaleReadPage = () => {
  const navigate = useNavigate();
  // 동화 인덱스 리스트
  const fairytaleRead = useRecoilValue(fairytaleReadState);
  const [infoFairytaleRead, setInfoFairytaleRead] = useRecoilState(
    fairytaleContentListState
  );
  // 동화 세부 정보 리스트
  const fairytaleContentList = useRecoilValue(fairytaleContentListState);
  // 현재 페이지 (이미지 단위)
  const [currentPageIndex, setCurrentPageIndex] = useState(0);
  // 컨텐츠 (스크립트 변화)
  const [currentContentIndex, setCurrentContentIndex] = useState(0);
  const [audioStarted, setAudioStarted] = useState(false);
  const [audioPlaying, setAudioPlaying] = useState(false);

  setInfoFairytaleRead(fairytaleContentList);

  const audioPlayerRef = useRef<HTMLAudioElement | null>(null);
  setInfoFairytaleRead(fairytaleContentList);
  const { playBookSound } = useSoundBookEffect();
  const setSoundData = useSetRecoilState(MainSoundState);

  useEffect(() => {
    setSoundData(false);
  }, [setSoundData]);

  useEffect(() => {
    if (
      currentContentIndex === fairytaleContentList.length - 1 &&
      currentPageIndex === fairytaleRead.length - 1
    ) {
      setTimeout(() => {
        toast("📖 동화가 끝났어요!");
      }, 200);
    }
  }, [
    currentContentIndex,
    currentPageIndex,
    fairytaleContentList.length,
    fairytaleRead.length,
  ]);

  const handleArrowRight = () => {
    if (currentContentIndex < fairytaleContentList.length - 1) {
      setCurrentContentIndex(currentContentIndex + 1);
    } else if (
      currentContentIndex === fairytaleContentList.length - 1 &&
      currentPageIndex < fairytaleRead.length - 1
    ) {
      playBookSound();
      setCurrentPageIndex(currentPageIndex + 1);
      setCurrentContentIndex(0);
    } else if (
      currentContentIndex === fairytaleContentList.length - 1 &&
      currentPageIndex === fairytaleRead.length - 1
    ) {
      setSoundData(true);
      window.location.href = "/children/like";
    }
  };

  const handleArrowLeft = () => {
    playBookSound();
    if (currentContentIndex === 0 && currentPageIndex > 0) {
      setCurrentPageIndex(currentPageIndex - 1);
      setCurrentContentIndex(
        fairytaleRead[currentPageIndex - 1].contentResDto.length - 1
      );
    } else if (currentContentIndex > 0) {
      setCurrentContentIndex(currentContentIndex - 1);
    } else if (currentContentIndex === 0 && currentPageIndex === 0) {
      toast("📖 첫장면이에요!");
    }
  };

  const voice: string =
    fairytaleContentList[currentContentIndex]?.voiceUrl || "";
  const backgroundImage = fairytaleRead[currentPageIndex]?.imgUrl;
  const script = fairytaleContentList[currentContentIndex]?.script;
  setInfoFairytaleRead(fairytaleRead[currentPageIndex].contentResDto);

  useEffect(() => {
    if (voice) {
      setAudioStarted(true);
    }
  }, [voice]);

  useEffect(() => {
    if (!audioStarted || !voice) {
      return;
    }

    const audioPlayer = new Audio(voice);

    const playAudio = () => {
      if (audioPlayer) {
        audioPlayer.play().catch((error) => {
          console.error("Error playing audio:", error);
        });
      }
    };

    const playAudioOnUserInteraction = () => {
      document.removeEventListener("click", playAudioOnUserInteraction);
      playAudio();
    };

    audioPlayer.addEventListener("canplaythrough", playAudioOnUserInteraction);

    playAudio(); // Play audio when the component mounts and voice is set

    return () => {
      if (audioPlayer) {
        audioPlayer.pause();
        audioPlayer.removeEventListener(
          "canplaythrough",
          playAudioOnUserInteraction
        );
        audioPlayer.remove();
      }
    };
  }, [audioStarted, voice]);

  const goMain = () => {
    setSoundData(true);
    navigate("/children/fairytale");
  };

  return (
    <Background
      style={{
        backgroundImage: `url(${backgroundImage})`,
        transitionDelay: "0.1s",
      }}
    >
      <ContentContainer>
        <Header>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "flex",
              margin: "4vh",
            }}
            onClick={goMain}
          >
            <ExitBtn src={exitBtn}></ExitBtn>
            <p
              style={{
                fontSize: "5vh",
                color: "#f25222",
                textShadow: "2px 4px 2px rgba(0, 0, 0, 0.2)",
              }}
            >
              나가기
            </p>
          </div>
        </Header>
        <ArrowBox>
          <ArrowLeft src={arrowLeft} onClick={handleArrowLeft} />
          <ArrowRight src={arrowRight} onClick={handleArrowRight} />
        </ArrowBox>
        <TextContainer>
          <TextBox>{script}</TextBox>
        </TextContainer>
      </ContentContainer>
    </Background>
  );
};

export default FairytaleReadPage;
