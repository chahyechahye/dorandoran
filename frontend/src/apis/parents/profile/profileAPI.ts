import { instance } from "@/apis/instance";
import {
  ProfileProps,
  ProfileChangeProps,
  MessageProps,
} from "@/types/parent/profileType";

const postProfile = async (name: ProfileProps) => {
  try {
    const response = await instance.post(`/profile`, name);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postProfile"));
  }
};

const postMessage = async (tel: MessageProps) => {
  try {
    const response = await instance.post("/user/message", tel);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postMessage"));
  }
};

const postProfileChange = async (profileId: ProfileChangeProps) => {
  try {
    const response = await instance.post("profile/change", profileId);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postProfileChange"));
  }
};

const getProfileList = async () => {
  try {
    const response = await instance.get(`profile/list`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - getProfileList"));
  }
};

export { postProfile, postProfileChange, postMessage, getProfileList };
