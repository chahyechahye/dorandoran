import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postChildrenCharacter } from "@/apis/children/profile/profileAPI";

const useChildrenCharacter = () => {
  return useMutation((animalId: number) => postChildrenCharacter(animalId), {
    onSuccess: () => {
      console.log("성공");
    },
    onError: (err: Error) => {
      console.log("Error in useChildrenCharacter:", err);
    },
  });
};

export { useChildrenCharacter };
