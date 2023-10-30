import React, { useEffect, useState, useMemo } from "react";
import styled from "styled-components";
import Swiper from "swiper"; // Import Swiper and necessary modules

import { useNavigate } from "react-router-dom";

interface CreditCard {
  id: number;
  name: string;
  company: string;
  domestic: string;
  overseas: string;
  condition: string;
  brandList: string[];
  imagePath: string;
  registerPath: string;
  reason: string;
}

const StyledMain = styled.main`
  position: relative;
  min-height: 50vh;
  flex-direction: column;
  column-gap: 3rem;
  margin: 0vh 5vh;
  padding-block: min(20vh, 3rem);
  padding-inline: 2.3em;
  align-items: center;
  justify-content: center;
  background-color: #1f5875;
  border-radius: 5vh;
  overflow: hidden;
  z-index: 1;

  // 스티치 효과 추가
  &::before,
  &::after {
    content: "";
    position: absolute;
    top: 2vh;
    bottom: 2vh;
    left: 2vh;
    right: 2vh;
    border-radius: 3vh;
    border: dashed 2vh #ffffff;
    box-sizing: border-box;
  }

  &::before {
    border-width: 0.5vh 0 0.5vh 0;
  }

  &::after {
    border-width: 0 0.5vh 0 0.5vh;
  }

  @media screen and (min-width: 960px) {
    display: flex;
    padding-inline: 0;
  }
`;

const StyledSwiperContainer = styled.div`
  position: relative;
  overflow: hidden;
  width: 100%;
  right: 0px;
  margin: 0 auto;

  @media screen and (min-width: 960px) {
    width: 80%;
    right: -60px;
  }
`;

const StyledSwiper = styled.div`
  position: relative;
  width: 100%;
  z-index: 2;
`;

const StyledSwiperSlide = styled.div`
  width: 10rem;
  height: 100%;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  align-items: self-start;
  position: relative;
  border-radius: 12px;
  text-align: center;
  opacity: 0.4;
  transition: opacity 0.4s ease-in;
  background-color: #eaeaea;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

  transition: transform 0.2s ease; /* 호버 애니메이션 추가 */
  transform-origin: center center;

  span {
    display: inline-block;
    background: #fff;
    border-radius: 0 50px 50px 0;
    text-transform: capitalize;
    padding: 12px 20px;
    letter-spacing: 0.5px;
    font-weight: 500;
    position: absolute;
    top: 2em;
    left: 0;
    color: #fff;
  }

  h2 {
    font-size: 3em;
    margin-top: 0.625rem;
    letter-spacing: 0.8px;
  }

  @media screen and (min-width: 800px) {
    h3 {
      font-size: 1.8rem;
    }
  }

  p {
    line-height: 1.6;
    font-size: 1.5rem;
    color: #2b62d1;
  }

  .slide-content {
    position: absolute;
    bottom: 0;
    left: 50%;
    transform: translate(-50%, -10px);
    width: 90%;
  }

  &.swiper-slide--1 {
  }

  &.swiper-slide--2 {
  }

  &.swiper-slide--3 {
  }

  &.swiper-slide--4 {
  }

  &.swiper-slide--5 {
  }

  &.swiper-slide-active {
    display: grid;
    opacity: 1;
  }
`;

const StyledSwiperPagination = styled.div`
  position: relative;
  bottom: -0.313rem;
  text-align: center;
  margin-top: 35px;
  width: auto;

  .swiper-pagination-bullet {
    border-radius: 0;
    width: 1.5rem;
    height: 0.25rem;
    background: #fff;
  }

  .swiper-pagination-bullet-active {
    background: #fff;
  }
`;

const Title = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1.5vh 0vh 1vh;
  transform: rotate(355deg);
`;

const Image = styled.img`
  width: 100%;
  aspect-ratio: 1/1;
  object-fit: cover;
  border-radius: 5px;
  filter: saturate(0.9) contrast(0.85) brightness(1.05);
`;

const MainTitle = styled.div`
  font-size: 8vh;
  color: #fff;
  margin: 5vh 0px;
  text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;

