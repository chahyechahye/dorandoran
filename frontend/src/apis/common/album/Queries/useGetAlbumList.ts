import { useQuery } from "@tanstack/react-query";
import { getAlbumList } from "@/apis/common/album/albumAPI";

const useGetAlbumList = () => {
  const { data } = useQuery(["AlbumList"], () => getAlbumList());
  return data;
};

export { useGetAlbumList };
