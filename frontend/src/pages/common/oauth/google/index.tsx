import { useEffect } from "react";
import LoadingPage from "@/pages/common/loading";
import axios from "axios";

const GoogleRedirect = () => {
  const params = new URL(document.location.toString()).searchParams;
  const code = params.get("code");

  axios
    .get(`${process.env.REACT_APP_BASE_URL}/api/oauth/google?code=${code}`)
    .then((res) => {
      localStorage.setItem("accesstoken", res.headers.accesstoken);
      window.location.href = "/parent/main";
    });

  return (
    <>
      <LoadingPage />
    </>
  );
};

export default GoogleRedirect;
