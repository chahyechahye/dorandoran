import React, { useState, useEffect } from "react";
import styled, { keyframes } from "styled-components";

import offSpeaker from "@/assets/img/offSpeaker.png";
import onSpeaker from "@/assets/img/onSpeaker.png";

const phoneAnimation = keyframes`
  0% {
    transform: scale(3.5);
  }
  100% {
    transform: scale(4);
  }
`;

const scalingAnimation = keyframes`
  0% {
    transform: scale(4);
    background-color: #FFB016;
  }
  100% {
    transform: scale(7);
    background-color: rgba(245, 0, 182, 0);
  }
`;

const Section = styled.section`
  height: 20vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Voice = styled.div<{ disabled?: boolean }>`
  z-index: 2;
  background: ${(props) =>
    !props.disabled
      ? "linear-gradient(to bottom, #ffd580 5%, #ffb016 100%)"
      : "#d9d9d9"};
  border-radius: 200px;
  padding: 1vh;
  animation: ${phoneAnimation} 1s cubic-bezier(0.12, 0.7, 0.74, 0.71) infinite
    alternate-reverse;
  display: grid;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 10px 14px -7px #777777;
  cursor: pointer;
  transition: background 0.3s ease; // Add this line for a smooth transition
`;

const Circle1 = styled.div`
  height: 4vh;
  width: 4vh;
  border-radius: 50%;
  background-color: #d9d9d9;
  position: absolute;
  animation: ${scalingAnimation} 2s cubic-bezier(0.12, 0.7, 0.74, 0.71) infinite;
  animation-delay: 0s;
`;

const Circle2 = styled.div`
  height: 4vh;
  width: 4vh;
  border-radius: 50%;
  background-color: #d9d9d9;
  position: absolute;
  animation: ${scalingAnimation} 2s cubic-bezier(0.12, 0.7, 0.74, 0.71) infinite;
  animation-delay: 1s;
`;

const SpeakBtn = ({
  onClick,
  disabled,
  soundEnd,
}: {
  onClick: () => void;
  disabled: boolean;
  soundEnd: boolean;
}) => {
  const [flag, setFlag] = useState(false);

  useEffect(() => {
    if (soundEnd) {
      setFlag(false);
    }
  }, [soundEnd]);

  const handleClick = () => {
    if (!flag && !disabled) {
      setFlag(true);
      onClick();
    }
  };

  return (
    <Section>
      <Voice disabled={disabled} onClick={handleClick}>
        {flag ? (
          <img
            src={onSpeaker}
            alt="역에서 탑승가능한 버스를 보는 버튼"
            width="24"
          />
        ) : (
          <img
            src={offSpeaker}
            alt="역에서 탑승가능한 버스를 보는 버튼"
            width="24"
          />
        )}
      </Voice>
      {flag && (
        <>
          <Circle1></Circle1>
          <Circle2></Circle2>
        </>
      )}
    </Section>
  );
};

export default SpeakBtn;
