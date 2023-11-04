import axios from "axios";

const getFairytaleList = async () => {
  try {
    const response = await axios.get(`https://dorandoran.site/api/book`);
    console.log("완료2");
    return response.data;
  } catch (error) {
    console.error("API 연동 오류 - getFairytaleList:", error);
    new Error("api 연동 오류 - getFairytaleList");
  }
};

export { getFairytaleList };