const LikeBookList = () => {
  // const creditInfo = useRecoilValue(creditInfoState);
  // const allCreditCard = useAllCreditCard(creditInfo);
  const creditCardRes = useMemo(
    () => [
      {
        id: 0,
        name: "",
        company: "",
        domestic: "",
        overseas: "",
        condition: "",
        brandList: [],
        imagePath:
          "https://images.unsplash.com/photo-1697468792373-ad4181550a5a?auto=format&fit=crop&q=80&w=2574&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        registerPath: "",
        reason: "",
      },
      {
        id: 1,
        name: "",
        company: "",
        domestic: "",
        overseas: "",
        condition: "",
        brandList: [],
        imagePath:
          "https://images.unsplash.com/photo-1698306790501-0b6a05c74abe?auto=format&fit=crop&q=80&w=2564&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        registerPath: "",
        reason: "",
      },
    ],
    []
  );

  const navigate = useNavigate();

  const [currentSlideIndex, setCurrentSlideIndex] = useState<CreditCard>({
    id: 0,
    name: "",
    company: "",
    domestic: "",
    overseas: "",
    condition: "",
    brandList: [],
    imagePath: "",
    registerPath: "",
    reason: "",
  });

  useEffect(() => {
    const swiper = new Swiper(".swiper", {
      effect: "coverflow",
      grabCursor: true,
      spaceBetween: 30,
      centeredSlides: true,
      coverflowEffect: {
        rotate: 0,
        stretch: 0,
        depth: 0,
        modifier: 1,
        slideShadows: false,
      },
      loop: true,
      pagination: {
        el: ".swiper-pagination",
        clickable: true,
      },
      keyboard: {
        enabled: true,
      },
      mousewheel: {
        thresholdDelta: 70,
      },
      breakpoints: {
        460: {
          slidesPerView: 3,
        },
        768: {
          slidesPerView: 3,
        },
        1024: {
          slidesPerView: 3,
        },
        1600: {
          slidesPerView: 3.6,
        },
      },
    });

    swiper.on("slideChange", function () {
      // 슬라이드가 변경될 때 호출되는 함수
      const activeSlide = swiper.slides[swiper.activeIndex];
      const classNames = activeSlide.className.split(" ");
      // 현재 활성화된 슬라이드의 클래스 이름 확인
      const slideNumberMatch = classNames[3].match(/swiper-slide--(\d+)/);
      if (slideNumberMatch) {
        const slideNumber = parseInt(slideNumberMatch[1], 10); // 매칭된 숫자 부분 추출 및 정수로 변환

        // slideNumber에 해당하는 데이터 가져오기
        const currentData = creditCardRes[slideNumber - 1]; // 슬라이드 인덱스는 0부터 시작하므로 배열 인덱스에 맞게 조정

        // currentData를 원하는 방식으로 활용할 수 있습니다.
        console.log("Current Data:", currentData);
        setCurrentSlideIndex(currentData);
      }
    });
  }, [creditCardRes]);

  return (
    <StyledMain>
      <MainTitle>좋아하는 동화</MainTitle>
      <StyledSwiperContainer>
        <StyledSwiper className="swiper">
          <div className="swiper-wrapper">
            {creditCardRes.map((item: CreditCard, index: number) => (
              <StyledSwiperSlide
                key={`slide-${item.id}`} // Use a unique key for each slide
                className={`swiper-slide swiper-slide--${index + 1}`}
                style={{
                  position: "relative",
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  overflow: "hidden",
                }}
              >
                <Image
                  src={item.imagePath}
                  style={{ width: "100%", borderRadius: "10px" }}
                />
                <Title>
                  <h2>어린왕자</h2>
                  <p>앙투안 드 생텍쥐페리</p>
                </Title>
              </StyledSwiperSlide>
            ))}
          </div>
        </StyledSwiper>
        <StyledSwiperPagination className="swiper-pagination"></StyledSwiperPagination>
      </StyledSwiperContainer>
    </StyledMain>
  );
};

export default LikeBookList;
