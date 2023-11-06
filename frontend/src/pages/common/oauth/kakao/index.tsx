import LoadingPage from "@/pages/common/loading";
import axios from "axios";
import { useSetRecoilState } from "recoil";
import { profileState } from "@/states/children/info";

const KakaoRedirect = () => {
  const setProfileData = useSetRecoilState(profileState);
  const params = new URL(document.location.toString()).searchParams;
  const code = params.get("code");

  axios
    .get(`${process.env.REACT_APP_BASE_URL}/api/oauth/kakao?code=${code}`)
    .then((res) => {
      localStorage.setItem("accessToken", res.headers.accesstoken);
      console.log(res.data.data.profileList[0]);
      setProfileData(res.data.data.profileList[0]);
      window.location.href = "/parent/main";
    });

  return (
    <>
      <LoadingPage />
    </>
  );
};

export default KakaoRedirect;
