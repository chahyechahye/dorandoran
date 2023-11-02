import { deleteAlbum } from "@/apis/common/album/albumAPI";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { AlbumProps } from "@/types/parent/albumType";

// 일기 삭제
const useDeleteAlbum = () => {
  const queryCilent = useQueryClient();
  return useMutation((albumId: AlbumProps) => deleteAlbum(albumId), {
    onSuccess: () => {
      queryCilent.invalidateQueries(["AlbumList"]);
      console.log("deleteAlbum Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useDeleteAlbum };
