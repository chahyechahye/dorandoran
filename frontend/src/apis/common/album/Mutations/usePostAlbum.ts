import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postAlbum } from "@/apis/common/album/albumAPI";

const usePostAlbum = () => {
  const queryCilent = useQueryClient();
  return useMutation((formData: FormData) => postAlbum(formData), {
    onSuccess: () => {
      queryCilent.invalidateQueries(["AlbumList"]);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostAlbum };
