import { FairytaleSearchProps } from "@/types/children/fairytaleType";
import { postFairytaleRead } from "../fairytaleAPI";
import { useMutation } from "@tanstack/react-query";
import { useQueryClient } from "react-query";

const useFairytaleRead = () => {
  const queryClient = useQueryClient();

  return useMutation(
    (fairytaleSearch: FairytaleSearchProps) =>
      postFairytaleRead(fairytaleSearch),
    {
      onSuccess: () => {
        queryClient.invalidateQueries(["fairytaleSearch"]);
      },
      onError: (err: Error) => {
        console.log("Error in useFairytaleRead:", err);
      },
    }
  );
};

export { useFairytaleRead };
