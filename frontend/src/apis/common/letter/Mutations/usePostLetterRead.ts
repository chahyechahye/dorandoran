import { postLetterRead } from "@/apis/common/letter/letterAPI";
import { useMutation, useQueryClient } from "@tanstack/react-query";

const usePostLetterRead = () => {
  const queryCilent = useQueryClient();
  return useMutation(() => postLetterRead(), {
    onSuccess: () => {
      queryCilent.invalidateQueries(["LetterList"]);
    },
    onError: (err: Error) => {
      console.log("Error in usePostLetterRead:", err);
    },
  });
};

export { usePostLetterRead };
