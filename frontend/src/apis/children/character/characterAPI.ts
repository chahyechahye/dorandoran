import { instance } from "@/apis/instance";

const getChildrenAnimal = async () => {
  try {
    const response = await instance.get(`/animal/all`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getChildrenAnimal");
  }
};

export { getChildrenAnimal };
