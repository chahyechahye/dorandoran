import styled, { keyframes } from "styled-components";
import background from "@/assets/img/background/background.jpg";
import logoImageSrc from "@/assets/img/logo/logo.png";
import ClickButton from "@/components/clickButton";
import { useNavigate } from "react-router-dom";
import { useChildrenCode } from "@/apis/children/profile/Queries/useChildrenCode";
import { useRecoilState, useSetRecoilState } from "recoil";
import { ChildrenInfoState, profileListState } from "@/states/children/info";
import { useEffect, useState, useReducer, useCallback } from "react";
import { postChildrenLogin } from "@/apis/children/profile/profileAPI";
import { useChildrenLogin } from "@/apis/children/profile/Mutations/useChildrenLogin";
import { useSoundEffect } from "@/components/sounds/soundEffect";

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

const LogoImage = styled.img`
  width: 80vh;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 2vh;
`;

const InviteCode = styled.input.attrs({
  type: "text",
  inputMode: "numeric",
  pattern: "[0-9]*",
  maxLength: 6,
})`
  width: 33vh;
  height: 6.5vh;
  background-color: white;
  border-radius: 5vh;
  border-color: transparent;
  box-shadow: 0 0 1vh rgba(0, 0, 0, 0.1);
  padding-left: 3vh;
  font-size: 2.5vh;
  font-family: Katuri;
  color: #eb9f4a;
  &::placeholder {
    font-size: 2.5vh;
    font-family: Katuri;
    color: #999999;
  }

  &:hover {
    box-shadow: 0 0 3vh rgba(255, 124, 9, 0.5);
    transition: all 0.3s;
  }
`;

const ChildrenLoginPage = () => {
  const navigate = useNavigate();

  const [code, setCode] = useState("");
  const [input, setInput] = useState("");

  const setChildrenCode = useChildrenCode(Number(code));
  const [profileList, setProfileList] = useRecoilState(profileListState);
  const { playSound } = useSoundEffect();

  useEffect(() => {
    if (setChildrenCode) {
      setProfileList(setChildrenCode.data.profileList);
      navigate("/children/profile");
    }
  }, [setChildrenCode, setProfileList, navigate]);

  const getChildrenCodeHandler = () => {
    playSound();
    setCode(input);
  };

  return (
    <Background>
      <ContentContainer>
        <LogoImage src={logoImageSrc} />
        <InviteCode
          placeholder="초대코드"
          value={input}
          onChange={(e) => setInput(e.target.value)}
        ></InviteCode>
        <ClickButton
          width="33vh"
          height="6.5vh"
          backgroundColor="#EB9F4A"
          fontColor="white"
          fontSize="3vh"
          text="모험을 떠나요"
          onClick={getChildrenCodeHandler}
        ></ClickButton>
      </ContentContainer>
    </Background>
  );
};

export default ChildrenLoginPage;
