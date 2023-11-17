import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const MainSoundState = atom<boolean>({
  key: "MainSound",
  default: false,
  effects_UNSTABLE: [persistAtom],
});

export const SoundState = atom<boolean>({
  key: "soundState",
  default: true,
  effects_UNSTABLE: [persistAtom],
});
