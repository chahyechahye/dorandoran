import { useMutation } from "@tanstack/react-query";
import { postAlbum } from "@/apis/common/album/albumAPI";

const usePostAlbum = () => {
  return useMutation((formData: FormData) => postAlbum(formData), {
    onSuccess: () => {
      console.log("usePostAlbum Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostAlbum };
