import React, { useEffect, useState } from "react";
import gsap from "gsap";
import styled, { keyframes, css } from "styled-components";
import FariytaleEnter from "@/components/fairytaleEnter";

import { ButtonEffect } from "@/styles/buttonEffect";
import exitBtn from "@/assets/img/exitBtn.png";

import movables from "@/assets/img/movables.png";
import character from "@/assets/img/fox.png";
import { useFairytaleList } from "@/apis/children/fairytale/Queries/useFariytaleList";
import { FairytaleListProps } from "@/types/children/fairytaleType";
import { useRecoilState, useRecoilValue } from "recoil";
import {
  FairytaleSearchState,
  fairytaleContentListState,
  fairytaleReadState,
  fairytaleState,
  profileState,
} from "@/states/children/info";
import { useFairytaleRead } from "@/apis/children/fairytale/Mutations/useFairytaleRead";
import GenderModal from "@/components/genderModal";
import { useNavigate } from "react-router-dom";
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
    background: #f0a652;
    color: white;
  }

  .card01 {
    background: #77b29f;
    color: white;
  }

  .card02 {
    background: #be93e2;
    color: white;
  }

  .card03 {
    background: #657dde;
    color: white;
  }

  .card04 {
    background: #fb0a02;
  }

  .card05 {
    background: #33b2ce;
    color: white;
  }

  /* .card06 {
    background: #fcc302;
  }

  .card07 {
    background: #464e46;
    color: white;
  }

  .card08 {
    background: #fb8704;
  } */

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
  font-size: 7vh;
  text-align: center;
  // 상단 타이틀, 양옆 화살표, 동그라미 위치 변경
  margin-top: 14vh;
  transition: 1.5s all;
  color: #ffffff;
  text-shadow: 2px 4px 2px rgba(0, 0, 0, 0.2);
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
  // 양쪽 화살표와 아래 넘겨지는 동그라미 변동
  margin-top: 1.5vh;
`;

const CardCircleAnimation = keyframes`
  0% {
    transform: translate(0, 0);
  }
  50% {
    transform: translate(-3vh);
  }
  100% {
    transform: translate(3vh, 0);
  }
`;

const CardCircle = styled.div`
  height: 35vh;
  width: 35vh;
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
  // 하늘 배경 이미지 변동
  margin-top: 2vh;
`;

const CardContent = styled.div`
  font-size: 20px;
  text-transform: lowercase;
  font-style: italic;
  // 타이틀과 표지캐릭터 이미지 변동
  margin-top: 8vh;
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
  // 양 옆 화살표 버튼 변경
  margin-top: 6vh;

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
  font-size: 8.5vh;
  margin-top: 3vh;
  text-shadow: 2px 4px 2px rgba(0, 0, 0, 0.2);
`;

const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
  right: 0;
  z-index: 1;
`;

const ExitBtn = styled.img`
  width: 12vh;
  ${ButtonEffect}
`;

const FairyTalePage = () => {
  const [activeCardClass, setActiveCardClass] =
    useState<keyof typeof cardColors>("card00");
  const [isModalOpen, setIsModalOpen] = useState(false);
  // 한권 저장
  const [fairytale, setFairytale] = useRecoilState(fairytaleState);
  // 한권 검색에 필요한 데이터
  const [fairytaleSearch, setFairytaleSearch] =
    useRecoilState(FairytaleSearchState);
  // 한권의 컨텐츠 저장
  const [fairytaleRead, setFairytaleRead] = useRecoilState(fairytaleReadState);
  const [fairytaleContent, setFairytaleContent] = useRecoilState(
    fairytaleContentListState
  );
  const [selectedGender, setSelectedGender] = useState("");
  console.log(fairytale);
  // 동화책 리스트 담기 (여러권)
  const setFairytaleList = useFairytaleList();
  const fairytaleList = setFairytaleList.data.bookResDtoList;

  // 동화책 한권 찾기
  const usePostFairytaleRead = useFairytaleRead();

  const fairytaleHandler = async (fairytale: FairytaleListProps) => {
    setFairytale(fairytale);
    setFairytaleSearch({ ...fairytaleSearch, bookId: fairytale.bookId });

    let genderSelect;

    if (selectedGender === "아빠") {
      genderSelect = "MALE";
    } else {
      genderSelect = "FEMALE";
    }

    try {
      const response = await usePostFairytaleRead.mutateAsync({
        gender: genderSelect,
        bookId: fairytale.bookId,
      });
      setFairytaleRead(response.data);
      setFairytaleContent(response.data[0].contentResDto);
    } catch (error) {
      console.log("api 오류 - postFairytaleReadHandlerinHandler");
    }
    setIsModalOpen(true);
  };

  const cardColors = {
    card00: "#f0a652",
    card01: "#77B29F",
    card02: "#be93e2",
    card03: "#657dde",
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

  const handleGenderSelection = (selectedOption: string) => {
    setSelectedGender(selectedOption);
  };

  const navigate = useNavigate();

  const goMain = () => {
    navigate("/children/main");
  };

  const profile = useRecoilValue(profileState);

  return (
    <>
      <GenderModal onGenderSelected={handleGenderSelection} type="children" />
      <Container style={{ background: cardColors[activeCardClass] || "#fff" }}>
        <Header>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "flex",
              margin: "4vh",
            }}
            onClick={goMain}
          >
            <ExitBtn src={exitBtn}></ExitBtn>
            <p
              style={{
                fontSize: "5vh",
                color: "#f25222",
                textShadow: "2px 4px 2px rgba(0, 0, 0, 0.2)",
              }}
            >
              나가기
            </p>
          </div>
        </Header>
        <Wrapper>
          <Title>어느 동화책을 읽을까요?</Title>
          <Cards className="cards">
            <CardButton className="btn prev">
              <span>Prev</span>
            </CardButton>
            <CardButton className="btn next">
              <span>Next</span>
            </CardButton>
            {fairytaleList.map(
              (fairytale: FairytaleListProps, index: number) => (
                <div key={index} className={`card card0${index}`}>
                  <CardContent>
                    <CardCircle
                      onClick={() => fairytaleHandler(fairytale)}
                    ></CardCircle>
                    <Image
                      src={fairytale.characterUrl}
                      alt={fairytale.title}
                      onClick={() => fairytaleHandler(fairytale)}
                    />
                    {/* RT 이미지 경로로 변경 */}
                    <CardTitle onClick={() => fairytaleHandler(fairytale)}>
                      {fairytale.title}
                    </CardTitle>
                    <p className="card-subtitle"></p>
                  </CardContent>
                </div>
              )
            )}
          </Cards>
        </Wrapper>

        <Character src={profile.animal.imgUrl} />
        <Movables src={movables} />
      </Container>
      {isModalOpen && <FariytaleEnter />}
    </>
  );
};

export default FairyTalePage;
