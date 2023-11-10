import { instance } from "@/apis/instance";
import { LetterProps } from "@/types/parent/letterType";

const postLetter = async (letterData: LetterProps) => {
  try {
    const config = {
      headers: { "Content-Type": "multipart/form-data" },
    };

    const response = await instance.post(`/letter`, letterData, config);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postLetter"));
  }
};

const getLetter = async () => {
  try {
    const response = await instance.get(`/letter`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - getLetter"));
  }
};

const postLetterRead = async () => {
  try {
    const response = await instance.post(`/letter/read`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postLetterRead"));
  }
};

export { postLetter, getLetter, postLetterRead };
