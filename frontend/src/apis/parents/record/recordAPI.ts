import { instance } from "@/apis/instance";
import { MessageProps } from "@/types/parent/profileType";

const getRecord = async () => {
  try {
    const response = await instance.get(`/record`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - getRecord"));
  }
};

const postVoice = async (file: File, gender: string) => {
  try {
    const config = {
      headers: { "Content-Type": "multipart/form-data" },
    };

    const response = await instance.post(
      `/voice`,
      {
        file: file,
        gender: gender,
      },
      config
    );
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postVoice"));
  }
};

const postAlarmMessage = async (tel: MessageProps) => {
  try {
    const response = await instance.post("/voice/tel", tel);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postAlarmMessage"));
  }
};

const postVoiceComplete = async () => {
  try {
    const response = await instance.post("/voice/complete");
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postVoiceComplete"));
  }
};

export { getRecord, postVoice, postAlarmMessage, postVoiceComplete };
