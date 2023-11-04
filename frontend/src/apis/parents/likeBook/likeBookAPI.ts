import { instance } from "@/apis/instance";
import { LikeBookProps } from "@/types/parent/likeBookType";

const getFavoriteBook = async (profileId: number) => {
  try {
    const response = await instance.get(`/favorite?profileId=${profileId}`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - getProfileList"));
  }
};

export { getFavoriteBook };
