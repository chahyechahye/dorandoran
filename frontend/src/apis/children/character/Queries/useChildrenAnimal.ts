import { useQuery } from "@tanstack/react-query";
import { getChildrenAnimal } from "@/apis/children/character/characterAPI";

const useChildrenAnimal = () => {
  const { data } = useQuery(["ChildrenAnimal"], () => getChildrenAnimal());
  return data;
};

export { useChildrenAnimal };
