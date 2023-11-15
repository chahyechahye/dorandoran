import styled, { keyframes } from "styled-components";
import background from "@/assets/img/background/background.jpg";

import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { fairytaleReadState, fairytaleState } from "../../states/children/info";
import bookCover from "@/assets/img/fairytale/fairytaleCover.png";

const fadeIn = keyframes`
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
`;

// Styled Components
const CardContainer = styled.div`
  position: fixed;
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.3);
  animation: ${fadeIn} 0.7s ease; // CSS 애니메이션 적용
`;

const Card = styled.div`
  color: #013243; /*SHERPA BLUE*/
  position: absolute;
  top: 50%;
  left: 50%;
  width: 45vh;
  height: 60vh;
  background: #e0e1dc;
  transform-style: preserve-3d;
  transform: translate(-50%, -50%) perspective(200vh);
  box-shadow:
    inset 30vh 0 5vh rgba(0, 0, 0, 0.5),
    2vh 0 6vh rgba(0, 0, 0, 0.5);
  transition: 1s;

  &.open {
    transform: translate(-50%, -50%) perspective(200vh) rotate(15deg) scale(1.2);
    box-shadow:
      inset 2vh 0 5vh rgba(0, 0, 0, 0.5),
      0 1vh 10vh rgba(0, 0, 0, 0.5);
  }

  &::before {
    content: "";
    position: absolute;
    top: -1vh;
    left: 0;
    width: 100%;
    height: 1vh;
    background: #bac1ba;
    transform-origin: bottom;
    transform: skewX(-45deg);
  }

  &::after {
    content: "";
    position: absolute;
    top: 0;
    right: -1vh;
    width: 1vh;
    height: 100%;
    background: #92a29c;
    transform-origin: left;
    transform: skewY(-45deg);
  }
`;

const ImgBox = styled.div`
  width: 100%;
  height: 100%;
  position: relative;
  transform-origin: left;
  transition: 1s;

  &.open {
    transform: rotateY(-135deg);
  }
`;

const Bark = styled.div`
  position: absolute;
  /* background: #e0e1dc; */
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: 1s;
  /* background-image: url(${bookCover}); */

  &.open {
    opacity: 1;
    transition: 0.6s;
    box-shadow: 30vh 20vh 10vh rgba(0, 0, 0, 0.4) inset;

    min-width: 37.5vh;
    max-height: 60vh;
  }
`;

const Img = styled.img`
  width: 100%;
  height: 100%;
  object-fit: fill;
`;

const Details = styled.img`
  position: absolute;
  top: 0;
  left: 0;
  box-sizing: border-box;
  z-index: -1;
  width: 100%;
  height: 100%;
  object-fit: cover;
`;

// React Component
const FariytaleEnter = () => {
  const [isOpen, setIsOpen] = useState(false);

  const fairytale = useRecoilValue(fairytaleState);
  const fairytaleRead = useRecoilValue(fairytaleReadState);

  const navigate = useNavigate();

  console.log(fairytaleRead[0]);
  console.log("패오라태알" + fairytale.title);
  useEffect(() => {
    const timer = setTimeout(() => {
      setIsOpen(true);
    }, 1000); // 3초 후에 카드를 열도록 설정

    return () => {
      clearTimeout(timer); // 컴포넌트 언마운트 시 타이머 클리어
    };
  }, []);

  useEffect(() => {
    if (isOpen) {
      const navigationTimer = setTimeout(() => {
        navigate("/children/read");
      }, 2000);

      return () => {
        clearTimeout(navigationTimer);
      };
    }
  }, [isOpen, navigate]);

  return (
    <CardContainer>
      <Card className={isOpen ? "open" : ""}>
        <ImgBox className={isOpen ? "open" : ""}>
          <Bark className={isOpen ? "open" : ""}></Bark>
          <Img src={bookCover} className={isOpen ? "open" : ""} />
          {/* <Img src={fairytale.imgUrl} className={isOpen ? "open" : ""} /> */}
        </ImgBox>
        <Details
          src={fairytale.imgUrl}
          className={isOpen ? "open" : ""}
        ></Details>
      </Card>
    </CardContainer>
  );
};

export default FariytaleEnter;
