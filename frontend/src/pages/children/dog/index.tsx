import { useState } from "react";
import styled, { keyframes, css } from "styled-components";
import redHeart from "@/assets/img/dogModal/redheart.png";
import greyHeart from "@/assets/img/dogModal/greyheart.png";
import arrowLeft from "@/assets/img/fairytale/arrowLeft.png";
import arrowRight from "@/assets/img/fairytale/arrowRight.png";
import ReplayModal from "@/components/replayModal";

const orange = "#F7AA2B";
const brown = "#CB7A1D";
const nonWhite = "#FEFEFE";
const red = "#F5534F";
const grey = "#e8e7ec";
const darkblue = "#757599";
const blue = "#5777C9";
const dark = "#451d1c";

const Background = styled.div<{ isHovered: boolean; isDisliked: boolean }>`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-color: ${(props) =>
    props.isDisliked ? darkblue : props.isHovered ? red : blue};
  display: flex;
  justify-content: center;
  align-items: center;
  transition: background-color 0.3s ease;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const transition = "0.2s ease-in";

const moveTongue = keyframes`
  0% {
    height: 2vh; 
  }

  100% {
    height: 2.7vh;
  }
`;

const moveTail = keyframes`
  0% {
    transform: rotate(37deg);
  }
  100% {
    transform: rotate(52deg);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
    
  }
`;

const cry = keyframes`
  100% {
    visibility: visible;
  }
`;

const Wrapper = styled.div`
  ${transition};
  display: flex;
  align-items: center;
  justify-content: center;
  overflow-y: hidden;
  margin-top: 15vh;
  width: 100%;
  height: 100%;
`;

const CardContainer = styled.div`
  position: relative;
  width: 36vh;
  height: 48vh;
  margin: auto;
  padding-top: 12.5vh;
  border-radius: 3%;
  background: #fff;
  z-index: 0;
  ${transition}

  &::before,
  &::after {
    content: "";
    position: absolute;
    height: 100%;
    margin: auto;
    left: 0;
    right: 0;
    border-radius: 3%;
    z-index: -1;
  }

  &::before {
    top: 3%;
    width: 93%;
    background: rgba(255, 255, 255, 0.7);
  }

  &::after {
    top: 5.5%;
    width: 85%;
    background: rgba(255, 255, 255, 0.35);
  }
