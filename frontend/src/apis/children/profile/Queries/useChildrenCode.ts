import { useQuery } from "@tanstack/react-query";
import { getChildrenCode } from "@/apis/children/profile/profileAPI";

const useChildrenCode = (code: number) => {
  const { data } = useQuery(["ChildrenCode", code], () =>
    getChildrenCode(code)
  );
  return data;
};

export { useChildrenCode };
