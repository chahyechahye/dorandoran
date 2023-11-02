import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postChildrenCharacter } from "@/apis/children/profile/profileAPI";

const useChildrenCharacter = () => {
  const queryClient = useQueryClient();

  return useMutation((animalId: number) => postChildrenCharacter(animalId), {
    onSuccess: () => {
      queryClient.invalidateQueries(["ChildrenCharacter"]);
    },
    onError: (err: Error) => {
      console.log("Error in useChildrenCharacter:", err);
    },
  });
};

export { useChildrenCharacter };
