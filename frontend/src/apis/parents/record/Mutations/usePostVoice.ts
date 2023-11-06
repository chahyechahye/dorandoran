import { useMutation } from "@tanstack/react-query";
import { postVoice } from "@/apis/parents/record/recordAPI";

const usePostVoice = () => {
  return useMutation(
    ({ file, gender }: { file: File; gender: string }) => {
      return postVoice(file, gender);
    },
    {
      onSuccess: () => {
        console.log("성공");
      },
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { usePostVoice };
