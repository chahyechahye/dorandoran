import { useMutation } from "@tanstack/react-query";
import { postVoiceComplete } from "@/apis/parents/record/recordAPI";

const usePostVoiceComplete = () => {
  return useMutation((genders: string) => postVoiceComplete(genders), {
    onSuccess: () => {
      console.log("usePostVoiceComplete Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostVoiceComplete };
