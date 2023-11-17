import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postChildrenLogin } from "@/apis/children/profile/profileAPI";
import { ChildrenLoginProps } from "@/types/children/profileType";

const useChildrenLogin = () => {
  const queryClient = useQueryClient();

  return useMutation(
    (ChildrenLoginData: ChildrenLoginProps) =>
      postChildrenLogin(ChildrenLoginData),
    {
      onSuccess: () => {
        queryClient.invalidateQueries(["ChildrenLogin"]);
      },
      onError: (err: Error) => {
        console.log("Error in useChildrenCLogin:", err);
      },
    }
  );
};

export { useChildrenLogin };