`;

const Dog = styled.div<{ isHovered: boolean; isDisliked: boolean }>`
  .head,
  .body {
    position: relative;
    width: 11.5vh;
  }

  .head {
    height: 11.5vh;
    border-radius: 50% 50% 0 0;
    margin: 0 auto;
  }

  .ears {
    position: relative;
    top: -14%;
    width: 100%;

    &::before,
    &::after {
      ${transition};
      content: "";
      position: absolute;
      top: 0;
      width: 3.5vh;
      height: 7vh;
      background: ${brown};
      border-top: 1.1vh solid ${orange};
      border-left: 0.7vh solid ${orange};
      border-right: 0.7vh solid ${orange};
      transition: transform 0.3s ease;
    }

    &::before {
      left: 0;
      border-radius: 50% 45% 0 0;
      transform: rotate(${(props) => (props.isDisliked ? "-50deg" : "")})
        translate(${(props) => (props.isDisliked ? "-0.7vh, 0.2vh" : "")});
    }

    &::after {
      right: 0;
      border-radius: 45% 50% 0 0;
      transform: rotate(${(props) => (props.isDisliked ? "50deg" : "")})
        translate(${(props) => (props.isDisliked ? "0.7vh, 0.2vh" : "")});
    }
  }

  .face {
    position: absolute;
    background: ${orange};
    width: 100%;
    height: 100%;
    border-radius: 50% 50% 0 0;

    &::before,
    &::after {
      ${transition};
      content: "";
      display: block;
      margin: auto;
      background: ${nonWhite};
      transition:
        height 0.3s ease,
        margin-top 0.3s ease;
    }

    &::before {
      width: 1.5vh;
      height: 3.5vh;
      margin-top: ${(props) =>
        props.isDisliked ? "2.8vh" : props.isHovered ? "1vh" : "2.4vh"};
      border-radius: 2vh 2vh 0 0;
    }

    &::after {
      position: absolute;
      bottom: -0.1vh;
      left: 0;
      right: 0;
      width: 6vh;
      height: ${(props) =>
        props.isDisliked ? "5.5vh" : props.isHovered ? "8.5vh" : "6.5vh"};
      border-radius: 45% 45% 0 0;
    }
  }

  .eyes {
    ${transition};
    transition: 0.3s ease;
    position: relative;
    top: ${(props) =>
      props.isDisliked ? "38%" : props.isHovered ? "13%" : "29%"};
    text-align: center;

    &::before,
    &::after {
      content: "";
      display: inline-block;
      width: ${(props) =>
        props.isDisliked ? "0.5vh" : props.isHovered ? "0.5vh" : "1.2vh"};
      height: ${(props) =>
        props.isDisliked ? "2vh" : props.isHovered ? "2vh" : "1.2vh"};
      border-radius: 100%;
      background: ${dark};
      margin: 0
        ${(props) =>
          props.isDisliked ? "20%" : props.isHovered ? "20%" : "14.5%"};

      transform: rotate(
        ${(props) =>
          props.isDisliked ? "60deg" : props.isHovered ? "60deg" : ""}
      );
      border-radius: ${(props) =>
        props.isDisliked ? "2vh" : props.isHovered ? "2vh" : ""};
    }

    &::after {
      transform: rotate(
        ${(props) =>
          props.isDisliked ? "-60deg" : props.isHovered ? "-60deg" : ""}
      );
    }
  }

  .teardrop {
    ${transition};

    position: absolute;
    top: 80%;
    left: 18%;
    width: 0.6vh;
    height: 0.6vh;
    border-radius: 0 50% 50% 50%;
    transform: rotate(45deg);
    background: ${nonWhite};
    visibility: ${(props) => (props.isDisliked ? "visible" : "hidden")};
    transition: visibility 0.1s ease;
  }

  .nose {
    ${transition};
    position: relative;
    top: ${(props) =>
      props.isDisliked ? "44%" : props.isHovered ? "18%" : "35%"};
    width: 1.6vh;
    height: 0.8vh;
    border-radius: 3.5vh 3.5vh 6.5vh 6.5vh;
    background: ${dark};
    margin: auto;
    transition: top 0.3s ease;
  }

  .mouth {
    ${transition};
    position: relative;
    top: ${(props) =>
      props.isDisliked ? "42%" : props.isHovered ? "16.5%" : "34.5%"};
    width: 0.5vh;
    height: 0.9vh;
    margin: 0 auto;
    text-align: center;
    background: ${dark};
    transition: top 0.3s ease;

    &::before,
    &::after {
      content: "";
      position: absolute;
      top: -0.5vh;
      width: 1.4vh;
      height: 1.6vh;
      border-radius: 50%;
      border: 0.4vh solid ${dark};
      border-left-color: transparent;
      border-top-color: transparent;
      z-index: 2;
    }

    &::before {
      transform: translateX(-89%) rotate(45deg);
    }

    &::after {
      transform: translateX(-0.1vh) rotate(45deg);
    }
  }

  .tongue {
    position: relative;
    z-index: 1;
    transition: animation 0.3s ease;

    &::before,
    &::after {
      content: "";
      position: absolute;
      ${transition};
    }

    &::before {
      top: 1vh;
      left: -0.7vh;
      width: 1.8vh;
      height: 0;
      border-radius: 50%;
      background: ${dark};
      z-index: -1;
    }

    &::after {
      top: 1.4vh;
      left: -0.4vh;
      width: 1.2vh;
      height: 0;
      border-radius: 2vh;
      background: ${red};
      z-index: 5;
      animation: ${moveTongue} 0.1s linear 0.35s infinite alternate forwards;
    }
  }

  .chin {
    ${transition};
    position: relative;
    top: ${(props) =>
      props.isDisliked ? "52%" : props.isHovered ? "47.5%" : "47.5%"};

    margin: 0 auto;
    width: 1.2vh;
    height: 1.2vh;
    border-top: 1vh solid ${grey};
    border-left: 0.5vh solid transparent;
    border-right: 0.5vh solid transparent;
    border-radius: 0.2vh;
    z-index: 0;
    visibility: ${(props) =>
      props.isDisliked ? "visible" : props.isHovered ? "hidden" : "visible"};
  }

  .body {
    position: relative;
    height: 13.9vh;
    margin: auto;
    z-index: 0;

    &::before,
    &::after {
      content: "";
      position: absolute;
      top: -0.1vh;
      left: 0;
      right: 0;
      bottom: 0;
      display: block;
      width: 100%;
      margin: auto;
      background: ${orange};
    }

    &::after {
      top: -0.2vh;
      bottom: -0.1vh;
      width: 6vh;
      background: ${nonWhite};
    }
  }

  .tail {
    ${transition};
    position: absolute;
    left: -60%;
    bottom: 0.1vh;
    background: ${orange};
    width: 9.3vh;
    height: 1.5vh;
    transform: rotate(
      ${(props) =>
        props.isDisliked ? "0" : props.isHovered ? "45deg" : "45deg"}
    );
    transform-origin: 100% 50%;
    border-radius: 2.5vh 0 0 2.5vh;
    z-index: -2;
    ${(props) =>
      !props.isDisliked &&
      css`
        animation: ${moveTail} 0.1s linear infinite alternate forwards;
      `}
  }

  .legs {
    ${transition};
    position: absolute;
    bottom: 0;
    left: -10%;
    width: 120%;
    height: 15%;
    background: ${orange};
    border-radius: 1vh 1vh 0 0;

    &::before,
    &::after {
      content: "";
      position: absolute;
      bottom: 0.1vh;
      background: ${brown};
      z-index: -1;
    }

    &::before {
      left: -7.5%;
      width: 115%;
      height: 55%;
      border-radius: 0.5vh 0.5vh 0 0;
    }

    &::after {
      left: -3.5%;
      width: 107%;
      height: 250%;
      border-radius: 2vh 2vh 3.5vh 3.5vh;
    }
  }
