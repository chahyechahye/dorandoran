const RedirectPage = () => {
  console.log("있냐?");
  const params = new URLSearchParams(location.search);
  const accessToken = params.get("accessToken");

  localStorage.setItem("accessToken", accessToken!);
  if (accessToken && accessToken.length > 0) {
    window.location.href = "/parent/main";
  }
  return <></>;
};

export default RedirectPage;
