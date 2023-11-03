import { useState } from "react";
import styled from "styled-components";

import RecordBox from "@/components/recordBox";
import RecordBtn from "@/components/recordBtn";
import SpeakBtn from "@/components/speakBtn";
import Modal from "@/components/modal";

import background from "@/assets/img/background/backgroundRecord.jpg";
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

const ParentLoginPage = () => {
  const [isAlertModalOpen, setIsAlertModalOpen] = useState(false); // 모달의 상태를 관리하는 state
  const [isAlarmModalOpen, setIsAlarmModalOpen] = useState(false);

  const OpenAlertModal = () => {
    setIsAlertModalOpen(true); // ProfileCircle을 클릭하면 모달을 엽니다.
  };

  const OpenAlarmModal = () => {
    setIsAlarmModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsAlertModalOpen(false); // 모달을 닫습니다.
    setIsAlarmModalOpen(false);
  };

  return (
    <>
      <Container>
        <Image src={Logo} alt="Background" />
        <div
          style={{
            fontSize: "3vh",
            color: "#fff",
            backgroundColor: "#F65F5F",
            padding: "1.2vh 2vh",
            borderRadius: "2vh",
            margin: "2vh 0vh",
          }}
        >
          22/300
        </div>
        <RecordBox />
        <div
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
          }}
        >
          <div>
            <RecordBtn />
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
            <SpeakBtn />
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
          subtitle={`300문장을 읽으면 아이에게 동화책을 읽어줄 수 있어요
          조용한 곳에서, 20~30cm 정도 떨어진 상태로 녹음해 주세요
          큰 소리로 또박또박 읽어주세요
          ( 실제로 읽어주는 목소리로 해주세요.)
          녹음 중 큰 소음이 녹음된 경우 해당 문장을 다시 녹음해주세요`}
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
    </>
  );
};

export default ParentLoginPage;
