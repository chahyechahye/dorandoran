import { instance } from "@/apis/instance";
import { ChildrenLoginProps } from "@/types/children/profileType";
import axios from "axios";

const getChildrenCode = async (code: number) => {
  try {
    const response = await axios.get(
      `https://dorandoran.site/api/profile?code=${code}`
    );

    return response.data;
  } catch {
    new Error("api 연동 오류 - getChildrenCode");
  }
};

const postChildrenLogin = async (ChildrenLoginData: ChildrenLoginProps) => {
  try {
    const response = await axios.post(
      `https://dorandoran.site/api/profile/login`,
      ChildrenLoginData
    );

    const accessToken = response.headers.accesstoken;
    localStorage.setItem("accessToken", accessToken);

    return response.data;
  } catch {
    new Error("api 연동 오류 - postChildrenLogin");
  }
};

const postChildrenCharacter = async (animalId: string) => {
  try {
    const response = await instance.post(`/profile/animal`, animalId);
    return response;
  } catch {
    new Error("api 연동 오류 - postChildrenCharacter");
  }
};

export { getChildrenCode, postChildrenLogin, postChildrenCharacter };
