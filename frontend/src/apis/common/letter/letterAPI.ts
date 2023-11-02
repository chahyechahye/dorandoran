import { instance } from "@/apis/instance";

const postLetter = async (formData: FormData) => {
  try {
    const config = {
      headers: { "Content-Type": "multipart/form-data" },
    };

    const response = await instance.post(`/letter`, formData, config);
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

export { postLetter, getLetter };
