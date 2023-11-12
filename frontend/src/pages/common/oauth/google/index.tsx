import LoadingPage from "@/pages/common/loading";
import axios from "axios";
import { useSetRecoilState } from "recoil";
import { profileState } from "@/states/children/info";
import { useNavigate } from "react-router-dom";

const GoogleRedirect = () => {
  const navigate = useNavigate();
  const setProfileData = useSetRecoilState(profileState);
  const params = new URL(document.location.toString()).searchParams;
  const code = params.get("code");

  axios
    .get(`${process.env.REACT_APP_BASE_URL}/api/oauth/google?code=${code}`)
    .then((res) => {
      localStorage.setItem("accessToken", res.headers.accesstoken);
      setProfileData(res.data.data.profileList[0]);
      navigate("/parent/main");
    });

  return (
    <>
      <LoadingPage />
    </>
  );
};

export default GoogleRedirect;
