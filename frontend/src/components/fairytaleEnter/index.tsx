import styled from "styled-components";
import title from "@/assets/img/background.jpg";

// Styled Components
const CardContainer = styled.div`
  margin: 0;
  padding: 0;
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

  &:hover {
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
  transition: 0.7s;

  ${Card}:hover & {
    transform: rotateY(-135deg);
  }
`;

const Bark = styled.div`
  position: absolute;
  background: #e0e1dc;
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: 0.7s;

  ${Card}:hover & {
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
  object-fit: fill;
`;

// React Component
const CardComponent = () => {
  return (
    <CardContainer>
      <Card>
        <ImgBox>
          <Bark></Bark>
          <Img src={title} />
        </ImgBox>
        <Details src={title}></Details>
      </Card>
    </CardContainer>
  );
};

export default CardComponent;
