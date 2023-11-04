import { ProfileProps } from "@/types/parent/profileType";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postProfile } from "@/apis/parents/profile/profileAPI";

const usePostProfile = () => {
  const queryCilent = useQueryClient();
  return useMutation((name: ProfileProps) => postProfile(name), {
    onSuccess: () => {
      console.log("usePostProfile Success");
      queryCilent.invalidateQueries(["ProfileList"]);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostProfile };
