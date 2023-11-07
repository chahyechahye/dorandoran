interface FairytaleListProps {
  bookId: number;
  title: string;
  imgUrl: string;
  author: null;
  publisher: null;
  totalPageCnt: number;
}

interface FairytaleContentProps {
  content_id: number;
  script: string;
  pv_id: number;
  voiceUrl: string | null;
}

interface FairytaleReadProps {
  pageId: number;
  idx: number;
  imgUrl: string;
  contentResDto: FairytaleContentProps[];
}

interface FairytaleSearchProps {
  bookId: number;
  gender: string;
}

interface AnimalListProps {
  id: number;
  name: string;
  imgUrl: string;
  color: string;
}

export type {
  FairytaleListProps,
  FairytaleReadProps,
  FairytaleContentProps,
  FairytaleSearchProps,
  AnimalListProps,
};
