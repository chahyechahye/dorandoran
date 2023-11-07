import { AnimalState, profileState } from "@/states/children/info";
import { useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import styled from "styled-components";

const OuterCircle = styled.div`
  width: 12vh;
  height: 12vh;
  border-radius: 50%;
  background-color: #fffafa;
  display: flex;
  justify-content: center;
  align-items: center;
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
  color: #312070;
  font-size: 30px;
`;

const ProfileImage = styled.img`
  width: 7vh;
`;

const ProfileCircle = ({
  onClick,
  profileImage,
  profileName,
}: {
  onClick?: () => void;
  profileImage?: string;
  profileName?: string;
}) => {
  const animalImg = useRecoilValue(profileState);

  const navigate = useNavigate();

  const goCharacter = () => {
    navigate("/children/character");
  };

  return (
    <div onClick={goCharacter}>
      <OuterCircle onClick={onClick}>
        <InnerCircle>
          {profileImage === "" ? (
            <Plus>+</Plus>
          ) : (
            <ProfileImage src={animalImg.animal.imgUrl} />
          )}
        </InnerCircle>
      </OuterCircle>
      <Name>{profileName}</Name>
    </div>
  );
};

export default ProfileCircle;
