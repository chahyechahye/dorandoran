import styled from "styled-components";
import { useState } from "react";

import ChildCard from "@/components/childCard";
import ProfileCircle from "@/components/profileCircle";
import Modal from "@/components/modal";
import ClickButton from "@/components/clickButton";

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
  margin-bottom: 3vh;
`;

const Image = styled.img`
  width: 45%;
  padding-bottom: 8vh;
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

const ParentProfilePage = () => {
  const [isRegistModalOpen, setIsRegistModalOpen] = useState(false); // 모달의 상태를 관리하는 state
  const [isSendInviteCode, setIsSendInviteCode] = useState(false);

  const OpenRegistModal = () => {
    setIsRegistModalOpen(true); // ProfileCircle을 클릭하면 모달을 엽니다.
  };

  const OpenInviteCodeModal = () => {
    setIsSendInviteCode(true);
  };

  const handleCloseModal = () => {
    setIsRegistModalOpen(false); // 모달을 닫습니다.
    setIsSendInviteCode(false);
  };

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
            onClick={OpenRegistModal}
          >
            <ProfileCircle />
          </div>
        </Content>
        <ClickButton
          width="60vh"
          height="10vh"
          backgroundColor="#FC7292"
          fontColor="white"
          fontSize="5vh"
          text="초대코드 보내기"
          onClick={OpenInviteCodeModal}
        ></ClickButton>
      </Container>
      {(isRegistModalOpen || isSendInviteCode) && (
        <Overlay onClick={handleCloseModal} />
      )}
      {/* 오버레이 렌더링 */}
      {isRegistModalOpen && (
        <Modal
          title="아이디 등록하기"
          subtitle="아이 이름을 입력하여 아이를 등록해주세요"
          placeholder="아이의 이름을 입력하세요"
          buttonText="생성하기"
          bgColor="#fc7292"
          buttonColor="#78bff0"
          showInput={true}
          onClose={handleCloseModal} // 모달 닫기를 위한 함수를 전달합니다.
        />
      )}
      {isSendInviteCode && (
        <Modal
          title="아이에게 보내기"
          subtitle="아이에게 초대코드를 보내서 동화책을 들려주세요"
          placeholder="전화번호를 입력해주세요"
          buttonText="보내기"
          bgColor="#4FCDC7"
          buttonColor="#F65F5F"
          showInput={true}
          onClose={handleCloseModal} // 모달 닫기를 위한 함수를 전달합니다.
        />
      )}
    </>
  );
};

export default ParentProfilePage;
