import { useQuery } from "@tanstack/react-query";
import { getChildrenCode } from "@/apis/children/profile/profileAPI";

const useChildrenCode = (code: number) => {
  const queryKey = ["ChildrenCode", code];

  const fetchData = () => {
    return getChildrenCode(code);
  };

  const { data } = useQuery(queryKey, fetchData, {
    enabled: Boolean(code), // Only enable the query when nowDate is truthy
    suspense: false,
  });

  return data;
};

export { useChildrenCode };
