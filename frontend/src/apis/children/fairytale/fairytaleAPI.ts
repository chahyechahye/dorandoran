import axios from "axios";

const getFairytaleList = async () => {
  try {
    const response = await axios.get(`/book`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getFairytaleList");
  }
};

export { getFairytaleList };
