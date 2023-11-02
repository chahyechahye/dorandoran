import { useQuery } from "@tanstack/react-query";
import { getFavoriteBook } from "@/apis/parents/likeBook/likeBookAPI";
import { LikeBookProps } from "@/types/parent/likeBookType";

const useGetFavoriteBook = (profileId: LikeBookProps) => {
  const { data } = useQuery(["FavoriteBook", profileId], () =>
    getFavoriteBook(profileId)
  );
  return data;
};

export { useGetFavoriteBook };
