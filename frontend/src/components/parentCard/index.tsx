import React from "react";
import styled from "styled-components";
import { ButtonEffect } from "@/styles/buttonEffect";

const Card = styled.div<{ backgroundColor?: string }>`
  width: 40vh;
  height: 50vh;
  background-color: ${(props) => props.backgroundColor || "#00bfff"};
  border-radius: 3vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 0vh 2vh;
  padding: 2vh;

  ${ButtonEffect}
`;

const Cassette = styled.div<{ img?: string }>`
  width: 32vh;
  height: 32vh;
  background: url(${(props) => props.img}) no-repeat center;
  background-size: contain;
  border-radius: 1vh;
  position: relative;
`;

const Text = styled.p`
  color: #ffffff;
  font-size: 7vh;
  margin: 1vh;
`;

interface Props {
  img?: string;
  backgroundColor?: string;
  text?: string;
  onClick?: () => void;
}

const ParentCard = ({ img, backgroundColor, text, onClick }: Props) => {
  return (
    <Card backgroundColor={backgroundColor} onClick={onClick}>
      <Cassette img={img}></Cassette>
      <Text>{text}</Text>
    </Card>
  );
};

export default ParentCard;
