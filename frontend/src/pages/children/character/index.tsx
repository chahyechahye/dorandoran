import styled from "styled-components";
import background from "@/assets/img/background/background.jpg";
import CharacterPage from "@/pages/common/character";
import ClickButton from "@/components/clickButton";
import { useNavigate } from "react-router-dom";
import { useChildrenCharacter } from "@/apis/children/profile/Mutations/useChildrenCharacter";
import { useRecoilState, useRecoilValue } from "recoil";
import { profileListState } from "../../../states/children/info";
import {
  AnimalIdState,
  profileState,
  selectAnimalState,
} from "@/states/children/info";
import { ChildrenProfileProps } from "@/types/children/profileType";

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

const CharacterModal = styled.div``;

const ModalBlack = styled.div`
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
`;

const ClickButtonContainer = styled.div`
  position: relative;
  z-index: 1;
  margin-top: 7vh;
`;

const ChildrenCharacterPage = () => {
  const navigate = useNavigate();

  const goMain = () => {
    settingAnimal();
    childrenCharacterHandler();
    navigate("/children/main");
  };

  const animalId = useRecoilValue(AnimalIdState);
  const selectAnimal = useRecoilValue(selectAnimalState);
  const [profile, setProfile] = useRecoilState(profileState);
  const [profileList, setProfileList] = useRecoilState(profileListState);

  console.log("1:" + JSON.stringify(profileList));
  console.log("캐릭터플필아이디:" + profile.id);
  const settingAnimal = () => {
    const updatedProfile = {
      ...profile,
      animal: selectAnimal,
    };

    // 현재 아동의 프로필 업데이트
    setProfile(updatedProfile);

    // 프로필 목록 내의 프로필 업데이트
    setProfileListByChildId(updatedProfile);
  };

  const setProfileListByChildId = (profile: ChildrenProfileProps) => {
    setProfileList((profileList) => {
      return profileList.map((profileItem) => {
        if (profileItem.id === profile.id) {
          console.log("profileItem.id" + profileItem.id);
          console.log("profile.id" + profile.id);
          return profile;
        } else {
          return profileItem;
        }
      });
    });
  };
  const usePostChildrenCharacter = useChildrenCharacter();

  const childrenCharacterHandler = () => {
    try {
      console.log("2:" + JSON.stringify(profileList));
      console.log("이건 잘나오나profile:" + profile.id);
      console.log("이건 잘나오나selectAnimal:" + selectAnimal.id);
      usePostChildrenCharacter.mutateAsync(selectAnimal.id);
    } catch (error) {
      console.log("api 오류 - childrenCharacterHandler");
    }
  };

  return (
    <Background>
      <ContentContainer>
        <CharacterModal>
          <ModalBlack />
          <CharacterPage />

          <ClickButtonContainer>
            <ClickButton
              width="70vh"
              height="9vh"
              backgroundColor="#FC7292"
              fontColor="white"
              fontSize="4.5vh"
              text="이 친구랑 놀래요!"
              onClick={goMain}
            ></ClickButton>
          </ClickButtonContainer>
        </CharacterModal>
      </ContentContainer>
    </Background>
  );
};

export default ChildrenCharacterPage;
