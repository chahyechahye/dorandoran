import { useQuery } from "@tanstack/react-query";
import { getFavoriteBook } from "@/apis/parents/likeBook/likeBookAPI";

const useGetFavoriteBook = (profileId: number) => {
  const { data } = useQuery(["FavoriteBook", profileId], () =>
    getFavoriteBook(profileId)
  );
  return data;
};

export { useGetFavoriteBook };
