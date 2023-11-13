import React, { useState, useEffect } from "react";
import styled from "styled-components";

import { useChildrenAnimal } from "@/apis/children/character/Queries/useChildrenAnimal";
import { AnimalListProps } from "@/types/children/fairytaleType";
import { useChildrenCharacter } from "@/apis/children/profile/Mutations/useChildrenCharacter";
import { useRecoilState, useRecoilValue } from "recoil";
import {
  AnimalIdState,
  AnimalState,
  selectAnimalState,
} from "@/states/children/info";
import useSound from "use-sound";

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

  const [playFox, { stop: stopFox }] = useSound(
    "https://storage.googleapis.com/dorandoran/fox_sel.wav"
  );
  const [playRabbit, { stop: stopRabbit }] = useSound(
    "https://storage.googleapis.com/dorandoran/rabbit_sel.wav"
  );
  const [playPenguin, { stop: stopPenguin }] = useSound(
    "https://storage.googleapis.com/dorandoran/peng_self.mp3"
  );
  const [playPanda, { stop: stopPanda }] = useSound(
    "https://storage.googleapis.com/dorandoran/newPanda_self.mp3"
  );

  const animal = getChildrenAnimal.data.animalList;

  const filteredAnimal = animal.filter(
    (item: AnimalListProps) => item.name !== "기본"
  );

  const [saveAnimalId, setSaveAnimalId] = useRecoilState(AnimalIdState);
  const [animalInfo, setAnimalInfo] = useRecoilState(AnimalState);
  const [SelectAnimal, setSelectAnimal] = useRecoilState(selectAnimalState);

  useEffect(() => {
    // Cleanup function to stop the sound when the component is unmounted
    return () => {
      stopFox();
      stopRabbit();
      stopPenguin();
      stopPanda();
    };
  }, [stopFox, stopRabbit, stopPenguin, stopPanda]); // Empty dependency array ensures this effect runs only once during mount and cleans up on unmount

  const handleMouseEnter = (index: number) => {
    setHighlightedIndex(index);
    setSelectAnimal(filteredAnimal[index]);
    setSaveAnimalId(filteredAnimal[index].id);

    stopFox();
    stopRabbit();
    stopPenguin();
    stopPanda();

    switch (filteredAnimal[index].name) {
      case "여우":
        playFox();
        break;
      case "토끼":
        playRabbit();
        break;
      case "펭귄":
        playPenguin();
        break;
      case "판다":
        playPanda();
        break;
      default:
        break;
    }
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
