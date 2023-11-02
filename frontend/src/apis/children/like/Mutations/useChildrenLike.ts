import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postChildrenLike } from "@/apis/children/like/likeAPI";

const useChildrenLike = () => {
  const queryClient = useQueryClient();

  return useMutation((bookId: number) => postChildrenLike(bookId), {
    onSuccess: () => {
      queryClient.invalidateQueries(["ChildrenLike"]);
    },
    onError: (err: Error) => {
      console.log("Error in useChildrenLike:", err);
    },
  });
};

export { useChildrenLike };
