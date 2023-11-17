import { instance } from "@/apis/instance";
import { FairytaleSearchProps } from "@/types/children/fairytaleType";

const getFairytaleList = async () => {
  try {
    const response = await instance.get(`/book`);
    return response.data;
  } catch (error) {
    console.error("API 연동 오류 - getFairytaleList:", error);
    new Error("api 연동 오류 - getFairytaleList");
  }
};

const postFairytaleRead = async (FairytaleSearchData: FairytaleSearchProps) => {
  try {
    const response = await instance.post(`/page/all`, FairytaleSearchData);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postFairytaleRead");
  }
};

const getReadCheck = async () => {
  try {
    const response = await instance.get(`/content/voice/check`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getReadCheck");
  }
};

export { getFairytaleList, postFairytaleRead, getReadCheck };
