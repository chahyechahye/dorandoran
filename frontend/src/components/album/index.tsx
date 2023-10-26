import React, { useState } from "react";
import styled from "styled-components";

import bookLeft from "@/assets/img/bookLeft.png";
import bookRight from "@/assets/img/bookRight.png";
import registBtn from "@/assets/img/registBtn.png";
import deleteBtn from "@/assets/img/deleteBtn.png";
import exitBtn from "@/assets/img/exitBtn.png";

const Container = styled.div`
  min-height: 100vh;
  color: #fff;
  perspective: 600px;
  overflow: hidden;
`;

const Book = styled.div`
  position: absolute;
  left: 50%;
  top: 45%;
  transform: rotateX(4deg) translateY(-3%) translate(-50%, -50%);
  width: 145vh;
  height: 90vh;
  max-height: 1200px;

  &::before {
    border-top-right-radius: 16px 6px;
    border-bottom-right-radius: 16px 6px;
  }

  &::after {
    left: calc(50% + 0.5rem);
    border-top-left-radius: 16px 6px;
    border-bottom-left-radius: 16px 6px;
  }

  div {
    background: url(${bookLeft});
    background-size: contain;
    background-repeat: no-repeat;
    width: 50%;
    height: 100%;
    position: absolute;
    top: 0;
    right: calc(49% + 0.5rem);
    overflow: auto;

    & + div {
      background: url(${bookRight});
      background-size: contain;
      background-repeat: no-repeat;
      right: auto;
      left: calc(50% + 0.5rem);
    }
  }
`;

const PageLeft = styled.div`
  text-align: center;
  padding: 5%;
  padding-top: 10%;
  padding-left: 15%;
  overflow: auto;

  img {
    max-width: 40%;
    min-width: 48px;
    max-height: 200px;
    width: auto;
    height: auto;
    background-color: #fff;
    padding: 0.5rem;
    margin: 0.5rem;
    display: inline-block;
    transition: 1.5s;

    &:hover,
    &.active {
      transform: scale(1.04);
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
    }
  }
`;

const PageRight = styled.div`
  img {
    position: absolute;
    left: 40%;
    top: 50%;
    transform: translate(-50%, -50%);
    max-width: 70%;
    max-height: 70%;
    opacity: 0;
    transition:
      0.8s,
      opacity 0.45s 0.15s;
    filter: blur(32px);
    background-color: #fff;
    padding: 1rem;

    &.active {
      filter: blur(0px);
      opacity: 1;
    }
  }
`;

const Bottom = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  bottom: 0;
  left: 50%;
  transform: translate(-50%, -50%);
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

const Image = styled.img`
  margin: 0vh 3vh;
`;

const ExitBtn = styled.img``;

const Album = ({ onClose }: { onClose: () => void }) => {
  const sources = [
    "https://orig00.deviantart.net/1dfd/f/2018/263/1/f/doro_by_kuvshinov_ilya-dcnbgw3.jpg",
    "https://orig00.deviantart.net/edd6/f/2018/170/3/5/depth_of_field_by_kuvshinov_ilya-dcetf0p.jpg",
    "https://orig00.deviantart.net/91c3/f/2018/170/1/9/boushi_by_kuvshinov_ilya-dcetfrj.jpg",
    "https://orig00.deviantart.net/1cf2/f/2018/263/6/7/uesaka_sumire_album_cover_illustration_by_kuvshinov_ilya-dcnbfu8.jpg",
    "https://orig00.deviantart.net/1dc6/f/2018/170/3/0/earring_by_kuvshinov_ilya-dceteuz.jpg",
    "https://orig00.deviantart.net/3171/f/2018/170/0/5/hayashi_by_kuvshinov_ilya-dcetah7.jpg",
    "https://orig00.deviantart.net/dc3b/f/2018/170/7/f/rose_by_kuvshinov_ilya-dcetaqj.jpg",
    "https://img00.deviantart.net/e978/i/2018/170/0/f/sanpo_by_kuvshinov_ilya-dceta26.jpg",
    "https://orig00.deviantart.net/bf7b/f/2018/170/9/2/my_artwork_collection_momentary_reprinted_by_kuvshinov_ilya-dcetaxx.jpg",
  ];

  const [activeIndex, setActiveIndex] = useState(0);

  const handleImageClick = (index: number) => {
    setActiveIndex(index);
  };

  return (
    <Container>
      <Header>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "flex",
            margin: "4vh",
          }}
          onClick={onClose}
        >
          <ExitBtn src={exitBtn}></ExitBtn>
          <p style={{ fontSize: "90px" }}>나가기</p>
        </div>
      </Header>
      <Book>
        <PageLeft>
          {sources.map((source, index) => (
            <img
              key={index}
              src={source}
              alt={`Image ${index}`}
              className={index === activeIndex ? "active" : ""}
              onClick={() => handleImageClick(index)}
            />
          ))}
        </PageLeft>
        <PageRight>
          {sources.map((source, index) => (
            <img
              key={index}
              src={source}
              alt={`Image ${index}`}
              className={index === activeIndex ? "active" : ""}
            />
          ))}
        </PageRight>
      </Book>
      <Bottom>
        <Image src={registBtn} alt="regist" />
        <Image src={deleteBtn} alt="regist" />
      </Bottom>
    </Container>
  );
};

export default Album;
