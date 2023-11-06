import { useMutation } from "@tanstack/react-query";
import { postChildrenLike } from "@/apis/children/like/likeAPI";

const useChildrenLike = () => {
  return useMutation((bookId: number) => postChildrenLike(bookId), {
    onSuccess: () => {
      console.log("성공");
    },
    onError: (err: Error) => {
      console.log("Error in useChildrenLike:", err);
    },
  });
};

export { useChildrenLike };
