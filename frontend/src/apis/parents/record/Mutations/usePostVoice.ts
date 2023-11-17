import { useMutation } from "@tanstack/react-query";
import { postVoice } from "@/apis/parents/record/recordAPI";

const usePostVoice = () => {
  return useMutation(
    ({
      file,
      gender,
      title,
      scriptNum,
    }: {
      file: File;
      gender: string;
      title: string;
      scriptNum: number;
    }) => {
      return postVoice(file, gender, title, scriptNum);
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
