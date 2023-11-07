import React, { useState } from "react";
import styled from "styled-components";

import foxImage from "@/assets/img/fox.png";
import pandaImage from "@/assets/img/panda.png";
import rabbitImage from "@/assets/img/rabbit.png";
import penguin from "@/assets/img/Penguin.png";
import { useChildrenAnimal } from "@/apis/children/character/Queries/useChildrenAnimal";
import { AnimalListProps } from "@/types/children/fairytaleType";
import { useChildrenCharacter } from "@/apis/children/profile/Mutations/useChildrenCharacter";
import { useRecoilState, useRecoilValue } from "recoil";
import {
  AnimalIdState,
  AnimalState,
  selectAnimalState,
} from "@/states/children/info";

interface DivProps {
  backgroundImage: string;
  backgroundColor: string;
}

const Div = styled.div<DivProps>`
  width: 40vw;
  max-width: 30vh;
  height: 65vh;
  background-size: auto 60%;
  background-position: 50% 50%;
  background-repeat: no-repeat;
  display: inline-block;
  margin: 0 0.5rem;
  filter: grayscale(1) opacity(0.2) contrast(200%);
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

  const getChildrenAnimal = useChildrenAnimal();

  const animal = getChildrenAnimal.data.animalList;

  const filteredAnimal = animal.filter(
    (item: AnimalListProps) => item.name !== "기본"
  );

  const [saveAnimalId, setSaveAnimalId] = useRecoilState(AnimalIdState);
  const [animalInfo, setAnimalInfo] = useRecoilState(AnimalState);
  const [SelectAnimal, setSelectAnimal] = useRecoilState(selectAnimalState);

  const handleMouseEnter = (index: number) => {
    setHighlightedIndex(index);
    setSelectAnimal(filteredAnimal[index]);
    setSaveAnimalId(filteredAnimal[index].id);
  };

  return (
    <div className="App" tabIndex={0}>
      {filteredAnimal.map((animal: AnimalListProps, index: number) => (
        <Div
          key={index}
          className={index === highlightedIndex ? "highlighted" : ""}
          backgroundImage={animal.imgUrl}
          backgroundColor={animal.color}
          onMouseEnter={() => handleMouseEnter(index)}
        >
          <Content>{animal.name}</Content>
        </Div>
      ))}
    </div>
  );
};

export default CharacterPage;
