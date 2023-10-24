import React, { useState } from "react";
import styled from "styled-components";

import foxImage from "@/assets/img/fox.png";
import pandaImage from "@/assets/img/panda.png";
import rabbitImage from "@/assets/img/rabbit.png";
import penguin from "@/assets/img/Penguin.png";

interface DivProps {
  backgroundImage: string;
  backgroundColor: string;
}

const Div = styled.div<DivProps>`
  width: 40vw;
  max-width: 500px;
  height: 75vh;
  background-size: auto 60%;
  background-position: 50% 50%;
  background-repeat: no-repeat;
  display: inline-block;
  margin: 0 0.5rem;
  filter: grayscale(1) opacity(0.1) contrast(200%);
  transition: 0.5s;
  transform: skewY(-10deg);
  z-index: -1;
  background-image: ${(props) => `url(${props.backgroundImage})`};
  background-color: ${(props) => props.backgroundColor};

  &.highlighted {
    filter: grayscale(0) opacity(1);
    transform: scale(1.1);
    box-shadow: 0 25px 50px black;
    z-index: 100;
  }
`;

const Content = styled.div`
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  text-align: center;
  font-size: 80px;
  font-weight: 900;
  color: white;
  text-shadow: 3px 3px #555;
`;

const CharacterPage: React.FC = () => {
  const [highlightedIndex, setHighlightedIndex] = useState<number>(-1);

  const divs = [
    {
      backgroundColor: "orange",
      backgroundImage: foxImage,
      content: "여울",
    },
    {
      backgroundColor: "red",
      backgroundImage: pandaImage,
      content: "팡팡",
    },
    {
      backgroundColor: "pink",
      backgroundImage: rabbitImage,
      content: "토리",
    },
    {
      backgroundColor: "purple",
      backgroundImage: penguin,
      content: "펭펭",
    },
  ];

  const handleMouseEnter = (index: number) => {
    setHighlightedIndex(index);
  };

  return (
    <div className="App" tabIndex={0}>
      {divs.map((div, index) => (
        <Div
          key={index}
          className={index === highlightedIndex ? "highlighted" : ""}
          backgroundImage={div.backgroundImage}
          backgroundColor={div.backgroundColor}
          onMouseEnter={() => handleMouseEnter(index)}
        >
          <Content>{div.content}</Content>
        </Div>
      ))}
    </div>
  );
};

export default CharacterPage;
