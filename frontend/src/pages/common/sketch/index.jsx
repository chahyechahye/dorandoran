import React, { useState, useEffect, useRef, useCallback } from "react";
import styled from "styled-components";
import * as THREE from "three";

import eraser from "@/assets/img/eraser.png";
import { useRecoilValue } from "recoil";
import { profileState } from "@/states/children/info";
import { usePostLetter } from "@/apis/common/letter/Mutations/usePostLetter";
import { ButtonEffect } from "@/styles/buttonEffect";
import { useSoundEffect } from "@/components/sounds/soundEffect";

import blackPen from "@/assets/img/pen/blackPen.png";
import redPen from "@/assets/img/pen/redPen.png";
import orangePen from "@/assets/img/pen/orangePen.png";
import yellowPen from "@/assets/img/pen/yellowPen.png";
import greenPen from "@/assets/img/pen/greenPen.png";
import skyBluePen from "@/assets/img/pen/skyBluePen.png";
import bluePen from "@/assets/img//pen/bluePen.png";
import purplePen from "@/assets/img/pen/purplePen.png";
import exitBtn from "@/assets/img/exitBtn.png";
import Logo from "@/assets/img/logo/logo.png";

import html2canvas from "html2canvas";
import sketchBackground from "@/assets/img/sketchBackground.png";
import { background } from "@/assets/img/background/backgroundMain.jpg";

import letterSend from "@/assets/img/letter/letterSend.png";
import { usePostAlbum } from "@/apis/common/album/Mutations/usePostAlbum";
import { toast } from "react-toastify";

const Body = styled.div`
  width: 100%;
  height: 100vh;
  background: url(${sketchBackground});
  background-size: cover;
  overflow: hidden;
`;

const Colours = styled.ul`
  bottom: 0px;
  display: none;
  left: 50%;
  list-style-type: none;
  padding-left: 0;
  position: absolute;
  transform: translateX(-50%);
  z-index: 4;

  @media (min-width: 1024px) {
    display: flex;
  }
`;

const ColourItem = styled.li`
  display: inline-block;
  height: 20vh;
  margin: ${({ index, currentColorIndex }) =>
    index === currentColorIndex ? "0px 12px" : "30px 12px 0px"};
  width: 5vh;

  &:nth-child(1) {
    background: url(${blackPen});
    background-size: contain;
    background-repeat: no-repeat;
  }
  &:nth-child(2) {
    background: url(${redPen});
    background-size: contain;
    background-repeat: no-repeat;
  }
  &:nth-child(3) {
    background: url(${orangePen});
    background-size: contain;
    background-repeat: no-repeat;
  }
  &:nth-child(4) {
    background: url(${yellowPen});
    background-size: contain;
    background-repeat: no-repeat;
  }
  &:nth-child(5) {
    background: url(${greenPen});
    background-size: contain;
    background-repeat: no-repeat;
  }
  &:nth-child(6) {
    background: url(${skyBluePen});
    background-size: contain;
    background-repeat: no-repeat;
  }
  &:nth-child(7) {
    background: url(${bluePen});
    background-size: contain;
    background-repeat: no-repeat;
  }
  &:nth-child(8) {
    background: url(${purplePen});
    background-size: contain;
    background-repeat: no-repeat;
  }
`;

const RefreshButton = styled.div`
  background: url(${eraser});
  background-size: contain;
  background-repeat: no-repeat;
  bottom: 18px;
  height: 26px;
  padding: 4px 1px 0px;
  position: absolute;
  left: 50%;
  text-align: center;
  transform: translateX(-50%);
  width: 15vh;
  height: 15vh;
  z-index: 3;

  @media (min-width: 1024px) {
    bottom: 27px;
    left: 30px;
    transform: none;
  }
`;

const SubmitButton = styled.button`
  bottom: 18px;
  display: none;
  position: absolute;
  right: 30px;
  z-index: 4;
  background: url(${letterSend});
  width: 20vh;
  height: 20vh;
  background-size: contain;
  background-repeat: no-repeat;
  border: none;

  @media (min-width: 1024px) {
    display: block;
  }
`;

