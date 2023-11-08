import React, { useState, useRef } from "react";

function MemeMaker() {
  const [isPainting, setIsPainting] = useState(false);
  const [isFilling, setIsFilling] = useState(false);
  const [lineWidth, setLineWidth] = useState(5);
  const [currentColor, setCurrentColor] = useState("#ffeaec");
  const [text, setText] = useState("");

  const colorOptions = [
    "#ffeaec",
    "#f39a9d",
    "#6db1bf",
    "#2d936c",
    "#8e44ad",
    "#301a4b",
    "#00c49a",
    "#f8e16c",
    "#ffc2b4",
    "#d35400",
    "#e67e22",
  ];

  const canvasRef = useRef(null);

  const CANVAS_WIDTH = window.innerWidth * 0.9;
  const CANVAS_HEIGHT = window.innerWidth * 0.9;

  const handleCanvasMouseDown = () => {
    setIsPainting(true);
  };

  const handleCanvasMouseUp = () => {
    setIsPainting(false);
  };

  const handleCanvasMouseMove = (event) => {
    if (isPainting) {
      const canvas = canvasRef.current;
      const ctx = canvas.getContext("2d");
      ctx.lineTo(event.nativeEvent.offsetX, event.nativeEvent.offsetY);
      ctx.stroke();
    }
  };

  const handleLineWidthChange = (event) => {
    setLineWidth(event.target.value);
  };

  const handleColorChange = (event) => {
    setCurrentColor(event.target.value);
  };

  const handleColorClick = (color) => {
    setCurrentColor(color);
  };

  const handleModeClick = () => {
    setIsFilling((prevIsFilling) => !prevIsFilling);
  };

  const handleCanvasClick = () => {
    if (isFilling) {
      const canvas = canvasRef.current;
      const ctx = canvas.getContext("2d");
      ctx.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    }
  };

  const handleDestroyClick = () => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext("2d");
    ctx.fillStyle = "white";
    ctx.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
  };

  const handleEraseClick = () => {
    setCurrentColor("white");
    setIsFilling(false);
  };

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    const url = URL.createObjectURL(file);
    const image = new Image();
    image.src = url;
    image.onload = () => {
      const canvas = canvasRef.current;
      const ctx = canvas.getContext("2d");
      ctx.drawImage(image, 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
      event.target.value = null;
    };
  };

  const handleTextChange = (event) => {
    setText(event.target.value);
  };

  const handleSaveClick = () => {
    const canvas = canvasRef.current;
    const url = canvas.toDataURL();
    const a = document.createElement("a");
    a.href = url;
    a.download = "myDrawing.png";
    a.click();
  };

  return (
    <div>
      <canvas
        ref={canvasRef}
        width={CANVAS_WIDTH}
        height={CANVAS_HEIGHT}
        onMouseDown={handleCanvasMouseDown}
        onMouseUp={handleCanvasMouseUp}
        onMouseMove={handleCanvasMouseMove}
        onClick={handleCanvasClick}
      />
      <input
        type="range"
        min="1"
        max="10"
        value={lineWidth}
        step="0.1"
        onChange={handleLineWidthChange}
      />
      <input type="color" value={currentColor} onChange={handleColorChange} />
      <div className="color-options">
        {colorOptions.map((color, index) => (
          <div
            key={index}
            className="color-option"
            style={{ backgroundColor: color }}
            onClick={() => handleColorClick(color)}
          ></div>
        ))}
      </div>
      <button onClick={handleModeClick}>Toggle Mode</button>
      <button onClick={handleDestroyClick}>Clear Canvas</button>
      <button onClick={handleEraseClick}>Erase</button>
      <label>
        Add Image
        <input type="file" accept="image/*" onChange={handleFileChange} />
      </label>
      <input
        type="text"
        placeholder="Enter Text"
        value={text}
        onChange={handleTextChange}
      />
      <button onClick={handleSaveClick}>Save</button>
    </div>
  );
}

export default MemeMaker;
