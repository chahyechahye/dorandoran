import React from "react";
import styled from "styled-components";

const RecordBoxWrapper = styled.div`
  position: relative;
  font-size: 3.5vh;
  background: #78bffc;
  color: #fff;
  padding: 6vh 10vh;
  border-radius: 3vh;
  margin-bottom: 2vh;
  width: 100vh;
  max-width: 160vh;
`;

const Text = styled.p`
  margin: 0;
`;

const ArrowWrapper = styled.div`
  position: absolute;
  right: 1vh;
  top: 50%;
  transform: translate(-50%, -50%);
`;

const ArrowIcon = styled.svg`
  width: 4vh;
  height: 4vh;
  fill: white;
`;

const RecordBox = () => {
  return (
    <RecordBoxWrapper>
      <Text>
        이 요리는 다양한 빛깔로 만들어 저녁에 먹으면 약간 짜게 느껴질 수
        있습니다.
      </Text>
      <ArrowWrapper>
        <ArrowIcon xmlns="http://www.w3.org/2000/svg" viewBox="0 0 25 32">
          <path d="M0 0V31.5L24.75 15.75L0 0Z" fill="white" />
        </ArrowIcon>
      </ArrowWrapper>
    </RecordBoxWrapper>
  );
};

export default RecordBox;