const Background = styled.div`
  position: fixed;
  background: #fff;
  width: 90%; // 화면 너비의 90%
  height: 90vh; // 화면 높이의 90%
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
`;

const ExitContainer = styled.div`
  position: fixed;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  top: 0;
  right: 0;
  margin: 2vh 4vh;
  z-index: 5;

  ${ButtonEffect}
`;

const ExitBtn = styled.img`
  width: 12vh;
`;

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

const DrawingCanvas = styled.canvas``;

const SketchPage = () => {
  const [colors] = useState([
    "#100c08",
    "#C91931",
    "#EF7E0E",
    "#F0B10D",
    "#7CAC1C",
    "#03AEC4",
    "#03497E",
    "#6E2A84",
    "#ffffff",
  ]);
  const [currentColorIndex, setCurrentColorIndex] = useState(0);
  const [previousColorIndex, setPreviousColorIndex] = useState(null);
  const [isDrawing, setIsDrawing] = useState(false);
  const [newlyUp, setNewlyUp] = useState(false);
  const lastPoint = useRef(null);
  const drawingCtxRef = useRef(null);
  const profileData = useRecoilValue(profileState);
  const sendLetter = usePostLetter();
  const { playSound } = useSoundEffect();
  const postAlbum = usePostAlbum();
  const [backButtonPressCount, setBackButtonPressCount] = useState(0);
  const [submitButtonClicked, setSubmitButtonClicked] = useState(false);

  const pencilPathDefaults = {
    minThickness: 4,
    maxThickness: 20,
  };

  const [pencilThickness, setPencilThickness] = useState(
    pencilPathDefaults.minThickness
  );

  const canvasRef = useRef(null);

  const drawings = useRef([]);

  function isNotClose(current, target) {
    return current < target - 0.004 || current > target + 0.004;
  }

  function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  function distanceBetween(point1, point2) {
    return Math.sqrt(
      Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2)
    );
  }

  function distanceBetweenSingle(point1, point2) {
    return point1 - point2;
  }

  function angleBetween(point1, point2) {
    return Math.atan2(point2.x - point1.x, point2.y - point1.y);
  }

  function direction(point1, point2) {
    return Math.atan2(point1, point2);
  }

  const [isEraserMode, setIsEraserMode] = useState(false);
  const [mouse, setMouse] = useState({ x: 0, y: 0 });
  const [mousePos, setMousePos] = useState(new THREE.Vector3(0, 0, 0));
  const [mouseDirection, setMouseDirection] = useState({ x: 1, y: 1 });
  const [pencilTargetPos, setPencilTargetPos] = useState({
    height: pencilPathDefaults.minThickness,
    xRotate: 0,
    yRotate: 0,
    zRotate: 0,
  });

  const toggleEraser = () => {
    // If we're currently not using the eraser
    if (currentColorIndex !== 8) {
      // Save the current color and thickness
      setPreviousColorIndex(currentColorIndex);

      // Set to eraser mode
      setCurrentColorIndex(8);
      setPencilThickness(pencilPathDefaults.maxThickness);
      clearBufferCanvas(); // 여기서 clearCanvas 함수를 호출하여 그림을 모두 지웁니다.
    } else {
      // If we're currently using the eraser, revert to the previous color and thickness
      setCurrentColorIndex(previousColorIndex);
      setPencilThickness(pencilPathDefaults.minThickness);
    }
  };

  const handleMouseDown = useCallback(
    (e) => {
      if (e.target.localName !== "canvas") {
        return;
      }
      setIsDrawing(true);
      setPencilTargetPos({ thickness: pencilPathDefaults.maxThickness });

      const canvas = canvasRef.current;
      const xPos = e.touches ? e.touches[0].clientX : e.clientX;
      const yPos = e.touches ? e.touches[0].clientY : e.clientY;

      console.log(canvas.offsetTop);

      const currentPoint = {
        x: xPos - "1vh", // 클릭된 위치에서 캔버스의 왼쪽 위치를 빼줍니다.
        y: yPos - 40, // 클릭된 위치에서 캔버스의 상단 위치를 빼줍니다.
      };

      lastPoint.current = currentPoint;

      drawingCtxRef.current.beginPath();

      // 그리기 모드일 때
      drawingCtxRef.current.fillStyle = colors[currentColorIndex];
      drawingCtxRef.current.globalAlpha = 1;
      drawingCtxRef.current.arc(
        currentPoint.x + 5,
        currentPoint.y + 5,
        pencilPathDefaults.thickness,
        false,
        Math.PI * 2,
        false
      );
      drawingCtxRef.current.fill();
    },
    [
      setIsDrawing,
      pencilPathDefaults.maxThickness,
      colors,
      currentColorIndex,
      pencilPathDefaults.thickness,
    ]
  );

  const handleMouseUp = useCallback(() => {
    setNewlyUp(true);
    setIsDrawing(false);
    setPencilTargetPos({ thickness: pencilPathDefaults.minThickness });

    setTimeout(() => {
      setNewlyUp(false);
    }, 50);
  }, [setIsDrawing, setNewlyUp, pencilPathDefaults.minThickness]);

  useEffect(() => {
    const drawingCanvas = document.createElement("canvas");
    drawingCanvas.width = window.innerWidth * 0.9; // 화면 너비의 90%
    drawingCanvas.height = window.innerHeight * 0.9; // 화면 높이의 90%

    drawingCanvas.style.position = "fixed";
    drawingCanvas.style.left = "50%";
    drawingCanvas.style.top = "50%";
    drawingCanvas.style.transform = "translate(-50%, -50%)";

    document.body.appendChild(drawingCanvas);

    drawingCtxRef.current = drawingCanvas.getContext("2d");

    const pencilPathTarget = { thickness: 0.2 };

    const handleMouseMove = (e) => {
      const canvasRect = drawingCanvas.getBoundingClientRect();
      const canvasWidth = canvasRect.width;
      const canvasHeight = canvasRect.height;

      const xPos =
        (e.touches ? e.touches[0].clientX : e.clientX) - canvasRect.left;
      const yPos =
        (e.touches ? e.touches[0].clientY : e.clientY) - canvasRect.top;

      // Update the mouse variable
      e.preventDefault();
      setMouse({
        x: (xPos / canvasWidth) * 2 - 1,
        y: -(yPos / canvasHeight) * 2 + 1,
      });

      if (isEraserMode) {
        drawingCtxRef.current.globalCompositeOperation = "destination-out"; // 지우개 모드일 때
        drawingCtxRef.current.globalAlpha = 1;
      } else {
        drawingCtxRef.current.fillStyle = colors[currentColorIndex]; // 펜 모드일 때
        drawingCtxRef.current.globalAlpha = 1;
      }

      if (!lastPoint.current) {
        lastPoint.current = { x: xPos, y: yPos };
      }

      // Mouse Tracking
      const currentPoint = { x: xPos, y: yPos };

      const angle = angleBetween(lastPoint.current, currentPoint);
      const xDist = distanceBetweenSingle(lastPoint.current.x, currentPoint.x);
      const yDist = distanceBetweenSingle(lastPoint.current.y, currentPoint.y);

      setMouseDirection({
        x: currentPoint.x > lastPoint.current.x ? 1 : -1,
        y: currentPoint.y > lastPoint.current.y ? -1 : 1,
      });

      const newXAngle = yDist / 100;
      const newZAngle = (xDist / 100) * -1;

      const maxAngle = 0.25;

      setPencilTargetPos({
        xRotate:
          newXAngle > -maxAngle && newXAngle < maxAngle
            ? newXAngle
            : newXAngle < -maxAngle
            ? -maxAngle
            : maxAngle,
        zRotate:
          newZAngle > -maxAngle && newZAngle < maxAngle
            ? newZAngle
            : newZAngle < -maxAngle
            ? -maxAngle
            : maxAngle,
      });

      if (isDrawing || newlyUp) {
        const dist = distanceBetween(lastPoint.current, currentPoint);

        for (let i = 0; i < dist; i += 0.3) {
          const x = lastPoint.current.x + Math.sin(angle) * i;
          const y = lastPoint.current.y + Math.cos(angle) * i;

          drawingCtxRef.current.beginPath();
          drawingCtxRef.current.fillStyle = colors[currentColorIndex]; // 현재 선택된 색상으로 설정
          drawingCtxRef.current.globalAlpha = getRandomInt(0.15, 0.25);
          drawingCtxRef.current.arc(
            x + 5,
            y + 5,
            pencilThickness,
            false,
            Math.PI * 2,
            false
          );
          drawingCtxRef.current.fill();
        }
      }

      lastPoint.current = currentPoint;
      drawingCtxRef.current.globalCompositeOperation = "source-over";
    };

    // Add event listeners
    document.addEventListener("touchstart", handleMouseDown);
    document.addEventListener("mousedown", handleMouseDown);
    document.addEventListener("touchend", handleMouseUp);
    document.addEventListener("mouseup", handleMouseUp);
    document.addEventListener("touchmove", handleMouseMove);
    document.addEventListener("mousemove", handleMouseMove);

    return () => {
      // Remove event listeners in the cleanup function if needed
      document.removeEventListener("touchstart", handleMouseDown);
      document.removeEventListener("mousedown", handleMouseDown);
      document.removeEventListener("touchend", handleMouseUp);
      document.removeEventListener("mouseup", handleMouseUp);
      document.removeEventListener("touchmove", handleMouseMove);
      document.removeEventListener("mousemove", handleMouseMove);
    };
  }, [
    currentColorIndex,
    handleMouseDown,
    handleMouseUp,
    isDrawing,
    colors,
    newlyUp,
    isEraserMode,
    pencilThickness,
  ]);

  const clearBufferCanvas = () => {
    if (drawingCtxRef.current && drawingCtxRef.current.canvas) {
      const canvas = drawingCtxRef.current.canvas;
      drawingCtxRef.current.clearRect(0, 0, canvas.width, canvas.height);
    }
  };

  function dataURLtoBlob(dataURL) {
    // Split the data URL to get the base64 data
    const dataParts = dataURL.split(",");
    const base64Data = dataParts[1];

    // Decode the base64 data to binary
    const binaryData = atob(base64Data);

    // Create an array buffer from the binary data
    const arrayBuffer = new ArrayBuffer(binaryData.length);
    const uint8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < binaryData.length; i++) {
      uint8Array[i] = binaryData.charCodeAt(i);
    }

    // Create a Blob from the array buffer with the correct MIME type
    const mime = dataParts[0].split(":")[1].split(";")[0];
    return new Blob([arrayBuffer], { type: mime });
  }

  const capturePage = () => {
    if (submitButtonClicked) {
      return; // Do nothing if the button has already been clicked
    }
    setSubmitButtonClicked(true);

    toast("✉ 편지를 보내는 중이예요!");
    clearBufferCanvas();
    // 요소를 숨기기 전에 display 속성을 저장합니다.
    const coloursElement = document.querySelector(".colours");
    const refreshButtonElement = document.querySelector(".refresh-button");
    const submitButtonElement = document.querySelector(".submit-button");
    const exitButtonElement = document.querySelector(".exit-button");
    const mainElement = document.querySelector(".main");

    const originalColoursDisplay = coloursElement.style.display;
    const originalRefreshButtonDisplay = refreshButtonElement.style.display;
    const originalSubmitButtonDisplay = submitButtonElement.style.display;
    const originalSubmitExitDisplay = submitButtonElement.style.display;
    const originalmainDisplay = submitButtonElement.style.display;

    // 요소를 숨깁니다.
    coloursElement.style.display = "none";
    refreshButtonElement.style.display = "none";
    submitButtonElement.style.display = "none";
    exitButtonElement.style.display = "none";
    mainElement.style.display = "none";

    // 페이지를 캡처합니다.
    html2canvas(document.body).then((canvas) => {
      // 이미지 데이터를 가져옵니다.
      const imageDataUrl = canvas.toDataURL("image/png");

      const blob = dataURLtoBlob(imageDataUrl);
      const formData = new FormData();
      formData.append("multipartFile", blob);

      postAlbum.mutateAsync(formData);

      sendLetter
        .mutateAsync({
          title: profileData.name,
          content: blob,
          profileId: profileData.id,
          senderId: profileData.childId,
        })
        .then(() => {
          handleExit();
        });

      // 이미지를 다운로드할 수 있는 링크를 생성합니다.
      // const link = document.createElement("a");
      // link.href = imageDataUrl;
      // link.download = "captured_page.png"; // 파일 이름을 지정할 수 있습니다.
      // link.click(); // 링크를 클릭하여 이미지 다운로드를 시작합니다.
    });

    // 요소의 display 속성을 복원합니다.
    coloursElement.style.display = originalColoursDisplay;
    refreshButtonElement.style.display = originalRefreshButtonDisplay;
    submitButtonElement.style.display = originalSubmitButtonDisplay;
    exitButtonElement.style.display = originalSubmitExitDisplay;
    mainElement.style.display = originalmainDisplay;
  };

  useEffect(() => {
    return () => {
      setSubmitButtonClicked(false);
    };
  }, []);

  useEffect(() => {
    const disableBackButton = (e) => {
      e.preventDefault();
    };

    window.history.pushState(null, "", window.location.href);
    window.onpopstate = () => {
      // Increment the back button press count
      setBackButtonPressCount((prevCount) => prevCount + 1);

      // Disable back button if pressed twice within 1 second
      if (backButtonPressCount === 1) {
        disableBackButton(event);

        // Reset back button press count after 1 second
        setTimeout(() => {
          setBackButtonPressCount(0);
        }, 1000);
      }
    };

    return () => {
      window.onpopstate = null;
    };
  }, [backButtonPressCount]);

  const handleExit = () => {
    playSound();
    const currentPathname = window.location.pathname; // 현재 URL 경로 가져오기

    if (currentPathname.startsWith("/parent/")) {
      window.location.href = "/parent/main";
    } else if (currentPathname.startsWith("/children/")) {
      window.location.href = "/children/main";
    }
  };

  return (
    <Body className="body">
      <Background />

      <Colours className="colours">
        {colors.map(
          (color, index) =>
            // index가 7이 아닌 경우에만 ColourItem을 생성
            index !== 8 && (
              <ColourItem
                key={index}
                index={index}
                currentColorIndex={currentColorIndex}
                onClick={() => {
                  setCurrentColorIndex(index);
                  setPreviousColorIndex(index);
                  setPencilThickness(pencilPathDefaults.minThickness);
                  clearBufferCanvas();
                }}
              ></ColourItem>
            )
        )}
      </Colours>

      <RefreshButton
        className="refresh-button"
        onClick={toggleEraser}
      ></RefreshButton>

      <MainLogo className="main" onClick={handleExit} />
      <SubmitButton
        className="submit-button"
        onClick={capturePage}
        disabled={submitButtonClicked}
      ></SubmitButton>
      <ExitContainer className="exit-button" onClick={handleExit}>
        <ExitBtn src={exitBtn}></ExitBtn>
        <p
          style={{
            fontSize: "5vh",
            color: "#f25222",
            textShadow: "2px 4px 2px rgba(0, 0, 0, 0.2)",
          }}
        >
          나가기
        </p>
      </ExitContainer>

      <DrawingCanvas ref={canvasRef}></DrawingCanvas>
    </Body>
  );
};

export default SketchPage;
