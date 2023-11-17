import { useQuery } from "@tanstack/react-query";
import { getRecord } from "@/apis/parents/record/recordAPI";

const useGetRecord = () => {
  const { data } = useQuery(["script"], () => getRecord());
  return data;
};

export { useGetRecord };
