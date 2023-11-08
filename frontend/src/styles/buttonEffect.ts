import { css } from "styled-components";

const ButtonEffect = css`
  transition:
    transform ease-in 0.1s,
    box-shadow ease-in 0.25s;

  &:focus {
    outline: 0;
  }

  &:active {
    transform: scale(0.9);
    background-color: darken($button-bg, 5%);
    box-shadow: 0 2px 25px rgba(255, 0, 130, 0.2);
  }
`;

export { ButtonEffect };
