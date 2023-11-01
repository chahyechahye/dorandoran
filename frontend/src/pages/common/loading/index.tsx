import styled from "styled-components";
import Lottie from "lottie-react";
import loading from "@/assets/gif/loading.json";

const Container = styled.div`
  position: fixed;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #fbf5f2;
`;

const Text = styled.p`
  font-size: 3vh;
  color: #595959;
`;

const LoadingPage = () => {
  return (
    <Container>
      <Lottie animationData={loading} />
      <Text>잠시만 기다려주세요...</Text>
    </Container>
  );
};

export default LoadingPage;
