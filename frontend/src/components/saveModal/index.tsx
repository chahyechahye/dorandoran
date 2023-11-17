import React from "react";
import styled from "styled-components";
import { ButtonEffect } from "@/styles/buttonEffect";

interface ModalProps {
  title: string;
  subtitle: string;
  bgColor: string;
  onClose: () => void;
  onResume: () => void;
  onRestart: () => void;
}

const Container = styled.div`
  position: fixed;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  left: 0;
  right: 0;
  margin: 0 auto;
  z-index: 0;
`;

const ModalWrapper = styled.div`
  background-color: #4fcdc7;
  width: 100vh;
  padding: 4vh;
  border-radius: 2vh;
  position: relative;
`;

const Title = styled.h1`
  font-size: 8vh;
  color: #fff;
  margin: 1vh 0;
`;

const Subtitle = styled.p`
  width: 100%;
  line-height: 6vh;
  font-size: 4vh;
  color: #fff;
  margin: 3vh 0;
`;

const StartButton = styled.div`
  padding: 2.5vh 5vh;
  background-color: ${(props) => props.color || "#f0d278"};
  color: #fff;
  font-size: 4.8vh;
  margin-left: 6vh;
  border-radius: 1vh;

  ${ButtonEffect}
`;

const ReButton = styled.div`
  padding: 2.5vh 5vh;
  background-color: ${(props) => props.color || "#FC7292"};
  color: #fff;
  font-size: 4.8vh;
  margin-left: 1vh;
  border-radius: 1vh;

  ${ButtonEffect}
`;

const SaveModal = ({
  title,
  subtitle,
  onClose,
  onResume,
  onRestart,
}: ModalProps) => {
  const formattedSubtitle = subtitle.split("\n").map((line, idx) => (
    <React.Fragment key={idx}>
      {line}
      {idx !== subtitle.split("\n").length - 1 && <br />}
    </React.Fragment>
  ));

  return (
    <Container>
      <ModalWrapper>
        <Title>{title}</Title>
        <Subtitle>{formattedSubtitle}</Subtitle>
        <div
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          <ReButton onClick={onResume}>이어하기</ReButton>
          <StartButton onClick={onRestart}>처음부터</StartButton>
        </div>
      </ModalWrapper>
    </Container>
  );
};

export default SaveModal;
