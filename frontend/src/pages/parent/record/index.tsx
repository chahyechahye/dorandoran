import React, { useState, useEffect, useCallback, useRef } from "react";
import styled from "styled-components";

import RecordBtn from "@/components/recordBtn";
import SpeakBtn from "@/components/speakBtn";
import Modal from "@/components/modal";
import { useGetRecord } from "@/apis/parents/record/Queries/useGetRecord";
import { usePostVoice } from "@/apis/parents/record/Mutations/usePostVoice";
import GenderModal from "@/components/genderModal";
import SaveModal from "@/components/saveModal";

import background from "@/assets/img/background/backgroundMain.jpg";
import Logo from "@/assets/img/Logo.png";
import exitBtn from "@/assets/img/exitBtn.png";
import { usePostVoiceComplete } from "@/apis/parents/record/Mutations/usePostVoicecomplete";
import { useNavigate } from "react-router-dom";
import { useSoundEffect } from "@/components/sounds/soundEffect";
import { MainSoundState } from "@/states/common/voice";
import { useRecoilState } from "recoil";
import { usePostSaveRecord } from "@/apis/parents/record/Mutations/usePostSaveRecord";
import { ButtonEffect } from "@/styles/buttonEffect";
import { useDeleteVoice } from "@/apis/parents/record/Mutations/useDeleteVoice";

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

const Overlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); // 반투명한 검은색 배경
  z-index: 0; // 모달보다 낮은 z-index를 가지게 하여, 모달 뒤에 위치하게 합니다.
`;

const Image = styled.img`
  width: 40%;
`;

const RecordBoxWrapper = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 3.5vh;
  background: #78bffc;
  color: #fff;
  padding: 6vh 10vh;
  border-radius: 3vh;
  margin-bottom: 2vh;
  width: 100vh;
  height: 26vh;
  max-width: 160vh;
`;

const Text = styled.p`
  margin: 0;
`;

const ArrowWrapper = styled.div`
  position: absolute;
  right: 1vh;
  top: 50%;
  transform: translate(-50%, -50%);
`;

const ArrowIcon = styled.svg`
  width: 5vh;
  height: 5vh;
  fill: white;
`;

const ExitContainer = styled.div`
  position: fixed;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  top: 0;
  left: 0;
  margin: 2vh 4vh;
  z-index: 5;

  ${ButtonEffect}
`;

const ExitBtn = styled.img`
  width: 12vh;
`;

