import { ProfileProps } from "@/types/parent/profileType";
import { useMutation } from "@tanstack/react-query";
import { postProfile } from "@/apis/parents/profile/profileAPI";

const usePostProfile = () => {
  return useMutation((name: ProfileProps) => postProfile(name), {
    onSuccess: () => {
      console.log("usePostProfile Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostProfile };
