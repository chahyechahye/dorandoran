import React, { useCallback, useEffect, useState } from "react";
import styled from "styled-components";
import background from "@/assets/img/background/backgroundFairytale.png";
import character from "@/assets/img/fox.png";
import { useNavigate } from "react-router-dom";
import FariytaleEnter from "@/components/fairytaleEnter";

const Background = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url(${background});
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ContentContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Character = styled.img`
  width: 15%;
  position: absolute;
  z-index: 6;
  top: 71%;
  left: 82%;
`;

const FairytaleContainer = styled.div`
  width: 30vh;
  height: 30vh;
  background-color: black;
  cursor: pointer; // 커서를 포인터로 변경하여 클릭 가능하게 함
`;

const ChildrenFairytalePage = () => {
  const [isModalOpen, setIsModalOpen] = useState(false); // 초기값을 false로 설정
  const navigation = useNavigate();

  const openModal = () => {
    setIsModalOpen(true);
    setTimeout(() => {
      GoFairytale();
    }, 3000);
  };

  const GoFairytale = useCallback(() => {
    navigation("/children/read");
  }, [navigation]);

  // useEffect(() => {
  //   if (isModalOpen) {
  //     GoFairytale();
  //   }
  // }, [isModalOpen, GoFairytale]);

  return (
    <Background>
      <ContentContainer>
        <FairytaleContainer onClick={openModal}></FairytaleContainer>
        <Character src={character} />
        {isModalOpen && <FariytaleEnter />}
      </ContentContainer>
    </Background>
  );
};

export default ChildrenFairytalePage;
