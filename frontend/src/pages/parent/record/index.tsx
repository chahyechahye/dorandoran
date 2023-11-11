import React, { useState, useEffect, useCallback, useRef } from "react";
import styled from "styled-components";

import RecordBtn from "@/components/recordBtn";
import SpeakBtn from "@/components/speakBtn";
import Modal from "@/components/modal";
import { useGetRecord } from "@/apis/parents/record/Queries/useGetRecord";
import { usePostVoice } from "@/apis/parents/record/Mutations/usePostVoice";
import GenderModal from "@/components/genderModal";

import background from "@/assets/img/background/backgroundRecord.jpg";
import Logo from "@/assets/img/Logo.png";
import { usePostVoiceComplete } from "@/apis/parents/record/Mutations/usePostVoicecomplete";
import { useNavigate } from "react-router-dom";
import { useSoundEffect } from "@/components/sounds/soundEffect";

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
  font-size: 3.5vh;
  background: #78bffc;
  color: #fff;
  padding: 6vh 10vh;
  border-radius: 3vh;
  margin-bottom: 2vh;
  width: 100vh;
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
  width: 4vh;
  height: 4vh;
  fill: white;
`;

const ParentRecordPage = () => {
  const script = useGetRecord();
  const scriptData = script.data.bookList;
  const totalScriptList = script.data.totalScriptList;
  const recordVoice = usePostVoice();
  const recordComplete = usePostVoiceComplete();

  const [isAlertModalOpen, setIsAlertModalOpen] = useState(true); // 모달의 상태를 관리하는 state
  const [isAlarmModalOpen, setIsAlarmModalOpen] = useState(false);
  const [selectedGender, setSelectedGender] = useState("");

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

  const OpenAlarmModal = () => {
    setIsAlarmModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsAlertModalOpen(false); // 모달을 닫습니다.
    setIsAlarmModalOpen(false);
  };

  const handleNextScript = () => {
    onSubmitAudioFile();
    setAudioUrl(null);
    const updatedScriptReadNum = [...scriptReadNum];
    if (currentScriptNum + 1 === totalScriptList[2] && currentPage === 2) {
      recordComplete.mutateAsync(selectedGender);
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
    recordVoice.mutateAsync({ file: sound, gender: selectedGender });
    console.log(sound);
  }, [audioUrl, recordVoice, selectedGender]);

  useEffect(() => {
    setIsAudioAvailable(Boolean(audioUrl));
  }, [audioUrl]);

  const handleGenderSelection = (selectedOption: string) => {
    setSelectedGender(selectedOption);
  };

  const navigate = useNavigate();
  const { playSound } = useSoundEffect();

  const goProfile = () => {
    playSound();
    navigate("/parent/main");
  };

  return (
    <>
      <GenderModal onGenderSelected={handleGenderSelection} />
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
      {(isAlertModalOpen || isAlarmModalOpen) && (
        <Overlay onClick={handleCloseModal} />
      )}
      {/* 오버레이 렌더링 */}
      {isAlertModalOpen && (
        <Modal
          title="목소리 녹음"
          subtitle={`스크립트를 읽으면 아이에게 동화책을 읽어줄 수 있어요
          20~30cm 정도 떨어진 상태로 녹음해 주세요
          큰 소리로 또박또박 읽어주세요
          ( 실제로 읽어주는 목소리로 해주세요.)
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
          onClose={handleCloseModal} // 모달 닫기를 위한 함수를 전달합니다.
        />
      )}
      <audio ref={audioPlayerRef} controls style={{ display: "none" }} />
    </>
  );
};

export default ParentRecordPage;
