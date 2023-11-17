import { useQuery } from "@tanstack/react-query";
import { getProfileList } from "@/apis/parents/profile/profileAPI";

const useGetProfileList = () => {
  const { data } = useQuery(["ProfileList"], () => getProfileList());
  return data;
};

export { useGetProfileList };
