import styled, { keyframes } from "styled-components";
import background from "@/assets/img/background/backgroundFairytale.png";
import character from "@/assets/img/fox.png";

// Styled Components
const Wrapper = styled.div`
  width: 70%;
  margin: 0 auto;
  background-color: lightgray;
`;

const Title = styled.h1`
  font-size: 60px;
  text-align: center;
  margin-top: 10vh;
  transition: 1.5s all;
`;

// 넘어갈 때 그라데이션
const Body = styled.div`
  margin: 0;
  transition: background-color 0.4s linear;
`;

const Card = styled.div`
  text-align: center;
  position: fixed;
  top: 25vh;
  bottom: 0;
  left: 0;
  right: 0;
  background: transparent;
`;

const CardContent = styled.div`
  display: flex;
  flex-direction: column;
`;

const move1 = keyframes`
  0% {
    transform: translate(0, 0);
  }
  50% {
    transform: translate(-5px);
  }
  100% {
    transform: translate(5px, 0);
  }
`;

const Circle = styled.div`
  height: 150px;
  width: 150px;
  border-radius: 50%;
  background: red;
  z-index: -1;
  position: absolute;
  top: 1vh;
  left: 43.5vw;
  background-image: url("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi.pinimg.com%2Foriginals%2F7d%2Fa6%2F96%2F7da696f1987d39249d42946f331ccc1b.jpg&f=1&nofb=1");
  background-size: cover;
  border: 2px solid;
  animation: ${move1} 5s linear 0s infinite alternate;
`;

const Image = styled.img`
  height: 200px;
  z-index: 20;
`;

const CardTitle = styled.h2`
  font-family: "Simpsonfont";
  font-size: 30px;
`;

const CardSubtitle = styled.p`
  font-family: "Open Sans";
  font-size: 20px;
  text-transform: lowercase;
  font-style: italic;
`;

const PrevButton = styled.button`
  display: block;
  width: 70px;
  height: 70px;
  background-color: rgba(0, 0, 0, 0.2);
  border: none;
  color: rgba(255, 255, 255, 0.7);
  border-radius: 100%;
  transition: all 0.3s linear;
  overflow: hidden;
  position: absolute;
  top: 20vh;
  left: 0;
`;

const NextButton = styled(PrevButton)`
  left: auto;
  right: 0;
`;

const Dots = styled.div`
  position: absolute;
  left: 0;
  right: 0;
  top: 58vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Dot = styled.button`
  background-color: rgba(255, 255, 255, 0.2);
  width: 20px;
  height: 20px;
  text-indent: -9999em;
  overflow: hidden;
  border: none;
  border-radius: 100%;
  transition: all 0.3s linear;
  margin: 0 8px;
  cursor: pointer;
`;

const ActiveDot = styled(Dot)`
  background-color: rgba(255, 255, 255, 0.4);
`;

const Spot = styled.div`
  background-color: rgba(255, 255, 255, 1);
  width: 10px;
  height: 10px;
  border-radius: 10px;
  top: 5px;
  z-index: -1;
  position: absolute;
`;

const Background = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
`;

const CardsContainer = styled.div`
  visibility: hidden;
  opacity: 0;
  height: 100vh;
  position: relative;
`;

// React Component
const ChildrenFairytalePage = () => {
  return (
    <Body>
      <Background>
        <Wrapper>
          <Title>어린 왕자</Title>
          <CardsContainer>
            <PrevButton>
              <span>Prev</span>
            </PrevButton>
            <NextButton>
              <span>Next</span>
            </NextButton>
            <Card className="card card00">
              <CardContent>
                <Circle></Circle>
                <Image src="https://assets.codepen.io/489403/swsb_character_fact_homer_550x960.png" />
                <CardTitle>Homer Simpson</CardTitle>
                <CardSubtitle></CardSubtitle>
              </CardContent>
            </Card>
            <ActiveDot />
          </CardsContainer>
        </Wrapper>
      </Background>
      <Dots>
        <Spot></Spot>
        <Dot></Dot>
        <Dot></Dot>
        <Dot></Dot>
        <Dot></Dot>
        <Dot></Dot>
        <Dot></Dot>
        <Dot></Dot>
        <Dot></Dot>
        <Dot></Dot>
      </Dots>
    </Body>
  );
};

export default ChildrenFairytalePage;
