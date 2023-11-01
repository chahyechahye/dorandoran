import { useEffect } from "react";
import axios from "axios";

const RedirectPage = () => {
  useEffect(() => {
    const params = new URL(document.location.toString()).searchParams;
    const code = params.get("code");
    const grantType = "authorization_code";
    const clientId = `${process.env.REACT_APP_RESTAPI_KAKAO_APP_KEY}`;
    const REDIRECT_URL = `${process.env.REACT_APP_BASE_URL}/oauth/redirect`;

    axios
      .post(
        `https://kauth.kakao.com/oauth/token?grant_type=${grantType}&client_id=${clientId}&redirect_uri=${REDIRECT_URL}&code=${code}`,
        {},
        {
          headers: {
            "Content-type": "application/x-www-form-urlencoded;charset=utf-8",
          },
        }
      )
      .then((res) => {
        console.log(res);
      });
  }, []);
  //   console.log("대박");
  //   const params = new URLSearchParams(location.search);
  //   const accessToken = params.get("accessToken");

  //   localStorage.setItem("accessToken", accessToken!);
  //   if (accessToken && accessToken.length > 0) {
  //     window.location.href = "/parent/main";
  //   }
  return <></>;
};

export default RedirectPage;
