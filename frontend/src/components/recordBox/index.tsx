import React from "react";

const RecordBox = () => {
  return (
    <div
      style={{
        position: "relative",
        fontSize: "4vh",
        background: "#78BFFC",
        color: "#fff",
        padding: "7vh 10vh",
        borderRadius: "3vh",
        width: "100vh",
        maxWidth: "160vh",
      }}
    >
      <p>
        이 요리는 다양한 빛깔로 만들어 저녁에 먹으면 약간 짜게 느껴질 수
        있습니다.
      </p>
      <div
        style={{
          position: "absolute",
          right: 30,
          top: "50%",
          transform: "translate(-50%, -50%)",
        }}
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="50"
          height="70"
          viewBox="0 0 25 32"
          fill="none"
        >
          <path d="M0 0V31.5L24.75 15.75L0 0Z" fill="white" />
        </svg>
      </div>
    </div>
  );
};

export default RecordBox;
