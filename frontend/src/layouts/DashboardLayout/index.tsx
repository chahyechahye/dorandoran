import React, { ReactNode, useEffect, useState } from "react";
import styled from "styled-components";

import Logo from "@/assets/img/logo/logo.png";

const MainLogo = styled.div`
  position: fixed;
  height: 13vh;
  width: 25vh;
  background-image: url(${Logo});
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
  margin: 0vh 2vh;
  z-index: 999;

  /* 호버 시 크기와 트랜지션 설정 */
  transition:
    transform 0.3s ease,
    width 0.3s ease,
    height 0.3s ease;

  /* 호버 시 크기 확대 */
  &:hover {
    transform: scale(1.07); /* 크기 확대 설정 */
  }
`;

interface DashboardLayoutProps {
  children: ReactNode; // children을 React.ReactNode 타입으로 지정
}

const DashboardLayout: React.FC<DashboardLayoutProps> = ({ children }) => {
  const currentPathname = window.location.pathname; // 현재 URL 경로 가져오기

  // 함수를 사용하여 경로를 변경하도록 설정
  const redirectToMain = () => {
    if (currentPathname.startsWith("/parent/")) {
      window.location.href = "/parent/main";
    } else if (currentPathname.startsWith("/children/")) {
      window.location.href = "/children/main";
    }
  };

  return (
    <>
      <MainLogo onClick={redirectToMain} />
      <div>{children}</div>
    </>
  );
};

export default DashboardLayout;
