import React, { useState, useEffect, useRef, useCallback } from "react";
import styled from "styled-components";
import * as THREE from "three";

import eraser from "@/assets/img/eraser.png";

import blackPen from "@/assets/img/pen/blackPen.png";
import redPen from "@/assets/img/pen/redPen.png";
import orangePen from "@/assets/img/pen/orangePen.png";
import yellowPen from "@/assets/img/pen/yellowPen.png";
import greenPen from "@/assets/img/pen/greenPen.png";
import skyBluePen from "@/assets/img/pen/skyBluePen.png";
import bluePen from "@/assets/img//pen/bluePen.png";
import purplePen from "@/assets/img/pen/purplePen.png";
import letterImage from "@/assets/img/letter/letterImage.png";
import sketchBackground from "@/assets/img/sketchBackground.png";

const Body = styled.div`
  background-color: #fff;
  overflow: hidden;
`;

const Background = styled.div`
  position: fixed;
  background: url(${sketchBackground});
  background-size: contain;
  background-repeat: no-repeat;
  width: 100%;
  height: 100%;
  z-index: 0;
`;

const Colours = styled.ul`
  bottom: 0px;
  display: none;
  left: 50%;
  list-style-type: none;
  padding-left: 0;
  position: fixed;
  transform: translateX(-50%);
  z-index: 4;

  @media (min-width: 1024px) {
    display: flex;
  }
`;

const ColourItem = styled.li`
  display: inline-block;
  height: 20vh;
  margin: 0 12px;
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

const EraserButton = styled.div`
  background: url(${eraser});
  background-size: contain;
  background-repeat: no-repeat;
  bottom: 18px;
  cursor: pointer;
  height: 26px;
  padding: 4px 1px 0px;
  position: fixed;
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

const SendButtonContainer = styled.div`
  position: fixed;
  display: block;
  top: 4vh;
  right: 5vh;
  z-index: 3;
  width: 15vh;
  height: 15vh;
`;

const SendButton = styled.div`
  background: url(${letterImage});
  background-size: contain;
  background-repeat: no-repeat;
  padding: 4px 1px 0px;
  text-align: center;
  width: 15vh;
  height: 15vh;
  z-index: 3;
`;

const DrawingCanvas = styled.canvas``;

const Sketch = () => {
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

  const pencilPathDefaults = {
    minThickness: 4,
    maxThickness: 20,
  };

  const [pencilThickness, setPencilThickness] = useState(
    pencilPathDefaults.minThickness
  );

  const canvasRef = useRef(null);

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

      const xPos = e.touches ? e.touches[0].clientX : e.clientX;
      const yPos = e.touches ? e.touches[0].clientY : e.clientY;

      const currentPoint = { x: xPos, y: yPos };

      drawingCtxRef.current.beginPath();
      drawingCtxRef.current.fillStyle = colors[currentColorIndex];
      drawingCtxRef.current.globalAlpha = 0.9;
      drawingCtxRef.current.arc(
        currentPoint.x + 5,
        currentPoint.y + 5,
        pencilPathDefaults.thickness,
        false,
        Math.PI * 2,
        false
      );
      drawingCtxRef.current.fill();

      lastPoint.current = currentPoint; // Store in useRef
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
    drawingCanvas.width = window.innerWidth * 0.9;
    drawingCanvas.height = window.innerHeight * 0.9;

    drawingCanvas.style.background = "#fff";
    drawingCanvas.style.position = "fixed";
    drawingCanvas.style.left = "50%";
    drawingCanvas.style.top = "50%";
    drawingCanvas.style.transform = "translate(-50%, -50%)";
    drawingCanvas.style.zIndex = 1;
    drawingCanvas.style.borderRadius = 20;

    document.body.appendChild(drawingCanvas);

    drawingCtxRef.current = drawingCanvas.getContext("2d");

    const pencilPathTarget = { thickness: 0.2 };

    const handleMouseMove = (e) => {
      const xPos = e.touches ? e.touches[0].clientX : e.clientX;
      const yPos = e.touches ? e.touches[0].clientY : e.clientY;

      // Update the mouse variable
      e.preventDefault();
      setMouse({
        x: (xPos / window.innerWidth) * 2 - 1,
        y: -(yPos / window.innerHeight) * 2 + 1,
      });

      if (isEraserMode) {
        drawingCtxRef.current.globalCompositeOperation = "destination-out"; // 지우개 모드일 때
      } else {
        drawingCtxRef.current.fillStyle = colors[currentColorIndex]; // 펜 모드일 때
        drawingCtxRef.current.globalAlpha = getRandomInt(0.15, 0.25);
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

  const stopScroll = (e) => {
    e.preventDefault();
  };

  const clearCanvas = () => {
    if (drawingCtxRef.current && drawingCtxRef.current.canvas) {
      const canvas = drawingCtxRef.current.canvas;
      drawingCtxRef.current.clearRect(0, 0, canvas.width, canvas.height);
    }
  };

  const savePNG = () => {
    const drawingCanvas = drawingCtxRef.current.canvas; // Access the canvas from the context ref

    const freshCanvas = document.createElement("canvas");
    freshCanvas.width = drawingCanvas.width;
    freshCanvas.height = drawingCanvas.height;

    const freshCtx = freshCanvas.getContext("2d");

    freshCtx.fillStyle = "#f7f4f0";
    freshCtx.fillRect(0, 0, freshCanvas.width, freshCanvas.height);
    freshCtx.drawImage(drawingCanvas, 0, 0);

    const imageDataURL = freshCanvas.toDataURL();
    const image = new Image();

    image.src = imageDataURL;

    const w = window.open("");
    w.document.write(image.outerHTML);
  };

  return (
    <Body className="body">
      <Background />
      <Colours className="colours">
        {colors.map((color, index) => (
          <ColourItem
            key={index}
            onClick={() => {
              setCurrentColorIndex(index);
              setPreviousColorIndex(index);
              setPencilThickness(pencilPathDefaults.minThickness);
            }}
          ></ColourItem>
        ))}
      </Colours>

      <EraserButton onClick={toggleEraser}></EraserButton>
      <SendButtonContainer>
        <SendButton onClick={savePNG}></SendButton>
        <p style={{ fontSize: "4vh", color: "red" }}>보내기 </p>
      </SendButtonContainer>

      <DrawingCanvas ref={canvasRef}></DrawingCanvas>
    </Body>
  );
};

export default Sketch;
