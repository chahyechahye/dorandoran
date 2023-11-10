import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const MainSoundState = atom<boolean>({
  key: "MainSound",
  default: true,
  effects_UNSTABLE: [persistAtom],
});
