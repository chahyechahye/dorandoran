import { useQuery } from "@tanstack/react-query";
import { getFairytaleList } from "@/apis/children/fairytale/fairytaleAPI";

const useFairytaleList = () => {
  const { data } = useQuery(["FairytaleList"], () => getFairytaleList());
  return data;
};

export { useFairytaleList };
