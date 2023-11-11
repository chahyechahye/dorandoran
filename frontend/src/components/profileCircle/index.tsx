import { AnimalState, profileState } from "@/states/children/info";
import { useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import styled from "styled-components";
import { ButtonEffect } from "@/styles/buttonEffect";

const OuterCircle = styled.div`
  width: 12vh;
  height: 12vh;
  border-radius: 50%;
  background-color: #fffafa;
  display: flex;
  justify-content: center;
  align-items: center;

  ${ButtonEffect}
`;

const InnerCircle = styled.div`
  width: 10vh;
  height: 10vh;
  border-radius: 50%;
  background-color: #00ced1;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Plus = styled.span`
  font-size: 9vh;
  color: white;
`;

const Name = styled.p`
  color: #224545;
  font-size: 30px;
  margin-top: 1vh;
`;

const ProfileImage = styled.img`
  width: 7vh;
`;

const ProfileContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const ProfileCircle = ({
  onClick,
  profileImage,
  profileName,
  type,
}: {
  onClick?: () => void;
  profileImage?: string;
  profileName?: string;
  type?: string;
}) => {
  const animalImg = useRecoilValue(profileState);

  const navigate = useNavigate();
  console.log("animalImg" + animalImg);
  const goCharacter = () => {
    if (type === "child") {
      navigate("/children/character");
    }
  };

  return (
    <ProfileContainer onClick={goCharacter}>
      <OuterCircle onClick={onClick}>
        <InnerCircle>
          {profileImage === "" ? (
            <Plus>+</Plus>
          ) : (
            <ProfileImage src={animalImg.animal.imgUrl} />
          )}
        </InnerCircle>
      </OuterCircle>
      <Name>{animalImg.name}</Name>
    </ProfileContainer>
  );
};

export default ProfileCircle;
