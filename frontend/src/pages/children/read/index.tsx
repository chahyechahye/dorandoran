import styled from "styled-components";
import background from "@/assets/img/background/background.jpg";

const Background = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url(${background});
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const ChildrenProfilePage = () => {
  return (
    <Background>
      <ContentContainer></ContentContainer>
    </Background>
  );
};

export default ChildrenProfilePage;