const ParentRecordPage = () => {
  const script = useGetRecord();
  const navigate = useNavigate();
  const scriptData = script.data.bookList;
  const totalScriptList = script.data.totalScriptList;
  const recordVoice = usePostVoice();
  const recordComplete = usePostVoiceComplete();
  const deleteVoice = useDeleteVoice();

  const [isAlertModalOpen, setIsAlertModalOpen] = useState(true); // 모달의 상태를 관리하는 state
  const [isAlarmModalOpen, setIsAlarmModalOpen] = useState(false);
  const [isSaveModalOpen, setIsSaveModalOpen] = useState(false);
  const [selectedGender, setSelectedGender] = useState("");
  const [resultGender, setResultGender] = useState("");

  // Add a useEffect to update resultGender when selectedGender changes
  useEffect(() => {
    if (selectedGender === "아빠") {
      setResultGender("MALE");
    } else if (selectedGender === "엄마") {
      setResultGender("FEMALE");
    } else {
      // Handle other cases if needed
      setResultGender("");
    }
  }, [selectedGender]);

  const getSaveRecord = usePostSaveRecord();

  const [stream, setStream] = useState<MediaStream | null>(null);
  const [media, setMedia] = useState<MediaRecorder | null>(null);
  const [onRec, setOnRec] = useState<boolean>(true);
  const [source, setSource] = useState<MediaStreamAudioSourceNode | null>(null);
  const [analyser, setAnalyser] = useState<ScriptProcessorNode | null>(null);
  const [audioUrl, setAudioUrl] = useState<Blob | null>(null);
  const audioPlayerRef = useRef<HTMLAudioElement>(null);
  const [isAudioAvailable, setIsAudioAvailable] = useState(false);
  const [soundEnd, setSoundEnd] = useState(false);

  const [currentPage, setCurrentPage] = useState(0);
  const [currentScriptNum, setCurrentScriptNum] = useState(0);
  const [scriptReadNum, setScriptReadNum] = useState([0, 0, 0]);
  const [isPlaying, setIsPlaying] = useRecoilState(MainSoundState);
  const [saveData, setSaveData] = useState({
    title: "백설 공주",
    scriptNum: 0,
  });
  const { playSound } = useSoundEffect();

  useEffect(() => {
    // This effect runs when the component mounts (on page enter)
    setIsPlaying(false);

    // Return a cleanup function to handle component unmounting (on page leave)
    return () => {
      setIsPlaying(true);
    };
  }, [setIsPlaying]);

  const OpenAlarmModal = () => {
    setIsAlarmModalOpen(true);
  };

  const handleCompleteModal = () => {
    navigate("/parent/main");
  };

  const handleCloseModal = () => {
    const response = getSaveRecord.mutateAsync(resultGender);
    response.then((res) => {
      if (Object.keys(res.data).length !== 0) {
        setIsSaveModalOpen(true);
        console.log(res.data);
        setSaveData(res.data);
      }
    });
    setIsAlertModalOpen(false); // 모달을 닫습니다.
    setIsAlarmModalOpen(false);
  };

  const handleCloseSaveModal = () => {
    setIsSaveModalOpen(false); // 모달을 닫습니다.
  };

  const handleNextScript = () => {
    onSubmitAudioFile();
    setAudioUrl(null);
    const updatedScriptReadNum = [...scriptReadNum];
    if (currentScriptNum + 1 === totalScriptList[2] && currentPage === 2) {
      recordComplete.mutateAsync(resultGender);
      OpenAlarmModal();
      return;
    }

    setCurrentScriptNum(currentScriptNum + 1);

    if (currentScriptNum === totalScriptList[currentPage] - 1) {
      setCurrentScriptNum(0);
      setCurrentPage(currentPage + 1);
      return;
    }
    updatedScriptReadNum[currentPage] += 1;

    setScriptReadNum(updatedScriptReadNum);
  };

  // 녹음 관련 함수들

  const onRecAudio = () => {
    const audioCtx = new (window.AudioContext ||
      (window as any).webkitAudioContext)(); // 현재 사용 가능한 것을 선택합니다.
    const analyser = audioCtx.createScriptProcessor(0, 1, 1);
    setAnalyser(analyser);

    function makeSound(stream: MediaStream) {
      const source = audioCtx.createMediaStreamSource(stream);
      setSource(source);
      source.connect(analyser);
      analyser.connect(audioCtx.destination);
    }

    navigator.mediaDevices.getUserMedia({ audio: true }).then((stream) => {
      const mediaRecorder = new MediaRecorder(stream);
      mediaRecorder.start();
      setStream(stream);
      setMedia(mediaRecorder);
      makeSound(stream);

      analyser.onaudioprocess = function (e) {
        if (e.playbackTime > 180) {
          stream.getAudioTracks().forEach(function (track) {
            track.stop();
          });
          mediaRecorder.stop();
          analyser.disconnect();
          audioCtx.createMediaStreamSource(stream).disconnect();

          mediaRecorder.ondataavailable = function (e) {
            setAudioUrl(e.data);
            setOnRec(true);
          };
        } else {
          setOnRec(false);
        }
      };
    });
  };

  const offRecAudio = () => {
    if (media) {
      media.ondataavailable = function (e) {
        setAudioUrl(e.data);
        setOnRec(true);
      };

      if (stream) {
        stream.getAudioTracks().forEach(function (track) {
          track.stop();
        });
        media.stop();
        if (analyser) {
          analyser.disconnect();
        }
        if (source) {
          source.disconnect();
        }
      }
    }
  };

  const playAudio = () => {
    if (audioUrl && audioPlayerRef.current) {
      audioPlayerRef.current.src = URL.createObjectURL(audioUrl);
      audioPlayerRef.current.volume = 1.0;
      audioPlayerRef.current.play();

      audioPlayerRef.current.addEventListener("ended", () => {
        setSoundEnd(true);
        setTimeout(() => {
          setSoundEnd(false);
        }, 1000);
      });
    }
  };

  const onSubmitAudioFile = useCallback(() => {
    if (audioUrl) {
      console.log(URL.createObjectURL(audioUrl));
    }
    const sound = new File([audioUrl as Blob], "soundBlob", {
      lastModified: new Date().getTime(),
      type: "audio/wav",
    });

    const setGender: string = selectedGender === "아빠" ? "MALE" : "FEMALE";

    recordVoice.mutateAsync({
      file: sound,
      gender: setGender,
      title: scriptData[currentPage].title,
      scriptNum: currentScriptNum + 1,
    });
    console.log(sound);
  }, [
    audioUrl,
    recordVoice,
    selectedGender,
    currentPage,
    scriptData,
    currentScriptNum,
  ]);

  useEffect(() => {
    setIsAudioAvailable(Boolean(audioUrl));
  }, [audioUrl]);

  const handleGenderSelection = (selectedOption: string) => {
    setSelectedGender(selectedOption);
  };

  const goProfile = () => {
    playSound();
    navigate("/parent/main");
  };

  const onResume = () => {
    if (saveData.title === "백설공주") {
      if (saveData.scriptNum === totalScriptList[0]) {
        setCurrentScriptNum(0);
        setCurrentPage(1);
        const updatedScriptReadNum = [...scriptReadNum];
        updatedScriptReadNum[0] = totalScriptList[0] - 1;

        setScriptReadNum(updatedScriptReadNum);
      } else {
        setCurrentScriptNum(saveData.scriptNum);
        setCurrentPage(0);

        const updatedScriptReadNum = [...scriptReadNum];
        updatedScriptReadNum[currentPage] = saveData.scriptNum;
        setScriptReadNum(updatedScriptReadNum);
      }
    } else if (saveData.title === "빨간 모자") {
      if (saveData.scriptNum === totalScriptList[1]) {
        setCurrentScriptNum(0);
        setCurrentPage(2);
        const updatedScriptReadNum = [...scriptReadNum];
        updatedScriptReadNum[0] = totalScriptList[0] - 1;
        updatedScriptReadNum[1] = totalScriptList[1] - 1;

        setScriptReadNum(updatedScriptReadNum);
      } else {
        setCurrentScriptNum(saveData.scriptNum);
        setCurrentPage(1);

        const updatedScriptReadNum = [...scriptReadNum];
        updatedScriptReadNum[0] = totalScriptList[0] - 1;
        updatedScriptReadNum[1] = saveData.scriptNum;

        setScriptReadNum(updatedScriptReadNum);
      }
    } else {
      setCurrentScriptNum(saveData.scriptNum);
      setCurrentPage(2);

      const updatedScriptReadNum = [...scriptReadNum];
      updatedScriptReadNum[0] = totalScriptList[0] - 1;
      updatedScriptReadNum[1] = totalScriptList[1] - 1;
      updatedScriptReadNum[2] = saveData.scriptNum;

      setScriptReadNum(updatedScriptReadNum);
    }

    handleCloseSaveModal();
    console.log("이어하기");
  };

  const onRestart = () => {
    // 처음부터 버튼을 눌렀을 때 실행할 동작을 정의
    // 예: 처음부터 녹음을 시작하는 로직 등
    deleteVoice.mutateAsync(resultGender);
    handleCloseSaveModal();
    console.log("처음부터");
  };

  return (
    <>
      <GenderModal onGenderSelected={handleGenderSelection} type="parent" />
      <Container>
        <Image src={Logo} alt="Background" onClick={goProfile} />
        <div
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
            width: "100vh",
          }}
        >
          <p style={{ fontSize: "5vh", color: "#6d85e1" }}>
            {currentPage + 1}. {scriptData[currentPage].title}
          </p>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "space-between",
            }}
          >
            {scriptReadNum.map((value, index) => (
              <div
                key={index}
                style={{
                  fontSize: "3vh",
                  color: "#fff",
                  backgroundColor:
                    index === currentPage
                      ? "#78BFFC" // 파란색 (#78BFFC)으로 설정
                      : value + 1 === totalScriptList[index]
                      ? "#F65F5F" // 빨간색 (#F65F5F)으로 설정
                      : "#A9A9A9", // 회색 (#A9A9A9)으로 설정
                  padding: "1.2vh 2vh",
                  borderRadius: "2vh",
                  margin: "2vh 0.5vh",
                }}
              >
                {index <= currentPage ? value + 1 : value}/
                {totalScriptList[index]}
              </div>
            ))}
          </div>
        </div>
        <RecordBoxWrapper>
          {scriptData[currentPage].scriptList[currentScriptNum].script}
          <ArrowWrapper>
            <ArrowIcon
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 25 32"
              onClick={handleNextScript}
            >
              <path d="M0 0V31.5L24.75 15.75L0 0Z" fill="white" />
            </ArrowIcon>
          </ArrowWrapper>
        </RecordBoxWrapper>
        <div
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
          }}
        >
          <div>
            <RecordBtn onClick={onRec ? onRecAudio : offRecAudio} />
            <p
              style={{
                marginRight: "20vh",
                color: "#F65F5F",
                fontSize: "4vh",
              }}
            >
              녹음하기
            </p>
          </div>
          <div>
            <SpeakBtn
              onClick={isAudioAvailable ? playAudio : () => {}}
              disabled={!isAudioAvailable}
              soundEnd={soundEnd}
            />
            <p
              style={{
                color: "#FFB016",
                fontSize: "4vh",
              }}
            >
              다시 듣기
            </p>
          </div>
        </div>
      </Container>
      {(isAlertModalOpen || isAlarmModalOpen || isSaveModalOpen) && (
        <Overlay onClick={handleCloseModal} />
      )}
      {/* 오버레이 렌더링 */}
      {isSaveModalOpen && (
        <SaveModal
          title="녹음된 기록이 있어요!"
          subtitle={`저장된 기록부터 다시 녹음할 수 있습니다.
          기존 녹음을 이어서 진행하시겠어요?`}
          bgColor="#78BFFC"
          onClose={handleCloseSaveModal}
          onResume={onResume}
          onRestart={onRestart}
        />
      )}

      {isAlertModalOpen && (
        <Modal
          title="목소리 녹음"
          subtitle={`스크립트를 읽으면 아이에게 동화책을 읽어줄 수 있어요
          20~30cm 정도 떨어진 상태로 녹음해 주세요
          정확한 목소리로 또박또박 읽어주세요
          목소리가 너무 크지 않게 녹음해주세요
          ( 실제로 읽어주는 목소리로 해주세요.)
          숨소리가 들어가지 않게 해주세요.
          잡음이 녹음된 경우 해당 문장을 다시 녹음해주세요`}
          placeholder=""
          buttonText="생성하기"
          bgColor="#fc7292"
          buttonColor="#78bff0"
          showInput={false}
          onClose={handleCloseModal} // 모달 닫기를 위한 함수를 전달합니다.
        />
      )}
      {isAlarmModalOpen && (
        <Modal
          title="녹음이 완료되었어요!"
          subtitle={`목소리 AI 학습 시간은 4~5 시간정도 소요됩니다. 
          AI 목소리가 완성되면 문자를 통해서 알려드릴게요`}
          placeholder="전화번호를 입력해주세요"
          buttonText="알림받기"
          bgColor="#78BFFC"
          buttonColor="#F65F5F"
          showInput={true}
          onClose={handleCompleteModal} // 모달 닫기를 위한 함수를 전달합니다.
        />
      )}
      <ExitContainer className="exit-button" onClick={goProfile}>
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
      </ExitContainer>

      <audio ref={audioPlayerRef} controls style={{ display: "none" }} />
    </>
  );
};

export default ParentRecordPage;
