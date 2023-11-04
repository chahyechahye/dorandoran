import { useMutation } from "@tanstack/react-query";
import { postMessage } from "@/apis/parents/profile/profileAPI";
import { MessageProps } from "@/types/parent/profileType";

const usePostMessage = () => {
  return useMutation((tel: MessageProps) => postMessage(tel), {
    onSuccess: () => {
      console.log("usePostMessage Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostMessage };
