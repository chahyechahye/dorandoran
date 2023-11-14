import { useMutation } from "@tanstack/react-query";
import { postSaveRecord } from "@/apis/parents/record/recordAPI";

const usePostSaveRecord = () => {
  return useMutation((gender: string) => postSaveRecord(gender), {
    onSuccess: () => {
      console.log("usePostSaveRecord Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostSaveRecord };
