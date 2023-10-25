import React from "react";
import styled from "styled-components";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Card = styled.div<{ backgroundColor?: string }>`
  width: 30vh;
  height: 30vh;
  background-color: ${(props) => props.backgroundColor || "#00bfff"};
  border-radius: 3vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 0 2vh;
  padding: 2vh;
`;

const Cassette = styled.div<{ img?: string }>`
  width: 24vh;
  height: 24vh;
  background: url(${(props) => props.img}) no-repeat center;
  background-size: contain;
  position: relative;
`;

const Text = styled.p`
  color: #5a3a1c;
  font-size: 7vh;
  margin: 3vh;
`;

interface Props {
  img?: string;
  backgroundColor?: string;
  text?: string;
}

const ChildCard = ({ img, backgroundColor, text }: Props) => {
  return (
    <Container>
      <Card backgroundColor={backgroundColor}>
        <Cassette img={img}></Cassette>
      </Card>
      <Text>{text}</Text>
    </Container>
  );
};

export default ChildCard;
