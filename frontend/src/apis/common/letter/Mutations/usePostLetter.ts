import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postAlbum } from "@/apis/common/album/albumAPI";

const usePostLetter = () => {
  const queryCilent = useQueryClient();
  return useMutation((formData: FormData) => postAlbum(formData), {
    onSuccess: () => {
      queryCilent.invalidateQueries(["LetterList"]);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostLetter };
