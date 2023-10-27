import styled, { keyframes } from "styled-components";
import background from "@/assets/img/background/childMain.png";
import castle from "@/assets/img/childMain/castle.png";
import postOffice from "@/assets/img/childMain/postoffice.png";
import camera from "@/assets/img/childMain/camera.png";
import books from "@/assets/img/childMain/books.png";

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

/*----------------------------------
	Main
----------------------------------*/
const Container = styled.div`
  background: linear-gradient(
    180deg,
    #1d95f5 0%,
    rgba(255, 255, 255, 0) 78.65%
  );
  width: 100%;
  height: 100%;
  position: absolute;
  overflow: hidden;
`;

/*----------------------------------
	Cloud
----------------------------------*/
const cloudAnimation = keyframes`
  0% {
    left: 7%;
  }
  50% {
    left: 80%;
  }
  100% {
    left: 7%;
  }
`;

const Cloud = styled.div`
  background: #d2efff;
  display: block;
  position: absolute;
  border-radius: 100%;
  width: 17vh;
  height: 6vh;
  z-index: 1;

  &::before {
    content: "";
    background: #d2efff;
    display: block;
    position: absolute;
    border-radius: 50%;
    width: 12.5vh;
    height: 6.5vh;
  }

  &::after {
    content: "";
    background: #d2efff;
    display: block;
    position: absolute;
    border-radius: 100%;
    top: -3vh;
    left: 3vh;
    width: 10vh;
    height: 9vh;
  }

  &:nth-child(1) {
    left: 100%;
    top: 15%;
    animation: ${cloudAnimation} 15s linear infinite;
  }
  // Define other clouds using Cloud component, adjusting properties as needed.
  &:nth-child(2) {
    top: 30%;
    width: 21vh;
    height: 10vh;
    &::before {
      top: 40%;
      left: -20%;
      width: 28vh;
      height: 10vh;
    }
    &::after {
      left: 20%;
      width: 13vh;
      height: 6.5vh;
    }
    animation: ${cloudAnimation} 12s linear infinite;
  }
  &:nth-child(3) {
    left: 60%;
    top: 25%;
    width: 11vh;
    height: 6vh;
    &::before {
      width: 15vh;
      height: 7vh;
    }
    &::after {
      width: 9vh;
      height: 8vh;
    }
    animation: ${cloudAnimation} 10s linear infinite;
  }
`;

/*----------------------------------
	Ground
----------------------------------*/
// const Ground = styled.div`
//   position: absolute;
//   width: 120%;
//   height: 100vh;
//   border-radius: 100%;
//   background: #5b916e;
//   bottom: -90vh;
// `;

// /*----------------------------------
// 	Tree
// ----------------------------------*/
// const Tree = styled.div`
//   width: 10vh;
//   height: 50vh;
//   background: #c0b088;
//   z-index: 1;
//   display: block;
//   position: absolute;
//   transform: rotate(6deg);
//   bottom: 20vh;
// `;

// const Leaves = styled.div`
//   content: "";
//   background: #349638;
//   border-radius: 100%;
//   width: 70vh;
//   top: -20vh;
//   height: 30vh;
//   position: absolute;
//   left: -30vh;
//   z-index: 1;

//   &:before {
//     content: "";
//     background: #349638;
//     border-radius: 100%;
//     width: 53vh;
//     top: -9vh;
//     left: 9vh;
//   }

//   &:after {
//     content: "";
//     background: #349638;
//     border-radius: 100%;
//     width: 34vh;
//     top: -14vh;
//     left: 19vh;
//   }
// `;

// Repeat for other trees.

/*----------------------------------
	Airplane
----------------------------------*/
const planeFlyAnimation = keyframes`
  0% {
    left: -10%;
    transform: scale(0.4) rotateY(0deg);
  }
  50% {
    left: 110%;
    transform: scale(1) rotateY(0deg);
  }
  51% {
    transform: scale(1) rotateY(180deg);
  }
  100% {
    left: -10%;
    transform: scale(1.4) rotateY(180deg);
  }
`;

const Airplane = styled.div`
  position: absolute;
  left: 40%;
  top: 10%;
  z-index: 3;
  animation: ${planeFlyAnimation} 10s linear infinite;
  transform-origin: center center;
`;

