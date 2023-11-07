import { instance } from "@/apis/instance";

const postLetter = async (
  title: string,
  content: FormData,
  profileId: number,
  senderId: number
) => {
  try {
    const config = {
      headers: { "Content-Type": "multipart/form-data" },
    };

    const response = await instance.post(
      `/letter`,
      {
        title: title,
        content: content,
        profileId: profileId,
        senderId: senderId,
      },
      config
    );
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
