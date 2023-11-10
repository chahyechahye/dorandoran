import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postLetter } from "@/apis/common/letter/letterAPI";
import { LetterProps } from "@/types/parent/letterType";

const usePostLetter = () => {
  return useMutation((letterData: LetterProps) => postLetter(letterData), {
    onSuccess: () => {
      console.log("전송 성공");
    },
    onError: (err) => {
      console.log(err);
    },
  });
};

export { usePostLetter };
