import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styled, { keyframes } from "styled-components";
import { useSoundIntro } from "@/components/sounds/introSound";
import { MainSoundState } from "@/states/common/voice";
import { useRecoilState } from "recoil";

const fadeIn = keyframes`
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
    visibility: visible;
  }
`;

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
  background: #fbf5f2;
`;

const bounce = keyframes`
  100% {
    top: -20px;
  }
`;

const bounceAnimation = (color: string, customShadow?: string) => keyframes`
  100% {
    top: -20px;
    text-shadow: 0 1px 0 ${color}, 0 2px 0 ${color}, 0 3px 0 ${color}, 0 4px 0 ${color}, 0 5px 0 ${color}, 0 6px 0 ${color}, 0 7px 0 ${color}, 0 8px 0 ${color}, 0 9px 0 ${color}, 0 10px 0 ${color}, 0 11px 0 ${color}, 0 12px 0 ${color}, 0 13px 0 ${color}, 0 14px 0 ${color}, 0 15px 0 ${color}, ${customShadow};
  }
`;

const AnimatedTextWrapper = styled.h1`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  margin-top: 8vh;
`;

const StyledButton = styled.div`
  background: #ffd283;
  color: #aa7314;
  padding: 2.5vh 4vh;
  font-size: 5vh;
  border-radius: 20px;
  border-bottom: 12px solid #aa7314;
  margin-top: 15vh;
  visibility: hidden;
  animation: ${fadeIn} 1s ease 2s forwards;

  &:active {
    border-bottom: 5px solid #aa7314;
    transform: translateY(5px);
  }

  &:hover {
    color: white;
    background: #ffac1d;
  }
`;

const AnimatedTextSpan = styled.span`
  position: relative;
  top: 20px;
  display: inline-block;
  animation: ${bounceAnimation("#ff5733")} 0.3s ease infinite alternate; /* Adjust the color for the first letter */
  font-family: "Titan One", cursive;
  font-size: 15vh;
  line-height: 110px;
  color: #fff;

  &:nth-child(1) {
    animation: ${bounceAnimation("#c47446", "0 50px 25px #c4744660")} 0.3s ease
      infinite alternate; /* Adjust the color for the second letter */
    color: #ff833a;
    animation-delay: 0s;
  }

  &:nth-child(2) {
    animation: ${bounceAnimation("#c47446", "0 50px 25px #c4744660")} 0.3s ease
      infinite alternate; /* Adjust the color for the second letter */
    color: #ff833a;
    animation-delay: 0.1s;
  }
  &:nth-child(3) {
    animation: ${bounceAnimation("#6c8d82", "0 50px 25px #6c8d8260")} 0.3s ease
      infinite alternate; /* Adjust the color for the third letter */
    animation-delay: 0.2s;
    color: #77b29f;
  }
  &:nth-child(4) {
    animation: ${bounceAnimation("#6c8d82", "0 50px 25px #6c8d8260")} 0.3s ease
      infinite alternate; /* Adjust the color for the third letter */
    animation-delay: 0.3s;
    color: #77b29f;
  }
  &:nth-child(5) {
    animation: ${bounceAnimation("#6c8d82", "0 50px 25px #6c8d8260")} 0.3s ease
      infinite alternate; /* Adjust the color for the third letter */
    animation-delay: 0.4s;
    color: #77b29f;
  }
  &:nth-child(6) {
    animation: ${bounceAnimation("#8860ab", "0 50px 25px #8860ab60")} 0.3s ease
      infinite alternate; /* Adjust the color for the third letter */
    animation-delay: 0.5s;
    color: #ab70df;
  }
  &:nth-child(7) {
    animation: ${bounceAnimation("#8860ab", "0 50px 25px #8860ab60")} 0.3s ease
      infinite alternate; /* Adjust the color for the third letter */
    animation-delay: 0.6s;
    color: #ab70df;
  }
  &:nth-child(8) {
    animation: ${bounceAnimation("#5f70b5", "0 50px 25px #5f70b560")} 0.3s ease
      infinite alternate; /* Adjust the color for the third letter */
    animation-delay: 0.7s;
    color: #6884f6;
  }
  &:nth-child(9) {
    animation: ${bounceAnimation("#5f70b5", "0 50px 25px #5f70b560")} 0.3s ease
      infinite alternate; /* Adjust the color for the third letter */
    animation-delay: 0.8s;
    color: #6884f6;
  }
  &:nth-child(10) {
    animation: ${bounceAnimation("#5f70b5", "0 50px 25px #5f70b560")} 0.3s ease
      infinite alternate; /* Adjust the color for the third letter */
    animation-delay: 0.9s;
    color: #6884f6;
  }
  // Add similar rules for the other letters...
`;

const IntroPage = () => {
  const navigate = useNavigate();
  const { playIntroSound } = useSoundIntro();
  const [isPlaying, setIsPlaying] = useRecoilState(MainSoundState);

  const handleButtonClick = () => {
    playIntroSound();
    setTimeout(() => {
      setIsPlaying(true);
      navigate("/main");
    }, 2000); // 3000 milliseconds = 3 seconds
  };

  return (
    <Container>
      <AnimatedTextWrapper>
        <AnimatedTextSpan>D</AnimatedTextSpan>
        <AnimatedTextSpan>o</AnimatedTextSpan>
        <AnimatedTextSpan>R</AnimatedTextSpan>
        <AnimatedTextSpan>A</AnimatedTextSpan>
        <AnimatedTextSpan>N</AnimatedTextSpan>
        <AnimatedTextSpan>D</AnimatedTextSpan>
        <AnimatedTextSpan>O</AnimatedTextSpan>
        <AnimatedTextSpan>R</AnimatedTextSpan>
        <AnimatedTextSpan>A</AnimatedTextSpan>
        <AnimatedTextSpan>N</AnimatedTextSpan>
      </AnimatedTextWrapper>
      <StyledButton onClick={handleButtonClick}>들어가기</StyledButton>
    </Container>
  );
};

export default IntroPage;
