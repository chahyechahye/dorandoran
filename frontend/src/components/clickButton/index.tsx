import styled from "styled-components";

export interface StyledClickButtonProps {
  width: string;
  height: string;
  backgroundColor: string;
  fontColor: string;
  fontSize: string;
  text: string;
}

const ClickContainer = styled.button<StyledClickButtonProps>`
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  background-color: ${(props) => props.backgroundColor};
  border-radius: 5vh;
  border-color: transparent;
  box-shadow: 0 0 1vh rgba(0, 0, 0, 0.1);
  color: ${(props) => props.fontColor};
  font-size: ${(props) => props.fontSize};
  font-family: Katuri;
`;

const ClickButton = ({
  width,
  height,
  backgroundColor,
  fontColor,
  fontSize,
  text,
}: StyledClickButtonProps) => {
  return (
    <ClickContainer
      width={width}
      height={height}
      backgroundColor={backgroundColor}
      fontColor={fontColor}
      fontSize={fontSize}
      text={text}
    >
      {text}
    </ClickContainer>
  );
};

export default ClickButton;
