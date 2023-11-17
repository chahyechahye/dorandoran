import { useMutation } from "@tanstack/react-query";
import { deleteVoice } from "@/apis/parents/record/recordAPI";

const useDeleteVoice = () => {
  return useMutation((genders: string) => deleteVoice(genders), {
    onSuccess: () => {
      console.log("useDeleteVoice Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useDeleteVoice };
