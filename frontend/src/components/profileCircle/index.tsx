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

const ProfileCircle = ({ onClick }: { onClick?: () => void }) => {
  return (
    <OuterCircle onClick={onClick}>
      <InnerCircle>
        <Plus>+</Plus>
      </InnerCircle>
    </OuterCircle>
  );
};

export default ProfileCircle;
