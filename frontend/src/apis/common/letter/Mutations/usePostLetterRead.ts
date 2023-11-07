import { postLetterRead } from "@/apis/common/letter/letterAPI";
import { useMutation } from "@tanstack/react-query";

const usePostLetterRead = () => {
  return useMutation(() => postLetterRead(), {
    onSuccess: () => {
      console.log("읽음");
    },
    onError: (err: Error) => {
      console.log("Error in usePostLetterRead:", err);
    },
  });
};

export { usePostLetterRead };
