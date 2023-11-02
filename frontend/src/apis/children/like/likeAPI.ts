import { instance } from "@/apis/instance";

const postChildrenLike = async (bookId: number) => {
  try {
    const response = await instance.post(`/profile/login`, bookId);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postChildrenLike");
  }
};

export { postChildrenLike };
