import React, { useEffect, useState } from "react";
import gsap from "gsap";
import styled, { keyframes, css } from "styled-components";
import FariytaleEnter from "@/components/fairytaleEnter";

import RT from "@/assets/img/RT.png";
import AX from "@/assets/img/Ax.png";
import Pig from "@/assets/img/Pig.png";
import Frog from "@/assets/img/Frog.png";

import movables from "@/assets/img/movables.png";
import character from "@/assets/img/fox.png";
import { useFairytaleList } from "@/apis/children/fairytale/Queries/useFariytaleList";

// import { background } from "@/assets/img/backgroundRecord.jpg";

const Character = styled.img`
  width: 15%;
  position: absolute;
  z-index: 6;
  top: 71%;
  left: 82%;
`;

const Movables = styled.img`
  width: 100%;
  position: fixed;
  z-index: 5;
  bottom: 0;
  left: 0;
`;

const Container = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: var(--card00-color);
`;

const additionalStyles = css`
  .card {
    text-align: center;
    position: fixed;
    top: 25vh;
    bottom: 0;
    left: 0;
    right: 0;
    background: transparent;
  }

  .card00 {
    background: #5a88d9;
  }

  .card01 {
    background: #aa935f;
    color: white;
  }

  .card02 {
    background: #c00503;
  }

  .card03 {
    background: #1460c4;
    color: white;
  }

  .card04 {
    background: #fb0a02;
  }

  .card05 {
    background: #33b2ce;
    color: white;
  }

  .card06 {
    background: #fcc302;
  }

  .card07 {
    background: #464e46;
    color: white;
  }

  .card08 {
    background: #fb8704;
  }

  .dots {
    position: absolute;
    left: 0;
    right: 0;
    top: 58vh;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .dot {
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
  }

  .dot:focus,
  .dot:active,
  .dot:hover {
    outline: none;
    background-color: rgba(255, 255, 255, 1);
  }

  .dot.active {
    background-color: rgba(255, 255, 255, 1);
  }
`;

const Wrapper = styled.div`
  width: 70%;
  margin: 0 auto;

  ${additionalStyles};
`;

const Title = styled.h1`
  font-size: 8vh;
  text-align: center;
  margin-top: 10vh;
  transition: 1.5s all;
`;

const Image = styled.img`
  height: 30vh;
  z-index: 20;
`;

const Cards = styled.div`
  visibility: hidden;
  opacity: 0;
  height: 100vh;
  position: relative;
`;

const CardCircleAnimation = keyframes`
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

const CardCircle = styled.div`
  height: 30vh;
  width: 30vh;
  border-radius: 50%;
  background: red;
  z-index: -1;
  position: absolute;
  top: 1vh;
  left: 43.5vw;
  background-image: url("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi.pinimg.com%2Foriginals%2F7d%2Fa6%2F96%2F7da696f1987d39249d42946f331ccc1b.jpg&f=1&nofb=1");
  background-size: cover;
  border: 2px solid;
  animation: ${CardCircleAnimation} 5s linear 0s infinite alternate;
`;

const CardContent = styled.div`
  font-size: 20px;
  text-transform: lowercase;
  font-style: italic;
`;

const CardButton = styled.button`
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
  z-index: 1;

  &.next {
    left: auto;
    right: 0;
  }

  span {
    display: none;
  }

  &:hover,
  &:focus,
  &:active {
    outline: none;
    background-color: rgba(0, 0, 0, 0.4);
    color: white;
    border-radius: 100%;
    cursor: pointer;
  }

  &:before {
    content: "‹";
    display: block;
    position: absolute;
    top: -13px;
    font-size: 70px;
    font-weight: 300;
  }

  &.prev:before {
    content: "‹";
    left: 23px;
  }

  &.next:before {
    content: "›";
    left: 28px;
  }
`;

const CardTitle = styled.h2`
  font-size: 70px;
  margin-top: 30px;
`;

const FairyTalePage = () => {
  const [activeCardClass, setActiveCardClass] =
    useState<keyof typeof cardColors>("card00");
  const [isModalOpen, setIsModalOpen] = useState(false);

  const setFairytale = useFairytaleList();

  const openModal = () => {
    setIsModalOpen(true);
  };

  const cardColors = {
    card00: "#5a88d9",
    card01: "#aa935f",
    card02: "#c00503",
    card03: "#1460c4",
  };

  useEffect(() => {
    // Initialize GSAP
    gsap.set(".cards", { autoAlpha: 1 });
    gsap.set(".card", { x: "-100%" });

    let currentStep = 0;
    const totalSlides = document.querySelectorAll(".card").length;

    const wrapper = gsap.utils.wrap(0, totalSlides);

    createTimelineIn("next", currentStep);

    function createTimelineIn(direction: string, index: number) {
      const goPrev = direction === "prev";

      const element = document.querySelector("div.card0" + index);

      const cardClasses = element?.className.split(" ");
      const cardClass = cardClasses?.[1];
      // const title = element?.querySelector(".card-title");
      const subtitle = element?.querySelector(".card-subtitle");
      const button = element?.querySelector(".button-container");

      const tlIn = gsap.timeline({
        defaults: {
          modifiers: {
            x: gsap.utils.unitize(function (x) {
              return goPrev ? Math.abs(x) : x;
            }),

            rotation: gsap.utils.unitize(function (rotation) {
              return goPrev ? -rotation : rotation;
            }),
          },
        },
      });
      tlIn
        .fromTo(
          element,
          {
            autoAlpha: 0,
            x: "-100%",
          },
          {
            duration: 0.7,
            x: 0,
            autoAlpha: 1,
            onStart: updateClass,
            onStartParams: [cardClass],
          }
        )
        .from(
          [subtitle, button],
          {
            duration: 0.2,
            x: -20,
            autoAlpha: 0,
            stagger: 0.1,
          },
          "-=0.1"
        );

      return tlIn;
    }

    function createTimelineOut(direction: string, index: number) {
      const goPrev = direction === "prev";
      const element = document.querySelector("div.card0" + index);
      const tlOut = gsap.timeline();
      tlOut.to(element, {
        duration: 0.7,
        x: 250,
        autoAlpha: 0,
        modifiers: {
          x: gsap.utils.unitize(function (x) {
            return goPrev ? -x : x;
          }),
        },
        ease: "back.in(2)",
      });
      return tlOut;
    }

    function updateCurrentStep(goToIndex: number) {
      currentStep = goToIndex;

      document.querySelectorAll(".dot").forEach(function (element, index) {
        element.setAttribute("class", "dot");
        if (index === currentStep) {
          element.classList.add("active");
        }
      });
      // positionDot();
    }

    function transition(direction: string, toIndex: number) {
      const tlTransition = gsap.timeline({
        onStart: function () {
          updateCurrentStep(toIndex);
        },
      });

      const tlOut = createTimelineOut(direction, currentStep);
      const tlIn = createTimelineIn(direction, toIndex);

      tlTransition.add(tlOut).add(tlIn);

      return tlTransition;
    }

    function isTweening() {
      return gsap.isTweening(".card");
    }

    const nextButton = document.querySelector("button.next");

    if (nextButton) {
      nextButton.addEventListener("click", function (e) {
        e.preventDefault();

        const nextStep = wrapper(currentStep + 1);

        !isTweening() && transition("next", nextStep);
      });
    } else {
      console.error("Next button not found");
    }

    const prevButton = document.querySelector("button.prev");

    if (prevButton) {
      prevButton.addEventListener("click", function (e) {
        e.preventDefault();

        const prevStep = wrapper(currentStep - 1);

        !isTweening() && transition("prev", prevStep);
      });
    } else {
      console.error("Prev button not found");
    }

    function updateClass(cardClass: any) {
      setActiveCardClass(cardClass);
    }

    function createNavigation() {
      const newDiv = document.createElement("div");
      newDiv.setAttribute("class", "dots");

      const spot = document.createElement("div");
      spot.setAttribute("class", "spot");

      for (let index = 0; index < totalSlides; index++) {
        const element = document.createElement("button");
        const text = document.createTextNode(`${index}`);
        element.appendChild(text);
        element.setAttribute("class", "dot");
        if (currentStep === index) {
          element.classList.add("active");
        }

        element.addEventListener("click", function () {
          if (!isTweening() && currentStep !== index) {
            const direction = index > currentStep ? "next" : "prev";
            transition(direction, index);
          }
        });
        newDiv.appendChild(element);
      }

      //append to container
      newDiv.appendChild(spot);
      const cardsContainer = document.querySelector(".cards");
      if (cardsContainer) {
        cardsContainer.appendChild(newDiv);
      }
      // positionDot();
    }

    // function positionDot() {
    //   const activeDotX = document.querySelector(".dot.active").offsetLeft;
    //   const spot = document.querySelector(".spot");
    //   const spotX = spot.offsetLeft;
    //   const destinationX = Math.round(activeDotX - spotX + 5);

    //   const dotTl = gsap.timeline();
    //   dotTl
    //     .to(spot, {
    //       duration: 0.4,
    //       x: destinationX,
    //       scale: 2.5,
    //       ease: "power1.Out",
    //     })
    //     .to(spot, {
    //       duration: 0.2,
    //       scale: 1,
    //       ease: "power1.in",
    //     });
    // }

    createNavigation();
  }, []);

  return (
    <>
      <Container style={{ background: cardColors[activeCardClass] || "#fff" }}>
        <Wrapper>
          <Title>어느 동화책을 읽고싶니?</Title>
          <Cards className="cards">
            <CardButton className="btn prev">
              <span>Prev</span>
            </CardButton>
            <CardButton className="btn next">
              <span>Next</span>
            </CardButton>
            <div className="card card00">
              <CardContent>
                <CardCircle></CardCircle>
                <Image src={RT} alt="RT" onClick={openModal} />
                {/* RT 이미지 경로로 변경 */}
                <CardTitle>토끼와 거북이</CardTitle>
                <p className="card-subtitle">Whatever, Ill be at Moes.</p>
              </CardContent>
            </div>
            <div className="card card01">
              <CardContent>
                <CardCircle></CardCircle>
                <Image src={AX} alt="RT" /> {/* RT 이미지 경로로 변경 */}
                <CardTitle>금도끼 은도끼</CardTitle>
                <p className="card-subtitle">Whatever, Ill be at Moes.</p>
              </CardContent>
            </div>
            <div className="card card02">
              <CardContent>
                <CardCircle></CardCircle>
                <Image src={Frog} alt="RT" /> {/* RT 이미지 경로로 변경 */}
                <CardTitle>개구리 왕자</CardTitle>
                <p className="card-subtitle">Whatever, Ill be at Moes.</p>
              </CardContent>
            </div>
            <div className="card card03">
              <CardContent>
                <CardCircle></CardCircle>
                <Image src={Pig} alt="RT" /> {/* RT 이미지 경로로 변경 */}
                <CardTitle>아기돼지 삼형제</CardTitle>
                <p className="card-subtitle">Whatever, Ill be at Moes.</p>
              </CardContent>
            </div>
          </Cards>
        </Wrapper>

        <Character src={character} />
        <Movables src={movables} />
      </Container>
      {isModalOpen && <FariytaleEnter />}
    </>
  );
};

export default FairyTalePage;
