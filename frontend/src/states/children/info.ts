import { ChildrenProfileProps } from "@/types/children/profileType";
import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const ChildrenInfoState = atom<number>({
  key: "ChildrenInfoState",
  default: 0,
  effects_UNSTABLE: [persistAtom],
});

export const bookListState = atom({
  key: "bookListState", // 고유한 문자열 키
  default: [], // 초기 상태 (비어있는 배열로 초기화)
  effects_UNSTABLE: [persistAtom],
});

export const profileListState = atom<ChildrenProfileProps[]>({
  key: "profileListState", // 고유한 문자열 키
  default: [], // 초기 상태 (비어있는 배열로 초기화)
  effects_UNSTABLE: [persistAtom],
});

export const profileState = atom<ChildrenProfileProps>({
  key: "profileState", // 고유한 문자열 키
  default: {
    id: 0,
    childId: 0,
    animal: {
      id: 0,
      name: "",
      imgUrl: "",
    },
    name: "",
  },
  effects_UNSTABLE: [persistAtom],
});
