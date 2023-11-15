import styled, { keyframes } from "styled-components";
import background from "@/assets/img/background/background.jpg";
import logoImageSrc from "@/assets/img/logo/logo.png";
import ClickButton from "@/components/clickButton";
import { useNavigate } from "react-router-dom";
import { useChildrenCode } from "@/apis/children/profile/Queries/useChildrenCode";
import { useRecoilState, useSetRecoilState } from "recoil";
import { ChildrenInfoState, profileListState } from "@/states/children/info";
import { useEffect, useState, useReducer, useCallback } from "react";
import { postChildrenLogin } from "@/apis/children/profile/profileAPI";
import { useChildrenLogin } from "@/apis/children/profile/Mutations/useChildrenLogin";

const Background = styled.body`
  user-select: none;
  top: 0;
  left: 0;
  width: 30vh;
  height: 30vh;
`;

// 스타일드 컴포넌트 생성
const ContainerBox = styled.div`
  #highlight-position {
    width: 50px;
    height: 50px;
    border-radius: 100px;
    box-shadow: 0 2px 25px rgb(0 90 255 / 16%);
    position: absolute;
    top: 0;
    will-change: left, top;
    left: 0;
    border: 2px solid rgb(174 184 255 / 25%);
    pointer-events: none;
    opacity: 0;
  }

  #confetti-box {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }

  .confetti {
    position: absolute;
    z-index: 9999;
  }

  .confetti-item {
    width: 10px;
    position: absolute;
    top: 0;
    left: 0;
    height: 10px;
  }

  .confetti-item.reverse {
  }
`;

const ReverseConfettiItem = styled(ContainerBox)``;

const HighlightPosition = styled.div`
  width: 50px;
  height: 50px;
  border-radius: 100px;
  box-shadow: 0 2px 25px rgb(0 90 255 / 16%);
  position: absolute;
  top: 0;
  will-change: left, top;
  left: 0;
  border: 2px solid rgb(174 184 255 / 25%);
  pointer-events: none;
  opacity: 0;
`;

const ClickEventComponent = () => {
  /* Random Id generator for giving confetti elements unique IDs */
  const randomId = function (length: number) {
    const result = [];
    const characters =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    const charactersLength = characters.length;
    for (let i = 0; i < length; i++) {
      result.push(
        characters.charAt(Math.floor(Math.random() * charactersLength))
      );
    }
    return result.join("");
  };

  /* Short function to create confetti at x, y with confettiItems number of items */
  const createConfetti = function (
    x: number,
    y: number,
    confettiItems: number
  ) {
    // createElement.classList.add("Confetti");
    const newDiv = document.createElement("div");
    newDiv.classList.add("confetti");
    const makeId = randomId(10);
    newDiv.setAttribute("data-id", makeId);
    let confettiHTML = "";
    const colors = ["#2162ff", "#9e21ff", "#21a9ff", "#a9ff21", "#ff2184"];

    for (let i = 0; i < confettiItems; ++i) {
      const color = Math.floor(Math.random() * colors.length);
      confettiHTML += `<div class="confetti-item" style="background-color: ${
        colors[color]
      };" data-angle="${Math.random()}" data-speed="${Math.random()}"></div>`;
      confettiHTML += `<div class="confetti-item reverse" style="background-color: ${
        colors[color]
      };" data-angle="${Math.random()}" data-speed="${Math.random()}"></div>`;
    }
    newDiv.style.position = `fixed`;
    newDiv.style.top = `${y}px`;
    newDiv.style.left = `${x}px`;
    newDiv.innerHTML = confettiHTML;
    document.body.appendChild(newDiv);

    const gravity = 50; // Fjolt is a high gravity planet
    const maxSpeed = 105000; // Pixels * 1000
    const minSpeed = 65000; // Pixels * 1000
    let t = 0; // Time starts at 0
    const maxAngle = 1500; // Radians * 1000
    const minAngle = 400; // Radians * 1000
    let opacity = 1;
    let rotateAngle = 0;

    const interval = setInterval(function () {
      document
        .querySelectorAll(`[data-id="${makeId}"] ConfettiItem`)
        .forEach(function (item) {
          let modifierX = 1;
          const modifierY = 1;
          if (item.classList.contains("reverse")) {
            modifierX = -1;
          }
          (item as any).style.opacity = opacity;

          const randomNumber = parseFloat(
            (item as any).getAttribute("data-angle")
          );
          const otherRandom = parseFloat(
            (item as any).getAttribute("data-speed")
          );

          const newRotateAngle = randomNumber * rotateAngle;
          const angle =
            (randomNumber * (maxAngle - minAngle) + minAngle) / 1000;
          const speed =
            (randomNumber * (maxSpeed - minSpeed) + minSpeed) / 1000;
          const realT =
            t * parseFloat((item as any).getAttribute("data-angle"));

          const x = speed * t * Math.cos(angle) + 50 * otherRandom * t;
          const y =
            speed * t * Math.sin(angle) -
            0.5 * gravity * Math.pow(t, 2) +
            50 * otherRandom * t;

          (item as any).style.transform = `translateX(${
            x * modifierX
          }px) translateY(${
            y * -1 * modifierY
          }px) rotateY(${newRotateAngle}deg) scale(${1})`;
        });
      t += 0.1;
      rotateAngle += 3;
      opacity -= 0.02;

      if (t >= 6) {
        t = 0.1;
        if (document.querySelector(`[data-id="${makeId}"]`) !== null) {
          (document as any).querySelector(`[data-id="${makeId}"]`).remove();
        }
        clearInterval(interval);
      }
    }, 33.33);
  };

  document.addEventListener("DOMContentLoaded", function (e) {
    (document as any)
      .getElementById("confetti-box")
      .addEventListener("pointerdown", function (e: any) {
        createConfetti(e.pageX, e.pageY, 20);
      });
    (document as any)
      .getElementById("highlight-position")
      .addEventListener("pointerdown", function (e: any) {
        createConfetti(e.pageX, e.pageY, 20);
      });
    (document as any)
      .getElementById("confetti-box")
      .addEventListener("pointermove", function (e: any) {
        (document as any).getElementById(
          "highlight-position"
        ).style.opacity = 1;
        (document as any).getElementById("highlight-position").style.top = `${
          e.pageY - 25
        }px`;
        (document as any).getElementById("highlight-position").style.left = `${
          e.pageX - 25
        }px`;
      });
  });

  return (
    <ContainerBox>
      <div id="confetti-box">{/* 내용 */}</div>
      <div id="info-box">
        <span>Click Anywhere</span>
      </div>
      <div id="highlight-position"></div>
    </ContainerBox>
  );
};

export default ClickEventComponent;
