import { useMutation } from "@tanstack/react-query";
import { postProfileChange } from "@/apis/parents/profile/profileAPI";
import { ProfileChangeProps } from "@/types/parent/profileType";

const usePostProfileChange = () => {
  return useMutation(
    (profileId: ProfileChangeProps) => postProfileChange(profileId),
    {
      onSuccess: () => {
        console.log("usePostProfileChange Success");
      },
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { usePostProfileChange };
