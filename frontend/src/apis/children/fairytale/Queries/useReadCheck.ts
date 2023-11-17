import { useQuery } from "@tanstack/react-query";
import { getReadCheck } from "@/apis/children/fairytale/fairytaleAPI";

const useGetReadCheck = () => {
  const { data } = useQuery(["ReadCheck"], () => getReadCheck());
  return data;
};

export { useGetReadCheck };
