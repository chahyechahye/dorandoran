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

const postVoice = async (
  file: File,
  gender: string,
  title: string,
  scriptNum: number
) => {
  try {
    const config = {
      headers: { "Content-Type": "multipart/form-data" },
    };

    const response = await instance.post(
      `/voice`,
      {
        file: file,
        gender: gender,
        title: title,
        scriptNum: scriptNum,
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

const postVoiceComplete = async (genders: string) => {
  try {
    const response = await instance.post("/voice/complete", {
      genders: genders,
    });
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postVoiceComplete"));
  }
};

const postSaveRecord = async (genders: string) => {
  try {
    const response = await instance.post(`/record/save`, {
      genders: genders,
    });
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postSaveRecord"));
  }
};

const deleteVoice = async (genders: string) => {
  try {
    const response = await instance.delete(`/voice/${genders}`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - deleteVoice"));
  }
};

export {
  getRecord,
  postVoice,
  postAlarmMessage,
  postVoiceComplete,
  postSaveRecord,
  deleteVoice,
};