const Head = styled.div`
  background: #f9fbfc;
  border-radius: 100%;
  width: 6vh;
  height: 2.5vh;
  position: absolute;
  z-index: 1;
  top: 3.1vh;
  left: 5.2vh;
`;

const Body = styled.div`
  background: #f9fbfc;
  border-radius: 40% 30% 20% 50%;
  width: 13vh;
  height: 2.6vh;
  position: absolute;
  top: 3vh;
  left: -2.5vh;
  z-index: 1;
  transform: rotate(0deg);
  transform-origin: center center;
`;

const LeftWing = styled.div`
  background: #f9fbfc;
  height: 2.7vh;
  width: 3.5vh;
  border-radius: 0.5vh;
  position: absolute;
  top: 1.3vh;
  left: 3vh;
  z-index: 0;
  -webkit-transform: skew(51deg, 0deg);
  -ms-transform: skew(51deg, 0deg);
  -o-transform: skew(51deg, 0deg);
  transform: skew(51deg, 0deg);
`;

const RightWing = styled.div`
  background: #f9fbfc;
  position: absolute;
  top: 4.8vh;
  left: 3vh;
  height: 2.5vh;
  width: 3.5vh;
  border-radius: 0.5vh;
  z-index: 1;
  box-shadow: 0px 6px 4px rgba(0, 0, 0, 0.16);
  -webkit-transform: skew(-49deg, 0deg);
  -ms-transform: skew(-49deg, 0deg);
  -o-transform: skew(-49deg, 0deg);
  transform: skew(-49deg, 0deg);
`;

const Tail = styled.div`
  background: linear-gradient(0deg, #fff, #bbdeff);
  border-radius: 0.2vh;
  width: 1.6vh;
  height: 1.6vh;
  position: absolute;
  left: -1vh;
  top: 2.2vh;
  -webkit-transform: skew(35deg, -9deg);
  -ms-transform: skew(35deg, -9deg);
  -o-transform: skew(35deg, -9deg);
  transform: skew(35deg, -9deg);
  transform-origin: center center;
`;

const Window = styled.div`
  background: #9ad0f5;
  border-radius: 30%;
  width: 0.8vh;
  height: 0.8vh;
  position: absolute;
`;

const Window1 = styled(Window)`
  top: 1vh;
  left: 4vh;
`;

const Window2 = styled(Window)`
  top: 1vh;
  left: 5vh;
`;

const Window3 = styled(Window)`
  top: 1vh;
  left: 6vh;
`;

const Window4 = styled(Window)`
  top: 1vh;
  left: 7vh;
`;

const Window5 = styled(Window)`
  top: 1vh;
  left: 8vh;
`;
const Window6 = styled(Window)`
  top: 1vh;
  left: 9vh;
`;

const Window7 = styled(Window)`
  border-top-right-radius: 8px;
  top: 1vh;
  left: 10.5vh;
`;

const CastleContainer = styled.div`
  z-index: 4;
  position: relative;
  display: flex;
  justify-content: space-around;
  align-items: center;
`;

const Castle = styled.img`
  width: 100%;
`;

const Camera = styled.img`
  width: 20%;
  z-index: 5;
  position: absolute;
  top: 50%;
  left: 43%;
`;
const PostOffice = styled.img`
  width: 24%;
  position: absolute;
  z-index: 5;
  top: 35%;
  left: 14%;
`;
const Books = styled.img`
  width: 18%;
  position: absolute;
  z-index: 5;
  top: 24%;
  left: 68%;
`;

// Now you can use these styled components in your React application.

const ChildrenMainPage = () => {
  return (
    <Background>
      <ContentContainer>
        <Container>
          <Cloud />
          <Cloud />
          <Cloud />
        </Container>
        <Airplane>
          <Head />
          <Body>
            <Window1 />
            <Window2 />
            <Window3 />
            <Window4 />
            <Window5 />
            <Window6 />
            <Window7 />
          </Body>
          <LeftWing />
          <RightWing />
          <Tail />
        </Airplane>
        <CastleContainer>
          <Castle src={castle} />
          <PostOffice src={postOffice} />
          <Books src={books} />
          <Camera src={camera} />
        </CastleContainer>
      </ContentContainer>
    </Background>
  );
};

export default ChildrenMainPage;
