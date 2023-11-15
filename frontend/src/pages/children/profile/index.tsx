import styled from "styled-components";
import background from "@/assets/img/background/background.jpg";
import ChildCard from "@/components/childCard";
import Face from "@/assets/img/smile.png";
import { useNavigate } from "react-router-dom";
import { useRecoilState, useRecoilValue } from "recoil";
import {
  AnimalState,
  childrenLoginState,
  profileListState,
  profileState,
} from "@/states/children/info";
import { useChildrenLogin } from "@/apis/children/profile/Mutations/useChildrenLogin";
import { ChildrenProfileProps } from "@/types/children/profileType";
import { useEffect } from "react";
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

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const CardContainer = styled.div`
  display: flex;
`;

const ChildrenProfilePage = () => {
  const profileList = useRecoilValue(profileListState);
  const [profileData, setProfileData] = useRecoilState(profileState);
  const [childrenLogin, setChildrenLogin] = useRecoilState(childrenLoginState);
  const { playSound } = useSoundEffect();

  const navigate = useNavigate();

  const usePostChildrenLogin = useChildrenLogin();

  const postLoginHandler = async (profile: ChildrenProfileProps) => {
    playSound();

    try {
      localStorage.removeItem("accessToken");

      // Update local state
      setChildrenLogin({ childId: profile.childId, profileId: profile.id });
      setProfileData(profile);

      // Use mutateAsync to make the API request
      await usePostChildrenLogin.mutateAsync({
        childId: profile.childId,
        profileId: profile.id,
      });

      await new Promise((resolve) => setTimeout(resolve, 200));

      // Navigate after the request is completed
      if (profile.animal.name === "기본") {
        navigate("/children/character");
      } else {
        navigate("/children/main");
      }
    } catch (error) {
      // Handle errors
      console.error("API 오류 - postLoginHandler:", error);

      // You might want to show an error message to the user or handle it in another way
    }
  };

  return (
    <Background>
      <ContentContainer>
        <CardContainer>
          {profileList.map((profile, index) => (
            <ChildCard
              key={index}
              img={profile.animal.imgUrl}
              backgroundColor={profile.animal.color}
              text={profile.name}
              onClick={() => postLoginHandler(profile)}
            />
          ))}
        </CardContainer>
      </ContentContainer>
    </Background>
  );
};

export default ChildrenProfilePage;
