interface LikeBookProps {
  profileId: number;
}

interface Book {
  bookId: number;
  title: string;
  imgUrl: string;
  author: string | null;
  publisher: string | null;
  totalPageCnt: number;
}

export type { LikeBookProps, Book };
