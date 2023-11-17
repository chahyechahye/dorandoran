import { useMutation } from "@tanstack/react-query";
import { postMessage } from "@/apis/parents/profile/profileAPI";
import { MessageProps } from "@/types/parent/profileType";

const usePostAlarmMessage = () => {
  return useMutation((tel: MessageProps) => postMessage(tel), {
    onSuccess: () => {
      console.log("usePostAlarmMessage Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostAlarmMessage };
