import { useQuery } from "@tanstack/react-query";
import { getLetter } from "@/apis/common/letter/letterAPI";

const useGetLetterList = () => {
  const { data } = useQuery(["LetterList"], () => getLetter());
  return data;
};

export { useGetLetterList };
