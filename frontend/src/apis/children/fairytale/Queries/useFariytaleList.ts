import { useQuery } from "@tanstack/react-query";
import { getFairytaleList } from "@/apis/children/fairytale/fairytaleAPI";

const useFairytaleList = () => {
  const { data } = useQuery(["FairytaleList"], () => getFairytaleList());
  console.log("완료1");
  return data;
};

export { useFairytaleList };
