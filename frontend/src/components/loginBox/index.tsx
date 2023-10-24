import styled from "styled-components";

export interface StyledLoginBoxProps {
  imgLink: string;
  text: string;
}

const BoxContainer = styled.div`
  width: 40vh;
  height: 35vh;
  background-color: white;
  border-radius: 5vh;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 0 1vh rgba(0, 0, 0, 0.1);
`;

const ContentContainer = styled.div`
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const BoxImage = styled.img`
  margin-top: 1vh;
  width: 15vh;
`;

const TextContainer = styled.div`
  font-size: 4vh;
  margin: 2vh;
`;

const LoginBox = ({ imgLink, text }: StyledLoginBoxProps) => {
  return (
    <BoxContainer>
      <ContentContainer>
        <BoxImage src={imgLink}></BoxImage>
        <TextContainer>{text}</TextContainer>
      </ContentContainer>
    </BoxContainer>
  );
};

export default LoginBox;