`;

const Button = styled.button`
  width: 25vh;
  height: 25vh;
  position: absolute;
  top: 45%;
  margin-top: -3.4vh;
  padding-top: 1vh;
  outline: none;
  border: none;
  border-radius: 50%;
  box-shadow: 0 0.5vh 1vh rgba(0, 0, 0, 0.1);
  background: ${nonWhite};
  z-index: 5;
  ${transition}

  &:hover {
    transform: scale(1.05);
  }
`;

const ButtonLike = styled(Button)`
  right: 10%;
  ${transition}

  @media (min-width: 76.8vh) {
    left: calc(50% + 22.5vh);
    right: initial;
  }

  &:hover {
  }
`;

const ButtonDislike = styled(Button)`
  left: 10%;
  ${transition}

  @media (min-width: 76.8vh) {
    left: calc(50% - 29.3vh);
  }

  &::after {
    content: "";
    position: absolute;
    transform: translate(-50%, -50%) rotate(-45deg);
    top: 45%;
    left: 49%;
    width: 1vh;
    height: 15vh;
    background: #b5b5b5;
    border: 0.5vh solid ${nonWhite};
  }

  .heart::before,
  .heart::after {
    background-color: #b5b5b5;
  }
`;

const Heart = styled.img`
  position: relative;
  display: inline-block;
  top: 15%;
  width: 15vh;
  height: 15vh;
`;

const DisHeart = styled.img`
  position: relative;
  display: inline-block;
  top: 15%;
  width: 15vh;
  height: 15vh;
`;

const QuestionBook = styled.div`
  font-size: 9vh;
  position: absolute;
  top: 12%;
  color: white;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
`;

const QuestionText = styled.div`
  font-size: 6vh;
  margin-top: 11vh;
  font-family: katuri;
  color: white;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
`;

const DogLike = () => {
  const [isHovered, setIsHovered] = useState(false);
  const [isDisliked, setIsDisliked] = useState(false);
  const [isReplayModal, setIsReplayModal] = useState(false);

  const handleButtonLike = () => {
    setIsHovered(!isHovered);
    setIsDisliked(false);

    setTimeout(() => {
      setIsReplayModal(true);
    }, 1250);
  };

  const handleButtonDislike = () => {
    setIsDisliked(!isDisliked);
    setIsHovered(false);

    setTimeout(() => {
      setIsReplayModal(true);
    }, 1250);
  };

  return (
    <Background isHovered={isHovered} isDisliked={isDisliked}>
      <QuestionBook>동화책 재밌었나요?</QuestionBook>
      <ContentContainer>
        <Wrapper>
          <ButtonLike className="btn-like" onClick={handleButtonLike}>
            <Heart className="heart" src={redHeart} />
            <QuestionText>재밌어요!</QuestionText>
          </ButtonLike>
          <ButtonDislike className="btn-dislike" onClick={handleButtonDislike}>
            <DisHeart className="heart" src={greyHeart} />
            <QuestionText>별로예요..</QuestionText>
          </ButtonDislike>
          <CardContainer className="card-container">
            <Dog isHovered={isHovered} isDisliked={isDisliked} className="dog">
              <div className="head">
                <div className="ears"></div>
                <div className="face"></div>
                <div className="eyes">
                  <div className="teardrop"></div>
                </div>
                <div className="nose"></div>
                <div className="mouth">
                  <div className={`${isHovered ? "tongue" : ""}`}></div>
                </div>
                <div className="chin"></div>
              </div>
              <div className="body">
                <div className="tail"></div>
                <div className="legs"></div>
              </div>
            </Dog>
          </CardContainer>
        </Wrapper>
        {isReplayModal && <ReplayModal />}
      </ContentContainer>
    </Background>
  );
};

export default DogLike;
