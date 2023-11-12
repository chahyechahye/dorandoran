import React, { useState } from "react";
import styled, { keyframes } from "styled-components";

const phoneAnimation = keyframes`
  0% {
    transform: scale(3.5);
  }
  100% {
    transform: scale(4);
  }
`;

const scalingAnimation = keyframes`
  0% {
    transform: scale(4);
    background-color: #f65f5f;
  }
  100% {
    transform: scale(7);
    background-color: rgba(245, 0, 182, 0);
  }
`;

const Section = styled.section`
  height: 20vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-right: 20vh;
`;

const Voice = styled.div`
  z-index: 2;
  background: linear-gradient(to bottom, #f4a3a3 5%, #f65f5f 100%);
  border-radius: 200px;
  padding: 1vh 1.2vh;
  animation: ${phoneAnimation} 1s cubic-bezier(0.12, 0.7, 0.74, 0.71) infinite
    alternate-reverse;
  display: grid;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 10px 14px -7px #777777;
  cursor: pointer;
`;

const Circle1 = styled.div`
  height: 4vh;
  width: 4vh;
  border-radius: 50%;
  background-color: #d9d9d9;
  position: absolute;
  animation: ${scalingAnimation} 2s cubic-bezier(0.12, 0.7, 0.74, 0.71) infinite;
  animation-delay: 0s;
`;

const Circle2 = styled.div`
  height: 4vh;
  width: 4vh;
  border-radius: 50%;
  background-color: #d9d9d9;
  position: absolute;
  animation: ${scalingAnimation} 2s cubic-bezier(0.12, 0.7, 0.74, 0.71) infinite;
  animation-delay: 1s;
`;

const RecordBtn = ({ onClick }: { onClick: () => void }) => {
  const [flag, setFlag] = useState(false);

  const handleClick = () => {
    setFlag(!flag);
    onClick();
  };

  return (
    <Section>
      <Voice onClick={handleClick}>
        {flag ? (
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="25"
            viewBox="0 0 41 55"
            fill="none"
          >
            <path
              d="M20.02 34.32C24.7676 34.32 28.5714 30.4876 28.5714 25.74L28.6 8.58C28.6 3.8324 24.7676 0 20.02 0C15.2724 0 11.44 3.8324 11.44 8.58V25.74C11.44 30.4876 15.2724 34.32 20.02 34.32ZM35.178 25.74C35.178 34.32 27.9136 40.326 20.02 40.326C12.1264 40.326 4.862 34.32 4.862 25.74H0C0 35.4926 7.7792 43.5578 17.16 44.9592V54.34H22.88V44.9592C32.2608 43.5864 40.04 35.5212 40.04 25.74H35.178Z"
              fill="white"
            />
          </svg>
        ) : (
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="25"
            viewBox="0 0 41 55"
            fill="none"
          >
            <path
              d="M20.5005 34.6501C25.2481 34.6501 29.0519 30.8177 29.0519 26.0701L29.0805 8.91008C29.0805 4.16248 25.2481 0.330078 20.5005 0.330078C15.7529 0.330078 11.9205 4.16248 11.9205 8.91008V26.0701C11.9205 30.8177 15.7529 34.6501 20.5005 34.6501ZM17.0685 8.62408C17.0685 6.73648 18.6129 5.19208 20.5005 5.19208C22.3881 5.19208 23.9325 6.73648 23.9325 8.62408L23.9039 26.3561C23.9039 28.2437 22.3881 29.7881 20.5005 29.7881C18.6129 29.7881 17.0685 28.2437 17.0685 26.3561V8.62408ZM35.6585 26.0701C35.6585 34.6501 28.3941 40.6561 20.5005 40.6561C12.6069 40.6561 5.34247 34.6501 5.34247 26.0701H0.480469C0.480469 35.8227 8.25967 43.8879 17.6405 45.2893V54.6701H23.3605V45.2893C32.7413 43.9165 40.5205 35.8513 40.5205 26.0701H35.6585Z"
              fill="white"
            />
          </svg>
        )}
      </Voice>
      {flag && (
        <>
          <Circle1></Circle1>
          <Circle2></Circle2>
        </>
      )}
    </Section>
  );
};

export default RecordBtn;
