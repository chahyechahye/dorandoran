import {
  FairytaleContentProps,
  FairytaleListProps,
  FairytaleReadProps,
  FairytaleSearchProps,
} from "@/types/children/fairytaleType";
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
});

export const profileListState = atom<ChildrenProfileProps[]>({
  key: "profileListState", // 고유한 문자열 키
  default: [], // 초기 상태 (비어있는 배열로 초기화)
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
});

export const fairytaleState = atom<FairytaleListProps>({
  key: "fairytaleState",
  default: {
    bookId: 0,
    title: "",
    imgUrl: "",
    author: null,
    publisher: null,
    totalPageCnt: 0,
  },
});

export const fairytaleReadListState = atom<FairytaleReadProps[]>({
  key: "fairytaleReadListState",
  default: [],
});

export const fairytaleReadState = atom<FairytaleReadProps[]>({
  key: "fairytaleReadState",
  default: [
    {
      pageId: 0,
      idx: 0,
      imgUrl: "",
      contentResDto: [
        {
          content_id: 0,
          script: "",
          pv_id: 0,
          voiceUrl: null,
        },
      ],
    },
  ],
});

export const fairytaleContentState = atom<FairytaleContentProps>({
  key: "fairytaleContentState",
  default: {
    content_id: 0,
    script: "",
    pv_id: 0,
    voiceUrl: null,
  },
});

export const fairytaleContentListState = atom<FairytaleContentProps[]>({
  key: "fairytaleContentState",
  default: [
    {
      content_id: 0,
      script: "",
      pv_id: 0,
      voiceUrl: null,
    },
  ],
});

export const FairytaleSearchState = atom<FairytaleSearchProps>({
  key: "FairytaleSearchState",
  default: {
    bookId: 0,
    gender: "FEMALE",
  },
});
